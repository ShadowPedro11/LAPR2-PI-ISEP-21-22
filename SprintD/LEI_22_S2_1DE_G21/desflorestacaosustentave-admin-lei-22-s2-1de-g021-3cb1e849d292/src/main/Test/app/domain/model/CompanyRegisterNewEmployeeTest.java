package app.domain.model;

import app.domain.model.employee.CenterCoordinator;
import app.domain.model.employee.Employee;
import app.domain.model.employee.Nurse;
import app.domain.model.employee.Receptionist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CompanyRegisterNewEmployeeTest {


    /*
    @Test
    void validateNoNullCreation() {
        Nurse nu = new Nurse(null, null, 0, null, 0);
        boolean actual = Company.validateEmployee(nu);
        assertFalse(actual);
    }

    @Test
    void registerNewEmployeeNurse() {
        Employee actual = Company.registerNewEmployee("João", "N13", 123456789, "joao@isep.ipp.pt", 12345678, 1);
        assertInstanceOf(Nurse.class, actual);
    }

    @Test
    void registerNewEmployeeReceptionist() {
        Employee actual = Company.registerNewEmployee("João", "N13", 123456789, "joao@isep.ipp.pt", 12345678, 2);
        assertInstanceOf(Receptionist.class, actual);
    }

    @Test
    void registerNewEmployeeCoordinator() {
        Employee actual = Company.registerNewEmployee("João", "N13", 123456789, "joao@isep.ipp.pt", 12345678, 3);
        assertInstanceOf(CenterCoordinator.class, actual);
    }

    @Test
    void validateEmployeeExpectTrue() {
        Nurse test = new Nurse("João", "N13", 123456789, "joao@isep.ipp.pt", 12345678);
        assertTrue(Company.validateEmployee(test));
    }

    @Test
    void validateEmployeeExpectFalse() {
        Nurse test = new Nurse(null, null, 0, null, 0);
        assertFalse(Company.validateEmployee(test));
    }

    @Test
    void validateEmployeeExpectFalseWithBlankSpaces() {
        Nurse test = new Nurse("","",0,"",0);
        assertFalse(Company.validateEmployee(test));
    }

    @Test
    void validateEmployeeExpectFalseWithInvalidPhoneNumber() {
        Nurse test = new Nurse("João","R. Dr.António Bernardino de Almeida",123,"joao@gmail.com",12345678);
        assertFalse(Company.validateEmployee(test));
    }

    @Test
    void validateEmployeeExpectFalseWithInvalidCitizenCardNumber() {
        Nurse test = new Nurse("João","R. Dr.António Bernardino de Almeida",123456789,"joao@gmail.com",123456);
        assertFalse(Company.validateEmployee(test));
    }

    /*
    @Test
    void addEmployeeExist() {
        Nurse test = new Nurse("João", "N13", 123456789, "joao@isep.ipp.pt", 12345678);
        Company.addEmployee(test);
        assertTrue(Company.getEmployeesList().contains(test));
    }

    @Test
    void addEmployeeDontExist() {
        Nurse test = new Nurse("João", "N13", 123456789, "joao@isep.ipp.pt", 12345678);
        assertFalse(Company.getEmployeesList().contains(test));
    }
    */
}