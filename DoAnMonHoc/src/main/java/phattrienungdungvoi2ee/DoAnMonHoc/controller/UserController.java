package phattrienungdungvoi2ee.DoAnMonHoc.controller;

import jakarta.validation.Valid;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.UserDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;
import phattrienungdungvoi2ee.DoAnMonHoc.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<User> getAll() {
		return userService.getAll();
	}

	@GetMapping("/me")
	public User getCurrentUser(Authentication authentication) {
		return userService.getByUsername(authentication.getName());
	}

	@GetMapping("/{id}")
	public User getById(@PathVariable String id) {
		return userService.getById(id);
	}

	@PostMapping
	public User create(@Valid @RequestBody UserDTO dto) {
		return userService.create(dto);
	}

	@PutMapping("/{id}")
	public User update(@PathVariable String id, @RequestBody UserDTO dto) {
		return userService.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable String id) {
		userService.delete(id);
	}
}
