package app.ui.console.admin;

import app.controller.SpecifyNewEmployeeController;
import app.domain.model.employee.Nurse;
import app.ui.console.utils.Utils;

public class SpecifyNewEmployeeUI implements Runnable {

    private final SpecifyNewEmployeeController ctrl = new SpecifyNewEmployeeController();

    @Override
    public void run() {
        System.out.println("##Register a new employee##");

        System.out.println("Type the employee name");
        String name = Utils.readLineFromConsole("Name :");

        System.out.println("Type the employee address");
        String address = Utils.readLineFromConsole("Address :");

        System.out.println("Type the employee phone number");
        int phoneNumber = Utils.readIntegerFromConsole("Phone number :");

        System.out.println("Type the employee E-mail");
        String email = Utils.readLineFromConsole("E-mail: ");

        System.out.println("Type the employee citizen card number");
        int citizenCardNumber = Utils.readIntegerFromConsole("Citizen card number :");

        System.out.println("Select the role for the new employee\n" +
                " 1 for nurse \n 2 for receptionist \n 3 for coordinator");

        boolean answeredRole = false;
        int role = 0;
        while (!answeredRole) {
            role = Utils.readIntegerFromConsole("Role : ");
            if (role < 1 || role > 3) {
                System.out.println("Invalid role");
                System.out.println("Enter the new employee role : \n" +
                        " 1 for nurse \n 2 for receptionist \n 3 for coordinator");
            } else {
                answeredRole = true;
            }
        }

        if (ctrl.registerNewEmployee(name, address, phoneNumber, email, citizenCardNumber, role)) {
            System.out.print(new Nurse(name, address, phoneNumber, email, citizenCardNumber));
            if (role == 1) {
                System.out.println("Occupation : Nurse");
            } else if (role == 2) {
                System.out.println("Occupation : Receptionist");
            } else {
                System.out.println("Occupation : Coordinator");
            }
            System.out.println("\nAre you sure you want to register this employee? \n" +
                    "1 for yes \n" +
                    "2 for no");

            boolean answered = false;
            int option = -1;

            while (!answered) {
                option = Utils.readIntegerFromConsole("Your option : ");
                if (option == 1) {
                    answered = true;
                    if(ctrl.saveNewEmployee()) {
                        System.out.println("Employee successfully registered");
                        System.out.println(ctrl.employee.getPassword());
                    } else {
                        System.out.println("Employee couldn't be registered");
                    }
                } else if (option == 2) {
                    answered = true;
                    System.out.println("This employee won't be registered");
                } else {
                    System.out.println("Invalid option");
                }
            }
        } else {
            System.out.println("Registration successfulness, the information provided is invalid");
        }
    }
}
