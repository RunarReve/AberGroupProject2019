package gpui;

import java.lang.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;
import javafx.scene.shape.*;

public abstract class managePhotos extends AnchorPane {

    protected final Label label;
    protected final Label label0;
    protected final Label label1;
    protected final Label label2;
    protected final Label label3;
    protected final Label label4;
    protected final Button button;
    protected final Button button0;
    protected final Button button1;
    protected final Button button2;
    protected final Button button3;
    protected final Label label5;
    protected final Label label6;
    protected final Button button4;
    protected final Button button5;
    protected final Button button6;
    protected final Button button7;
    protected final Button button8;
    protected final Button button9;
    protected final Button button10;
    protected final Button button11;
    protected final Label label7;
    protected final Rectangle rectangle;

    public managePhotos() {

        label = new Label();
        label0 = new Label();
        label1 = new Label();
        label2 = new Label();
        label3 = new Label();
        label4 = new Label();
        button = new Button();
        button0 = new Button();
        button1 = new Button();
        button2 = new Button();
        button3 = new Button();
        label5 = new Label();
        label6 = new Label();
        button4 = new Button();
        button5 = new Button();
        button6 = new Button();
        button7 = new Button();
        button8 = new Button();
        button9 = new Button();
        button10 = new Button();
        button11 = new Button();
        label7 = new Label();
        rectangle = new Rectangle();

        setId("AnchorPane");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        label.setLayoutX(39.0);
        label.setLayoutY(38.0);
        label.setText("Currently uploaded photos (max 5):");

        label0.setLayoutX(39.0);
        label0.setLayoutY(87.0);
        label0.setText("(empty)");

        label1.setLayoutX(39.0);
        label1.setLayoutY(121.0);
        label1.setText("(empty)");

        label2.setLayoutX(39.0);
        label2.setLayoutY(158.0);
        label2.setText("(empty)");

        label3.setLayoutX(39.0);
        label3.setLayoutY(192.0);
        label3.setText("(empty)");

        label4.setLayoutX(39.0);
        label4.setLayoutY(229.0);
        label4.setText("(empty)");

        button.setLayoutX(237.0);
        button.setLayoutY(85.0);
        button.setMnemonicParsing(false);
        button.setText("Remove");
        button.setTextFill(javafx.scene.paint.Color.RED);

        button0.setLayoutX(237.0);
        button0.setLayoutY(122.0);
        button0.setMnemonicParsing(false);
        button0.setText("Remove");
        button0.setTextFill(javafx.scene.paint.Color.RED);

        button1.setLayoutX(237.0);
        button1.setLayoutY(156.0);
        button1.setMnemonicParsing(false);
        button1.setText("Remove");
        button1.setTextFill(javafx.scene.paint.Color.RED);

        button2.setLayoutX(237.0);
        button2.setLayoutY(190.0);
        button2.setMnemonicParsing(false);
        button2.setText("Remove");
        button2.setTextFill(javafx.scene.paint.Color.RED);

        button3.setLayoutX(237.0);
        button3.setLayoutY(227.0);
        button3.setMnemonicParsing(false);
        button3.setText("Remove");
        button3.setTextFill(javafx.scene.paint.Color.RED);

        label5.setLayoutX(39.0);
        label5.setLayoutY(282.0);
        label5.setText("Add new photo:");

        label6.setLayoutX(39.0);
        label6.setLayoutY(306.0);
        label6.setText("Upload File:");

        button4.setLayoutX(179.0);
        button4.setLayoutY(304.0);
        button4.setMnemonicParsing(false);
        button4.setText("Choose File");

        button5.setLayoutX(483.0);
        button5.setLayoutY(346.0);
        button5.setMnemonicParsing(false);
        button5.setText("Save and Exit");

        button6.setLayoutX(174.0);
        button6.setLayoutY(85.0);
        button6.setMnemonicParsing(false);
        button6.setText("View");
        button6.setTextFill(javafx.scene.paint.Color.BLACK);

        button7.setLayoutX(174.0);
        button7.setLayoutY(122.0);
        button7.setMnemonicParsing(false);
        button7.setText("View");
        button7.setTextFill(javafx.scene.paint.Color.BLACK);

        button8.setLayoutX(174.0);
        button8.setLayoutY(156.0);
        button8.setMnemonicParsing(false);
        button8.setText("View");
        button8.setTextFill(javafx.scene.paint.Color.BLACK);

        button9.setLayoutX(174.0);
        button9.setLayoutY(190.0);
        button9.setMnemonicParsing(false);
        button9.setText("View");
        button9.setTextFill(javafx.scene.paint.Color.BLACK);

        button10.setLayoutX(174.0);
        button10.setLayoutY(227.0);
        button10.setMnemonicParsing(false);
        button10.setText("View");
        button10.setTextFill(javafx.scene.paint.Color.BLACK);

        button11.setLayoutX(39.0);
        button11.setLayoutY(346.0);
        button11.setMnemonicParsing(false);
        button11.setText("Discard Changes");

        label7.setLayoutX(392.0);
        label7.setLayoutY(54.0);
        label7.setText("Currently viewing image:");

        rectangle.setArcHeight(5.0);
        rectangle.setArcWidth(5.0);
        rectangle.setFill(javafx.scene.paint.Color.DODGERBLUE);
        rectangle.setHeight(129.0);
        rectangle.setLayoutX(392.0);
        rectangle.setLayoutY(102.0);
        rectangle.setStroke(javafx.scene.paint.Color.BLACK);
        rectangle.setStrokeType(javafx.scene.shape.StrokeType.INSIDE);
        rectangle.setWidth(152.0);

        getChildren().add(label);
        getChildren().add(label0);
        getChildren().add(label1);
        getChildren().add(label2);
        getChildren().add(label3);
        getChildren().add(label4);
        getChildren().add(button);
        getChildren().add(button0);
        getChildren().add(button1);
        getChildren().add(button2);
        getChildren().add(button3);
        getChildren().add(label5);
        getChildren().add(label6);
        getChildren().add(button4);
        getChildren().add(button5);
        getChildren().add(button6);
        getChildren().add(button7);
        getChildren().add(button8);
        getChildren().add(button9);
        getChildren().add(button10);
        getChildren().add(button11);
        getChildren().add(label7);
        getChildren().add(rectangle);

    }
}
