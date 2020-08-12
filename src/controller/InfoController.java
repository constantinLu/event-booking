package controller;

import entities.Event;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import utils.BooleanMapper;
import utils.DateHelper;
import static utils.Path.EVENT_INFO;
import utils.Style;

public class InfoController {
    @FXML
    TextFlow descriptionTextFlow;
    @FXML
    Text startDateText;
    @FXML
    Text endDateText;
    @FXML
    Text titleEventText;
    @FXML
    Text isBookingAllowedText;
    @FXML
    Text isOnlineText;
    @FXML
    Text locationText;

    @FXML
    Text descriptionText;

    @FXML
    Text noOfSeatsText;

    public void showInfoController(ActionEvent actionEvent, Event event) throws IOException {
        Stage infoEventDialog = new Stage();
        Stage parentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent eventInfoPage = FXMLLoader.load(getClass().getResource(EVENT_INFO));
        Scene eventInfoScene = new Scene(eventInfoPage);
        infoEventDialog.setScene(eventInfoScene);
        infoEventDialog.initOwner(parentStage);
        infoEventDialog.initModality(Modality.APPLICATION_MODAL);
        infoEventDialog.showAndWait();
    }

    public void initData(Event event) {
        descriptionText.setText(event.getDescription());

        titleEventText.setText(event.getTitle());
        startDateText.setText(DateHelper.formatLocalDateTime(event.getStartDate()));
        endDateText.setText(DateHelper.formatLocalDateTime(event.getEndDate()));
        locationText.setText(event.getLocation());
        noOfSeatsText.setText(event.getConstraints());

        isOnlineText.setText(BooleanMapper.mapForUi(event.isOnline()));
        Style.styleTextBasedOnBoolean(isOnlineText, event.isOnline());

        isBookingAllowedText.setText(BooleanMapper.mapForUi(event.isBookingAllowed()));
        Style.styleTextBasedOnBoolean(isBookingAllowedText, event.isBookingAllowed());


    }
}

