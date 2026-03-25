package phattrienungdungvoi2ee.DoAnMonHoc.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class WebController {

    @GetMapping("/")
    public String index() {
        return "redirect:/dashboard";
    }

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated() && !"anonymousUser".equals(authentication.getName())) {
            return "redirect:/dashboard";
        }
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

    @GetMapping("/projects")
    public String projects() {
        return "projects";
    }

    @GetMapping("/project/{id}")
    public String projectDetail(@PathVariable String id) {
        return "project-detail";
    }

    @GetMapping("/project/{id}/edit")
    public String projectEdit(@PathVariable String id) {
        return "project-edit";
    }

    @GetMapping("/issues")
    public String issues() {
        return "issues";
    }

    @GetMapping("/issue/{id}")
    public String issueDetail(@PathVariable String id) {
        return "issue-detail";
    }

    @GetMapping("/profile")
    public String profile() {
        return "profile";
    }
}
