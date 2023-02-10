package app.controller;

import app.domain.model.Company;
import app.domain.model.employee.Employee;

public class SpecifyNewEmployeeController {

    private Company company;
    public Employee employee;

    public SpecifyNewEmployeeController () {
        this(App.getInstance().getCompany());
    }

    public SpecifyNewEmployeeController (Company company) {
        this.company = company;
        this.employee = null;
    }

    /**
     * Register a new employee with the following attributes:
     * @param name
     * @param address
     * @param phoneNumber
     * @param email
     * @param citizenCardNumber
     * @param role
     * @return a true if, and only if, the new employee was successfully registered.
     */
    public boolean registerNewEmployee(String name, String address, int phoneNumber, String email, int citizenCardNumber, int role) {
        this.employee = this.company.registerNewEmployee(name, address, phoneNumber, email, citizenCardNumber, role);
        return this.company.validateEmployee(employee);
    }

    /**
     * Stores the new employee into the employees ArrayList in the Company class.
     * @return true if, and only if, the new employee was successfully saved.
     */
    public boolean saveNewEmployee () {
        return this.company.addEmployee(employee);
    }

}
