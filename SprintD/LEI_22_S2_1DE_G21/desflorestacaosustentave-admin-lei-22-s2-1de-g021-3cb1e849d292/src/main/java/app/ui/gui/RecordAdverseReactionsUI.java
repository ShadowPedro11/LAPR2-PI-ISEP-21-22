package app.ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class RecordAdverseReactionsUI extends Application implements Runnable {

    @Override
    public void run() {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader root = new FXMLLoader(getClass().getResource("adverseReactions.fxml"));
        root.setLocation(getClass().getClassLoader().getResource("adverseReactions.fxml"));
        Scene scene = new Scene(root.load());
        stage.setTitle("Recording Adverse Reactions To a User");
        stage.getIcons().add(new Image(getClass().getClassLoader().getResourceAsStream("icon/appIcon.jpg")));
        stage.setScene(scene);
        stage.show();
    }
}
