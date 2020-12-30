package aber.group9.pubapp;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Random;

import aber.group9.pubapp.object.Pub;

public class EmptyTourActivity extends AppCompatActivity {
    int maxPubs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_tour);
    }

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

    public void viewPubs(View view){
        Intent viewPubsIntent = new Intent(EmptyTourActivity.this, PubListActivity.class);
        viewPubsIntent.putExtra("pubs",
                getIntent().getParcelableArrayListExtra("pubs"));
        viewPubsIntent.putExtra("all_pubs",
                getIntent().getParcelableArrayListExtra("all_pubs"));
        viewPubsIntent.putExtra("town_name",
                getIntent().getStringExtra("town_name"));
        viewPubsIntent.putExtra("pub_crawl",
                getIntent().getParcelableArrayListExtra("pub_crawl"));
        viewPubsIntent.putExtra("is_crawl",
                getIntent().getIntExtra("is_crawl", 0));
        viewPubsIntent.putExtra("time_mins",
                getIntent().getIntExtra("time_mins", 0));
        viewPubsIntent.putExtra("interval",
                getIntent().getIntExtra("interval", 0));
        startActivity(viewPubsIntent);
    }

    public void randomTour(View view){
        ArrayList<Pub> pubs = getIntent().getParcelableArrayListExtra("pubs");
        ArrayList<Pub> pubTour = new ArrayList<>();


        if(pubs.size() > 1){
                Random random = new Random();
                while(maxPubs < 1)
                    maxPubs= random.nextInt(pubs.size());

            Collections.shuffle(pubs);
            for (int i = 0; i < maxPubs; i++) {
                pubTour.add(pubs.get(i));
            }

            Intent createCrawlIntent = new Intent(this, CreateTourActivity.class);
            createCrawlIntent.putExtra("pubs", pubs);
            createCrawlIntent.putExtra("all_pubs",
                    getIntent().getParcelableArrayListExtra("all_pubs"));
            createCrawlIntent.putExtra("town_name",
                    getIntent().getStringExtra("town_name"));
            createCrawlIntent.putExtra("pub_crawl", pubTour);
            createCrawlIntent.putExtra("is_crawl",
                    getIntent().getIntExtra("is_crawl", 0));
            createCrawlIntent.putExtra("time_mins",
                    getIntent().getIntExtra("time_mins", 0));
            createCrawlIntent.putExtra("interval",
                    getIntent().getIntExtra("interval", 0));

            startActivity(createCrawlIntent);
        }
    }
}
