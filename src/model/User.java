package model;
import java.util.ArrayList;

public class User {
    private final String userName;
    private final String password;
    private final String profilePicture;
    private final String gender;
    private final ArrayList<String> careers;
    private final String birthday;
    private final String browser;

    public User(String userName, String password, String profilePicture, String gender, ArrayList<String> careers, String birthday, String browser) {
        this.userName = userName;
        this.password = password;
        this.profilePicture = profilePicture;
        this.gender = gender;
        this.careers = careers;
        this.birthday = birthday;
        this.browser = browser;
    }

    public void addToCareers(String career) {
        if (careers.size() <= 3) careers.add(career);
    }

    /*Getters*/

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public String getGender() {
        return gender;
    }

    public String getCareers() {
        StringBuilder sb = new StringBuilder();
        for (String s: careers) sb.append(s).append("\n");
        return sb.toString();
    }

    public String getBirthday() {
        return birthday;
    }

    public String getBrowser() {
        return browser;
    }
}
