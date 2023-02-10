package app.domain.model;

import app.controller.App;
import app.domain.model.employee.CenterCoordinator;
import app.domain.model.employee.Employee;
import app.domain.model.employee.Nurse;
import app.domain.model.employee.Receptionist;

import java.util.ArrayList;
import java.util.List;

public class ListEmployees {
    /**
     * Arraylist where the Employee instances with the wanted role are stored.
     */
    private List<Employee> employeesList = new ArrayList<>();

    private Company company = App.getInstance().getCompany();

    /**
     * This method calls the Company's method getEmployee() to get an Employee instance from the arraylist
     * that stores all the Employee instances. Then checks if the Employee instance is also an instance of the class of
     * the role that is desired. If it is, it returns the Employee instance, if not, it returns null.
     *
     * @param role
     * @param index
     * @return Employee instance.
     */
    public Employee getEmployeeWithWantedRole(int role, int index) {
        Employee employee = company.getEmployee(index);
        switch (role) {
            case 1:
                if (employee instanceof Nurse) {
                    return employee;
                }
                break;
            case 2:
                if (employee instanceof Receptionist) {
                    return employee;
                }
                break;
            case 3:
                if (employee instanceof CenterCoordinator) {
                    return employee;
                }
                break;
        }
        return null;
    }
}
