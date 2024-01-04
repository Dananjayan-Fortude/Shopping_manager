import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class ShoppingCart implements Serializable {
    private List<Product> cartItems;

    public ShoppingCart() {
        this.cartItems = new ArrayList<>();
    }

    public void addItem(Product product) {
        cartItems.add(product);
    }

    public void removeItem(Product product) {
        cartItems.remove(product);
    }

    public double calculateTotal() {
        double total = 0.0;
        for (Product product : cartItems) {
            total += product.getPrice();
        }
        return total;
    }
}
