/*
 * @(#) AddPubController.java 0.1 2019/03/05 //TODO: Check date!
 *
 * Copyright (c) 2019 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group09.GUI;

import uk.ac.aber.cs221.group09.object.Pub;
import uk.ac.aber.cs221.group09.database.DbCommunicator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import org.json.JSONObject;
import javax.imageio.ImageIO;
import javax.net.ssl.HttpsURLConnection;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

/**
 * AddPubController - Is a controller for AddPub.fxml where buttons and the programs
 * functionalities will be computed
 * <p>
 * Will be used to read inn new input from the user to create a pub
 * Also to display a selected previously made pub
 *
 * @author Runar Reve (rur7)
 * @version 0.1 Draft
 */
public class AddPubController {

    @FXML Button backBtn;
    @FXML Button saveAndNewBtn;
    @FXML Button filterBtn;
    @FXML Button pictureBtn;
    @FXML Button savePub;
    @FXML Button addressBtn;
    @FXML TextField nameField = new TextField();
    @FXML TextField descriptionField = new TextField();
    @FXML TextField address = new TextField();
    @FXML TextField postCode = new TextField();
    @FXML Label townText;   //For displaying the name of the town to the user
    @FXML Label errorLabel; //For displaying errors to the user
    @FXML Label yCoords, xCoords; //For displaying the raw coordinates
    @FXML TextField imgLink; //User will enter the link to the picture here
    @FXML TextField googleLink; //This is just to make sure the user got the right location for debugging

    private ArrayList<Pub> parentPubList;
    private String town;
    private Pub currentPub; //If this is not null it means its being edited
    private boolean[] filters = new boolean[7]; //[0] will indicate if filters has been modified
    private double lng, lat; //X/Y coordination

    /**
     * Will go back to the previous scene (pubMenu) without adding the newly made pub.
     *
     * @param event an ActionEvent.
     * @throws IOException if the file it is trying to move to is not there, or faulty.
     */
    @FXML void moveBack(ActionEvent event)
    throws IOException {
        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("PubMenu.fxml"));
        Loader.load();

        PubMenuController nextTab = Loader.getController();
        nextTab.setPreset(parentPubList, town);

