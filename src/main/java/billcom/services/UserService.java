package billcom.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import billcom.entities.User;
import billcom.entities.UserDto;
import billcom.exception.ResourceNotFoundException;
import billcom.repositories.UserRepository;

@Service
public class UserService {

	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public List<User> getAllUsers() {

		return (List<User>) userRepository.findAll();
	}

	public UserDto getOneUser(long id) {
		Optional<User> u = this.userRepository.findById(id);
		if (u.isPresent()) {
			return UserDto.toDto(u.get());
		} else {
			return null;
		}
	}

	public User editUser(Long userId, User userRequest) {
		return userRepository.findById(userId).map(user -> {
			user.setEmail(userRequest.getEmail());
			user.setName(userRequest.getName());
			user.setLastName(userRequest.getLastName());
			user.setPhone(userRequest.getPhone());
			return userRepository.save(user);
		}).orElseThrow(() -> new ResourceNotFoundException("userId " + userId + " not found"));
	}

}
