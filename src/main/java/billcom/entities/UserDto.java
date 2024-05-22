package billcom.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	
	 
	private Long id;
	private String email;
	private String name;
	private String lastname;
	private String phone;
	
	public static UserDto toDto(User en) {
		
		return UserDto.builder()
				.id(en.getId())
				.email(en.getEmail())
				.name(en.getName())
				.lastname(en.getLastName())
				.phone(en.getPhone())
				.build();
	}

}
