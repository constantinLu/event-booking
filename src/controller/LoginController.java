package controller;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.AuthenticationService;
import service.AuthenticationServiceImpl;
import static utils.Path.*;
import utils.Redirect;

public class LoginController implements Initializable {

    @FXML
    public PasswordField passwordField;
    @FXML
    private TextField studentIdField;

    private User loggedUser;

    private Boolean isUserLogged;

    AuthenticationService authenticationService = new AuthenticationServiceImpl();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


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
                new Redirect().redirectToMainPage(event, MAIN_PAGE, user);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Unable to login");
        }
    }


    public void redirectToRegister(ActionEvent actionEvent) {
        try {
            new Redirect().redirectToParent(actionEvent, REGISTER);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TextField getStudentIdField() {
        return studentIdField;
    }

    public void setStudentIdField(TextField studentIdField) {
        this.studentIdField = studentIdField;
    }

    public User getLoggedUser() {
        return loggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        this.loggedUser = loggedUser;
    }

}
