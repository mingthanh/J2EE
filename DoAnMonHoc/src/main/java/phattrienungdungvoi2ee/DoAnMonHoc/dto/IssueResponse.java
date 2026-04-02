package phattrienungdungvoi2ee.DoAnMonHoc.dto;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class IssueResponse {
	private String id;
	private String issueKey;
	private String title;
	private String summary;
	private String description;
	private Integer statusId;
	private String statusCode;
	private String status;
	private Integer priorityId;
	private String priorityCode;
	private String priority;
	private Integer typeId;
	private String typeCode;
	private String type;
	private String projectId;
	private String projectName;
	private String reporterId;
	private String reporterName;
	private String assigneeId;
	private String assigneeName;
	private List<String> assigneeIds;
	private List<String> assigneeNames;
	private LocalDateTime createdAt;
	private LocalDateTime dueAt;
}
