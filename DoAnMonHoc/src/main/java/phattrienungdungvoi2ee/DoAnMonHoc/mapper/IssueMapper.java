package phattrienungdungvoi2ee.DoAnMonHoc.mapper;

import org.springframework.stereotype.Component;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.IssueRequest;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.IssueResponse;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;

@Component
public class IssueMapper {

	public String extractSummary(IssueRequest request) {
		if (request.getSummary() != null && !request.getSummary().isBlank()) {
			return request.getSummary().trim();
		}
		if (request.getTitle() != null && !request.getTitle().isBlank()) {
			return request.getTitle().trim();
		}
		return null;
	}

	public IssueResponse toResponse(Issue issue) {
		IssueResponse response = new IssueResponse();
		response.setId(issue.getId());
		response.setIssueKey(issue.getIssueKey());
		response.setSummary(issue.getSummary());
		response.setTitle(issue.getSummary());
		response.setDescription(issue.getDescription());
		response.setCreatedAt(issue.getCreatedAt());
		response.setDueAt(issue.getDueAt());

		if (issue.getStatus() != null) {
			response.setStatusId(issue.getStatus().getId());
			response.setStatusCode(issue.getStatus().getName());
			response.setStatus(toDisplayStatus(issue.getStatus().getName()));
		}
		if (issue.getPriority() != null) {
			response.setPriorityId(issue.getPriority().getId());
			response.setPriorityCode(issue.getPriority().getName());
			response.setPriority(toDisplayLabel(issue.getPriority().getName()));
		}
		if (issue.getType() != null) {
			response.setTypeId(issue.getType().getId());
			response.setTypeCode(issue.getType().getName());
			response.setType(toDisplayLabel(issue.getType().getName()));
		}
		if (issue.getProject() != null) {
			response.setProjectId(issue.getProject().getId());
			response.setProjectName(issue.getProject().getName());
		}
		if (issue.getReporter() != null) {
			response.setReporterId(issue.getReporter().getId());
			response.setReporterName(issue.getReporter().getDisplayName() != null
				? issue.getReporter().getDisplayName()
				: issue.getReporter().getUsername());
		}
		if (issue.getAssignee() != null) {
			response.setAssigneeId(issue.getAssignee().getId());
			response.setAssigneeName(issue.getAssignee().getDisplayName() != null
				? issue.getAssignee().getDisplayName()
				: issue.getAssignee().getUsername());
		}

		return response;
	}

	private String toDisplayStatus(String status) {
		return switch (status) {
			case "TODO" -> "Open";
			case "IN_PROGRESS" -> "In Progress";
			case "REVIEW" -> "Review";
			case "DONE" -> "Done";
			default -> toDisplayLabel(status);
		};
	}

	private String toDisplayLabel(String value) {
		if (value == null || value.isBlank()) {
			return value;
		}
		String normalized = value.toLowerCase().replace('_', ' ');
		String[] words = normalized.split(" ");
		StringBuilder builder = new StringBuilder();
		for (String word : words) {
			if (word.isBlank()) {
				continue;
			}
			if (!builder.isEmpty()) {
				builder.append(' ');
			}
			builder.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1));
		}
		return builder.toString();
	}
}
