import java.io.Serializable;
import java.util.List;

interface ShoppingManager extends Serializable {
    void addProduct(Product product);
    void deleteProduct(Product product);
    void printProducts();;
    void saveProducts();
    void displayMenu();
}
