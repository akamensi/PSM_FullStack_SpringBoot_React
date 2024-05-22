package billcom.models.response;

import java.util.List;

public class JwtResponse {
	
	  private String token;
	  private String type = "Bearer ";
	  private Long id;
	  private String username;
	  private String email;
	  private String name;
	  private String lastName;
	  private String phone;
	  private List<String> roles;
	  
	  
	public JwtResponse(String accessToken, Long id, String username, String email, String name, String lastName,
			String phone, List<String> roles) {
		super();
		this.token = accessToken;
		this.id = id;
		this.username = username;
		this.email = email;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.roles = roles;
	}



	  public String getAccessToken() {
	    return token;
	  }

	  public void setAccessToken(String accessToken) {
	    this.token = accessToken;
	  }

	  public String getTokenType() {
	    return type;
	  }

	  public void setTokenType(String tokenType) {
	    this.type = tokenType;
	  }

	  public Long getId() {
	    return id;
	  }

	  public void setId(Long id) {
	    this.id = id;
	  }

	  public String getEmail() {
	    return email;
	  }

	  public void setEmail(String email) {
	    this.email = email;
	  }

	  public String getUsername() {
	    return username;
	  }

	  public void setUsername(String username) {
	    this.username = username;
	  }

	  public List<String> getRoles() {
	    return roles;
	  }



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public String getPhone() {
		return phone;
	}



	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
	  
	  

}
