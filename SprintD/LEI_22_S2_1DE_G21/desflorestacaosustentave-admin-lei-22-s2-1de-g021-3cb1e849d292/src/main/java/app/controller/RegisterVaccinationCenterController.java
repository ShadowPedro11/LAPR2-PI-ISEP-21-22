package app.controller;

import app.domain.model.VaccinationCenter.HealthCareCenter;
import app.domain.model.VaccinationCenter.MassVaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.model.employee.CenterCoordinator;
import app.domain.model.Company;



public class RegisterVaccinationCenterController {

    /**
     * The Company that will be associated the Vaccination Center
     */
    private Company company;
    /**
     * The Vaccination Center
     */
    private MassVaccinationCenter mvc;
    private HealthCareCenter hc;
    /**
     * The Center Coordinator responsible
     */
    private CenterCoordinator cc;


    public RegisterVaccinationCenterController() {
        this(App.getInstance().getCompany());
    }

    public RegisterVaccinationCenterController(Company company) {
        this.company = company;
        this.mvc = null;
        this.cc = null;
    }

    /**
     * Creates a Vaccination Center in Company and checks if it is valid
     * @param name
     * @param address
     * @param phoneNumber
     * @param emailAddress
     * @param faxNumber
     * @param webSiteAddress
     * @param openHour
     * @param closeHour
     * @param slotDuration
     * @param maxVaccinePerSlot
     * @param cc
     * @param vt
     * @return boolean referring to valid or invalid
     */
    public boolean createMassVaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String webSiteAddress, String openHour, String closeHour, int slotDuration, int maxVaccinePerSlot, CenterCoordinator cc, VaccineType vt) {
        this.cc = cc;
        this.mvc = (MassVaccinationCenter) this.company.createMassVaccinationCenter(name,address,phoneNumber,emailAddress,faxNumber,webSiteAddress,openHour,closeHour,slotDuration,maxVaccinePerSlot,cc,vt);
        return this.company.validateMassVaccinationCenter(this.mvc);
    }

    public boolean createHealthCareVaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String webSiteAddress, String openHour, String closeHour, int slotDuration, int maxVaccinePerSlot, CenterCoordinator cc, String args) {
        this.cc = cc;
        this.hc = (HealthCareCenter) this.company.createHealthCareVaccinationCenter(name,address,phoneNumber,emailAddress,faxNumber,webSiteAddress,openHour,closeHour,slotDuration,maxVaccinePerSlot,cc,args);
        return this.company.validateHealthCareVaccinationCenter(hc);
    }

    /**
     * Checks and saves the Vaccination Center accessing the Company
     * @return boolean referring if the vaccination center is saved
     */
    public boolean saveMassVaccinationCenter() {
        return this.company.saveMassVaccinationCenter(mvc);
    }
    public boolean saveHealthCareVaccinationCenter() {
        return this.company.saveHealthCareVaccinationCenter(hc);
    }


    }
