package phattrienungdungvoi2ee.DoAnMonHoc.service.impl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
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
import phattrienungdungvoi2ee.DoAnMonHoc.service.ProjectMemberService;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentRepository commentRepository;
	private final IssueRepository issueRepository;
	private final UserRepository userRepository;
	private final ProjectMemberService projectMemberService;

	public CommentServiceImpl(
		CommentRepository commentRepository,
		IssueRepository issueRepository,
		UserRepository userRepository,
		ProjectMemberService projectMemberService
	) {
		this.commentRepository = commentRepository;
		this.issueRepository = issueRepository;
		this.userRepository = userRepository;
		this.projectMemberService = projectMemberService;
	}

	@Override
	public Comment addComment(CommentDTO dto) {
		Issue issue = issueRepository.findById(dto.getIssueId())
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));
		if (issue.getProject() != null) {
			projectMemberService.validateCurrentUserMember(issue.getProject().getId());
		}
		if (dto.getContent() == null || dto.getContent().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment content is required");
		}
		String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(currentUsername)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
		Comment comment = new Comment();
		comment.setIssue(issue);
		comment.setUser(user);
		comment.setContent(dto.getContent().trim());
		return commentRepository.save(comment);
	}

	@Override
	public List<Comment> getByIssue(String issueId) {
		Issue issue = issueRepository.findById(issueId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));
		if (issue.getProject() != null) {
			projectMemberService.validateCurrentUserMember(issue.getProject().getId());
		}
		return commentRepository.findByIssue_IdOrderByCreatedAtAsc(issueId);
	}
}
