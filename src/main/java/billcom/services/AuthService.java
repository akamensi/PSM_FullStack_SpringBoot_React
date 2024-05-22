package billcom.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import billcom.entities.ERole;
import billcom.entities.Role;
import billcom.entities.User;
import billcom.jwt.JwtUtils;
import billcom.models.request.LoginRequet;
import billcom.models.request.SingupRequest;
import billcom.models.response.JwtResponse;
import billcom.models.response.MessageReponse;
import billcom.repositories.RoleRepository;
import billcom.repositories.UserRepository;

@Service
public class AuthService {

	private AuthenticationManager authenticationManager;

	private UserRepository userRepository;

	private RoleRepository roleRepository;

	private PasswordEncoder encoder;

	private JwtUtils jwtUtils;

	
	public AuthService(AuthenticationManager authenticationManager, UserRepository userRepository,
			RoleRepository roleRepository, PasswordEncoder encoder, JwtUtils jwtUtils) {
		this.authenticationManager = authenticationManager;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}

	public ResponseEntity<?> registerUser(SingupRequest singupRequest) {

		// checks if the username and email provided in the signup request already exist
		// in the database
		if (userRepository.existsByUsername(singupRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageReponse("Error: Username is already taken!"));
		}
		if (userRepository.existsByEmail(singupRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageReponse("Error: Email is already in use!"));
		}
		// creates a new User entity
		User user = new User(singupRequest.getUsername(), singupRequest.getEmail(),
				encoder.encode(singupRequest.getPassword()), // encodes the password using the PasswordEncoder
				singupRequest.getName(), singupRequest.getLastName(), singupRequest.getPhone());

		Set<String> strRoles = singupRequest.getRole();

		Set<Role> roles = new HashSet<>(); // ce qu'on doit créer et sauvegarder dans la base pour chaque User
	    if (strRoles == null) {
	        Role userRole = roleRepository.findByName(ERole.USER)
	            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	        roles.add(userRole);
	      } else {
	        strRoles.forEach(role -> {
	          switch (role) {
	          case "admin":
	            Role adminRole = roleRepository.findByName(ERole.ADMIN)
	                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	            roles.add(adminRole);

	            break;
	          case "superadmin":
	            Role superAdminRole = roleRepository.findByName(ERole.SUPER_ADMIN)
	                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	            roles.add(superAdminRole);

	            break;
	          default:
	            Role userRole = roleRepository.findByName(ERole.USER)
	                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
	            roles.add(userRole);
	          }
	        });
	      }
		user.setRoles(roles);
		userRepository.save(user);
		return ResponseEntity.ok(new MessageReponse("Inscription avec succès!"));
	}
	
	
	
	public ResponseEntity<?> authenticateUser(LoginRequet loginRequet){
		
		 //1- It authenticates the user using the AuthenticationManager
		
		  Authentication authentication = authenticationManager.authenticate( new
		  UsernamePasswordAuthenticationToken(loginRequet.getUsername(),loginRequet.getPassword()));
		  
		     //2-generates a JWT token using JwtUtils
		    SecurityContextHolder.getContext().setAuthentication(authentication);
		    String jwt = jwtUtils.generateJwtToken(authentication);
		    
		    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		    
		    List<String> roles = userDetails.getAuthorities().stream()
			        .map(item -> item.getAuthority())
			        .collect(Collectors.toList());

			    return ResponseEntity.ok(new JwtResponse(jwt, 
			                         userDetails.getId(), 
			                         userDetails.getUsername(), 
			                         userDetails.getEmail(),
			                         userDetails.getName(),
			                         userDetails.getLastName(),
			                         userDetails.getPhone(),
			                         roles));
	}

}
