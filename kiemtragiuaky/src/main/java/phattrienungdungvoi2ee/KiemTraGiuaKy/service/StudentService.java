package phattrienungdungvoi2ee.KiemTraGiuaKy.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Role;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Student;
import phattrienungdungvoi2ee.KiemTraGiuaKy.repository.RoleRepository;
import phattrienungdungvoi2ee.KiemTraGiuaKy.repository.StudentRepository;

import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public StudentService(StudentRepository studentRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Optional<Student> findByUsername(String username) {
        return studentRepository.findByUsername(username);
    }

    public Optional<Student> findByEmail(String email) {
        return studentRepository.findByEmail(email);
    }

    @Transactional
    public Student registerStudent(String username, String rawPassword, String email) {
        if (studentRepository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }
        if (studentRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email already exists");
        }

        Role studentRole = roleRepository.findByName("STUDENT")
                .orElseThrow(() -> new IllegalStateException("Role STUDENT not found"));

        Student student = new Student();
        student.setUsername(username);
        student.setPassword(passwordEncoder.encode(rawPassword));
        student.setEmail(email);
        student.getRoles().add(studentRole);

        return studentRepository.save(student);
    }

    @Transactional
    public Student ensureOauthStudent(String email) {
        return studentRepository.findByEmail(email)
                .orElseGet(() -> {
                    Role studentRole = roleRepository.findByName("STUDENT")
                            .orElseThrow(() -> new IllegalStateException("Role STUDENT not found"));
                    Student student = new Student();
                    student.setUsername(email);
                    student.setPassword(passwordEncoder.encode("oauth2-user"));
                    student.setEmail(email);
                    student.getRoles().add(studentRole);
                    return studentRepository.save(student);
                });
    }
}
