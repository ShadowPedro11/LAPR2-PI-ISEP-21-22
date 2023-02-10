package app.controller;


import app.domain.model.Company;
import app.domain.shared.Constants;
import app.domain.store.DataLegacySystemStore;
import app.domain.store.SNSUserStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.DateTimeException;
import java.util.Properties;


public class ImportDataLegacySystemController {
    private Stage stage;
    private Scene scene;
    @FXML
    private Label label1;
    @FXML
    private TextField textFieldCSVFile;
    @FXML
    private TextArea textArea1;
    @FXML
    private Button yesButton;
    @FXML
    private Button noButton;
    @FXML
    private RadioButton arrivalTimeRadioButton;
    @FXML
    private RadioButton leavingTimeRadioButton;
    @FXML
    private RadioButton ascendantRadioButton;
    @FXML
    private RadioButton descendantRadioButton;

    private String pathToCSV;
    private Company company = App.getInstance().getCompany();
    private DataLegacySystemStore dataLegacySystemStore = company.getDataLegacySystemStore();
    private String sortingTypeArrLea = null;
    private String sortingTypeAscDes = null;

    static String nameOfAlgorithm;


    public void enter(ActionEvent event) {
        dataLegacySystemStore.setCompany(company);
        try {
            if (validatePathToCSV(textFieldCSVFile.getText())) {
                label1.setVisible(true);
                yesButton.setVisible(true);
                noButton.setVisible(true);
                label1.setText("File exists. Do you want to load the data?");
            } else {
                label1.setVisible(false);
                yesButton.setVisible(false);
                noButton.setVisible(false);
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("File does not exist. Please enter a valid path.");
                alert.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean validatePathToCSV(String pathToCSV) {
        return dataLegacySystemStore.validatePathToCSV(pathToCSV);
    }

    public void actionNoButton(ActionEvent event) {
        label1.setVisible(false);
        yesButton.setVisible(false);
        noButton.setVisible(false);
        textFieldCSVFile.clear();
    }

    @FXML
    private void switchToScene2(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ImportDataLegacySystem2.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private void back(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("ImportDataLegacySystem.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getSortingTypeArrivalLeaving(ActionEvent event) {
        if (arrivalTimeRadioButton.isSelected()) {
            sortingTypeArrLea = "arrival";
        } else if (leavingTimeRadioButton.isSelected()) {
            sortingTypeArrLea = "leaving";
        }
    }

    public void getSortingTypeAscendDescend(ActionEvent event) {
        if (ascendantRadioButton.isSelected()) {
            sortingTypeAscDes = "ascendant";
        } else if (descendantRadioButton.isSelected()) {
            sortingTypeAscDes = "descendant";
        }
    }

    private static Properties getProperties() {
        Properties props = new Properties();
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {

        }
        return props;
    }

    public void printList() {
        String nameHeader = "Name";
        String snsUserNumberHeader = "SNS User Number";
        String vaccineNameHeader = "Vaccine Name";
        String vaccineTypeShortDescriptionHeader = "Vaccine Type Short Description";
        String doseHeader = "Dose";
        String lotNumberHeader = "Lot Number";
        String scheduledDateTimeHeader = "Scheduled Date and Time";
        String arrivalDateTimeHeader = "Arrival Date and Time";
        String nurseAdministrationDateTimeHeader = "Nurse Administration Date and Time";
        String leavingDateTimeHeader = "Leaving Date and Time";
        String header = String.format( "%-50s | %-10s | %-20s | %-20s | %-10s | %-10s | %-17s | %-17s | %-17s | %-17s |\n",
                nameHeader, snsUserNumberHeader, vaccineNameHeader, vaccineTypeShortDescriptionHeader, doseHeader, lotNumberHeader,
                scheduledDateTimeHeader, arrivalDateTimeHeader, nurseAdministrationDateTimeHeader, leavingDateTimeHeader);

        textArea1.setText(header + dataLegacySystemStore.everythingToString());

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(dataLegacySystemStore.getNumberUserSuccessfullyRegistered() + "/" + dataLegacySystemStore.getTotalNumberOfUsersData() + " successfully loaded");
        alert.show();

        dataLegacySystemStore.addToWaitingAndRecoveryRoomRegisters();
    }

    public void sortData(ActionEvent event) {
        try {
            dataLegacySystemStore.read();
        }catch (DateTimeException dte){
            throw new IllegalArgumentException("Invalid date or time");
        }

        Properties props = getProperties();
        nameOfAlgorithm = props.getProperty("NameOfAlgorithm");

        if (sortingTypeArrLea == null && sortingTypeAscDes == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a sorting and a presenting type.");
            alert.show();
        } else if (sortingTypeArrLea == null && sortingTypeAscDes != null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Please select a sorting type.");
            alert.show();
        } else if (sortingTypeArrLea != null && sortingTypeAscDes == null) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setContentText("Please select a presenting type.");
            alert1.show();
        } else if (sortingTypeArrLea != null && sortingTypeAscDes != null) {
            if (nameOfAlgorithm == null){
                nameOfAlgorithm = "bubbleSort";
            }
            if (nameOfAlgorithm.equals("bubbleSort")) {

                if (sortingTypeArrLea.equals("arrival") && sortingTypeAscDes.equals("ascendant")) {
                    dataLegacySystemStore.bubbleSortingAlgorithmArrivalDateTimeAscendant();

                } else if (sortingTypeArrLea.equals("arrival") && sortingTypeAscDes.equals("descendant")) {
                    dataLegacySystemStore.bubbleSortingAlgorithmArrivalDateTimeDescendant();

                } else if (sortingTypeArrLea.equals("leaving") && sortingTypeAscDes.equals("ascendant")) {
                    dataLegacySystemStore.bubbleSortingAlgorithmLeavingDateTimeAscendant();

                } else if (sortingTypeArrLea.equals("leaving") && sortingTypeAscDes.equals("descendant")) {
                    dataLegacySystemStore.bubbleSortingAlgorithmLeavingDateTimeDescendant();

                }

            } else if (nameOfAlgorithm.equals("selectionSort")) {
                if (sortingTypeArrLea.equals("arrival") && sortingTypeAscDes.equals("ascendant")) {
                    dataLegacySystemStore.selectionSortingAlgorithmArrivalDateTimeAscendant();

                } else if (sortingTypeArrLea.equals("arrival") && sortingTypeAscDes.equals("descendant")) {
                    dataLegacySystemStore.selectionSortingAlgorithmArrivalDateTimeDescendant();

                } else if (sortingTypeArrLea.equals("leaving") && sortingTypeAscDes.equals("ascendant")) {
                    dataLegacySystemStore.selectionSortingAlgorithmLeavingDateTimeAscendant();


                } else if (sortingTypeArrLea.equals("leaving") && sortingTypeAscDes.equals("descendant")) {
                    dataLegacySystemStore.selectionSortingAlgorithmLeavingDateTimeDescendant();

                }
            }

            printList();

        }
    }
}
