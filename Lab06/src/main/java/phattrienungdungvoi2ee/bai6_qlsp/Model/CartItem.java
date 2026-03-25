package phattrienungdungvoi2ee.bai6_qlsp.Model;

public class CartItem {

    private final Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public long getLineTotal() {
        long unitPrice = product.getPrice() == null ? 0L : product.getPrice();
        return unitPrice * quantity;
    }
}
