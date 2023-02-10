package app.domain.model;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    Company company = new Company("Teste");
    Employee expected = company.registerNewEmployee("Joana","Porto", 924753689, "joana@gmail.com", 12345678, 1);


    @Test
    void verifyIfExistsEmployeesFalse() {
        boolean expected = false;
        assertEquals(expected, Company.verifyIfExistsEmployees());
    }

    @Test
    void verifyIfExistsEmployeesTrue() {
        boolean expected = true;
        Employee employee = company.registerNewEmployee("Joana","Porto", 924753689, "joana@gmail.com", 12345678, 1);
       company.addEmployee(employee);
        assertEquals(expected, Company.verifyIfExistsEmployees());
    }

    @Test
    void validateVaccine() {
        Vaccine vaccine = new Vaccine(null,null,null);
        boolean expected = false;
        assertEquals(expected,company.validateVaccine(vaccine));
    }
}