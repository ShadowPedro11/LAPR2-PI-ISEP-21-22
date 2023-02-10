package app.ui.console.admin;

import app.controller.App;
import app.controller.SpecifyNewVaccineAndAdministrationProcessController;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;
import app.domain.model.VaccineType;
import app.ui.console.MenuItem;
import app.ui.console.ShowTextUI;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SpecifyNewVaccineAndAdministrationProcessUI implements Runnable {

    private final SpecifyNewVaccineAndAdministrationProcessController ctrl = new SpecifyNewVaccineAndAdministrationProcessController();
    Company company = App.getInstance().getCompany();
    List<VaccineType> vaccineTypeList = company.getVaccineTypes();

    @Override
    public void run() {
        int option = -1;
        int option1 = -1;
        System.out.println("##Specify a new vaccine##");

        System.out.println("Enter the new vaccine name");
        String name = Utils.readLineFromConsole("Name :");

        System.out.println("Enter the new vaccine brand");
        String brand = Utils.readLineFromConsole("Brand :");

        System.out.println("Enter the new vaccine type");
        int vtInt = readVaccineType();
        VaccineType type = vaccineTypeList.get(vtInt);

        List<VaccineAdministration> administrations =readVaccineAdministration();

        if (ctrl.createNewVaccine(name, brand, type, administrations)) {
            System.out.println(new Vaccine(name, brand, type, administrations));
            System.out.println("Are you sure you want to specify this vaccine? \n\n" +
                    "1 for yes \n" +
                    "2 for no");
            int option2 =  Utils.readIntegerFromConsole("Answer): ");
            if(option2==1){
                ctrl.saveNewVaccineAndAdministrationProcess();
            }else if(option2==2){
                ctrl.saveNewVaccineAndAdministrationProcess();
            }


        }
    }

        public int readVaccineType() {
            List<MenuItem> options = new ArrayList<>();
            VaccineType vt;
            List<VaccineType> vaccineTypeList = company.getVaccineTypes();

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

    private List<VaccineAdministration> readVaccineAdministration() {
        boolean flag = false;
        List<VaccineAdministration> l = new LinkedList<>();
        do {
            System.out.println("Enter the administration processes:");
            int dose = Utils.readIntegerFromConsole("Dose (ml): ");
            int minAge = Utils.readIntegerFromConsole("Min age: ");
            int maxAge = Utils.readIntegerFromConsole("Max age: ");
            int dosesNumber = Utils.readIntegerFromConsole("Doses Number: ");
            int time;
            if (dosesNumber==1){
                 time = 0;
            }else {
                 time =  Utils.readIntegerFromConsole("Time interval (days): ");
            }

            l.add(new VaccineAdministration(dose,minAge,maxAge,dosesNumber,time));
            System.out.println("Do you want to add another administration process? (s/n)");
            if (!Utils.confirm("Choice: ")) {
                flag = true;
            }
        } while (!flag);
        return l;
    }
}
