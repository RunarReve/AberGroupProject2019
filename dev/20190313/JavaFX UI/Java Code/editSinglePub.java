package gpui;

import java.lang.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;

public abstract class editSinglePub extends AnchorPane {

    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Label label4;
    protected final TextField textField;
    protected final TextField textField0;
    protected final Button button;
    protected final Button button0;
    protected final Button button1;
    protected final Button button2;
    protected final Button button3;

    public editSinglePub() {

        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label4 = new Label();
        textField = new TextField();
        textField0 = new TextField();
        button = new Button();
        button0 = new Button();
        button1 = new Button();
        button2 = new Button();
        button3 = new Button();

        setId("editSinglePub");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        label.setLayoutX(31.0);
        label.setLayoutY(22.0);
        label.setText("You are currently editing .");

        label0.setLayoutX(31.0);
        label0.setLayoutY(52.0);
        label0.setText("Name");

        label1.setLayoutX(31.0);
        label1.setLayoutY(90.0);
        label1.setText("Description");

        label2.setLayoutX(31.0);
        label2.setLayoutY(168.0);
        label2.setText("GPS Coordinates");

        label3.setLayoutX(31.0);
        label3.setLayoutY(211.0);
        label3.setText("Characteristics");

        label4.setLayoutX(31.0);
        label4.setLayoutY(265.0);
        label4.setText("Photos");

        textField.setLayoutX(136.0);
        textField.setLayoutY(49.0);
        textField.setPrefWidth(200.0);

        textField0.setLayoutX(136.0);
        textField0.setLayoutY(87.0);
        textField0.setPrefHeight(72.0);
        textField0.setPrefWidth(295.0);

        button.setLayoutX(136.0);
        button.setLayoutY(166.0);
        button.setMnemonicParsing(false);
        button.setText("Choose Location");

        button0.setLayoutX(136.0);
        button0.setLayoutY(263.0);
        button0.setMnemonicParsing(false);
        button0.setText("Manage Photos");

        button1.setLayoutX(22.0);
        button1.setLayoutY(348.0);
        button1.setMnemonicParsing(false);
        button1.setText("Back");

        button2.setLayoutX(485.0);
        button2.setLayoutY(348.0);
        button2.setMnemonicParsing(false);
        button2.setText("Save Changes");

        button3.setLayoutX(136.0);
        button3.setLayoutY(209.0);
        button3.setMnemonicParsing(false);
        button3.setText("Edit Characteristics");

        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(label3);
        getChildren().add(label4);
        getChildren().add(textField);
        getChildren().add(textField0);
        getChildren().add(button);
        getChildren().add(button0);
        getChildren().add(button1);
        getChildren().add(button2);
        getChildren().add(button3);

    }
}
