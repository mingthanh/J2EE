package phattrienungdungvoi2ee.KiemTraGiuaKy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Course;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Enrollment;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Student;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(Student student);
    Optional<Enrollment> findByStudentAndCourse(Student student, Course course);
    void deleteByStudentAndCourse(Student student, Course course);
}
