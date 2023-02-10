package app.ui.console.admin;

import app.controller.ListEmployeesController;
import app.domain.model.*;
import app.domain.model.employee.Employee;
import app.ui.console.utils.Utils;

public class ListEmployeesUI implements Runnable{
    /**
     * Instance of the class ListEmployeesController.
     */
    private final ListEmployeesController ctrl = new ListEmployeesController();

    /**
     * These methods create the UI for the communication with the user.
     */
    public void run(){
        System.out.println("##List the Employees##");
        System.out.println("Enter the role that you wish to list:");
        System.out.println("1 for Nurse \n2 for Receptionist \n3 for Center Coordinator");

        int role = 0;
        boolean answeredRole = false;
        while (!answeredRole) {
            role = Utils.readIntegerFromConsole("Role : ");
            if (role < 1 || role > 3) {
                System.out.println("Invalid role");
                System.out.println("Enter the role that you wish to list : \n" +
                        " 1 for nurse \n 2 for receptionist \n 3 for coordinator");
            } else {
                answeredRole = true;
            }
        }

        if (Company.verifyIfExistsEmployees()) {
            int numberOfEmployees = 0;
            for (int index = 0; index < Company.getEmployeesList().size(); index++) {
                Employee employee = ctrl.getEmployeeWithWantedRole(role, index);
                if (employee != null){
                    System.out.println(employee);
                    numberOfEmployees++;
                }
            }
            if(numberOfEmployees == 0){
                System.out.println("There are no employees with this role registered.");
            }
        }else{
            System.out.println("There are no employees registered.");
        }
    }
}
