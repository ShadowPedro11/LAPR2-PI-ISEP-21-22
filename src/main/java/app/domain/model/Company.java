package app.domain.model;

import pt.isep.lei.esoft.auth.AuthFacade;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Paulo Maio <pam@isep.ipp.pt>
 */
public class Company {


    private String designation;
    private AuthFacade authFacade;

    public Company(String designation) {
        if (StringUtils.isBlank(designation))
            throw new IllegalArgumentException("Designation cannot be blank.");

        this.designation = designation;
        this.authFacade = new AuthFacade();

    }


    public String getDesignation() {
        return designation;
    }

    public AuthFacade getAuthFacade() {
        return authFacade;
    }

    //==================================================================================


    //=============================================================================José

    /**
     * Arraylist with all Employee objects.
     */
    private static ArrayList<Employee> employees = new ArrayList<>();

    /**
     * This method register new employee method using the following parameters (All paramether are mandatory):
     * @param name              of the employee
     * @param address           of the employee
     * @param phoneNumber       of the employee
     * @param email             of the employee
     * @param citizenCardNumber of the employee
     * @param role              1 for nurse, 2 for receptionist, 3 coordinator
     */
    public static Employee registerNewEmployee(String name, String address, int phoneNumber, String email, int citizenCardNumber, int role) {
        Employee employee = null;
        if (role == 1) {
            employee = registerNewNurse(name, address, phoneNumber, email, citizenCardNumber);
        } else if (role == 2) {
            employee = registerNewReceptionist(name, address, phoneNumber, email, citizenCardNumber);
        } else if (role == 3) {
            employee = registerNewCoordinator(name, address, phoneNumber, email, citizenCardNumber);
        }
        return employee;
    }

    /**
     * This method create a new nurse instance with the following parameters:
     * @param name
     * @param address
     * @param phoneNumber
     * @param email
     * @param citizenCardNumber
     * @return a new nurse instance.
     */
    private static Employee registerNewNurse(String name, String address, int phoneNumber, String email, int citizenCardNumber) {
        return new Nurse(name, address, phoneNumber, email, citizenCardNumber);
    }

    /**
     * This method create a new receptionist instance with the following parameters:
     * @param name
     * @param address
     * @param phoneNumber
     * @param email
     * @param citizenCardNumber
     * @return a new receptionist instance.
     */
    private static Employee registerNewReceptionist(String name, String address, int phoneNumber, String email, int citizenCardNumber) {
        return new Receptionist(name, address, phoneNumber, email, citizenCardNumber);
    }

    /**
     * This method create a new coordinator instance with the following parameters:
     * @param name
     * @param address
     * @param phoneNumber
     * @param email
     * @param citizenCardNumber
     * @return a new coordinator instance.
     */
    private static Employee registerNewCoordinator(String name, String address, int phoneNumber, String email, int citizenCardNumber) {
        return new CenterCoordinator(name, address, phoneNumber, email, citizenCardNumber);
    }

    /**
     * This method verifies if the employee object, that recives as a parameter is valid.
     * @return true, if and only if, the employee has no null or empty attributes.
     */
    public static boolean validateEmployee(Employee employee) {
        if (employee.getName() == null || employee.getAddress() == null || employee.getPhoneNumber() < 99999999 || employee.getPhoneNumber() > 999999999 || employee.getEmail() == null || employee.getCitizenCardNumber() < 9999999 || employee.getCitizenCardNumber() > 99999999
        || employee.getName().isBlank() || employee.getAddress().isBlank() || employee.getEmail().isBlank())
            return false;
        return !employees.contains(employee);
    }

    /**
     * This method adds the employee instance to the employees arraylist which stores
     * all of the employee instances
     * @param employee
     * @return true in case the employee was succesfully added to the arraylist.
     */
    public static boolean addEmployee(Employee employee) {
        if (validateEmployee(employee))
            return employees.add(employee);
        return false;
    }

    //===========================US12

