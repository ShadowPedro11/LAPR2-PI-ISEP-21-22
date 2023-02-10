package app.domain.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

public class SNSUser implements Serializable {

    private String name;
    private String sex;
    private String birthDate;
    private String address;
    private int phoneNumber;
    private String email;
    private int snsNumber;
    private int citizenCardNumber;
    private String password;
    private ArrayList<Vaccine> vaccineList = new ArrayList<>();

    public SNSUser (String name, String gender, String birthDate, String address, int phoneNumber, String email, int snsNumber, int citizenCardNumber) {
        this.name = name;
        this.sex = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.snsNumber = snsNumber;
        this.citizenCardNumber = citizenCardNumber;
        this.password = Password.generatePassword();
    }

    public long getAge() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar actual = Calendar.getInstance();
        Calendar wanted = Calendar.getInstance();
        String[] dateSplit = this.birthDate.split("/");
        int day = Integer.parseInt(dateSplit[0]);
        int month = Integer.parseInt(dateSplit[1]);
        int year = Integer.parseInt(dateSplit[2]);
        wanted.set(Calendar.DAY_OF_MONTH, day);
        wanted.set(Calendar.MONTH, month - 1);
        wanted.set(Calendar.YEAR, year);
        long days = Duration.between(wanted.toInstant(), actual.toInstant()).toDays();
        return days / 365;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setSnsNumber(int snsNumber) {
        this.snsNumber = snsNumber;
    }

    public void setCitizenCardNumber(int citizenCardNumber) {
        this.citizenCardNumber = citizenCardNumber;
    }

    @Override
    public String toString() {
        return "User info:\n" +
                "Name: " + name + "\n" +
                "Sex: " + sex + "\n" +
                "Birth date: " + birthDate + "\n" +
                "Address: " + address + "\n" +
                "Phone number: " + phoneNumber + "\n" +
                "E-mail: " + email + "\n" +
                "SNS number: " + snsNumber + "\n" +
                "Citizen card number: " + citizenCardNumber + "\n";
    }

    public void addVaccine (Vaccine vaccine) {
        vaccineList.add(vaccine);
    }

    public ArrayList<Vaccine> getVaccineList () {
        return this.vaccineList;
    }

    public String getPassword() {
        return this.password;
    }
}
