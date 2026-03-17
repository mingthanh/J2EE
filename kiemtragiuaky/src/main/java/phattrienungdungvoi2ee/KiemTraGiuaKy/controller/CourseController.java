package phattrienungdungvoi2ee.KiemTraGiuaKy.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Category;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Course;
import phattrienungdungvoi2ee.KiemTraGiuaKy.repository.CategoryRepository;
import phattrienungdungvoi2ee.KiemTraGiuaKy.service.CourseService;

import java.util.List;

@Controller
@RequestMapping("/admin/courses")
public class CourseController {

    private final CourseService courseService;
    private final CategoryRepository categoryRepository;

    public CourseController(CourseService courseService, CategoryRepository categoryRepository) {
        this.courseService = courseService;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping
    public String list(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       Model model) {
        Page<Course> courses = courseService.getCourses(page, size);
        model.addAttribute("courses", courses);
        return "course/list";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("course", new Course());
        model.addAttribute("categories", categoryRepository.findAll());
        return "course/create";
    }

    @PostMapping("/create")
    public String create(@ModelAttribute Course course, @RequestParam(required = false) Long categoryId) {
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            course.setCategory(category);
        }
        courseService.save(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        Course course = courseService.findById(id).orElseThrow();
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("course", course);
        model.addAttribute("categories", categories);
        return "course/edit";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute Course course,
                         @RequestParam(required = false) Long categoryId) {
        Course existing = courseService.findById(id).orElseThrow();
        existing.setName(course.getName());
        existing.setCredits(course.getCredits());
        existing.setLecturer(course.getLecturer());
        existing.setImage(course.getImage());
        if (categoryId != null) {
            Category category = categoryRepository.findById(categoryId).orElse(null);
            existing.setCategory(category);
        } else {
            existing.setCategory(null);
        }
        courseService.save(existing);
        return "redirect:/admin/courses";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        courseService.deleteById(id);
        return "redirect:/admin/courses";
    }
}
