package uk.ac.aber.cs221.group09;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import uk.ac.aber.cs221.group09.database.DbCommunicator;
import uk.ac.aber.cs221.group09.database.DbReceiverInterface;
import uk.ac.aber.cs221.group09.database.DbUpdaterInterface;
import uk.ac.aber.cs221.group09.object.Pub;
import uk.ac.aber.cs221.group09.object.Town;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.ArrayList;
/**
 * DbTest - This is the class that is responsible for testing the methods in the database.
 * <p>
 * When this test class is run, it will add a Town object, testTown and proceed to add it into the Town array list in the database.
 * It will also include a Pub object, testPub with all of its test filters and adds it into the Pub array list in the database.
 * This test will then proceed to remove the Pub and Town's ArrayList.
 * This class test checks if the methods are functioning as required.
 *
 * @author Sid Limbu (sil19)
 * @version 0.1 Draft
 * @see Town
 * @see Pub
 * @see DbCommunicator
 */

    public class DbTest {

        @Test
        public void databaseTest() {
            DbCommunicator test1 = new DbCommunicator();
            Town testTown = new Town("Test town");
            test1.addTown(testTown);
            ArrayList<Town> towns = test1.retrieveTowns();
            assertTrue(towns.contains(testTown));

            Pub testPub= new Pub("Pier", "Test town", "nice town", "abc", 1.0f,
                    1.0f, "abc", "abc", true, false, false, false, false, false);
            test1.addPub(testPub);
            ArrayList<Pub> pubs= test1.retrievePubs("Test town");
            assertEquals(1, pubs.size());


            //Pub removePubTest= new Pub("Pier", "Test town","Nice town", "efg", 1.0f, 1.0f ," abcde","HA2 8PG",
                  //  true, true, true, false, false, false);
            Pub removePubTest= new Pub("Pier", "Test town", "nice town", "abc", 1.0f, 1.0f, "def", "ghi", true, false, false, false, false, false);
            test1.removePub(removePubTest);
            ArrayList<Pub> pubs1= test1.retrievePubs("Test town");
            assertEquals(0, pubs1.size());

            int sizeOfTownList= towns.size();
            test1.removeTown(testTown);
            towns=test1.retrieveTowns();
            int newSizeOfTown= towns.size();
            System.out.println(sizeOfTownList+""+ newSizeOfTown);
            assertTrue(newSizeOfTown==sizeOfTownList-1);





        }







    }