    /**
     * Arraylist with all the vaccine types registered
     */
    private static ArrayList<VaccineType> vaccineTypes = new ArrayList<>();


    /**
     * Creates a new vaccine type with the following attributes :
     * @param code
     * @param description
     * @param technology
     * @return
     */
    public static VaccineType createNewVaccineType(String code, String description, String technology) {
        return new VaccineType(code, description, technology);
    }

    /**
     * Validates the vaccine type, checks for null or empty attributes.
     * @return true if, and only if, the vaccine type has no null or empty attributes.
     */
    public static boolean validateVaccineType(VaccineType vt) {
        if (vt.getCode() == null || vt.getDescription() == null || vt.getTechnology() == null
        || vt.getCode().isBlank() || vt.getDescription().isBlank() || vt.getTechnology().isBlank() || vt.getCode().length() != 5)
            return false;
        return !vaccineTypes.contains(vt);
    }

    /**
     * Adds the vaccine type to the vaccineType array list.
     * @return returns true if the vaccine was successfully added
     */
    public static boolean addVaccineType(VaccineType vt) {
        if (validateVaccineType(vt))
            return vaccineTypes.add(vt);
        return false;
    }

    public static ArrayList<VaccineType> getVaccineTypes() {
        return vaccineTypes;
    }

    //==============================================================================Tiago

    /**
     * This method gets an Employee instance with a certain index from the employees arraylist
     * that stores all the Employee instances.
     *
     * @param index
     * @return Employee instance
     */
    public static Employee getEmployee(int index) {
        return employees.get(index);
    }

    /**
     * This method gets the employees arraylist where all the Employee instances are stored.
     *
     * @return Arraylist with the Employee instances.
     */
    public static ArrayList<Employee> getEmployeesList() {
        return Company.employees;
    }

    /**
     * This method verifies if there are any Employee instances stored in the employees arraylist.
     *
     * @return true, in case there are Employee instances stored, or false, in case there are not Employee instances stored.
     */
    public static boolean verifyIfExistsEmployees() {
        boolean exists = true;
        if (employees.size() == 0) {
            exists = false;
        }
        return exists;
    }

    //==============================================================================Pedro

    /**
     * ArrayList with all Vaccination Centers created
     */
    public static List<VaccinationCenter> vaccinationCenterList = new ArrayList<>();

    /**
     * Create and return a new Vaccination Center
     *
     * @param name
     * @param address
     * @param phoneNumber
     * @param emailAddress
     * @param faxNumber
     * @param webSiteAddress
     * @param openHour
     * @param closeHour
     * @param slotDuration
     * @param maxVaccinePerSlot
     * @param cc
     * @return Vaccination Center Object
     */
    public VaccinationCenter createVaccinationCenter(String name, String address, String phoneNumber, String emailAddress, String faxNumber, String webSiteAddress, String openHour, String closeHour, int slotDuration, int maxVaccinePerSlot, CenterCoordinator cc) {
        return new VaccinationCenter(name, address, phoneNumber, emailAddress, faxNumber, webSiteAddress, openHour, closeHour, slotDuration, maxVaccinePerSlot, cc);
    }


    /**
     * Validate the parameters of a Vaccination Center and returns a boolean
     *
     * @param vc
     * @return boolean, valid or invalid related to VaccinationCenter parameters
     */
    public boolean validateVaccinationCenter(VaccinationCenter vc) {
        if (vc.getName() != null && vc.getAddress() != null && vc.getPhoneNumber() != null && vc.getEmailAddress() != null && vc.getFaxNumber() != null && vc.getWebSiteAddress() != null && vc.getOpenHour() != null && vc.getCloseHour() != null && vc.getMaxVaccinePerSlot() > 0 && vc.getSlotDuration() > 0 && vc.getCc() != null
                && vc.getName().compareTo("") != 0 && vc.getAddress().compareTo("") != 0 && vc.getPhoneNumber().compareTo("") != 0 && vc.getEmailAddress().compareTo("") != 0 && vc.getFaxNumber().compareTo("") != 0 && vc.getWebSiteAddress().compareTo("") != 0 && vc.getOpenHour().compareTo("") != 0 && vc.getCloseHour().compareTo("") != 0) {
            if (vc == null)
                return false;
            return !this.vaccinationCenterList.contains(vc);
        } else {
            return false;
        }
    }

