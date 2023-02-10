package app.domain.model;

public class VaccineAdministration {

    /**
     * All variables that the VaccineAdministration class needs.
     */
    private String dosage;
    private int minAgeGroup;
    private int maxAgeGroup;
    private int numberOfDoses;

    /**
     * Constructor for the VaccineAdministration class,
     * creates the administration data for a new vaccine with the following attributes:
     * @param dosage
     * @param minAgeGroup
     * @param maxAgeGroup
     * @param numberOfDoses
     */

    public VaccineAdministration(String dosage, int minAgeGroup, int maxAgeGroup, int numberOfDoses) {
        this.dosage = dosage;
        this.minAgeGroup = minAgeGroup;
        this.maxAgeGroup = maxAgeGroup;
        this.numberOfDoses = numberOfDoses;
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
                '}';
    }

    public String getDosage(){
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

}
