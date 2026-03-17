package phattrienungdungvoi2ee.KiemTraGiuaKy.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Category;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Role;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Student;
import phattrienungdungvoi2ee.KiemTraGiuaKy.repository.CategoryRepository;
import phattrienungdungvoi2ee.KiemTraGiuaKy.repository.RoleRepository;
import phattrienungdungvoi2ee.KiemTraGiuaKy.repository.StudentRepository;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedData(RoleRepository roleRepository,
                                      StudentRepository studentRepository,
                                      CategoryRepository categoryRepository,
                                      PasswordEncoder passwordEncoder) {
        return args -> {
            Role adminRole = roleRepository.findByName("ADMIN")
                    .orElseGet(() -> roleRepository.save(new Role(null, "ADMIN")));
            Role studentRole = roleRepository.findByName("STUDENT")
                    .orElseGet(() -> roleRepository.save(new Role(null, "STUDENT")));

            if (studentRepository.findByUsername("admin").isEmpty()) {
                Student admin = new Student();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("Admin@123"));
                admin.setEmail("admin@local");
                admin.getRoles().add(adminRole);
                studentRepository.save(admin);
            }

            if (categoryRepository.count() == 0) {
                categoryRepository.saveAll(List.of(
                        new Category(null, "Technology"),
                        new Category(null, "Business"),
                        new Category(null, "Design"),
                        new Category(null, "Marketing")
                ));
            }
        };
    }
}
