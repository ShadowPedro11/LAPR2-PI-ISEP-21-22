package app.domain.model;

public class Receptionist extends Employee{

    public Receptionist (String name, String address, int phoneNumber, String email, int citizenCardNumber) {
        super(name, address, phoneNumber, email, citizenCardNumber);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
