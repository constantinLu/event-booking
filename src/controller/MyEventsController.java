package controller;

import alert.Alert;
import alert.AlertColor;
import alert.AlertPane;
import entities.Event;
import entities.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import service.EventService;
import service.EventServiceImpl;
import service.UserService;
import service.UserServiceImpl;
import static utils.DateHelper.formatLocalDateTime;
import static utils.Style.*;

public class MyEventsController implements Alert, Initializable {

    @FXML
    public Pane errorPane;
    @FXML
    public Text errorCode;

    private EventService eventService = new EventServiceImpl();

    private UserService userService = new UserServiceImpl();

    User loggedUser;

    public MyEventsController(User user) {
        this.loggedUser = user;
    }

    private VBox myEventBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initializeAlertPane();
    }

    @Override
    public void initializeAlertPane() {
        AlertPane.createInstance(errorPane, errorCode);
    }

    void getEvents(VBox eventVbox) {
        myEventBox = eventVbox;
        addHeader(myEventBox);
        //get all events
        List<Event> events = eventService.getEventsOrganisedByUser(loggedUser.getUserId());
        for (int i = 0; i < events.size(); i++) {
            Event eventEntity = events.get(i);

            HBox hbox = createHbox(eventEntity);
            styleHBox(hbox, i);

            myEventBox.getChildren().add(hbox);
            myEventBox.setSpacing(5);
            myEventBox.setVisible(true);
            VBox.setMargin(hbox, new Insets(5, 5, 5, 5));
            myEventBox.setPadding(new Insets(10, 10, 10, 10));
        }
    }


    private void addHeader(VBox vBox) {
        HBox hBox = new HBox();

        ImageView imageViewLabel = new ImageView();
        imageViewLabel.setImage(new Image(getClass().getResourceAsStream("/resources/images/event.png")));
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


        Label editButtonLabel = new Label();
        editButtonLabel.setText("Edit");
        styleLabel(editButtonLabel, true);


        Label deleteLabel = new Label();
        deleteLabel.setText("Delete");
        styleLabel(deleteLabel, true);

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
                editButtonLabel,
                deleteLabel);

        styleHBox(hBox, -1);
        vBox.getChildren().add(hBox);
    }


    private HBox createHbox(Event eventEntity) {
        HBox hBox = new HBox();

        ImageView imageView = new ImageView();
        String image;
        if (true) { //change based of available seats.
            image = "/resources/images/event.png";
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

        Button editButton = new Button();
        editButton.setText("Edit");
        editButton.setDisable(false);
        editButton.setStyle("-fx-background-color: #febb02");
        styleButton(editButton, 0);
        editButton.setDisable(false);
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO: ADD TO BOOKED EVENTS LIST
                System.out.println(eventEntity.getEventId());
            }
        });

        Button deleteButton = new Button();
        deleteButton.setText("Remove");
        deleteButton.setDisable(false);
        deleteButton.setStyle("-fx-background-color: #febb02");


        styleButton(deleteButton, 1);
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO: ADD TO BOOKED EVENTS LIST
                if (eventService.removeEvent(eventEntity.getEventId())) {
                    AlertPane.show("Event deleted", AlertColor.SUCCESS);
                    eventService.getEventsOrganisedByUser(loggedUser.getUserId());
                    myEventBox.requestLayout();
                } else {
                    AlertPane.show("Error while deleting event", AlertColor.ERROR);
                }
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
                editButton,
                deleteButton);

        return hBox;
    }
}




