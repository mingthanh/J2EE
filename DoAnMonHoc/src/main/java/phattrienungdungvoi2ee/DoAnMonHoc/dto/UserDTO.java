package phattrienungdungvoi2ee.DoAnMonHoc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
	private String username;
	private String email;
	private String password;
	private String displayName;
	private String role;
	private Boolean active;
}
