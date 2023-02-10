package app.ui.console;

import app.controller.App;
import app.controller.SnsUserVaccineScheduleController;
import app.domain.model.Company;
import app.domain.model.SnsUserVaccineSchedule;
import app.domain.model.VaccinationCenter.MassVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineType;
import app.ui.console.utils.Utils;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SnsUserVaccineScheduleUI implements Runnable {

    /**
     *New instance of SnsUserVaccineScheduleController
     */
    private final SnsUserVaccineScheduleController ctrl = new SnsUserVaccineScheduleController(App.getInstance().getCompany());
    /**
     *List of all vaccination Centers registered
     */
    List<VaccinationCenter> vaccinationCenterList = ctrl.getVaccinationCenterList();
    /**
     *List of all vaccine type registered
     */
    List<VaccineType> vaccineTypeList = ctrl.getVaccineTypes();
    /**
     *Instance of actual Company
     */
    Company company = App.getInstance().getCompany();

    @Override
    public void run() {
        System.out.println("###Registering a new vaccination Schedule##");
        int snsUserNumber = readSnsUserNumber();
        System.out.println(snsUserNumber);
        if (ctrl.checkSnsUserNumber(snsUserNumber)) {
            if (vaccinationCenterList.isEmpty() || vaccineTypeList.isEmpty()) {
                if (vaccinationCenterList.isEmpty() && vaccineTypeList.isEmpty()){
                    System.out.println("No Vaccinations Centers and Vaccine Types available");
                }else if(!vaccinationCenterList.isEmpty() && vaccineTypeList.isEmpty()){
                    System.out.println("No Vaccine Types available");
                }else if(vaccinationCenterList.isEmpty() && !vaccineTypeList.isEmpty()){
                    System.out.println("No Vaccinations Centers available");
                }
            }else {
                int vcInt = readVaccinationCenter();
                VaccinationCenter vc = vaccinationCenterList.get(vcInt);
               // System.out.println(vcInt);
                VaccineType vt;
                if(vc instanceof MassVaccinationCenter){
                     vt = ((MassVaccinationCenter) vc).getVt();
                }else {
                    int vtInt = readVaccineType();
                     vt = vaccineTypeList.get(vtInt);
                    //System.out.println(vtInt);
                }
                String day = readDay(vc,vt);
                String hr = null;
                try {
                     hr = ctrl.readHour(snsUserNumber,vc,vt,day);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(ctrl.createVaccinationSchedule(snsUserNumber,vc,vt,day,hr)){
                    System.out.println("The data is correct? ");
                    System.out.println(new SnsUserVaccineSchedule(snsUserNumber,vc,vt,day,hr));
                    boolean resposta = true;
                    System.out.println("Yes 1.");
                    System.out.println("No 2.");
                    while (resposta) {
                        int answer = Utils.readIntegerFromConsole("Answer :");
                        if (answer==1){
                            resposta=false;
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
                                    ctrl.sendMessage(snsUserNumber, vc, vt, day, hr);
                                }else if(answer1 ==2){
                                    resposta2=false;
                                }
                            }
                        }else if(answer == 2){
                            resposta=false;
                            System.out.println("Vaccination Schedule registration failed!");
                        }
                    }
                }else {
                    System.out.println("Vaccination Schedule failed!");
                }
            }
        }else {
            System.out.println("SnsUserNumber don´t match");
        }
    }

    public int readSnsUserNumber() {
        boolean valid = false;
        System.out.print("Enter your Sns UserNumber: ");
        int snsUserNumber = 0;
        do {
            try {
                snsUserNumber = Utils.readIntegerFromConsole("SnsUserNumber :");
                ctrl.chekSnsUserNumberRules(snsUserNumber);
                valid = true;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        } while (!valid);
        return snsUserNumber;
    }

    public int readVaccinationCenter() {
        List<MenuItem> options = new ArrayList<>();
        VaccinationCenter vc;
        List<VaccinationCenter> vaccinationCenterList = Company.getVaccinationCenterList();

        for (VaccinationCenter c : vaccinationCenterList) {
            options.add(new MenuItem(c.getName(), new ShowTextUI("You have chosen" + c.getName())));
        }

        int option = 0;
        do {
            option = Utils.showAndSelectIndex(options, "\n\nVaccination Center List:");

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

}