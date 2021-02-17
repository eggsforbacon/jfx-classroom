package ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.*;
import java.io.File;
import java.util.ArrayList;

public class ClassroomGUI {

    private final Classroom apoII;
    private final ObservableList<String> browserList = FXCollections
            .observableArrayList("Chrome", "Safari", "Mozilla", "Opera", "Opera GX", "Brave", "New Edge", "Edge", "Tor", "Other");

    /*Create account fields*/
    @FXML
    private TextField newUsernameField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private TextField pathField;

    @FXML
    private ToggleGroup tgGender;

    @FXML
    private RadioButton maleRB;

    @FXML
    private RadioButton femaleRB;

    @FXML
    private RadioButton otherrnsRB;

    @FXML
    private CheckBox s_eChbx;

    @FXML
    private CheckBox t_eChbx;

    @FXML
    private CheckBox i_eChbx;

    @FXML
    private DatePicker bDayDP;

    @FXML
    private ChoiceBox<String> browserCbx;
    /**/
    /*Classroom list fields*/
    @FXML
    private Label loggedUserLB;

    @FXML
    private ImageView loggedUserIMV;

    @FXML
    private TableView<User> accountsTBV;

    @FXML
    private TableColumn<User, String> usrColumn;

    @FXML
    private TableColumn<User, String> gndrColumn;

    @FXML
    private TableColumn<User, String> crrColumn;

    @FXML
    private TableColumn<User, String> bdayColumn;

    @FXML
    private TableColumn<User, String> brwsColumn;
    /**/
    /*Login fields*/
    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInBTN;

    @FXML
    private Button signUpBTN;
    /**/
    /*Panes*/
    @FXML
    private BorderPane alertError;

    @FXML
    private BorderPane alertCreated;

    @FXML
    private BorderPane alertIncomplete;

    @FXML
    private BorderPane createaccPane;

    @FXML
    private BorderPane acclistPane;

    @FXML
    private BorderPane mainPane;
    /**/
    /*Misc*/
    @FXML
    private Label incompleteLBL;

    public ClassroomGUI(Classroom classroom) {
        apoII = classroom;
    }

