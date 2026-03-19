package phattrienungdungvoi2ee.DoAnMonHoc.config;

import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.IssueType;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.PriorityType;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.StatusType;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.IssueTypeRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.PriorityTypeRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.StatusTypeRepository;

@Configuration
public class DataSeeder {

	@Bean
	CommandLineRunner seedLookups(
		StatusTypeRepository statusTypeRepository,
		PriorityTypeRepository priorityTypeRepository,
		IssueTypeRepository issueTypeRepository
	) {
		return args -> {
			if (statusTypeRepository.count() == 0) {
				StatusType todo = new StatusType();
				todo.setId(1);
				todo.setName("TODO");
				todo.setDescription("Issue is newly created");

				StatusType inProgress = new StatusType();
				inProgress.setId(2);
				inProgress.setName("IN_PROGRESS");
				inProgress.setDescription("Issue is being worked on");

				StatusType done = new StatusType();
				done.setId(3);
				done.setName("DONE");
				done.setDescription("Issue is completed");

				statusTypeRepository.saveAll(List.of(todo, inProgress, done));
			}

			if (priorityTypeRepository.count() == 0) {
				PriorityType low = new PriorityType();
				low.setId(1);
				low.setName("LOW");
				low.setLevel(1);

				PriorityType medium = new PriorityType();
				medium.setId(2);
				medium.setName("MEDIUM");
				medium.setLevel(2);

				PriorityType high = new PriorityType();
				high.setId(3);
				high.setName("HIGH");
				high.setLevel(3);

				priorityTypeRepository.saveAll(List.of(low, medium, high));
			}

			if (issueTypeRepository.count() == 0) {
				IssueType task = new IssueType();
				task.setId(1);
				task.setName("TASK");

				IssueType bug = new IssueType();
				bug.setId(2);
				bug.setName("BUG");

				IssueType story = new IssueType();
				story.setId(3);
				story.setName("STORY");

				issueTypeRepository.saveAll(List.of(task, bug, story));
			}
		};
	}
}
