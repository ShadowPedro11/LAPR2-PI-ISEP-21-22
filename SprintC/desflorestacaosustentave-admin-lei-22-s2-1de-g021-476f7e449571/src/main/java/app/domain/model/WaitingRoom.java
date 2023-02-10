package app.domain.model;

import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.store.VaccineScheduleStore;

import java.util.ArrayList;
import java.util.List;

public class WaitingRoom {
    /**
     * isInWaitingRoom is the ArrayList that stores all the users that are currently at a waiting room.
     */
    private static ArrayList<SNSUser> isInWaitingRoom = new ArrayList<>();
    /**
     * whichCenterTheUserIsON is the ArrayList that stores the vaccination centers where the users arrived and are currently
     * waiting at the waiting room.
     */
    private static ArrayList<VaccinationCenter> whichCenterTheUserIsON = new ArrayList<>();
    private static List<SnsUserVaccineSchedule> snsUserVaccineShcheduleList = new VaccineScheduleStore().getSnsUserVaccineShcheduleList();

    /**
     * This method adds a user that is ready to take the vaccine to the waiting room of the correct vaccination center
     * @param vaccinationCenter
     * @param user
     * @return
     */
    public static boolean addToWaitingRoom(VaccinationCenter vaccinationCenter, SNSUser user) {
        whichCenterTheUserIsON.add(vaccinationCenter);
        return isInWaitingRoom.add(user);
    }

    /**
     * This method goes to the ArrayList that contains all the users that are currently at a waiting room and selects the ones that are
     * on the waiting room of the current session's vaccination center.
     *
     * @param currentVC is the vaccination center where the current Nurse that is logged in is working.
     * @return ArrayList with the selected users.
     */
    public static ArrayList<SNSUser> getWaitingRoom(VaccinationCenter currentVC){
        ArrayList<SNSUser> waitingRoom = new ArrayList<>();
        int counter = 0;
        for (VaccinationCenter vc : whichCenterTheUserIsON){
            if (vc.equals(currentVC)){
                waitingRoom.add(isInWaitingRoom.get(counter));
            }
            counter++;
        }
        return waitingRoom;
    }

    /**
     * This method checks if the vaccination center that the user scheduled its vaccine is the same as the one that he has arrived
     * @param vaccinationCenter
     * @param snsUserNumber
     * @return
     */
    public boolean checkVaccinationCenter(VaccinationCenter vaccinationCenter, int snsUserNumber){
        for (SnsUserVaccineSchedule c : snsUserVaccineShcheduleList) {
            if (c.getSnsUserNumber()==snsUserNumber)
                if (c.toDto().getVaccinationCenter().getName().compareTo(vaccinationCenter.getName()) == 0) {
                    return true;
                }
        }
        return false;
    }
}
