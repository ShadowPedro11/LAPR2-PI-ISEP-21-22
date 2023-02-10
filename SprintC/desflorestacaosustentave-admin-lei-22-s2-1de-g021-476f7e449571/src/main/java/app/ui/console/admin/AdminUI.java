package app.ui.console.admin;

import app.domain.Saver;
import app.ui.console.*;
import app.ui.console.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

public class AdminUI implements Runnable{
    public AdminUI()
    {
    }

    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();
        options.add(new MenuItem("Register Vaccination Center ", new RegisterVaccinationCenterUI()));
        options.add(new MenuItem("Register new emlpoyee", new SpecifyNewEmployeeUI()));
        options.add(new MenuItem("List employees", new ListEmployeesUI()));
        options.add(new MenuItem("Create new vaccine and its administration process", new SpecifyNewVaccineAndAdministrationProcessUI()));
        options.add(new MenuItem("Register new vaccine type", new SpecifyNewVaccineTypeUI()));
        options.add(new MenuItem("Register users from CSV file", new RegisterUserFromCSVUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nAdmin Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );
        //Saver.saveData();
    }
}
