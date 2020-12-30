//TODO: Delete if unnecessary, along with other interfaces

/*
   * @(#) PubTourInterface.java 0.1 2019/03/05
   *
   * Copyright (c) 2019 University of Wales, Aberystwyth.
   * All rights reserved.
   *
   */

package uk.ac.aber.cs221.group09;

import uk.ac.aber.cs221.group09.object.Pub;
import java.util.ArrayList;

  /**
   * PubTourInterface - A interface to modify the data for in ways the user wants.
   * <p>
   * Some classes to sort to filter pubs, "filterPubs", that you give it following an example pub that has the attributes the user wants.
   * There is a randomize pub where it will extract the amount of pubs you want randomly and put them in another list.
   *
   * @author Runar Reve (rur7)
   * @version 0.1 Draft
   */
public interface PubTourInterface {

    /**
     * Will filter out pubs to a new ArrayList that only has wanted attributes.
     *
     * @param mainList the main list that is going to be filtered.
     * @param samplePub example pub that has the wanted attributes, null where N/A.
     * @return a new ArrayList with pubs with wanted attributes.
     */
    ArrayList<Pub> filterPubs(ArrayList<Pub> mainList, Pub samplePub);

    /**
     * Create a random pub tour.
     *
     * @param mainPubList is the list that is going to be extracting pubs out from.
     * @param amountOfPubs the amount of pubs wanted in the list.
     * @return a new ArrayList with pubs that is going to be in the tour.
     */
    ArrayList<Pub> randomPubs(ArrayList<Pub> mainPubList, int amountOfPubs);
}
