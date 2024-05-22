package billcom.jwt;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import billcom.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
	
	  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);
	  private static final String AUTHORIZATION_HEADER = "Authorization";
	  private static final String BEARER_PREFIX = "Bearer ";
	  
	  private JwtUtils jwtUtils;
	  private UserDetailsServiceImpl userDetailsServiceImpl;
	  
	  

	  @Autowired
	public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsServiceImpl userDetailsServiceImpl) {
		this.jwtUtils = jwtUtils;
		this.userDetailsServiceImpl = userDetailsServiceImpl;
	}

	  public AuthTokenFilter() {}


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try{
			   String jwt = parseJwt(request); //method extracts the JWT token from the Authorization header.
			   System.out.println(jwtUtils);
			      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
			        String username = jwtUtils.getUserNameFromJwtToken(jwt);
			        
			        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
			        UsernamePasswordAuthenticationToken authentication =
			        		new UsernamePasswordAuthenticationToken(
			                userDetails,
			                null,
			                userDetails.getAuthorities());
			        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			        SecurityContextHolder.getContext().setAuthentication(authentication);
		          }
	              }catch (Exception e) {
		              logger.error("Cannot set user authentication: {}", e.getMessage());
	              }
		
		          filterChain.doFilter(request, response);
	              }
	 
	
	
	
		
		  private String parseJwt(HttpServletRequest request) {  //Extracts the JWT token from the Authorization header if it starts with "Bearer"
			    String headerAuth = request.getHeader(AUTHORIZATION_HEADER);
			    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER_PREFIX)) {
			        return headerAuth.substring(7);
			    }

			    return null;
			  }
	

}
