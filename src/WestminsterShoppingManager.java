import java.io.*;
import java.util.*;

public class WestminsterShoppingManager implements ShoppingManager, Serializable {
    // Constants
    private static final int MAX_PRODUCTS = 2;
    private static final String DATA_FILE = "products.txt";
    // Instance variables
    private static List<Product> productList;
    private final Scanner scanner;
    private static final long serialVersionUID = 1L;


    // Constructor
    public WestminsterShoppingManager() {
        // Initialize the scanner and product list
        // Initialize the product list only if it's null
        if (productList == null) {
            productList = new ArrayList<>();
        }
        this.scanner = new Scanner(System.in);
    }

    public List<Product> getProductList() {
        return productList;
    }

    // Method to add a product to the system
    public void addProduct(Product product) {
        if (productList.size() == MAX_PRODUCTS) {
            System.out.println("Maximum number of products reached. Product not added.");
        } else {
            // Add the product to the list
            productList.add(product);
            System.out.println("Product added successfully.");
        }
    }

    // Method to delete a product from the system
    public void deleteProduct(Product product) {
        // Attempt to remove the product from the list
        if (productList.remove(product)) {
            System.out.println("Product deleted successfully.");
        } else {
            System.out.println("Product not found.");
        }
    }

    // Method to print the list of products in the system
    public void printProducts() {
        // Check if the product list is empty
        if (productList.isEmpty()) {
            System.out.println("No products available.");
            return;
        }

        // Sort the product list by product ID
        Collections.sort(productList, Comparator.comparing(Product::getProductId));

        System.out.println("List of Products:");
        System.out.println("----------------------------------------");
        for (Product product : productList) {
            // Display product information
            product.displayProductInfo();
            System.out.println("Type: " + (product instanceof Electronics ? "Electronics" : "Clothing"));
            System.out.println("----------------------------------------");
        }
    }

