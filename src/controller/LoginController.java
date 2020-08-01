package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {

    @FXML
    public PasswordField password;
    @FXML
    private TextField studentId;

    private boolean isSuccefulLogin;


    public void login(Stage primaryStage)  {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../userinterface/login.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Event Booking");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    @FXML
    public void onClose(ActionEvent event) {
        System.exit(0);
    }


    @FXML
    public void onLogin(ActionEvent event) {
        String test = studentId.getText();
        String passw = password.getText();
        System.out.println("LogggedIn");
    }

    public void redirectToRegister(ActionEvent actionEvent) {
        try {
            Parent registerPage = FXMLLoader.load(getClass().getResource("/userinterface/register.fxml"));
            Scene registerPageScene = new Scene(registerPage);
            Stage appStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(registerPageScene);
            appStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
