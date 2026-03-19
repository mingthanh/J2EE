package phattrienungdungvoi2ee.DoAnMonHoc.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, String> {
	List<Comment> findByIssue_Id(String issueId);
}
