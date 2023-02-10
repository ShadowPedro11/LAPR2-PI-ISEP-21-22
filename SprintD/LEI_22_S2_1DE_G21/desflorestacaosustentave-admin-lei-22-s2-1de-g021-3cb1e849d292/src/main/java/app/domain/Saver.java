package app.domain;

import app.controller.App;
import app.domain.model.*;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.employee.Employee;
import app.domain.shared.Constants;
import app.domain.shared.Files;
import app.domain.store.SNSUserStore;
import app.domain.store.VaccinationEventStore;
import app.domain.store.VaccineScheduleStore;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Saver {

    public static void saveData() {
        saveEmployees();
        saveUserStore();
        saveVaccineTypes();
        saveVaccines();
        saveVaccinationCenters();
        saveVaccineAdministrations();
        saveVaccineScheduleStore();
saveVaccineEvent();

    }


    private static void saveVaccineEvent() {
        VaccinationEventStore vaccinationEventStore = App.getInstance().getCompany().getVaccinationEventStore();
        try {
            FileOutputStream file = new FileOutputStream(Files.FILE_VACCINE_EVENT);
            ObjectOutputStream object = new ObjectOutputStream(file);
            try {
                object.writeObject(vaccinationEventStore);
            } finally {
                file.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveVaccineScheduleStore() {
        VaccineScheduleStore vaccineScheduleStore = App.getInstance().getCompany().getVaccineScheduleStore();
        try {
            FileOutputStream fileOutputStreamVaccineScheduleStore = new FileOutputStream(Files.FILE_VACCINE_SCHEDULE_STORE);
            ObjectOutputStream objectOutputStreamVaccineScheduleStore = new ObjectOutputStream(fileOutputStreamVaccineScheduleStore);
            try {
                objectOutputStreamVaccineScheduleStore.writeObject(vaccineScheduleStore);
            } finally {
                fileOutputStreamVaccineScheduleStore.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveVaccinationCenters() {
        List<VaccinationCenter> vaccinationCenterList = Company.getVaccinationCenterList();
        try {
            FileOutputStream fileOutputStreamVaccinationCenters = new FileOutputStream(Files.FILE_VACCINATION_CENTERS);
            ObjectOutputStream objectOutputStreamVaccinationCenters = new ObjectOutputStream(fileOutputStreamVaccinationCenters);
            try {
                objectOutputStreamVaccinationCenters.writeObject(vaccinationCenterList);
            } finally {
                fileOutputStreamVaccinationCenters.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private static void saveVaccineAdministrations() {
        ArrayList<VaccineAdministration> vaccineAdministrations = App.getInstance().getCompany().getAdministrations();
        try {
            FileOutputStream fileOutputStreamVaccineAdministrations = new FileOutputStream(Files.FILE_ADMINISTRATION_PROCESS);
            ObjectOutputStream objectOutputStreamVaccineAdministrations = new ObjectOutputStream(fileOutputStreamVaccineAdministrations);
            try {
                objectOutputStreamVaccineAdministrations.writeObject(vaccineAdministrations);
            } finally {
                fileOutputStreamVaccineAdministrations.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveVaccines() {
        ArrayList<Vaccine> vaccines = App.getInstance().getCompany().getVaccineList();
        try {
            FileOutputStream fileOutputStreamVaccines = new FileOutputStream(Files.FILE_VACCINE);
            ObjectOutputStream objectOutputStreamVaccines = new ObjectOutputStream(fileOutputStreamVaccines);
            try {
                objectOutputStreamVaccines.writeObject(vaccines);
            } finally {
                fileOutputStreamVaccines.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void saveVaccineTypes() {
        ArrayList<VaccineType> vaccineTypes = App.getInstance().getCompany().getVaccineTypes();
        try {
            FileOutputStream fileOutputStreamVaccineTypes = new FileOutputStream(Files.FILE_VACCINE_TYPE);
            ObjectOutputStream objectOutputStreamVaccineTypes = new ObjectOutputStream(fileOutputStreamVaccineTypes);
            try {
                objectOutputStreamVaccineTypes.writeObject(vaccineTypes);
            } finally {
                fileOutputStreamVaccineTypes.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        ArrayList<Employee> employeesList = App.getInstance().getCompany().getEmployees();
        try {
            FileOutputStream employeeFiles = new FileOutputStream(Files.FILE_EMPLOYEE_BIN);
            ObjectOutputStream employeesOut = new ObjectOutputStream(employeeFiles);
            
            try {
                employeesOut.writeObject(employeesList);
            } finally {
                employeeFiles.close();
            }
            
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
