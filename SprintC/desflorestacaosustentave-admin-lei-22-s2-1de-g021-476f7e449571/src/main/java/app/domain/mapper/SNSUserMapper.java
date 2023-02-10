package app.domain.mapper;

import app.domain.dto.SNSUserDto;
import app.domain.model.SNSUser;

public class SNSUserMapper {

    public static SNSUser toModel (SNSUserDto userDto) {
        SNSUser newUser = new SNSUser(userDto.getName(), userDto.getSex(), userDto.getBirthDate(), userDto.getAddress(), userDto.getPhoneNumber(), userDto.getEmail(), userDto.getSnsNumber(), userDto.getCitizenCardNumber());
        return newUser;
    }

    public static SNSUserDto toDto (SNSUser user) {
        SNSUserDto newUser = new SNSUserDto(user.getName(), user.getSex(), user.getBirthDate(), user.getAddress(), user.getPhoneNumber(), user.getEmail(), user.getSnsNumber(), user.getCitizenCardNumber());
        return newUser;
    }

}
