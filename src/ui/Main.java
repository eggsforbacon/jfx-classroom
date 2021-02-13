package ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.Classroom;

import java.util.ArrayList;

public class Main extends Application {

    ClassroomGUI controller;
    Classroom apoII;

    public Main() {
        apoII = new Classroom();
        ArrayList<String> rootCareers = new ArrayList<>();
        rootCareers.add("Admin");
        apoII.addUser("root","toor","src/ui/resources/userpicdef.png","n.a.",rootCareers,"n.a.","Tor");
        controller = new ClassroomGUI(apoII);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-pane.fxml"));
        fxmlLoader.setController(controller);
        Parent root = fxmlLoader.load();
        Image icon = new Image("ui/resources/icon.png");
        primaryStage.getIcons().add(icon);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Classroom: login menu");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
