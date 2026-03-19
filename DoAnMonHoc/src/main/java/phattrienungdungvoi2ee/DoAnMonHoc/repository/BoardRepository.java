package phattrienungdungvoi2ee.DoAnMonHoc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import phattrienungdungvoi2ee.DoAnMonHoc.entity.Board;

public interface BoardRepository extends JpaRepository<Board, String> {
}
