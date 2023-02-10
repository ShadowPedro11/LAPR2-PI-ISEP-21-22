package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter.HealthCareCenter;
import app.domain.model.VaccinationCenter.MassVaccinationCenter;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.employee.CenterCoordinator;
import app.domain.model.employee.Nurse;
import app.domain.model.employee.Receptionist;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;
import pt.isep.lei.esoft.auth.UserSession;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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


        VaccineType vt1 = new VaccineType("qwert","Covid","mRNA");
        VaccineType vt2 = new VaccineType("asdfg","Dengue","mRNA");
        company.addVaccineType(vt1);
        company.addVaccineType(vt2);
        CenterCoordinator cc1 =new CenterCoordinator("Ana","Casa da Ana",927641959,"ana@gmail.com",12345678);
        CenterCoordinator cc2 =new CenterCoordinator("Paulo","Casa do Paulo",965711204,"paulo@gmail.com",21345678);
        company.addEmployee(cc1);
        company.addEmployee(cc2);
        MassVaccinationCenter vc1 = new MassVaccinationCenter("CentroTeste1","Rua do CentroTeste1","987321654","centroteste1@gmail.com","1234567890","www.centroteste1.com","8:00","16:00",5,5,cc1,vt1);
        HealthCareCenter vc2 = new HealthCareCenter("CentroTeste2","Rua do CentroTeste2","978321654","centroteste2@gmail.com","2134567890","www.centroteste2.com","10:00","18:00",5,5,cc1,"Centro Regional do Minho");
        company.saveMassVaccinationCenter(vc1);
        company.saveHealthCareVaccinationCenter(vc2);
        //company.getSNSUserStore().addUsers("users");
        //company.addLoginForUser();
        Nurse nurse = new Nurse("Tiago", "Casa do Tiago",924096624,"tiagoEnfermeiro@gmail.com",14769391);
        company.addEmployee(nurse);
        Receptionist receptionist = new Receptionist("Alexandre","Casa do Alex", 963452045,"alexrececionista@gmail.com",15392845);
        company.addEmployee(receptionist);
        Receptionist receptionist1 = new Receptionist("testReceptionist", "Casa do ze", 123456789, "jose@test.com", 12345678);
        company.addEmployee(receptionist1);
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
