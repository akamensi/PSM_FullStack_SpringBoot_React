package billcom.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import billcom.entities.User;
import billcom.repositories.UserRepository;



@Service
public class UserDetailsServiceImpl implements UserDetailsService { //It is responsible for loading user details by username during the authentication process
	
	private UserRepository userRepository; 
	
	

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}



	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));

		return UserDetailsImpl.build(user);
	}
}
