package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * This class creates the User object and contains methods to handle them.
 */
public class User {
    private final int User_ID;
    private final String Username;
    private final String Password;
    private static final ObservableList<User> allUsers = FXCollections.observableArrayList();

    /**
     * This method is a constructor for the User object.
     * @param userID The unique identifier for the User.
     * @param username The Name of the User used on sign in.
     * @param password The Password of the User used on sign in.
     */
    public User(int userID, String username, String password) {
        this.User_ID = userID;
        this.Username = username;
        this.Password = password;
    }

    /**
     * This method is the getter for the unique identifier of the User.
     * @return The ID of the User.
     */
    public int getUser_ID() { return User_ID; }

    /**
     * This method adds a User to the list of all users.
     * @param newUser The User to be added to the list.
     */
    public static void addUser(User newUser) { allUsers.add(newUser); }

    /**
     * This method is a getter fo the list of all Users.
     * @return The list of all Users.
     */
    public static ObservableList<User> getAllUsers() { return allUsers; }

    /**
     * This method compares the input of Username and Password to the Users list to check if it is a valid combination or not.
     * @param user The Username entered.
     * @param pass The Password entered.
     * @return True if password and username match, otherwise returns false.
     */
    public static boolean loginCheck(String user, String pass) {
        for (int i = 0; i < getAllUsers().size(); i++) {
            if (getAllUsers().get(i).Username.equals(user) && getAllUsers().get(i).Password.equals(pass)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method overrides the default ToString method and makes it return the Username, primarily for combo box compatibility.
     * @return The Username.
     */
    @Override public String toString() { return Username; }
}