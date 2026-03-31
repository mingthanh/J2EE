package phattrienungdungvoi2ee.DoAnMonHoc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, String> {
	List<Issue> findByStatus_Id(Integer statusId);
	List<Issue> findByProject_IdOrderByCreatedAtDesc(String projectId);
	long countByProject_Id(String projectId);

	@Query("""
		select distinct i
		from Issue i
		join i.project p
		join ProjectMember pm on pm.project = p
		where pm.user.username = :username
	""")
	List<Issue> findAllByProjectMemberUsername(String username);

	@Query("""
		select distinct i
		from Issue i
		join i.project p
		join ProjectMember pm on pm.project = p
		where i.status.id = :statusId and pm.user.username = :username
	""")
	List<Issue> findByStatus_IdAndProjectMemberUsername(Integer statusId, String username);
}
