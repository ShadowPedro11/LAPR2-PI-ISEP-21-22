package app.controller;

import app.domain.model.Company;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.ui.console.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class ChoseVaccinationCenterController {

    public ArrayList<VaccinationCenter> choseVaccinationCenter(){
        ArrayList<VaccinationCenter> vt = new ArrayList<>();
        for (VaccinationCenter vc : Company.getVaccinationCenterList()){
            vt.add(vc);
        }
        return vt;
    }

    public String getNameVaccinationCenter(VaccinationCenter vaccinationCenter){
        return vaccinationCenter.getName();
    }
}
