package app.domain.model.employee;

import java.io.Serializable;

public class Receptionist extends Employee implements Serializable {

    public Receptionist (String name, String address, int phoneNumber, String email, int citizenCardNumber) {
        super(name, address, phoneNumber, email, citizenCardNumber);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}

