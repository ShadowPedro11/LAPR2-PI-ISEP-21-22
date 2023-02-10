package app.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AnalyzeCenterPerformanceUI extends Application implements Runnable {


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("centerPerformance.fxml"));
        root.setLocation(getClass().getClassLoader().getResource("centerPerformance.fxml"));
        Scene scene = new Scene(root.load());
        stage.setTitle("Center Performance");
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon/appIcon.jpg")));
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void run() {
            launch();
    }
}
