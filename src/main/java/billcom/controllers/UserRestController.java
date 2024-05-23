package billcom.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import billcom.entities.dto.UserDto;
import billcom.services.UserService;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/restUser")
public class UserRestController {
	
	private UserService userService;

	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	
    @GetMapping("/list")
    public ResponseEntity<List<UserDto>> getUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long id) {
        UserDto userDto = userService.getOneUser(id);
        return ResponseEntity.ok(userDto);
    }
    
    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserDto> editUser(@PathVariable Long id,@Valid  @RequestBody UserDto userDto) {
    	UserDto updatedUser = userService.editUser(id, userDto);
        return ResponseEntity.ok(updatedUser);
    }
    
    @GetMapping("/connectionStatus")
    public ResponseEntity<String> getConnectionStatus() {
        String connectionStatus = userService.getUserConnectionStatus();
        HttpStatus httpStatus = connectionStatus.equals("User is connected") ? HttpStatus.OK : HttpStatus.UNAUTHORIZED;
        return new ResponseEntity<>(connectionStatus, httpStatus);
    }


}
