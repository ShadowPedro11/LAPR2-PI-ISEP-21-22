package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccineType;


public class SpecifyNewVaccineTypeController {

    private Company company;
    private VaccineType vt;

    public SpecifyNewVaccineTypeController() {
        this(App.getInstance().getCompany());
    }

    public SpecifyNewVaccineTypeController (Company company) {
        this.company = company;
        this.vt = null;
    }

    /**
     * Creates a new vaccine type with the following attributes:
     * @param code
     * @param descrition
     * @param technology
     * @return true if, and only if, the created vaccine type is valid (not null or blank attributes or the code parameter is shorter
     * or longer than 5 alphanumerical char.
     */
    public boolean createVaccineType (String code, String descrition, String technology) {
        this.vt = this.company.createNewVaccineType(code, descrition, technology);
        return this.company.validateVaccineType(vt);
    }

    /**
     * Saves the new vaccine type to the vaccyneTypes ArrayLoist in the Company class
     * @return true if, and only if, the registration was done successfully.
     */
    public boolean saveVaccineType () {
        return this.company.addVaccineType(vt);
    }
}
