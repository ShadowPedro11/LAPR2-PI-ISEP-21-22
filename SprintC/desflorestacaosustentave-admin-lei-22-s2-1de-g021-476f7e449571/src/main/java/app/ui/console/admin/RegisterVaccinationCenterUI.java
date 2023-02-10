package app.ui.console.admin;

import app.controller.App;
import app.controller.RegisterVaccinationCenterController;
import app.domain.model.VaccinationCenter.HealthCareCenter;
import app.domain.model.VaccinationCenter.MassVaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.employee.CenterCoordinator;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.ui.console.MenuItem;
import app.ui.console.ShowTextUI;
import app.ui.console.utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class RegisterVaccinationCenterUI implements Runnable {

    /**
     * New instance of RegisterVaccinationCenterController
     */
    private final RegisterVaccinationCenterController ctrl = new RegisterVaccinationCenterController();
    private Company company = App.getInstance().getCompany();


    /**
     * Runs the app to interact with the user and register a new Vaccination Center
     */
    public void run() {
        List<VaccinationCenter>VaccinationCenterList = Company.getVaccinationCenterList();
        List<CenterCoordinator>CenterCoordinatorList = Company.getCenterCoordinatorList();
        List<VaccineType> vaccineTypeList = company.getVaccineTypes();


        System.out.println("#VaccinationCenterList#");
        System.out.println(VaccinationCenterList);
        System.out.println();
        System.out.println("###Registering a new vacination center##");
        System.out.println("Which Vaccination center would like to regist");
        int option = 0;
        int optionController = 0;
        while (optionController==0){
            System.out.println("1.Mass Vaccination Center");
            System.out.println("2.Health Care Center");
            option = Utils.readIntegerFromConsole("Option :");
            if(option==1 || option==2 ){
                optionController++;
            }
        }
        System.out.print("Enter the name of the center: ");
        String name = Utils.readLineFromConsole("Name :");
        System.out.print("Enter the address of the center: ");
        String address = Utils.readLineFromConsole("Address :");
        System.out.print("Enter the phone number of the center: ");
        String phoneNumber = Utils.readLineFromConsole("Phone :");
        System.out.print("Enter the email of the center: ");
        String emailAddress = Utils.readLineFromConsole("Email :");
        System.out.println("Enter the fax of the center: ");
        String faxNumber = Utils.readLineFromConsole("Fax :");
        System.out.println("Enter the website of the center: ");
        String webSiteAddress = Utils.readLineFromConsole("Website :");
        System.out.println("Enter the open hours of the center: ");
        String openHour = Utils.readLineFromConsole("Open hours :");
        System.out.println("Enter the close hours of the center: ");
        String closeHour = Utils.readLineFromConsole("Close hours :");
        System.out.println("Enter the slot duration of the center: ");
        int slotDuration = Utils.readIntegerFromConsole("Slot duration :");
        System.out.println("Enter the vacines slot cap of the center: ");
        int maxVaccinePerSlot = Utils.readIntegerFromConsole("Vacines slot cap :");
        int Tocc = readCenterCoordinator();
        if(Tocc!=-1){
            CenterCoordinator  cc = CenterCoordinatorList.get(Tocc);
            if (option==1){
            int Tovc = readVaccineType();
            if(Tovc!=-1) {
                VaccineType vt = vaccineTypeList.get(Tovc);
                if (ctrl.createMassVaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, webSiteAddress, openHour, closeHour, slotDuration, maxVaccinePerSlot, cc, vt)) {
                    System.out.println("The data is correct? ");
                    System.out.println(new MassVaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, webSiteAddress, openHour, closeHour, slotDuration, maxVaccinePerSlot, cc, vt));
                    boolean resposta = true;
                    System.out.println("Yes 1.");
                    System.out.println("No 2.");
                    while (resposta) {
                        int answer = Utils.readIntegerFromConsole("Answer :");
                        if (answer == 1) {
                            resposta = false;
                            ctrl.saveMassVaccinationCenter();
                            System.out.println("Vacination center registered successfully!");
                        } else if (answer == 2) {
                            resposta = false;
                            System.out.println("Vacination center registration failed!");
                        }
                    }
                } else {
                    System.out.println("Vacination center registration failed!");
                }
            }else {
                System.out.println("Vacination center registration failed!");
            }
        }else if(option==2){
            String args = Utils.readLineFromConsole("Enter Respetive ARS/AGES :");
            System.out.println("Enter ARS/AGES: ");
            if (ctrl.createHealthCareVaccinationCenter(name,address,phoneNumber,emailAddress,faxNumber,webSiteAddress,openHour,closeHour,slotDuration,maxVaccinePerSlot,cc,args)) {
                System.out.println("The data is correct? ");
                System.out.println(new HealthCareCenter(name,address,phoneNumber,emailAddress,faxNumber,webSiteAddress,openHour,closeHour,slotDuration,maxVaccinePerSlot,cc,args));
                boolean resposta = true;
                System.out.println("Yes 1.");
                System.out.println("No 2.");
                while (resposta) {
                    int answer = Utils.readIntegerFromConsole("Answer :");
                    if (answer==1){
                        resposta=false;
                        ctrl.saveHealthCareVaccinationCenter();
                        System.out.println("Vacination center registered successfully!");
                    }else if(answer == 2){
                        resposta=false;
                        System.out.println("Vacination center registration failed!");
                    }
                }
            } else {
                System.out.println("Vacination center registration failed!");
            }
            }
        }else{
            System.out.println("Vacination center registration failed!");
        }
    }

    public int readCenterCoordinator() {
        List<MenuItem> options = new ArrayList<>();
        CenterCoordinator cc;
        List<CenterCoordinator> CenterCorrdinatorList = Company.getCenterCoordinatorList();

        for (CenterCoordinator c : CenterCorrdinatorList) {
            options.add(new MenuItem(c.getName(), new ShowTextUI("You have chosen" + c.getName())));
        }

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nCenter Coordinator List:");

            if ((option >= 0) && (option < options.size())) {
                return option;
            }
        }
        while (option != -1);
        return option;
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
}
