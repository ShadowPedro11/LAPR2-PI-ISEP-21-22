package app.domain.model;

public class Nurse extends Employee {

    public Nurse (String name, String address, int phoneNumber, String email, int citizenCardNumber) {
        super(name, address, phoneNumber, email, citizenCardNumber);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
