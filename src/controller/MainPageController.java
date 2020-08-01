package controller;

import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class MainPageController implements Initializable {

    @FXML
    private Label userField;

    @FXML
    private User loggedUser;

    EventsController eventsController;


    @FXML
    private EventsController appBarController; // injected via <fx:include fx:id="child" ... />

    @FXML
    public void initialize() {
//        eventsController.se(this);
//        navMenuController.setScreenParent(this);
//        mainContentController.setScreenParent(this);
    }


    public void initData(User user) {
        loggedUser = user;
        userField.setText(loggedUser.getFirstName() + " " + loggedUser.getLastName());
    }

    @FXML
    public void onClose(ActionEvent event) {
        System.exit(0);
    }

    //action for buttons // DELETE THIS
    public void onLogin(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onEventsAction(ActionEvent actionEvent) throws IOException {
    }
}
