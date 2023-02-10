package app.ui.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AuthUIGui extends Application implements Runnable {

    @Override
    public void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("Login.fxml"));
        root.setLocation(getClass().getClassLoader().getResource("Login.fxml"));
        Scene scene = new Scene(root.load());
        stage.setTitle("Login");
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon/appIcon.jpg")));
        stage.setScene(scene);
        stage.show();
    }
}
