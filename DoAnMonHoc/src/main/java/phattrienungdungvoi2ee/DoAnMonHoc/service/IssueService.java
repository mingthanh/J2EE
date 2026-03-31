package phattrienungdungvoi2ee.DoAnMonHoc.service;

import java.util.List;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.IssueRequest;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;

public interface IssueService {
	List<Issue> getAll();
	Issue getById(String id);
	Issue create(IssueRequest dto);
	Issue update(String id, IssueRequest dto);
	void delete(String id);
	Issue assignUser(String issueId, String userId);
	List<Issue> findByStatusId(Integer statusId);
	List<Issue> findByProjectId(String projectId);
}
