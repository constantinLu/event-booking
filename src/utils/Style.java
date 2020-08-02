package utils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class Style {

    /**
     * -fx-background-color: transparent
     * -fx-border-color: red");
     * -fx-border-width: 2 2 2 2");
     * -fx-border-width
     * -fx-border-color
     *
     * if -1 is a header yellow
     * if odd - white
     * if even - grey
     * if
     * @param hbox
     * @param value
     */
    public static void styleHBox(HBox hbox, int value) {
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

    /**
     * style box for addEvent
     * @param hbox
     * @param value
     * 1.0 - default color
     */
    public static void styleHBox(HBox hbox, double value) {
            hbox.setSpacing(20);
            hbox.setAlignment(Pos.CENTER_LEFT);
            HBox.setMargin(hbox, new Insets(5, 20, 5, 5));
            hbox.setPadding(new Insets(20, 20, 20, 10));
    }


    public static String styleImageView(int value) {
        if (value % 2 == 0) {
            return ("-fx-background-color: transparent");
        } else {
            return ("-fx-background-color: red");
        }
    }


    public static void styleLabel(Node node, boolean isHeader) {
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
        if (object.getId() != null) {
            if (object.getId().equals("noOfSeats") || object.getId().equals("online")) {
                width = 70;
            }
        }

        object.setMinWidth(width);
        object.setMaxWidth(width);
        object.setPrefWidth(width);

        object.setMinHeight(height);
        object.setPrefHeight(height);
        object.setMaxHeight(height);
    }


    public static void styleText(Text text) {
        text.setFont(new Font("Lucida Sans Demibold", 12));
        text.setWrappingWidth(100);
    }

    public static void styleButton(Button button, int value) {
        switch (value) {
            case 0:
                button.setTextFill(Color.web("#FFFFFF"));
                button.setFont(new Font("System Bold", 15));
                break;
            default:
                break;
        }
    }

}
