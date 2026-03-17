package phattrienungdungvoi2ee.KiemTraGiuaKy.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Course;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Enrollment;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Student;
import phattrienungdungvoi2ee.KiemTraGiuaKy.repository.EnrollmentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    public List<Enrollment> getEnrollmentsByStudent(Student student) {
        return enrollmentRepository.findByStudent(student);
    }

    @Transactional
    public Enrollment enroll(Student student, Course course) {
        return enrollmentRepository.findByStudentAndCourse(student, course)
                .orElseGet(() -> {
                    Enrollment enrollment = new Enrollment();
                    enrollment.setStudent(student);
                    enrollment.setCourse(course);
                    enrollment.setEnrollDate(LocalDateTime.now());
                    return enrollmentRepository.save(enrollment);
                });
    }

    @Transactional
    public void unenroll(Student student, Course course) {
        enrollmentRepository.deleteByStudentAndCourse(student, course);
    }
}
