package app.domain.store;

import app.controller.App;
import app.domain.model.*;
import javafx.scene.control.Alert;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CheckAndExportStore {

    Company company= App.getInstance().getCompany();
    /**
     * Gets VaccinationEvent Store
     */
    static VaccinationEventStore  eventStore =  new VaccinationEventStore();
    /**
     * Gets list of vaccination events
     */
     List<VaccinationEvent> vaccinationEventList = company.getVaccinationEventStore().getList();
    SNSUserStore snsUserStore = company.getSNSUserStore();




    /**Check if the two days inputted are correct
     * @param dayInitial
     * @param dayEnd
     * @return Boolean True/False if the interval is correct/incorrect
     */
    public boolean checkIntervalTimeRules(String dayInitial, String dayEnd){
        try {
            chekDayDateRules(dayInitial);
        }catch (IllegalArgumentException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid on Start Date");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            return false;

        }
        try {
            chekDayDateRules(dayEnd);
        }catch (IllegalArgumentException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Input not valid on End Date");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            return false;
        }
        try {
            checkInterval(dayInitial,dayEnd);
        }catch (IllegalArgumentException e){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Invalid");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            return false;
        }
        return true;
    }

    /** Check if a day is valid
     * @param day
     */
    public void chekDayDateRules(String day) {
        int slash = 0;
        for (int i = 0; i < day.length(); i ++) {
            if (day.charAt(i) == '/') {
                slash++;
            }
        }
        if(!day.contains("/")){
            throw new IllegalArgumentException("Date should be in this format dd/MM/yyyy");
        }
        if (slash!=2){
            throw new IllegalArgumentException("Date should be in this format dd/MM/yyyy");
        }

        String[] dayArr = day.split("/");
        int dayy = Integer.parseInt(dayArr[0]);
        int month = Integer.parseInt(dayArr[1]);
        int year = Integer.parseInt(dayArr[2]);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar actual = Calendar.getInstance();
        Calendar wanted = Calendar.getInstance();
        wanted.set(Calendar.DAY_OF_MONTH, dayy);
        wanted.set(Calendar.MONTH, month - 1);
        wanted.set(Calendar.YEAR, year);
        if (dayArr.length != 3 || dayArr[0].trim().length() > 2 || dayArr[1].trim().length() > 2 || dayArr[2].trim().length() != 4) {
            throw new IllegalArgumentException("Date must be in this format (dd/mmm/yyyy)");
        }
        if (isLeapYear(year)) {
            //bissexto
            if (month > 0 && month < 13) {
                verifieDateParametersInLeapYear(dayy, month);
            } else {
                throw new IllegalArgumentException("Invalid month");
            }
        } else {
            if (month > 0 && month < 13) {
                verifieDateParametersInNormalYear(dayy, month);
            } else {
                throw new IllegalArgumentException("Invalid month");
            }
        }

        Calendar today = Calendar.getInstance();
        Date todayDate = today.getTime();
        Date wantedDate = wanted.getTime();
        if(wantedDate.after(todayDate)){
            throw new IllegalArgumentException("This day is after the actual date");
        }
    }

    /**Check if the day is valid in each month of a leap year
     * @param dayy
     * @param month
     */
    private static void verifieDateParametersInLeapYear(int dayy, int month) {
        if (month == 2 && (dayy >= 30 || dayy <= 0)) {
            throw new IllegalArgumentException("Invalid date, invalid day for this month");
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && (dayy >= 31 || dayy <= 0)) {
            throw new IllegalArgumentException("Invalid date, invalid day for this month");
        }
        if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && (dayy >= 32 || dayy <= 0)) {
            throw new IllegalArgumentException("Invalid date, invalid day for this month");
        }
    }

    /**Check if the day is valid in each month of a normal year
     * @param dayy
     * @param month
     */
    private static void verifieDateParametersInNormalYear(int dayy, int month) {
        if (month == 2 && (dayy >= 29 || dayy <= 0)) {
            throw new IllegalArgumentException("Invalid date, invalid day for this month");
        }
        if ((month == 4 || month == 6 || month == 9 || month == 11) && (dayy >= 31 || dayy <= 0)) {
            throw new IllegalArgumentException("Invalid date, invalid day for this month");
        }
        if ((month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) && (dayy >= 32 || dayy <= 0)) {
            throw new IllegalArgumentException("Invalid date, invalid day for this month");
        }
    }

    /**Check if athe year input is leap or not
     * @param year
     * @return boolean validation of the leap year
     */
    public static boolean isLeapYear(int year) {
        boolean leap = false;

        // if the year is divided by 4
        if (year % 4 == 0) {

            // if the year is century
            if (year % 100 == 0) {

                // if year is divided by 400
                // then it is a leap year
                if (year % 400 == 0)
                    leap = true;
                else
                    leap = false;
            }

            // if the year is not century
            else
                leap = true;
        }

        else
            leap = false;
        return leap;
    }

    /**Check if the end date is after the start date
     * @param dayInitial
     * @param dayEnd
     */
    public static void checkInterval(String dayInitial, String dayEnd){

        String[] dayArrInitial = dayInitial.split("/");
        String[] dayArrEnd = dayEnd.split("/");
        int dayyIn = Integer.parseInt(dayArrInitial[0]);
        int monthIn = Integer.parseInt(dayArrInitial[1]);
        int yearIn = Integer.parseInt(dayArrInitial[2]);
        int dayyEd = Integer.parseInt(dayArrEnd[0]);
        int monthEd = Integer.parseInt(dayArrEnd[1]);
        int yearEd = Integer.parseInt(dayArrEnd[2]);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar initial = Calendar.getInstance();
        Calendar end = Calendar.getInstance();
        initial.set(Calendar.DAY_OF_MONTH, dayyIn);
        initial.set(Calendar.MONTH, monthIn - 1);
        initial.set(Calendar.YEAR, yearIn);
        end.set(Calendar.DAY_OF_MONTH, dayyEd);
        end.set(Calendar.MONTH, monthEd - 1);
        end.set(Calendar.YEAR, yearEd);
        if (end.before(initial)) {
            throw new IllegalArgumentException("Invalid interval time ");
        }
    }

    //=======================================================================================================//

    /**Generates the text to be added to the csv file
     * @param dayInitial
     * @param dayEnd
     * @return String whit the text
     * @throws ParseException
     */
    public String getText(String dayInitial,String dayEnd) throws ParseException {
        List<String> dataList = new ArrayList<>();
        dataList.clear();
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(dayInitial);
        Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(dayEnd);
        String email = App.getInstance().getCurrentUserSession().getUserId().getEmail();
        //String email = "ana@gmail.com";
        doListOfStatistics(dataList, date1, date2, email);
        String txt = "";
        for (String c : dataList) {
            txt = txt + c + "\n";
        }
        return txt;
    }

    /**Create a list whit vaccination Statistics wanted
     * @param dataList
     * @param dateInitial
     * @param dateEnd
     * @param email
     * @throws ParseException
     */
    private void doListOfStatistics(List<String> dataList, Date dateInitial, Date dateEnd, String email) throws ParseException {
        Collections.sort(vaccinationEventList);
        int colluns=2;
        int lines=1000;
        String[][] jdk = new String[lines][colluns];
        for(VaccinationEvent c: vaccinationEventList){
           if(email.compareTo(c.toDto().getSnsUserSchedule().toDto().getVaccinationCenter().getCc().getEmail())==0){
                if(getState(c.toDto().getVaccineName(),c.toDto().getDoseNumber(),c.toDto().getSnsUserSchedule()).compareTo("completed")==0){
                    String actualDate = c.toDto().getSnsUserSchedule().toDto().getDay();
                    Date date3=new SimpleDateFormat("dd/MM/yyyy").parse(actualDate);
                    if ((date3.after(dateInitial) && date3.before(dateEnd)) || date3.equals(dateInitial) || date3.equals(dateEnd)){
                        int controller1=0;
                        for (int i = 0; i < lines; i++) {
                            if (jdk[i][0]!=null){
                                if(controller1==0){
                                    if (jdk[i][0].compareTo(c.toDto().getSnsUserSchedule().toDto().getDay()) == 0) {
                                        int da = Integer.parseInt(jdk[i][1]) +1;
                                        jdk[i][1]=Integer.toString(da);
                                        controller1++;
                                    }
                                }
                            }
                        }
                        if (controller1==0){
                            int controller2 = 0;
                            for (int i = 0; i < lines; i++) {
                                if (jdk[i][0]==null){
                                    if(controller2==0){
                                        jdk[i][0]=c.toDto().getSnsUserSchedule().toDto().getDay();
                                        jdk[i][1]="1";
                                        controller2++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        for (int i = 0; i < lines; i++) {
            if (jdk[i][0]!=null){
                dataList.add(jdk[i][0]+";"+jdk[i][1]);
            }
        }
    }

    private String getState(String vaccineName, int doseNumber, SnsUserVaccineSchedule snsUserSchedule){
       String state="";
       Vaccine v1 = company.getVaccineByName(vaccineName);
       List<VaccineAdministration> vaccineAdministrationsList = v1.getAdministrations();
       SNSUser user =  snsUserStore.getUserBySNSNumber(snsUserSchedule.toDto().getSnsUserNumber());
       long age = user.getAge();
        int currentState=0;

       for (VaccineAdministration c: vaccineAdministrationsList){
           if(c.getMinAgeGroup()<age && c.getMaxAgeGroup()>age){
               currentState=c.getNumberOfDoses();
           }
       }


       if(doseNumber==currentState){
           state="completed";
       }
       return state;
    }


    //=======================================================================================================//

    /**Create a file with the vaccination statistics
     * @param file
     * @param text
     * @throws FileNotFoundException
     */
    public void saveSystem(File file, String text) throws  FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.write(text);
        printWriter.close();

    }




}
