package app.controller.gui;

import app.controller.App;
import app.ui.gui.CheckAndExportUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class CenterCoordinatorController {

    private Parent root;
    private Scene scene;
    private Stage stage;
    public void CheckAndExportBtn(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(CheckAndExportUI.class.getResource("CheckAndExport.fxml"));
            fxmlLoader.setLocation(getClass().getClassLoader().getResource("CheckAndExport.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void AnalyzeBtn(ActionEvent event) {
        try {
            FXMLLoader root = new FXMLLoader(getClass().getResource("centerPerformance.fxml"));
            root.setLocation(getClass().getClassLoader().getResource("centerPerformance.fxml"));
            Scene scene = new Scene(root.load());
            Stage stage = new Stage();
            stage.setTitle("Center Performance");
            stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon/appIcon.jpg")));
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void Legacy(ActionEvent event) {
        try {
        FXMLLoader root = new FXMLLoader(getClass().getResource("ImportDataLegacySystem.fxml"));
        root.setLocation(getClass().getClassLoader().getResource("ImportDataLegacySystem.fxml"));
        Scene scene = new Scene(root.load());
        Stage stage = new Stage();
        stage.setTitle("Import data from legacy system");
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon/appIcon.jpg")));
        stage.setScene(scene);
        stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void back(ActionEvent actionEvent) {
        App.getInstance().doLogout();
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Login.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
