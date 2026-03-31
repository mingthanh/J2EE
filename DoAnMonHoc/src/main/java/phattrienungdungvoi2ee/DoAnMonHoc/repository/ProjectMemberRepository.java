package phattrienungdungvoi2ee.DoAnMonHoc.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.ProjectMember;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Project;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.ProjectMemberRole;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;

public interface ProjectMemberRepository extends JpaRepository<ProjectMember, String> {
	boolean existsByProject_IdAndUser_Username(String projectId, String username);
	boolean existsByProject_IdAndUser_Id(String projectId, String userId);
	boolean existsByUserAndProject(User user, Project project);
	boolean existsByProject_IdAndUser_UsernameAndRole(String projectId, String username, ProjectMemberRole role);
	Optional<ProjectMember> findByProject_IdAndUser_Id(String projectId, String userId);
	Optional<ProjectMember> findByProject_IdAndUser_Username(String projectId, String username);
	Optional<ProjectMember> findFirstByProject_IdOrderByCreatedAtAsc(String projectId);
	List<ProjectMember> findByProject_IdOrderByCreatedAtAsc(String projectId);
}
