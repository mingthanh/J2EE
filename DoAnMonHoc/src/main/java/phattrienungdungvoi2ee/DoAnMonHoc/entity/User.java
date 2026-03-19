package phattrienungdungvoi2ee.DoAnMonHoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

	@Id
	@Column(length = 36)
	private String id;

	@Column(length = 50)
	private String username;

	@Column(length = 100)
	private String email;

	@Column(length = 255)
	private String password;

	@Column(name = "display_name", length = 100)
	private String displayName;

	@Column(length = 50)
	private String role;

	private Boolean active;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@OneToMany(mappedBy = "lead", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Project> leadProjects;

	@OneToMany(mappedBy = "reporter")
	@JsonIgnore
	private List<Issue> reportedIssues;

	@OneToMany(mappedBy = "assignee")
	@JsonIgnore
	private List<Issue> assignedIssues;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Comment> comments;

	@OneToMany(mappedBy = "user")
	@JsonIgnore
	private List<Board> boards;

	@PrePersist
	public void prePersist() {
		if (id == null || id.isBlank()) {
			id = UUID.randomUUID().toString();
		}
		if (createdAt == null) {
			createdAt = LocalDateTime.now();
		}
		if (active == null) {
			active = true;
		}
	}
}
