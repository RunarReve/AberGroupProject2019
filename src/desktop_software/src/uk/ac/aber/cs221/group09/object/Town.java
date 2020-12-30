/*
 * @(#) Town.java 0.1 2019/03/05
 *
 * Copyright (c) 2019 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs221.group09.object;

import uk.ac.aber.cs221.group09.database.DbCommunicator;

import java.util.ArrayList;

/**
 * Town - Used to create town objects.
 * <p>
 * Will be used to create town objects and manipulate the data of it.
 * Use the getters and setters to retrieve or edit the towns data.
 * townToDatabase is used to convert the object into a string that can be used to communicate with a database.  TODO: Update this line's phrasing, esp. to reflect any changes to the named method
 *
 * @author Runar Reve (rur7)
 * @version 0.1 Draft
 * @see TownInterface
 * @see DbCommunicator
 */
public class Town

implements TownInterface {

    private String townName;
    private int townId;
    @Override
    public boolean equals(Object o){
        Town t= (Town)o;
        if(this.townName.equals(t.getTownName())){
            return true;
        }
        return false;
    }// TODO: Remove if still unused.

    /**
     * Constructor without parameters.
     */
    Town(){ // TODO: Remove if still unused
        townName = "N/A";
    }

    /**
     * Constructor that receives and sets the town's name.
     *
     * @param name the name of the town.
     */
    public Town(String name){
        townName = name;
    }

    /**
     * Retrieves the name of the town.
     *
     * @return the name of the town.
     */
    @Override
    public String getTownName() {return townName;}

    /**
     * Changes the name of the town.
     *
     * @param townName the new name of the town.
     */
    @Override
    public void setTownName(String townName) {this.townName = townName;}

    // TODO: Remove or add description.
    @Override
    public String townToDatabase(Town t) {
        return null;
    }

}