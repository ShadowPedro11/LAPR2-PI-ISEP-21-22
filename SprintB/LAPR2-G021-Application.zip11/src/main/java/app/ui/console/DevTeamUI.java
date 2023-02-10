package app.ui.console;

/**
 *
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class DevTeamUI implements Runnable{

    public DevTeamUI()
    {

    }
    public void run()
    {
        System.out.println("\n");
        System.out.printf("Development Team:\n");
        System.out.printf("\t Pedro Pereira - 1211131@isep.ipp.pt \n");
        System.out.printf("\t Alexandre Geração - 1211151@isep.ipp.pt \n");
        System.out.printf("\t Tiago Oliveira - 1211128@isep.ipp.pt \n");
        System.out.printf("\t José Gouveia - 1211089@isep.ipp.pt \n");
        System.out.println("\n");
    }
}
