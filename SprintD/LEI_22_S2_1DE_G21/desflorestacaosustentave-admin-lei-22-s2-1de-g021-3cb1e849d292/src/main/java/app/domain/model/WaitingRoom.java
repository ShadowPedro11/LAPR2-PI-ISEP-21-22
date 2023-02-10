package app.domain.model;

import app.controller.App;
import app.domain.model.VaccinationCenter.VaccinationCenter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WaitingRoom implements Serializable {
    /**
     * isInWaitingRoom is the ArrayList that stores all the users that are currently at a waiting room.
     */
    private List<SNSUser> isInWaitingRoom = new ArrayList<>();

    private List<SNSUser> wasInWaitingRoom = new ArrayList<>();
    private List<LocalDate> arrivalDate = new ArrayList<>();
    private List<LocalTime> arrivalTime = new ArrayList<>();


    /**
     * This method adds a user that is ready to take the vaccine to the waiting room of the correct vaccination center
     * @param user
     * @return
     */
    public boolean addToWaitingRoom(SNSUser user) {
        wasInWaitingRoom.add(user);
        arrivalDate.add(LocalDate.now());
        arrivalTime.add(LocalTime.now());
        return isInWaitingRoom.add(user);
    }

    /**
     * This method will add a user to the waiting room registers.
     * @param user who were in the waiting room.
     * @param date when the user entered.
     * @param time when the user entered.
     */
    public void addToWaitingRoom(SNSUser user, LocalDate date, LocalTime time) {
        wasInWaitingRoom.add(user);
        arrivalDate.add(date);
        arrivalTime.add(time);
    }

    /**
     * This method goes to the ArrayList that contains all the users that are currently at a waiting room and selects the ones that are
     * on the waiting room of the current session's vaccination center.
     * @return ArrayList with the selected users.
     */
    public List<SNSUser> getWaitingRoom(){
        return isInWaitingRoom;
    }

    public List<SNSUser> getWasInWaitingRoom(){
        return wasInWaitingRoom;
    }


    /**
     * This method checks if the vaccination center that the user scheduled its vaccine is the same as the one that he has arrived
     * @param vaccinationCenter
     * @param snsUserNumber
     * @return
     */
    public boolean checkVaccinationCenter(VaccinationCenter vaccinationCenter, int snsUserNumber){
        for (SnsUserVaccineSchedule c : App.getInstance().getCompany().getVaccineScheduleStore().getSnsUserVaccineShcheduleList()) {
            if (c.getSnsUserNumber()==snsUserNumber)
                if (c.toDto().getVaccinationCenter().getName().compareTo(vaccinationCenter.getName()) == 0) {
                    return true;
                }
        }
        return false;
    }

    public boolean isInWaitingRoom(SNSUser user) {
        for (SNSUser u: isInWaitingRoom){
            if(u.equals(user)){
                return true;
            }
        }
        return false;
    }

    public boolean isInWaitingRoom (int snsUserNumber){
        for (SNSUser u: isInWaitingRoom){
            if(u.getSnsNumber()== snsUserNumber){
                return true;
            }
        }
        return false;
    }

    /**
     * This method will remove a user that is no longer in the waiting room therefore passing to the vaccination event and finally pass to the recovery room.
     * @param user that will get vaccinated and will pass to the recovery room.
     * @return true if, and only if, the user is successfully removed from the waiting room.
     */
    public void removeFromWaitingRoom(SNSUser user) {
        int index = isInWaitingRoom.indexOf(user);
        isInWaitingRoom.remove(index);
    }

    /**
     * This mehthod will check if a user entered the vaccination center in a specific date
     * @param date which we want to test
     * @return true if the vaccination center received a user that day
     */
    public boolean checkIfAnyoneEntered(LocalDate date) {
        for (LocalDate dateRegister : arrivalDate) {
            if (dateRegister.isEqual(date))
                return true;
        }
        return false;
    }

    public List<LocalDate> getArrivalDate() {
        return arrivalDate;
    }

    /**
     * @return returns a list of arrival times.
     */
    public List<LocalTime> getArrivalTime() {
        return arrivalTime;
    }

    /**
     * This method will search for the arrival time of the users who entered the vaccination date on a specific date.
     * @param date that we want to get the arrival time list
     * @return The list with the arrival time of a specific date.
     */
    public List<LocalTime> getArrivalTime(LocalDate date) {
        List<LocalTime> arrivalTimeOfADay = new ArrayList<>();
        for (int i = 0; i < arrivalTime.size(); i++) {
            if (arrivalDate.get(i).isEqual(date)) {
                arrivalTimeOfADay.add(arrivalTime.get(i));
            }
        }
        return arrivalTimeOfADay;
    }
}
