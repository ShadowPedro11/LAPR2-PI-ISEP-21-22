package app.controller;

import app.domain.model.employee.Employee;
import app.domain.model.ListEmployees;

public class ListEmployeesController {
    /**
     * This method calls the ListEmployee's method list getEmployeeWithWantedRole()
     * to get an Employee instance with the wanted role.
     *
     * @param role
     * @param index
     * @return Employee instance.
     */
    public Employee getEmployeeWithWantedRole(int role, int index){return ListEmployees.getEmployeeWithWantedRole(role, index);}

}
