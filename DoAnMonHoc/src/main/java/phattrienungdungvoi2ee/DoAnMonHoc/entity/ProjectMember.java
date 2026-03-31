package phattrienungdungvoi2ee.DoAnMonHoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(
	name = "project_members",
	uniqueConstraints = @UniqueConstraint(columnNames = {"project_id", "user_id"})
)
public class ProjectMember {

	@Id
	@Column(length = 36)
	private String id;

	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	@JsonIgnore
	private Project project;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private ProjectMemberRole role;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

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
