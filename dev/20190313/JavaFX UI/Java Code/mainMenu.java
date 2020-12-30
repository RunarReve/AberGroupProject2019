package gpui;

import java.lang.*;
import java.util.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;

public abstract class mainMenu extends AnchorPane {

    protected final Label label;
    protected final Button button;
    protected final ListView listView;
    protected final Label label0;
    protected final TextField textField;
    protected final Button button0;
    protected final Button button1;

    public mainMenu() {

        label = new Label();
        button = new Button();
        listView = new ListView();
        label0 = new Label();
        textField = new TextField();
        button0 = new Button();
        button1 = new Button();

        setId("menuUi");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        label.setLayoutX(55.0);
        label.setLayoutY(43.0);
        label.setPrefHeight(66.0);
        label.setPrefWidth(222.0);
        label.setText("Choose the town you want to edit from the list, or enter a new town name to create it:");
        label.setWrapText(true);

        button.setLayoutX(186.0);
        button.setLayoutY(324.0);
        button.setMnemonicParsing(false);
        button.setText("Continue");

        listView.setLayoutX(55.0);
        listView.setLayoutY(109.0);
        listView.setPrefHeight(200.0);
        listView.setPrefWidth(200.0);

        label0.setLayoutX(300.0);
        label0.setLayoutY(124.0);
        label0.setText("Enter town name:");

        textField.setLayoutX(400.0);
        textField.setLayoutY(121.0);
        textField.setPrefWidth(159.0);

        button0.setLayoutX(371.0);
        button0.setLayoutY(174.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(44.0);
        button0.setPrefWidth(69.0);
        button0.setText("Create Town");
        button0.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button0.setWrapText(true);

        button1.setLayoutX(448.0);
        button1.setLayoutY(174.0);
        button1.setMnemonicParsing(false);
        button1.setPrefHeight(45.0);
        button1.setPrefWidth(111.0);
        button1.setText("Create Town and Add Pubs");
        button1.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        button1.setWrapText(true);

        getChildren().add(label);
        getChildren().add(button);
        getChildren().add(listView);
        getChildren().add(label0);
        getChildren().add(textField);
        getChildren().add(button0);
        getChildren().add(button1);

    }
}
