package billcom.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import billcom.models.request.LoginRequet;
import billcom.models.request.SingupRequest;
import billcom.services.AuthService;
import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
	
	private AuthService authService;

	public AuthRestController(AuthService authService) {
		super();
		this.authService = authService;
	}
	
	@PostMapping("/signUp")
	@ResponseBody
	public ResponseEntity<?> registerUser(@Valid @RequestBody SingupRequest SingupRequest)
	{
		return authService.registerUser(SingupRequest);
	}
	
	@PostMapping("/login")
	@ResponseBody
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequet loginRequet)
	{
		return authService.authenticateUser(loginRequet);
	}
	
	
	
	

}
