package phattrienungdungvoi2ee.DoAnMonHoc.service.impl;

import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.IssueDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.IssueType;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.PriorityType;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Project;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.StatusType;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.IssueRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.IssueTypeRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.PriorityTypeRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.ProjectRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.StatusTypeRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.UserRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.service.IssueService;

@Service
public class IssueServiceImpl implements IssueService {

	private final IssueRepository issueRepository;
	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;
	private final StatusTypeRepository statusTypeRepository;
	private final PriorityTypeRepository priorityTypeRepository;
	private final IssueTypeRepository issueTypeRepository;

	public IssueServiceImpl(
		IssueRepository issueRepository,
		ProjectRepository projectRepository,
		UserRepository userRepository,
		StatusTypeRepository statusTypeRepository,
		PriorityTypeRepository priorityTypeRepository,
		IssueTypeRepository issueTypeRepository
	) {
		this.issueRepository = issueRepository;
		this.projectRepository = projectRepository;
		this.userRepository = userRepository;
		this.statusTypeRepository = statusTypeRepository;
		this.priorityTypeRepository = priorityTypeRepository;
		this.issueTypeRepository = issueTypeRepository;
	}

	@Override
	public List<Issue> getAll() {
		return issueRepository.findAll();
	}

	@Override
	public Issue getById(String id) {
		return issueRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue not found"));
	}

	@Override
	public Issue create(IssueDTO dto) {
		Issue issue = new Issue();
		applyDto(issue, dto);
		if (issue.getIssueKey() == null || issue.getIssueKey().isBlank()) {
			issue.setIssueKey(generateIssueKey(issue.getProject()));
		}
		return issueRepository.save(issue);
	}

	@Override
	public Issue update(String id, IssueDTO dto) {
		Issue issue = getById(id);
		applyDto(issue, dto);
		if (issue.getIssueKey() == null || issue.getIssueKey().isBlank()) {
			issue.setIssueKey(generateIssueKey(issue.getProject()));
		}
		return issueRepository.save(issue);
	}

	@Override
	public void delete(String id) {
		Issue issue = getById(id);
		issueRepository.delete(issue);
	}

	@Override
	public Issue assignUser(String issueId, String userId) {
		Issue issue = getById(issueId);
		User assignee = userRepository.findById(userId)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
		issue.setAssignee(assignee);
		return issueRepository.save(issue);
	}

	@Override
	public List<Issue> findByStatusId(Integer statusId) {
		return issueRepository.findByStatus_Id(statusId);
	}

	private void applyDto(Issue issue, IssueDTO dto) {
		if (dto.getSummary() != null) {
			issue.setSummary(dto.getSummary());
		}
		if (dto.getDescription() != null) {
			issue.setDescription(dto.getDescription());
		}
		if (dto.getIssueKey() != null) {
			issue.setIssueKey(dto.getIssueKey());
		}
		if (dto.getDueAt() != null) {
			issue.setDueAt(dto.getDueAt());
		}
		if (dto.getProjectId() != null) {
			Project project = projectRepository.findById(dto.getProjectId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
			issue.setProject(project);
		}
		if (dto.getReporterId() != null) {
			User reporter = userRepository.findById(dto.getReporterId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reporter not found"));
			issue.setReporter(reporter);
		}
		if (dto.getAssigneeId() != null) {
			User assignee = userRepository.findById(dto.getAssigneeId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Assignee not found"));
			issue.setAssignee(assignee);
		}
		if (dto.getStatusId() != null) {
			StatusType status = statusTypeRepository.findById(dto.getStatusId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Status not found"));
			issue.setStatus(status);
		}
		if (dto.getPriorityId() != null) {
			PriorityType priority = priorityTypeRepository.findById(dto.getPriorityId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Priority not found"));
			issue.setPriority(priority);
		}
		if (dto.getTypeId() != null) {
			IssueType type = issueTypeRepository.findById(dto.getTypeId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Issue type not found"));
			issue.setType(type);
		}
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
