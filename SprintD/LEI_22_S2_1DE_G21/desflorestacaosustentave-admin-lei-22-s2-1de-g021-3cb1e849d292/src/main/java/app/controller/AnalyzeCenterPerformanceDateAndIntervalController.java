package app.controller;

import app.domain.exceptions.InvalidDateException;
import app.domain.exceptions.InvalidTimeException;
import app.domain.exceptions.NumberOutOfLimits;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.store.AnalyzeCenterPerformanceStore;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;


public class AnalyzeCenterPerformanceDateAndIntervalController {

    public Label vaccinationCenterName;
    public TextField intervalDuration;
    public DatePicker datePicker;
    private Stage stage;
    private Scene scene;
    private Parent root;

    private Company company;
    private AnalyzeCenterPerformanceStore store;
    private VaccinationCenter vaccinationCenter;

    public AnalyzeCenterPerformanceDateAndIntervalController() {
        this(App.getInstance().getCompany());
    }

    public AnalyzeCenterPerformanceDateAndIntervalController(Company company) {
        this.company = company;
        this.store = company.getCenterPerformanceStore();
        company.getCurrentVCOfCC();
        vaccinationCenter = company.getCurrentVC();
    }

    public void validateDate(ActionEvent event) {
        try {
            LocalDate date = getDate(event);
            String[] hourMinute = vaccinationCenter.getCloseHour().split(":");

            if (date.isEqual(LocalDate.now())) {
                if (LocalTime.now().isBefore(LocalTime.of(Integer.parseInt(hourMinute[0]), Integer.parseInt(hourMinute[1]))))
                    throw new InvalidTimeException("The vaccination center hasn't finish working for today");
            } else if (date.isAfter(LocalDate.now())) {
                throw new InvalidDateException("The date you introduced is invalid");
            } else if (!store.checkDayForAnalysis(date, this.vaccinationCenter)) {
                throw new InvalidDateException("The vaccination center did not receive anyone that day");
            }

            setMinutesPerInterval(event);

        } catch (DateTimeParseException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("That is an invalid date");
            alert.show();
        } catch (InvalidDateException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("That is an invalid date to make the analysis\nThe vaccination center did not received anyone that day or is a future date");
            alert.show();
        } catch (InvalidTimeException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("The vaccination center haven't finish working for today");
            alert.show();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Something went wrong occurred try again");
            alert.show();
        }
    }

    private void setMinutesPerInterval(ActionEvent event) {
        try {
            int interval = Integer.parseInt(intervalDuration.getText());
            if (interval <= 0 || interval > 720)
                throw new NumberOutOfLimits("Number out of limits, should be between 1 and 720");
            store.setMinutesPerInterval(interval);
            switchToScene2(event);
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("You should only type numbers for the interval duration");
            alert.show();
        } catch (NumberOutOfLimits e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setContentText("Number out of limits, should be between 1 and 720");
            alert.show();
        }
    }

    private void switchToScene2(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("centerPerformance2.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public LocalDate getDate(ActionEvent event) {

        return datePicker.getValue();
    }

}
