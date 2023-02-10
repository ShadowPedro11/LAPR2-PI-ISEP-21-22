package app.domain.model.employee;

import app.controller.App;
import app.domain.model.Password;
import app.domain.shared.Constants;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.Serializable;

public abstract class Employee implements Serializable {


    /**
     * All variables that the Employee class neds.
     */
    //private Id (automatic)
    private String name;
    private String address;
    private int phoneNumber;
    private String email;
    private int citizenCardNumber;
    private String password;

    /**
     * Constructor for the Employee class,
     * creates a new employee with the following atributes:
     * @param name
     * @param address
     * @param phoneNumber
     * @param email
     * @param citizenCardNumber
     */
    public Employee(String name, String address, int phoneNumber, String email, int citizenCardNumber) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.citizenCardNumber = citizenCardNumber;
        this.password = Password.generatePassword();
    }

    /**
     * This method prints all information about the employee.
     */
    @Override
    public String toString() {
        return "name: " + name + "\n" +
                "address: " + address + '\n' +
                "phoneNumber: " + phoneNumber + '\n' +
                "email: " + email + '\n' +
                "citizenCardNumber: " + citizenCardNumber + '\n';
    }

    public String getName() {
        return name;
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

    public int getCitizenCardNumber() {
        return citizenCardNumber;
    }

    public String getPassword() {
        return password;
    }
}