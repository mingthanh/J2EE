package phattrienungdungvoi2ee.DoAnMonHoc.service.impl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.ProjectMemberRequest;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Project;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.ProjectMember;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.ProjectMemberRole;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;
import phattrienungdungvoi2ee.DoAnMonHoc.exception.AccessDeniedException;
import phattrienungdungvoi2ee.DoAnMonHoc.exception.DuplicateMemberException;
import phattrienungdungvoi2ee.DoAnMonHoc.exception.UserNotFoundException;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.ProjectMemberRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.ProjectRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.UserRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.service.NotificationService;
import phattrienungdungvoi2ee.DoAnMonHoc.service.ProjectMemberService;

@Service
public class ProjectMemberServiceImpl implements ProjectMemberService {

	private final ProjectMemberRepository projectMemberRepository;
	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;
	private final NotificationService notificationService;

	public ProjectMemberServiceImpl(
		ProjectMemberRepository projectMemberRepository,
		ProjectRepository projectRepository,
		UserRepository userRepository,
		NotificationService notificationService
	) {
		this.projectMemberRepository = projectMemberRepository;
		this.projectRepository = projectRepository;
		this.userRepository = userRepository;
		this.notificationService = notificationService;
	}

	@Override
	public boolean isCurrentUserMember(String projectId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || projectId == null) {
			return false;
		}
		if (isSuperAdmin(authentication)) {
			return true;
		}
		return isMember(projectId, authentication.getName());
	}

	@Override
	public boolean isMember(String projectId, String username) {
		if (projectId == null || username == null || username.isBlank()) {
			return false;
		}
		return projectMemberRepository.existsByProject_IdAndUser_Username(projectId, username);
	}

	@Override
	public boolean isCurrentUserProjectAdmin(String projectId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || projectId == null) {
			return false;
		}
		if (isSuperAdmin(authentication)) {
			return true;
		}
		try {
			return getMembership(projectId, authentication.getName()).getRole().isProjectAdmin();
		} catch (AccessDeniedException ex) {
			return false;
		}
	}

	@Override
	public boolean canManageProject(String projectId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || projectId == null) {
			return false;
		}
		if (isSuperAdmin(authentication)) {
			return true;
		}
		try {
			ProjectMemberRole role = getMembership(projectId, authentication.getName()).getRole();
			return role.canManageProject();
		} catch (AccessDeniedException ex) {
			return false;
		}
	}

	@Override
	public boolean canEditIssues(String projectId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || projectId == null) {
			return false;
		}
		if (isSuperAdmin(authentication)) {
			return true;
		}
		try {
			ProjectMemberRole role = getMembership(projectId, authentication.getName()).getRole();
			return role.canEditIssues();
		} catch (AccessDeniedException ex) {
			return false;
		}
	}

	@Override
	public boolean canManageMembership(String projectId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || projectId == null) {
			return false;
		}
		if (isSuperAdmin(authentication)) {
			return true;
		}
		try {
			return isProjectCreator(projectId, authentication.getName());
		} catch (AccessDeniedException ex) {
			return false;
		}
	}

	@Override
	public void validateCurrentUserMember(String projectId) {
		if (!isCurrentUserMember(projectId)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN, "You are not a member of this project");
		}
	}

	@Override
	public void validateCanManageProject(String projectId) {
		if (!canManageProject(projectId)) {
			throw new AccessDeniedException("Only project admins or PMs can manage this project");
		}
	}

	@Override
	public void validateCanEditIssues(String projectId) {
		if (!canEditIssues(projectId)) {
			throw new AccessDeniedException("Your project role is read-only");
		}
	}

	@Override
	public void validateCanManageMembership(String projectId) {
		if (!canManageMembership(projectId)) {
			throw new AccessDeniedException("Only the project creator can manage members");
		}
	}

	@Override
	public void validateMember(String projectId, String userId) {
		if (!projectMemberRepository.existsByProject_IdAndUser_Id(projectId, userId)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a member of this project");
		}
	}

	@Override
	public ProjectMember addMember(String projectId, ProjectMemberRequest request) {
		Project project = getProject(projectId);
		validateCanManageMembership(projectId);

		User targetUser = findUserByIdentifier(request.getIdentifier());
		User currentUser = getCurrentUser();

		if (currentUser.getId().equals(targetUser.getId())) {
			throw new DuplicateMemberException("Cannot add yourself again");
		}
		if (projectMemberRepository.existsByUserAndProject(targetUser, project)) {
			throw new DuplicateMemberException("User already exists in project");
		}

		return saveMember(project, targetUser, resolveRequestedRole(request.getRole()));
	}

	@Override
	public ProjectMember addProjectAdmin(String projectId, String userId) {
		return addMemberInternal(projectId, userId, ProjectMemberRole.PROJECT_ADMIN);
	}

	@Override
	public ProjectMember addMemberByUserId(String projectId, String userId, ProjectMemberRole role) {
		return addMemberInternal(projectId, userId, role);
	}

	@Override
	public void removeMember(String projectId, String userId) {
		validateCanManageMembership(projectId);
		ProjectMember targetMembership = projectMemberRepository.findByProject_IdAndUser_Id(projectId, userId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project member not found"));
		ProjectMember creatorMembership = getCreatorMembership(projectId);
		if (creatorMembership.getUser().getId().equals(targetMembership.getUser().getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project creator cannot be removed");
		}
		projectMemberRepository.delete(targetMembership);
	}

	@Override
	public void leaveProject(String projectId) {
		validateCurrentUserMember(projectId);
		ProjectMember currentMembership = getMembership(projectId, currentUsername());
		ProjectMember creatorMembership = getCreatorMembership(projectId);
		if (creatorMembership.getUser().getId().equals(currentMembership.getUser().getId())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project creator cannot leave the project");
		}
		projectMemberRepository.delete(currentMembership);
	}

	@Override
	public List<ProjectMember> getMembers(String projectId) {
		validateCurrentUserMember(projectId);
		return projectMemberRepository.findByProject_IdOrderByCreatedAtAsc(projectId);
	}

	@Override
	public ProjectMember getMembership(String projectId, String username) {
		return projectMemberRepository.findByProject_IdAndUser_Username(projectId, username)
			.orElseThrow(() -> new AccessDeniedException("User is not a member of this project"));
	}

	private boolean isSuperAdmin(Authentication authentication) {
		return authentication.getAuthorities().stream()
			.anyMatch(authority -> "ROLE_SUPER_ADMIN".equals(authority.getAuthority()));
	}

	private boolean isProjectCreator(String projectId, String username) {
		return getCreatorMembership(projectId).getUser().getUsername().equals(username);
	}

	private ProjectMember addMemberInternal(String projectId, String userId, ProjectMemberRole role) {
		Project project = getProject(projectId);
		User user = userRepository.findById(userId)
			.orElseThrow(() -> new UserNotFoundException("User not found"));

		if (projectMemberRepository.existsByUserAndProject(user, project)) {
			return projectMemberRepository.findByProject_IdAndUser_Id(projectId, userId)
				.orElseThrow(() -> new DuplicateMemberException("User already exists in project"));
		}

		return saveMember(project, user, role);
	}

	private ProjectMember saveMember(Project project, User user, ProjectMemberRole role) {
		ProjectMember member = new ProjectMember();
		member.setProject(project);
		member.setUser(user);
		member.setRole(role == null ? ProjectMemberRole.MEMBER : role);
		ProjectMember savedMember = projectMemberRepository.save(member);
		notificationService.notifyProjectMemberAdded(savedMember);
		return savedMember;
	}

	private ProjectMemberRole resolveRequestedRole(String rawRole) {
		if (rawRole == null || rawRole.isBlank()) {
			return ProjectMemberRole.MEMBER;
		}
		try {
			ProjectMemberRole role = ProjectMemberRole.valueOf(rawRole.trim().toUpperCase());
			return role == ProjectMemberRole.LEAD ? ProjectMemberRole.PROJECT_ADMIN : role;
		} catch (IllegalArgumentException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid project role");
		}
	}

	private Project getProject(String projectId) {
		return projectRepository.findById(projectId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
	}

	private User findUserByIdentifier(String identifier) {
		String normalized = identifier == null ? null : identifier.trim();
		return userRepository.findByUsername(normalized)
			.or(() -> userRepository.findByEmail(normalized))
			.orElseThrow(() -> new UserNotFoundException("User not found"));
	}

	private User getCurrentUser() {
		String username = currentUsername();
		return userRepository.findByUsername(username)
			.orElseThrow(() -> new UserNotFoundException("User not found"));
	}

	private ProjectMember getCreatorMembership(String projectId) {
		return projectMemberRepository.findFirstByProject_IdOrderByCreatedAtAsc(projectId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project creator not found"));
	}

	private String currentUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}
}
