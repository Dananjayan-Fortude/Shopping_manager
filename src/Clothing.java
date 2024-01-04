import java.io.Serializable;

class Clothing extends Product implements Serializable {
    private String productId;
    private String productName;
    private int availableItems;
    private double price;
    private String size;
    private String color;
    private static final long serialVersionUID = 1L;


    // Constructor for Clothing class...
    public Clothing(String productId, String productName, int availableItems, double price, String size, String color) {
        super(productId, productName, availableItems, price);
        this.productId = productId;
        this.productName = productName;
        this.availableItems = availableItems;
        this.price = price;
        this.size = size;
        this.color = color;
    }

    //methods from Product interface...
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
        System.out.println("Clothing Product Information:");
        System.out.println("Product ID: " + productId);
        System.out.println("Product Name: " + productName);
        System.out.println("Size: " + size);
        System.out.println("Color: " + color);
        System.out.println("Price: $" + price);
        System.out.println("Available Items: " + availableItems);
    }

    // Getter and Setter methods for size and color...

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

