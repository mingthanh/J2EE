package phattrienungdungvoi2ee.DoAnMonHoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
}
