package app.domain;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.Vaccine;
import app.domain.model.VaccineAdministration;
import app.domain.model.VaccineType;
import app.domain.model.employee.Employee;
import app.domain.shared.Files;
import app.domain.store.SNSUserStore;
import app.domain.store.VaccineScheduleStore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Saver {

    private static ArrayList<Employee> employeesList = App.getInstance().getCompany().getEmployees();
    private static SNSUserStore userStore = App.getInstance().getCompany().getSNSUserStore();
    private static ArrayList<VaccineType> vaccineTypes = App.getInstance().getCompany().getVaccineTypes();
    private static List<VaccinationCenter> vaccinationCenterList = Company.getVaccinationCenterList();
    private static VaccineScheduleStore vaccineScheduleStore = App.getInstance().getCompany().getVaccineScheduleStore();
    private static ArrayList<Vaccine> vaccines = App.getInstance().getCompany().getVaccineList();
    private static ArrayList<VaccineAdministration> vaccineAdministrations = App.getInstance().getCompany().getAdministrations();
    public static void saveData() {
        saveEmployees();
        saveUserStore();

    }

    private static void saveUserStore() {
        SNSUserStore snsUserStore = App.getInstance().getCompany().getSNSUserStore();
        try {
            FileOutputStream fileOutputStreamUsersStore = new FileOutputStream(Files.FILE_USERS_BIN);
            ObjectOutputStream outputStreamUserStore = new ObjectOutputStream(fileOutputStreamUsersStore);
            try {
                outputStreamUserStore.writeObject(snsUserStore);
            } finally {
                fileOutputStreamUsersStore.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveEmployees() {
        try {
            FileOutputStream employeeFiles = new FileOutputStream(Files.FILE_EMPLOYEE_BIN);
            ObjectOutputStream employeesOut = new ObjectOutputStream(employeeFiles);
            
            try {
                employeesOut.writeObject(employeesList);
            } finally {
                employeeFiles.close();
            }
            
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
