package phattrienungdungvoi2ee.DoAnMonHoc.service;

import java.util.List;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.ProjectDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Project;

public interface ProjectService {
	List<Project> getAll();
	Project getById(String id);
	Project create(ProjectDTO dto);
	Project update(String id, ProjectDTO dto);
	void delete(String id);
}
