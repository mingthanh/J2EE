package phattrienungdungvoi2ee.DoAnMonHoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "issues")
public class Issue {

	@Id
	@Column(length = 36)
	private String id;

	@Column(name = "issue_key", length = 50)
	private String issueKey;

	@Column(length = 255)
	private String summary;

	@Column(columnDefinition = "TEXT")
	private String description;

	@ManyToOne
	@JoinColumn(name = "status_id")
	private StatusType status;

	@ManyToOne
	@JoinColumn(name = "priority_id")
	private PriorityType priority;

	@ManyToOne
	@JoinColumn(name = "type_id")
	private IssueType type;

	@ManyToOne
	@JoinColumn(name = "project_id")
	private Project project;

	@ManyToOne
	@JoinColumn(name = "reporter_id")
	private User reporter;

	@ManyToMany
	@JoinTable(
		name = "issue_assignees",
		joinColumns = @JoinColumn(name = "issue_id"),
		inverseJoinColumns = @JoinColumn(name = "user_id")
	)
	private List<User> assignees = new ArrayList<>();

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "due_at")
	private LocalDateTime dueAt;

	@OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Comment> comments;

	@PrePersist
	public void prePersist() {
		if (id == null || id.isBlank()) {
			id = UUID.randomUUID().toString();
		}
		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}
	}
}
