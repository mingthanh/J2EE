package phattrienungdungvoi2ee.DoAnMonHoc.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;
import phattrienungdungvoi2ee.DoAnMonHoc.repository.UserRepository;

@Component
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final UserRepository userRepository;

	public OAuth2SuccessHandler(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

		String email = oAuth2User.getAttribute("email");
		String name = oAuth2User.getAttribute("name");
		String picture = oAuth2User.getAttribute("picture");

		// Kiểm tra user đã tồn tại hay chưa
		User user = userRepository.findByEmail(email).orElse(null);

		if (user == null) {
			// Tạo user mới từ Google OAuth2
			user = new User();
			user.setId(UUID.randomUUID().toString());
			user.setEmail(email);
			user.setUsername(email.split("@")[0]); // Lấy phần trước @ làm username
			user.setDisplayName(name);
			user.setAvatarUrl(picture);
			user.setRole(null);
			user.setActive(true);
			user.setCreatedAt(LocalDateTime.now());
			user.setPassword(""); // Password để trống vì là OAuth2

			userRepository.save(user);
		} else {
			// Cập nhật thông tin nếu user đã tồn tại
			user.setDisplayName(name);
			user.setAvatarUrl(picture);
			userRepository.save(user);
		}

		setDefaultTargetUrl("/dashboard");
		super.onAuthenticationSuccess(request, response, authentication);
	}
}
