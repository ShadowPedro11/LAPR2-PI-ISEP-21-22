package app.domain.store;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.SnsUserVaccineSchedule;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineType;
import app.ui.console.MenuItem;
import app.ui.console.ShowTextUI;
import app.ui.console.utils.Utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Represent a VaccineScheduleStore
 */
public class VaccineScheduleStore implements Serializable {
    /**
     * instance of 1500 to the length of an array
     */
    private int arrLength = 1500;
    /**
     * Create a list of SnsUserVaccineSchedule
     */
    private List<SnsUserVaccineSchedule> SnsUserVaccineShcheduleList = new ArrayList<>();
    /**
     * Multidimensional array of hours available for each vaccination center
     */
    private String[][] timeForSchedule = new String[arrLength][arrLength];
    /**
     * Multidimensional array of slots  available for each hour in each vaccination center
     */
    private String[][] numVaccinesAvailable = new String[arrLength][arrLength];


    /** Builds a new instance of Sns User Vaccine Schedule
     * @param snsUserNumber
     * @param vc
     * @param vt
     * @param day
     * @param hr
     * @return a new instance of Sns User Vaccine Schedule
     */
    public SnsUserVaccineSchedule createVaccinationSchedule(int snsUserNumber, VaccinationCenter vc, VaccineType vt, String day, String hr) {
        return new SnsUserVaccineSchedule(snsUserNumber, vc, vt, day, hr);
    }

    /** Validate SnsUserVaccine Schedule
     * @param vcSkd
     * @return boolean representing the validation of the SnsUserVaccineSchedule
     */
    public boolean validateVaccinationSchedule(SnsUserVaccineSchedule vcSkd) {
        if(vcSkd != null && checkDuplicates(vcSkd) ){
            return true;
        }else {
            return false;
        }
    }

    /** Check if a user a vaccine schedule more than once
     * @param vcSkd
     * @return boolean, true if a user don´t have the same type of vaccine schedule
     */
    public boolean checkDuplicates(SnsUserVaccineSchedule vcSkd){
        List<SnsUserVaccineSchedule> SnsUserVaccineShcheduleList = getSnsUserVaccineShcheduleList();
        int actualSnsUserNumber = vcSkd.toDto().getSnsUserNumber();
        VaccineType vt = vcSkd.toDto().getVaccineType();
        for(SnsUserVaccineSchedule c : SnsUserVaccineShcheduleList){
            if(actualSnsUserNumber == c.toDto().getSnsUserNumber()){
                if (vt ==  c.toDto().getVaccineType()){
                    return false;
                }
            }
        }
        return true;
    }

    /**Add the vaccine schedule to the list of vaccines schedule
     * @param vcSkd
     * @return
     */
    public boolean saveVaccinationSchedule(SnsUserVaccineSchedule vcSkd) {
        if(!validateVaccinationSchedule(vcSkd))
            return false;
        return this.SnsUserVaccineShcheduleList.add(vcSkd);
    }

    /** Get current user email and  call a method that chek if this sns user number associated with the email matches with the input sns user number
     * @param snsUserNumber
     * @return boolean of the verification of snsusernumber
     */
    public boolean checkSnsUserNumber(int snsUserNumber){
        Company company = App.getInstance().getCompany();
        ArrayList<SNSUser> snsUserList = company.getSNSUserStore().getUserList();
        String currentUserEmail = App.getInstance().getCurrentUserSession().getUserId().getEmail();
        return checker(snsUserNumber, snsUserList, currentUserEmail);
    }

    /**Checks if the sns user number input matches whit the current sns user number
     * @param snsUserNumber
     * @param snsUserList
     * @param currentUserEmail
     * @return boolean of the validation success
     */
    public boolean checker(int snsUserNumber, ArrayList<SNSUser> snsUserList, String currentUserEmail) {
        boolean checker = false;
        for (SNSUser c : snsUserList) {
            if(c.getEmail().compareTo(currentUserEmail)==0){
                if (snsUserNumber == c.getSnsNumber()){
                    checker = true;
                }
            }
        }
        return checker;
    }

