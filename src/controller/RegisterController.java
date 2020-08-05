package controller;

import alert.Alert;
import static alert.AlertColor.FIELD_ERROR;
import alert.AlertPane;
import entities.Roles;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import service.AuthenticationService;
import service.AuthenticationServiceImpl;
import static utils.Path.LOGIN;
import utils.Redirect;
import utils.Validator;

public class RegisterController implements Alert, Initializable {


    public TextField studentId;
    public PasswordField password;
    public TextField lastName;
    public PasswordField confirmPassword;
    public TextField firstName;
    public TextField email;


    @FXML
    public Pane errorPane;
    @FXML
    public Text errorMessage;

    AuthenticationService authenticationService = new AuthenticationServiceImpl();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAlertPane();
    }

    @Override
    public void initializeAlertPane() {
        AlertPane.createInstance(errorPane, errorMessage);
    }

    public void onRegister(ActionEvent actionEvent) {
        User.Builder userBuilder = new User.Builder(studentId.getText(), password.getText()).withEmail(email.getText()).withFirstName(firstName.getText()).withLastName(lastName.getText()).withRole(Roles.STUDENT);
        User user = userBuilder.createUser();
        if (validateRegisterPage(user)) {
            if (authenticationService.register(user)) {
                try {
                    new Redirect().redirectToParent(actionEvent, LOGIN);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onClose(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void redirectToLogin(ActionEvent actionEvent) {
        try {
            new Redirect().redirectToParent(actionEvent, LOGIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean validateRegisterPage(User user) {
        if (!password.getText().equals(confirmPassword.getText())) {
            AlertPane.show("Passwords must match", FIELD_ERROR);
            return false;
        }
        if (!Validator.validate(studentId, password, email, firstName, lastName)) {
            Validator.errors.forEach((key, value) -> AlertPane.show(value, FIELD_ERROR));
            return false;
        }
        return true;
    }
}