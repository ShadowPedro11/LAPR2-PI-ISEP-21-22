package app.ui.console;

import app.controller.App;
import app.controller.ScheduleVaccinationReceptionistController;
import app.domain.model.SnsUserVaccineSchedule;
import app.domain.model.VaccinationCenter.MassVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineType;
import app.ui.console.utils.Utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScheduleVaccinationReceptionistUI implements Runnable {

    ScheduleVaccinationReceptionistController ctrl = new ScheduleVaccinationReceptionistController();
    VaccinationCenter currentVC = ReceptionistUI.currentVC;
    List<VaccineType> vaccineTypeList = ctrl.getVaccineTypes();
    VaccineType vaccineType;


    @Override
    public void run() {
        System.out.println("##Registering a new vaccination schedule##");
        int snsNumber = Utils.readIntegerFromConsole("SNS number of the user you want to schedule a vaccination appointment: ");

        if (ctrl.validateSNSUser(snsNumber)) {
            if (vaccineTypeList.isEmpty()) {
                System.out.println("There are no vaccine types to be administered");
            } else {

                    if (currentVC instanceof MassVaccinationCenter) {
                        vaccineType = ((MassVaccinationCenter) currentVC).getVt();
                    } else {
                        System.out.println("Chose the vaccine type to be administered");
                        vaccineType = vaccineTypeList.get(readVaccineType());
                    }

                    String day = readDay(currentVC, vaccineType);
                    String hour = null;
                    try {
                        hour = ctrl.readHour(snsNumber,currentVC, vaccineType, day);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    if (ctrl.createVaccinationSchedule(snsNumber, currentVC, vaccineType, day, hour)) {
                        System.out.println("The data is correct? ");
                        System.out.println(new SnsUserVaccineSchedule(snsNumber, currentVC, vaccineType, day, hour));
                        boolean resposta = true;
                        System.out.println("Yes 1.");
                        System.out.println("No 2.");
                        while (resposta) {
                            int answer = Utils.readIntegerFromConsole("Answer :");
                            if (answer == 1) {
                                resposta = false;
                                ctrl.saveVaccinationSchedule();
                                System.out.println("Vaccination Schedule registered successfully!");
                                System.out.println("You want to receive a message whit the information about the schedule appointment?");
                                boolean resposta2 = true;
                                System.out.println("Yes 1.");
                                System.out.println("No 2.");
                                while (resposta2) {
                                    int answer1 = Utils.readIntegerFromConsole("Answer :");
                                    if (answer1 == 1) {
                                        resposta2 = false;
                                        ctrl.sendMessage(snsNumber, currentVC, vaccineType, day, hour);
                                    } else if (answer1 == 2) {
                                        resposta2 = false;
                                    }
                                }
                            } else if (answer == 2) {
                                resposta = false;
                                System.out.println("Vaccination Schedule registration failed!");
                            }
                        }

                    }
            }
        } else {
            System.out.println("SNS number is not valid");
        }


    }

    public String readDay(VaccinationCenter vc, VaccineType vt) {
        String day = "";
        boolean valid = false;
        do {
            try {
                day = Utils.readLineFromConsole("Day pretended (dd/MM/yyyy) :");
                ctrl.chekDayDateRules(day);
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
        return day;
    }

    public int readVaccineType() {
        List<MenuItem> options = new ArrayList<>();
        VaccineType vt;
        List<VaccineType> vaccineTypeList = App.getInstance().getCompany().getVaccineTypes();

        for (VaccineType c : vaccineTypeList) {
            options.add(new MenuItem(c.toString(), new ShowTextUI("You have chosen" + c.toString())));
        }
        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nVaccine Type List:");

            if ((option >= 0) && (option < options.size())) {
                return option;
            }
        }
        while (option != -1);
        return option;
    }

}
