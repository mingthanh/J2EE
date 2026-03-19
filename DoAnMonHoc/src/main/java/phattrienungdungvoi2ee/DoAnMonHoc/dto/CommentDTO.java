package phattrienungdungvoi2ee.DoAnMonHoc.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CommentDTO {
	private String issueId;
	private String userId;
	private String content;
}
