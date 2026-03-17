package phattrienungdungvoi2ee.KiemTraGiuaKy.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    Page<Course> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
