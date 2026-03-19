package phattrienungdungvoi2ee.DoAnMonHoc.controller;

import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.IssueDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;
import phattrienungdungvoi2ee.DoAnMonHoc.service.IssueService;

@RestController
@RequestMapping("/api/issues")
public class IssueController {

	private final IssueService issueService;

	public IssueController(IssueService issueService) {
		this.issueService = issueService;
	}

	@GetMapping
	public List<Issue> getAll() {
		return issueService.getAll();
	}

	@GetMapping("/{id}")
	public Issue getById(@PathVariable String id) {
		return issueService.getById(id);
	}

	@GetMapping("/status/{statusId}")
	public List<Issue> getByStatus(@PathVariable Integer statusId) {
		return issueService.findByStatusId(statusId);
	}

	@PostMapping
	public Issue create(@RequestBody IssueDTO dto) {
		return issueService.create(dto);
	}

	@PutMapping("/{id}")
	public Issue update(@PathVariable String id, @RequestBody IssueDTO dto) {
		return issueService.update(id, dto);
	}

	@PutMapping("/{id}/assign/{userId}")
	public Issue assign(@PathVariable String id, @PathVariable String userId) {
		return issueService.assignUser(id, userId);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		issueService.delete(id);
	}
}