    @FXML
    void signInPressed(MouseEvent event) {
        if ((usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) || apoII.userExists(usernameField.getText(), passwordField.getText()) == null) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login-error.fxml"));
                fxmlLoader.setController(this);
                Parent root3 = fxmlLoader.load();
                Stage loginError = new Stage();
                loginError.initModality(Modality.APPLICATION_MODAL);
                Image icon = new Image("ui/resources/icon.png");
                loginError.getIcons().add(icon);
                loginError.setTitle("Login Error");
                loginError.setScene(new Scene(root3));
                loginError.show();
            } catch (Exception e) {
                System.out.println("Can't load window at the moment. The alert can´t be loaded");
            }
        } else if (apoII.userExists(usernameField.getText(), passwordField.getText()) != null) {
            try {
                Stage stage = (Stage) mainPane.getScene().getWindow();
                stage.close();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
                fxmlLoader.setController(this);
                Parent root4 = fxmlLoader.load();
                Stage accountList = new Stage();
                Image icon = new Image("ui/resources/icon.png");
                accountList.getIcons().add(icon);
                accountList.setTitle("Classroom: User account list");
                accountList.setScene(new Scene(root4));
                initAccountList(apoII.userExists(usernameField.getText(), passwordField.getText()));
                accountList.show();
            } catch (Exception e) {
                System.out.println("Can't load window at the moment. Account list can't be loaded");
            }
        }
    }

    @FXML
    private void initAccountList(User loggedUser) {
        loggedUserLB.setText(loggedUser.getUserName());
        File file = new File(loggedUser.getProfilePicturePath());
        Image image = new Image(file.toURI().toString());
        loggedUserIMV.setImage(image);

        usrColumn.setCellValueFactory(new PropertyValueFactory<User, String>("userName"));
        usrColumn.setStyle( "-fx-alignment: CENTER;");
        gndrColumn.setCellValueFactory(new PropertyValueFactory<User, String>("gender"));
        gndrColumn.setStyle( "-fx-alignment: CENTER;");
        crrColumn.setCellValueFactory(new PropertyValueFactory<User, String>("careers"));
        crrColumn.setStyle( "-fx-alignment: CENTER;");
        bdayColumn.setCellValueFactory(new PropertyValueFactory<User, String>("birthday"));
        bdayColumn.setStyle( "-fx-alignment: CENTER;");
        brwsColumn.setCellValueFactory(new PropertyValueFactory<User, String>("browser"));
        brwsColumn.setStyle( "-fx-alignment: CENTER;");

        ObservableList<User> accountsList = FXCollections.observableArrayList(apoII.getAccounts());
        accountsTBV.setItems(accountsList);
    }

    @FXML
    void logOutClicked(ActionEvent event) {
        Stage stage = (Stage) acclistPane.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
            fxmlLoader.setController(this);
            Parent root1 = fxmlLoader.load();
            Stage mainPane = new Stage();
            Image icon = new Image("ui/resources/icon.png");
            mainPane.getIcons().add(icon);
            mainPane.setTitle("Classroom: login menu");
            mainPane.setScene(new Scene(root1));
            mainPane.show();
        } catch (Exception e) {
            System.out.println("Can't load window at the moment.");
        }
    }

    @FXML
    void signUpPressed(ActionEvent event) {
        Stage stage = (Stage) mainPane.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("create-account.fxml"));
            fxmlLoader.setController(this);
            Parent root2 = fxmlLoader.load();
            browserCbx.setValue("Opera");
            browserCbx.setItems(browserList);
            Stage createAccountWD = new Stage();
            Image icon = new Image("ui/resources/icon.png");
            createAccountWD.getIcons().add(icon);
            createAccountWD.setTitle("Classroom: create a new account");
            createAccountWD.setScene(new Scene(root2));
            pathField.setEditable(false);
            createAccountWD.show();
        } catch (Exception e) {
            System.out.println("Can't load window at the moment.");
        }
    }

    @FXML
    void browseClicked(ActionEvent event) {
        FileChooser imageBrowser = new FileChooser();
        File pp =  imageBrowser.showOpenDialog(null);
        imageBrowser.setTitle("Choose an Image file");

        if (pp != null) {
            pathField.setText(pp.getAbsolutePath());
        }

    }

    @FXML
    void createAccountClicked(ActionEvent event) {
        String newUserName = newUsernameField.getText();
        String newPassword = newPasswordField.getText();
        String newProfilePicPath = (pathField.getText().isEmpty()) ? "src/ui/resources/userpicdef.png": pathField.getText();
        RadioButton selectedRadioButton = (RadioButton) tgGender.getSelectedToggle();
        String gender = selectedRadioButton.getText();
        ArrayList<String> newCareers = new ArrayList<>();
        if (s_eChbx.isSelected()) newCareers.add(s_eChbx.getText());
        if (t_eChbx.isSelected()) newCareers.add(t_eChbx.getText());
        if (i_eChbx.isSelected()) newCareers.add(i_eChbx.getText());
        String newBDay = bDayDP.getValue().toString();
        String newBrowser = browserCbx.getValue();
        if (newUserName.isEmpty() || newPassword.isEmpty() || gender.isEmpty() || newCareers.isEmpty() || newBDay.isEmpty() || newBrowser.isEmpty()) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("incomplete-error.fxml"));
                fxmlLoader.setController(this);
                Parent root3 = fxmlLoader.load();
                Stage alertIncomplete = new Stage();
                alertIncomplete.initModality(Modality.APPLICATION_MODAL);
                Image icon = new Image("ui/resources/icon.png");
                alertIncomplete.getIcons().add(icon);
                alertIncomplete.setTitle("Validation Error");
                alertIncomplete.setScene(new Scene(root3));
                alertIncomplete.show();
            } catch (Exception e) {
                System.out.println("Can't load window at the moment.");
            }
        } else if (apoII.userExists(newUserName)) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("incomplete-error.fxml"));
                fxmlLoader.setController(this);
                Parent root3 = fxmlLoader.load();
                Stage alertIncomplete = new Stage();
                alertIncomplete.initModality(Modality.APPLICATION_MODAL);
                Image icon = new Image("ui/resources/icon.png");
                alertIncomplete.getIcons().add(icon);
                alertIncomplete.setTitle("Validation Error");
                alertIncomplete.setScene(new Scene(root3));
                incompleteLBL.setText("Can't create user. Please try another username (Case sensitive)");
                alertIncomplete.show();
            } catch (Exception e) {
                System.out.println("Can't load window at the moment.");
            }
        } else {
            apoII.addUser(newUserName,newPassword,newProfilePicPath,gender,newCareers,newBDay,newBrowser);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("user-created.fxml"));
                fxmlLoader.setController(this);
                Parent root3 = fxmlLoader.load();
                Stage alertCreated = new Stage();
                alertCreated.initModality(Modality.APPLICATION_MODAL);
                Image icon = new Image("ui/resources/icon.png");
                alertCreated.getIcons().add(icon);
                alertCreated.setTitle("Account created");
                alertCreated.setScene(new Scene(root3));
                alertCreated.show();
            } catch (Exception e) {
                System.out.println("Can't load window at the moment.");
            }
        }
    }

    @FXML
    void signInInsteadClicked(ActionEvent event) {
        Stage stage = (Stage) createaccPane.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
            fxmlLoader.setController(this);
            Parent root1 = fxmlLoader.load();
            Stage mainPane = new Stage();
            Image icon = new Image("ui/resources/icon.png");
            mainPane.getIcons().add(icon);
            mainPane.setTitle("Classroom: login menu");
            mainPane.setScene(new Scene(root1));
            mainPane.show();
        } catch (Exception e) {
            System.out.println("Can't load window at the moment.");
        }
    }

    @FXML
    void dismissIncomplete(ActionEvent event) {
        Stage stage = (Stage) alertIncomplete.getScene().getWindow();
        stage.close();
    }

    @FXML
    void dismissNotFound(ActionEvent event) {
        Stage stage = (Stage) alertError.getScene().getWindow();
        stage.close();
    }

    @FXML
    void dismissCreated(ActionEvent event) {
        Stage stage = (Stage) createaccPane.getScene().getWindow();
        stage.close();
        stage = (Stage) alertCreated.getScene().getWindow();
        stage.close();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
            fxmlLoader.setController(this);
            Parent root1 = fxmlLoader.load();
            Stage mainPane = new Stage();
            Image icon = new Image("ui/resources/icon.png");
            mainPane.getIcons().add(icon);
            mainPane.setTitle("Classroom: login menu");
            mainPane.setScene(new Scene(root1));
            mainPane.show();
        } catch (Exception e) {
            System.out.println("Can't load window at the moment.");
        }
    }

    @FXML
    void exportClicked(ActionEvent event) {

    }

    @FXML
    void importClicked(ActionEvent event) {

    }
}
