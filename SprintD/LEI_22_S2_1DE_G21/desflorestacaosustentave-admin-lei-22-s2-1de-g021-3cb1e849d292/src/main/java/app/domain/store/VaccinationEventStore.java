package app.domain.store;

import app.domain.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class VaccinationEventStore implements Serializable {

    private transient List<Vaccine> listOfVaccines;
    private List<VaccinationEvent> vaccinationEventList;
    private transient int snsUserNumber;
    private transient VaccineType vaccineType;
    private transient String vaccineLotNumber;
    private transient String vaccineName;
    private transient int doseNumber;
    private transient SnsUserVaccineSchedule vcSkd;
    private transient VaccinationEvent vaccinationEvent;
    private transient RecoveryRoom recoveryRoom;
    private transient SNSUserStore snsUserStore;

    public VaccinationEventStore() {
        vaccinationEventList = new ArrayList<>();
    }

    public List<VaccinationEvent> getList() {
        return vaccinationEventList;
    }

    public List<VaccinationEvent> addToList(VaccinationEvent vaccinationEvent) {
        vaccinationEventList.add(vaccinationEvent);
        return vaccinationEventList;
    }


    public void setListOfVaccines(List<Vaccine> listOfVaccines) {
        this.listOfVaccines = listOfVaccines;
    }

    public List<Vaccine> getListOfVaccines() {
        return this.listOfVaccines;
    }

    public List<String> getVaccinesNames() {
        List<String> vaccineNames = new ArrayList<>();
        for (Vaccine vaccine : this.listOfVaccines) {
            vaccineNames.add(vaccine.getName());
        }
        return vaccineNames;
    }


    public void saveDataForVaccinationEvent(int snsUserNumber, VaccineType vaccineType) {
        this.snsUserNumber = snsUserNumber;
        this.vaccineType = vaccineType;
    }

    public int getSnsUserNumber() {
        return snsUserNumber;
    }

    public void saveDataForVaccinationEvent(String vaccineLotNumberFinal, String currentVaccine, int vaccineDoseNumber) {
        this.vaccineLotNumber = vaccineLotNumberFinal;
        this.vaccineName = currentVaccine;
        this.doseNumber = vaccineDoseNumber;
    }

    public void saveSnsUserVaccineSchedule(SnsUserVaccineSchedule vcSkd) {
        this.vcSkd = vcSkd;
    }

    public boolean addVaccineEvent() {
        return vaccinationEventList.add(new VaccinationEvent(this.snsUserNumber, this.vaccineName, this.vaccineType, this.vaccineLotNumber, this.doseNumber, this.vcSkd));
    }

    public boolean checkIfUserHasEvent(int snsUserNumber) {
        for (VaccinationEvent u : vaccinationEventList) {
            if (u.getSnsUserNumber() == snsUserNumber) {
                return true;
            }
        }
        return false;
    }

    public VaccinationEvent getVaccinationEvent(int snsUserNumber) {
        for (VaccinationEvent u : vaccinationEventList) {
            if (u.getSnsUserNumber() == snsUserNumber) {
                return u;
            }
        }
        return null;
    }

    public void timer(int snsUserNumber) {
        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                sendMessage(snsUserNumber);
                recoveryRoom.removeFromRecoveryRoom(snsUserStore.getUserBySNSNumber(snsUserNumber));
            }
        };
        timer.schedule(timerTask, 1800000);
    }

    public VaccinationEvent getVaccinationEvent() {
        return this.vaccinationEvent;
    }

    public int compareVaccineDose(int snsUserNumber, String vaccineName) {
        VaccineType vaccineTypeOfUser = getVaccineTypeOfUser(vaccineName);
        int vaccineDoseNumber = 1;
        for (VaccinationEvent u : vaccinationEventList) {
            if (u.getSnsUserNumber() == snsUserNumber && u.getVaccineType().equals(vaccineTypeOfUser)) {
                vaccineDoseNumber++;
            }
        }
        return vaccineDoseNumber;
    }

    public VaccineType getVaccineTypeOfUser(String vaccineName) {
        for (Vaccine c : listOfVaccines)
            if (c.getName().equals(vaccineName)) {
                return c.getType();
            }
        return null;
    }

    public void sendMessage(int snsUserNumber) {
        FileWriter fw = null;
        try {
            fw = new FileWriter("sms.txt", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bw = new BufferedWriter(fw);
        PrintWriter pw = new PrintWriter(bw);

        pw.write("The user" + snsUserNumber + "can now leave the recovery room");
        pw.close();
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
