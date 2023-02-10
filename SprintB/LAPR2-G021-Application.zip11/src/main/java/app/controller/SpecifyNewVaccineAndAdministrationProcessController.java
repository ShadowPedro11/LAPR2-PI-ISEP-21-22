package app.controller;

import app.domain.model.Company;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;

public class SpecifyNewVaccineAndAdministrationProcessController {

    private Company company;

    private Vaccine vaccine;

    private VaccineAdministration vaccineAdministration;

    public SpecifyNewVaccineAndAdministrationProcessController() {
        this(App.getInstance().getCompany());
    }

    public SpecifyNewVaccineAndAdministrationProcessController(Company company) {
        this.company = company;
        this.vaccine = null;
    }

    public boolean createNewVaccine(String name, String brand, String type) {
        this.vaccine = this.company.createNewVaccine(name, brand, type);
        return this.company.validateVaccine(vaccine);
    }

    public boolean createVaccineAdministration(String dosage, int minAgeGroup, int maxAgeGroup, int numberOfDoses) {
        this.vaccineAdministration = this.company.createVaccineAdministration(dosage, minAgeGroup, maxAgeGroup, numberOfDoses);
        return this.company.validateVaccineAdministration(vaccineAdministration);
    }

    public boolean saveNewVaccineAndAdministrationProcess() {
        return this.company.addVaccine(vaccine, vaccineAdministration);
    }
}
