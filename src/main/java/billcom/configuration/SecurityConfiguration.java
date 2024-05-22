package billcom.configuration;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import billcom.jwt.AuthEntryPointJwt;
import billcom.jwt.AuthTokenFilter;
import billcom.services.UserDetailsServiceImpl;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
	
	private UserDetailsServiceImpl userDetailsServiceImpl;
	

	public SecurityConfiguration(UserDetailsServiceImpl userDetailsServiceImpl) {
		super();
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}
	
	@Bean
	AuthEntryPointJwt unauthorizedHandler() {
		return new AuthEntryPointJwt();
	}
	
	
	@Bean
	AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}
	
	@Bean
	   PasswordEncoder passwordEncoder() {
		    return new BCryptPasswordEncoder();
		  }
	
	  @Bean
	   DaoAuthenticationProvider authenticationProvider() {
	      DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	  
	  authProvider.setUserDetailsService(userDetailsServiceImpl);
	  authProvider.setPasswordEncoder(passwordEncoder());
	  
	  return authProvider;
	  }
	  
		
	  
		  @Bean
		  SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		  http.csrf(csrf -> csrf.disable()) .exceptionHandling(exception ->
		  exception.authenticationEntryPoint(unauthorizedHandler()))
		  .sessionManagement(session ->
		  session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		  .authorizeHttpRequests(auth ->
		  auth.requestMatchers("/api/auth/**").permitAll()
		  .anyRequest().authenticated());
		  
		  http.authenticationProvider(authenticationProvider());
		  
		  http.addFilterBefore(authenticationJwtTokenFilter(),
		  UsernamePasswordAuthenticationFilter.class);
		  
		  return http.build(); }
		 
	  
		/*
		 * @Bean DefaultSecurityFilterChain configure(HttpSecurity http) throws
		 * Exception { http .csrf().disable() .authorizeRequests()
		 * .requestMatchers("/api/auth/**").permitAll() // Adjust this based on your
		 * registration endpoint .anyRequest().authenticated() .and()
		 * .exceptionHandling().authenticationEntryPoint(unauthorizedHandler());
		 * 
		 * return http.build(); }
		 */
	  
	  
	  @Bean  // pour le AuthService pour manager les authentifaction
	   AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
	    return authConfig.getAuthenticationManager();
	  }
	  
	  
		 @Bean// pour refreche le dossier uploads au lancement de projet
	     WebSecurityCustomizer webSecurityCustomizer() throws Exception {
	        return (web) -> web.ignoring().requestMatchers("/uploads/**");
	    }
	  
	  
	  
	    
	    
	
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

}
