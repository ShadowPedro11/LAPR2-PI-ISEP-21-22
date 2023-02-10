package app.ui.console;

import app.controller.ChoseVaccinationCenterController;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class ReceptionistUI implements Runnable {

    ChoseVaccinationCenterController choseVaccinationCenterController = new ChoseVaccinationCenterController();
    public static VaccinationCenter currentVC = null;

    @Override
    public void run() {
        ArrayList<VaccinationCenter> vaccinationCenters = choseVaccinationCenterController.choseVaccinationCenter();

        System.out.println("Choose your current vaccination center");
        for (int i = 0; i < vaccinationCenters.size(); i++) {
            System.out.println(i + 1 + ". " + choseVaccinationCenterController.getNameVaccinationCenter(vaccinationCenters.get(i)));
        }

        boolean awnsered = false;
        int chosenVC = 0;
        do {
            chosenVC = Utils.readIntegerFromConsole("Centro nº:");
            chosenVC--;
            if (chosenVC >= 0 && chosenVC < vaccinationCenters.size()) {
                currentVC = vaccinationCenters.get(chosenVC);
                awnsered = true;
            } else {
                System.out.println("Invalid vaccination center");
            }

        } while (!awnsered);
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Schedule vaccination", new ScheduleVaccinationReceptionistUI()));
        options.add(new MenuItem("User Arrival", new UserArrivalReceptionistUI()));

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nReceptionist Menu:");


            if ((option >= 0) && (option < options.size())) {

                options.get(option).run();
            }
        }

        while (option != -1);

    }
}
