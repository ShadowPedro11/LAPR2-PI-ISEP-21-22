package app.controller;

import app.domain.dto.SNSUserDto;
import app.domain.mapper.SNSUserMapper;
import app.domain.model.SNSUser;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.WaitingRoom;

import java.util.ArrayList;
import java.util.List;

public class CheckWaitingRoomController {
    /**
     * This method calls the WaitingRoom's method getWaitingRoom to get the list of users that are at the waiting
     * room of a certain vaccination center. Then it converts the SNSUser's instances of the list with the selected users to SNSUserDTO.
     * @param currentVC is the vaccination center where the current Nurse that is logged in is working.
     * @return List with the selected users.
     */
    public List<SNSUserDto> getWaitingRoom(VaccinationCenter currentVC) {
        List<SNSUser> waitingRoom = WaitingRoom.getWaitingRoom(currentVC);
        List<SNSUserDto> waitingRoomDTO = new ArrayList<>();

        for (SNSUser snsUser : waitingRoom){
            waitingRoomDTO.add(SNSUserMapper.toDto(snsUser));
        }
        return waitingRoomDTO;
    }

    public String getSNSUserName(SNSUserDto snsUserDto) {
        return snsUserDto.getName();
    }

    public String getSNSUserSex(SNSUserDto snsUserDto) {
        return snsUserDto.getSex();
    }

    public String getSNSUserBirthDate(SNSUserDto snsUserDto) {
        return snsUserDto.getBirthDate();
    }

    public int getSNSUserNumber(SNSUserDto snsUserDto) {
        return snsUserDto.getSnsNumber();
    }

    public int getSNSUserPhoneNumber(SNSUserDto snsUserDto) {
        return snsUserDto.getPhoneNumber();
    }

}
