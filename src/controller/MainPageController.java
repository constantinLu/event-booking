package controller;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import networking.JDBC;
import utils.PageView;
import utils.Path;
import utils.Redirect;

import java.io.IOException;

public class MainPageController {

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

    //ADD EVENT
    @FXML
    public Pane addPane;
    @FXML
    public TextField addTitle;
    @FXML
    public TextField addDescription;
    @FXML
    public TextField addLocation;
    @FXML
    public ChoiceBox isOnlineDropDown;
    @FXML
    public DatePicker startDatePicker;
    @FXML
    public DatePicker endDatePicker;
    @FXML
    public TextField addSeats;
    @FXML
    public ChoiceBox isBookingAllowed;

    //MY EVENTS
    @FXML
    ScrollPane scrollMyEvents;
    @FXML
    VBox myEventsVbox;

    //BOOKED EVENTS
    @FXML
    ScrollPane scrollBookedEvents;
    @FXML
    VBox bookedEventsVbox;

    //ADMIN
    @FXML
    public ScrollPane scrollPaneAdmin;
    @FXML
    public VBox adminVbox;

    //Controllers
    private EventsController eventsController;
    private BookedEventsController bookedEventsController;
    private AddEventsController addEventsController;
    private MyEventsController myEventsController;
    private AdminController adminController;


    public void initData(User user) {
        loggedUser = user;
        userField.setText(loggedUser.getFirstName() + " " + loggedUser.getLastName());

        eventsController = new EventsController(loggedUser);
        bookedEventsController = new BookedEventsController(loggedUser);
        addEventsController = new AddEventsController(loggedUser);
        myEventsController = new MyEventsController(loggedUser);
        adminController = new AdminController(loggedUser);
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

    @FXML
    public void onBookAction(ActionEvent actionEvent) {
        openView(PageView.BOOKED_EVENTS);
        bookedEventsController.getEvents(bookedEventsVbox);
    }

    @FXML
    public void onAddEventAction(ActionEvent actionEvent) {
        openView(PageView.ADD_EVENTS);
        addEventsController.addEvents(addPane);
    }

    @FXML
    public void onMyCreatedEventsAction(ActionEvent actionEvent) {
        openView(PageView.My_EVENTS);
        myEventsController.getEvents(myEventsVbox);
    }

    @FXML
    public void onAdminAction(ActionEvent actionEvent) {
        openView(PageView.ADMIN_VIEW);
        adminController.getUsers(adminVbox);
    }


    public void backHome(ActionEvent actionEvent) {
        clearAll();
        dashboardImage.setVisible(true);

    }

    public void onLogOut(ActionEvent actionEvent){

        loggedUser=null;
        clearAll();
        try{
            new Redirect().redirectToLogin(actionEvent, Path.LOGIN);
        }catch (IOException e){
            e.printStackTrace();
        }
        JDBC.Destroy();

    }

    private void openView(PageView pageView) {
        clearAll();

        switch (pageView) {
            case ALL_EVENTS:
                scrollEvents.setVisible(true);
                eventVbox.setVisible(true);
                break;
            case ADD_EVENTS:
                addPane.setVisible(true);
                addTitle.setVisible(true);
                break;
            case My_EVENTS:
                scrollMyEvents.setVisible(true);
                myEventsVbox.setVisible(true);
                break;
            case BOOKED_EVENTS:
                scrollBookedEvents.setVisible(true);
                bookedEventsVbox.setVisible(true);
                break;
            case ADMIN_VIEW:
                scrollPaneAdmin.setVisible(true);
                adminVbox.setVisible(true);
                break;
        }
    }

    private void clearAll() {
        dashboardImage.setVisible(false);

        scrollEvents.setVisible(false);
        eventVbox.getChildren().clear();
        eventVbox.setVisible(false);


        scrollBookedEvents.setVisible(false);
        bookedEventsVbox.setVisible(false);
        bookedEventsVbox.getChildren().clear();


        scrollMyEvents.setVisible(false);
        myEventsVbox.getChildren().clear();


        addPane.setVisible(false);


        scrollPaneAdmin.setVisible(false);
        adminVbox.setVisible(false);
        adminVbox.getChildren().clear();
    }
}
