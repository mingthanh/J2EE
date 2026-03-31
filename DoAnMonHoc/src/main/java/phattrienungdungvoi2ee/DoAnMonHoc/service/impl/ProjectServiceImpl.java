package phattrienungdungvoi2ee.DoAnMonHoc.service.impl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.ProjectDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.ProjectMemberRequest;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Project;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.ProjectMember;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.ProjectMemberRole;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.IssueRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.ProjectRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.UserRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.service.ProjectMemberService;
import phattrienungdungvoi2ee.DoAnMonHoc.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;
	private final IssueRepository issueRepository;
	private final ProjectMemberService projectMemberService;

	public ProjectServiceImpl(
		ProjectRepository projectRepository,
		UserRepository userRepository,
		IssueRepository issueRepository,
		ProjectMemberService projectMemberService
	) {
		this.projectRepository = projectRepository;
		this.userRepository = userRepository;
		this.issueRepository = issueRepository;
		this.projectMemberService = projectMemberService;
	}

	@Override
	public List<Project> getAll() {
		return isSuperAdmin()
			? projectRepository.findAll()
			: projectRepository.findAllByMemberUsername(currentUsername());
	}

	@Override
	public Project getById(String id) {
		Project project = projectRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
		projectMemberService.validateCurrentUserMember(id);
		return project;
	}

	@Override
	public Project create(ProjectDTO dto) {
		Project project = new Project();
		applyDto(project, dto);
		User creator = userRepository.findByUsername(currentUsername())
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
		if (project.getLead() == null) {
			project.setLead(creator);
		}
		Project savedProject = projectRepository.save(project);
		projectMemberService.addProjectAdmin(savedProject.getId(), creator.getId());
		if (savedProject.getLead() != null && !savedProject.getLead().getId().equals(creator.getId())) {
			projectMemberService.addMemberByUserId(
				savedProject.getId(),
				savedProject.getLead().getId(),
				ProjectMemberRole.PM
			);
		}
		return savedProject;
	}

	@Override
	public Project update(String id, ProjectDTO dto) {
		Project project = getById(id);
		projectMemberService.validateCanManageProject(id);
		applyDto(project, dto);
		Project savedProject = projectRepository.save(project);
		if (savedProject.getLead() != null) {
			projectMemberService.addMemberByUserId(
				savedProject.getId(),
				savedProject.getLead().getId(),
				ProjectMemberRole.PM
			);
		}
		return savedProject;
	}

	@Override
	@PreAuthorize("@authorizationService.canManageProject(#id)")
	public void delete(String id) {
		Project project = getById(id);
		if (issueRepository.countByProject_Id(id) > 0) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot delete project that still has issues");
		}
		projectRepository.delete(project);
	}

	@Override
	public ProjectMember addMember(String projectId, ProjectMemberRequest request) {
		return projectMemberService.addMember(projectId, request);
	}

	@Override
	public void removeMember(String projectId, String userId) {
		projectMemberService.removeMember(projectId, userId);
	}

	@Override
	public void leaveProject(String projectId) {
		projectMemberService.leaveProject(projectId);
	}

	@Override
	public List<ProjectMember> getMembers(String projectId) {
		return projectMemberService.getMembers(projectId);
	}

	private void applyDto(Project project, ProjectDTO dto) {
		if (dto.getName() != null) {
			project.setName(dto.getName());
		}
		if (dto.getKey() != null) {
			project.setKey(dto.getKey());
		}
		if (dto.getDescription() != null) {
			project.setDescription(dto.getDescription());
		}
		if (dto.getActive() != null) {
			project.setActive(dto.getActive());
		}
		if (dto.getLeadId() != null) {
			User lead = userRepository.findById(dto.getLeadId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lead user not found"));
			project.setLead(lead);
		}
	}

	private String currentUsername() {
		return org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getName();
	}

	private boolean isSuperAdmin() {
		return org.springframework.security.core.context.SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
			.anyMatch(authority -> "ROLE_SUPER_ADMIN".equals(authority.getAuthority()));
	}
}
