package org.filetransfer.AnimalFriendsDad.Security;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	
	@Autowired
	public UserRepositoryAuthenticationProvider authenticationProvider;
	
	@Autowired
	RepositoryUserDetailsService userDetailsService;
	
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10, new SecureRandom());
	}
	
	@Override
	 protected void configure(HttpSecurity http) throws Exception {
		
		//Paginas publicas -- añadir todas las paginas publicas
		http.authorizeRequests().antMatchers("/").permitAll();
		http.authorizeRequests().antMatchers("/principal").permitAll();
		http.authorizeRequests().antMatchers("/principal1").permitAll();
		
		http.authorizeRequests().antMatchers("/login").permitAll();
		http.authorizeRequests().antMatchers("/loginUsuario").permitAll();
		
		http.authorizeRequests().antMatchers("/registrar").permitAll();
		http.authorizeRequests().antMatchers("/registrarUsuario").permitAll();
		http.authorizeRequests().antMatchers("/registrarUsuari").permitAll();
		http.authorizeRequests().antMatchers("/registerWeb").permitAll();
		
		http.authorizeRequests().antMatchers("/animales").permitAll();
		http.authorizeRequests().antMatchers("/localizaciones").permitAll();
		http.authorizeRequests().antMatchers("/productos").permitAll();
		
		http.authorizeRequests().antMatchers("/animales/{{id}}").permitAll();
		http.authorizeRequests().antMatchers("/locales/{{id}}").permitAll();
		http.authorizeRequests().antMatchers("/productos/{{id}}").permitAll();
		
		http.authorizeRequests().antMatchers("/plantilla.css").permitAll();
		http.authorizeRequests().antMatchers("/estilos.css").permitAll();
		http.authorizeRequests().antMatchers("/indexPrueba").permitAll();
		http.authorizeRequests().antMatchers("/principal.css").permitAll();
		http.authorizeRequests().antMatchers("/animales.jpg").permitAll();
		http.authorizeRequests().antMatchers("/hoteles.jpg").permitAll();
		http.authorizeRequests().antMatchers("/collares.jpg").permitAll();
		http.authorizeRequests().antMatchers("/capuchilo.jpg").permitAll();
		http.authorizeRequests().antMatchers("/erizo.jpg").permitAll();
		http.authorizeRequests().antMatchers("/pelota.jpg").permitAll();
		http.authorizeRequests().antMatchers("/piensosgatos.jpg").permitAll();
		http.authorizeRequests().antMatchers("/paginassecundarias.css").permitAll();
		http.authorizeRequests().antMatchers("/paginaprincipal.css").permitAll();
		http.authorizeRequests().antMatchers("/fondo.jpg").permitAll();
		
		http.authorizeRequests().antMatchers("/animales/new").permitAll();
		http.authorizeRequests().antMatchers("/animales/new/created").permitAll();
		http.authorizeRequests().antMatchers("/logout").permitAll();
		
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/animales/{id}/delete").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/localizaciones/{id}/delete").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE,"/produsctos/{id}/delete").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/newLocalizacion").hasAnyRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/newProducto").hasAnyRole("ADMIN");
		//paginas privadas -- se añaden automaticamente
		http.authorizeRequests().anyRequest().permitAll();		

		
		//  login
		http.formLogin().loginPage("/login");
		http.formLogin().usernameParameter("nombre");
		http.formLogin().passwordParameter("psw");
		http.formLogin().defaultSuccessUrl("/");
		http.formLogin().failureUrl("/login");

		// Logout
		http.logout().logoutUrl("/logout");
		http.logout().logoutSuccessUrl("/");
		
		// Disable CSRF at the moment
		/*http.csrf().disable();
		
		
		  // Enable Basic Authentication
        http.httpBasic();
    		
        // Disable Form login Authentication
        http.formLogin().disable();

        // Avoid creating session (because every request has credentials) 
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		*/
	}
		
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// User
		/*auth.inMemoryAuthentication()
		.withUser("Jose").password("1234").roles("USER");
		
		auth.inMemoryAuthentication().withUser("admin").password("adminpass").roles("USER","ADMIN");
*/
		//auth.authenticationProvider(authenticationProvider);
		
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	
	
	

}
