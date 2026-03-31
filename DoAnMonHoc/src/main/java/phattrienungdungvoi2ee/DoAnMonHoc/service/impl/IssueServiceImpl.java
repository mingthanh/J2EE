package phattrienungdungvoi2ee.DoAnMonHoc.service.impl;

import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.IssueRequest;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.IssueType;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.PriorityType;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Project;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.StatusType;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;
import phattrienungdungvoi2ee.DoAnMonHoc.mapper.IssueMapper;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.IssueRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.IssueTypeRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.PriorityTypeRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.ProjectRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.StatusTypeRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.UserRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.service.IssueService;
import phattrienungdungvoi2ee.DoAnMonHoc.service.NotificationService;
import phattrienungdungvoi2ee.DoAnMonHoc.service.ProjectMemberService;

@Service
public class IssueServiceImpl implements IssueService {

	private static final String TODO = "TODO";
	private static final String IN_PROGRESS = "IN_PROGRESS";
	private static final String REVIEW = "REVIEW";
	private static final String DONE = "DONE";

	private static final Map<String, Set<String>> ALLOWED_TRANSITIONS = Map.of(
		TODO, Set.of(IN_PROGRESS),
		IN_PROGRESS, Set.of(TODO, REVIEW),
		REVIEW, Set.of(IN_PROGRESS, DONE),
		DONE, Set.of(REVIEW)
	);

	private final IssueRepository issueRepository;
	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;
	private final StatusTypeRepository statusTypeRepository;
	private final PriorityTypeRepository priorityTypeRepository;
	private final IssueTypeRepository issueTypeRepository;
	private final IssueMapper issueMapper;
	private final ProjectMemberService projectMemberService;
	private final NotificationService notificationService;

	public IssueServiceImpl(
		IssueRepository issueRepository,
		ProjectRepository projectRepository,
		UserRepository userRepository,
		StatusTypeRepository statusTypeRepository,
		PriorityTypeRepository priorityTypeRepository,
		IssueTypeRepository issueTypeRepository,
		IssueMapper issueMapper,
		ProjectMemberService projectMemberService,
		NotificationService notificationService
	) {
		this.issueRepository = issueRepository;
		this.projectRepository = projectRepository;
		this.userRepository = userRepository;
		this.statusTypeRepository = statusTypeRepository;
		this.priorityTypeRepository = priorityTypeRepository;
		this.issueTypeRepository = issueTypeRepository;
		this.issueMapper = issueMapper;
		this.projectMemberService = projectMemberService;
		this.notificationService = notificationService;
	}

	@Override
	public List<Issue> getAll() {
		return isSuperAdmin()
			? issueRepository.findAll()
			: issueRepository.findAllByProjectMemberUsername(currentUsername());
	}

