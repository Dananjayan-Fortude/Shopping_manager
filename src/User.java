import java.io.*;
import java.util.ArrayList;
import java.util.List;

interface UserInterface extends Serializable {
    String getUsername();

    String getPassword();
}

class User implements Serializable, UserInterface {
    private String username;
    private String password;
    private List<UserInterface> userDetailsList;
    private static final String USER_FILE = "userDetails.txt";
    private static final long serialVersionUID = 1887401392057699497L;


    public User() {
        this.username = "";
        this.password = "";
        this.userDetailsList = new ArrayList<>();
        loadUsers();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean addUser(String username, String password) {
        try {

            // Check if the username already exists
            for (UserInterface userInterface : userDetailsList) {
                if (username.equals(userInterface.getUsername())) {
                    // Username already exists, return false without adding the user
                    return false;
                }
            }
            setUserDetails(username, password);

            // If the loop completes without returning, add the new user
            userDetailsList.add(this);

            // Write the updated user list to the file
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(USER_FILE))) {
                oos.writeObject(userDetailsList);
                System.out.println("User details saved successfully.");
            } catch (IOException e) {
                System.out.println("Error saving user details to file: " + e.getMessage());
            }

            return true;
        } catch (Exception e) {
            // Handle any unexpected exceptions
            System.out.println("Unexpected error: " + e.getMessage());
            return false;
        }
    }

    public void setUserDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public boolean checkUser(UserInterface user) {
        for (UserInterface u : userDetailsList) {
            if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
                return true;
            }
        }
        return false;
    }

    public void loadUsers() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(USER_FILE))) {//read the file
            userDetailsList = (List<UserInterface>) ois.readObject();//read the product list
        } catch (FileNotFoundException e) {//check the file
            System.out.println("No saved user file found. Starting with an empty user list.");
        } catch (IOException | ClassNotFoundException e) {//check the file
            System.out.println("Error loading users from file: " + e.getMessage());
        }
    }
}
