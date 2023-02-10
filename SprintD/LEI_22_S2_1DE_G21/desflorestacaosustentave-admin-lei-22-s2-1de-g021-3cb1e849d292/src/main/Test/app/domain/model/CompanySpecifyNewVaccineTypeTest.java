package app.domain.model;


import app.controller.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanySpecifyNewVaccineTypeTest {

    Company company = App.getInstance().getCompany();

    @Test
    void validateNoNullCreation() {
        VaccineType vt = new VaccineType(null, null, null);
        assertFalse(company.validateVaccineType(vt));
    }

    @Test
    void createNewVaccineType() {
        VaccineType vt = company.createNewVaccineType("abc12", "A test vaccine type", "Live attenuated vaccine");
        assertInstanceOf(VaccineType.class, vt);
    }

    @Test
    void validateVaccineTypeBlank() {
        VaccineType vt = company.createNewVaccineType("", "", "");
        assertFalse(company.validateVaccineType(vt));
    }

    @Test
    void validateVaccineTypeInvalidCode() {
        VaccineType vt = company.createNewVaccineType("abcde6", "A test vaccine type", "Live attenuated vaccine");
        assertFalse(company.validateVaccineType(vt));
    }

    @Test
    void addVaccineTypeExist() {
        VaccineType vt = company.createNewVaccineType("abc12", "A test vaccine type", "Live attenuated vaccine");
        company.addVaccineType(vt);
        assertTrue(company.getVaccineTypes().contains(vt));
    }

    @Test
    void addVaccineTypeDontExist() {
        VaccineType vt = company.createNewVaccineType("abc12", "A test vaccine type", "Live attenuated vaccine");
        assertFalse(company.getVaccineTypes().contains(vt));
    }

}