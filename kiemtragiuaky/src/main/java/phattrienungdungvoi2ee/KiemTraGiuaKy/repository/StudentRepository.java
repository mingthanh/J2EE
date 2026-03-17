package phattrienungdungvoi2ee.KiemTraGiuaKy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUsername(String username);
    Optional<Student> findByEmail(String email);
}
