package phattrienungdungvoi2ee.KiemTraGiuaKy.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Course;
import phattrienungdungvoi2ee.KiemTraGiuaKy.service.CourseService;

@Controller
public class HomeController {

    private final CourseService courseService;

    public HomeController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/")
    public String root() {
        return "redirect:/home";
    }

    @GetMapping({"/home", "/courses"})
    public String home(@RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size,
                       @RequestParam(required = false, name = "q") String keyword,
                       Model model) {
        Page<Course> courses = courseService.searchCourses(keyword, page, size);
        model.addAttribute("courses", courses);
        model.addAttribute("q", keyword == null ? "" : keyword);
        return "home";
    }
}
