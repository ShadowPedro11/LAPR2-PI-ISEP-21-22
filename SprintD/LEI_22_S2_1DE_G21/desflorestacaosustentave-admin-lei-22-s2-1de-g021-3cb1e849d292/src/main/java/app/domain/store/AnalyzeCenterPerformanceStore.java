package app.domain.store;

import app.domain.model.RecoveryRoom;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.WaitingRoom;
import app.domain.shared.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class AnalyzeCenterPerformanceStore {

    /**
     * Number of minutes per interval.
     */
    private int minutesPerInterval;

    /**
     * Number of intervals to be analyzed through the day.
     */
    private int intervals;

    /**
     * Date for the analysis
     */
    private LocalDate date;

    /**
     * Vaccination center to analyse
     */
    private VaccinationCenter vaccinationCenter;

    /**
     * Waiting room from vaccination center
     */
    private WaitingRoom waitingRoom;

    /**
     * Recovery room from vaccination center
     */
    private RecoveryRoom recoveryRoom;

    /**
     * The sub list with maximum sum
     */
    private int[] subList;

    /**
     * Start of the sub list with maximum sum
     */
    private int subListStart;

    /**
     * End of sub list with maximum sum
     */
    private int subListEnd;

    /**
     * Array with the difference between users who entered the center and users who left
     */
    private int[] difference;

    /**
     * Constructor of hte class
     */
    public AnalyzeCenterPerformanceStore() {
    }

    /**
     * this method will set the time that each interval to be analyzed will have in minutes.
     */
    private void setIntervals(int minutesPerInterval) {
        intervals = 720 / minutesPerInterval;
    }


    /**
     * This method receives the number of minutes each interval should have, and sets the amount of intervals the analysis should have.
     *
     * @param minutes of each interval.
     */
    public void setMinutesPerInterval(int minutes) {
        minutesPerInterval = minutes;
        setIntervals(minutesPerInterval);
    }


    /**
     * This method will set the vaccination center that the analysis is going to be created and verify if the date that the user want to analyse is valid
     *
     * @param date              that we want to analyse the performance of the center
     * @param vaccinationCenter that we want to analyse the performance
     * @return true if the day selected is valid
     */
    public Boolean checkDayForAnalysis(LocalDate date, VaccinationCenter vaccinationCenter) {
        this.date = date;
        setVaccinationCenter(vaccinationCenter);
        return this.waitingRoom.checkIfAnyoneEntered(date);
    }

    /**
     * This method sets the vaccination center that is going to be analysed
     * also sets the waiting room and recovery room from the vaccination center
     *
     * @param vaccinationCenter that we will analyse
     */
    private void setVaccinationCenter(VaccinationCenter vaccinationCenter) {
        this.vaccinationCenter = vaccinationCenter;
        setWaitingRoom(vaccinationCenter.getWaitingRoom());
        setRecoveryRoom(vaccinationCenter.getRecoveryRoom());
    }

    /**
     * This method sets the waiting room of the vaccination center
     *
     * @param waitingRoom of the vacciantion center
     */
    private void setWaitingRoom(WaitingRoom waitingRoom) {
        this.waitingRoom = waitingRoom;
    }

    /**
     * This method sets the recovery room of the vaccination center
     *
     * @param recoveryRoom of the vaccination center
     */
    private void setRecoveryRoom(RecoveryRoom recoveryRoom) {
        this.recoveryRoom = recoveryRoom;
    }

    /**
     * this method will create the analysis of the selected day
     *
     * @return a sub list with the greatest sum of all sub lists of the day
     */
    public int[] createAnalysis() {
        if (chooseAlgorithm()) {
            System.out.println("bruteForce");
            return bruteForce(this.difference);
        } else {
            System.out.println("NotBruteForce");
            return kadane(this.difference);
        }
    }

    /**
     * This method will set the values for the difference array
     */
    public void createList() {
        List<LocalTime> arrivalTime = this.waitingRoom.getArrivalTime(this.date);
        List<LocalTime> leavingTime = this.recoveryRoom.getLeavingTime(this.date);
        int[] diff = saveDifferenceBetweenInAndOut(arrivalTime, leavingTime);
        this.difference = diff;
    }

    /**
     * This method will return an array were the i-th value is the difference between the number of new clients and the number of clients leaving
     *
     * @param arrivalTime of the new clients
     * @param leavingTime of the clients
     * @return an array were the i-th value is the difference between people who entered the center and people who exit the center
     */
    private int[] saveDifferenceBetweenInAndOut(List<LocalTime> arrivalTime, List<LocalTime> leavingTime) {
        int[] difference = new int[intervals];
        String openingHour = this.vaccinationCenter.getOpenHour();
        String[] timeSplit = openingHour.split(":");
        LocalTime time = LocalTime.of(Integer.parseInt(timeSplit[0]), Integer.parseInt(timeSplit[1]));

        for (int differencePos = 0; differencePos < difference.length; differencePos++) {
            LocalTime time2 = time.plusMinutes(minutesPerInterval);
            for (int i = 0; i < arrivalTime.size(); i++) {
                if (arrivalTime.get(i).isBefore(time2) && (arrivalTime.get(i).isAfter(time) || arrivalTime.get(i).equals(time))) {
                    difference[differencePos]++;
                }
                if (leavingTime.get(i).isBefore(time2) && (leavingTime.get(i).isAfter(time) || leavingTime.get(i).equals(time)))
                    difference[differencePos]--;
            }
            time = time2;
        }
        return difference;
    }

    /**
     * This method will choose the algorithm to use for the determination of the sub list with the greatest sum.
     * It will use a properties file in order to determine which algorithm to use
     * In case that the property is true the program will use a brute force algorithm, if it is false instead it will ue the kadane's algorithm
     *
     * @return true or false depending on the chosen algorithm
     */
    private boolean chooseAlgorithm() {
        Properties properties = getProperties();
        return Boolean.parseBoolean(properties.getProperty("BruteForceAlgorithm"));
    }

    /**
     * This method will read the properties file
     *
     * @return
     */
    private Properties getProperties() {
        Properties props = new Properties();

        // Add default properties and values
        props.setProperty(Constants.PARAMS_COMPANY_DESIGNATION, "DGS/SNS");


        // Read configured values
        try {
            InputStream in = new FileInputStream(Constants.PARAMS_FILENAME);
            props.load(in);
            in.close();
        } catch (IOException ex) {

        }
        return props;
    }

    /**
     * This method will convert an array of integers to a string
     *
     * @param array of integers
     * @return a string containing the values of the array of integers
     */
    public String intArrayToString(int[] array) {
        if (chooseAlgorithm())
            return "The vaccination center was more efficient between " + getTime(subListStart) + " and " + getTime(subListEnd + 1) + ".";
        else
            return "The vaccination center was more efficient between " + getTime(subListStart) + " and " + getTime(subListEnd) + ".";
    }

    /**
     * This method is used by the gui to get a list containing all the intervals to be analysed
     *
     * @return a list of string containing all the intervals in the following format: starHour "to" endHour
     */
    public List<String> getIntervalsString() {
        String openingHour = this.vaccinationCenter.getOpenHour();
        List<String> list = new ArrayList<>();
        String[] open = openingHour.split(":");
        LocalTime time = LocalTime.of(Integer.parseInt(open[0]), Integer.parseInt(open[1]));
        for (int i = 0; i < intervals; i++) {
            LocalTime time2 = time.plusMinutes(this.minutesPerInterval);
            //list.add(time.getHour() + ":" + time.getMinute() + " to " + time2.getHour() + ":" + time2.getMinute() + "       "  + this.difference[i]);
            list.add(String.format("%d:%d   to    %d:%d        %d", time.getHour(), time.getMinute(), time2.getHour(), time2.getMinute(), this.difference[i]));
            time = time2;
        }
        return list;
    }

    /**
     * This method will is meant to be used by the sting creator for the most efficient time interval of the vaccination center
     *
     * @param index of the time that we want to use,
     *              for example if there are 30 minutes by interval the time at index 0 is 8:00 (assuming that the vaccination center starts working at 8:00)
     * @return the time specified by the index
     */
    private LocalTime getTime(int index) {
        String[] split = vaccinationCenter.getOpenHour().split(":");
        LocalTime time = LocalTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]));
        for (int i = 0; i < index; i++) {
            LocalTime time1 = time.plusMinutes(minutesPerInterval);
            time = time1;
        }
        return time;
    }

    /**
     * This method is a brute force algorithm which will find and return the sub list with the greates sum
     *
     * @param array that we want to find the greatest sub list sum
     * @return the wanted sub list.
     */
    public int[] bruteForce(int[] array) {
        int maxSum = 0;
        int sum;

        for (int i = 0; i < array.length - 1; i++) {
            sum = 0;
            for (int j = i; j < array.length; j++) {
                sum += array[j];
                if (sum > maxSum) {
                    maxSum = sum;
                    this.subListStart = i;
                    this.subListEnd = j;
                }
            }
        }

        int pos = 0;
        int[] subList = new int[subListEnd - subListStart + 1];
        for (int i = subListStart; i <= this.subListEnd; i++) {
            subList[pos] = array[i];
            pos++;
        }

        this.subList = subList;

        return subList;
    }

    /**
     * This method uses the kadane's algorithm to find a sub list of a list with the greatest sum.
     *
     * @param array that we want to find the grates sub list sum
     * @return the wanted sub list.
     */
    public int[] kadane(int[] array) {
        int maxSoFar = 0;
        int maxEndingHere = 0;
        this.subListStart = 0;
        this.subListEnd = 0;
        int startMaxEndingHere = 0;

        for (int i = 0; i < array.length; ++i) {
            int elem = array[i];
            int endMaxEndingHere = i + 1;
            if (maxEndingHere + elem < 0) {
                maxEndingHere = 0;
                startMaxEndingHere = i + 1;
            } else {
                maxEndingHere += elem;
            }

            if (maxSoFar < maxEndingHere) {
                maxSoFar = maxEndingHere;
                this.subListStart = startMaxEndingHere;
                this.subListEnd = endMaxEndingHere;
            }
        }

        int[] result = Arrays.copyOfRange(array, this.subListStart, this.subListEnd);
        this.subList = result;
        return result;
    }

    /**
     * This method will crate a string of the sub list with maximum sum calculated by one of the two algorithm above
     *
     * @return a string of the sub list previously calculated
     */
    public String subListString() {
        String result = "{";
        for (int i = 0; i < this.subList.length - 1; i++) {
            result += this.subList[i] + ",";
        }
        result = result + this.subList[this.subList.length - 1] + "}";
        return result;
    }

    /**
     * This method calculates the sum of all the elements in the sub list previously calculated
     *
     * @return a string value of the total sum
     */
    public String getSum() {
        int sum = 0;
        for (int i = 0; i < this.subList.length; i++) {
            sum += this.subList[i];
        }
        return String.valueOf(sum);
    }
}
