package phattrienungdungvoi2ee.DoAnMonHoc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, String> {
	List<Issue> findByStatus_Id(Integer statusId);
}
