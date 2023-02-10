package app.controller;

import app.domain.model.Company;
import app.domain.model.SnsUserVaccineSchedule;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.store.SNSUserStore;
import app.domain.store.VaccineScheduleStore;

import java.io.IOException;
import java.util.List;

public class ScheduleVaccinationReceptionistController {

    private Company company;
    private VaccineScheduleStore store;

    private SNSUserStore userStore;

    private SnsUserVaccineSchedule vaccineSchedule;

    public ScheduleVaccinationReceptionistController () {
        company = App.getInstance().getCompany();
        store = App.getInstance().getCompany().getVaccineScheduleStore();
        userStore = App.getInstance().getCompany().getSNSUserStore();
        vaccineSchedule = null;
    }

    public List<VaccineType> getVaccineTypes() {
        return company.getVaccineTypes();
    }

    public boolean createVaccinationSchedule(int snsUserNumber, VaccinationCenter vc, VaccineType vt, String day, String hr) {
        this.vaccineSchedule = this.store.createVaccinationSchedule(snsUserNumber, vc, vt, day, hr);
        return this.store.validateVaccinationSchedule(vaccineSchedule);
    }

    public void chekDayDateRules(String day){
        this.store.chekDayDateRules(day);
    }

    public String readHour(int snsUserNumber,VaccinationCenter vaccinationCenter, VaccineType vaccineType, String day) throws IOException {
        return this.store.readHour(snsUserNumber,vaccinationCenter,vaccineType,day);
    }

    public boolean saveVaccinationSchedule() {
        return this.store.saveVaccinationSchedule(vaccineSchedule);
    }

    public void sendMessage(int snsUserNumber, VaccinationCenter vc, VaccineType vt, String day, String hr){
        this.store.sendMessage(snsUserNumber,vc,vt,day,hr);
    }

    public boolean validateSNSUser(int snsNumber) {
        if (userStore.getUserBySNSNumber(snsNumber) == null) {
            return false;
        }
        return true;
    }

}
