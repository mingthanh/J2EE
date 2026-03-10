package phattrienungdungvoi2ee.bai6_qlsp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phattrienungdungvoi2ee.bai6_qlsp.Model.Category;
import phattrienungdungvoi2ee.bai6_qlsp.Repository.CategoryRepository;
import phattrienungdungvoi2ee.bai6_qlsp.Repository.ProductRepository;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void saveCategory(Category category) {
        categoryRepository.save(category);
    }

    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public boolean existsNameForCreate(String name) {
        return categoryRepository.existsByNameIgnoreCase(name);
    }

    public boolean existsNameForUpdate(String name, Long id) {
        return categoryRepository.existsByNameIgnoreCaseAndIdNot(name, id);
    }

    public boolean canDelete(Long id) {
        return productRepository.countByCategoryId(id) == 0;
    }

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }
}
