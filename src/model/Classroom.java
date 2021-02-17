package model;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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

    public void exportClassroom(String regex, String fileName) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(fileName);

        for (User u : accounts) {
            pw.println(u.getUserName() + regex + u.getPassword() + regex + u.getProfilePicturePath() + regex + u.getGender() + regex
                    + u.getCareers().replaceAll("\\n",",") + regex + u.getBirthday() + regex + u.getBrowser());
        }

        pw.close();
    }

    public void importClassroom(String regex, String fileName) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        while(line!=null){
            String[] parts = line.split(regex);
            ArrayList<String> parts4 = new ArrayList<>(Arrays.asList((parts[4].split(","))));
            addUser(parts[0],parts[1],parts[2],parts[3],parts4,parts[5],parts[6]);
            line = br.readLine();
        }

        br.close();
    }
}
