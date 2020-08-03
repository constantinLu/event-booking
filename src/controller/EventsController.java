package controller;

import entities.Event;
import entities.User;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

public class EventsController {

    private EventService eventService = new EventServiceImpl();

    private UserService userService = new UserServiceImpl();


    private User loggedUser;

    public EventsController(User loggedUser) {
        this.loggedUser = loggedUser;
    }

    public void addEvents(VBox addEventVBox) {

        //obj to persist
        Event eventEntity = new Event();

        //Title
        HBox titleHbox = new HBox();
        Label titleLabel = new Label();
        titleLabel.setText("Title");
        styleLabel(titleLabel, false);

        TextField titleFiled = new TextField();
        titleFiled.setOnMouseExited(event -> {
            System.out.println("Test title");
            eventEntity.setTitle(titleFiled.getText());
        });
        styleHBox(titleHbox, 1);
        titleHbox.getChildren().addAll(titleLabel, titleFiled);


        //Description
        HBox descriptionHbox = new HBox();
        Label descriptionLabel = new Label();
        descriptionLabel.setText("Description");
        styleLabel(descriptionLabel, false);

        TextField descriptionField = new TextField();
        descriptionField.setOnMouseExited(event -> {
            System.out.println("Testing Description");
            eventEntity.setDescription(descriptionField.getText());
        });
        styleHBox(descriptionHbox, 0);
        descriptionHbox.getChildren().addAll(descriptionLabel, descriptionField);


        //Online
        HBox onlineHbox = new HBox();
        Label goOnlineLabel = new Label();
        goOnlineLabel.setText("Go online");
        styleLabel(goOnlineLabel, false);

        final ComboBox onlineComboBox = new ComboBox();
        onlineComboBox.getItems().addAll("YES", "NO");
        onlineComboBox.setOnAction(event -> {
            System.out.println("Testing combobox");
            if (onlineComboBox.getSelectionModel().getSelectedItem().toString().equals("YES")) {
                eventEntity.setOnline(true);
            } else {
                eventEntity.setOnline(false);

            }
        });
        styleHBox(onlineHbox, 1);
        onlineHbox.getChildren().addAll(goOnlineLabel, onlineComboBox);


        //Location
        HBox locationHbox = new HBox();
        Label locationLabel = new Label();
        locationLabel.setText("Location");
        styleLabel(locationLabel, false);

        TextField locationField = new TextField();
        locationField.setOnMouseExited(event -> {
            System.out.println("Testing location");
            eventEntity.setLocation(locationField.getText());
        });
        styleHBox(locationHbox, 0);
        descriptionHbox.getChildren().addAll(locationLabel, locationField);


        //startDate
        HBox startDateHbox = new HBox();
        Label startDateLabel = new Label();
        startDateLabel.setText("Start Date");
        styleLabel(startDateLabel, false);

        DatePicker startDatePicker = new DatePicker();
        startDatePicker.setOnAction(event -> {
            System.out.println("Test startDate");
            eventEntity.setStartDate(LocalDateTime.of(startDatePicker.getValue(), LocalTime.MIDNIGHT));
        });
        styleHBox(startDateHbox, 1);
        descriptionHbox.getChildren().addAll(startDateLabel, startDatePicker);


        //startDate
        HBox endDateHbox = new HBox();
        Label endDateLabel = new Label();
        endDateLabel.setText("End Date");
        styleLabel(endDateLabel, false);

        DatePicker endDatePicker = new DatePicker();
        endDatePicker.setOnAction(event -> {
            System.out.println("Test endDate");
            eventEntity.setEndDate(LocalDateTime.of(endDatePicker.getValue(), LocalTime.MIDNIGHT));
        });
        styleHBox(endDateHbox, 0);
        descriptionHbox.getChildren().addAll(endDateLabel, endDatePicker);


        //Seats
        HBox seatsHbox = new HBox();
        Label seatsLabel = new Label();
        seatsLabel.setText("No. of Seats");
        styleLabel(seatsLabel, false);

        TextField seatsFiled = new TextField();
        seatsFiled.setOnMouseExited(event -> eventEntity.setConstraints(seatsFiled.getText()));


        Button press = new Button("Add Event");
        styleButton(press, 0);
        press.setOnAction(event -> {
            System.out.println(eventEntity);
        });

        styleHBox(seatsHbox, 1);
        seatsHbox.getChildren().addAll(seatsLabel, seatsFiled);


        addEventVBox.getChildren().addAll(titleHbox, descriptionHbox, onlineComboBox, locationHbox, startDateHbox,
                endDateHbox,
                seatsFiled,
                press);
        addEventVBox.setStyle("-fx-background-color: red");


//        if (eventService.addEvent(eventEntity)) {
//            //use a notification and a button
//            System.out.println("Event created");
//        }
    }


