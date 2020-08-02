package controller;

import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainPageController implements Initializable {

    @FXML
    Label userField;

    User loggedUser;

    //ALL EVENTS
    @FXML
    AnchorPane dashboardAncorPane;
    @FXML
    ScrollPane scrollEvents;
    @FXML
    VBox eventVbox;
    @FXML
    Button bookEventButton;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void initData(User user) {
        loggedUser = user;
        userField.setText(loggedUser.getFirstName() + " " + loggedUser.getLastName());
    }

    @FXML
    public void onClose(ActionEvent event) {
        System.exit(0);
    }


    @FXML
    public void onEventAction(ActionEvent actionEvent) {
        eventVbox.getChildren().clear();
        EventsController eventsController = new EventsController(eventVbox, bookEventButton);
        eventsController.getEvents();
    }

    @FXML
    public void onBookAction(ActionEvent actionEvent) {
    }


    @FXML
    public void onAddEventAction(ActionEvent actionEvent) {
//        //redirectPane(actionEvent, CARD);
//        Event event = new Event();
//        event.setEventId(1);
//        event.setLocation("Roman");
//        event.setTitle("Teatru");
//        event.setBookingAllowed(true);
//        event.setOnline(true);
//        event.setConstraints("10");
//        event.setDescription("Testing an event");
//        event.setStartDate(LocalDateTime.now());
//        event.setEndDate(LocalDateTime.of(2020, 10, 21, 9, 15, 0));
//        if (eventService.addEvent(event)) {
//            //use a notification and a button
//            System.out.println("Event created");
//        }
    }

    @FXML
    public void onShowMyCreatedEventsAction(ActionEvent actionEvent) {
    }

    @FXML
    public void onShownAdminAction(ActionEvent actionEvent) {
    }

    public void onLogin(ActionEvent actionEvent) {
    }

//    private void openAllEventsPane() {
//        allEventsPane.setVisible(true);
//        bookedEventsPane.setVisible(false);

//        addEventPane.setVisible(false);
//        myEventsPane.setVisible(false);
//    }
//
//    private void openbookedEventsPane() {
//        allEventsPane.setVisible(false);
//        bookedEventsPane.setVisible(true);
//        addEventPane.setVisible(false);
//        myEventsPane.setVisible(false);
//    }
//
//    private void openAddEventPane() {
//        allEventsPane.setVisible(false);
//        bookedEventsPane.setVisible(false);
//        addEventPane.setVisible(true);
//        myEventsPane.setVisible(false);
//    }
//
//    private void openMyEventsPane() {
//        allEventsPane.setVisible(false);
//        bookedEventsPane.setVisible(false);
//        addEventPane.setVisible(false);
//        myEventsPane.setVisible(true);
//    }
}
