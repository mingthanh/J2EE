package phattrienungdungvoi2ee.DoAnMonHoc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {

	@Override
	@EntityGraph(attributePaths = "lead")
	List<Project> findAll();
}
