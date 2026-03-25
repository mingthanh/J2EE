package phattrienungdungvoi2ee.bai6_qlsp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phattrienungdungvoi2ee.bai6_qlsp.Model.Product;
import phattrienungdungvoi2ee.bai6_qlsp.Repository.ProductRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class ProductService {

    public static final int PAGE_SIZE = 5;

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> searchProducts(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return productRepository.findAll();
        }
        return productRepository.findByNameContainingIgnoreCase(keyword.trim());
    }

    public List<Product> getFilteredProducts(String keyword, Long categoryId, String sort) {
        return productRepository.findAll()
                .stream()
                .filter(product -> matchesKeyword(product, keyword))
                .filter(product -> matchesCategory(product, categoryId))
                .sorted(resolveComparator(sort))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByPage(List<Product> products, int page) {
        int safePage = Math.max(page, 1);
        int fromIndex = (safePage - 1) * PAGE_SIZE;
        if (fromIndex >= products.size()) {
            return List.of();
        }

        int toIndex = Math.min(fromIndex + PAGE_SIZE, products.size());
        return products.subList(fromIndex, toIndex);
    }

    public int getTotalPages(int totalItems) {
        return Math.max(1, (int) Math.ceil((double) totalItems / PAGE_SIZE));
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private boolean matchesKeyword(Product product, String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return true;
        }

        String normalizedKeyword = keyword.trim().toLowerCase(Locale.ROOT);
        return product.getName() != null && product.getName().toLowerCase(Locale.ROOT).contains(normalizedKeyword);
    }

    private boolean matchesCategory(Product product, Long categoryId) {
        if (categoryId == null) {
            return true;
        }

        return product.getCategory() != null && categoryId.equals(product.getCategory().getId());
    }

    private Comparator<Product> resolveComparator(String sort) {
        Comparator<Product> defaultComparator = Comparator.comparing(Product::getId, Comparator.nullsLast(Long::compareTo)).reversed();

        if (sort == null || sort.isBlank()) {
            return defaultComparator;
        }

        return switch (sort) {
            case "priceAsc" -> Comparator.comparing(Product::getPrice, Comparator.nullsLast(Long::compareTo))
                    .thenComparing(Product::getId, Comparator.nullsLast(Long::compareTo));
            case "priceDesc" -> Comparator.comparing(Product::getPrice, Comparator.nullsLast(Comparator.reverseOrder()))
                    .thenComparing(Product::getId, Comparator.nullsLast(Comparator.reverseOrder()));
            default -> defaultComparator;
        };
    }
}
