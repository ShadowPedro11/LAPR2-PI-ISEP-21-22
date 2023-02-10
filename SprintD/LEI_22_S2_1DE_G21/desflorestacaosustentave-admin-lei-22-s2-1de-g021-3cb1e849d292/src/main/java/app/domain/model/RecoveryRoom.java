package app.domain.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RecoveryRoom implements Serializable {

    /**
     * List containing all users in recovery room.
     */
    private List<SNSUser> usersInRecoveryRoom = new ArrayList<>();

    private List<SNSUser> wasInRecoveryRoom = new ArrayList<>();
    private List<LocalDate> leavingDate = new ArrayList<>();
    private List<LocalTime> leavingTime = new ArrayList<>();


    /**
     * This method will add the SNS user, who already were vaccinated, to the recovery room.
     *
     * @return true if, and only if, the user is successfully removed from the waiting room and added to the recovery room.
     */
    public boolean addToRecoveryRoom(SNSUser user) {
        wasInRecoveryRoom.add(user);
        return usersInRecoveryRoom.add(user);
    }

    /**
     * this method will remove an SNS user after the vaccination recovery time is over.
     *
     * @return true if, and only if, the removal of the user is completed successfully.
     */
    public void removeFromRecoveryRoom(SNSUser user) {
        leavingTime.add(LocalTime.now());
        leavingDate.add(LocalDate.now());
        int index = usersInRecoveryRoom.indexOf(user);
        usersInRecoveryRoom.remove(index);
    }

    /**
     * This method will add a user to the recovery room registers.
     * @param user who entered the recovery room.
     * @param date when the user entered the vaccination center.
     * @param time when the user left the vaccination ccenter.
     */
    public void addToRecoveryRoomRegisters(SNSUser user, LocalDate date, LocalTime time) {
        wasInRecoveryRoom.add(user);
        leavingDate.add(date);
        leavingTime.add(time);
    }

    /**
     * This method will add the leaving time from the vaccination center of the user.
     * @param leavingTime when the user left
     * @return true if the leaving time was successfully added.
     */
    public boolean addLeavingTime(LocalTime leavingTime) {
        return addLeavingTime(leavingTime);
    }

    /**
     * @return the List of leaving time of the users who were in the recovery room.
     */
    public List<LocalTime> getLeavingTime() {
        return leavingTime;
    }

    /**
     * This method will search for the leaving time of the users who left the vaccination date on a specific date.
     * @param date that we want to get the leaving time list
     * @return The list with the leaving time of a specific date.
     */
    public List<LocalTime> getLeavingTime(LocalDate date) {
        List<LocalTime> leavingTimeOfADay = new ArrayList<>();
        for (int i = 0; i < leavingTime.size(); i++) {
            if (leavingDate.get(i).isEqual(date)) {
                leavingTimeOfADay.add(leavingTime.get(i));
            }
        }
        return leavingTimeOfADay;
    }

    public List<SNSUser> getWasInRecoveryRoom() {return  wasInRecoveryRoom;}

    public List<LocalDate> getLeavingDate(){return leavingDate;}
}
