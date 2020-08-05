package utils;

import alert.AlertPane;
import javafx.scene.control.TextField;

public class Validators {

    private Validators() {
    }

//    @SafeVarargs
//    public static <T extends TextField> boolean validate(T... widget) {
//        if (widget.length != 0) {
//            for (T element : widget) {
//                if (element.getText().isEmpty()) {
//                    AlertPane.show("Some fields are empty");
//                    return false;
//                } else if (element.getText().length() < 4) {
//                    AlertPane.show("Entry is too short");
//                    return false;
//                }
//            }
//            return true;
//        } else return false;
//    }


}
