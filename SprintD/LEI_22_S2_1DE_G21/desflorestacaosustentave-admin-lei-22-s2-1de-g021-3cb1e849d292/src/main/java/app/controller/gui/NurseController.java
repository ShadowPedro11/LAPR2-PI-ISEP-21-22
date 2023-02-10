package app.controller.gui;

import app.controller.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class NurseController {
    private Parent root;
    private Scene scene;
    private Stage stage;

    public void Vaccination(ActionEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("recordAdministrationToUser.fxml"));
            root.setLocation(getClass().getClassLoader().getResource("recordAdministrationToUser.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = new Stage();
            stage.setTitle("Recording Administration Vaccine to User");
            stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon/appIcon.jpg")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Adverse(ActionEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("adverseReactions.fxml"));
            root.setLocation(getClass().getClassLoader().getResource("adverseReactions.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = new Stage();
            stage.setTitle("Recording Adverse Reaction to User");
            stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon/appIcon.jpg")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void back(ActionEvent event) {
        App.getInstance().doLogout();
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
