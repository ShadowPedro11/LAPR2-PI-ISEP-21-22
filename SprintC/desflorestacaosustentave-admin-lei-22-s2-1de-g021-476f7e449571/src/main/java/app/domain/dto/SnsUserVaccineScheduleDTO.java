package app.domain.dto;

import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineType;


/**
 * Represent a SnsUserVaccineScheduleDTO through Sns User number, Vaccination center, Vaccine Type, Day, Hour
 */
public class SnsUserVaccineScheduleDTO {
    /**
     * The Sns User Number
     */
    private int snsUserNumber;
    /**
     * The Vaccination Center of Vaccine Schedule
     */
    private VaccinationCenter vaccinationCenter;
    /**
     * The Vaccine Type of Vaccine Schedule
     */
    private VaccineType vaccineType;
    /**
     * The day of Vaccine Schedule
     */
    private String day;
    /**
     * The hour of Vaccine Schedule
     */
    private String hour;

    /** Builds a Sns User Vaccine Schedule DTO instance
     * @param snsUserNumber
     * @param vaccinationCenter
     * @param vaccineType
     * @param day
     * @param hour
     */
    public SnsUserVaccineScheduleDTO(int snsUserNumber, VaccinationCenter vaccinationCenter, VaccineType vaccineType, String day, String hour) {
        this.snsUserNumber = snsUserNumber;
        this.vaccinationCenter = vaccinationCenter;
        this.vaccineType = vaccineType;
        this.day = day;
        this.hour = hour;
    }

    /**
     * @return day of Vaccine Schedule
     */
    public String getDay() {
        return day;
    }

    /**
     * @return hour of Vaccine Schedule
     */
    public String getHour() {
        return hour;
    }

    /**
     * @return Sns User Number of Vaccine Schedule
     */
    public int getSnsUserNumber() {
        return snsUserNumber;
    }

    /**
     * @return Vaccination Center of Vaccine Schedule
     */
    public VaccinationCenter getVaccinationCenter() {
        return vaccinationCenter;
    }

    /**
     * @return Vaccine Type of Vaccine Schedule
     */
    public VaccineType getVaccineType() {
        return vaccineType;
    }

}
