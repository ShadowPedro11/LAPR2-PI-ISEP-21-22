package app.domain.model;

import app.domain.dto.SnsUserVaccineScheduleDTO;
import app.domain.model.VaccinationCenter.VaccinationCenter;

import java.io.Serializable;
import java.util.Set;


/**
 * Represents a Sns User Vaccine Schedule through Sns User number, Vaccination center, Vaccine Type, Day, Hour
 */
public class SnsUserVaccineSchedule implements Serializable {
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

    /** Builds a Sns User Vaccine Schedule instance
     * @param snsUserNumber
     * @param vaccinationCenter
     * @param vaccineType
     * @param day
     * @param hour
     */
    public SnsUserVaccineSchedule(int snsUserNumber, VaccinationCenter vaccinationCenter, VaccineType vaccineType, String day, String hour) {
        this.snsUserNumber = snsUserNumber;
        this.vaccinationCenter = vaccinationCenter;
        this.vaccineType = vaccineType;
        this.day = day;
        this.hour = hour;
    }

    public int getSnsUserNumber() {
        return snsUserNumber;
    }

    /**
     * @return SnsUserVaccineSchedule String whit SnsUserNumber, Vaccination Center name, Vaccine Type description, day and hour
     */
    public String toString() {
        return "VacinneSchedule{" +
                "SnsUser Number='" + snsUserNumber + '\'' +
                ", Vaccination Center='" + vaccinationCenter.getName() + '\'' +
                ", Vaccine Type='" + vaccineType.getDescription() + '\'' +
                ", Day='" + day + '\'' +
                ", Hour='" + hour + '\'' +
                '}'+"\n";

    }

    /**
     * @return instance of new Sns User Vaccine Schedule DTO
     */
    public SnsUserVaccineScheduleDTO toDto() {
        return new SnsUserVaccineScheduleDTO(snsUserNumber, vaccinationCenter, vaccineType, day, hour);
    }

}
