/*
 * @(#) DbReceiverInterface.java 0.1 2019/03/15
 *
 * Copyright (c) 2019 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group09.database;

import uk.ac.aber.cs221.group09.object.Pub;
import uk.ac.aber.cs221.group09.object.Town;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * DbReceiverInterface - A interface for how to receive towns and pubs from the database.
 * <p>
 * Both town and pubs have their separate methods for retrieving data from the database.
 * In every method it has to take in a Statement that is the connection with the database.
 * For updating the database see DbUpdateInterface.
 *
 * @author Runar Reve (rur7)
 * @version 0.1 Draft
 * @see DbUpdaterInterface
 */
public interface DbReceiverInterface {

    //-----PUB-----
    /**
     * Will retrieve specific pubs depending on the filters that is given to it.
     *
     * @param town name of town that is being retrieved.
     * @return an ArrayList of pubs following the filters.
     * @throws SQLException if an SQL error occurs.
     */
    ArrayList<Pub> retrievePubs(String town)
            throws SQLException;

    //-----TOWN-----
    /**
     * Retrieves all the Towns in the database
     *
     * @return an ArrayList of all the towns in the database.
     * @throws SQLException if an SQL error occurs.
     */
    ArrayList<Town> retrieveTowns()
            throws SQLException;


}
