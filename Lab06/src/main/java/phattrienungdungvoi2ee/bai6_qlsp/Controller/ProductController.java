package phattrienungdungvoi2ee.bai6_qlsp.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import phattrienungdungvoi2ee.bai6_qlsp.Model.Category;
import phattrienungdungvoi2ee.bai6_qlsp.Model.Product;
import phattrienungdungvoi2ee.bai6_qlsp.Service.CategoryService;
import phattrienungdungvoi2ee.bai6_qlsp.Service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    // LIST
    @GetMapping
    public String listProducts(@RequestParam(required = false) String keyword, Model model) {
        model.addAttribute("products", productService.searchProducts(keyword));
        model.addAttribute("keyword", keyword == null ? "" : keyword);
        return "product/list";
    }

    // SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/add";
    }

    // SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Product product = productService.getProductById(id);
        if (product == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy sản phẩm.");
            return "redirect:/products";
        }

        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "product/add";
    }

    // SAVE
    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product,
                              BindingResult bindingResult,
                              @RequestParam(required = false) Long categoryId,
                              Model model,
                              RedirectAttributes redirectAttributes) {
        if (categoryId != null) {
            Category category = categoryService.getCategoryById(categoryId);
            if (category == null) {
                bindingResult.rejectValue("category", "category.invalid", "Danh mục không hợp lệ.");
            } else {
                product.setCategory(category);
            }
        } else {
            product.setCategory(null);
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "product/add";
        }

        productService.saveProduct(product);
        redirectAttributes.addFlashAttribute("successMessage", "Lưu sản phẩm thành công.");
        return "redirect:/products";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Product product = productService.getProductById(id);
        if (product == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy sản phẩm.");
            return "redirect:/products";
        }

        productService.deleteProduct(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa sản phẩm thành công.");
        return "redirect:/products";
    }
}
