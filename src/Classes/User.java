package Classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class User {
    private int User_ID;
    private String Username;
    private String Password;
    private static final ObservableList<User> allUsers = FXCollections.observableArrayList();

    public User(int userID, String username, String password) {
        this.User_ID = userID;
        this.Username = username;
        this.Password = password;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public String getPassword() {
        return Password;
    }

    public String getUsername() {
        return Username;
    }

    public static void addUser(User newUser) {
        allUsers.add(newUser);
    }

    public static ObservableList<User> getAllUsers() {
        return allUsers;
    }

    public static boolean loginCheck(String user, String pass) {
        for (int i = 0; i < getAllUsers().size(); i++) {
            if (getAllUsers().get(i).Username.equals(user) && getAllUsers().get(i).Password.equals(pass)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return Username;
    }
}
