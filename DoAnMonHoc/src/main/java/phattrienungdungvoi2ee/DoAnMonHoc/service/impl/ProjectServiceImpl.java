package phattrienungdungvoi2ee.DoAnMonHoc.service.impl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.ProjectDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Project;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.ProjectRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.UserRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.service.ProjectService;

@Service
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	private final UserRepository userRepository;

	public ProjectServiceImpl(ProjectRepository projectRepository, UserRepository userRepository) {
		this.projectRepository = projectRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<Project> getAll() {
		return projectRepository.findAll();
	}

	@Override
	public Project getById(String id) {
		return projectRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Project not found"));
	}

	@Override
	public Project create(ProjectDTO dto) {
		Project project = new Project();
		applyDto(project, dto);
		return projectRepository.save(project);
	}

	@Override
	public Project update(String id, ProjectDTO dto) {
		Project project = getById(id);
		applyDto(project, dto);
		return projectRepository.save(project);
	}

	@Override
	public void delete(String id) {
		Project project = getById(id);
		projectRepository.delete(project);
	}

	private void applyDto(Project project, ProjectDTO dto) {
		if (dto.getName() != null) {
			project.setName(dto.getName());
		}
		if (dto.getKey() != null) {
			project.setKey(dto.getKey());
		}
		if (dto.getDescription() != null) {
			project.setDescription(dto.getDescription());
		}
		if (dto.getLeadId() != null) {
			User lead = userRepository.findById(dto.getLeadId())
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Lead user not found"));
			project.setLead(lead);
		}
	}
}
