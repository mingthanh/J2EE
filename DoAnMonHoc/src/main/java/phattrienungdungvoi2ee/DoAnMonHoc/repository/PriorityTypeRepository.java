package phattrienungdungvoi2ee.DoAnMonHoc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.PriorityType;

public interface PriorityTypeRepository extends JpaRepository<PriorityType, Integer> {
	Optional<PriorityType> findByName(String name);
}
