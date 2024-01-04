import java.io.Serializable;

class Electronics extends Product implements Serializable {
    private final String productId;
    private final String productName;
    private final int availableItems;
    private final double price;
    private String brand;
    private String warranty;
    private static final long serialVersionUID = 1L;


    public Electronics(String productId, String productName, int availableItems, double price, String brand, String warranty) {
        super(productId, productName, availableItems, price);
        this.productId = productId;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
        this.brand = brand;
        this.warranty = warranty;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public int getAvailableItems() {
        return availableItems;
    }

    public double getPrice() {
        return price;
    }

    public void displayProductInfo() {
        System.out.println("Electronics Product Information:");
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + productName);
        System.out.println("Brand: " + brand);
        System.out.println("Warranty Period: " + warranty);
        System.out.println("Price: $" + price);
        System.out.println("Available Items: " + availableItems);
    }

    // Getter and Setter methods for brand and warranty...

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }
}
