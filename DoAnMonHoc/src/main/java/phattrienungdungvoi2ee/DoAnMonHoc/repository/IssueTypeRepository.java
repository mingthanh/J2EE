package phattrienungdungvoi2ee.DoAnMonHoc.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.IssueType;

public interface IssueTypeRepository extends JpaRepository<IssueType, Integer> {
	Optional<IssueType> findByName(String name);
}
