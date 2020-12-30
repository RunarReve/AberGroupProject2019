package aber.group9.pubapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import aber.group9.pubapp.object.Pub;

public class ViewTourActivity extends AppCompatActivity {
    //TODO add this class (remember to add the activity to the manifest)
    ArrayList<Pub> tour;
    View v;
    int timeMins, interval;
    TextView pubName, pubAddress, arrivalTime, departureTime, googleLink;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_tour);
        tour = getIntent().getParcelableArrayListExtra("pub_crawl");
        timeMins = getIntent().getIntExtra("time_mins", 0);
        interval = getIntent().getIntExtra("interval", 10);

        pubName = (TextView)findViewById(R.id.textView_pubName);
        pubAddress = (TextView)findViewById(R.id.textView_address);
        arrivalTime = (TextView)findViewById(R.id.textView_arrivalTime);
        departureTime = (TextView)findViewById(R.id.textView_departureTime);
        googleLink = (TextView)findViewById(R.id.textView_googleLink);

        pubName.setText(tour.get(0).getPubName());
        pubAddress.setText(createAddressString());
        arrivalTime.setText(createArrivalTimeString(timeMins));
        departureTime.setText(createDepatureTimeString(timeMins, interval));
        googleLink.setText(createGoogleLinkString());


    }

    public String createAddressString(){
        return tour.get(0).getAddress() + ", " + tour.get(0).getTown() + ".\n" + tour.get(0).getPostCode() + ".";
    }


    public String createArrivalTimeString(int mins){
        return (mins/60 < 10 ? "0" : "") + mins/60 + ":" + (mins%60 < 10 ? "0" : "") + mins%60;
    }


    public String createDepatureTimeString(int mins, int interval){
        mins += interval;
        mins = mins % 1440;
        return (mins/60 < 10 ? "0" : "") + mins/60 + ":" + (mins%60 < 10 ? "0" : "") + mins%60;

    }

    public String createGoogleLinkString(){
        return "https://www.google.com/maps/search/?api=1&query=" + tour.get(0).getxCoordinate() + "," + tour.get(0).getyCoordinate();
    }


    public void updateTour(View view){
        if  (tour.size() > 1){
            tour.remove(0);
            pubName.setText(tour.get(0).getPubName());
            pubAddress.setText(createAddressString());
            timeMins += interval;
            timeMins = timeMins % 1440;
            arrivalTime.setText(createArrivalTimeString(timeMins));
            departureTime.setText(createDepatureTimeString(timeMins, interval));
            googleLink.setText(createGoogleLinkString());

        } else showCongratulations(view);
    }



    public void showCongratulations(View view){
        Intent congratulationIntent = new Intent(this, CongratulationActivity.class);
        congratulationIntent.putExtra("pubs", getIntent().getParcelableArrayListExtra("pubs"));
        startActivity(congratulationIntent);
    }


    public void goHome(View view){
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("pubs", getIntent().getParcelableArrayListExtra("pubs"));
        startActivity(homeIntent);
    }

    public void back(View view){
        finish();
    }
}
