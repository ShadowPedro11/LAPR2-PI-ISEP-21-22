package app.ui.console.admin;

import app.controller.App;
import app.controller.RegisterUserFromCSVController;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.ui.console.utils.Utils;

public class RegisterUserFromCSVUI implements Runnable {

    private final RegisterUserFromCSVController ctrl = new RegisterUserFromCSVController();

    @Override
    public void run() {
        System.out.println("##Register users from CSV file##\n" +
                "Type the path to tthe CSV file containing the users you want to register");
        String pahtToCSV = Utils.readLineFromConsole("Path: ");

        if (ctrl.validatePathToCSV(pahtToCSV)) {
            System.out.println("File exist\n" +
                    "Do you want to register these users?\n" +
                    "1 for yes\n" +
                    "2 for no\n");

            boolean awnsered = false;
            int awnser = 0;

            while (!awnsered) {
                awnser = Utils.readIntegerFromConsole("Awnser: ");
                if (awnser < 1 || awnser > 2)
                    System.out.println("Invalid awnser");
                else
                    awnsered = true;
            }

            if (awnser == 1) {
                if (ctrl.saveUsers()) {
                    System.out.println("Users successfully registered");
                    for (SNSUser u : ctrl.store.getUserList()) {
                        System.out.println(u.getEmail() + " " + u.getPassword());
                    }
                } else {
                    System.out.println("Users unssuccesfully registered");
                }
            } else {
                System.out.println("Users won't be registered");
            }

        } else {
            System.out.println("File does not exist");
        }


    }
}
