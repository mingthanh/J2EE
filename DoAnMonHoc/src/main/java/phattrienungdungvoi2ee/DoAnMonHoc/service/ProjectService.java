package phattrienungdungvoi2ee.DoAnMonHoc.service;

import java.util.List;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.ProjectDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.ProjectMemberRequest;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Project;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.ProjectMember;

public interface ProjectService {
	List<Project> getAll();
	Project getById(String id);
	Project create(ProjectDTO dto);
	Project update(String id, ProjectDTO dto);
	void delete(String id);
	ProjectMember addMember(String projectId, ProjectMemberRequest request);
	void removeMember(String projectId, String userId);
	void leaveProject(String projectId);
	List<ProjectMember> getMembers(String projectId);
}
