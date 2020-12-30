package aber.group9.pubapp.object;

import java.util.ArrayList;
import java.util.Date;

public interface PubCrawlInterface {

    //getters and setters for the times
    int getStartTimeHours();
    void setStartTimeHours(int startTimeHours);
    int getStartTimeMins();
    void setStartTimeMins(int startTimeMins);
    int getInterval();
    void setInterval(int interval);

    //Managing the array list
    ArrayList<Pub> getPubsInCrawl();
    void setPubsInCrawl(ArrayList<Pub> pubsInCrawl);
    void addPub(Pub pub);
    void removeCurrentPub();

}
