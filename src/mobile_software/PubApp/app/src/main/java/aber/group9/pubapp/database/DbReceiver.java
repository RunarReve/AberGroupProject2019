/*
 * @(#) DbReceiver.java 0.1 2019/03/05
 *
 * Copyright (c) 2019 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package aber.group9.pubapp.database;


import android.os.AsyncTask;

import java.sql.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import aber.group9.pubapp.object.Pub;

/**
 * DbCommunicator - A class that takes care of sending commands to the Database.
 * <p>
 * Have to establish a connection outside this class.
 * Then follow interface in uk.ac.aber.cs221.group09.database.DbReceiverInterface/DbUpdateInterface for how to use each function.
 *
 * @author Runar Reve (rur7)
 * @version 0.1 Draft
 * @see DbReceiverInterface
 */
public class DbReceiver
implements DbReceiverInterface{

    static final private String JDBC_DRIVER = "org.postgresql.Driver";  //Driver of the Database, located in ../lib
    static final private String DB_URL = "jdbc:postgresql://db.dcs.aber.ac.uk:5432/cs22120_18_19_csgp09"; //DB URL
    static final private String USER = "csgp09_admin";  //Databases Username
    static final private String PASS = "xx17jdqP";      //Databases Password

    /**
     * Will retrieve specific pubs depending on the filters that is given to it.
     *
     * @param town name of town that is being retrieved.
     * @return an ArrayList of pubs following the filters.
     */
    @Override
    public ArrayList<Pub> retrievePubs(String town) {

        ArrayList<Pub> pubs  = new ArrayList<>();
        try { //Tries to retrieve
            PubAsynctask pubgetter = new PubAsynctask();
            pubgetter.setTown(town);
            pubs = pubgetter.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return pubs;
    }

    private class PubAsynctask extends AsyncTask<Void, Integer, ArrayList<Pub>> {
        String town;
        public void setTown(String town){
            this.town = town;
        }
        @Override
        protected ArrayList<Pub> doInBackground(Void... params) {
            ArrayList<Pub> pubs = new ArrayList<>();
            String SQL ="SELECT * FROM pubs WHERE townname = '"; //SQL that will be used
            SQL = SQL + town +"'"; //Merge town with SQL command

            //Setup for connecting to database
            Connection connection = null;
            Statement statement = null;
            try {
                Class.forName(JDBC_DRIVER);
                System.out.println("DB Driver Found");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("DB Driver not found");
            }

            try {
                System.out.println("Connecting to database... ");
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Creating Statement...");
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL);

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

                    Pub newP = new Pub(pubName, town,description,imgLink,xCoordinate,yCoordinate,address,postCode, filters, false);
                    pubs.add(newP); //Add to town list
                }
                //Close connections
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Connection failed");
            } finally { //Will make sure it is closed
                try {
                    if (statement != null) {
                        statement.close();
                    }
                }catch (SQLException se2) {
                } //Nothing that can be done
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            return pubs;
        }

        @Override
        protected void onPostExecute(ArrayList<Pub> s){
            super.onPostExecute(s);
        }
    }
    /**
     * Retrieves all the Towns in the database
     *
     * @return an ArrayList of strings of all the towns in the database.
     */
    @Override
    public ArrayList<String> retrieveTowns(){
        ArrayList<String> towns  = new ArrayList<>();
        try { //Tries to retrieve
            towns = new TownAsynctask().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        ArrayList<Pub> pubs = this.retrievePubs(towns.get(0));
        return towns;
    }
    private class TownAsynctask extends AsyncTask<Void, Integer, ArrayList<String>> {
        @Override
        protected ArrayList<String> doInBackground(Void... params) {
            ArrayList<String> towns = new ArrayList<>();
            String SQL = "SELECT townName FROM towns"; //SQL that will be used

            //Setup for connecting to database
            Connection connection = null;
            Statement statement = null;
            try {
                Class.forName(JDBC_DRIVER);
                System.out.println("DB Driver Found");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                System.out.println("DB Driver not found");
            }

            try {
                System.out.println("Connecting to database... ");
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Creating Statement...");
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(SQL);

                while (resultSet.next()) {
                    //Get from resultSet
                    String townName = resultSet.getString("townName"); // get result from "townName"
                    towns.add(townName); //Add to town list
                }
                //Close connections
                resultSet.close();
                statement.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Connection failed");
            } finally { //Will make sure it is closed
                try {
                    if (statement != null) {
                        statement.close();
                    }
                }catch (SQLException se2) {
                } //Nothing that can be done
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            return towns;
        }

        @Override
        protected void onPostExecute(ArrayList<String> s){
            super.onPostExecute(s);
        }
    }
}
