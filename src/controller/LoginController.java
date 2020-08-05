package controller;

import alert.Alert;
import static alert.AlertColor.ERROR;
import static alert.AlertColor.FIELD_ERROR;
import alert.AlertPane;
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
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import service.AuthenticationService;
import service.AuthenticationServiceImpl;
import static utils.Path.*;
import utils.Redirect;
import utils.Validator;

public class LoginController implements Alert, Initializable {

    @FXML
    public PasswordField passwordField;
    @FXML
    private TextField studentIdField;


    @FXML
    public Pane errorPane;
    @FXML
    public Text errorMessage;

    private AuthenticationService authenticationService = new AuthenticationServiceImpl();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAlertPane();
    }


    @Override
    public void initializeAlertPane() {
        AlertPane.createInstance(errorPane, errorMessage);
    }

    public void start(Stage primaryStage) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(LOGIN));
        } catch (IOException e) {
            e.printStackTrace();
        }
        primaryStage.setTitle("Event Booking");
        assert root != null;
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
        User user = authenticationService.login(studentIdField.getText(), passwordField.getText());
        if (user == null) {
            AlertPane.show("Invalid username or password", FIELD_ERROR);
            return;
        }
        if (Validator.validate(studentIdField, passwordField)) {
            try {
                new Redirect().redirectToMainPage(event, MAIN_PAGE, user);
            } catch (IOException e) {
                AlertPane.show("Redirect failed", FIELD_ERROR);
                onLogin(event);
            }
        } else {
            Validator.errors.forEach((key, value) -> AlertPane.show(value, FIELD_ERROR));
        }
    }


    @FXML
    public void redirectToRegister(ActionEvent actionEvent) {
        try {
            new Redirect().redirectToParent(actionEvent, REGISTER);
        } catch (IOException e) {
            AlertPane.show("Redirect failed", ERROR);
        }
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


}