    /**
     * Save a Vaccination Center in the ArrayList
     *
     * @param vc
     * @return boolean, related to save a VaccinationCenter in an ArrayList
     */
    public boolean saveVaccinationCenter(VaccinationCenter vc) {
        if (!validateVaccinationCenter(vc))
            return false;
        return this.vaccinationCenterList.add(vc);
    }


    /**
     * Return the Vaccination Center List
     *
     * @return list with all Vaccination Centers
     */
    public static List<VaccinationCenter> getVaccinationCenterList() {
        return vaccinationCenterList;
    }


    //==================================================================================

    //==============================================================================Alex

    /**
     * Arraylist with all Vaccine objects.
     */
    private static ArrayList<Vaccine> vaccines = new ArrayList<>();
    private static ArrayList<VaccineAdministration> administrations = new ArrayList<>();

    /**
     * This method creates a new vaccine method using the following parameters (All parameters are mandatory):
     *
     * @param name  of the vaccine
     * @param brand of the vaccine
     * @param type  of the vaccine
     */
    public Vaccine createNewVaccine(String name, String brand, String type) {
        return new Vaccine(name, brand, type);
    }

    /**
     * This method creates the administration process for a new vaccine using the following parameters (All parameters used are mandatory):
     *
     * @param dosage        of the vaccine
     * @param minAgeGroup   of the vaccine
     * @param maxAgeGroup   of the vaccine
     * @param numberOfDoses of the vaccine
     */
    public VaccineAdministration createVaccineAdministration(String dosage, int minAgeGroup, int maxAgeGroup, int numberOfDoses) {

        return new VaccineAdministration(dosage, minAgeGroup, maxAgeGroup, numberOfDoses);
    }

    /**
     * This method verifies if the vaccine object is valid (different from null)
     *
     * @param vaccine
     * @return true in case the vaccine is valid.
     */
    public static boolean validateVaccine(Vaccine vaccine) {
        if (vaccine.getName() != null && vaccine.getBrand() != null && vaccine.getType() != null && vaccine.getName().compareTo("") != 0 && vaccine.getBrand().compareTo("") != 0 && vaccine.getType().compareTo("") != 0) {
            if (vaccine == null)
                return false;
            return !vaccines.contains(vaccine);
        } else {
            return false;
        }
    }

    /**
     * This method verifies if the administration data is valid (different from null)
     *
     * @param vaccineAdministration
     * @return true in case the vaccineAdministration is valid.
     */

    public static boolean validateVaccineAdministration(VaccineAdministration vaccineAdministration) {
        if (vaccineAdministration.getDosage() != null && vaccineAdministration.getMinAgeGroup() > 0 && vaccineAdministration.getMaxAgeGroup() > 0 && vaccineAdministration.getNumberOfDoses() > 0 && vaccineAdministration.getDosage().compareTo("") != 0) {
            if (vaccineAdministration == null)
                return false;
            return !administrations.contains(vaccineAdministration);
        } else {
            return false;
        }
    }

    /**
     * This method adds the vaccine and vaccineAdministration instances to the vaccine and vaccineAdministration arraylists which stores
     * all the vaccine and vaccineAdministration instances
     *
     * @param vaccine
     * @param vaccineAdministration
     * @return true in case the employee was successfully added to the arraylist
     */

    public boolean addVaccine(Vaccine vaccine, VaccineAdministration vaccineAdministration) {
        if (validateVaccine(vaccine) && validateVaccineAdministration(vaccineAdministration)) {
            this.administrations.add(vaccineAdministration);
            this.vaccines.add(vaccine);
            return true;
        }
        return false;
    }
}