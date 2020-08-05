package controller;

import static alert.AlertColor.ERROR;
import static alert.AlertColor.SUCCESS;
import alert.AlertPane;
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

public class AddEventsController implements Initializable {

    private EventService eventService = new EventServiceImpl();

    private User loggedUser;

    public AddEventsController(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    TextField titleTextField;

    TextField descriptionTextField;

    TextField locationTextField;

    DatePicker startDatePicker;

    DatePicker endDatePicker;

    TextField seats;


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
                            titleTextField = (TextField) child;
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
                            descriptionTextField = (TextField) child;
                            addEvent.setDescription(descriptionTextField.getText());
                        });
                        break;

                    case "addLocation":
                        child.setOnMouseExited(event ->
                        {
                            locationTextField = (TextField) child;
                            addEvent.setLocation(locationTextField.getText());
                        });
                        break;

                    case "startDatePicker":
                        startDatePicker = (DatePicker) child;
                        startDatePicker.setOnAction(event -> {
                            addEvent.setStartDate(LocalDateTime.of(startDatePicker.getValue(), LocalTime.MIDNIGHT));
                        });
                        break;

                    case "endDatePicker":
                        endDatePicker = (DatePicker) child;
                        endDatePicker.setOnAction(event -> {
                            addEvent.setEndDate(LocalDateTime.of(endDatePicker.getValue(), LocalTime.MIDNIGHT));
                        });
                        break;

                    case "addSeats":
                        child.setOnMouseExited(event ->
                        {
                            seats = (TextField) child;
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
                                        if (validateFields(addEvent)) {
                                            eventService.addEvent(addEvent);
                                            titleTextField.setText("");
                                            descriptionTextField.setText("");
                                            locationTextField.setText("");
                                            //TODO: THROWS ERROR WHEN SET TO NULL :-??
                                            startDatePicker.setValue(null);
                                            endDatePicker.setValue(null);
                                            seats.setText("");
                                            AlertPane.show("Event Added", SUCCESS);
                                        } else {
                                            AlertPane.show("All fields are required", ERROR);

                                        }
                                }
                        );
                }
            }
        });
    }

    private boolean validateFields(Event event) {
        if (event.getTitle() != null && event.getDescription() != null && event.getLocation() != null &&
                event.getStartDate() != null && event.getEndDate() != null && event.getConstraints() != null) {
            return true;
        }
        return false;
    }
}




