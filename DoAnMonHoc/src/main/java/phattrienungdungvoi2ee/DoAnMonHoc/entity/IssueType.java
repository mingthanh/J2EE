package phattrienungdungvoi2ee.DoAnMonHoc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "issue_type")
public class IssueType {

	@Id
	private Integer id;

	@Column(length = 100)
	private String name;

	@OneToMany(mappedBy = "type")
	@JsonIgnore
	private List<Issue> issues;
}
