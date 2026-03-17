package phattrienungdungvoi2ee.KiemTraGiuaKy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Student;
import phattrienungdungvoi2ee.KiemTraGiuaKy.repository.StudentRepository;
import phattrienungdungvoi2ee.KiemTraGiuaKy.service.StudentService;

import java.util.HashSet;
import java.util.Set;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(StudentRepository studentRepository) {
        return username -> {
            Student student = studentRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
            Set<GrantedAuthority> authorities = new HashSet<>();
            student.getRoles().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName())));
            return new User(student.getUsername(), student.getPassword(), authorities);
        };
    }

    @Bean
    public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService(StudentService studentService) {
        DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
        return request -> {
            OAuth2User oauth2User = delegate.loadUser(request);
            String email = oauth2User.getAttribute("email");
            if (email != null) {
                studentService.ensureOauthStudent(email);
            }
            Set<GrantedAuthority> authorities = new HashSet<>(oauth2User.getAuthorities());
            authorities.add(new SimpleGrantedAuthority("ROLE_STUDENT"));
            String nameAttributeKey = email != null ? "email" : "sub";
            return new DefaultOAuth2User(authorities, oauth2User.getAttributes(), nameAttributeKey);
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService,
                                                   @Nullable ClientRegistrationRepository clientRegistrationRepository)
            throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/home", "/courses", "/login", "/register",
                                "/css/**", "/images/**", "/oauth2/**", "/login/oauth2/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/enroll/**", "/my-courses").hasRole("STUDENT")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );

        if (clientRegistrationRepository != null) {
            http.oauth2Login(oauth2 -> oauth2
                    .loginPage("/login")
                    .userInfoEndpoint(userInfo -> userInfo.userService(oauth2UserService))
                    .defaultSuccessUrl("/home", true)
            );
        }

        return http.build();
    }
}
