package dev.yavuztas.boilerplate.springbootwebapp.config;

import dev.yavuztas.boilerplate.springbootwebapp.service.UserAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

/**
 * Spring Security configuration for Web Endpoints.
 *
 * @author Yavuz Tas
 */
@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserAuthenticationService userAuthenticationService;

	@Override
	public void configure(WebSecurity web) {
		// we need to give in /h2-console
		web.ignoring().requestMatchers(PathRequest.toH2Console());
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// setting custom user details service in order to make authentication from datasource
		// also disable password encoder for simplicity
		auth.userDetailsService(userAuthenticationService).passwordEncoder(NoOpPasswordEncoder.getInstance());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
				.anyRequest().authenticated()
				.and().formLogin()
				.permitAll();
	}

}