    /** Create a message on sms.txt with the data of Sns User Vaccine Schedule
     * @param snsUserNumber
     * @param vc
     * @param vt
     * @param day
     * @param hr
     */
    public void sendMessage(int snsUserNumber, VaccinationCenter vc, VaccineType vt, String day, String hr) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("sms.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);
        pw.write( new SnsUserVaccineSchedule(snsUserNumber, vc, vt, day, hr).toString());
        pw.close();
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return List of Sns Users Vaccines Schedule
     */
    public  List<SnsUserVaccineSchedule> getSnsUserVaccineShcheduleList() {
        return SnsUserVaccineShcheduleList;
    }

    /**
     * @return Array of of hours available for each vaccination center
     */
    public  String[][] getTimeForSchedule(){
        return timeForSchedule;
    }

    /**
     * @return array of slots  available for each hour in each vaccination center
     */
    public  String[][] getNumVaccinesAvailable(){
        return numVaccinesAvailable;
    }

    /** Checks if a Sns User Number have 9 digits
     * @param snsUserNumber
     */
    public void chekSnsUserNumberRules(int snsUserNumber) {
        if (snsUserNumber < 99999999 || snsUserNumber > 999999999) {
            throw new IllegalArgumentException("SnsUserNumber might have 9 digits");
        }
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
        //System.out.println(dateFormat.format(actual.getTime()));
        //System.out.println(dateFormat.format(wanted.getTime()));
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
        if (wanted.before(actual)) {
            throw new IllegalArgumentException("Invalid date, impossible to schedule to this day ");
        }


    }

    /**Check if the day is valid in each month of a leap year
     * @param dayy
     * @param month
     */
    private void verifieDateParametersInLeapYear(int dayy, int month) {
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
    private void verifieDateParametersInNormalYear(int dayy, int month) {
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
    public boolean isLeapYear(int year) {
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

    /**Presents a list of hours and slots available for each vaccination center at any day
     * @param vc
     * @param vt
     * @param day
     * @return
     * @throws IOException
     */
    public String readHour(int SnsUserNumber, VaccinationCenter vc, VaccineType vt, String day) throws IOException {
        List<MenuItem> options = new ArrayList<>();
        List<Date> dias = new ArrayList<>();
        String opHr = vc.getOpenHour();
        String edHr = vc.getCloseHour();
        int sltDur = vc.getSlotDuration();
        int sltCap = vc.getMaxVaccinePerSlot();
        String[] opHourArr = opHr.split(":");
        String[] edHourArr = edHr.split(":");
        long opHourMilli = (Integer.parseInt(opHourArr[0]) - 1) * 60 * 60 * 1000 + Integer.parseInt(opHourArr[1]) * 60 * 1000;
        long edHourMilli = (Integer.parseInt(edHourArr[0]) - 1) * 60 * 60 * 1000 + Integer.parseInt(edHourArr[1]) * 60 * 1000;
        Date opHour = new Date(opHourMilli);
        Date edHour = new Date(edHourMilli);
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        dias.add(opHour);
        while (edHourMilli > opHourMilli) {
            opHourMilli = opHourMilli + vc.getSlotDuration() * 60 * 1000;
            Date hrtt = new Date(opHourMilli);
            dias.add(hrtt);
        }
        String[][] timeForSchedule = getTimeForSchedule();
        String[][] numVaccinesAvailable = getNumVaccinesAvailable();
        int controller1 = 0;
        int aux1 = -1;
        for (int i = 0; i < timeForSchedule.length; i++) {
            if (timeForSchedule[0][i] != null) {
                if (timeForSchedule[0][i].compareTo(day+vc.getName()) == 0) {
                    aux1=i;
                    controller1++;
                }
            }
        }
        if (controller1==0){
            int controller2 = 0;
            for (int i = 0; i < timeForSchedule.length; i++) {
                if (timeForSchedule[0][i]==null){
                    if (controller2==0){
                        timeForSchedule[0][i] = day+vc.getName();
                        numVaccinesAvailable[0][i] = day+vc.getName();
                        controller1++;
                        int a = 1;
                        for (Date c : dias) {
                            timeForSchedule[a][i] = dateFormat.format(c);
                            numVaccinesAvailable[a][i] = Integer.toString(vc.getMaxVaccinePerSlot());
                            aux1 = i;
                            a++;
                        }
                        controller2++;
                    }
                }
            }
        }
        int printliner = 1;
        for (Date c : dias) {
            options.add(new MenuItem(timeForSchedule[printliner][aux1] + " " + numVaccinesAvailable[printliner][aux1], new ShowTextUI("You have chosen" + dateFormat.format(c))));
            printliner++;
        }
        int option = 0;
        do
        {option = Utils.showAndSelectIndex(options, "\n\nHours Available:");
            if ( (option >= 0) && (option < options.size() && numVaccinesAvailable[option+1][aux1].compareTo("0")!=0)) {
                if(validateVaccinationSchedule(new SnsUserVaccineSchedule(SnsUserNumber,vc,vt,day,timeForSchedule[option+1][aux1]))) {
                    numVaccinesAvailable[option + 1][aux1] = Integer.toString(Integer.parseInt(numVaccinesAvailable[option + 1][aux1]) - 1);
                }
                ToFile(timeForSchedule, numVaccinesAvailable);
                return timeForSchedule[option+1][aux1];
            }
        }
        while (option != -1 );
        return timeForSchedule[option+1][aux1];
    }

    private void ToFile(String[][] timeForSchedule, String[][] numVaccinesAvailable) throws IOException {
        FileWriter writer = new FileWriter("output.txt");
        for (int i = 0; i < 150; i++) {
            for (int j = 0; j < 150; j++) {
                if(timeForSchedule[i][j]!=null){
                    writer.write(timeForSchedule[i][j] + "-" + numVaccinesAvailable[i][j] + " | ");
                }
            }
            writer.write(System.lineSeparator());
        }
        writer.close();
    }

    public boolean validateUserScheduleBySnsNumber (int snsNumber) {
        for (SnsUserVaccineSchedule schedule : SnsUserVaccineShcheduleList) {
            if (snsNumber == schedule.getSnsUserNumber()){
                return true;
            }
        }
        return false;
    }

}