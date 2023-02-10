package app.domain.model;

import app.domain.model.employee.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ListEmployeesTest {
    Company company = new Company("Teste");
    @Test
    void listEmployeesWithWantedRole() {
        Employee employee1 = company.registerNewEmployee("Joana","Porto", 924753689, "joana@gmail.com", 12345678, 1);
        Employee employee2 = company.registerNewEmployee("Joao","Braga", 916374832, "joao@gmail.com", 43213412, 2);
        Employee employee3 = company.registerNewEmployee("Diana","Aveiro", 937210994, "diana@gmail.com", 53412345, 3);
        company.addEmployee(employee1);
        company.addEmployee(employee2);
        company.addEmployee(employee3);
        assertEquals(employee1,ListEmployees.getEmployeeWithWantedRole(1,0));
    }
}