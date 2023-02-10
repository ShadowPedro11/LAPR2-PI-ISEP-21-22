package app.controller;

import app.domain.store.AnalyzeCenterPerformanceStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AnalyzeCenterPerformanceCreateAnalysisController implements Initializable {
    public Label timeInterval;
    public ListView listIntervals;
    public Label totalSum;
    public Label subList;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private AnalyzeCenterPerformanceStore store;



    @FXML
    private void back(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("centerPerformance.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void confirm(ActionEvent event) {
        timeInterval.setText(store.intArrayToString(store.createAnalysis()));
        subList.setText(store.subListString());
        totalSum.setText(store.getSum());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        store = App.getInstance().getCompany().getCenterPerformanceStore();
        store.createList();
        List<String> intervals = store.getIntervalsString();
        listIntervals.getItems().addAll(intervals);
    }
}
