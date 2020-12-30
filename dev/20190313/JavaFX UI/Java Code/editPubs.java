package gpui;

import java.lang.*;
import java.util.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.*;

public abstract class editPubs extends AnchorPane {

    protected final Label label;
    protected final Button button;
    protected final Button button0;
    protected final Button button1;
    protected final Button button2;
    protected final ListView listView;
    protected final Label label0;

    public editPubs() {

        label = new Label();
        button = new Button();
        button0 = new Button();
        button1 = new Button();
        button2 = new Button();
        listView = new ListView();
        label0 = new Label();

        setId("AnchorPane");
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        label.setLayoutX(59.0);
        label.setLayoutY(34.0);
        label.setText("You have loaded Aberystwyth's list.");
        label.setWrapText(true);

        button.setLayoutX(245.0);
        button.setLayoutY(219.0);
        button.setMnemonicParsing(false);
        button.setPrefWidth(146.0);
        button.setText("Add a New Pub");

        button0.setLayoutX(245.0);
        button0.setLayoutY(262.0);
        button0.setMnemonicParsing(false);
        button0.setPrefWidth(146.0);
        button0.setText("Edit Selected Pub");

        button1.setLayoutX(245.0);
        button1.setLayoutY(300.0);
        button1.setMnemonicParsing(false);
        button1.setPrefWidth(146.0);
        button1.setText("Delete Selected Pub");
        button1.setTextFill(javafx.scene.paint.Color.RED);

        button2.setLayoutX(59.0);
        button2.setLayoutY(355.0);
        button2.setMnemonicParsing(false);
        button2.setText("Back");

        listView.setLayoutX(59.0);
        listView.setLayoutY(119.0);
        listView.setPrefHeight(200.0);
        listView.setPrefWidth(120.0);

        label0.setLayoutX(59.0);
        label0.setLayoutY(58.0);
        label0.setPrefHeight(52.0);
        label0.setPrefWidth(266.0);
        label0.setText("Please select a pub from the list that you would like to edit or delete. Alternatively you can choose to add a new pub.");
        label0.setWrapText(true);

        getChildren().add(label);
        getChildren().add(button);
        getChildren().add(button0);
        getChildren().add(button1);
        getChildren().add(button2);
        getChildren().add(listView);
        getChildren().add(label0);

    }
}
