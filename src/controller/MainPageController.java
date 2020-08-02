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
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
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

    //OTHER
    private EventsController eventsController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventsController = new EventsController();
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
        eventsController.getEvents(eventVbox);
    }

    public void onAddEventAction(ActionEvent actionEvent) {
        openView(PageView.ADD_EVENTS);
        eventsController.addEvents(addEventVBox);
    }



    @FXML
    public void onShowMyCreatedEventsAction(ActionEvent actionEvent) {
    }

    @FXML
    public void onShownAdminAction(ActionEvent actionEvent) {
    }

    @FXML
    public void onBookAction(ActionEvent actionEvent) {
    }



    public void backHome(ActionEvent actionEvent) {
        dashboardImage.setVisible(true);
        eventVbox.getChildren().clear();

    }

    private void openView(PageView pageView) {
        dashboardImage.setVisible(false);

        eventVbox.getChildren().clear();
        eventVbox.setVisible(false);

        addEventVBox.getChildren().clear();
        addEventVBox.setVisible(true);

        switch (pageView) {
            case ALL_EVENTS:
                eventVbox.setVisible(true);
                break;
            case ADD_EVENTS:
                addEventVBox.setVisible(true);
        }
    }
}
