package utils;

import java.util.HashMap;
import java.util.Map;
import javafx.scene.control.TextField;

public class Validator {

    private Validator() {
    }

    public static final Map<Boolean, String> errors = new HashMap<>();

    @SafeVarargs
    public static <T extends TextField> Boolean validate(T... widget) {
        if (widget.length != 0) {
            for (T node : widget) {
                if (node.getText().isEmpty()) {
                    errors.put(false, "Some fields are empty");
                } else if (node.getText().length() < 0) { //TODO: change this at the end
                    errors.put(false, "Field text is too short");
                } else if (node.getText().length() > 60) {
                    errors.put(false, "Field text is too long");
                }
            }
        }
        return (errors.size() == 0);
    }
}
