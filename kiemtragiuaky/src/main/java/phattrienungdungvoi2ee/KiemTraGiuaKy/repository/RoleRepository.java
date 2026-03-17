package phattrienungdungvoi2ee.KiemTraGiuaKy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.KiemTraGiuaKy.model.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}
