package phattrienungdungvoi2ee.DoAnMonHoc.service.impl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.CommentDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Comment;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.CommentRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.IssueRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.UserRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final IssueRepository issueRepository;
	private final UserRepository userRepository;

	public CommentServiceImpl(
		CommentRepository commentRepository,
		IssueRepository issueRepository,
		UserRepository userRepository
	) {
		this.commentRepository = commentRepository;
		this.issueRepository = issueRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Comment addComment(CommentDTO dto) {
		Issue issue = issueRepository.findById(dto.getIssueId())
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));
		User user = userRepository.findById(dto.getUserId())
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
		Comment comment = new Comment();
		comment.setIssue(issue);
		comment.setUser(user);
		comment.setContent(dto.getContent());
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> getByIssue(String issueId) {
		return commentRepository.findByIssue_Id(issueId);
	}
}
