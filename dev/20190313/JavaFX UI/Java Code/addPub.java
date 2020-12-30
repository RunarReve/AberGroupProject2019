package gpui;

import java.lang.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;

public abstract class addPub extends AnchorPane {

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
    protected final Button button4;

    public addPub() {

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
        button4 = new Button();

        setId("AnchorPane");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        label.setLayoutX(35.0);
        label.setLayoutY(31.0);
        label.setText("You are currently adding pubs to Aberystwyth's list.");

        label0.setLayoutX(35.0);
        label0.setLayoutY(61.0);
        label0.setText("Name");

        label1.setLayoutX(35.0);
        label1.setLayoutY(99.0);
        label1.setText("Description");

        label2.setLayoutX(35.0);
        label2.setLayoutY(177.0);
        label2.setText("GPS Coordinates");

        label3.setLayoutX(35.0);
        label3.setLayoutY(220.0);
        label3.setText("Characteristics");

        label4.setLayoutX(35.0);
        label4.setLayoutY(274.0);
        label4.setText("Photos");

        textField.setLayoutX(140.0);
        textField.setLayoutY(58.0);
        textField.setPrefWidth(200.0);

        textField0.setLayoutX(140.0);
        textField0.setLayoutY(96.0);
        textField0.setPrefHeight(72.0);
        textField0.setPrefWidth(295.0);

        button.setLayoutX(140.0);
        button.setLayoutY(175.0);
        button.setMnemonicParsing(false);
        button.setText("Choose Location");

        button0.setLayoutX(140.0);
        button0.setLayoutY(272.0);
        button0.setMnemonicParsing(false);
        button0.setText("Manage Photos");

        button1.setLayoutX(26.0);
        button1.setLayoutY(357.0);
        button1.setMnemonicParsing(false);
        button1.setText("Back");

        button2.setLayoutX(346.0);
        button2.setLayoutY(349.0);
        button2.setMnemonicParsing(false);
        button2.setPrefHeight(38.0);
        button2.setPrefWidth(124.0);
        button2.setText("Save Changes and Return to Menu");
        button2.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button2.setWrapText(true);

        button3.setLayoutX(489.0);
        button3.setLayoutY(357.0);
        button3.setMnemonicParsing(false);
        button3.setText("Add Next Pub");

        button4.setLayoutX(140.0);
        button4.setLayoutY(220.0);
        button4.setMnemonicParsing(false);
        button4.setText("Add Charcteristics");

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
        getChildren().add(button4);

    }
}
