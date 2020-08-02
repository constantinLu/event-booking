package controller;

import entities.Event;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import service.EventService;
import service.EventServiceImpl;

public class EventsController implements Initializable {

    private VBox eventVbox;

    Button bookEventButton;

    private CheckBox eventCheckbox;


    private EventService eventService = new EventServiceImpl();

    public EventsController() {
    }

    public EventsController(VBox eventVbox, Button bookEventButton) {
        this.eventVbox = eventVbox;
        this.bookEventButton = bookEventButton;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }


    public void isChecboxTicked() {

    }

    public void addEvents(VBox addEventVBox) {

        Label label = new Label("Text");
        addEventVBox.getChildren().add(label);
        addEventVBox.setVisible(true);
//        Event eventEntity = new Event();
//        HBox titleHbox = new HBox();
//
//        Label titleLabel = new Label();
//        titleLabel.setText("Title");
//        styleLabel(titleLabel, false);
//        TextField titleFiled = new TextField();
//
//        titleHbox.getChildren().addAll(titleLabel, titleFiled);
//
//        HBox descriptionHbox = new HBox();
//        Label descriptionLabel = new Label();
//        descriptionLabel.setText("Description");
//        styleLabel(descriptionLabel, false);
//        TextField descriptionField = new TextField();
//        descriptionField.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                eventEntity.setTitle(event.getTarget().toString());
//            }
//        });
//
//
//        descriptionHbox.getChildren().addAll(descriptionLabel, descriptionField);
//
//
//        addEventVBox.getChildren().addAll(titleHbox, descriptionHbox);
//
//
//        //redirectPane(actionEvent, CARD);
//
//        eventEntity.setEventId(1);
//        eventEntity.setLocation("Roman");
//        eventEntity.setTitle("Teatru");
//        eventEntity.setBookingAllowed(true);
//        eventEntity.setOnline(true);
//        eventEntity.setConstraints("10");
//        eventEntity.setDescription("Testing an event");
//        eventEntity.setStartDate(LocalDateTime.now());
//        eventEntity.setEndDate(LocalDateTime.of(2020, 10, 21, 9, 15, 0));
//        if (eventService.addEvent(eventEntity)) {
//            //use a notification and a button
//            System.out.println("Event created");
//        }
    }


    void getEvents() {
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

        CheckBox checkBoxLabel = new CheckBox();
        checkBoxLabel.setSelected(false);
        checkBoxLabel.setAlignment(Pos.CENTER_LEFT);
        checkBoxLabel.setVisible(false);


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
        noOfSeatsLabel.setText("No. of seats");
        styleLabel(noOfSeatsLabel, true);


        hBox.getChildren().add(checkBoxLabel);
        hBox.getChildren().add(imageViewLabel);
        hBox.getChildren().add(titleLabel);
        hBox.getChildren().add(descriptionLabel);
        hBox.getChildren().add(locationLabel);
        hBox.getChildren().add(startDateLabel);
        hBox.getChildren().add(endDateLabel);
        hBox.getChildren().add(noOfSeatsLabel);


        styleHBox(hBox, -1);
        vBox.getChildren().add(hBox);


    }


    private HBox createHbox(Event eventEntity) {
        HBox hBox = new HBox();

//        CheckBox checkBox = new CheckBox();
//        checkBox.setSelected(false);
//        checkBox.setAlignment(Pos.CENTER_LEFT);
        Button button = new Button();
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(eventEntity.getEventId());
            }
        });


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


        Label numberOfSeats = new Label();
        numberOfSeats.setText(String.valueOf(eventEntity.getConstraints()));
        styleLabel(numberOfSeats, false);


        hBox.getChildren().add(button);
        hBox.getChildren().add(imageView);
        hBox.getChildren().add(title);
        hBox.getChildren().add(description);
        hBox.getChildren().add(location);
        hBox.getChildren().add(startDate);
        hBox.getChildren().add(endDate);
        hBox.getChildren().add(numberOfSeats);

        return hBox;

    }


    /**
     * -fx-background-color: transparent
     * -fx-border-color: red");
     * -fx-border-width: 2 2 2 2");
     * -fx-border-width
     * -fx-border-color
     *
     * @param hbox
     * @param value
     */
    private void styleHBox(HBox hbox, int value) {
        hbox.setSpacing(20);
        hbox.setAlignment(Pos.CENTER_LEFT);
        HBox.setMargin(hbox, new Insets(5, 20, 5, 5));
        hbox.setPadding(new Insets(20, 20, 20, 10));
        if (value % 2 == 0) {
            hbox.setStyle("-fx-background-color: #e4e7ed");
        } else {
            hbox.setStyle("-fx-background-color: #FFFFFF");
        }
        // if hbox is header change to yellow
        if (value == -1) {
            hbox.setStyle("-fx-background-color:#febb02");
        }
    }


    private String styleImageView(int value) {
        if (value % 2 == 0) {
            return ("-fx-background-color: transparent");
        } else {
            return ("-fx-background-color: red");
        }
    }


    private void styleLabel(Node node, boolean isHeader) {
        Label object = null;
        if (node instanceof Label) {
            object = (Label) node;
        }
        if (isHeader) {
            object.setFont(new Font("Arial Bold", 16));
            object.setTextFill(Color.web("#FFFFFF"));
        } else {
            object.setFont(new Font("Lucida Sans Demibold", 12));
            object.setTextFill(Color.web("#000000"));
        }

        object.setAlignment(Pos.CENTER_LEFT);
        int width = 100;
        int height = 50;
        object.setMinWidth(width);
        object.setMaxWidth(width);
        object.setPrefWidth(width);

        object.setMinHeight(height);
        object.setPrefHeight(height);
        object.setMaxHeight(height);
    }


    private void styleText(Text text) {
        text.setFont(new Font("Lucida Sans Demibold", 12));
        text.setWrappingWidth(100);
    }


    private String formatLocalDateTime(LocalDateTime localDateTime) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        return localDateTime.format(dtf);
    }


    public CheckBox getEventCheckbox() {
        return eventCheckbox;
    }
}




