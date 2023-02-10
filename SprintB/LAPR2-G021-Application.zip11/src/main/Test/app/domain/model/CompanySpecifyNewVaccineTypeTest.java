package app.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanySpecifyNewVaccineTypeTest {

    @Test
    void validateNoNullCreation() {
        VaccineType vt = new VaccineType(null, null, null);
        assertFalse(Company.validateVaccineType(vt));
    }

    @Test
    void createNewVaccineType() {
        VaccineType vt = Company.createNewVaccineType("abc12", "A test vaccine type", "Live attenuated vaccine");
        assertInstanceOf(VaccineType.class, vt);
    }

    @Test
    void validateVaccineTypeBlank() {
        VaccineType vt = Company.createNewVaccineType("", "", "");
        assertFalse(Company.validateVaccineType(vt));
    }

    @Test
    void validateVaccineTypeInvalidCode() {
        VaccineType vt = Company.createNewVaccineType("abcde6", "A test vaccine type", "Live attenuated vaccine");
        assertFalse(Company.validateVaccineType(vt));
    }

    @Test
    void addVaccineTypeExist() {
        VaccineType vt = Company.createNewVaccineType("abc12", "A test vaccine type", "Live attenuated vaccine");
        Company.addVaccineType(vt);
        assertTrue(Company.getVaccineTypes().contains(vt));
    }

    @Test
    void addVaccineTypeDontExist() {
        VaccineType vt = Company.createNewVaccineType("abc12", "A test vaccine type", "Live attenuated vaccine");
        assertFalse(Company.getVaccineTypes().contains(vt));
    }
}