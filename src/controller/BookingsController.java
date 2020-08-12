package controller;

import alert.Alert;
import static alert.AlertColor.ERROR;
import alert.AlertPane;
import entities.Booking;
import entities.Event;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import service.*;
import static utils.Path.MAIN_PAGE;
import utils.Redirect;
import static utils.Style.*;

public class BookingsController implements Alert, Initializable {

    @FXML
    public Pane errorPane;
    @FXML
    public Text errorMessage;

    @FXML
    VBox bookingsVbox;

    private UserService userService = new UserServiceImpl();
    private EventService eventService = new EventServiceImpl();
    private BookingService bookingService = new BookingServiceImpl();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        showData();
        initializeAlertPane();
    }

    private void showData() {
        List<Booking> bookings = bookingService.getAllBooking();
        addHeader(bookingsVbox);
        for (int i = 0; i < bookings.size(); i++) {
            Booking bookingEntity = bookings.get(i);

            HBox hbox = createHbox(bookingEntity);
            styleHBox(hbox, i);

            bookingsVbox.getChildren().add(hbox);
            bookingsVbox.setSpacing(20);
            bookingsVbox.setVisible(true);
            VBox.setMargin(hbox, new Insets(5, 5, 5, 5));
            bookingsVbox.setPadding(new Insets(10, 10, 10, 10));
        }
    }


    private void addHeader(VBox vBox) {
        HBox hBox = new HBox();

        Label userName = new Label();
        userName.setText("User");
        styleLabel(userName, true);

        Label booked = new Label();
        booked.setText("booked");
        styleLabel(booked, true);
        booked.setVisible(false);

        Label event = new Label();
        event.setText("Event");
        styleLabel(event, true);


        Label buttonLabel = new Label();
        buttonLabel.setText("    Actions  ");
        styleLabel(buttonLabel, true);


        hBox.getChildren().addAll(

                userName,
                booked,
                event,
                buttonLabel);

        styleHBox(hBox, -1);
        vBox.getChildren().add(hBox);
    }


    private HBox createHbox(Booking bookingEntity) {
        HBox hBox = new HBox();

        Label userName = new Label();
        User user = userService.getUserById(bookingEntity.getUserId());
        userName.setText(user.getFirstName() + " " + user.getLastName());
        styleLabel(userName, false);

        Text booked = new Text();
        booked.setText("booked");
        styleText(booked);

        Label eventName = new Label();
        Event event = eventService.getEventById(bookingEntity.getEventId());
        eventName.setText(event.getTitle());
        styleLabel(eventName, false);


        Button deleteBooking = new Button();
        deleteBooking.setText("Delete");
        deleteBooking.setDisable(false);
        deleteBooking.setStyle("-fx-background-color: #febb02");
        deleteBooking.setDisable(false);
        deleteBooking.setOnAction(eventAction -> {
            bookingService.deleteBooking(bookingEntity.getBookingId());
            bookingsVbox.getChildren().clear();
            showData();

        });

        styleButton(deleteBooking, 0);
        styleHBox(hBox, -1);

        hBox.getChildren().addAll(
                userName,
                booked,
                eventName,
                deleteBooking);

        return hBox;
    }


    @Override
    public void initializeAlertPane() {
        AlertPane.createInstance(errorPane, errorMessage);
    }

    @FXML
    public void backToDashBoard(ActionEvent actionEvent) {
        try {
            new Redirect().redirectToParent(actionEvent, MAIN_PAGE);
        } catch (IOException e) {
            AlertPane.show("Redirect failed", ERROR);
        }
    }
}
