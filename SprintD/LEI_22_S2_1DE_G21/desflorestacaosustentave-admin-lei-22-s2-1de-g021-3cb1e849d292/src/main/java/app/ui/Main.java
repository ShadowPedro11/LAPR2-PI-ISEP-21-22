package app.ui;

import app.domain.model.ExportDataAutomatic;
import app.ui.console.MainMenuUI;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */

//Teste
public class Main {

    public static void main(String[] args)
    {
        ExportDataAutomatic.setTimerTask();
        try
        {
            MainMenuUI menu = new MainMenuUI();

            menu.run();
        }
        catch( Exception e )
        {
            e.printStackTrace();
        }
    }
}
