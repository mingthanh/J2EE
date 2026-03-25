package phattrienungdungvoi2ee.DoAnMonHoc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	Optional<User> findByUsername(String username);
}
