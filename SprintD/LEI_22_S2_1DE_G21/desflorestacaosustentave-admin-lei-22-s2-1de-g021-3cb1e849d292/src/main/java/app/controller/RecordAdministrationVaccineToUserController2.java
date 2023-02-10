package app.controller;

import app.domain.model.*;
import app.domain.store.SNSUserStore;
import app.domain.store.VaccinationEventStore;
import app.domain.store.VaccineScheduleStore;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RecordAdministrationVaccineToUserController2 implements Initializable {
    public ListView<String> listViewOfVaccine;
    public TextField vaccineLotNumber;
    public Text vaccineLotNumberText;
    public Label vaccineLabel;
    Company company;
    VaccineScheduleStore vaccineScheduleStore;
    List<SnsUserVaccineSchedule> list;
    SNSUserStore snsUserStore;
    List<String> listOfVaccines;
    RecoveryRoom recoveryRoom;
    WaitingRoom waitingRoom;
    VaccinationEventStore store;
    String vaccineLotNumberFinal;
    String currentVaccine;
    int vaccineDoseNumber;
    int snsUserNumber;

    public void loadVaccines(ActionEvent actionEvent) {
        vaccineLotNumberFinal = vaccineLotNumberText.getText();
        if (currentVaccine == null || vaccineLotNumberFinal == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("One of the parameters is empty");
            alert.show();
        } else {
            vaccineDoseNumber = store.compareVaccineDose(snsUserNumber, currentVaccine);
            waitingRoom.removeFromWaitingRoom(snsUserStore.getUserBySNSNumber(snsUserNumber));
            recoveryRoom.addToRecoveryRoom(snsUserStore.getUserBySNSNumber(snsUserNumber));
            store.saveDataForVaccinationEvent(vaccineLotNumberFinal, currentVaccine, vaccineDoseNumber);
            store.addVaccineEvent();
            store.timer(snsUserNumber);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data successfully saved");
            alert.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.company = App.getInstance().getCompany();
        this.vaccineScheduleStore = company.getVaccineScheduleStore();
        this.list = vaccineScheduleStore.getSnsUserVaccineShcheduleList();
        this.recoveryRoom = company.getCurrentVC().getRecoveryRoom();
        this.store = company.getVaccinationEventStore();
        this.waitingRoom = company.getCurrentVC().getWaitingRoom();
        this.snsUserStore = company.getSNSUserStore();
        this.snsUserNumber = company.getVaccinationEventStore().getSnsUserNumber();

        listOfVaccines = store.getVaccinesNames();
        listViewOfVaccine.getItems().addAll(listOfVaccines);
        listViewOfVaccine.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                currentVaccine = (String) listViewOfVaccine.getSelectionModel().getSelectedItem();
            }
        });

    }


}
