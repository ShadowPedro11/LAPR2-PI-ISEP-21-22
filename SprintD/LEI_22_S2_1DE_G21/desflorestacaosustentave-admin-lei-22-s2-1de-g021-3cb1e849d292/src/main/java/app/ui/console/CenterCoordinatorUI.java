package app.ui.console;

import app.ui.console.utils.Utils;
import app.ui.gui.AnalyzeCenterPerformanceUI;
import app.ui.gui.CheckAndExportUI;
import app.ui.gui.ImportDataLegacySystemUI;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class CenterCoordinatorUI implements Runnable {

    @Override
    public void run()
    {
        List<MenuItem> options = new ArrayList<MenuItem>();

        options.add(new MenuItem("Check and Export Data",new CheckAndExportUI()));
        options.add(new MenuItem("Analyze center performance", new AnalyzeCenterPerformanceUI()));
        options.add(new MenuItem("Import and sort data from legacy system", new ImportDataLegacySystemUI()));

        int option = 0;
        do
        {
            option = Utils.showAndSelectIndex(options, "\n\nCenter coordinator Menu:");

            if ( (option >= 0) && (option < options.size()))
            {
                options.get(option).run();
            }
        }
        while (option != -1 );

    }
}
