package alert;

import java.util.Objects;
import javafx.concurrent.Task;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class AlertPane {

    private static Pane errorPane;
    private static Text errorMessage;
    private static AlertPane instance;

    private AlertPane(Pane mPane, Text mTextField) {
        errorPane = mPane;
        errorMessage = mTextField;
        instance = this;
    }

    public static AlertPane getInstance() {
        return instance;
    }

    public static void createInstance(Pane pane, Text mTextField) {
        Objects.requireNonNull(pane, "Node cannot be null");
        Objects.requireNonNull(mTextField, "Text widget cannot be null");
        if (instance == null) {
            synchronized (AlertPane.class) {
                if (instance == null) {
                    try {
                        new AlertPane(pane, mTextField);
                    } catch (NullPointerException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            AlertPane.errorPane = pane;
            errorMessage = mTextField;
        }
    }

    public static void removeInstance() {
        if (instance != null) {
            instance = null;
        }
    }

    public static void show(String message, AlertColor colour) {
        if (message != null) {
            getInstance().showNotification(message, colour);
        }
    }

    private void showNotification(String message, AlertColor colour) {
        errorPane.setVisible(true);
        errorMessage.setText(message);
        switch (colour) {
            case INFO:
                errorPane.setStyle("-fx-background-color: rgba(217,245,255,0.27)");
                break;
            case WARNING:
                errorPane.setStyle("-fx-background-color: rgba(254,187,2,0.33)");
                break;
            case ERROR:
                errorPane.setStyle("-fx-background-color: rgba(220,13,0,0.61)");
                break;
            case SUCCESS:
                errorPane.setStyle("-fx-background-color: rgba(59,255,55,0.22)");
                break;
            case NONE:
                errorPane.setStyle("-fx-background-color: transparent");
                break;
        }
        errorMessage.setTextAlignment(TextAlignment.CENTER);
        Task task = hideAlert();
        new Thread(task).start();
    }

    private Task hideAlert() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                Thread.sleep(2500);
                errorPane.setVisible(false);
                return true;
            }
        };
    }
}
