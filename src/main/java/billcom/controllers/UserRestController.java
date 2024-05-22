package billcom.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import billcom.entities.User;
import billcom.services.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/restUser")
public class UserRestController {
	
	private UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping("/list")
	@ResponseBody
	public List<User> getAllUser() {
		return (List<User>) userService.getAllUsers();
	}
	
	

}
