package phattrienungdungvoi2ee.DoAnMonHoc.config;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.IssueType;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.PriorityType;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.StatusType;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.IssueTypeRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.PriorityTypeRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.StatusTypeRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.UserRepository;

@Configuration
public class DataSeeder {

	@Bean
	CommandLineRunner seedLookups(
		StatusTypeRepository statusTypeRepository,
		PriorityTypeRepository priorityTypeRepository,
		IssueTypeRepository issueTypeRepository
	) {
		return args -> {
			ensureStatus(statusTypeRepository, 1, "TODO", "Issue is newly created");
			ensureStatus(statusTypeRepository, 2, "IN_PROGRESS", "Issue is being worked on");
			ensureStatus(statusTypeRepository, 3, "DONE", "Issue is completed");
			ensureStatus(statusTypeRepository, 4, "REVIEW", "Issue is waiting for review");

			ensurePriority(priorityTypeRepository, 1, "LOW", 1);
			ensurePriority(priorityTypeRepository, 2, "MEDIUM", 2);
			ensurePriority(priorityTypeRepository, 3, "HIGH", 3);

			ensureIssueType(issueTypeRepository, 1, "TASK");
			ensureIssueType(issueTypeRepository, 2, "BUG");
			ensureIssueType(issueTypeRepository, 3, "STORY");
		};
	}

	private void ensureStatus(StatusTypeRepository repository, int id, String name, String description) {
		StatusType status = repository.findById(id).orElseGet(StatusType::new);
		status.setId(id);
		status.setName(name);
		status.setDescription(description);
		repository.save(status);
	}

	private void ensurePriority(PriorityTypeRepository repository, int id, String name, int level) {
		PriorityType priority = repository.findById(id).orElseGet(PriorityType::new);
		priority.setId(id);
		priority.setName(name);
		priority.setLevel(level);
		repository.save(priority);
	}

	private void ensureIssueType(IssueTypeRepository repository, int id, String name) {
		IssueType issueType = repository.findById(id).orElseGet(IssueType::new);
		issueType.setId(id);
		issueType.setName(name);
		repository.save(issueType);
	}

	@Bean
	CommandLineRunner seedUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			userRepository.findAll().forEach(user -> {
				if ("ADMIN".equalsIgnoreCase(user.getRole()) || "ROLE_ADMIN".equalsIgnoreCase(user.getRole())) {
					user.setRole("SUPER_ADMIN");
					userRepository.save(user);
				} else if ("USER".equalsIgnoreCase(user.getRole()) || "ROLE_USER".equalsIgnoreCase(user.getRole())) {
					user.setRole(null);
					userRepository.save(user);
				}
			});

			if (userRepository.count() == 0) {
				User admin = new User();
				admin.setId(UUID.randomUUID().toString());
				admin.setUsername("admin");
				admin.setEmail("admin@example.com");
				admin.setPassword(passwordEncoder.encode("password123"));
				admin.setDisplayName("Administrator");
				admin.setRole("SUPER_ADMIN");
				admin.setActive(true);
				admin.setCreatedAt(LocalDateTime.now());

				User user1 = new User();
				user1.setId(UUID.randomUUID().toString());
				user1.setUsername("user1");
				user1.setEmail("user1@example.com");
				user1.setPassword(passwordEncoder.encode("password123"));
				user1.setDisplayName("User One");
				user1.setRole(null);
				user1.setActive(true);
				user1.setCreatedAt(LocalDateTime.now());

				User user2 = new User();
				user2.setId(UUID.randomUUID().toString());
				user2.setUsername("user2");
				user2.setEmail("user2@example.com");
				user2.setPassword(passwordEncoder.encode("password123"));
				user2.setDisplayName("User Two");
				user2.setRole(null);
				user2.setActive(true);
				user2.setCreatedAt(LocalDateTime.now());

				userRepository.saveAll(List.of(admin, user1, user2));
			}
		};
	}
}
