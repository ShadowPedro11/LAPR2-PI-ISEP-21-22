package app.domain.model;
import app.domain.dto.VaccinationEventDTO;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class VaccinationEvent implements Comparable<VaccinationEvent>, Serializable {

    private SnsUserVaccineSchedule SnsUserSchedule;
    private int snsUserNumber;
    private String vaccineName;
    private VaccineType vaccineType;
    private String vaccineLotNumber;
    private int doseNumber;

    private AdverseReactions adverseReactions;


    public VaccinationEvent( int snsUserNumber, String vaccineName, VaccineType vaccineType, String vaccineLotNumber, int doseNumber,SnsUserVaccineSchedule SnsUserSchedule){
        //String can be concluded or State XX
        this.doseNumber = doseNumber;
        this.vaccineName = vaccineName;
        this.vaccineType = vaccineType;
        this.snsUserNumber = snsUserNumber;
        this.vaccineLotNumber = vaccineLotNumber;
        this.adverseReactions = new AdverseReactions();

        this.SnsUserSchedule=SnsUserSchedule;
    }

    public VaccinationEventDTO toDto() {
        return new VaccinationEventDTO(  snsUserNumber,  vaccineName,  vaccineType,  vaccineLotNumber,  doseNumber,SnsUserSchedule);
    }

    @Override
    public int compareTo(VaccinationEvent o) {
        SimpleDateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy");
        try {
            Date date1 = dateFormat.parse(this.toDto().getSnsUserSchedule().toDto().getDay());
            Date date2 = dateFormat.parse(o.toDto().getSnsUserSchedule().toDto().getDay());
            if (date1.equals(date2)){
                return 0;
            }if(date1.after(date2)){
                return 1;
            }else {
                return -1;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getSnsUserNumber() {
        return snsUserNumber;
    }

    public void setSnsUserNumber(int snsUserNumber) {
        this.snsUserNumber = snsUserNumber;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public void setVaccineName(String vaccineName) {
        this.vaccineName = vaccineName;
    }

    public VaccineType getVaccineType() {
        return vaccineType;
    }

    public void setVaccineType(VaccineType vaccineType) {
        this.vaccineType = vaccineType;
    }

    public String getVaccineLotNumber() {
        return vaccineLotNumber;
    }

    public void setVaccineLotNumber(String vaccineLotNumber) {
        this.vaccineLotNumber = vaccineLotNumber;
    }

    public int getDoseNumber() {
        return doseNumber;
    }

    public void setDoseNumber(int doseNumber) {
        this.doseNumber = doseNumber;
    }

    public void addAdverseReactions(String adverseReactionsData) {
        this.adverseReactions.addAdverseReaction(adverseReactionsData);
    }

    public SnsUserVaccineSchedule getSnsUserSchedule() {
        return SnsUserSchedule;
    }

    public void setSnsUserSchedule(SnsUserVaccineSchedule snsUserSchedule) {
        SnsUserSchedule = snsUserSchedule;
    }

    @Override
    public String toString() {
        return "VaccinationEvent{" +
                "SnsUserSchedule=" + SnsUserSchedule +
                ", snsUserNumber=" + snsUserNumber +
                ", vaccineName='" + vaccineName + '\'' +
                ", vaccineType=" + vaccineType +
                ", vaccineLotNumber='" + vaccineLotNumber + '\'' +
                ", doseNumber=" + doseNumber +
                ", adverseReactions=" + adverseReactions +
                '}';
    }
}

