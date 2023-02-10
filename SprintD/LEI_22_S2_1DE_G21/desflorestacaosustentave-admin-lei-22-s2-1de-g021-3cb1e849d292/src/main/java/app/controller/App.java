package app.controller;

import app.domain.Loader;
import app.domain.model.*;
import app.domain.model.VaccinationCenter.HealthCareCenter;
import app.domain.model.VaccinationCenter.MassVaccinationCenter;
import app.domain.model.employee.CenterCoordinator;
import app.domain.model.employee.Nurse;
import app.domain.model.employee.Receptionist;
import app.domain.shared.Constants;
import app.domain.store.SNSUserStore;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class App {

    private Company company;
    private AuthFacade authFacade;

    private App()
    {
        Properties props = getProperties();
        this.company = new Company(props.getProperty(Constants.PARAMS_COMPANY_DESIGNATION));
        this.authFacade = this.company.getAuthFacade();
        bootstrap();
    }

    public Company getCompany()
    {
        return this.company;
    }


    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String email, String pwd)
    {
        return this.authFacade.doLogin(email,pwd).isLoggedIn();
    }

    public void doLogout()
    {
        this.authFacade.doLogout();
    }

    private Properties getProperties()
    {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "DGS/SNS");


        // Read configured values
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        }
        catch(IOException ex)
        {

        }
        return props;
    }


    private void bootstrap()
    {


        this.authFacade.addUserRole(Constants.ROLE_ADMIN,Constants.ROLE_ADMIN);
        this.authFacade.addUserRole(Constants.ROLE_SNSUSER,Constants.ROLE_SNSUSER);
        this.authFacade.addUserRole(Constants.ROLE_RECEPTIONIST, Constants.ROLE_RECEPTIONIST);
        this.authFacade.addUserRole(Constants.ROLE_NURSE, Constants.ROLE_NURSE);
        this.authFacade.addUserRole(Constants.ROLE_CENTERCOORDINATOR, Constants.ROLE_CENTERCOORDINATOR);
        this.authFacade.addUserWithRole("Main Administrator", "admin@lei.sem2.pt", "123456",Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Main Administrator", "1211131@isep.ipp.pt", "123456",Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Main Administrator", "1211151@isep.ipp.pt", "123456",Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Main Administrator", "1211128@isep.ipp.pt", "123456",Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Main Administrator", "1211089@isep.ipp.pt", "123456",Constants.ROLE_ADMIN);
        this.authFacade.addUserWithRole("Sns User Test", "user@isep.ipp.pt", "123456",Constants.ROLE_SNSUSER);
        this.authFacade.addUserWithRole("Center Coordinator", "cc@gmail.com", "123456",Constants.ROLE_CENTERCOORDINATOR);
        Loader.addLoginAllUsers(this.company.getSNSUserStore().getUserList(), this.company.getEmployees(), authFacade);

        if(company.getVaccineList().isEmpty()) {
            List<VaccineAdministration> vaccineAdministrationList1 = new ArrayList<VaccineAdministration>();
            List<VaccineAdministration> vaccineAdministrationList2 = new ArrayList<VaccineAdministration>();


            VaccineType vt1 = new VaccineType("qwert", "Covid", "mRNA");
            VaccineType vt2 = new VaccineType("asdfg", "Dengue", "mRNA");
            company.addVaccineType(vt1);
            company.addVaccineType(vt2);


            Vaccine vaccine1 = new Vaccine("Pfizer Covid Vaccine", "Pfizer", vt1, vaccineAdministrationList1);
            Vaccine vaccine2 = new Vaccine("Moderna Covid Vaccine", "Moderna", vt1, vaccineAdministrationList1);
            Vaccine vaccine3 = new Vaccine("Pfixer Dengue Vaccine", "Pfizer", vt2, vaccineAdministrationList2);
            Vaccine vaccine4 = new Vaccine("Moderna Dengue Vaccine", "Moderna", vt2, vaccineAdministrationList2);
            company.addVaccine(vaccine1);
            company.addVaccine(vaccine2);
            company.addVaccine(vaccine3);
            company.addVaccine(vaccine4);
            Vaccine vaccine = new Vaccine("Spikevax","Gucci",vt1,vaccineAdministrationList1);
            company.addVaccine(vaccine);

            VaccineAdministration vaccineAdministration1 = new VaccineAdministration(450, 8, 40, 1, 90);
            VaccineAdministration vaccineAdministration2 = new VaccineAdministration(450, 41, 60, 2, 90);
            VaccineAdministration vaccineAdministration3 = new VaccineAdministration(450, 61, 100, 3, 90);
            VaccineAdministration vaccineAdministration4 = new VaccineAdministration(350, 1, 50, 1, 10);
            VaccineAdministration vaccineAdministration5 = new VaccineAdministration(250, 51, 100, 2, 50);
            vaccineAdministrationList1.add(vaccineAdministration1);
            vaccineAdministrationList1.add(vaccineAdministration2);
            vaccineAdministrationList1.add(vaccineAdministration3);
            vaccineAdministrationList2.add(vaccineAdministration4);
            vaccineAdministrationList2.add(vaccineAdministration5);

            CenterCoordinator cc1 = new CenterCoordinator("Ana", "Casa da Ana", 927641959, "ana@gmail.com", 12345678);
            CenterCoordinator cc2 = new CenterCoordinator("Paulo", "Casa do Paulo", 965711204, "paulo@gmail.com", 21345678);
            company.addEmployee(cc1);
            company.addEmployee(cc2);

            MassVaccinationCenter vc1 = new MassVaccinationCenter("Mass Vaccination Center of Porto", "Rua do São João", "987321654", "centro1@gmail.com", "1234567890", "www.centro1.com", "8:00", "20:00", 5, 5, cc1, vt1);
            HealthCareCenter vc2 = new HealthCareCenter("Porto Health Care Center", "Rua da Asperela", "978321654", "centro2@gmail.com", "2134567890", "www.centro2.com", "10:00", "18:00", 5, 5, cc2, "Centro Regional do Porto");
            company.saveMassVaccinationCenter(vc1);
            company.saveHealthCareVaccinationCenter(vc2);

            Nurse nurse = new Nurse("Tiago", "Casa do Tiago", 924096624, "tiagoEnfermeiro@gmail.com", 14769391);
            company.addEmployee(nurse);

            Receptionist receptionist = new Receptionist("Alexandre", "Casa do Alex", 963452045, "alexrececionista@gmail.com", 15392845);
            company.addEmployee(receptionist);

            Receptionist receptionist1 = new Receptionist("testReceptionist", "Casa do ze", 123456789, "jose@test.com", 12345678);
            company.addEmployee(receptionist1);

            String day1 = "19/06/2022";
            String day2 = "19/06/2022";
            String day3 = "19/06/2022";
            String day4 = "19/06/2022";
            String day5 = "19/06/2022";
            String hour1 = "12:00";

        SNSUser user1 = new SNSUser("Vitor Hugo Cavalcanti","Masculino","25/02/1952","Rua São João do Porto|222|4150-385|PORTO",999224331,"user1@gmail.com",981593120,90095830);
        SNSUser user2 = new SNSUser("Augusto Ramos",	"Masculino"	,"24/05/1951"	,"Rua  Grupo 10 de Maio|210|4200-315|PORTO"	,999224332	,"user2@gmail.com"	,981593121	,90095831);
        SNSUser user3 = new SNSUser("Diogo Pereira",	"Masculino",	"13/06/1951"	,"Rua  Almoster|188|4350-28|PORTO"	,999224333	,"user3@gmail.com"	,981593122	,90095832);
        SNSUser user4 = new SNSUser("Vitor Santos",	"Masculino"	,"22/02/1950"	,"Rua  Agostinho Ricca|145|4250-159|PORTO"	,999224334	,"user4@gmail.com"	,981593123	,90095833);
        SNSUser user5 = new SNSUser("Luiz Felipe Silveira",	"Masculino",	"30/05/1953",	"Rua  Guadiana|487|4200-316|PORTO"	,999224335	,"user5@gmail.com"	,981593124	,90095834);
        SNSUser user6 = new SNSUser("Dr. Thomas Melo"	,"Masculino"	,"11/06/1954",	"Rua  Guedes de Azevedo|237|4000-271|PORTO"	,999224336	,"user6@gmail.com",	981593125	,90095835);
        SNSUser user7 = new SNSUser("Clarice da Paz",	"Feminino",	"18/03/1951",	"Rua  Guedes de Azevedo|304|4000-273|PORTO",	999224337	,"user7@gmail.com",	981593126	,90095836);
        SNSUser user8 = new SNSUser("Luigi Nogueira"	, "Masculino",	"21/07/1954"	,"Rua  Guedes de Azevedo|273|4000-272|PORTO"	,999224338	,"user8@gmail.com",981593127	,90095837);
        SNSUser user9 = new SNSUser("Gabriel Farias",	"Masculino"	,"06/05/1954"	,"Rua  Guedes de Azevedo|248|4000-271|PORTO",	999224339	,"user9@gmail.com",	981593128,	90095838);
        SNSUser user10 = new SNSUser("Augusto Farias"	,	"Masculino","11/12/1953"	,"Rua  Guedes de Azevedo|90|4049-8|PORTO	",999224340	,"user10@gmail.com",981593129	,90095839);
        company.getSNSUserStore().addUsersToSystem(user1);
        company.getSNSUserStore().addUsersToSystem(user2);
        company.getSNSUserStore().addUsersToSystem(user3);
        company.getSNSUserStore().addUsersToSystem(user4);
        company.getSNSUserStore().addUsersToSystem(user5);
        company.getSNSUserStore().addUsersToSystem(user6);
        company.getSNSUserStore().addUsersToSystem(user7);
        company.getSNSUserStore().addUsersToSystem(user8);
        company.getSNSUserStore().addUsersToSystem(user9);
        company.getSNSUserStore().addUsersToSystem(user10);

            SnsUserVaccineSchedule vcSkd1 = new SnsUserVaccineSchedule(981593120, vc1, vc1.getVt(), day1, hour1);
            SnsUserVaccineSchedule vcSkd2 = new SnsUserVaccineSchedule(981593121, vc1, vc1.getVt(), day2, hour1);
            SnsUserVaccineSchedule vcSkd3 = new SnsUserVaccineSchedule(981593122, vc1, vc1.getVt(), day2, hour1);
            SnsUserVaccineSchedule vcSkd4 = new SnsUserVaccineSchedule(981593123, vc1, vc1.getVt(), day4, hour1);
            SnsUserVaccineSchedule vcSkd5 = new SnsUserVaccineSchedule(981593124, vc1, vc1.getVt(), day5, hour1);
            SnsUserVaccineSchedule vcSkd6 = new SnsUserVaccineSchedule(981593125, vc2, vt2, day1, hour1);
            SnsUserVaccineSchedule vcSkd7 = new SnsUserVaccineSchedule(981593126, vc2, vt2, day1, hour1);
            SnsUserVaccineSchedule vcSkd8 = new SnsUserVaccineSchedule(981593127, vc2, vt2, day2, hour1);
            SnsUserVaccineSchedule vcSkd9 = new SnsUserVaccineSchedule(981593128, vc2, vt2, day4, hour1);
            SnsUserVaccineSchedule vcSkd10 = new SnsUserVaccineSchedule(981593129, vc2, vt2, day5, hour1);

            VaccinationEvent vaccinationEvent1 = new VaccinationEvent(981593120, vaccine1.getName(), vc1.getVt(), "asdHH", 3, vcSkd1);
            VaccinationEvent vaccinationEvent2 = new VaccinationEvent(981593121, vaccine1.getName(), vc1.getVt(), "asdHH", 3, vcSkd2);
            VaccinationEvent vaccinationEvent3 = new VaccinationEvent(981593122, vaccine2.getName(), vc1.getVt(), "asdHH", 3, vcSkd3);
            VaccinationEvent vaccinationEvent4 = new VaccinationEvent(981593123, vaccine2.getName(), vc1.getVt(), "asdHH", 3, vcSkd4);
            VaccinationEvent vaccinationEvent5 = new VaccinationEvent(981593124, vaccine2.getName(), vc1.getVt(), "asdHH", 3, vcSkd5);
            VaccinationEvent vaccinationEvent6 = new VaccinationEvent(981593125, vaccine3.getName(), vt2, "asdHH", 2, vcSkd6);
            VaccinationEvent vaccinationEvent7 = new VaccinationEvent(981593126, vaccine3.getName(), vt2, "asdHH", 2, vcSkd7);
            VaccinationEvent vaccinationEvent8 = new VaccinationEvent(981593127, vaccine3.getName(), vt2, "asdHH", 2, vcSkd8);
            VaccinationEvent vaccinationEvent9 = new VaccinationEvent(981593128, vaccine4.getName(), vt2, "asdHH", 2, vcSkd9);
            VaccinationEvent vaccinationEvent10 = new VaccinationEvent(981593129, vaccine4.getName(), vt2, "asdHH", 2, vcSkd10);
            company.getVaccinationEventStore().addToList(vaccinationEvent1);
            company.getVaccinationEventStore().addToList(vaccinationEvent2);
            company.getVaccinationEventStore().addToList(vaccinationEvent3);
            company.getVaccinationEventStore().addToList(vaccinationEvent4);
            company.getVaccinationEventStore().addToList(vaccinationEvent5);
            company.getVaccinationEventStore().addToList(vaccinationEvent6);
            company.getVaccinationEventStore().addToList(vaccinationEvent7);
            company.getVaccinationEventStore().addToList(vaccinationEvent8);
            company.getVaccinationEventStore().addToList(vaccinationEvent9);
            company.getVaccinationEventStore().addToList(vaccinationEvent10);

        }

    }

    // Extracted from https://www.javaworld.com/article/2073352/core-java/core-java-simply-singleton.html?page=2
    private static App singleton = null;
    public static App getInstance()
    {
        if(singleton == null)
        {
            synchronized(App.class)
            {
                singleton = new App();
            }
        }
        return singleton;
    }

}
