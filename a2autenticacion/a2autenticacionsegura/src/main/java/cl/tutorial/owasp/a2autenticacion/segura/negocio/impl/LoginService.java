/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.segura.negocio.impl;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import cl.tutorial.owasp.a2autenticacion.segura.modelo.dao.IntentoFallidoDao;
import cl.tutorial.owasp.a2autenticacion.segura.modelo.entidad.IntentoFallido;
import cl.tutorial.owasp.a2autenticacion.segura.modelo.enums.TipoBloqueo;
import cl.tutorial.owasp.a2autenticacion.segura.negocio.ILoginService;

/**
 * <p>
 * clase manipula cache de intentos y va guardando en base de datos.
 * </p>
 *
 * @author gerardo
 *
 */
@Service
public class LoginService implements ILoginService {

	private static final int MAX_INTENTO_IP = 10;
	private static final int MAX_INTENTO_USUARIO = 3;
	private LoadingCache<String, Integer> intentosIp;
	private LoadingCache<String, Integer> intentosUsuario;

	@Autowired
	private IntentoFallidoDao intentoFallidoDao;

	@PostConstruct
	public void init() {
		intentosUsuario = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS)
				.build(new CacheLoader<String, Integer>() {
					@Override
					public Integer load(final String key) {
						return 0;
					}
				});
		intentosIp = CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS)
				.build(new CacheLoader<String, Integer>() {
					@Override
					public Integer load(final String key) {
						return 0;
					}
				});
	}

	@Override
	public boolean isUsuarioBloqueado(final String username) {
		return isBloqueado(username, TipoBloqueo.USUARIO);
	}

	@Override
	public boolean isIpBloqueado(final String ip) {
		return isBloqueado(ip, TipoBloqueo.IP);
	}

	@Override
	public void loginExisto(final String username, final String ip) {
		intentosIp.invalidate(ip);
		intentosUsuario.invalidate(username);
	}

	@Override
	public void loginFallido(final IntentoFallido intentoFallido) {
		loginFallido(intentoFallido.getIp(), TipoBloqueo.IP);
		loginFallido(intentoFallido.getUsername(), TipoBloqueo.USUARIO);
		intentoFallidoDao.save(intentoFallido);
	}

	private boolean isBloqueado(final String key, final TipoBloqueo tipoBloqueo) {
		try {
			if (TipoBloqueo.IP.equals(tipoBloqueo)) {
				return intentosIp.get(key) >= MAX_INTENTO_IP;
			} else {
				return intentosUsuario.get(key) >= MAX_INTENTO_USUARIO;
			}
		} catch (ExecutionException e) {
			return false;
		}
	}

	private void loginFallido(final String key, final TipoBloqueo tipoBloqueo) {
		if (key == null || key.trim().isEmpty()) {
			return;
		}
		int intentos = getIntentos(key, tipoBloqueo);
		intentos++;
		setIntentos(key, intentos, tipoBloqueo);
	}

	private void setIntentos(final String key, final int intentos, final TipoBloqueo tipoBloqueo) {
		if (TipoBloqueo.IP.equals(tipoBloqueo)) {
			intentosIp.put(key, intentos);
		} else {
			intentosUsuario.put(key, intentos);
		}
	}

	private int getIntentos(final String key, final TipoBloqueo tipoBloqueo) {
		try {
			if (TipoBloqueo.IP.equals(tipoBloqueo)) {
				return intentosIp.get(key);
			} else {
				return intentosUsuario.get(key);
			}
		} catch (ExecutionException e) {
			return 0;
		}
	}

}
