package utils;

import controller.InfoController;
import controller.MainPageController;
import entities.Event;
import entities.User;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Redirect {

    public void redirectToParent(ActionEvent actionEvent, String path) throws IOException {

        Parent registerPage = FXMLLoader.load(getClass().getResource(path));
        Scene registerPageScene = new Scene(registerPage);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(registerPageScene);
        appStage.show();
    }


    public void redirectToMainPage(ActionEvent actionEvent, String path, Object user) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource(path)));
        Parent view = loader.load();
        Scene scene = new Scene(view);

        MainPageController mainPageController = loader.getController();
        mainPageController.initData((User) user);

        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    public void redirectToLogin(ActionEvent actionEvent, String path) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource(path)));
        Parent view = loader.load();
        Scene scene = new Scene(view);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.show();
    }

    public void openInfoEventModal(ActionEvent actionEvent, String path, Event event) throws IOException {
        Stage infoEventDialog = new Stage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation((getClass().getResource(path)));
        Stage parentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        Parent eventInfoPage = loader.load();
        Scene eventInfoScene = new Scene(eventInfoPage);
        infoEventDialog.setTitle("Event Information");
        InfoController infoController = loader.getController();
        infoController.initData(event);
        infoEventDialog.setScene(eventInfoScene);
        infoEventDialog.initOwner(parentStage);
        infoEventDialog.initModality(Modality.APPLICATION_MODAL);
        infoEventDialog.showAndWait();
    }
}
