package app.controller;
import app.domain.store.CheckAndExportStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class CheckAndExportController {
    @FXML
    private Button btnExport;

    /**
     * Text area when will be presented the results of the operation
     */
    @FXML
    private TextArea txtArea;

    /**
     * Button to check the data and run the function
     */
    @FXML
    private Button btnCheck;

    /**
     * Date picker when is inputted the start date
     */
    @FXML
    private DatePicker edDate;

    /**
     * Date picker when is inputted the end date
     */
    @FXML
    private DatePicker strDate;

    FileChooser fileChooser = new FileChooser();
    CheckAndExportStore store = new CheckAndExportStore();

    /**Check the date, generates the text that is printed on text area and asks to save the file
     * @param event
     * @throws ParseException
     * @throws FileNotFoundException
     */
    @FXML
    public void getVaccinationStatistics(ActionEvent event) throws ParseException, FileNotFoundException {
        String dayInitial = strDate.getEditor().getText();
        String dayEnd = edDate.getEditor().getText();

        boolean checker;
        checker=store.checkIntervalTimeRules(dayInitial, dayEnd);
        if(checker){
            String txt = store.getText(dayInitial,dayEnd);
            txtArea.clear();
            txtArea.setText(txt);
            if(txt.compareTo("")!=0) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Save");
                alert.setHeaderText("Do you want to save this statistics.");
                alert.setResizable(false);
                alert.setContentText("Select okay or cancel.");
                Optional<ButtonType> result = alert.showAndWait();
                saveToFile(result);
            }else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("Data Error");
                errorAlert.setContentText("Non data available for this time interval");
                errorAlert.showAndWait();
            }
        }
    }

    /**If the button is selected save the file with the name and the location that user wants
     * @param result
     * @throws FileNotFoundException
     */
    public void saveToFile(Optional<ButtonType> result) throws FileNotFoundException {
        if(!result.isPresent()) {
            txtArea.clear();
        }else if(result.get() == ButtonType.OK) {
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showSaveDialog(new Stage());
            if (file != null) {
                store.saveSystem(file,txtArea.getText());
            }
        }else if(result.get() == ButtonType.CANCEL){
            txtArea.clear();
        }
    }

    @FXML
    void initialize() {
        assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'CheckAndExport.fxml'.";
        assert edDate != null : "fx:id=\"edDate\" was not injected: check your FXML file 'CheckAndExport.fxml'.";
        assert btnExport != null : "fx:id=\"btnExport\" was not injected: check your FXML file 'CheckAndExport.fxml'.";
        assert txtArea != null : "fx:id=\"txtArea\" was not injected: check your FXML file 'CheckAndExport.fxml'.";
        assert strDate != null : "fx:id=\"strDate\" was not injected: check your FXML file 'CheckAndExport.fxml'.";
    }





}

