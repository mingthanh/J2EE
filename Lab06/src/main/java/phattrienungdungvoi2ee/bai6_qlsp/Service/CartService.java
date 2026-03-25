package phattrienungdungvoi2ee.bai6_qlsp.Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import phattrienungdungvoi2ee.bai6_qlsp.Model.CartItem;
import phattrienungdungvoi2ee.bai6_qlsp.Model.Product;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartService {

    private static final String CART_SESSION_KEY = "cart";

    @Autowired
    private ProductService productService;

    public void addToCart(Long productId, int quantity, HttpSession session) {
        if (quantity < 1) {
            quantity = 1;
        }

        Product product = productService.getProductById(productId);
        if (product == null) {
            return;
        }

        Map<Long, CartItem> cart = getCart(session);
        CartItem existingItem = cart.get(productId);
        if (existingItem == null) {
            cart.put(productId, new CartItem(product, quantity));
        } else {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        }
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void updateQuantity(Long productId, int quantity, HttpSession session) {
        Map<Long, CartItem> cart = getCart(session);
        if (!cart.containsKey(productId)) {
            return;
        }

        if (quantity <= 0) {
            cart.remove(productId);
        } else {
            cart.get(productId).setQuantity(quantity);
        }
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public void removeFromCart(Long productId, HttpSession session) {
        Map<Long, CartItem> cart = getCart(session);
        cart.remove(productId);
        session.setAttribute(CART_SESSION_KEY, cart);
    }

    public List<CartItem> getCartItems(HttpSession session) {
        return new ArrayList<>(getCart(session).values());
    }

    public long getCartTotal(HttpSession session) {
        return getCartItems(session)
                .stream()
                .mapToLong(CartItem::getLineTotal)
                .sum();
    }

    public int getCartItemCount(HttpSession session) {
        return getCartItems(session)
                .stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }

    public void clearCart(HttpSession session) {
        session.removeAttribute(CART_SESSION_KEY);
    }

    @SuppressWarnings("unchecked")
    private Map<Long, CartItem> getCart(HttpSession session) {
        Object cartObject = session.getAttribute(CART_SESSION_KEY);
        if (cartObject instanceof Map<?, ?> cartMap) {
            return (Map<Long, CartItem>) cartMap;
        }

        Map<Long, CartItem> cart = new LinkedHashMap<>();
        session.setAttribute(CART_SESSION_KEY, cart);
        return cart;
    }
}
