package app.ui.console;

import app.controller.ChoseVaccinationCenterController;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.ui.console.utils.Utils;



import java.util.ArrayList;
import java.util.List;

public class NurseUI implements Runnable {
    public static VaccinationCenter currentVC = null;
    ChoseVaccinationCenterController choseVaccinationCenterController = new ChoseVaccinationCenterController();
    @Override
    public void run()
    {
        ArrayList<VaccinationCenter> vt = choseVaccinationCenterController.choseVaccinationCenter();
        int option;

        do
        {
            System.out.println("Select the vaccination center where you are working: ");
            for (int i = 0; i < vt.size(); i++) {
                System.out.println(i+1 + ". " + choseVaccinationCenterController.getNameVaccinationCenter(vt.get(i)));
            }
            option = Utils.readIntegerFromConsole("Type your option: ");

            if ( (option - 1 >= 0) && (option - 1 < vt.size()))
            {
                currentVC = vt.get(option-1);
                System.out.println("Do you confirm your vaccination center: " + choseVaccinationCenterController.getNameVaccinationCenter(currentVC) + "?" +  "\n1 for yes \n2 for no" );
                boolean answered = false;
                while (!answered){
                    option = Utils.readIntegerFromConsole("Type your option: ");
                    if (option == 1){
                        answered = true;
                        option =-1;
                    } else if (option == 2) {
                        answered = true;
                        option = 0;
                    }else{
                        System.out.println("Invalid option");
                    }
                }
            }
        }
        while (option != -1 );

        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Check for users on waiting room", new CheckWaitingRoomUI()));

        option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nNurse Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );

    }
}
