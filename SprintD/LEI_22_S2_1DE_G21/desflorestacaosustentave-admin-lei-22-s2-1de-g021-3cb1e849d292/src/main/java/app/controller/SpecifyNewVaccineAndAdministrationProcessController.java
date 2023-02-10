package app.controller;

import app.domain.model.Company;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;
import app.domain.model.VaccineType;

import java.util.List;

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

    public boolean createNewVaccine(String name, String brand, VaccineType type, List<VaccineAdministration> administrations) {
        this.vaccine = this.company.createNewVaccine(name, brand, type,administrations);
        return this.company.validateVaccine(vaccine);
    }

    public boolean createVaccineAdministration(int dosage, int minAgeGroup, int maxAgeGroup, int numberOfDoses, int timeInterval) {
        this.vaccineAdministration = this.company.createVaccineAdministration(dosage, minAgeGroup, maxAgeGroup, numberOfDoses,timeInterval);
        return this.company.validateVaccineAdministration(vaccineAdministration);
    }

    public boolean saveNewVaccineAndAdministrationProcess() {
        return this.company.addVaccine(vaccine);
    }
}
