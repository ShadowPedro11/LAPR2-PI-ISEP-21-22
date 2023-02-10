package app.ui.console;

import app.controller.SpecifyNewVaccineAndAdministrationProcessController;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;
import app.ui.console.utils.Utils;

public class SpecifyNewVaccineAndAdministrationProcessUI implements Runnable {

    private final SpecifyNewVaccineAndAdministrationProcessController ctrl = new SpecifyNewVaccineAndAdministrationProcessController();

    @Override
    public void run() {
        int option = -1;
        int option1 = -1;
        System.out.println("##Specify a new vaccine##");

        System.out.println("Enter the new vaccine name");
        String name = Utils.readLineFromConsole("Name :");

        System.out.println("Enter the new vaccine brand");
        String brand = Utils.readLineFromConsole("Brand :");

        System.out.println("Enter the new vaccine type");
        String type = Utils.readLineFromConsole("Type :");

        if (ctrl.createNewVaccine(name, brand, type)) {
            System.out.println(new Vaccine(name, brand, type));
            System.out.println("Are you sure you want to specify this vaccine? \n\n" +
                    "1 for yes \n" +
                    "2 for no");

            boolean answered = false;

            while (!answered) {
                option = Utils.readIntegerFromConsole("Your option : ");
                if (option == 1) {
                    answered = true;
                    System.out.println("New vaccine successfully specified");
                } else if (option == 2) {
                    answered = true;
                    System.out.println("This vaccine won't be specified");
                } else {
                    System.out.println("Invalid option");
                }
            }
        } else {
            System.out.println("Invalid subjects");
        }
        if (option == 1) {
            System.out.println("##Insert the administration data##");

            System.out.println("Enter the vaccine dosage for the vaccine");
            String dosage = Utils.readLineFromConsole("Dosage :");

            System.out.println("Enter the minimum age group for the vaccine");
            int minAgeGroup = Utils.readIntegerFromConsole("Minimum Age Group :");

            System.out.println("Enter the maximum age group for the vaccine");
            int maxAgeGroup = Utils.readIntegerFromConsole("Maximum Age Group :");

            System.out.println("Enter the number of doses for the vaccine");
            int numberOfDoses = Utils.readIntegerFromConsole("Number of Doses :");

            if (ctrl.createVaccineAdministration(dosage, minAgeGroup, maxAgeGroup, numberOfDoses)) {
                System.out.println(new VaccineAdministration(dosage, minAgeGroup, maxAgeGroup, numberOfDoses));
                System.out.println("Are you sure you want to save this administration data to the vaccine? \n\n" +
                        "1 for yes \n" +
                        "2 for no");

                boolean answered1 = false;
                while (!answered1) {
                    option1 = Utils.readIntegerFromConsole("Your option : ");
                    if (option1 == 1) {
                        answered1 = true;
                        System.out.println("Administration data successfully saved");
                    } else if (option1 == 2) {
                        answered1 = true;
                        System.out.println("This data won't be saved");
                    } else {
                        System.out.println("Invalid option");
                    }
                }
            } else {
                System.out.println("Invalid subjects");
            }
        }

        if (option == 1 && option1 == 1) {
            ctrl.saveNewVaccineAndAdministrationProcess();
        }

    }
}
