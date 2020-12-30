package gpui;

import java.lang.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;

public abstract class charPopUp extends AnchorPane {

    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Label label4;
    protected final Label label5;
    protected final Label label6;
    protected final Label label7;
    protected final RadioButton radioButton;
    protected final RadioButton radioButton0;
    protected final RadioButton radioButton1;
    protected final RadioButton radioButton2;
    protected final RadioButton radioButton3;
    protected final RadioButton radioButton4;
    protected final RadioButton radioButton5;
    protected final RadioButton radioButton6;
    protected final RadioButton radioButton7;
    protected final RadioButton radioButton8;
    protected final RadioButton radioButton9;
    protected final RadioButton radioButton10;
    protected final RadioButton radioButton11;
    protected final RadioButton radioButton12;
    protected final RadioButton radioButton13;
    protected final RadioButton radioButton14;
    protected final Button button;
    protected final Button button0;

    public charPopUp() {

        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label4 = new Label();
        label5 = new Label();
        label6 = new Label();
        label7 = new Label();
        radioButton = new RadioButton();
        radioButton0 = new RadioButton();
        radioButton1 = new RadioButton();
        radioButton2 = new RadioButton();
        radioButton3 = new RadioButton();
        radioButton4 = new RadioButton();
        radioButton5 = new RadioButton();
        radioButton6 = new RadioButton();
        radioButton7 = new RadioButton();
        radioButton8 = new RadioButton();
        radioButton9 = new RadioButton();
        radioButton10 = new RadioButton();
        radioButton11 = new RadioButton();
        radioButton12 = new RadioButton();
        radioButton13 = new RadioButton();
        radioButton14 = new RadioButton();
        button = new Button();
        button0 = new Button();

        setId("AnchorPane");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(340.0);
        setPrefWidth(261.0);

        label.setLayoutX(14.0);
        label.setLayoutY(14.0);
        label.setText("Characteristics");

        label0.setLayoutX(17.0);
        label0.setLayoutY(61.0);
        label0.setText("Food");

        label1.setLayoutX(17.0);
        label1.setLayoutY(90.0);
        label1.setText("Real Ale");

        label2.setLayoutX(17.0);
        label2.setLayoutY(117.0);
        label2.setText("Club");

        label3.setLayoutX(17.0);
        label3.setLayoutY(143.0);
        label3.setText("Allows Dogs");

        label4.setLayoutX(17.0);
        label4.setLayoutY(174.0);
        label4.setText("Welsh");

        label5.setLayoutX(17.0);
        label5.setLayoutY(207.0);
        label5.setText("Family Friendly");

        label6.setLayoutX(17.0);
        label6.setLayoutY(233.0);
        label6.setText("Jukebox");

        label7.setLayoutX(17.0);
        label7.setLayoutY(266.0);
        label7.setText("Loud Music");

        radioButton.setLayoutX(131.0);
        radioButton.setLayoutY(61.0);
        radioButton.setMnemonicParsing(false);
        radioButton.setText("True");

        radioButton0.setLayoutX(193.0);
        radioButton0.setLayoutY(61.0);
        radioButton0.setMnemonicParsing(false);
        radioButton0.setText("False");

        radioButton1.setLayoutX(131.0);
        radioButton1.setLayoutY(90.0);
        radioButton1.setMnemonicParsing(false);
        radioButton1.setText("True");

        radioButton2.setLayoutX(193.0);
        radioButton2.setLayoutY(90.0);
        radioButton2.setMnemonicParsing(false);
        radioButton2.setText("False");

        radioButton3.setLayoutX(131.0);
        radioButton3.setLayoutY(117.0);
        radioButton3.setMnemonicParsing(false);
        radioButton3.setText("True");

        radioButton4.setLayoutX(193.0);
        radioButton4.setLayoutY(117.0);
        radioButton4.setMnemonicParsing(false);
        radioButton4.setText("False");

        radioButton5.setLayoutX(131.0);
        radioButton5.setLayoutY(143.0);
        radioButton5.setMnemonicParsing(false);
        radioButton5.setText("True");

        radioButton6.setLayoutX(193.0);
        radioButton6.setLayoutY(143.0);
        radioButton6.setMnemonicParsing(false);
        radioButton6.setText("False");

        radioButton7.setLayoutX(131.0);
        radioButton7.setLayoutY(174.0);
        radioButton7.setMnemonicParsing(false);
        radioButton7.setText("True");

        radioButton8.setLayoutX(193.0);
        radioButton8.setLayoutY(174.0);
        radioButton8.setMnemonicParsing(false);
        radioButton8.setText("False");

        radioButton9.setLayoutX(131.0);
        radioButton9.setLayoutY(207.0);
        radioButton9.setMnemonicParsing(false);
        radioButton9.setText("True");

        radioButton10.setLayoutX(193.0);
        radioButton10.setLayoutY(207.0);
        radioButton10.setMnemonicParsing(false);
        radioButton10.setText("False");

        radioButton11.setLayoutX(131.0);
        radioButton11.setLayoutY(233.0);
        radioButton11.setMnemonicParsing(false);
        radioButton11.setText("True");

        radioButton12.setLayoutX(193.0);
        radioButton12.setLayoutY(233.0);
        radioButton12.setMnemonicParsing(false);
        radioButton12.setText("False");

        radioButton13.setLayoutX(131.0);
        radioButton13.setLayoutY(266.0);
        radioButton13.setMnemonicParsing(false);
        radioButton13.setText("True");

        radioButton14.setLayoutX(193.0);
        radioButton14.setLayoutY(266.0);
        radioButton14.setMnemonicParsing(false);
        radioButton14.setText("False");

        button.setLayoutX(191.0);
        button.setLayoutY(306.0);
        button.setMnemonicParsing(false);
        button.setText("Save");

        button0.setLayoutX(14.0);
        button0.setLayoutY(306.0);
        button0.setMnemonicParsing(false);
        button0.setText("Cancel");

        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(label3);
        getChildren().add(label4);
        getChildren().add(label5);
        getChildren().add(label6);
        getChildren().add(label7);
        getChildren().add(radioButton);
        getChildren().add(radioButton0);
        getChildren().add(radioButton1);
        getChildren().add(radioButton2);
        getChildren().add(radioButton3);
        getChildren().add(radioButton4);
        getChildren().add(radioButton5);
        getChildren().add(radioButton6);
        getChildren().add(radioButton7);
        getChildren().add(radioButton8);
        getChildren().add(radioButton9);
        getChildren().add(radioButton10);
        getChildren().add(radioButton11);
        getChildren().add(radioButton12);
        getChildren().add(radioButton13);
        getChildren().add(radioButton14);
        getChildren().add(button);
        getChildren().add(button0);

    }
}
