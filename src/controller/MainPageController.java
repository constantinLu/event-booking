package controller;

import alert.Alert;
import alert.AlertPane;
import connection.JdbcConnection;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import utils.PageView;
import utils.Path;
import utils.Redirect;

public class MainPageController implements Alert, Initializable {


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

    //ALERS
    @FXML
    public Pane errorPane;
    @FXML
    public Text errorCode;

    @FXML
    public Label role;

    //Controllers
    private EventsController eventsController;
    private BookedEventsController bookedEventsController;
    private AddEventsController addEventsController;
    private MyEventsController myEventsController;
    private AdminController adminController;

    //SIDEBAR
    public Button eventButtonSideBar;
    public Button bookedButtonSideBar;
    public Button addEventButtonSideBar;
    public Button myEventsButtonSideBar;
    public Button adminButtonSideBar;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isOnlineDropDown.setItems(FXCollections.observableArrayList("YES", "NO"));
        isOnlineDropDown.setValue("YES");
        isBookingAllowed.setItems(FXCollections.observableArrayList("YES", "NO"));
        isBookingAllowed.setValue("YES");
        initializeAlertPane();

    }

    @Override
    public void initializeAlertPane() {
        AlertPane.createInstance(errorPane, errorCode);
    }

    public void initData(User user) {
        loggedUser = user;
        role.setText(user.getRole().toString());
        userField.setText(loggedUser.getFirstName() + " " + loggedUser.getLastName());
        renderAccesRights();
        eventsController = new EventsController(loggedUser);
        bookedEventsController = new BookedEventsController(loggedUser);
        addEventsController = new AddEventsController(loggedUser);
        myEventsController = new MyEventsController(loggedUser, scrollMyEvents,
                myEventsVbox);
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
        openView(PageView.MY_EVENTS);
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

    public void onLogOut(ActionEvent actionEvent) {

        loggedUser = null;
        clearAll();
        try {
            new Redirect().redirectToLogin(actionEvent, Path.LOGIN);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JdbcConnection.Destroy();

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
            case MY_EVENTS:
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

    private void renderAccesRights() {
        if (loggedUser != null) {
            eventButtonSideBar.setVisible(true);
            bookedButtonSideBar.setVisible(true);
            addEventButtonSideBar.setVisible(true);
            myEventsButtonSideBar.setVisible(true);
            adminButtonSideBar.setVisible(true);
            switch (loggedUser.getRole()) {
                case STUDENT:
                    addEventButtonSideBar.setVisible(false);
                    myEventsButtonSideBar.setVisible(false);
                    adminButtonSideBar.setVisible(false);
                case EVENT_ORGANISER:
                    adminButtonSideBar.setVisible(false);
            }
        }

    }
}
