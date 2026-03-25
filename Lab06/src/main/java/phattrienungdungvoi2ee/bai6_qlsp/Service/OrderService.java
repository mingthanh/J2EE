package phattrienungdungvoi2ee.bai6_qlsp.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import phattrienungdungvoi2ee.bai6_qlsp.Model.Account;
import phattrienungdungvoi2ee.bai6_qlsp.Model.CartItem;
import phattrienungdungvoi2ee.bai6_qlsp.Model.Order;
import phattrienungdungvoi2ee.bai6_qlsp.Model.OrderDetail;
import phattrienungdungvoi2ee.bai6_qlsp.Repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order createOrder(Account account, List<CartItem> cartItems) {
        Order order = new Order();
        order.setCreatedAt(LocalDateTime.now());
        order.setTotalAmount(cartItems.stream().mapToLong(CartItem::getLineTotal).sum());
        order.setAccount(account);

        for (CartItem cartItem : cartItems) {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(cartItem.getProduct());
            orderDetail.setQuantity(cartItem.getQuantity());
            orderDetail.setPrice(cartItem.getProduct().getPrice());
            orderDetail.setLineTotal(cartItem.getLineTotal());
            order.getOrderDetails().add(orderDetail);
        }

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAllByOrderByCreatedAtDesc();
    }
}
