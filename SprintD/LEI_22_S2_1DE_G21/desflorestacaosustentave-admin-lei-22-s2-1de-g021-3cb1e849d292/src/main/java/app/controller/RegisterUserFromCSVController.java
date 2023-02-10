package app.controller;

import app.domain.model.Company;
import app.domain.store.SNSUserStore;

public class RegisterUserFromCSVController {

   private Company company;
   public SNSUserStore store;
   private String pathToCSV;

    public RegisterUserFromCSVController () {
        this(App.getInstance().getCompany());
    }

    public RegisterUserFromCSVController (Company company) {
        this.company = company;
        this.store = company.getSNSUserStore();
    }

    public boolean validatePathToCSV  (String pathToCSV) {
        if (store.validatePathToCSV(pathToCSV)) {
            this.pathToCSV = pathToCSV;
            return true;
        }
        return false;
    }

    public boolean saveUsers () {
        if (store.addUsers(pathToCSV)) {
            addLoginForUsers();
            return true;
        } else
            return false;
    }

    public void addLoginForUsers () {
        this.company.addLoginForUser(store.getUserList());
    }
}
