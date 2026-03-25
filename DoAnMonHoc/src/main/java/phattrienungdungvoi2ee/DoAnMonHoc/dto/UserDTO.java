package phattrienungdungvoi2ee.DoAnMonHoc.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {
	private String username;
	private String email;
	@Size(min = 6, message = "Mật khẩu phải có ít nhất 6 ký tự")
	private String password;
	private String displayName;
	private String avatarUrl;
	private String role;
	private Boolean active;
}
