package phattrienungdungvoi2ee.DoAnMonHoc.service.impl;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import phattrienungdungvoi2ee.DoAnMonHoc.dto.UserDTO;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.UserRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<User> getAll() {
		return userRepository.findAll();
	}

	@Override
	public User getByUsername(String username) {
		return userRepository.findByUsername(username)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
	}

	@Override
	public User getById(String id) {
		return userRepository.findById(id)
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
	}

	@Override
	public User create(UserDTO dto) {
		User user = new User();
		applyDto(user, dto);
		return userRepository.save(user);
	}

	@Override
	public User update(String id, UserDTO dto) {
		User user = getById(id);
		applyDto(user, dto);
		return userRepository.save(user);
	}

	@Override
	public void delete(String id) {
		User user = getById(id);
		userRepository.delete(user);
	}

	private void applyDto(User user, UserDTO dto) {
		if (dto.getUsername() != null) {
			user.setUsername(dto.getUsername());
		}
		if (dto.getEmail() != null) {
			user.setEmail(dto.getEmail());
		}
		if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
			user.setPassword(passwordEncoder.encode(dto.getPassword()));
		}
		if (dto.getDisplayName() != null) {
			user.setDisplayName(dto.getDisplayName());
		}
		if (dto.getAvatarUrl() != null) {
			user.setAvatarUrl(dto.getAvatarUrl());
		}
		if (dto.getRole() != null) {
			user.setRole(dto.getRole());
		}
		if (dto.getActive() != null) {
			user.setActive(dto.getActive());
		}
	}
}
