package app.domain.dto;

import app.domain.model.Company;
import app.domain.store.SNSUserStore;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataLegacySystemDto {
    private int snsNumber;
    private String vaccineName;
    private String dose;
    private String lotNumber;
    private LocalDateTime scheduledDateTime;
    private LocalDateTime arrivalDateTime;
    private LocalDateTime nurseAdministrationDateTime;
    private LocalDateTime leavingDateTime;
    public DataLegacySystemDto(int snsNumber, String vaccineName, String dose, String lotNumber, LocalDateTime scheduledDateTime,
                               LocalDateTime arrivalDateTime, LocalDateTime nurseAdministrationDateTime, LocalDateTime leavingDateTime) {
        checkRuleSnsNumber(snsNumber);
        checkVaccineName(vaccineName);
        checkDose(dose);
        checkLotNumber(lotNumber);
        checkScheduledDateTime(scheduledDateTime);
        checkArrivalDateTime(arrivalDateTime);
        checkNurseAdministrationDateTime(nurseAdministrationDateTime);
        checkLeavingDateTime(leavingDateTime);
        this.snsNumber = snsNumber;
        this.vaccineName = vaccineName;
        this.dose = dose;
        this.lotNumber = lotNumber;
        this.scheduledDateTime = scheduledDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.nurseAdministrationDateTime = nurseAdministrationDateTime;
        this.leavingDateTime = leavingDateTime;
    }

    private void checkRuleSnsNumber(int snsNumber) {
        if (snsNumber == 0)
            throw new IllegalArgumentException("Null sns number");
    }

    private void checkVaccineName(String vaccineName) {
        if (vaccineName == null)
            throw new IllegalArgumentException("Null vaccine Name");
    }

    private void checkDose(String dose) {
        if (dose == null)
            throw new IllegalArgumentException("Null dose");
    }

    private void checkLotNumber(String lotNumber) {
        if (lotNumber == null)
            throw new IllegalArgumentException("Null lot number");
    }

    private void checkScheduledDateTime(LocalDateTime scheduledDateTime) {
        if (scheduledDateTime == null)
            throw new IllegalArgumentException("Null scheduled date and time");
    }

    private void checkArrivalDateTime(LocalDateTime arrivalDateTime) {
        if (arrivalDateTime == null)
            throw new IllegalArgumentException("Null arrival time");
    }

    private void checkNurseAdministrationDateTime(LocalDateTime nurseAdministrationDateTime) {
        if (nurseAdministrationDateTime == null)
            throw new IllegalArgumentException("Null nurse administration date and time");
    }

    private void checkLeavingDateTime(LocalDateTime leavingDateTime) {
        if (leavingDateTime == null)
            throw new IllegalArgumentException("Null leaving date and time");
    }

    public int getSnsNumber() {
        return this.snsNumber;
    }

    public String getVaccineName() {
        return vaccineName;
    }

    public String getDose() {
        return dose;
    }

    public String getLotNumber() {
        return lotNumber;
    }

    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public LocalDateTime getNurseAdministrationDateTime() {
        return nurseAdministrationDateTime;
    }

    public LocalDateTime getLeavingDateTime() {
        return leavingDateTime;
    }

    public String toString(SNSUserStore snsUserStore, Company company) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return (String.format("%-50s | %-10s | %-20s | %-20s | %-10s | %-10s | %-17s | %-17s | %-17s | %-17s |\n",
                snsUserStore.getUserBySNSNumber(snsNumber).getName(),
                snsNumber,
                vaccineName,
                company.getVaccineByName(vaccineName).getType().getDescription(),
                dose,
                lotNumber,
                scheduledDateTime.format(formatter),
                arrivalDateTime.format(formatter),
                nurseAdministrationDateTime.format(formatter),
                leavingDateTime.format(formatter)));
    }

}
