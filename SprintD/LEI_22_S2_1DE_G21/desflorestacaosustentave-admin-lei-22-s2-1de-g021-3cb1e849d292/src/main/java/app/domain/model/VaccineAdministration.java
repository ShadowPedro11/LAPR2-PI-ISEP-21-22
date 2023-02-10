package app.domain.model;

import java.io.Serializable;

public class VaccineAdministration implements Serializable {

    /**
     * All variables that the VaccineAdministration class needs.
     */
    private int dosage;
    private int minAgeGroup;
    private int maxAgeGroup;
    private int numberOfDoses;
    private int timeIntervaling;

    /**
     * Constructor for the VaccineAdministration class,
     * creates the administration data for a new vaccine with the following attributes:
     * @param dosage
     * @param minAgeGroup
     * @param maxAgeGroup
     * @param numberOfDoses
     */

    public VaccineAdministration(int dosage, int minAgeGroup, int maxAgeGroup, int numberOfDoses, int timeIntervaling) {
        this.dosage = dosage;
        this.minAgeGroup = minAgeGroup;
        this.maxAgeGroup = maxAgeGroup;
        this.numberOfDoses = numberOfDoses;
        this.timeIntervaling = timeIntervaling;
    }

    /**
     * This method prints all information about the administration data.
     */

    public String toString() {
        return "Vaccine administration data{'" +
                ", dosage='" + dosage + '\'' +
                ", age group= from'" + minAgeGroup + '\'' +
                ", to'" + maxAgeGroup + '\'' +
                ", number of doses='" + numberOfDoses + '\'' +
                ", time intervaling='" + timeIntervaling + '\'' +
                '}';
    }

    public int getDosage(){
        return dosage;
    }

    public int getMinAgeGroup(){
        return minAgeGroup;
    }

    public int getMaxAgeGroup(){
        return maxAgeGroup;
    }

    public int getNumberOfDoses(){
        return numberOfDoses;
    }

    public int getTimeIntervaling(){
        return timeIntervaling;
    }

}
