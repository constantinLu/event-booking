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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    @FXML
    ScrollPane scrollMyEvents;

    @FXML
    VBox myEventsVbox;

    @FXML
    ScrollPane scrollBookedEvents;
    @FXML
    VBox bookedEventsVbox;


    //OTHER
    private EventsController eventsController;

    private MyEventsController myEventsController;
    private BookedEventsController bookedEventsController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO DELETE IF NOT NEEDED.
        bookEventButton.setVisible(false);
    }

    public void initData(User user) {
        loggedUser = user;
        userField.setText(loggedUser.getFirstName() + " " + loggedUser.getLastName());

        eventsController = new EventsController(loggedUser);
        myEventsController = new MyEventsController(loggedUser);
        bookedEventsController = new BookedEventsController(loggedUser);

//        dashboardImage.setVisible(true);
    }

    @FXML
    public void onClose(ActionEvent event) {
        System.exit(0);
    }


    @FXML
    public void onEventAction(ActionEvent actionEvent) {
        openView(PageView.ALL_EVENTS);
        eventsController.getEvents(eventVbox);
    }

    public void onAddEventAction(ActionEvent actionEvent) {
        openView(PageView.ADD_EVENTS);
        eventsController.addEvents(addEventVBox);
    }



    @FXML
    public void onShowMyCreatedEventsAction(ActionEvent actionEvent) {
        openView(PageView.My_EVENTS);
        myEventsController.getEvents(myEventsVbox);

    }

    @FXML
    public void onShownAdminAction(ActionEvent actionEvent) {
    }

    @FXML
    public void onBookAction(ActionEvent actionEvent) {
        openView(PageView.BOOKED_EVENTS);
        bookedEventsController.getEvents(bookedEventsVbox);
    }



    public void backHome(ActionEvent actionEvent) {
        clearAll();
        dashboardImage.setVisible(true);

    }

    private void openView(PageView pageView) {
        clearAll();

        switch (pageView) {
            case ALL_EVENTS:
                scrollEvents.setVisible(true);
                eventVbox.setVisible(true);
                break;
            case ADD_EVENTS:
                addEventVBox.setVisible(true);
                break;
            case My_EVENTS:
                scrollMyEvents.setVisible(true);
                myEventsVbox.setVisible(true);
                break;
            case BOOKED_EVENTS:
                scrollBookedEvents.setVisible(true);
                bookedEventsVbox.setVisible(true);
        }
    }

    public void clearAll(){
        dashboardImage.setVisible(false);
        scrollEvents.setVisible(false);

        eventVbox.getChildren().clear();
        eventVbox.setVisible(false);

        addEventVBox.getChildren().clear();
        addEventVBox.setVisible(false);

        scrollMyEvents.setVisible(false);
        myEventsVbox.getChildren().clear();

        scrollBookedEvents.setVisible(false);
        bookedEventsVbox.setVisible(false);
        bookedEventsVbox.getChildren().clear();
    }
}
