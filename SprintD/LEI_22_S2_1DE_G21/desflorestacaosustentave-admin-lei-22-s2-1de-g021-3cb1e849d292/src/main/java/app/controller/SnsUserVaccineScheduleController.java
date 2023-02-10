package app.controller;

import app.domain.model.Company;
import app.domain.model.SnsUserVaccineSchedule;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.VaccineType;
import app.domain.store.VaccineScheduleStore;

import java.io.IOException;
import java.util.List;

public class SnsUserVaccineScheduleController {
    private VaccineScheduleStore store;
    private Company company;
    private SnsUserVaccineSchedule vcSkd;

    public SnsUserVaccineScheduleController() {
        this(App.getInstance().getCompany());
    }

    public SnsUserVaccineScheduleController(Company company) {
        this.company = company;
        this.store = company.getVaccineScheduleStore();
        this.vcSkd = null;
    }

    public boolean createVaccinationSchedule(int snsUserNumber, VaccinationCenter vc, VaccineType vt, String day, String hr) {
        this.vcSkd = this.store.createVaccinationSchedule(snsUserNumber, vc, vt, day, hr);
        return this.store.validateVaccinationSchedule(vcSkd);
    }

    public boolean saveVaccinationSchedule() {

        return this.store.saveVaccinationSchedule(vcSkd);
    }

    public boolean checkSnsUserNumber(int snsUserNumber){
        return this.store.checkSnsUserNumber(snsUserNumber);
    }

    public void sendMessage(int snsUserNumber, VaccinationCenter vc, VaccineType vt, String day, String hr){
        this.store.sendMessage(snsUserNumber,vc,vt,day,hr);
    }

    public List<VaccinationCenter> getVaccinationCenterList() {
        return Company.getVaccinationCenterList();
    }

    public List<VaccineType> getVaccineTypes() {
        return company.getVaccineTypes();
    }

    public  String[][] getTimeForSchedule(){
        return this.store.getTimeForSchedule();
    }

    public  String[][] getNumVaccinesAvailable(){
        return this.store.getNumVaccinesAvailable();
    }

    public void chekSnsUserNumberRules(int snsUserNumber){
        this.store.chekSnsUserNumberRules(snsUserNumber);
    }

    public void chekDayDateRules(String day){
        this.store.chekDayDateRules(day);
    }

     public String readHour(int snsUserVaccineSchedule, VaccinationCenter vc, VaccineType vt, String day) throws IOException{
       return this.store.readHour(snsUserVaccineSchedule,vc,vt,day);
     }


}
