package app.domain;

import app.controller.App;
import app.domain.model.*;
import app.domain.model.VaccinationCenter.VaccinationCenter;
import app.domain.model.employee.CenterCoordinator;
import app.domain.model.employee.Employee;
import app.domain.model.employee.Nurse;
import app.domain.model.employee.Receptionist;
import app.domain.shared.Constants;
import app.domain.shared.Files;
import app.domain.store.SNSUserStore;
import app.domain.store.VaccinationEventStore;
import app.domain.store.VaccineScheduleStore;
import pt.isep.lei.esoft.auth.AuthFacade;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Loader {

    public static VaccinationEventStore loadVaccinationEventStore() {
        VaccinationEventStore vaccinationEventStore = new VaccinationEventStore();
        try {
            FileInputStream file = new FileInputStream(Files.FILE_VACCINE_EVENT);
            ObjectInputStream object = new ObjectInputStream(file);
            try {
                vaccinationEventStore = (VaccinationEventStore) object.readObject();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                file.close();
            }
        } catch (FileNotFoundException e) {
            return vaccinationEventStore;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return vaccinationEventStore;
    }

    public static ArrayList<VaccineAdministration> loadVaccineAdministrations() {
        ArrayList<VaccineAdministration> administrations = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(Files.FILE_ADMINISTRATION_PROCESS);
            ObjectInputStream object = new ObjectInputStream(file);
            try {
                administrations = (ArrayList<VaccineAdministration>) object.readObject();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                file.close();
            }
        } catch (FileNotFoundException e) {
            return administrations;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return administrations;
    }

    public static ArrayList<Vaccine> loadVaccines() {
        ArrayList<Vaccine> vaccines = new ArrayList<>();
        try {
            FileInputStream file = new FileInputStream(Files.FILE_VACCINE);
            ObjectInputStream object = new ObjectInputStream(file);
            try {
                vaccines = (ArrayList<Vaccine>) object.readObject();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                file.close();
            }
        } catch (FileNotFoundException e) {
            return vaccines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return vaccines;
    }

    public static VaccineScheduleStore loadVaccineScheduleStore() {
        VaccineScheduleStore vaccineScheduleStore = new VaccineScheduleStore();
        try {
            FileInputStream file = new FileInputStream(Files.FILE_VACCINE_SCHEDULE_STORE);
            ObjectInputStream object = new ObjectInputStream(file);
            try {
                vaccineScheduleStore = (VaccineScheduleStore) object.readObject();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                file.close();
            }
        } catch (FileNotFoundException e) {
            return vaccineScheduleStore;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return vaccineScheduleStore;
    }

    public static List<VaccinationCenter> loadVaccinationCenters() {
        List<VaccinationCenter> vaccinationCenters = new ArrayList<>();
        try {
            FileInputStream vcFile = new FileInputStream(Files.FILE_VACCINATION_CENTERS);
            ObjectInputStream vcObject = new ObjectInputStream(vcFile);
            try {
                vaccinationCenters = (List<VaccinationCenter>) vcObject.readObject();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                vcFile.close();
            }
        } catch (FileNotFoundException e) {
            return vaccinationCenters;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return vaccinationCenters;
    }


    public static ArrayList<VaccineType> loadVaccineTypes() {
        ArrayList<VaccineType> vaccineTypes = new ArrayList<>();
        try {
            FileInputStream vaccineTypesFile = new FileInputStream(Files.FILE_VACCINE_TYPE);
            ObjectInputStream vaccineTypesStream = new ObjectInputStream(vaccineTypesFile);
            try {
                vaccineTypes = (ArrayList<VaccineType>) vaccineTypesStream.readObject();

            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } finally {
                vaccineTypesFile.close();
            }
        } catch (FileNotFoundException e) {
            return vaccineTypes;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return vaccineTypes;
    }

    public static SNSUserStore loadUsers() {
        SNSUserStore newSnsUserStore = new SNSUserStore();
        try {
            FileInputStream fileInputStreamUserStore = new FileInputStream(Files.FILE_USERS_BIN);
            ObjectInputStream objectInputStreamUserStore = new ObjectInputStream(fileInputStreamUserStore);
            try {
                newSnsUserStore = (SNSUserStore) objectInputStreamUserStore.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            } finally {
                fileInputStreamUserStore.close();
            }
        } catch (FileNotFoundException e) {
            return newSnsUserStore;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return newSnsUserStore;
    }

    public static ArrayList<Employee> loadEmployees() {
        ArrayList<Employee> employeesList = new ArrayList<>();
        try {
            FileInputStream fileInputStreamUserStore = new FileInputStream(Files.FILE_EMPLOYEE_BIN);
            ObjectInputStream objectInputStreamUserStore = new ObjectInputStream(fileInputStreamUserStore);
            try {
                employeesList = (ArrayList<Employee>) objectInputStreamUserStore.readObject();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);

            } finally {
                fileInputStreamUserStore.close();
            }
        } catch (FileNotFoundException e) {
            return employeesList;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return employeesList;
    }


    public static void addLoginAllUsers(ArrayList<SNSUser> userList, ArrayList<Employee> employees, AuthFacade authFacade) {
        for (SNSUser user : userList) {
            authFacade.addUserWithRole(user.getName(), user.getEmail(), user.getPassword(), Constants.ROLE_SNSUSER);
        }
        addLoginEmployee(employees, authFacade);
    }

    private static void addLoginEmployee(ArrayList<Employee> employees, AuthFacade authFacade) {
        for (Employee employee : employees) {
            if (employee instanceof Receptionist) {
                authFacade.addUserWithRole("Recptionist", employee.getEmail(), employee.getPassword(), Constants.ROLE_RECEPTIONIST);
            }
            if (employee instanceof Nurse) {
                authFacade.addUserWithRole("Nurse", employee.getEmail(), employee.getPassword(), Constants.ROLE_NURSE);
            }
            if (employee instanceof CenterCoordinator) {
                authFacade.addUserWithRole("Center Coordinator", employee.getEmail(), employee.getPassword(), Constants.ROLE_CENTERCOORDINATOR);
            }

        }
    }
}
