package phattrienungdungvoi2ee.bai6_qlsp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import phattrienungdungvoi2ee.bai6_qlsp.Model.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    long countByCategoryId(Long categoryId);
    List<Product> findByNameContainingIgnoreCase(String keyword);
}
