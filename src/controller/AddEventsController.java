package controller;

import entities.Event;
import entities.User;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import service.EventService;
import service.EventServiceImpl;
import service.UserService;
import service.UserServiceImpl;

public class AddEventsController implements Initializable {

    private EventService eventService = new EventServiceImpl();

    private UserService userService = new UserServiceImpl();

    private User loggedUser;

    public AddEventsController(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void addEvents(Pane addPane) {

        Event addEvent = new Event();
        addPane.getChildren().forEach(child ->
        {
            //for components
            if (child.getId() != null) {
                switch (child.getId()) {
                    case "addTitle":
                        child.setOnMouseExited(event ->
                        {
                            TextField titleTextField = (TextField) child;
                            addEvent.setTitle(titleTextField.getText());
                        });
                        break;
                    case "isOnlineDropDown":
                        ChoiceBox onlineChoiceBox = (ChoiceBox) child;
                        onlineChoiceBox.setOnAction(event -> {
                            onlineChoiceBox.setValue(onlineChoiceBox.getSelectionModel().getSelectedItem());
                            addEvent.setOnline(onlineChoiceBox.getValue() == "YES");
                        });
                        break;

                    case "addDescription":
                        child.setOnMouseExited(event ->
                        {
                            TextField titleTextField = (TextField) child;
                            addEvent.setDescription(titleTextField.getText());
                        });
                        break;

                    case "addLocation":
                        child.setOnMouseExited(event ->
                        {
                            TextField locationTextField = (TextField) child;
                            addEvent.setLocation(locationTextField.getText());
                        });
                        break;

                    case "startDatePicker":
                        DatePicker startDatePicker = (DatePicker) child;
                        startDatePicker.setOnAction(event -> {
                            addEvent.setStartDate(LocalDateTime.of(startDatePicker.getValue(), LocalTime.MIDNIGHT));
                        });
                        break;

                    case "endDatePicker":
                        DatePicker endDatePicker = (DatePicker) child;
                        endDatePicker.setOnAction(event -> {
                            addEvent.setEndDate(LocalDateTime.of(endDatePicker.getValue(), LocalTime.MIDNIGHT));
                        });
                        break;

                    case "addSeats":
                        child.setOnMouseExited(event ->
                        {
                            TextField seats = (TextField) child;
                            addEvent.setConstraints(seats.getText());
                        });
                        break;
                    case "isBookingAllowed":
                        ChoiceBox bookingAllowed = (ChoiceBox) child;
                        bookingAllowed.setOnAction(event -> {
                            bookingAllowed.setValue(bookingAllowed.getSelectionModel().getSelectedItem());
                            addEvent.setBookingAllowed(bookingAllowed.getValue() == "YES");
                        });
                        break;
                    case "addEventButton":
                        Button addEventButton = (Button) child;
                        addEventButton.setOnAction(event ->
                                {
                                    addEvent.setOrganiserId(loggedUser.getUserId());
                                    eventService.addEvent(addEvent);
                                }
                        );

                }
            }
        });
    }

    private void clearValues() {

    }


}




