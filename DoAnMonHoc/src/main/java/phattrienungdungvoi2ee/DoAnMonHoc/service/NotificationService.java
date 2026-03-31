package phattrienungdungvoi2ee.DoAnMonHoc.service;

import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;

public interface NotificationService {
	SseEmitter subscribe();
	void notifyAssignment(Issue issue);
}
