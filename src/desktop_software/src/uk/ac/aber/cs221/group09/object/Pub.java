// TODO: Reorder vars and related getters/setters to a consistent format

/*
 * @(#) Pub.java 0.1 2019/03/05
 *
 * Copyright (c) 2019 University of Wales, Aberystwyth.
 * All rights reserved.
 *
 */

package uk.ac.aber.cs221.group09.object;

import uk.ac.aber.cs221.group09.database.DbCommunicator;

/**
 * Pub - Used to create pub objects, which hold information about pubs.
 * <p>
 * Will be used to create a pub object and manipulate the data of it.
 * Use the getters and setters to retrieve or edit the pubs data.
 * pubToDatabase is used to convert the object into a string that can be used to communicate with a database.
 *
 * @author Runar Reve (rur7)
 * @version 0.1 Draft
 * @see DbCommunicator
 */
public class Pub
        implements PubInterface {

    //-----STANDARD-INFO-----
    private String pubName;
    private String town;
    private String description;
    private String imgLink; //Link to the picture
    private float xCoordinate;
    private float yCoordinate;
    private String address;
    private String postCode;

    //-----FILTERS-----
    private boolean hasFood;
    private boolean hasRealAle;
    private boolean allowsDogs;
    private boolean loudMusic;
    private boolean club;
    private boolean TV;

    //TODO: add and comment parameters
    /**
     * A constructor for pubs.
     *
     * @param pubName the name of the pub.
     * @param town the town that the pub belongs to.
     * @param description text describing the pub.
     * @param imgLink String of a link to an image of the pub
     * @param xCoordinate the geographical positioning of the pub in latitude.
     * @param yCoordinate the geographical positioning of the pub in longitude.
     * @param filters the characteristics of the pub (whether it allows dogs, whether it serves food, etc.).
     */
    public Pub(String pubName, String town, String description, String imgLink,
               float xCoordinate,float yCoordinate, String address, String postCode,
               boolean[] filters) {
        this.pubName = pubName;
        this.town = town;
        this.description = description;
        this.imgLink = imgLink;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.address = address;
        this.postCode = postCode;
        this.hasFood = filters[1];
        this.hasRealAle = filters[2];
        this.allowsDogs = filters[3];
        this.loudMusic = filters[4];
        this.club = filters[5];
        this.TV = filters[6];
    }

    public Pub(String pubName, String town, String description, String imgLink,
               float xCoordinate,float yCoordinate, String address, String postCode,
               boolean hasFood,boolean hasRealAle,boolean allowsDogs,boolean loudMusic, boolean club, boolean TV) {
        this.pubName = pubName;
        this.town = town;
        this.description = description;
        this.imgLink = imgLink;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.address = address;
        this.postCode = postCode;
        this.hasFood = hasFood;
        this.hasRealAle = hasRealAle;
        this.allowsDogs = allowsDogs;
        this.loudMusic = loudMusic;
        this.club = club;
        this.TV = TV;
    }

    /**
     * Will convert the pubs information to be an SQL INSERT command
     * @return a String that can be used as a SQL command
     */
    @Override
    public String pubToDatabase() {
        String sql = "INSERT INTO pubs(pubName, townName, xCoordinate, yCoordinate, " +
                "address, postCode, description, imgLink, hasFood, hasRealAle, club, " +
                "allowsDogs, loudMusic, TV) "
                + "VALUES('";
        sql = sql + getPubName()     + "', '";
        sql = sql + getTown()        + "', ";
        sql = sql + getxCoordinate() + ", ";
        sql = sql + getyCoordinate() + ", '";
        sql = sql + getAddress()     + "', '";
        sql = sql + getPostCode()    + "', '";
        sql = sql + getDescription() + "', '";
        sql = sql + getImgLink()     + "', ";
        sql = sql + isHasFood()      + ", ";
        sql = sql + isHasRealAle()   + ", ";
        sql = sql + isClub()         + ", ";
        sql = sql + isAllowsDogs()   + ", ";
        sql = sql + isLoudMusic()    + ", ";
        sql = sql + isTV()           + ");";
        return sql;
    }

    /**
     * Retrieves the name of the pub.
     *
     * @return the name of the pub.
     */
    public String getPubName() {return pubName;}

    /**
     * Changes the name of the pub to the input name.
     *
     * @param pubName the new pub name.
     */
    public void setPubName(String pubName) {this.pubName = pubName;}

    /**
     * Retrieves the name of the town that the pub is located in.
     *
     * @return the town of the pub.
     */
    public String getTown() {return town;}

    /**
     * Changes the name of the town that the pub is located in.
     *
     * @param town the name of the new town.
     */
    public void setTown(String town) {this.town = town;}

    /**
     * Retrieves the textual description of the pub.
     *
     * @return the pub's textual description.
     */
    public String getDescription() {return description;}

    //TODO add img stuff to interface
    /**
     * Adds a link where a picture of the pub is
     *
     * @param imgLink the link to where a picture of the pub is.
     */
    public void setImgLink(String imgLink) {this.imgLink = imgLink;}

    /**
     * Retrieves the textual description of the pub.
     *
     * @return a link to a picture of the pub
     */
    public String getImgLink() {return imgLink;}

    /**
     * Changes the textual description of the pub.
     *
     * @param description the new textual description for the pub.
     */
    public void setDescription(String description) {this.description = description;}

    /**
     * Retrieves the latitude of the pub's physical location.
     *
     * @return the pub's latitude.
     */
    public float getxCoordinate() {return xCoordinate;}

    /**
     * Changes the latitude of the pub's physical location.
     *
     * @param xCoordinate the new latitude for the pub.
     */
    public void setxCoordinate(float xCoordinate) {this.xCoordinate = xCoordinate;}

    /**
     * Retrieves the longitude of the pub's physical location.
     *
     * @return the pub's longitude.
     */
    public float getyCoordinate() {return yCoordinate;}

    /**
     * Changes the longitude of the pub's physical location.
     *
     * @param yCoordinate the new longitude for the pub.
     */
    public void setyCoordinate(float yCoordinate) {this.yCoordinate = yCoordinate;}

    //TODO write comments and format it better
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    /**
     * Retrieves whether the pub serves food or not.
     *
     * @return whether or not the pub serves food.
     */
    public boolean isHasFood() {return hasFood;} // TODO: Rename getters for boolean vars to be more sentence-like (eg. doesPubHaveFood)

    /**
     * Changes whether or not the pub serves food.
     *
     * @param hasFood the new setting for whether or not the pub serves food.
     */
    public void setHasFood(boolean hasFood) {this.hasFood = hasFood;}

    /**
     * Retrieves whether the pub serves real ale or not.
     *
     * @return whether or not the pub serves real ale.
     */
    public boolean isHasRealAle() {return hasRealAle;}

    /**
     * Changes whether or not the pub serves real ale.
     *
     * @param hasRealAle the new setting for whether or not the pub serves real ale.
     */
    public void setHasRealAle(boolean hasRealAle) {this.hasRealAle = hasRealAle;}

    /**
     * Retrieves whether or not the pub allows dogs.
     *
     * @return whether or not the pub allows dogs.
     */
    public boolean isAllowsDogs() {return allowsDogs;}

    /**
     * Changes wheter or not the pub allows dogs.
     *
     * @param allowsDogs the new setting for whether or not the pub allows dogs.
     */
    public void setAllowsDogs(boolean allowsDogs) {this.allowsDogs = allowsDogs;}

    /**
     * Retrieves whether or not the pub plays loud music.
     *
     * @return whether or not the pub plays loud music.
     */
    public boolean isLoudMusic() {return loudMusic;}

    /**
     * Changes whether or not the pub plays loud music.
     *
     * @param loudMusic the new setting for whether or not the pub plays loud music.
     */
    public void setLoudMusic(boolean loudMusic) {this.loudMusic = loudMusic;}

    /**
     * Retrieves whether or not the pub is a club.
     *
     * @return whether or not the pub is a club.
     */
    public boolean isClub() {return club;}

    /**
     * Changes whether or not the pub is a club.
     *
     * @param club the new setting for whether or not the pub is a club.
     */
    public void setClub(boolean club) {this.club = club;}

    /**
     * Retrieves whether or not the pub has a TV.
     *
     * @return whether or not the pub has a TV.
     */
    public boolean isTV() {return TV;}

    /**
     * Changes whether or not the pub has a TV.
     *
     * @param tv the new setting for whether or not the pub has a TV.
     */
    public void setTV(boolean tv) {this.TV = tv;}
}
