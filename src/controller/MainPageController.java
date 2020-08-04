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
    private AnchorPane addEventAncorPane;

    @FXML
    ScrollPane scrollMyEvents;

    @FXML
    VBox myEventsVbox;

    //OTHER
    private EventsController eventsController;

    private MyEventsController myEventsController;

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
        eventsController.addEvents(addEventAncorPane);
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
                addEventAncorPane.setVisible(true);
                break;
            case My_EVENTS:
                scrollMyEvents.setVisible(true);
                myEventsVbox.setVisible(true);
                break;
        }
    }

    public void clearAll(){
        dashboardImage.setVisible(false);
        scrollEvents.setVisible(false);

        eventVbox.getChildren().clear();
        eventVbox.setVisible(false);

        addEventAncorPane.getChildren().clear();
        addEventAncorPane.setVisible(false);

        scrollMyEvents.setVisible(false);
        myEventsVbox.getChildren().clear();
    }
}