    void getEvents(VBox eventVbox) {
        addHeader(eventVbox);
        //get all events
        List<Event> events = eventService.getAllEvents();
        for (int i = 0; i < events.size(); i++) {
            Event eventEntity = events.get(i);

            HBox hbox = createHbox(eventEntity);
            styleHBox(hbox, i);

            eventVbox.getChildren().add(hbox);
            eventVbox.setSpacing(5);
            eventVbox.setVisible(true);
            VBox.setMargin(hbox, new Insets(5, 5, 5, 5));
            eventVbox.setPadding(new Insets(10, 10, 10, 10));
        }
    }


    private void addHeader(VBox vBox) {
        HBox hBox = new HBox();

        ImageView imageViewLabel = new ImageView();
        imageViewLabel.setImage(new Image(getClass().getResourceAsStream("/resources/images/eventIcon.png")));
        imageViewLabel.setFitHeight(50);
        imageViewLabel.setFitWidth(50);
        imageViewLabel.setVisible(false);


        Label titleLabel = new Label();
        titleLabel.setText("Event Title");
        styleLabel(titleLabel, true);

        Label descriptionLabel = new Label();
        descriptionLabel.setText("Description");
        styleLabel(descriptionLabel, true);

        Label locationLabel = new Label();
        locationLabel.setText("Location");
        styleLabel(locationLabel, true);

        Label startDateLabel = new Label();
        startDateLabel.setText("Event starts");
        styleLabel(startDateLabel, true);

        Label endDateLabel = new Label();
        endDateLabel.setText("Event ends");
        styleLabel(endDateLabel, true);

        Label noOfSeatsLabel = new Label();
        noOfSeatsLabel.setId("noOfSeats");
        noOfSeatsLabel.setText("Seats");
        styleLabel(noOfSeatsLabel, true);


        Label onlineLabel = new Label();
        onlineLabel.setId("online");
        onlineLabel.setText("Online");
        styleLabel(onlineLabel, true);

        Label organiserLabel = new Label();
        organiserLabel.setText("Organiser");
        styleLabel(organiserLabel, true);


        Label buttonLabel = new Label();
        buttonLabel.setText("Book Event");
        styleLabel(buttonLabel, true);


        hBox.getChildren().addAll(
                imageViewLabel,
                titleLabel,
                descriptionLabel,
                locationLabel,
                startDateLabel,
                endDateLabel,
                noOfSeatsLabel,
                onlineLabel,
                organiserLabel,
                buttonLabel);

        styleHBox(hBox, -1);
        vBox.getChildren().add(hBox);
    }


    private HBox createHbox(Event eventEntity) {
        HBox hBox = new HBox();

        ImageView imageView = new ImageView();
        String image;
        if (true) { //change based of available seats.
            image = "/resources/images/eventIcon.png";
        } else {
            image = "/resources/images/event_full.png";
        }
        imageView.setImage(new Image(getClass().getResourceAsStream(image)));
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);


        Label title = new Label();
        title.setText(eventEntity.getTitle());
        styleLabel(title, false);

        Text description = new Text();
        description.setText(eventEntity.getDescription());
        styleText(description);

        Label location = new Label();
        location.setText(eventEntity.getLocation());
        styleLabel(location, false);

        Label startDate = new Label();
        startDate.setText(formatLocalDateTime(eventEntity.getStartDate()));
        styleLabel(startDate, false);


        Label endDate = new Label();
        endDate.setText(formatLocalDateTime(eventEntity.getEndDate()));
        styleLabel(endDate, false);


        Label noOfSeats = new Label();
        noOfSeats.setId("noOfSeats");
        noOfSeats.setText(String.valueOf(eventEntity.getConstraints()));
        styleLabel(noOfSeats, false);


        Label online = new Label();
        online.setId("online");

        styleLabel(online, false);
        if (eventEntity.isOnline()) {
            online.setText(String.valueOf("YES"));
            online.setTextFill(Color.valueOf("green"));
        } else {
            online.setText(String.valueOf("NO"));
            online.setTextFill(Color.valueOf("red"));
        }


        Label organiser = new Label();
        String oraniserName = null;
        try {
            oraniserName = userService.getOrganiserName(eventEntity.getOrganiserId());
        } catch (Exception e) {
            System.out.println("Organiser is null");
            oraniserName = "Administrator";
        }
        organiser.setText(oraniserName);
        styleLabel(organiser, false);

        Button button = new Button();
        boolean isBooked = eventService.isEventAlreadyBooked(eventEntity.getEventId(),loggedUser.getUserId());
        setStyleButton(button,isBooked);
        styleButton(button, 0);
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO: ADD TO BOOKED EVENTS LIST
                System.out.println(eventEntity.getEventId());
                boolean isBooked = eventService.bookEvent(eventEntity,loggedUser.getUserId());
                setStyleButton(button,isBooked);
            }
        });


        hBox.getChildren().addAll(
                imageView,
                title,
                description,
                location,
                startDate,
                endDate,
                noOfSeats,
                online,
                organiser,
                button);

        return hBox;
    }

    private void setStyleButton(Button button, boolean isBooked){
        if (!isBooked) {
            button.setText("Book");
            button.setDisable(false);
            button.setStyle("-fx-background-color: #febb02");
        } else {
            button.setText("Booked");
            button.setDisable(true);
            button.setStyle("-fx-background-color: #00b300");
        }
    }
}




