package app.controller.gui;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import app.controller.App;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccinationEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;


public class GetVaccinationCenterController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private ListView<String> vaccinationcenterName;

        @FXML
        void SelectVaccinationCenter(ActionEvent event) {
            int index = vaccinationcenterName.getSelectionModel().getSelectedIndex();
            List<VaccinationCenter> centerList = App.getInstance().getCompany().returnVaccinationCenter();
            VaccinationCenter vaccinationCenter = centerList.get(index);
            App.getInstance().getCompany().setCurrentVC(vaccinationCenter);
            try {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("NurseUI.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @FXML
        void initialize() {
            assert vaccinationcenterName != null : "fx:id=\"vaccinationcenterName\" was not injected: check your FXML file 'selectVaccinationCenter.fxml'.";

        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<String> arrayList = new ArrayList<>();
        List<VaccinationCenter> centerList = App.getInstance().getCompany().returnVaccinationCenter();
        for (VaccinationCenter c: centerList){
            arrayList.add(c.getName());
        }
        vaccinationcenterName.getItems().addAll(arrayList);

    }
}
