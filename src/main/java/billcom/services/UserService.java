package billcom.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import billcom.entities.User;
import billcom.entities.dto.UserDto;
import billcom.exception.ResourceNotFoundException;
import billcom.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;
	private ModelMapper modelMapper;

	@Autowired
	public UserService(UserRepository userRepository, ModelMapper modelMapper) {
		super();
		this.userRepository = userRepository;
		this.modelMapper = modelMapper;
	}

	
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                    .map(user -> new UserDto(user.getName(),
                    		user.getEmail(),
                    		user.getLastName(),
                    		user.getPhone()))
                    .collect(Collectors.toList());
    }
    
    
    public UserDto getOneUser(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return new UserDto(user.getName(), user.getEmail(), user.getLastName(), user.getPhone());
        } else {
            throw new ResourceNotFoundException("User not found with id: " + id);
        }
    }
	
	
    public UserDto editUser(Long id, UserDto userDto) {
        User user = userRepository.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("userId " + id + " not found"));

        // Update user entity with new data
        user.setEmail(userDto.getEmail());
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastname());
        user.setPhone(userDto.getPhone());

        // Save the updated user entity
        user = userRepository.save(user);

        // Map the updated user entity to a UserDto and return it
        return modelMapper.map(user, UserDto.class);
    }
    
    public String getUserConnectionStatus() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication != null && authentication.isAuthenticated()) {
            // User is authenticated
            return "User is connected";
        } else {
            // User is not authenticated
            return "User is not connected";
        }
    }
	


}
