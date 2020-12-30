// TODO: Remove if still redundant at end

/*
 * @(#) uk.ac.aber.cs221.group09.object.PubInterface.java 0.1 2019/03/05
 *
 * Copyright (c) 2002 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */
package uk.ac.aber.cs221.group09.object;
import uk.ac.aber.cs221.group09.database.DbCommunicator;

/**
 * uk.ac.aber.cs221.group09.object.PubInterface - An interface for a pub class.
 * <p>
 * Will be used to create a pub object and manipulate the data of it.
 * Use the getters and setters to retrieve or edit the pubs data.
 * pubToDatabase is used to convert the object into a string that can be used to communicate with a database
 *
 * @author (Runar Reve, rur7)
 * @version 0.1  (Draft)
 * @see Pub
 * @see DbCommunicator
 */
public interface PubInterface {


    /**
     * Converts the information of the pub into something the database can read
     * @return string that can be used in a SQL command
     */
    String pubToDatabase();

    //Getters and Setters for different private values
    String getPubName();
    void setPubName(String pubName);
    float getxCoordinate();
    void setxCoordinate(float xCoordinate);
    float getyCoordinate();
    void setyCoordinate(float yCoordinate);
    String getDescription();
    void setDescription(String description);
    boolean isHasFood();
    void setHasFood(boolean hasFood);
    boolean isHasRealAle();
    void setHasRealAle(boolean hasRealAle);
    boolean isClub();
    void setClub(boolean club);
    boolean isAllowsDogs();
    void setAllowsDogs(boolean allowsDogs);
    boolean isLoudMusic();
    void setLoudMusic(boolean loudMusic);
    boolean isTV();
    void setTV(boolean tv);


}
