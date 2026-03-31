package phattrienungdungvoi2ee.DoAnMonHoc.service;

import java.util.List;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.ProjectMemberRequest;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.ProjectMember;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.ProjectMemberRole;

public interface ProjectMemberService {
	boolean isCurrentUserMember(String projectId);
	boolean isMember(String projectId, String username);
	boolean isCurrentUserProjectAdmin(String projectId);
	boolean canManageProject(String projectId);
	boolean canEditIssues(String projectId);
	boolean canManageMembership(String projectId);
	void validateCurrentUserMember(String projectId);
	void validateMember(String projectId, String userId);
	void validateCanManageProject(String projectId);
	void validateCanEditIssues(String projectId);
	void validateCanManageMembership(String projectId);
	ProjectMember addMember(String projectId, ProjectMemberRequest request);
	ProjectMember addProjectAdmin(String projectId, String userId);
	ProjectMember addMemberByUserId(String projectId, String userId, ProjectMemberRole role);
	void removeMember(String projectId, String userId);
	void leaveProject(String projectId);
	List<ProjectMember> getMembers(String projectId);
	ProjectMember getMembership(String projectId, String username);
}