	@Override
	public Issue getById(String id) {
		Issue issue = issueRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));
		if (issue.getProject() != null) {
			projectMemberService.validateCurrentUserMember(issue.getProject().getId());
		}
		return issue;
	}

	@Override
	public Issue create(IssueRequest request) {
		Issue issue = new Issue();
		applyRequest(issue, request, true);
		if (issue.getProject() == null) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project is required");
		}
		projectMemberService.validateCanEditIssues(issue.getProject().getId());
		if (issue.getStatus() == null) {
			issue.setStatus(statusTypeRepository.findByName(TODO)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Default status TODO not found")));
		}
		if (issue.getReporter() == null) {
			String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
			userRepository.findByUsername(currentUsername).ifPresent(issue::setReporter);
		}
		if (issue.getIssueKey() == null || issue.getIssueKey().isBlank()) {
			issue.setIssueKey(generateIssueKey(issue.getProject()));
		}
		return issueRepository.save(issue);
	}

	@Override
	@PreAuthorize("@authorizationService.canUpdateIssue(#id)")
	public Issue update(String id, IssueRequest request) {
		Issue issue = getById(id);
		applyRequest(issue, request, false);
		if (issue.getIssueKey() == null || issue.getIssueKey().isBlank()) {
			issue.setIssueKey(generateIssueKey(issue.getProject()));
		}
		return issueRepository.save(issue);
	}

	@Override
	@PreAuthorize("@authorizationService.canUpdateIssue(#id)")
	public void delete(String id) {
		Issue issue = getById(id);
		issueRepository.delete(issue);
	}

	@Override
	@PreAuthorize("@authorizationService.canUpdateIssue(#issueId)")
	public Issue assignUser(String issueId, String userId) {
		Issue issue = getById(issueId);
		if (issue.getProject() != null) {
			projectMemberService.validateCanManageProject(issue.getProject().getId());
			projectMemberService.validateMember(issue.getProject().getId(), userId);
		}
		User assignee = userRepository.findById(userId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
		issue.setAssignee(assignee);
		Issue savedIssue = issueRepository.save(issue);
		notificationService.notifyAssignment(savedIssue);
		return savedIssue;
	}

	@Override
	public List<Issue> findByStatusId(Integer statusId) {
		return isSuperAdmin()
			? issueRepository.findByStatus_Id(statusId)
			: issueRepository.findByStatus_IdAndProjectMemberUsername(statusId, currentUsername());
	}

	@Override
	public List<Issue> findByProjectId(String projectId) {
		projectMemberService.validateCurrentUserMember(projectId);
		return issueRepository.findByProject_IdOrderByCreatedAtDesc(projectId);
	}

	private void applyRequest(Issue issue, IssueRequest request, boolean creating) {
		String summary = issueMapper.extractSummary(request);
		if (creating && (summary == null || summary.isBlank())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Issue summary is required");
		}
		if (summary != null) {
			issue.setSummary(summary);
		}
		if (issue.getSummary() == null || issue.getSummary().isBlank()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Issue summary is required");
		}

		validateDeadline(request.getDueAt());

		if (request.getDescription() != null) {
			issue.setDescription(request.getDescription());
		}
		if (request.getIssueKey() != null) {
			issue.setIssueKey(request.getIssueKey());
		}
		if (request.getDueAt() != null) {
			issue.setDueAt(request.getDueAt());
		}
		if (request.getProjectId() != null) {
			Project project = projectRepository.findById(request.getProjectId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
			projectMemberService.validateCanEditIssues(project.getId());
			issue.setProject(project);
		}
		if (request.getReporterId() != null) {
			User reporter = userRepository.findById(request.getReporterId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporter not found"));
			issue.setReporter(reporter);
		}
		if (request.getAssigneeId() != null) {
			if (issue.getProject() == null) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project is required before assigning user");
			}
			projectMemberService.validateMember(issue.getProject().getId(), request.getAssigneeId());
			User assignee = userRepository.findById(request.getAssigneeId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignee not found"));
			issue.setAssignee(assignee);
		}

		StatusType targetStatus = resolveStatus(request);
		if (targetStatus != null) {
			validateStatusUpdate(issue, targetStatus, creating);
			issue.setStatus(targetStatus);
		}

		PriorityType priority = resolvePriority(request);
		if (priority != null) {
			issue.setPriority(priority);
		}

		IssueType type = resolveType(request);
		if (type != null) {
			issue.setType(type);
		}
	}

	private StatusType resolveStatus(IssueRequest request) {
		if (request.getStatusId() != null) {
			return statusTypeRepository.findById(request.getStatusId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found"));
		}
		if (request.getStatus() != null && !request.getStatus().isBlank()) {
			String normalized = normalizeLookupName(request.getStatus());
			return statusTypeRepository.findByName(normalized)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found"));
		}
		return null;
	}

	private PriorityType resolvePriority(IssueRequest request) {
		if (request.getPriorityId() != null) {
			return priorityTypeRepository.findById(request.getPriorityId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Priority not found"));
		}
		if (request.getPriority() != null && !request.getPriority().isBlank()) {
			String normalized = normalizeLookupName(request.getPriority());
			return priorityTypeRepository.findByName(normalized)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Priority not found"));
		}
		return null;
	}

	private IssueType resolveType(IssueRequest request) {
		if (request.getTypeId() != null) {
			return issueTypeRepository.findById(request.getTypeId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue type not found"));
		}
		if (request.getType() != null && !request.getType().isBlank()) {
			String normalized = normalizeLookupName(request.getType());
			return issueTypeRepository.findByName(normalized)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue type not found"));
		}
		return null;
	}

	private void validateDeadline(LocalDateTime dueAt) {
		if (dueAt != null && dueAt.isBefore(LocalDateTime.now())) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Deadline must be in the future");
		}
	}

	private void validateStatusUpdate(Issue issue, StatusType targetStatus, boolean creating) {
		if (creating) {
			return;
		}

		if (!isSuperAdmin() && targetStatus.getId() != null) {
			String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
			boolean isAssignee = issue.getAssignee() != null && currentUsername.equals(issue.getAssignee().getUsername());
			boolean isChangingStatus = issue.getStatus() == null
				|| !normalizeLookupName(issue.getStatus().getName()).equals(normalizeLookupName(targetStatus.getName()));
			if (!isAssignee && isChangingStatus) {
				throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Only assignee can update issue status");
			}
		}

		if (issue.getStatus() == null || issue.getStatus().getName() == null) {
			return;
		}

		String current = normalizeLookupName(issue.getStatus().getName());
		String target = normalizeLookupName(targetStatus.getName());
		if (current.equals(target)) {
			return;
		}

		if (DONE.equals(current) && TODO.equals(target)) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cannot move status from DONE to TODO");
		}

		Set<String> allowedTargets = ALLOWED_TRANSITIONS.getOrDefault(current, Set.of());
		if (!allowedTargets.contains(target)) {
			throw new ResponseStatusException(
				HttpStatus.BAD_REQUEST,
				"Invalid status transition from " + current + " to " + target
			);
		}
	}

	private boolean isSuperAdmin() {
		return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream()
			.anyMatch(authority -> "ROLE_SUPER_ADMIN".equals(authority.getAuthority()));
	}

	private String currentUsername() {
		return SecurityContextHolder.getContext().getAuthentication().getName();
	}

	private String normalizeLookupName(String raw) {
		String normalized = Normalizer.normalize(raw.trim(), Normalizer.Form.NFD)
			.replaceAll("\\p{M}", "")
			.toUpperCase()
			.replace(' ', '_');
		return switch (normalized) {
			case "OPEN" -> TODO;
			case "IN_PROGRESS", "INPROGRESS", "DANG_LAM" -> IN_PROGRESS;
			case "TO_DO", "TODO", "TAO", "TAP" -> TODO;
			case "REVIEW", "XEM_XET" -> REVIEW;
			case "DONE", "HOAN_THANH" -> DONE;
			case "LOW", "THAP" -> "LOW";
			case "MEDIUM", "TRUNG_BINH" -> "MEDIUM";
			case "HIGH", "CAO" -> "HIGH";
			default -> normalized;
		};
	}

	private String generateIssueKey(Project project) {
		String prefix = "ISSUE";
		if (project != null && project.getKey() != null && !project.getKey().isBlank()) {
			prefix = project.getKey().trim();
		}
		String suffix = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
		return prefix + "-" + suffix;
	}
}
