package app.controller;

import app.domain.model.Company;
import app.domain.model.SNSUser;
import app.domain.model.SnsUserVaccineSchedule;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.WaitingRoom;
import app.domain.store.SNSUserStore;
import app.domain.store.VaccineScheduleStore;
import app.ui.console.UserArrivalReceptionistUI;


import java.util.List;

public class UserArrivalReceptionistController {

    Company company = App.getInstance().getCompany();
    VaccineScheduleStore vaccineScheduleStore = company.getVaccineScheduleStore();
    SNSUserStore snsUserStore = company.getSNSUserStore();
    List<SnsUserVaccineSchedule> list = vaccineScheduleStore.getSnsUserVaccineShcheduleList();
    WaitingRoom waitingRoom = company.getCurrentVC().getWaitingRoom();



    /**
     * Verifies if the user has a schedule for taking a vaccine
     * @param snsUserNumber
     * @return true if, and only if, the user has a vaccine scheduled
     */
    public boolean verifyIfUserHasSchedule(int snsUserNumber) {
        boolean schedule = false;
        for (SnsUserVaccineSchedule c : list) {
            if (c.toDto().getSnsUserNumber() == snsUserNumber) {
                schedule = true;
            }
        }
        return schedule;
    }

    /**
     * Verifies if the user exists in the list of registered SNS users
     * @param snsUserNumber
     * @return true if, and only if, the user is the user exists
     */
    public boolean verifyIfUserExists(int snsUserNumber) {
        if (snsUserStore.getUserBySNSNumber(snsUserNumber) == null) {
            return false;
        }
        return true;
    }

    /**
     * Gets the data from the scheduled vaccine of the user
     * @param snsUserNumber
     * @return the sns number, vaccination center, day and hour of the vaccine schedule of the user
     */
    public String getScheduleData(int snsUserNumber) {
        String vaccineScheduleData = "";
        for (SnsUserVaccineSchedule c : list) {
            if (c.toDto().getSnsUserNumber() == snsUserNumber) {
                vaccineScheduleData = c.toString();
            }
        }
        return vaccineScheduleData;
    }

    /**
     * Checks if the user is in the correct vaccination center to take the vaccine
     * @param vaccinationCenter
     * @param snsUserNumber
     * @return true if, and only if, the vaccination center from the schedule and the vaccination center where is making the arrival match
     */
    public boolean checkIfUserIsInCorrectVC(VaccinationCenter vaccinationCenter, int snsUserNumber) {
        return waitingRoom.checkVaccinationCenter(vaccinationCenter, snsUserNumber);
    }

    /**
     * Moves the user to a waiting room of the correct vaccination center
     * @param snsUser
     * @return true if, and only if, the data given is correct
     */
    public boolean moveUserToWaitingRoom(SNSUser snsUser) {
        return waitingRoom.addToWaitingRoom(snsUser);
    }
}