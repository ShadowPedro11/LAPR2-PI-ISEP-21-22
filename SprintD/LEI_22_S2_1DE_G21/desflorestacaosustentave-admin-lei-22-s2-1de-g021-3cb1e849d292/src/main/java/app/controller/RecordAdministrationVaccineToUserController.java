package app.controller;

import app.domain.model.*;
import app.domain.store.SNSUserStore;
import app.domain.store.VaccinationEventStore;
import app.domain.store.VaccineScheduleStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RecordAdministrationVaccineToUserController {
    public TextField textFieldForNumber;
    public Label firstScreenLabel;
    private Stage stage;
    private Scene scene;
    Company company;
    SNSUserStore snsUserStore;
    VaccineScheduleStore vaccineScheduleStore;
    List<SnsUserVaccineSchedule> list;
    List<Vaccine> listOfVaccines;
    WaitingRoom waitingRoom;
    VaccineType vaccineType;
    int snsUserNumber;
    VaccinationEventStore store;
    public RecordAdministrationVaccineToUserController() {
        this(App.getInstance().getCompany());
    }

    public RecordAdministrationVaccineToUserController(Company company) {
        this.company = company;
        this.snsUserStore = company.getSNSUserStore();
        this.vaccineScheduleStore = company.getVaccineScheduleStore();
        this.list = vaccineScheduleStore.getSnsUserVaccineShcheduleList();
        this.listOfVaccines = new ArrayList<>();
        this.waitingRoom = company.getCurrentVC().getWaitingRoom();
        this.store = company.getVaccinationEventStore();
    }

    public boolean verifyIfUserExists(int snsUserNumber) {
        if (snsUserStore.getUserBySNSNumber(snsUserNumber) == null) {
            return false;
        }
        return true;
    }

    public boolean verifyIfUserIsInWaitingRoom(int snsUserNumber) {
        if (waitingRoom.isInWaitingRoom(snsUserNumber)) {
            return true;
        }
        return false;
    }

    public VaccineType checkTheVaccineType(int snsUserNumber) {
        VaccineType vaccineType = null;
        for (SnsUserVaccineSchedule c : list) {
            if (c.toDto().getSnsUserNumber() == snsUserNumber) {
                vaccineType = c.toDto().getVaccineType();
                store.saveSnsUserVaccineSchedule(c);
            }
        }
        return vaccineType;
    }

    public List<Vaccine> giveAllVaccines(VaccineType vaccineType) {
        List<Vaccine> vaccines = new ArrayList<>();
        List<Vaccine> vaccineTest = company.getVaccineList();
        for (Vaccine vaccine : vaccineTest) {
            if (vaccine.getType().getCode().equals(vaccineType.getCode())) {
                vaccines.add(vaccine);
            }
        }
        return vaccines;
    }


    public void confirmationButton(ActionEvent actionEvent) {
        try{
            snsUserNumber = Integer.parseInt(textFieldForNumber.getText());

            if (verifyIfUserExists(snsUserNumber) && verifyIfUserIsInWaitingRoom(snsUserNumber)){


                vaccineType = checkTheVaccineType(snsUserNumber);
                listOfVaccines = giveAllVaccines(vaccineType);
                store.setListOfVaccines(listOfVaccines);

                store.saveDataForVaccinationEvent(snsUserNumber, vaccineType);
                switchToScene2(actionEvent);
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("This user does not exist or is not in this vaccination center");
                alert.show();
            }

        } catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The number is invalid");
            alert.show();
        }

    }

    private void switchToScene2(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("recordVaccine.fxml")));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void seeUserData(ActionEvent actionEvent) {
        try{
            snsUserNumber = Integer.parseInt(textFieldForNumber.getText());

            if (verifyIfUserExists(snsUserNumber) && verifyIfUserIsInWaitingRoom(snsUserNumber)){
                SNSUser userData = snsUserStore.getUserBySNSNumber(snsUserNumber);
                String snsUserName = userData.getName();
                Long snsUserAge = userData.getAge();
                firstScreenLabel.setText("User info: " + snsUserName + ", " + snsUserAge + " years old");
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("This user does not exist or is not in this vaccination center");
                alert.show();
            }

        } catch (NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The number is invalid");
            alert.show();
        }
    }
}
