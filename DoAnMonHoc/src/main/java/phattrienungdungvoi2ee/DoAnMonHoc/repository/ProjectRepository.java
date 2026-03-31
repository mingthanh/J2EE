package phattrienungdungvoi2ee.DoAnMonHoc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, String> {

	@Override
	@EntityGraph(attributePaths = "lead")
	List<Project> findAll();

	@Query("""
		select distinct p
		from Project p
		left join fetch p.lead
		join ProjectMember pm on pm.project = p
		where pm.user.username = :username
	""")
	List<Project> findAllByMemberUsername(String username);

	boolean existsByKey(String key);
}
