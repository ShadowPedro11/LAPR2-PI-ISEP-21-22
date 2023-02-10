package app.ui.console;

import app.controller.CheckWaitingRoomController;
import app.domain.dto.SNSUserDto;
import app.domain.model.VaccinationCenter.VaccinationCenter;

import java.util.List;

public class CheckWaitingRoomUI implements Runnable{
    private VaccinationCenter currentVC = NurseUI.currentVC;
    CheckWaitingRoomController checkWaitingRoomController = new CheckWaitingRoomController();

    /**
     * This method is responsible for the communication with the user.
     */
    @Override
    public void run() {
        List<SNSUserDto> waitingRoom = checkWaitingRoomController.getWaitingRoom(currentVC);
        if (waitingRoom.size() != 0) {
            System.out.println("\n##Waiting Room##\n" + "\n##User's info##\n");
            for (SNSUserDto snsUserDto : waitingRoom) {
                System.out.println("Name: " + checkWaitingRoomController.getSNSUserName(snsUserDto) + "\n" +
                        "Sex: " + checkWaitingRoomController.getSNSUserSex(snsUserDto) + "\n" +
                        "Birth date: " + checkWaitingRoomController.getSNSUserBirthDate(snsUserDto) + "\n" +
                        "SNS number: " + checkWaitingRoomController.getSNSUserNumber(snsUserDto) + "\n" +
                        "Phone number: " + checkWaitingRoomController.getSNSUserPhoneNumber(snsUserDto));
            }
        }else{
            System.out.println("\nThere are no users on the waiting room.");
        }
    }
}