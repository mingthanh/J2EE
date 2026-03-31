package phattrienungdungvoi2ee.DoAnMonHoc.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectMemberRequest {

	@NotBlank(message = "identifier is required")
	private String identifier;

	private String role;
}
