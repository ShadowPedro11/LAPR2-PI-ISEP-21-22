package app.domain.dto;

import app.domain.model.AdverseReactions;
import app.domain.model.SnsUserVaccineSchedule;
import app.domain.model.Vaccine;
import app.domain.model.VaccineType;

public class VaccinationEventDTO {

    private SnsUserVaccineSchedule SnsUserSchedule;
    private String State;
    private Vaccine vaccine;
    private int snsUserNumber;
    private String vaccineName;
    private VaccineType vaccineType;
    private String vaccineLotNumber;
    private int doseNumber;

    private AdverseReactions adverseReactions;

    public VaccinationEventDTO(int snsUserNumber, String vaccineName, VaccineType vaccineType, String vaccineLotNumber, int doseNumber, SnsUserVaccineSchedule SnsUserSchedule){
        //String can be concluded or State XX
        this.doseNumber = doseNumber;
        this.vaccineLotNumber = vaccineLotNumber;
        this.vaccineName = vaccineName;
        this.vaccineType = vaccineType;
        this.snsUserNumber = snsUserNumber;
        this.adverseReactions = new AdverseReactions();
        this.SnsUserSchedule=SnsUserSchedule;
    }

    public int getDoseNumber() {
        return doseNumber;
    }

    public String getVaccineLotNumber() {
        return vaccineLotNumber;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public int getSnsUserNumber() {
        return snsUserNumber;
    }

    public SnsUserVaccineSchedule getSnsUserSchedule() {
        return SnsUserSchedule;
    }



}
