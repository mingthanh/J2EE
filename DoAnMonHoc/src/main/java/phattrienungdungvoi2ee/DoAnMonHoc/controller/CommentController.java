package phattrienungdungvoi2ee.DoAnMonHoc.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.CommentDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Comment;
import phattrienungdungvoi2ee.DoAnMonHoc.service.CommentService;

@RestController
@RequestMapping("/api/issues")
public class CommentController {

	private final CommentService commentService;

	public CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/{issueId}/comments")
	public List<Comment> getByIssue(@PathVariable String issueId) {
		return commentService.getByIssue(issueId);
	}

	@PostMapping("/{issueId}/comments")
	public Comment addComment(@PathVariable String issueId, @RequestBody CommentDTO dto) {
		dto.setIssueId(issueId);
		return commentService.addComment(dto);
	}
}
