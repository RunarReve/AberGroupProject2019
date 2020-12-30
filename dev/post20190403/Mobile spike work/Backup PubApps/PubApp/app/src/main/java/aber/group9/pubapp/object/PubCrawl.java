package aber.group9.pubapp.object;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class PubCrawl implements PubCrawlInterface, Parcelable {

    private ArrayList<Pub> pubsInCrawl;
    private int startTimeHours;
    private int startTimeMins;
    private int interval;

    public PubCrawl(ArrayList<Pub> pubsinCrawl){
        this.pubsInCrawl = pubsinCrawl;
        this.startTimeHours = 19;
        this.startTimeMins = 30;
        this.interval = 30;
    }

    @Override
    public ArrayList<Pub> getPubsInCrawl() {
        return pubsInCrawl;
    }

    @Override
    public void setPubsInCrawl(ArrayList<Pub> pubsInCrawl) {
        this.pubsInCrawl = pubsInCrawl;
    }

    @Override
    public void addPub(Pub pub){
        pubsInCrawl.add(pub);
    }

    @Override
    public void removeCurrentPub(){
        this.pubsInCrawl.remove(0);
    }


    @Override
    public int getStartTimeHours() {
        return startTimeHours;
    }

    @Override
    public void setStartTimeHours(int startTime) {
        this.startTimeHours = startTime;
    }

    @Override
    public int getStartTimeMins(){
        return startTimeMins;
    }

    @Override
    public void setStartTimeMins(int startTime){
        this.startTimeMins = startTime;
    }

    @Override
    public int getInterval() {
        return interval;
    }

    @Override
    public void setInterval(int interval) {
        this.interval = interval;
    }

    public void writeToParcel(Parcel dest, int flags){
        for (int i = 0; i <pubsInCrawl.size() ; i++) {
            dest.writeString(pubsInCrawl.get(i).getPubName());
            dest.writeString(pubsInCrawl.get(i).getTown());
            dest.writeString(pubsInCrawl.get(i).getDescription());
            dest.writeString(pubsInCrawl.get(i).getImgLink());
            dest.writeFloat(pubsInCrawl.get(i).getxCoordinate());
            dest.writeFloat(pubsInCrawl.get(i).getyCoordinate());
            dest.writeString(pubsInCrawl.get(i).getAddress());
            dest.writeString(pubsInCrawl.get(i).getPostCode());
//            dest.writeInt(pubsInCrawl.get(i).isHasFood() ? 1 : 0);
//            dest.writeInt(pubsInCrawl.get(i).isHasRealAle() ? 1 : 0);
//            dest.writeInt(pubsInCrawl.get(i).isAllowsDogs() ? 1 : 0);
//            dest.writeInt(pubsInCrawl.get(i).isLoudMusic() ? 1 : 0);
//            dest.writeInt(pubsInCrawl.get(i).isClub() ? 1 : 0);
//            dest.writeInt(pubsInCrawl.get(i).isTV() ? 1 : 0);
        }
        dest.writeInt(startTimeHours);
        dest.writeInt(startTimeMins);
        dest.writeInt(interval);
    }

    private PubCrawl(Parcel parcel){
        for(int i=0; i <pubsInCrawl.size();i++){
            pubsInCrawl.get(i).setPubName(parcel.readString());
            pubsInCrawl.get(i).setTown(parcel.readString());
            pubsInCrawl.get(i).setDescription(parcel.readString());
            pubsInCrawl.get(i).setImgLink(parcel.readString());
            pubsInCrawl.get(i).setxCoordinate(parcel.readFloat());
            pubsInCrawl.get(i).setyCoordinate(parcel.readFloat());
            pubsInCrawl.get(i).setAddress(parcel.readString());
            pubsInCrawl.get(i).setPostCode(parcel.readString());
//            hasFood = parcel.readInt() != 0;
//            hasRealAle = parcel.readInt() != 0;
//            pubsInCrawl.get(i).isAllowsDogs() = parcel.readInt() != 0;
//            pubsInCrawl.get(i).isLoudMusic() = parcel.readInt() != 0;
//            pubsInCrawl.set(i).isClub() = parcel.readInt() != 0;
//            pubsInCrawl.get(i).setTV(parcel.readInt());

        }
    }

    public static final Parcelable.Creator<PubCrawl> CREATOR = new Parcelable.Creator<PubCrawl>() {
        public PubCrawl createFromParcel(Parcel in){
            return new PubCrawl(in);
        }

        public PubCrawl[] newArray(int size){
            return new PubCrawl[size];
        }
    };


    public int describeContents() {
        return 0;
    }

    public static Creator<PubCrawl> getCreator(){
        return CREATOR;
    }


}
