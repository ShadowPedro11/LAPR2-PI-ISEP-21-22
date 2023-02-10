package app.ui.console;

import app.controller.App;
import app.controller.UserArrivalReceptionistController;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.ui.console.utils.Utils;

public class UserArrivalReceptionistUI implements Runnable {

    /**
     * New instance of UserArrivalReceptionistController
     */
    UserArrivalReceptionistController ctrl = new UserArrivalReceptionistController();

    VaccinationCenter currentVC = App.getInstance().getCompany().getCurrentVC();
    @Override
    public void run() {

        int snsUserNumber = Utils.readIntegerFromConsole("Please insert the SNS user's number:");
        int option = -1;

        if (!ctrl.verifyIfUserExists(snsUserNumber)) {
            System.out.println("This user does not exist");
        } else {
            if (ctrl.verifyIfUserHasSchedule(snsUserNumber)) {
                System.out.println();
                System.out.println(ctrl.getScheduleData(snsUserNumber));
                System.out.println("Is this user ready to take the vaccine? \n\n" +
                        "1 for yes \n" +
                        "2 for no");

                boolean answered = false;

                while (!answered) {
                    option = Utils.readIntegerFromConsole("Your option : ");
                    if (option == 1) {
                        answered = true;
                        System.out.println("User ready to take vaccine");
                    } else if (option == 2) {
                        answered = true;
                        System.out.println("User not ready to take vaccine");
                    } else {
                        System.out.println("Invalid option");
                    }
                }

            } else {
                System.out.println("The user does not have any vaccine scheduled");
            }

            if (option == 1) {
                if(ctrl.checkIfUserIsInCorrectVC(currentVC, snsUserNumber)){
                    ctrl.moveUserToWaitingRoom(App.getInstance().getCompany().getSNSUserStore().getUserBySNSNumber(snsUserNumber));
                } else {
                    System.out.println("The user is not in the correct vaccination center");
                }
            }
        }
    }
}
