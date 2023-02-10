package app.domain.model.employee;

import app.domain.model.Password;

import java.io.Serializable;

public class Nurse extends Employee implements Serializable {

    public Nurse (String name, String address, int phoneNumber, String email, int citizenCardNumber) {
        super(name, address, phoneNumber, email, citizenCardNumber);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
