// TODO: Remove if still redundant at end

/*
   * @(#) uk.ac.aber.cs221.group09.object.TownInterface.java 0.1 2019/03/05
   *
   * Copyright (c) 2002 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */
package uk.ac.aber.cs221.group09.object;
import uk.ac.aber.cs221.group09.database.DbCommunicator;

import java.util.ArrayList;

/**
   * uk.ac.aber.cs221.group09.object.TownInterface - An interface for a town class.
   * <p>
   * Will be used to create town objects and manipulate the data of it.
   * Use the getters and setters to retrieve or edit the towns data.
   * townToDatabase is used to convert the object into a string that can be used to communicate with a database
   *
   * @author (Runar Reve, rur7)
   * @version 0.1  (Draft)
   * @see Town
   * @see DbCommunicator
   */
public interface TownInterface {

    /**
     * Getter for the towns name
     * @return the towns name
     */
    String getTownName();

    /**
     * Setter for the towns name
     * @param townName the new name of the town
     */
    void setTownName(String townName);

    /**
     * Converts the information of a town into something the database can read
     * @param town the town that is being converted
     * @return string that can be used in a SQL command
     */
    String townToDatabase(Town town);
}
