/**
 * Copyright 2018 FOLIL, Todos los derechos reservados.
 */
package cl.tutorial.owasp.a2autenticacion.insegura.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import cl.tutorial.owasp.a2autenticacion.insegura.modelo.enums.RolesEnum;
import cl.tutorial.owasp.a2autenticacion.insegura.util.EncripatadorUtil;

/**
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

	@Autowired
	public void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(encriptadorUtil.getBCryptPasswordEncoder());
	}

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable();
		// permitidos
		http.authorizeRequests()
				.antMatchers(URL_LOGIN, URL_LOGOUT, "/webjars/**", "/css/**", "/img/**", "/js/**", "/robots.txt")
				.permitAll();

		// restricciones
		String rolesAcceso = String.format(TODOS_ROLES_PERMITIDOS, RolesEnum.ROLE_ADMIN, RolesEnum.ROLE_USER);
		http.authorizeRequests().antMatchers("/**").access(rolesAcceso);

		http.formLogin().loginProcessingUrl(URL_LOGIN).loginPage(URL_LOGIN) //
				.defaultSuccessUrl("/seguro/index").usernameParameter("username").passwordParameter("password").and() //
				.logout().logoutUrl(URL_LOGOUT).logoutSuccessUrl("/login?sesion");
	}
}
