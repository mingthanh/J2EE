package phattrienungdungvoi2ee.DoAnMonHoc.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IssueDTO {
	private String issueKey;
	private String summary;
	private String description;
	private Integer statusId;
	private Integer priorityId;
	private Integer typeId;
	private String projectId;
	private String reporterId;
	private String assigneeId;
	private List<String> assigneeIds;
	private LocalDateTime dueAt;
}
