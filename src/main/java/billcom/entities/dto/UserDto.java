package billcom.entities.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/*@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor*/
public class UserDto {
	
	 
	//private Long id;
	@NotBlank
	@Size(max = 50)
	@Email
	private String email;
	
	@NotBlank
	@Size(max = 20)
	private String name;
	
	@NotBlank
	@Size(max = 20)
	private String lastname;
	
	@NotBlank
	@Size(max = 120)
	private String phone;
	
	
	public UserDto() {}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public UserDto(String email, String name, String lastname, String phone) {
		super();
		this.email = email;
		this.name = name;
		this.lastname = lastname;
		this.phone = phone;
	}
	
	
	
	/*
	 * public static UserDto toDto(User en) {
	 * 
	 * return UserDto.builder() .id(en.getId()) .email(en.getEmail())
	 * .name(en.getName()) .lastname(en.getLastName()) .phone(en.getPhone())
	 * .build(); }
	 */

}

