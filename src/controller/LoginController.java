package controller;

import entities.User;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.AuthenticationService;
import service.AuthenticationServiceImpl;
import static utils.Path.*;
import utils.Redirect;

public class LoginController {

    @FXML
    public PasswordField passwordField;
    @FXML
    private TextField studentIdField;

    private boolean isSuccefulLogin;

    AuthenticationService authenticationService = new AuthenticationServiceImpl();


    public void login(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(LOGIN));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Event Booking");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    /*
      //Scene scene = new Scene(root);
        // primaryStage.initStyle(StageStyle.UNDECORATED);
            Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Event Booking");
        primaryStage.setScene(scene);
        root.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getSceneX() - xOffset);
            primaryStage.setY(event.getSceneY() - yOffset);
        });
     */


    @FXML
    public void onClose(ActionEvent event) {
        System.exit(0);
    }


    @FXML
    public void onLogin(ActionEvent event) {
        User user = authenticationService.login(studentIdField.getText(), passwordField.getText());
        if (user != null) {
            System.out.println("Successful login for : " + user);
            try {
                new Redirect().redirect(event, MAIN_PAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Unable to login");
        }
    }


    public void redirectToRegister(ActionEvent actionEvent) {
        try {
            new Redirect().redirect(actionEvent, REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
