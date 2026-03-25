package phattrienungdungvoi2ee.bai6_qlsp.Controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import phattrienungdungvoi2ee.bai6_qlsp.Model.CartItem;
import phattrienungdungvoi2ee.bai6_qlsp.Model.Account;
import phattrienungdungvoi2ee.bai6_qlsp.Model.Order;
import phattrienungdungvoi2ee.bai6_qlsp.Service.AccountService;
import phattrienungdungvoi2ee.bai6_qlsp.Service.CartService;
import phattrienungdungvoi2ee.bai6_qlsp.Service.OrderService;
import phattrienungdungvoi2ee.bai6_qlsp.Service.ProductService;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService productService;

    @Autowired
    private AccountService accountService;

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Long productId,
                            @RequestParam(defaultValue = "1") int quantity,
                            HttpSession session,
                            RedirectAttributes redirectAttributes) {
        if (productService.getProductById(productId) == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không tìm thấy sản phẩm để thêm vào giỏ hàng.");
            return "redirect:/products";
        }

        cartService.addToCart(productId, quantity, session);
        redirectAttributes.addFlashAttribute("successMessage", "Đã thêm sản phẩm vào giỏ hàng.");
        return "redirect:/products";
    }

    @GetMapping
    public String viewCart(Model model, HttpSession session) {
        populateCartModel(model, session);
        return "cart/index";
    }

    @PostMapping("/update/{productId}")
    public String updateCart(@PathVariable Long productId,
                             @RequestParam(defaultValue = "1") int quantity,
                             HttpSession session,
                             RedirectAttributes redirectAttributes) {
        cartService.updateQuantity(productId, quantity, session);
        redirectAttributes.addFlashAttribute("successMessage", "Cập nhật giỏ hàng thành công.");
        return "redirect:/cart";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Long productId,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        cartService.removeFromCart(productId, session);
        redirectAttributes.addFlashAttribute("successMessage", "Đã xóa sản phẩm khỏi giỏ hàng.");
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(HttpSession session,
                           Authentication authentication,
                           RedirectAttributes redirectAttributes) {
        List<CartItem> cartItems = cartService.getCartItems(session);
        if (cartItems.isEmpty()) {
            redirectAttributes.addFlashAttribute("errorMessage", "Giỏ hàng đang trống, chưa thể đặt hàng.");
            return "redirect:/cart";
        }

        Account account = accountService.getAccountByLoginName(authentication.getName());
        if (account == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Không xác định được tài khoản đặt hàng.");
            return "redirect:/cart";
        }

        Order order = orderService.createOrder(account, cartItems);
        cartService.clearCart(session);
        redirectAttributes.addFlashAttribute("successMessage", "Đặt hàng thành công. Mã đơn hàng: #" + order.getId());
        return "redirect:/products";
    }

    private void populateCartModel(Model model, HttpSession session) {
        model.addAttribute("cartItems", cartService.getCartItems(session));
        model.addAttribute("cartTotal", cartService.getCartTotal(session));
        model.addAttribute("cartItemCount", cartService.getCartItemCount(session));
    }
}
