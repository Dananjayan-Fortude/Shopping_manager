import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Gui {
    static List<Product> items;
    static String category;
    private JTable table;
    JFrame signUp = new JFrame("Sign Up");
    JFrame login = new JFrame("Login");
    JFrame mainScreen = new JFrame("Westminster Shopping Center");
    WestminsterShoppingManager manager = new WestminsterShoppingManager();
    private User user = new User();

    public void loadItems() {
        // Load the items from the file
        items = manager.getProductList();
    }

    public void signup() {
        // Create and set up the frame
        signUp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a canvas to paint on
        Canvas canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                // Load the image
                try {
                    URL url = new URL("https://img.freepik.com/free-vector/locker_53876-25496.jpg?size=626&ext=jpg");
                    Image image = ImageIO.read(url);
                    g.drawImage(image, 0, 0, 150, 150, this);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        };
        canvas.setBounds(10, 10, 150, 150);
        //fields for username and password
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        usernameField.setBounds(255, 20, 200, 25);
        passwordField.setBounds(255, 60, 200, 25);

        //label for username and password
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(190, 20, 90, 25);
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(190, 60, 90, 25);

        //button for login and cancel
        JButton sign_upButton = new JButton("Sign Up");
        sign_upButton.setBounds(360, 120, 110, 25);
        sign_upButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This code will be executed when the "Sign Up" button is clicked
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (!user.addUser(username, password)) {
                    JOptionPane.showMessageDialog(null, "Username already exists", "Error", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                } else {
//                    // User added successfully
//                    usernameField.setText("");
//                    passwordField.setText("");

                    signUp.dispose(); // Close the sign-up frame
                    login(); // Open the login frame or perform other actions
                }
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180, 120, 110, 25);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This code will be executed when the "Cancel" button is clicked
                signUp.dispose(); // Close the frame
                login.dispose();
                mainScreen.dispose();
            }
        });

        signUp.add(canvas);
        signUp.add(usernameLabel);
        signUp.add(passwordLabel);
        signUp.add(usernameField);
        signUp.add(passwordField);
        signUp.add(sign_upButton);
        signUp.add(cancelButton);

        // Set the size and make the frame visible
        signUp.setSize(500, 210);
        signUp.setLayout(null);
        signUp.setVisible(true);
    }

    public void login() {
        // Create and set up the frame
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a canvas to paint on
        Canvas canvas = new Canvas() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                // Load the image
                try {
                    URL url = new URL("https://img.freepik.com/free-vector/locker_53876-25496.jpg?size=626&ext=jpg");
                    Image image = ImageIO.read(url);
                    g.drawImage(image, 0, 0, 150, 150, this);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        };
        canvas.setBounds(10, 10, 150, 150);
        //fields for username and password
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        usernameField.setBounds(255, 20, 200, 25);
        passwordField.setBounds(255, 60, 200, 25);

        //label for username and password
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setBounds(190, 20, 90, 25);
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(190, 60, 90, 25);

        //button for login and cancel
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(360, 120, 110, 25);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This code will be executed when the "Login" button is clicked
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter a username and password", "Error", JOptionPane.ERROR_MESSAGE);
                    usernameField.setText("");
                    passwordField.setText("");
                } else {
                    user.setUsername(username);
                    user.setPassword(password);

                    if (!user.checkUser(user)) {
                        JOptionPane.showMessageDialog(null, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
                        usernameField.setText("");
                        passwordField.setText("");
                    } else {
                        // User logged in successfully
                        usernameField.setText("");
                        passwordField.setText("");

                        login.dispose(); // Close the login frame
                        mainScreen(); // Open the main screen frame or perform other actions
                    }
                }
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.setBounds(180, 120, 110, 25);
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This code will be executed when the "Cancel" button is clicked
                login.setVisible(false); // Close the frame
                mainScreen();
            }
        });
        JButton signupButton = new JButton("Sign Up");
        signupButton.setBounds(270, 150, 110, 25);
        signupButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // This code will be executed when the "Cancel" button is clicked
                login.setVisible(false);
                signup();
            }
        });

        login.add(canvas);
        login.add(usernameLabel);
        login.add(passwordLabel);
        login.add(usernameField);
        login.add(passwordField);
        login.add(loginButton);
        login.add(cancelButton);
        login.add(signupButton);


        // Set the size and make the frame visible
        login.setSize(500, 210);
        login.setLayout(null);
        login.setVisible(true);
    }

    public void mainScreen() {
        loadItems();
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.setLayout(null);
        Font font = new Font("Calibri", Font.PLAIN, 15);

        //label
        JLabel welcomeLabel = new JLabel("Welcome " + user.getUsername());
        welcomeLabel.setBounds(10, 10, 200, 25);
        welcomeLabel.setFont(font);
        mainScreen.add(welcomeLabel);
        JLabel categoryLabel = new JLabel("Select Product Category");
        categoryLabel.setBounds(160, 10, 200, 25);
        mainScreen.add(categoryLabel);

        //buttons
        JButton shoppingCart = new JButton("Shopping Cart");
        shoppingCart.setBounds(500, 10, 150, 30);
        mainScreen.add(shoppingCart);


        //combo box
        String[] categories = {"All", "Electronics", "Clothing"};
        JComboBox categoryList = new JComboBox(categories);
        categoryList.setBounds(320, 10, 150, 30);
        categoryList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                items = new ArrayList<>();
                category = (String) categoryList.getSelectedItem();
                switch (Objects.requireNonNull(category)) {
                    case "All":
                        items = manager.getProductList();
                        break;
                    case "Electronics":
                        items = manager.getProductList().stream()
                                .filter(product -> product instanceof Electronics)
                                .map(product -> (Electronics) product)
                                .collect(Collectors.toList());
                        break;
                    case "Clothing":
                        items = manager.getProductList().stream()
                                .filter(product -> product instanceof Clothing)
                                .map(product -> (Clothing) product)
                                .collect(Collectors.toList());
                        break;
                }
                table();
            }
        });
        mainScreen.add(categoryList);

        //calling the table method
        table();

        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        separator.setPreferredSize(new Dimension(mainScreen.getWidth(), 1));
        mainScreen.add(separator);

        mainScreen.setSize(650, 500);
        mainScreen.setVisible(true);
    }

    public void table() {
        //table
        List<String[]> data = new ArrayList<>();
        if (!items.isEmpty()) {
            for (Product item : items) {
                String[] rowData = new String[5];
                rowData[0] = item.getProductId();
                rowData[1] = item.getProductName();
                rowData[2] = item instanceof Electronics ? "Electronics" : "Clothing";
                rowData[3] = String.valueOf(item.getPrice());
                if (item instanceof Electronics) {
                    rowData[4] = ((Electronics) item).getBrand() + ", " + ((Electronics) item).getWarranty();
                } else if (item instanceof Clothing) {
                    rowData[4] = ((Clothing) item).getSize() + ", " + ((Clothing) item).getColor();
                }
                data.add(rowData);
            }

        } else {
            String[] rowData = new String[5];
            rowData[0] = "";
            rowData[1] = "";
            rowData[2] = "";
            rowData[3] = "";
            rowData[4] = "";
            data.add(rowData);
        }
        String[][] dataArray = data.toArray(new String[0][]);
        String[] column = {"Product ID", "Name", "Category", "Price($)", "Info"};
        DefaultTableModel model = new DefaultTableModel(dataArray, column) {
            public boolean isCellEditable(int row, int column) {
                return false; // Set all cells to non-editable
            }
        };

        if (table == null) {
            table = new JTable(model);
            JScrollPane sp = new JScrollPane(table);
            sp.setBounds(20, 60, 610, 200);
            mainScreen.add(sp);
        } else {
            table.setModel(model);
        }
    }

    public void start(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Gui gui = new Gui();
            gui.login();
        });
    }

}
