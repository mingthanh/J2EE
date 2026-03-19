package phattrienungdungvoi2ee.DoAnMonHoc.service;

import java.util.List;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.CommentDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Comment;

public interface CommentService {
	Comment addComment(CommentDTO dto);
	List<Comment> getByIssue(String issueId);
}
