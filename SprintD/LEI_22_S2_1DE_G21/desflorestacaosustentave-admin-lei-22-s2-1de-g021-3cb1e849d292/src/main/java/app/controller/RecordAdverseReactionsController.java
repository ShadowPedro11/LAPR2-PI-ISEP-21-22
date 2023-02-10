package app.controller;

import app.domain.model.AdverseReactions;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.store.SNSUserStore;
import app.domain.store.VaccinationEventStore;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RecordAdverseReactionsController {

    public Button saveButtonAdverseReactions;
    Company company;
    SNSUserStore snsUserStore;
    VaccinationEventStore vaccinationEventStore;
    boolean confirmation = false;
    int snsUserNumber;
    String adverseReactionsData;
    public TextField textFieldAdverseReactions;
    public Label adverseReactionsLabel;

    public RecordAdverseReactionsController() {
        this(App.getInstance().getCompany());
    }

    public RecordAdverseReactionsController(Company company) {
        this.company = company;
        this.snsUserStore = company.getSNSUserStore();
        this.vaccinationEventStore = company.getVaccinationEventStore();
    }

    public boolean verifyIfUserExists(int snsUserNumber) {
        if (snsUserStore.getUserBySNSNumber(snsUserNumber) == null) {
            return false;
        }
        return true;
    }

    public boolean verifyIfUserHasVaccinationEvent(int snsUserNumber) {
        return vaccinationEventStore.checkIfUserHasEvent(snsUserNumber);
    }

    public void confirmationButton(ActionEvent actionEvent) {
        try {
            snsUserNumber = Integer.parseInt(textFieldAdverseReactions.getText());

            if (verifyIfUserExists(snsUserNumber) && verifyIfUserHasVaccinationEvent(snsUserNumber)) {
                SNSUser userData = snsUserStore.getUserBySNSNumber(snsUserNumber);
                String snsUserName = userData.getName();
                Long snsUserAge = userData.getAge();
                adverseReactionsLabel.setText("User info: " + snsUserName + ", " + snsUserAge + " years old");
                confirmation = true;
                saveButtonAdverseReactions.setVisible(true);
                
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("This user does not exist or hasn't taken the vaccine yet");
                alert.show();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("The number is invalid");
            alert.show();
        }
    }

    public void saveButton(ActionEvent actionEvent) {
        if (confirmation) {
            adverseReactionsData = textFieldAdverseReactions.getText();
            vaccinationEventStore.getVaccinationEvent(snsUserNumber).addAdverseReactions(adverseReactionsData);

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Data successfully saved");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You need to confirm the user first");
            alert.show();
        }
    }
}
