package controller;

import java.io.IOException;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import service.AuthenticationService;
import service.AuthenticationServiceImpl;
import static utils.Path.*;
import utils.Redirect;

public class LoginController {

    @FXML
    public PasswordField password;
    @FXML
    private TextField studentId;

    private boolean isSuccefulLogin;

    AuthenticationService authenticationService =new AuthenticationServiceImpl();


    public void login(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(LOGIN));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Scene scene = new Scene(root);
       // primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Event Booking");
        primaryStage.setScene(new Scene(root, 700, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    /*
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
        String username = studentId.getText();
        String passwordString = password.getText();
        User user = authenticationService.login(username,passwordString);
        if(user!=null){
            System.out.println("Successful login for : "+user);
        }else{
            System.out.println("Unable to login");
        }
//        System.out.println("LogggedIn");
        String test = studentId.getText();
        String passw = password.getText();
        if (test.equals("gizet")) {
            try {
                new Redirect().redirect(event, MAIN_PAGE);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("LogggedIn");
    }

    public void redirectToRegister(ActionEvent actionEvent) {
        try {
            new Redirect().redirect(actionEvent, REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
