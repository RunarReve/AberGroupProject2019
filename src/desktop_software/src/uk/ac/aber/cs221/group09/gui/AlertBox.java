/*
 * @(#) AlertBox.java 0.1 2019/03/05 //TODO: Check date!
 *
 * Copyright (c) 2019 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group09.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * AlertBox - Popup window to ask the user if they are sure they want to do something
 * <p>
 * Will take inn a message that will be displayed to the user
 * Then the user will answer yes or no on
 *
 * @author Runar Reve
 * @version 0.1 Draft
 */
public class AlertBox {

    private static boolean answer; // The answer user selects

    /**
     * Creates a window to take a binary response from the user.
     *
     * @param title the name of the window.
     * @param message the textual content of the pop-up.
     * @return whether or not the user wishes to proceed.
     */
    public static boolean display(String title, String message) {

        Stage alertBox = new Stage();
        alertBox.initModality(Modality.APPLICATION_MODAL);
        alertBox.setTitle(title);
        alertBox.setMinWidth(300);
        alertBox.setMinHeight(100);
        Label text = new Label(message);
        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");

        yesBtn.setOnAction(e -> {
            answer = true;
            alertBox.close();
        });

        noBtn.setOnAction(e -> {
            answer = false;
            alertBox.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(text, yesBtn, noBtn);
        layout.setAlignment(Pos.CENTER);
        alertBox.setScene(new Scene(layout));
        alertBox.showAndWait();

        return answer;
    }
}
