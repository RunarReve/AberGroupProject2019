/*
 * @(#) PubMenuController.java 0.1 2019/03/05 //TODO: Check date!
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import uk.ac.aber.cs221.group09.database.DbCommunicator;
import uk.ac.aber.cs221.group09.object.Pub;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * TODO
 *
 * @author
 * @version 0.1 Draft
 */
public class PubMenuController

implements Initializable {

    @FXML Button backBtn;
    @FXML Button createPub;
    @FXML Button editPub;
    @FXML Button deletePub;
    @FXML ListView<String> listViewPub;
    @FXML Label townText = new Label();
    @FXML Label errorText = new Label();
    private String town;
    private static ArrayList<Pub> pubList = new ArrayList<>();

    /**
     * TODO
     *
     * @param event
     * @throws IOException
     */
    public void backButton(ActionEvent event)
    throws IOException { //Button Action
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * TODO
     *
     * @param event
     * @throws IOException
     */
    public void createNewPub(ActionEvent event)
    throws IOException { //Button Action
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("AddPub.fxml"));
        Loader.load();

        AddPubController nextTab = Loader.getController();
        nextTab.setPreset(pubList, town);

        Parent root = Loader.getRoot();
        Scene tableViewScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();
    }

    /**
     * TODO
     *
     * @param event
     * @throws IOException
     */
    public void editSelectedPub(ActionEvent event)
    throws IOException { //Button Action
        String selectedPub = listViewPub.getSelectionModel().getSelectedItem();
        if(selectedPub != null){
            for(int i = 0; i < pubList.size(); i++){
                if (selectedPub.equals(pubList.get(i).getPubName())) {
                    FXMLLoader Loader = new FXMLLoader();
                    Loader.setLocation(getClass().getResource("AddPub.fxml"));
                    Loader.load();

                    AddPubController nextTab = Loader.getController();
                    nextTab.setPreset(pubList, town);
                    nextTab.setEditPage(pubList.get(i));
                    //deleteSelectedPub();

                    Parent root = Loader.getRoot();
                    Scene tableViewScene = new Scene(root);
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(tableViewScene);
                    window.show();
                }
            }
        }
        else{
            errorText.setText("Please select a pub to edit");
        }
    }

    /**
     * TODO
     */
    public void deleteSelectedPub() { //Button Action
        String selectedPub = listViewPub.getSelectionModel().getSelectedItem();
        if(selectedPub != null){
            for(int i = 0; i < pubList.size(); i++){
                if (selectedPub.equals(pubList.get(i).getPubName())) {
                    DbCommunicator com = new DbCommunicator();
                    com.removePub(pubList.get(i));
                    pubList.remove(i);
                    updatePubList(pubList);
                    return;
                }
            }
        }
        else{
            errorText.setText("Please select a pub to delete");
        }
    }

    /**
     * Sets the text at the top of the screen to that of the loaded town. TODO
     *
     * @param town the name of the town.
     */
    public void setTownText(String town){
        townText.setText("You have loaded: " + town);
        this.town = town;
    }

    /**
     * TODO
     *
     * @param list the pub list... __________________________
     * @param town the name of the town.
     */
    public void setPreset(ArrayList<Pub> list, String town){
        pubList = list;
        setTownText(town);
        updatePubList(pubList);
    }

    /**
     * TODO
     *
     * @param list the pub list... ________________________
     */
    private void updatePubList(ArrayList<Pub> list){
        ObservableList<String> data = FXCollections.observableArrayList();
        for (int i = 0; i < list.size(); i++) {
            data.add(list.get(i).getPubName());
        }
        listViewPub.setItems(data);

    }

    /**
     * TODO
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb){
        updatePubList(pubList); //Updates the list of pubs

    }
}
