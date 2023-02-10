package app.ui.console.admin;

import app.controller.SpecifyNewVaccineTypeController;
import app.domain.model.VaccineType;
import app.ui.console.utils.Utils;

public class SpecifyNewVaccineTypeUI implements Runnable {

    private final SpecifyNewVaccineTypeController ctrl = new SpecifyNewVaccineTypeController();

    @Override
    public void run() {
        System.out.println("##Register new vaccine type##");

        System.out.println("Enter new vaccine code(five alphanumerical characters)");
        String code = Utils.readLineFromConsole("Code :");

        System.out.println("Enter new vaccine description(short description)");
        String description = Utils.readLineFromConsole("Description :");

        System.out.println("Enter new vaccine technology");
        String technology = Utils.readLineFromConsole("Technology :");

        if (ctrl.createVaccineType(code, description, technology)) {
            System.out.println(new VaccineType(code, description, technology));
            System.out.println("Are you sure you want to register this new vaccine type\n\n" +
                    "1 for yes\n" +
                    "2 for no");

            boolean answered = false;
            int option = -1;

            while (!answered) {
                option = Utils.readIntegerFromConsole("Your option : ");
                if (option == 1) {
                    answered = true;
                    if (ctrl.saveVaccineType()) {
                        System.out.println("Vaccine type succesfully registered");
                    } else {
                        System.out.println("Vaccine type couldn't be registered");
                    }
                } else if (option == 2) {
                    answered = true;
                    System.out.println("This new vaccine type won't be registered");
                } else {
                    System.out.println("Invalid option");
                }
            }

        } else {
            System.out.println("The criation of the new vaccine was unsuccessfully, the information provided is invalid");
        }
    }

}
