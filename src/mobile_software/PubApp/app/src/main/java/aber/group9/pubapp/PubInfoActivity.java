package aber.group9.pubapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import aber.group9.pubapp.object.Pub;

public class PubInfoActivity extends AppCompatActivity {
    Pub pub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub_info);
        showPubInformation();

    }

    public void showPubInformation() {
        //TODO Assign the extra attributes to their corresponding textViews (as below) - YOU NEED TO PASS THE EXTRA ATTRIBUTES INTO THIS ACTIVITY FIRST (SEE TODO'S ON PUBLISTACTIVITY)
        pub = getIntent().getParcelableExtra("selected_pub");;

        TextView pubName = (TextView)
                findViewById(R.id.txt_pub_name);

        TextView pubDesc = (TextView)
                findViewById(R.id.txt_pub_description);

        TextView pubAddress = (TextView)
                findViewById(R.id.txt_pub_address);

        TextView pubFeaturesOne = (TextView)
                findViewById(R.id.txt_pub_features);

        TextView pubFeatiresTwo = (TextView)
                findViewById(R.id.txt_pub_features_2);


        pubName.setText(pub.getPubName());
        pubDesc.setText(pub.getDescription());
        pubAddress.setText(createAddressString());
        pubFeaturesOne.setText(createFiltersStringOne());
        pubFeatiresTwo.setText(createFiltersStrinTwo());

    }

    public String createAddressString(){
        return pub.getAddress() + ", " + pub.getTown() + ".\n" + pub.getPostCode() + ".";
    }

    public String createFiltersStringOne(){
        StringBuilder sb = new StringBuilder();

        if(pub.isHasFood()) sb.append("Serves food.\n");
        else sb.append("Does not serve food.\n");

        if(pub.isHasRealAle()) sb.append("Serves real ale.\n");
        else sb.append("Does not serve real ale.\n");

        if(pub.isAllowsDogs()) sb.append("Allows dogs.");
        else sb.append("Does not allow dogs.");

        return sb.toString();
    }

    public String createFiltersStrinTwo(){
        StringBuilder sb = new StringBuilder();

        if(pub.isLoudMusic()) sb.append("Plays loud music.\n");
        else sb.append("Does not play loud music.\n");

        if(pub.isClub()) sb.append("Is a club.\n");
        else sb.append("Is a pub/bar.\n");

        if(pub.isTV()) sb.append("Has a TV.");
        else sb.append("Does not have a TV.");

        return sb.toString();
    }

//    public void goHome(View view){
//        Intent homeIntent = new Intent(this, HomeActivity.class);
//        homeIntent.putExtra("pubs", getIntent().getParcelableArrayListExtra("pubs"));
//        homeIntent.putExtra("all_pubs",
//                getIntent().getParcelableArrayListExtra("all_pubs"));
//        homeIntent.putExtra("town_name", getIntent().getStringExtra("town_name"));
//        startActivity(homeIntent);
//    }
    public void goHome(View view){
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("pubs",
                getIntent().getParcelableArrayListExtra("pubs"));
        homeIntent.putExtra("all_pubs",
                getIntent().getParcelableArrayListExtra("all_pubs"));
        homeIntent.putExtra("town_name",
                getIntent().getStringExtra("town_name"));
        homeIntent.putExtra("pub_crawl",
                getIntent().getParcelableArrayListExtra("pub_crawl"));
        homeIntent.putExtra("is_crawl",
                getIntent().getIntExtra("is_crawl", 0));
        homeIntent.putExtra("time_mins",
                getIntent().getIntExtra("time_mins", 0));
        homeIntent.putExtra("interval",
                getIntent().getIntExtra("interval", 0));
        startActivity(homeIntent);
    }

    public void back(View view){
        finish();
    }
}
