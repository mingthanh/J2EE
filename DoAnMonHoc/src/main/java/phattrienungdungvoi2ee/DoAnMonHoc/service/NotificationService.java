package phattrienungdungvoi2ee.DoAnMonHoc.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.ProjectMember;

public interface NotificationService {
	SseEmitter subscribe();
	void notifyAssignment(Issue issue);
	void notifyProjectMemberAdded(ProjectMember member);
}
