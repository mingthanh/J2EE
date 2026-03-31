package phattrienungdungvoi2ee.DoAnMonHoc.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.IssueRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.service.ProjectMemberService;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.UserRepository;

@Component("authorizationService")
public class AuthorizationService {

	private final UserRepository userRepository;
	private final IssueRepository issueRepository;
	private final ProjectMemberService projectMemberService;

	public AuthorizationService(
		UserRepository userRepository,
		IssueRepository issueRepository,
		ProjectMemberService projectMemberService
	) {
		this.userRepository = userRepository;
		this.issueRepository = issueRepository;
		this.projectMemberService = projectMemberService;
	}

	public boolean isCurrentUser(String userId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || userId == null) {
			return false;
		}

		return userRepository.findByUsername(authentication.getName())
			.map(user -> user.getId().equals(userId))
			.orElse(false);
	}

	public boolean canUpdateIssue(String issueId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || issueId == null) {
			return false;
		}

		if (isSuperAdmin()) {
			return true;
		}

		String currentUsername = authentication.getName();
		return issueRepository.findById(issueId)
			.map(Issue::getProject)
			.map(project -> project != null && projectMemberService.canEditIssues(project.getId()))
			.orElse(false);
	}

	public boolean canAccessProject(String projectId) {
		return projectMemberService.isCurrentUserMember(projectId);
	}

	public boolean canManageProject(String projectId) {
		return projectMemberService.canManageProject(projectId);
	}

	public boolean isSuperAdmin() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return false;
		}
		return authentication.getAuthorities().stream()
			.anyMatch(authority -> "ROLE_SUPER_ADMIN".equals(authority.getAuthority()));
	}
}
