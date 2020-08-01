package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import static utils.Path.LOGIN;
import utils.Redirect;

public class RegisterController {


    public TextField studentId;
    public PasswordField password;
    public TextField lastName;
    public PasswordField confirmPassword;
    public TextField firstName;
    public TextField email;


    public void onRegister(ActionEvent actionEvent) {
        try {
            new Redirect().redirect(actionEvent,LOGIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClose(ActionEvent actionEvent) {
        System.exit(0);
    }


}