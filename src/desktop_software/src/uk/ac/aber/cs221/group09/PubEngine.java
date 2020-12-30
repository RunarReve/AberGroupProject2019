/*
   * @(#) PubEngine.java 0.1 2019/03/05
   *
   * Copyright (c) 2019 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package uk.ac.aber.cs221.group09;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uk.ac.aber.cs221.group09.GUI.MainMenuController;
import uk.ac.aber.cs221.group09.database.DbCommunicator;
import uk.ac.aber.cs221.group09.object.Pub;
import uk.ac.aber.cs221.group09.object.Town;
import java.io.IOException;
import java.util.ArrayList;

/**
   * PubEngine - This is the main class that will set up everything and run the program.
   * <p>
   * When starting the program it should start running from here and set up the database connection.
   * Some classes to sort to filter pubs, "filterPubs", that you give it following an example pub that has the attributes the user wants.
   * Also there is a randomize pub where it will extract the amount of pubs you want randomly and put them in another list.
   *
   * @author Runar Reve (rur7)
   * @version 0.1 Draft
   * @see Town
   * @see Pub
   * @see DbCommunicator
   * @see PubTourInterface
   */
public class PubEngine extends Application {

    /**
     * TODO
     *
     * @param args
     */
    public static void main(String[] args) {launch(args);}

    /**
     * TODO
     *
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage)
    throws IOException {
        ArrayList<Town> mainTownList; //List of all town in DB

        DbCommunicator COM = new DbCommunicator();//Connection to the Db class
        mainTownList = COM.retrieveTowns(); //Retrieves towns from the database

        FXMLLoader Loader = new FXMLLoader();
        Loader.setLocation(getClass().getResource("GUI/MainMenu.fxml"));
        Loader.load();
        MainMenuController menu = Loader.getController();
        menu.setTownList(mainTownList);

        Parent root = Loader.getRoot();

        primaryStage.setTitle("Pub Crawl Administrator");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * TODO
     *
     * @param mainList
     * @param samplePub
     * @return
     */
    public ArrayList<Pub> filterPubs(ArrayList<Pub> mainList, Pub samplePub) {
        ArrayList<Pub> filterPubs= new ArrayList<>();
        for (Pub p: mainList){
            if (p.isAllowsDogs() == samplePub.isAllowsDogs() &&
                    p.isClub() == samplePub.isClub() &&
                    p.isHasFood() == samplePub.isHasFood() && p.isHasRealAle() == samplePub.isHasRealAle()
                    && p.isLoudMusic() == samplePub.isLoudMusic() && p.isTV() == samplePub.isTV()){
                filterPubs.add(p);
            }
        }

        return filterPubs;
    }

    /**
     * TODO
     *
     * @param mainPubList
     * @param amountOfPubs
     * @return
     */
    public ArrayList<Pub> randomPubs(ArrayList<Pub> mainPubList, int amountOfPubs) {
        return null;
    }
}