package app.domain;

import app.controller.App;
import app.domain.model.Company;
import app.domain.model.employee.Employee;
import app.domain.shared.Files;
import app.domain.store.SNSUserStore;

import java.io.*;
import java.util.ArrayList;

public class Loader {

    public static void loadData() {
        loadEmployees();
        loadEmployeesLogin();
        loadUsers();
        loadUsersLogin();

    }

    private static void loadEmployeesLogin() {
        Company company = App.getInstance().getCompany();
        for (Employee e : company.getEmployees()) {
            company.addLoginForEmployee(e);
        }
    }

    private static void loadUsersLogin() {
        Company company = App.getInstance().getCompany();
        company.addLoginForUser(company.getSNSUserStore().getUserList());
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
}
