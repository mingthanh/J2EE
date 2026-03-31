package phattrienungdungvoi2ee.DoAnMonHoc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.StatusType;

public interface StatusTypeRepository extends JpaRepository<StatusType, Integer> {
	Optional<StatusType> findByName(String name);
}