        Parent root = Loader.getRoot();
        Scene tableViewScene = new Scene(root);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(tableViewScene);
        window.show();

    }

    /**
     * Will save the Pub that has been made and add it to the list.
     *
     * @param event an ActionEvent.
     * @throws IOException if the file it is trying to move to is not there, or faulty.
     */
    @FXML void savePubAndBack(ActionEvent event)
    throws IOException {
        if(checkEnterd()) {
            savePub();
            moveBack(event);
        }
    }

    /**
     * Will save the pub and clean the scene for the user to add another pup
     */
    @FXML void saveAndNewPub(){
        if(checkEnterd()) {
            savePub();
            clearScene();
        }
    }

    /**
     * Will open a menu where the user can select characteristics that the pub has
     * And view previously selected characteristics
     */
    @FXML void openFilter(){
        filters = FilterPopup.display(filters);
    }

    /**
     * Open a window where the user can view picture.
     */
    @FXML void openPicture(){
        if(imgLink.getText().length() == 0)
            return;
        Image image;
        try {
            image = ImageIO.read(new URL(imgLink.getText()));
        } catch (IOException e) {
            errorLabel.setText("Invalid image link");
            return;
        }
        JFrame frame = new JFrame();
        frame.setSize(1000, 1000);
        JLabel label = new JLabel(new ImageIcon(image));
        frame.add(label);
        frame.setVisible(true);
    }

    /**
     * Will set the preset options in the scene to make it more flexible.
     *
     * @param list the list of pubs we are adding to.
     * @param town town the pub is in.
     */
    @FXML void setPreset(ArrayList<Pub> list, String town){
        parentPubList = list;
        this.town = town;
        townText.setText("You are currently adding a pub to " + town);
    }

    /**
     * Will populate the TextFields with the variables from the pub that is being edited.
     *
     * @param thePub the pub that is being edited/viewed.
     */
    public void setEditPage(Pub thePub){
        nameField.setText(thePub.getPubName());
        descriptionField.setText(thePub.getDescription());
        filters = convertFiltersToArray(thePub);
        currentPub = thePub;
        lng = thePub.getyCoordinate();
        lat = thePub.getxCoordinate();
        yCoords.setText(Double.toString(lng));
        xCoords.setText(Double.toString(lat));
        address.setText(thePub.getAddress());
        postCode.setText(thePub.getPostCode());
        townText.setText("You are currently working on the pub " + thePub.getPubName());
        googleLink.setText("https://www.google.com/maps/search/?api=1&query=" + lat + "," + lng);
        imgLink.setText(thePub.getImgLink());
    }

    /**
     * Will check if the user has entered information and, if it's valid, checks for acceptable entrances and duplication.
     *
     * @return true if does not violate any rules.
     */
    private boolean checkEnterd(){
        if (nameField.getText().length()>45){
            errorLabel.setText("Cannot be saved: characters too long ");
            return false;
        }
        else if(nameField.getText().length() == 0){
            errorLabel.setText("Please add a name to pub");
            return false;
        }
        if (imgLink.getText()== null) //If database has this as null, replace it as nothing
            imgLink.setText("");
        if (descriptionField.getText().length()>495){
            errorLabel.setText("Cannot be saved: description too long");
            return false;
        }
        else if(descriptionField.getText().length()== 0) {
            errorLabel.setText("Please add a description to the pub");
            return false;
        }
        if(!filters[0]) { //Checks the modified boolean in the array
            errorLabel.setText("Select Filters for the Pub");
            return false;
        }
        if(!checkAddress())
            return false;
        if (!checkDuplicate(nameField.getText())) {
            errorLabel.setText(nameField.getText() + " already exists");
            return false;
        }
        return true;
    }

    /**
     * Checks if the text in the address and postcode textField.
     *
     * @return true if there is values in textfield.
     */
    private boolean checkAddress(){
        if (address.getText() == null || address.getText().length() == 0){
            errorLabel.setText("Enter an address");
            return false;
        }
        else if(address.getText()==null || address.getText().length()>50){
            errorLabel.setText("Cannot be saved: address too long");
        }
        if(postCode.getText().length() == 0 || postCode.getText() == null){
            errorLabel.setText("Enter a postcode");
            return false;
        }
        return true;
    }

    /**
     * Searches through list of pub to check for duplications.
     *
     * @param pub the new pub that is being tested.
     * @return true if it is a unique pub name.
     */
    private boolean checkDuplicate(String pub){
        if(currentPub != null && currentPub.getPubName().equals(pub)) //If it's going to replace a pub and not changed name, no need of checking
            return true;
        for (Pub p: parentPubList) { //Check each pub in the list if there is a pub by that name
            if (p.getPubName().equals(pub)) {
                return false;
            }
        }
        return true;
    }
    /**
     * Will save the pub inserted in the fields in the scene, and upload that pub to the database.
     */
    private void savePub(){
        try {
            getCoordination();
        } catch (Exception e) {
            errorLabel.setText("Something went wrong while converting ");
        }

        //Make sure Strings do not contain "'" to SQL inject by using .replace("'", "")
        Pub nex = new Pub(nameField.getText().replace("'", ""),
                town, descriptionField.getText().replace("'", ""),
                imgLink.getText().replace("'", ""),
                Float.parseFloat(xCoords.getText()), Float.parseFloat(yCoords.getText()),
                address.getText().replace("'",""), postCode.getText().replace("'",""),
                filters);
        DbCommunicator COM = new DbCommunicator();
        if(currentPub != null) { //Delete the previous version of this pub
            COM.removePub(currentPub);
            parentPubList.remove(currentPub);
        }
        parentPubList.add(nex);
        COM.addPub(nex);
    }

    /**
     * Clears all the fields on this scene.
     */
    private void clearScene(){
        nameField.clear();
        descriptionField.clear();
        yCoords.setText(" ");
        xCoords.setText(" ");
        address.clear();
        postCode.clear();
        filters[0] = false; //has not been modified
        currentPub = null;
        townText.setText("You are currently adding a pub to " + town);
        googleLink.clear();
        imgLink.clear();
    }

    /**
     * Function to get the filters from a pub to an array that is easier to pass around in functions
     *
     * @param pub the Pub object where filters is being extracted
     * @return a array of boolean values representing the filters
     */
    private boolean[] convertFiltersToArray(Pub pub){
        boolean returnFilter[] = new boolean[7]; //The array of booleans to represent filters that will be returned
        returnFilter[0] = true; //Has been modified, so is true
        returnFilter[1] = pub.isHasFood();
        returnFilter[2] = pub.isHasRealAle();
        returnFilter[3] = pub.isAllowsDogs();
        returnFilter[4] = pub.isLoudMusic();
        returnFilter[5] = pub.isClub();
        returnFilter[6] = pub.isTV();
        return returnFilter;
    }

    /**
     * Converts address and postcode and converts them to coordinates
     * It is using the Geocode Google API
     *
     * @throws IOException will be thrown if its not able to get or set information
     */
    @FXML void getCoordination()
    throws IOException {
        if(!checkAddress())
            return;
        //Gets strings from the Text fields
        String addr = address.getText();
        addr = addr.replace(' ', '+');
        String post = postCode.getText();
        post = post.replace(' ', '+');
        String countryCode ="GB";
        //The Google API key
        String googleAPIKey = "AIzaSyBW0WhTlMmQfksPkx4PqR37VNdoeBwjPxs";
        //Put the strings together with a google geocode link to retrieve a json file
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" +
                addr + ",+" + post + ",+" + countryCode +
                "&key=" + googleAPIKey;

        URL obj = new URL(url);


        //Sets up a connection and sets what it should do ("GET" information)
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        if(con.getResponseCode() != 200){ //If it weren't able to load the page properly
            errorLabel.setText("Error with the page loading page");
            return;
        }

        //Gets the stream from the connection
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();
        //Copies the information over to a stringBuffer
        while ((inputLine = in.readLine()) != null) {response.append(inputLine); }
        in.close(); //Close the connection

        //Creates a JSON object with the data gathered
        JSONObject myResponse = new JSONObject(response.toString());
        if (myResponse.getString("status").equals("OK")) { //Checks if the status of the data is OK
            myResponse = myResponse.getJSONArray("results")
                    .getJSONObject(0).getJSONObject("geometry").getJSONObject("location");
            //Sets the longitude and latitude,...
            lng = myResponse.getDouble("lng");
            lat = myResponse.getDouble("lat");
            //...display them,...
            yCoords.setText(Double.toString(lng));
            xCoords.setText(Double.toString(lat));
            //... and gives the user a link to check if correct
            googleLink.setText("https://www.google.com/maps/search/?api=1&query=" + lat + "," + lng);
        }
        else //If status wasn't OK, it did not find the address
            errorLabel.setText("Did not find address");
    }
}