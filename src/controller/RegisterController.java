package controller;

import entities.User;
import java.io.IOException;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.AuthenticationService;
import service.AuthenticationServiceImpl;
import static utils.Path.LOGIN;
import utils.Redirect;
import static utils.Path.LOGIN;
import utils.Redirect;

public class RegisterController {


    public TextField studentId;
    public PasswordField password;
    public TextField lastName;
    public PasswordField confirmPassword;
    public TextField firstName;
    public TextField email;

    AuthenticationService authenticationService = new AuthenticationServiceImpl();


    public void onRegister(ActionEvent actionEvent) {
        User.Builder userBuilder = new User.Builder(studentId.getText(),password.getText()).withEmail(email.getText()).withFirstName(firstName.getText()).withLastName(lastName.getText());
        User user = userBuilder.createUser();
        User registeredUser = authenticationService.register(user);
        try {
            new Redirect().redirect(actionEvent,LOGIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Registration sucessfull for " + registeredUser);

    }

    public void onClose(ActionEvent actionEvent) {
        System.exit(0);
    }


}