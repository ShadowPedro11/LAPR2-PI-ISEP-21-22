package app.ui.gui;
import app.controller.App;
import app.domain.model.Company;
import app.domain.model.VaccinationEvent;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.List;


public class CheckAndExportUI extends Application implements Runnable  {
    Company company = App.getInstance().getCompany();

    public void start(Stage stage) throws IOException  {
        FXMLLoader fxmlLoader = new FXMLLoader(CheckAndExportUI.class.getResource("CheckAndExport.fxml"));
        fxmlLoader.setLocation(getClass().getClassLoader().getResource("CheckAndExport.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void run() {
       launch();
    }




}

