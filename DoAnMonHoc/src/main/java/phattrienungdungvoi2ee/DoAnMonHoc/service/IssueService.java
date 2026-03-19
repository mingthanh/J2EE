package phattrienungdungvoi2ee.DoAnMonHoc.service;

import java.util.List;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.IssueDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Issue;

public interface IssueService {
	List<Issue> getAll();
	Issue getById(String id);
	Issue create(IssueDTO dto);
	Issue update(String id, IssueDTO dto);
	void delete(String id);
	Issue assignUser(String issueId, String userId);
	List<Issue> findByStatusId(Integer statusId);
}
