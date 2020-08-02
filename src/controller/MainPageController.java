package controller;

import entities.Event;
import entities.User;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import service.EventService;
import service.EventServiceImpl;
import utils.PageView;

public class MainPageController implements Initializable {

    //DASHBOARD
    @FXML
    private ImageView dashboardImage;

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


    //ADD EVENT
    @FXML
    private VBox addEventVBox;


    EventsController eventsController;

    Boolean isEventActionPressed = false;

    private EventService eventService;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventsController = new EventsController(eventVbox, bookEventButton);
        eventService = new EventServiceImpl();
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
        openView(PageView.ALL_EVENTS);
        eventsController.getEvents();
        isEventActionPressed = true;
    }


    @FXML
    public void onAddEventAction(ActionEvent actionEvent) {
        dashboardImage.setVisible(false);
        //redirectPane(actionEvent, CARD);
        Event event = new Event();
        event.setEventId(1);
        event.setLocation("Roman");
        event.setTitle("Teatru");
        event.setBookingAllowed(true);
        event.setOnline(true);
        event.setConstraints("10");
        event.setDescription("Testing an event");
        event.setStartDate(LocalDateTime.now());
        event.setEndDate(LocalDateTime.of(2020, 10, 21, 9, 15, 0));
        if (eventService.addEvent(event)) {
            //use a notification and a button
            System.out.println("Event created");
        }

        addEventVBox.setVisible(true);
    }

    @FXML
    public void onShowMyCreatedEventsAction(ActionEvent actionEvent) {
    }

    @FXML
    public void onShownAdminAction(ActionEvent actionEvent) {
    }


    @FXML
    public void onMouseClicked(MouseEvent mouseEvent) {
        eventsController.getEventCheckbox().selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                // TODO Auto-generated method stub
                if (newValue) {
                    eventsController.getEventCheckbox().setDisable(true);
                    // your checkbox has been ticked.
                    // write login-username to config file
                } else {
                    eventsController.getEventCheckbox().setDisable(false);
                    // your checkbox has been unticked. do stuff...
                    // clear the config file
                }
            }
        });
    }


    @FXML
    public void onBookAction(ActionEvent actionEvent) {
    }

    public void onMouseClicked1(MouseEvent mouseEvent) {
        System.out.println();
    }

    public void backHome(ActionEvent actionEvent) {
        dashboardImage.setVisible(true);
        eventVbox.getChildren().clear();
    }

    private void openView(PageView pageView) {
        switch (pageView) {
            case ALL_EVENTS:
                eventVbox.getChildren().clear();
                dashboardImage.setVisible(false);
                eventVbox.setVisible(true);
                break;
        }
    }
}
