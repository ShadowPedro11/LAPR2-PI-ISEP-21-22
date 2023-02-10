package app.controller.gui;

import app.controller.App;
import app.controller.AuthController;
import app.domain.shared.Constants;
import app.ui.console.*;
import app.ui.console.admin.AdminUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pt.isep.lei.esoft.auth.mappers.dto.UserRoleDTO;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AuthGuiController implements Initializable {

    private App app;
    public Button LoginBtn;
    public PasswordField passwordArea;
    public TextField emailArea;

    private Parent root;
    private Scene scene;
    private Stage stage;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.app = App.getInstance();
    }

    public void LoginAction(ActionEvent event) throws IOException {
        String email = emailArea.getText();
        String pwd = passwordArea.getText();
        boolean success = app.doLogin(email, pwd);
        if (success) {
            if (app.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_CENTERCOORDINATOR)) {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("CenterCoordinatorUI.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            if (app.getCurrentUserSession().isLoggedInWithRole(Constants.ROLE_NURSE)) {
                root = FXMLLoader.load(getClass().getClassLoader().getResource("selectVaccinationCenter.fxml"));
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }


        }
    }

}
