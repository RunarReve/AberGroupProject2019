/*
 * @(#) DbUpdateInterface.java 0.1 2019/03/05
 *
 * Copyright (c) 2019 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group09.database;

import uk.ac.aber.cs221.group09.object.Pub;
import uk.ac.aber.cs221.group09.object.Town;
import java.sql.SQLException;

/**
 * DbUpdaterInterface - An interface for how to update the database.
 * <p>
 * Both town and pubs have their separate methods for updating the database.
 * In every method it has to take in a Statement that is the connection with the database.
 * For receiving data from the database see DBReceiverInterface.
 *
 * @author Runar Reve (rur7)
 * @version 0.1 Draft
 * @see DbReceiverInterface
 */
public interface DbUpdaterInterface {

    //-----PUB-----
    /**
     * Adds a new pub to the database.
     *
     * @param newPub the new pub that is going to be added.
     * @throws SQLException if an SQL error occurs.
     */
    void addPub(Pub newPub)
            throws SQLException;

    /**
     * Remove an existing pub from the database.
     *
     * @param pub the pub that is being removed.
     * @return true if pub successfully removed the pub.
     * @throws SQLException if an SQL error occurs.
     */
    boolean removePub(Pub pub)
            throws SQLException;

    //-----TOWN-----
    /**
     * Adds a new town in the database.
     *
     * @param newTown the new town that is being pushed.
     * @throws SQLException if an SQL error occurs.
     */
    void addTown(Town newTown)
            throws SQLException;

    /**
     * Remove an existing town from the database.
     *
     * @param town the town that is being removed.
     * @return true if town was successfully removed.
     * @throws SQLException if an SQL error occurs.
     */
    boolean removeTown(Town town)
            throws SQLException;

}
