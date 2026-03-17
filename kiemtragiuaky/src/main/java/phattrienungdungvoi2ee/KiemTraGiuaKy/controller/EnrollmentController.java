package phattrienungdungvoi2ee.KiemTraGiuaKy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Course;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Enrollment;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Student;
import phattrienungdungvoi2ee.KiemTraGiuaKy.service.CourseService;
import phattrienungdungvoi2ee.KiemTraGiuaKy.service.EnrollmentService;
import phattrienungdungvoi2ee.KiemTraGiuaKy.service.StudentService;

import java.security.Principal;
import java.util.List;

@Controller
public class EnrollmentController {

    private final EnrollmentService enrollmentService;
    private final CourseService courseService;
    private final StudentService studentService;

    public EnrollmentController(EnrollmentService enrollmentService, CourseService courseService, StudentService studentService) {
        this.enrollmentService = enrollmentService;
        this.courseService = courseService;
        this.studentService = studentService;
    }

    @PostMapping("/enroll/{courseId}")
    public String enroll(@PathVariable Long courseId, Principal principal) {
        Student student = studentService.findByUsername(principal.getName()).orElseThrow();
        Course course = courseService.findById(courseId).orElseThrow();
        enrollmentService.enroll(student, course);
        return "redirect:/my-courses";
    }

    @GetMapping("/my-courses")
    public String myCourses(Principal principal, Model model) {
        Student student = studentService.findByUsername(principal.getName()).orElseThrow();
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudent(student);
        model.addAttribute("enrollments", enrollments);
        return "student/mycourses";
    }
}
