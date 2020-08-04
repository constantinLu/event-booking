package controller;

import entities.Event;
import entities.User;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import service.EventService;
import service.EventServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import static utils.DateHelper.formatLocalDateTime;
import static utils.Style.*;

public class AddEventsController {

    private EventService eventService = new EventServiceImpl();

    private UserService userService = new UserServiceImpl();

    private User loggedUser;

    public AddEventsController(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void addEvents(AnchorPane addEventVBox) {

//        //obj to persist
//        Event eventEntity = new Event();
//
//        //Title
//        HBox titleHbox = new HBox();
//        Label titleLabel = new Label();
//        titleLabel.setText("Title");
//        styleLabel(titleLabel, false);
//
//        TextField titleFiled = new TextField();
//        titleFiled.setOnMouseExited(event -> {
//            System.out.println("Test title");
//            eventEntity.setTitle(titleFiled.getText());
//        });
//        styleHBox(titleHbox, 1);
//        titleHbox.getChildren().addAll(titleLabel, titleFiled);
//
//
//        //Description
//        HBox descriptionHbox = new HBox();
//        Label descriptionLabel = new Label();
//        descriptionLabel.setText("Description");
//        styleLabel(descriptionLabel, false);
//
//        TextField descriptionField = new TextField();
//        descriptionField.setOnMouseExited(event -> {
//            System.out.println("Testing Description");
//            eventEntity.setDescription(descriptionField.getText());
//        });
//        styleHBox(descriptionHbox, 0);
//        descriptionHbox.getChildren().addAll(descriptionLabel, descriptionField);
//
//
//        //Online
//        HBox onlineHbox = new HBox();
//        Label goOnlineLabel = new Label();
//        goOnlineLabel.setText("Go online");
//        styleLabel(goOnlineLabel, false);
//
//        final ComboBox onlineComboBox = new ComboBox();
//        onlineComboBox.getItems().addAll("YES", "NO");
//        onlineComboBox.setOnAction(event -> {
//            System.out.println("Testing combobox");
//            if (onlineComboBox.getSelectionModel().getSelectedItem().toString().equals("YES")) {
//                eventEntity.setOnline(true);
//            } else {
//                eventEntity.setOnline(false);
//
//            }
//        });
//        styleHBox(onlineHbox, 1);
//        onlineHbox.getChildren().addAll(goOnlineLabel, onlineComboBox);
//
//
//        //Location
//        HBox locationHbox = new HBox();
//        Label locationLabel = new Label();
//        locationLabel.setText("Location");
//        styleLabel(locationLabel, false);
//
//        TextField locationField = new TextField();
//        locationField.setOnMouseExited(event -> {
//            System.out.println("Testing location");
//            eventEntity.setLocation(locationField.getText());
//        });
//        styleHBox(locationHbox, 0);
//        descriptionHbox.getChildren().addAll(locationLabel, locationField);
//
//
//        //startDate
//        HBox startDateHbox = new HBox();
//        Label startDateLabel = new Label();
//        startDateLabel.setText("Start Date");
//        styleLabel(startDateLabel, false);
//
//        DatePicker startDatePicker = new DatePicker();
//        startDatePicker.setOnAction(event -> {
//            System.out.println("Test startDate");
//            eventEntity.setStartDate(LocalDateTime.of(startDatePicker.getValue(), LocalTime.MIDNIGHT));
//        });
//        styleHBox(startDateHbox, 1);
//        descriptionHbox.getChildren().addAll(startDateLabel, startDatePicker);
//
//
//        //startDate
//        HBox endDateHbox = new HBox();
//        Label endDateLabel = new Label();
//        endDateLabel.setText("End Date");
//        styleLabel(endDateLabel, false);
//
//        DatePicker endDatePicker = new DatePicker();
//        endDatePicker.setOnAction(event -> {
//            System.out.println("Test endDate");
//            eventEntity.setEndDate(LocalDateTime.of(endDatePicker.getValue(), LocalTime.MIDNIGHT));
//        });
//        styleHBox(endDateHbox, 0);
//        descriptionHbox.getChildren().addAll(endDateLabel, endDatePicker);
//
//
//        //Seats
//        HBox seatsHbox = new HBox();
//        Label seatsLabel = new Label();
//        seatsLabel.setText("No. of Seats");
//        styleLabel(seatsLabel, false);
//
//        TextField seatsFiled = new TextField();
//        seatsFiled.setOnMouseExited(event -> eventEntity.setConstraints(seatsFiled.getText()));
//
//
//        Button press = new Button("Add Event");
//        styleButton(press, 0);
//        press.setOnAction(event -> {
//            System.out.println(eventEntity);
//        });
//
//        styleHBox(seatsHbox, 1);
//        seatsHbox.getChildren().addAll(seatsLabel, seatsFiled);
//
//
//        addEventVBox.getChildren().addAll(titleHbox, descriptionHbox, onlineComboBox, locationHbox, startDateHbox,
//                endDateHbox,
//                seatsFiled,
//                press);
//        addEventVBox.setStyle("-fx-background-color: red");


//        if (eventService.addEvent(eventEntity)) {
//            //use a notification and a button
//            System.out.println("Event created");
//        }
    }


}




