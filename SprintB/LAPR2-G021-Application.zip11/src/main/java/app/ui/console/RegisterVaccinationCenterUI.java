package app.ui.console;


import app.controller.RegisterVaccinationCenterController;
import app.domain.model.CenterCoordinator;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;
import app.ui.console.utils.Utils;

import java.util.List;

public class RegisterVaccinationCenterUI implements Runnable {

    /**
     * New instance of RegisterVaccinationCenterController
     */
    private final RegisterVaccinationCenterController ctrl = new RegisterVaccinationCenterController();


    /**
     * Runs the app to interact with the user and register a new Vaccination Center
     */
    public void run() {
        List<VaccinationCenter>VaccinationCenterList = Company.getVaccinationCenterList();
        System.out.println("#VaccinationCenterList#");
        System.out.println(VaccinationCenterList);
        System.out.println();
        System.out.println("###Registering a new vacination center##");
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
        System.out.println("Enter CenterCoordinatorData: ");
        String ccName = Utils.readLineFromConsole("Name :");
        String ccAddress = Utils.readLineFromConsole("Address :");
        int ccPhoneNumber = Utils.readIntegerFromConsole("PhoneNumber :");
        String ccEmail = Utils.readLineFromConsole("Email :");
        int ccCitizenCardNumber = Utils.readIntegerFromConsole("CitizenCardNumber :");
        CenterCoordinator cc = new CenterCoordinator(ccName,ccAddress,ccPhoneNumber,ccEmail,ccCitizenCardNumber);
        //if(cc.)
        if (ctrl.createVaccinationCenter(name,address,phoneNumber,emailAddress,faxNumber,webSiteAddress,openHour,closeHour,slotDuration,maxVaccinePerSlot,cc)) {
            System.out.println("The data is correct? ");
            System.out.println(new VaccinationCenter(name,address,phoneNumber,emailAddress,faxNumber,webSiteAddress,openHour,closeHour,slotDuration,maxVaccinePerSlot,cc));
            boolean resposta = true;
            System.out.println("Yes 1.");
            System.out.println("No 2.");
            while (resposta) {
                int answer = Utils.readIntegerFromConsole("Answer :");
                if (answer==1){
                    resposta=false;
                    ctrl.saveVaccinationCenter();
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
    }
