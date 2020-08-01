package controller;

import entities.User;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import service.AuthenticationService;
import service.AuthenticationServiceImpl;

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
        System.out.println("Registration sucessfull for " + registeredUser);

    }

    public void onClose(ActionEvent actionEvent) {

    }
}
