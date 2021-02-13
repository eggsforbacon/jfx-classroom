package model;
import java.util.ArrayList;
import java.util.List;

public class Classroom {

    private final List<User> accounts = new ArrayList<>();

    public void addUser(String userName, String password, String profilePicturePath, String gender, ArrayList<String> careers, String birthday, String browser) {
        User newUser = new User(userName,password,profilePicturePath,gender,careers,birthday,browser);
        accounts.add(newUser);
    }

    /*Getters*/

    public List<User> getAccounts() {
        return accounts;
    }

    public User userExists(String username, String password) {
        User temp = null;
        for (User user : accounts) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                temp = user;
                break;
            }
        }
        return temp;
    }

    public boolean userExists(String username) {
        for (User user: accounts) {
            if (user.getUserName().equals(username)) return true;
        }
        return false;
    }
}
