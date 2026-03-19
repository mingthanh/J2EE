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
@Table(name = "priority_type")
public class PriorityType {

	@Id
	private Integer id;

	@Column(length = 100)
	private String name;

	private Integer level;

	@OneToMany(mappedBy = "priority")
	@JsonIgnore
	private List<Issue> issues;
}