    // Method to save the list of products to a file
    public void saveProducts() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            // Write the product list to the file
            oos.writeObject(productList);
            System.out.println("Products saved successfully.");
        } catch (IOException e) {
            System.out.println("Error saving products to file: " + e.getMessage());
        }
    }

    // Method to display the console menu for the Westminster Shopping Manager
    public void displayMenu() {
        while (true) {
            // Display the main menu options
            System.out.println("\n=== Westminster Shopping Manager Menu ===");
            System.out.println("1. Add Electronics");
            System.out.println("2. Add Clothing");
            System.out.println("3. Delete Product");
            System.out.println("4. Print Products");
            System.out.println("5. Save Products");
            System.out.println("6. For GUI");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            // Read the user's choice
            int choice = scanner.nextInt();
            scanner.nextLine();

            // Process the user's choice using a switch statement
            switch (choice) {
                case 1:
                    addProduct(createElectronics());
                    break;
                case 2:
                    addProduct(createClothing());
                    break;
                case 3:
                    deleteProduct();
                    break;
                case 4:
                    printProducts();
                    break;
                case 5:
                    saveProducts();
                    break;
                case 6:
                    System.out.println("GUI is loading...");
                    Gui gui = new Gui();
                    gui.start(null);
                    break;
                case 0:
                    System.out.println("Exiting Westminster Shopping Manager. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    //method to add electronics
    private Electronics createElectronics() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Electronics Details:");

        // Product ID
        System.out.print("Product ID: ");
        String productId = scanner.nextLine();
        if (!productList.isEmpty()) {
            do {
                for (int i = 0; i < productList.size(); i++) {
                    if (productId.equals(productList.get(i).getProductId())) {
                        System.out.println("Product ID already exists. Please enter a valid value.");
                        System.out.print("Product ID: ");
                        productId = scanner.nextLine();
                    }
                }
            } while (productId.equals(productList.get(0).getProductId()));
        }

        // Product Name
        System.out.print("Product Name: ");
        String productName = scanner.nextLine();

        // Available Items (with validation)
        int availableItems;
        do {
            System.out.print("Available Items: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                System.out.print("Available Items: ");
                scanner.next(); // Consume the invalid input
            }
            availableItems = scanner.nextInt();
            if (availableItems < 0) {
                System.out.println("Available items must be non-negative. Please enter a valid value.");
            }
        } while (availableItems < 0);

        // Price (with validation)
        double price;
        do {
            System.out.print("Price: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.print("Price: ");
                scanner.next(); // Consume the invalid input
            }
            price = scanner.nextDouble();
            if (price < 0) {
                System.out.println("Price must be non-negative. Please enter a valid value.");
            }
        } while (price < 0);

        scanner.nextLine(); // Consume the newline character

        // Brand
        System.out.print("Brand: ");
        String brand = scanner.nextLine();

        // Warranty Period (with validation - must be a digit)
        String warranty;
        do {
            System.out.print("Warranty Period (in months): ");
            warranty = scanner.nextLine();
            if (!warranty.matches("\\d+")) {
                System.out.println("Invalid input. Warranty period must be a digit. Please enter a valid value.");
            }
        } while (!warranty.matches("\\d+"));

        return new Electronics(productId, productName, availableItems, price, brand, warranty);
    }


    private Clothing createClothing() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Clothing Details:");

        // Product ID
        System.out.print("Product ID: ");
        String productId = scanner.nextLine();
        if (!productList.isEmpty()) {
            do {
                for (int i = 0; i < productList.size(); i++) {
                    if (productId.equals(productList.get(i).getProductId())) {
                        System.out.println("Product ID already exists. Please enter a valid value.");
                        System.out.print("Product ID: ");
                        productId = scanner.nextLine();
                    }
                }
            } while (productId.equals(productList.get(0).getProductId()));
        }

        // Product Name
        System.out.print("Product Name: ");
        String productName = scanner.nextLine();

        // Available Items (with validation)
        int availableItems;
        do {
            System.out.print("Available Items: ");
            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid integer.");
                System.out.print("Available Items: ");
                scanner.next(); // Consume the invalid input
            }
            availableItems = scanner.nextInt();
            if (availableItems < 0) {
                System.out.println("Available items must be non-negative. Please enter a valid value.");
            }
        } while (availableItems < 0);

        // Price (with validation)
        double price;
        do {
            System.out.print("Price: ");
            while (!scanner.hasNextDouble()) {
                System.out.println("Invalid input. Please enter a valid number.");
                System.out.print("Price: ");
                scanner.next(); // Consume the invalid input
            }
            price = scanner.nextDouble();
            if (price < 0) {
                System.out.println("Price must be non-negative. Please enter a valid value.");
            }
        } while (price < 0);

        scanner.nextLine(); // Consume the newline character

        // Size
        System.out.print("Size: ");
        String size = scanner.nextLine();

        // Color
        System.out.print("Color: ");
        String color = scanner.nextLine();

        return new Clothing(productId, productName, availableItems, price, size, color);
    }


    //method to delete product
    private void deleteProduct() {
        System.out.println("----------------------------------------");

        System.out.println("Product IDs in the system:");

        // Sort the product list by product ID
        for (Product product : productList) {
            System.out.println(product.getProductId());
        }
        System.out.println("----------------------------------------");
        System.out.print("Enter the product ID to delete: ");
        String productIdToDelete = scanner.nextLine();//read the product id to delete

        boolean found = false;
        Product deletedProduct = null;

        // Iterate through the product list to find the product to delete
        Iterator<Product> iterator = productList.iterator();
        while (iterator.hasNext()) {
            Product product = iterator.next();//get the product
            if (product.getProductId().equals(productIdToDelete)) {//check the product id
                deletedProduct = product;
                iterator.remove();
                found = true;
                break;
            }
        }

        // Display the result of the deletion
        if (found) {
            System.out.println("Product deleted successfully.");
            deletedProduct.displayProductInfo();
            System.out.println("Type: " + (deletedProduct instanceof Electronics ? "Electronics" : "Clothing"));
            System.out.println("Total number of products left: " + productList.size());
        } else {
            System.out.println("Product not found. Deletion failed.");
        }
    }

    public static void main(String[] args) {
        // Create a new instance of the Westminster Shopping Manager
        WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
        shoppingManager.loadProducts();
        shoppingManager.displayMenu();
    }

    //method to load products
    private void loadProducts() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {//read the file
            productList = (List<Product>) ois.readObject();//read the product list
            System.out.println("Products loaded successfully.");
        } catch (FileNotFoundException e) {//check the file
            System.out.println("No saved products file found. Starting with an empty product list.");
        } catch (IOException | ClassNotFoundException e) {//check the file
            System.out.println("Error loading products from file: " + e.getMessage());
        }
    }
}
