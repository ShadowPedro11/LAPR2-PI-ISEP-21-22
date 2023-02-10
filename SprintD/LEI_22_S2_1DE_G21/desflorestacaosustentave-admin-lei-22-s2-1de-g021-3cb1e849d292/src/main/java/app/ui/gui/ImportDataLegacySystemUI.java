package app.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ImportDataLegacySystemUI extends Application implements Runnable{

    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("ImportDataLegacySystem.fxml"));
        root.setLocation(getClass().getClassLoader().getResource("ImportDataLegacySystem.fxml"));
        Scene scene = new Scene(root.load());

        stage.setTitle("Import data from legacy system");
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon/appIcon.jpg")));
        stage.setScene(scene);
        stage.show();
    }

    public void run(){
        launch();
    }
}
