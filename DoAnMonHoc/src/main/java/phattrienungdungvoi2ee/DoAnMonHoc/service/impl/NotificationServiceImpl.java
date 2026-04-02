package phattrienungdungvoi2ee.DoAnMonHoc.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.ProjectMember;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.UserRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.service.NotificationService;

@Service
public class NotificationServiceImpl implements NotificationService {

	private static final long SSE_TIMEOUT = 0L;

	private final Map<String, SseEmitter> emitters = new ConcurrentHashMap<>();
	private final UserRepository userRepository;

	public NotificationServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public SseEmitter subscribe() {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = userRepository.findByUsername(username)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

		SseEmitter emitter = new SseEmitter(SSE_TIMEOUT);
		emitters.put(user.getId(), emitter);

		emitter.onCompletion(() -> emitters.remove(user.getId()));
		emitter.onTimeout(() -> emitters.remove(user.getId()));
		emitter.onError(ex -> emitters.remove(user.getId()));

		try {
			emitter.send(SseEmitter.event().name("connected").data(Map.of("message", "connected")));
		} catch (IOException ex) {
			emitters.remove(user.getId());
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Unable to start notification stream");
		}

		return emitter;
	}

	@Override
	public void notifyAssignment(Issue issue) {
		if (issue == null || issue.getAssignees() == null || issue.getAssignees().isEmpty()) {
			return;
		}

		for (User assignee : issue.getAssignees()) {
			SseEmitter emitter = emitters.get(assignee.getId());
			if (emitter == null) {
				continue;
			}

			try {
				emitter.send(SseEmitter.event()
					.name("issue-assigned")
					.data(Map.of(
						"type", "ISSUE_ASSIGNED",
						"issueId", issue.getId(),
						"issueKey", issue.getIssueKey(),
						"summary", issue.getSummary(),
						"projectId", issue.getProject() != null ? issue.getProject().getId() : null,
						"projectName", issue.getProject() != null ? issue.getProject().getName() : null
					)));
			} catch (IOException ex) {
				emitters.remove(assignee.getId());
			}
		}
	}

	@Override
	public void notifyProjectMemberAdded(ProjectMember member) {
		if (member == null || member.getUser() == null || member.getProject() == null) {
			return;
		}

		SseEmitter emitter = emitters.get(member.getUser().getId());
		if (emitter == null) {
			return;
		}

		try {
			emitter.send(SseEmitter.event()
				.name("project-member-added")
				.data(Map.of(
					"type", "PROJECT_MEMBER_ADDED",
					"projectId", member.getProject().getId(),
					"projectName", member.getProject().getName(),
					"role", member.getRole() != null ? member.getRole().name() : "MEMBER"
				)));
		} catch (IOException ex) {
			emitters.remove(member.getUser().getId());
		}
	}
}
