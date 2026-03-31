package phattrienungdungvoi2ee.DoAnMonHoc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProjectDTO {
	private String name;
	private String key;
	private String description;
	private String leadId;
	private Boolean active;
}
