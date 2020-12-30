/*
 * @(#) DbCommunicator.java 0.1 2019/03/05
 *
 * Copyright (c) 2019 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group09.database;

import uk.ac.aber.cs221.group09.object.Pub;
import uk.ac.aber.cs221.group09.object.Town;
import java.sql.*;
import java.util.ArrayList;

/**
 * DbCommunicator - A class that takes care of sending commands to the Database.
 * <p>
 * Have to establish a connection outside this class.
 * Then follow interface in uk.ac.aber.cs221.group09.database.DbReceiverInterface/DbUpdateInterface for how to use each function.
 *
 * @author Runar Reve (rur7)
 * @version 0.1 Draft
 * @see DbReceiverInterface
 * @see DbUpdaterInterface
 */
public class DbCommunicator

implements DbReceiverInterface, DbUpdaterInterface {

    static final private String JDBC_DRIVER = "org.postgresql.Driver";  //Driver of the Database, located in ../lib
    static final private String DB_URL = "jdbc:postgresql://db.dcs.aber.ac.uk:5432/cs22120_18_19_csgp09"; //DB URL
    static final private String USER = "csgp09_admin";  //Databases Username
    static final private String PASS = "xx17jdqP";      //Databases Password

    /**
     * Will retrieve specific pubs depending on the filters that is given to it.
     *
     * @param town name of town that is being retrieved.
     * @return an ArrayList of pubs following the filters.
     * @throws SQLException if an SQL error occurs.
     */
    @Override
    public ArrayList<Pub> retrievePubs(String town) {

        ArrayList<Pub> pubs  = new ArrayList<>();
        String SQL ="SELECT * FROM pubs WHERE townname = '"; //SQL that will be used
        SQL = SQL + town +"'"; //Merge town with SQL command

        //Setup for connecting to database
        Connection connection = null;
        Statement statement = null;

        //Setting up a connection with a database (DB_URL)
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database... ");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating Statement...");
            statement = connection.createStatement();


            ResultSet resultSet = statement.executeQuery(SQL); //The values that has been received

            //-----CREATE-PUB-OBJECTS-AND-PUT-IN-LIST-----
            while (resultSet.next()) {
                //Gets all needed values from resultSet
                boolean[] filters = new boolean[7]; //Array to gather filters
                String pubName      = resultSet.getString("pubName");
                float xCoordinate   = resultSet.getFloat("xCoordinate");
                float yCoordinate   = resultSet.getFloat("yCoordinate");
                String description  = resultSet.getString("description");
                String imgLink      = resultSet.getString("imgLink");
                String address      = resultSet.getString("address");
                String postCode     = resultSet.getString("postCode");
                filters[1]          = resultSet.getBoolean("hasFood");
                filters[2]          = resultSet.getBoolean("hasRealAle");
                filters[3]          = resultSet.getBoolean("allowsDogs");
                filters[4]          = resultSet.getBoolean("loudMusic");
                filters[5]          = resultSet.getBoolean("club");
                filters[6]          = resultSet.getBoolean("TV");

                Pub newP = new Pub(pubName, town,description,imgLink,xCoordinate,yCoordinate,address,postCode, filters);
                pubs.add(newP); //Add to town list
            }

            //Close the connections
            resultSet.close();
            statement.close();
            connection.close();
        }catch(Exception e){ //Som catch statement if don't work
            e.printStackTrace();

        }finally { //Will make sure it is closed
            try {
                if (statement != null) {
                    statement.close();
                }
            }catch(SQLException se2) {
            } //Nothing that can be done
            try{
                if (connection != null){
                    connection.close();
                }
            }catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return pubs;
    }

    /**
     * Adds a new pub to the database.
     *
     * @param newPub the new pub that is going to be added.
     * @throws SQLException if an SQL error occurs.
     */
    @Override
    public void addPub(Pub newPub) {
        System.out.println("Adding: " + newPub.getPubName());
        sendInfo(newPub.pubToDatabase());
    }

    /**
     * Remove an existing pub from the database.
     *
     * @param pub the pub that is being removed.
     * @return true if pub successfully removed the pub.
     * @throws SQLException if an SQL error occurs.
     */
    @Override
    public boolean removePub(Pub pub) {
        System.out.println("Removing: " + pub.getPubName());
        String sql = "DELETE FROM pubs WHERE pubName = '" + pub.getPubName() +"' AND townName = '" + pub.getTown() + "';";
        sendInfo(sql);
        return true;
    }

    /**
     * Retrieves all the Towns in the database
     *
     * @return an ArrayList of all the towns in the database.
     * @throws SQLException if an SQL error occurs.
     */
    @Override
    public ArrayList<Town> retrieveTowns() {
        ArrayList<Town> towns  = new ArrayList<>();
        String SQL;
        SQL = "SELECT townName FROM towns"; //SQL that will be used

        //Setup for connecting to database
        Connection connection = null;
        Statement statement = null;

        //Setting up a connection with a database (DB_URL)
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database... ");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

            System.out.println("Creating Statement...");
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL);


            while (resultSet.next()) {
                //Get from resultSet
                String townName = resultSet.getString("townName"); // get result from "townName"
                Town newT = new Town(townName); //Create a town
                towns.add(newT); //Add to town list
            }
            resultSet.close(); //Closes this connection

            //Close the connections
            statement.close();
            connection.close();
        }catch(Exception e){ //Som catch statement if don't work
            e.printStackTrace();

        }finally { //Will make sure it is closed
            try {
                if (statement != null) {
                    statement.close();
                }
            }catch(SQLException se2) {
            } //Nothing that can be done
            try{
                if (connection != null){
                    connection.close();
                }
            }catch(SQLException se) {
                se.printStackTrace();
            }
        }
        return towns;
    }

    /**
     * Adds a new town in the database.
     *
     * @param newTown the new town that is being pushed.
     * @throws SQLException if an SQL error occurs.
     */
    @Override
    public void addTown(Town newTown) {
        String sql = "INSERT INTO towns(townName)\n" +
                "VALUES ('" + newTown.getTownName() + "');";
        sendInfo(sql);
    }

    /**
     * Remove an existing town from the database.
     *
     * @param town the town that is being removed.
     * @return true if town was successfully removed.
     * @throws SQLException if an SQL error occurs.
     */
    @Override
    public boolean removeTown(Town town) {
        String sql = "DELETE FROM towns WHERE townName = '" + town.getTownName() +"';";
        sendInfo(sql);
        return true;
    }

    /**
     * Used by the other function to send a premade command to the database
     *
     * @param SqlCommand the command that is being sent to the database server
     */
    private void sendInfo(String SqlCommand){
        Connection connection = null;
        Statement statement = null;

        //Setting up a connection with a database (DB_URL)
        try {
            Class.forName(JDBC_DRIVER);

            System.out.println("Connecting to database... ");
            connection = DriverManager.getConnection(DB_URL, USER, PASS);
            System.out.println("Creating Statement...");
            statement = connection.createStatement();

            //SENDING SQL COMMAND
            statement.executeUpdate(SqlCommand);

            //Need to close the connection
            statement.close();
            connection.close();
        }catch(Exception e){ //Som catch statement if something happens
            e.printStackTrace();
        }finally { //Will make sure everything is closed
            try {
                if (statement != null) {
                    statement.close();
                }
            }catch(SQLException se2) {
            } //Nothing that can be done
            try{
                if (connection != null){
                    connection.close();
                }
            }catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }
}
