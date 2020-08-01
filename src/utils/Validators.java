package utils;

import view.NotificationPane;
import javafx.scene.control.TextField;

public class Validators {

    private Validators() {
    }

    @SafeVarargs
    public static <T extends TextField> boolean validate(T... widget) {
        if (widget.length != 0) {
            for (T element : widget) {
                if (element.getText().isEmpty()) {
                    NotificationPane.show("Some fields are empty");
                    return false;
                } else if (element.getText().length() < 4) {
                    NotificationPane.show("Entry is too short");
                    return false;
                }
            }
            return true;
        } else return false;
    }


}
