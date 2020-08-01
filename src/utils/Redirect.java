package utils;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Redirect {

    public void redirect(ActionEvent actionEvent, String path) throws IOException {

        Parent registerPage = FXMLLoader.load(getClass().getResource(path));
        Scene registerPageScene = new Scene(registerPage);
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        appStage.setScene(registerPageScene);
        appStage.show();
    }
}
