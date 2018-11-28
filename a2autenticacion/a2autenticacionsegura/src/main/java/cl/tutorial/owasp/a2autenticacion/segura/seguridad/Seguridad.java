/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.segura.seguridad;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import cl.tutorial.owasp.a2autenticacion.segura.modelo.enums.RolesEnum;
import cl.tutorial.owasp.a2autenticacion.segura.util.EncripatadorUtil;

/**
 * <p>
 * Clase de configuracion de seguridad
 * </p>
 *
 * @author gerardo
 *
 */
@Configuration
@EnableWebSecurity
public class Seguridad extends WebSecurityConfigurerAdapter {

	private static final String URL_LOGIN = "/login";
	private static final String URL_LOGOUT = "/logout";
	private static final String TODOS_ROLES_PERMITIDOS = "hasAnyRole('%s', '%s')";

	@Autowired
	private UserDetailsService userDetailsServiceImpl;

	@Autowired
	private EncripatadorUtil encriptadorUtil;

	public static final Logger LOGGER = LoggerFactory.getLogger(Seguridad.class);

	@Autowired
	public void configuracionGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		LOGGER.info("Marcando manipulador de autenticacion {}", userDetailsServiceImpl);
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(encriptadorUtil.getBCryptPasswordEncoder());
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		LOGGER.info("Marcando urls permitidas");
		// permitidos
		http.authorizeRequests()
				.antMatchers(URL_LOGIN, URL_LOGOUT, "/webjars/**", "/css/**", "/img/**", "/js/**", "/robots.txt")
				.permitAll();

		String rolesAcceso = String.format(TODOS_ROLES_PERMITIDOS, RolesEnum.ROLE_ADMIN, RolesEnum.ROLE_USER);
		LOGGER.info("Marcando urls restringidas por rol {}", rolesAcceso);
		http.authorizeRequests().antMatchers("/**").access(rolesAcceso);

		LOGGER.info("Configurando proceso de autenticacion");
		http.authorizeRequests().and().formLogin()//
				.failureUrl("/login?error=true").loginProcessingUrl(URL_LOGIN).loginPage(URL_LOGIN)
				.defaultSuccessUrl("/seguro/index").usernameParameter("username").passwordParameter("password").and()
				.logout().invalidateHttpSession(true).logoutUrl(URL_LOGOUT).logoutSuccessUrl("/login?sesion").and()
				.sessionManagement().maximumSessions(3).maxSessionsPreventsLogin(true);
	}

}
