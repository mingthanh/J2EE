package phattrienungdungvoi2ee.KiemTraGiuaKy.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Course;
import phattrienungdungvoi2ee.KiemTraGiuaKy.repository.CourseRepository;

import java.util.Optional;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Page<Course> getCourses(int page, int size) {
        return courseRepository.findAll(PageRequest.of(page, size));
    }

    public Page<Course> searchCourses(String keyword, int page, int size) {
        if (keyword == null || keyword.isBlank()) {
            return getCourses(page, size);
        }
        return courseRepository.findByNameContainingIgnoreCase(keyword.trim(), PageRequest.of(page, size));
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public Course save(Course course) {
        return courseRepository.save(course);
    }

    public void deleteById(Long id) {
        courseRepository.deleteById(id);
    }
}
