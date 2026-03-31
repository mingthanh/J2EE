package phattrienungdungvoi2ee.DoAnMonHoc.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.IssueRequest;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.IssueResponse;
import phattrienungdungvoi2ee.DoAnMonHoc.mapper.IssueMapper;
import phattrienungdungvoi2ee.DoAnMonHoc.service.IssueService;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

	private final IssueService issueService;
	private final IssueMapper issueMapper;

	public IssueController(IssueService issueService, IssueMapper issueMapper) {
		this.issueService = issueService;
		this.issueMapper = issueMapper;
	}

	@GetMapping
	public List<IssueResponse> getAll() {
		return issueService.getAll().stream().map(issueMapper::toResponse).toList();
	}

	@GetMapping("/{id}")
	public IssueResponse getById(@PathVariable String id) {
		return issueMapper.toResponse(issueService.getById(id));
	}

	@GetMapping("/status/{statusId}")
	public List<IssueResponse> getByStatus(@PathVariable Integer statusId) {
		return issueService.findByStatusId(statusId).stream().map(issueMapper::toResponse).toList();
	}

	@GetMapping("/project/{projectId}")
	public List<IssueResponse> getByProject(@PathVariable String projectId) {
		return issueService.findByProjectId(projectId).stream().map(issueMapper::toResponse).toList();
	}

	@PostMapping
	public IssueResponse create(@RequestBody IssueRequest request) {
		return issueMapper.toResponse(issueService.create(request));
	}

	@PutMapping("/{id}")
	public IssueResponse update(@PathVariable String id, @RequestBody IssueRequest request) {
		return issueMapper.toResponse(issueService.update(id, request));
	}

	@PutMapping("/{id}/assign/{userId}")
	@PreAuthorize("@authorizationService.canUpdateIssue(#id)")
	public IssueResponse assign(@PathVariable String id, @PathVariable String userId) {
		return issueMapper.toResponse(issueService.assignUser(id, userId));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		issueService.delete(id);
	}
}
