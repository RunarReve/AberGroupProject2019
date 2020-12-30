package gpui;

import java.lang.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public abstract class editLocation extends AnchorPane {

    protected final Label label;
    protected final Rectangle rectangle;
    protected final Button button;
    protected final Button button0;

    public editLocation() {

        label = new Label();
        rectangle = new Rectangle();
        button = new Button();
        button0 = new Button();

        setId("AnchorPane");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        label.setLayoutX(32.0);
        label.setLayoutY(31.0);
        label.setText("Please Select a Location:");

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.DODGERBLUE);
        rectangle.setHeight(259.0);
        rectangle.setLayoutX(61.0);
        rectangle.setLayoutY(66.0);
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.setWidth(482.0);

        button.setLayoutX(41.0);
        button.setLayoutY(353.0);
        button.setMnemonicParsing(false);
        button.setText("Close Without Saving");

        button0.setLayoutX(486.0);
        button0.setLayoutY(353.0);
        button0.setMnemonicParsing(false);
        button0.setText("Save and Close");

        getChildren().add(label);
        getChildren().add(rectangle);
        getChildren().add(button);
        getChildren().add(button0);

    }
}
