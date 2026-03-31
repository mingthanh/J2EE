package phattrienungdungvoi2ee.DoAnMonHoc.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.ProjectDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.ProjectMemberRequest;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Project;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.ProjectMember;
import phattrienungdungvoi2ee.DoAnMonHoc.service.ProjectService;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {

	private final ProjectService projectService;

	public ProjectController(ProjectService projectService) {
		this.projectService = projectService;
	}

	@GetMapping
	public List<Project> getAll() {
		return projectService.getAll();
	}

	@GetMapping("/{id}")
	public Project getById(@PathVariable String id) {
		return projectService.getById(id);
	}

	@GetMapping("/{id}/members")
	public List<ProjectMember> getMembers(@PathVariable String id) {
		return projectService.getMembers(id);
	}

	@PostMapping
	public Project create(@RequestBody ProjectDTO dto) {
		return projectService.create(dto);
	}

	@PostMapping("/{projectId}/members")
	public ProjectMember addMember(@PathVariable String projectId, @Valid @RequestBody ProjectMemberRequest request) {
		return projectService.addMember(projectId, request);
	}

	@DeleteMapping("/{projectId}/members/{userId}")
	public void removeMember(@PathVariable String projectId, @PathVariable String userId) {
		projectService.removeMember(projectId, userId);
	}

	@DeleteMapping("/{projectId}/members/me")
	public void leaveProject(@PathVariable String projectId) {
		projectService.leaveProject(projectId);
	}

	@PutMapping("/{id}")
	public Project update(@PathVariable String id, @RequestBody ProjectDTO dto) {
		return projectService.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		projectService.delete(id);
	}
}
