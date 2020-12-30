/*
 * @(#) FilterPopup.java 0.1 2019/03/05
 *
 * Copyright (c) 2019 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group09.GUI;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * TODO: Explanation, author, etc.
 *
 * @author Runar Reve
 * @version 1.0 Release
 */
class FilterPopup {

    /**
     * TODO
     *
     * @param previousFilters
     * @return
     */
    public static boolean[] display(boolean[] previousFilters) {

        boolean returnFilter[] = new boolean[7]; //The array of booleans to represent filters that will be returned
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Filter Selection");
        window.setMinWidth(300);
        window.setMinHeight(100);
        Label header = new Label("Select filters");
        Label errorText = new Label(" ");

        ArrayList<ChoiceBox<String>> choiceBox = new ArrayList<>();
        for(int i = 0; i < 6; i++){
            choiceBox.add(new ChoiceBox<>());
            choiceBox.get(i).getItems().addAll("Yes", "No");
            if(previousFilters[0]){
                if(previousFilters[i+1])
                    choiceBox.get(i).getSelectionModel().selectFirst();
                else
                    choiceBox.get(i).getSelectionModel().selectLast();
            }
        }

        Label foodtext = new Label("Serves Food");
        HBox foodbox = new HBox(20);
        foodbox.getChildren().addAll(foodtext, choiceBox.get(0));

        Label aletext = new Label("Has Real Ale");
        HBox alebox = new HBox(20);
        alebox.getChildren().addAll(aletext, choiceBox.get(1));

        Label dogstext = new Label("Allows Dogs");
        HBox dogsbox = new HBox(20);
        dogsbox.getChildren().addAll(dogstext, choiceBox.get(2));

        Label musictext = new Label("Loud Music");
        HBox musicbox = new HBox(20);
        musicbox.getChildren().addAll(musictext, choiceBox.get(3));

        Label clubtext = new Label("Club");
        HBox clubbox = new HBox(20);
        clubbox.getChildren().addAll(clubtext, choiceBox.get(4));

        Label TVtext = new Label("Has TV");
        HBox TVbox = new HBox(20);
        TVbox.getChildren().addAll(TVtext, choiceBox.get(5));

        Button doneBtn = new Button("Done");
        doneBtn.setOnAction(e -> {
            if(!testIfNull(choiceBox)) { //Check if every box has been selected
                returnFilter[0] = true; // The first in array will indicate it has been modified
                for (int i = 0; i < 6; i++) {
                    returnFilter[i + 1] = choiceBox.get(i).getValue().equals("Yes"); //yes: true, no: false
                }
                window.close();
            }else{
                errorText.setText("Fill all filters");
            }
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(header, foodbox,alebox,dogsbox,musicbox,clubbox,TVbox,errorText, doneBtn);
        layout.setAlignment(Pos.CENTER);
        window.setScene(new Scene(layout));
        window.showAndWait();

        return returnFilter;
    }

    /**
     * Will test if there is null in an ArrayList of ChoiceBox.
     *
     * @param choiceBox the ArrayList the function is going through.
     * @return true if found a null.
     */
    static boolean testIfNull(ArrayList<ChoiceBox<String>> choiceBox){
        for (int i = 0; i < 6; i++) {
            if(choiceBox.get(i).getValue() == null)
                return true;
        }
        return false ;
    }

}
