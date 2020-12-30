/*
 * @(#) MainMenuController.java 0.1 2019/03/05 //TODO: Check date!
 *
 * Copyright (c) 2019 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group09.GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import uk.ac.aber.cs221.group09.database.DbCommunicator;
import uk.ac.aber.cs221.group09.object.Pub;
import uk.ac.aber.cs221.group09.object.Town;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * TODO Add author and explanation.
 *
 * @author
 * @draft 0.1 Draft
 */
public class MainMenuController

implements Initializable {

    @FXML Button continueBtn;
    @FXML Button createTownBtn;
    @FXML Button createTownAndNextBtn;
    @FXML Button deleteBtn;
    @FXML TextField townNameField = new TextField();
    @FXML Label errorText = new Label();
    @FXML ListView<String> listViewTown;
    public static ArrayList<Town> townList = new ArrayList<>();

    /**
     * Function for switching scenes.
     * If made a new one and auto imports packages, rename java.awt.event.ActionEvent to javafx.event.Ac.. to be able to communicate with fxml.
     *
     * @param event is just when an event is being used.
     * @throws IOException if there is a typo or other errors with the file in .getResource.
     */
    public void moveNextScene(ActionEvent event)
    throws IOException { //Button Action
        String selectedTown = listViewTown.getSelectionModel().getSelectedItem();
        if(selectedTown != null){
            moveScene(event, selectedTown);
        }
        else
            errorText.setText("You need to pick a town");
    }

    /**
     * Will create a town and upload the town to the database.
     */
    public void createTown(){ //Button Action
        if(townNameField.getText().length() != 0) { //Checks if it has extracted something
            //TODO SID check lenght
            if (townNameField.getText().length()==0) {
                errorText.setText("Error: please enter a town name");
            }
            else if(townNameField.getText().length()>20){
                errorText.setText("Error: town name too long");
                return;
            }

            if (checkDuplicate(townNameField.getText())) {
                saveTown();
                updateTownList(townList);
            } else {
                errorText.setText("Town already exists!");
                return;
            }
        }
        else{
            errorText.setText("Please enter a town name");
        }
    }

    /**
     * Attempts to delete the selected pub. TODO
     */
    public void deleteTown(){//Button Action
        String selectedTown = listViewTown.getSelectionModel().getSelectedItem();
        if(selectedTown != null){
            DbCommunicator COM = new DbCommunicator();
            ArrayList<Pub> checkPubs = COM.retrievePubs(selectedTown);
            if(checkPubs.size() != 0){
                boolean result = AlertBox.display("Town deletion", "There are Pubs in the town: " + selectedTown + "?\nThis will delete all pubs in this town");
                if(result){ //If user said yes, delete town and its pubs
                    for (Pub deletePub:checkPubs) { //Deletes all pubs in this town
                        COM.removePub(deletePub);
                    }
                    COM.removeTown(townList.get(listViewTown.getSelectionModel().getSelectedIndex()));
                    townList.remove(townList.get(listViewTown.getSelectionModel().getSelectedIndex()));
                    updateTownList(townList);
                }
            }else { // If there in no pubs in town, just go ahead and delete it
                COM.removeTown(townList.get(listViewTown.getSelectionModel().getSelectedIndex()));
                townList.remove(townList.get(listViewTown.getSelectionModel().getSelectedIndex()));
                updateTownList(townList);
            }
        }
        else
            errorText.setText("You need to select a town");
    }

    /**
     * TODO
     *
     * @param event
     * @throws IOException
     */
    public void createTownAndNext(ActionEvent event)
    throws IOException{//Button Action
        String town = townNameField.getText();
        if(town.length() != 0) { //Checks if it has extracted something
            if(checkDuplicate(town)) {
                saveTown();
                moveScene(event, town);
            }else{
                errorText.setText("Town already exists!");
                return;
            }
        }
        else{
            errorText.setText("Please enter a town name");
        }
    }

    /**
     * TODO
     *
     * @param list
     */
    public void setTownList(ArrayList<Town> list){
        townList = list;
        updateTownList(townList);
    }

    /**
     * TODO
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        updateTownList(townList);
    }

    /**
     * Takes in an ArrayList of String and display that to ListView.
     *
     * @param list The new list of towns to be displayed
     */
    private void updateTownList(ArrayList<Town> list){
        ObservableList<String> data = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            data.add(list.get(i).getTownName());
        }
        listViewTown.setItems(data);
    }

    /**
     * Will save the town added to the add town field.
     */
    private void saveTown(){
        Town newTown = new Town(townNameField.getText());
        townList.add(newTown); //Add to local list

        DbCommunicator COM = new DbCommunicator();
        COM.addTown(newTown);

        townNameField.clear();
        errorText.setText("");
    }

    /**
     * Will take a town and extract the pubs from that town to move onto the menu scene of pubs
     *
     * @param event the event to change scene
     * @param town the selected town
     * @throws IOException if the URL does not exists
     */
    private void moveScene(ActionEvent event, String town)
    throws IOException{
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("PubMenu.fxml"));
        Loader.load();

        //To retrieve pubs from Database
        DbCommunicator COM = new DbCommunicator();
        ArrayList<Pub> pubList = COM.retrievePubs(town);

        //Load the next scene with the wanted data
        PubMenuController nextTab = Loader.getController();
        nextTab.setPreset(pubList, town);

        Parent root = Loader.getRoot();
        Scene tableViewScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * Searches through list of towns to check for duplications.
     *
     * @param town the new town that is being tested.
     * @return true if it is a unique town name.
     */
    private boolean checkDuplicate(String town){
        for (Town aTownList : townList) {
            if (town.equals(aTownList.getTownName())) {
                return false;
            }
        }
        return true;
    }
}