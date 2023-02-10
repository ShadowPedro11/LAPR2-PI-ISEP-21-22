package app.domain.model;

import app.controller.App;
import app.domain.model.VaccinationCenter.HealthCareCenter;
import app.domain.model.VaccinationCenter.MassVaccinationCenter;
import app.domain.model.employee.CenterCoordinator;
import app.domain.shared.Constants;
import app.domain.store.VaccinationEventStore;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Class to run an automatic text that exports vaccination statistics
 */
public class ExportDataAutomatic extends TimerTask {
    List<VaccinationEvent> vaccinationEventList = App.getInstance().getCompany().getVaccinationEventStore().getList();
    static String hour;

    /**
     * Runs the generation of export
     */
    @Override
    public void run() {
        System.out.println("Automatic Task Executed: Daily record generated");
        List<String> dataList = PrintResults();
        for (String c: dataList){
           //System.out.println(c);
        }
        try {
            toFile(dataList);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create a timer task when is defined the interval between two operations
     */
    public static void setTimerTask() {
        Properties props = getProperties();
        hour=props.getProperty("ExportDataAutomaticHour");
        hour=checkHour(hour);
        //System.out.println(hour);
        Timer timer = new Timer();
        String[] hourArr=hour.split(":");
        long day =TimeUnit.DAYS.toMillis(1);
        long minute5 = TimeUnit.MINUTES.toMillis(5);
        long seconds20 = TimeUnit.SECONDS.toMillis(20);
        Calendar myCal = Calendar.getInstance();
        myCal.set(2022, Calendar.JUNE,19);
        myCal.set(Calendar.HOUR_OF_DAY,Integer.parseInt(hourArr[0]));
        myCal.set(Calendar.MINUTE,Integer.parseInt(hourArr[1]));
        myCal.set(Calendar.SECOND,0);
        myCal.set(Calendar.MILLISECOND,0);
        Date date = myCal.getTime();
        //System.out.println(date);
        timer.scheduleAtFixedRate(new ExportDataAutomatic(),date,day);
    }

    public static String checkHour(String hour){
        String checker = hour;
        String safeHour = "23:55";
        DateFormat df = new SimpleDateFormat("HH:mm");

        if(hour.contains(":")){
            String[] arr = checker.split(":");
            if (arr.length==2){
                if (Integer. parseInt(arr[0])<24 && Integer. parseInt(arr[0])>0){
                    if (Integer. parseInt(arr[1])<60 && Integer. parseInt(arr[1])>0){
                        return hour;
                    }
                }
            }
        }
        return safeHour;
    }


    /**
     * @return properties values of config.properties
     */
    private static Properties getProperties() {
        Properties props = new Properties();

        // Add default properties and values
        //props.setProperty(hour, "22:30");


        // Read configured values
        try
        {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch(IOException ex) {

        }
        return props;
    }

    /** Creates the list of vaccination statistics wanted
     * @return Arraylist of String whit vaccination Statistics
     */
    public List<String> PrintResults(){
        List<String> dataList = new ArrayList<>();
        int colluns=2;
        int lines=1000;
        String[][] jdk = new String[lines][colluns];
        if (!vaccinationEventList.isEmpty()) {
            Collections.sort(vaccinationEventList);
        }
        for(VaccinationEvent c: vaccinationEventList){
            // if(c.toDto().getState().compareTo("completed")==0){
            if(c.toDto().getSnsUserSchedule().toDto().getDay().compareTo(getAtualDate())==0) {
                int controller1 = 0;
                for (int i = 0; i < lines; i++) {
                    if (jdk[i][0] != null) {
                        if (controller1 == 0) {
                            if (jdk[i][0].compareTo(c.toDto().getSnsUserSchedule().toDto().getDay()+ ";" +c.toDto().getSnsUserSchedule().toDto().getVaccinationCenter().getName()) == 0) {
                                int da = Integer.parseInt(jdk[i][1]) + 1;
                                jdk[i][1] = Integer.toString(da);
                                controller1++;
                            }
                        }
                    }
                }
                if (controller1 == 0) {
                    int controller2 = 0;
                    for (int i = 0; i < lines; i++) {
                        if (jdk[i][0] == null) {
                            if (controller2 == 0) {
                                jdk[i][0] = c.toDto().getSnsUserSchedule().toDto().getDay()+ ";" +c.toDto().getSnsUserSchedule().toDto().getVaccinationCenter().getName();
                                jdk[i][1] = "1";
                                controller2++;
                            }
                        }
                    }
                }

            }
            //   }

        }
        for (int i = 0; i < lines; i++) {
            if (jdk[i][0]!=null){
                dataList.add(jdk[i][0]+";"+jdk[i][1]);
            }

        }
        return dataList;
    }

    /**Gets the list of vaccination Statistics and generate a file with this information
     * @param dataList
     * @throws FileNotFoundException
     */
    private static void toFile(List<String> dataList) throws FileNotFoundException {
        String text = "";
        for (String c: dataList){

            text = text +  c +" \n";
        }

        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy-HH-mm");
        String date1 = format1.format(date);
        String filename = "VaccinationOfAllCenterData[" + date1 +"]";
        File file = new File(filename);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.write(text);
        printWriter.close();



    }



    /**Gets the actual date, day, month and year
     * @return an String whit the actual date day/month/year
     */
    private static String getAtualDate(){
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        String date1 = format1.format(date);
        return date1;
    }
}
