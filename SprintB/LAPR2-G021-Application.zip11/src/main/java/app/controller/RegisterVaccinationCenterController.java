package app.controller;

import app.domain.model.CenterCoordinator;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter;

public class RegisterVaccinationCenterController {

    /**
     * The Company that will be associated the Vaccination Center
     */
    private Company company;
    /**
     * The Vaccination Center
     */
    private VaccinationCenter vc;
    /**
     * The Center Coordinator responsible
     */
    private CenterCoordinator cc;


    public RegisterVaccinationCenterController() {
        this(App.getInstance().getCompany());
    }

    public RegisterVaccinationCenterController(Company company) {
        this.company = company;
        this.vc = null;
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
     * @return boolean referring to valid or invalid
     */
    public boolean createVaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String webSiteAddress, String openHour, String closeHour, int slotDuration, int maxVaccinePerSlot, CenterCoordinator cc) {
        this.cc = cc;
        this.vc = this.company.createVaccinationCenter(name,address,phoneNumber,emailAddress,faxNumber,webSiteAddress,openHour,closeHour,slotDuration,maxVaccinePerSlot,cc);
        return this.company.validateVaccinationCenter(vc);
    }

    /**
     * Checks and saves the Vaccination Center accessing the Company
     * @return boolean referring if the vaccination center is saved
     */
    public boolean saveVaccinationCenter() {
        this.company.addEmployee(cc);
        return this.company.saveVaccinationCenter(vc);
    }


    }
