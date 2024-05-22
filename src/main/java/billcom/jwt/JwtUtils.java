package billcom.jwt;


import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import billcom.services.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;


@Component
public class JwtUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
	
	  @Value("${jwtSecret}")
	  private String jwtSecret;
	  
	  @Value("${jwtExpirationMs}")
	  private int jwtExpirationMs;
	  
	  @PostConstruct //To validate the jwtSecret when the application starts public
	  void validateSecret() { if (jwtSecret == null || jwtSecret.length() < 600) {
	  throw new
	  IllegalArgumentException("JWT secret key must be at least 32 characters long"
	  ); } }
	  
	  
	  public String generateJwtToken(Authentication authentication) {
		  
		  UserDetailsImpl userDetailsImpl = (UserDetailsImpl) authentication.getPrincipal();
		  
		return Jwts.builder() 
			.setSubject((userDetailsImpl.getUsername()))
	        .setIssuedAt(new Date())
	        .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
	        .claim("roles", userDetailsImpl.getAuthorities()) //additional context and security:
	        .signWith(key(), SignatureAlgorithm.HS256)
	        .compact();
	  }
	  
	  private Key key() { byte[] secret = jwtSecret.getBytes(); return
	  Keys.hmacShaKeyFor(secret);
	  }
	  
	  
  public String getUserNameFromJwtToken(String token) { //how to extract a username from a JWT token.
    return Jwts.parserBuilder().setSigningKey(key()).build()
               .parseClaimsJws(token).getBody().getSubject();
  }
  
  public boolean validateJwtToken(String authToken) {
	    try {
	      Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
	      return true;
	    } catch (MalformedJwtException e) {
	      logger.error("Invalid JWT token: {}", e.getMessage());
	    } catch (ExpiredJwtException e) {
	      logger.error("JWT token is expired: {}", e.getMessage());
	    } catch (UnsupportedJwtException e) {
	      logger.error("JWT token is unsupported: {}", e.getMessage());
	    } catch (IllegalArgumentException e) {
	      logger.error("JWT claims string is empty: {}", e.getMessage());
	    }

	    return false;
	  }

	  
	  
	  
	  
	  
	  
	  
	  
	  
}
