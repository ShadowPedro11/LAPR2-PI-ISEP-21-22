package app.domain.dto;

import java.io.Serializable;

public class SNSUserDto implements Serializable {

    private String name;
    private String sex;
    private String birthDate;
    private String address;
    private int phoneNumber;
    private String email;
    private int snsNumber;
    private int citizenCardNumber;

    public SNSUserDto (String name, String sex, String birthDate, String address, int phoneNumber, String email, int snsNumber, int citizenCardNumber) {
        checkRuleName(name);
        checkRuleGender(sex);
        checkRuleBirthDate(birthDate);
        checkRuleAddress(address);
        checkRulePhoneNumber(phoneNumber);
        checkRuleEmail(email);
        checkRuleSnsNumber(snsNumber);
        checkRuleCitizenCardNumber(citizenCardNumber);
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.snsNumber = snsNumber;
        this.citizenCardNumber = citizenCardNumber;
    }

    private void checkRuleCitizenCardNumber(int citizenCardNumber) {
        if (citizenCardNumber == 0)
            throw new IllegalArgumentException("Null citizen card number");
    }

    private void checkRuleSnsNumber(int snsNumber) {
        if (snsNumber == 0)
            throw new IllegalArgumentException("Null sns number");
    }

    private void checkRuleEmail(String email) {
        if (email == null)
            throw new IllegalArgumentException("Null e-mail");
    }

    private void checkRulePhoneNumber(int phoneNumber) {
        if (phoneNumber == 0)
            throw new IllegalArgumentException("Null phone number");
    }

    private void checkRuleAddress(String address) {
        if (address == null)
            throw new IllegalArgumentException("Null address");
    }

    private void checkRuleBirthDate(String birthDate) {
        if (birthDate == null)
            throw new IllegalArgumentException("Null birth date");
    }

    private void checkRuleGender(String gender) {
        if (gender == null)
            throw new IllegalArgumentException("Null gender");
    }

    private void checkRuleName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Null name");
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getSnsNumber() {
        return snsNumber;
    }

    public int getCitizenCardNumber() {
        return citizenCardNumber;
    }

}
