package phattrienungdungvoi2ee.bai6_qlsp.Controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import phattrienungdungvoi2ee.bai6_qlsp.Model.Category;
import phattrienungdungvoi2ee.bai6_qlsp.Service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // LIST
    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());
        return "category/list";
    }

    // SHOW ADD FORM
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    // SHOW EDIT FORM
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy danh mục.");
            return "redirect:/categories";
        }
        model.addAttribute("category", category);
        return "category/add";
    }

    // SAVE
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("category") Category category,
                       BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        if (category.getId() == null) {
            if (categoryService.existsNameForCreate(category.getName())) {
                bindingResult.rejectValue("name", "name.duplicate", "Tên danh mục đã tồn tại.");
            }
        } else if (categoryService.existsNameForUpdate(category.getName(), category.getId())) {
            bindingResult.rejectValue("name", "name.duplicate", "Tên danh mục đã tồn tại.");
        }

        if (bindingResult.hasErrors()) {
            return "category/add";
        }

        categoryService.saveCategory(category);
        redirectAttributes.addFlashAttribute("successMessage", "Lưu danh mục thành công.");
        return "redirect:/categories";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Category category = categoryService.getCategoryById(id);
        if (category == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy danh mục.");
            return "redirect:/categories";
        }

        if (!categoryService.canDelete(id)) {
            redirectAttributes.addFlashAttribute("errorMessage", "Danh mục đang được sử dụng bởi sản phẩm, không thể xóa.");
            return "redirect:/categories";
        }

        categoryService.deleteCategory(id);
        redirectAttributes.addFlashAttribute("successMessage", "Xóa danh mục thành công.");
        return "redirect:/categories";
    }
}
