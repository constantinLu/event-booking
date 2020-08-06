package controller;

import alert.Alert;
import alert.AlertColor;
import alert.AlertPane;
import entities.Event;
import entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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
import utils.Path;
import utils.Redirect;
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

    public MyEventsController(User user, ScrollPane pane, VBox box) {
        this.loggedUser = user;
        this.box = box;
        this.pane = pane;
    }

    private VBox myEventBox;
    private VBox box;
    private ScrollPane pane;

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


        Label viewDeleteLabel = new Label();
        viewDeleteLabel.setText("    Actions  ");
        styleLabel(viewDeleteLabel, true);

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
                viewDeleteLabel);

        styleHBox(hBox, -1);
        vBox.getChildren().add(hBox);
    }


    private HBox createHbox(Event eventEntity) {
        HBox hBox = new HBox();

        ImageView imageView = new ImageView();
        imageView.setImage(new Image(getClass().getResourceAsStream("/resources/images/event.png")));
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


        //VBOX
        VBox buttons = new VBox();
        buttons.setSpacing(5);
        buttons.setAlignment(Pos.CENTER);

        Button viewDetailsButton = new Button();
        viewDetailsButton.setText("View Details");
        viewDetailsButton.setDisable(false);
        viewDetailsButton.setStyle("-fx-background-color: #febb02");
        viewDetailsButton.setDisable(false);

        styleButton(viewDetailsButton, 0);
        viewDetailsButton.setOnAction(event -> {
            //TODO: IMPLEMENT VIEW DETAILS
            try {
                new Redirect().openInfoEventModal(event, Path.EVENT_INFO, eventEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        Button deleteButton = new Button();
        deleteButton.setText("Remove");
        deleteButton.setDisable(false);
        deleteButton.setStyle("-fx-background-color: #febb02");
        viewDetailsButton.setDisable(false);
        styleButton(deleteButton, 1);

        deleteButton.setOnAction(event -> {
            if (eventService.removeEvent(eventEntity.getEventId())) {
                //TODO: FIND HOW TO REFRESH THE PANE
                AlertPane.show("Event deleted", AlertColor.SUCCESS);
            } else {
                AlertPane.show("Error while deleting event", AlertColor.ERROR);
            }
        });
        buttons.getChildren().addAll(viewDetailsButton, deleteButton);


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
                buttons);

        return hBox;
    }
}




