package aber.group9.pubapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import aber.group9.pubapp.object.Pub;


public class HomeActivity extends AppCompatActivity {
    ArrayList<Pub> pubs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //pubs = getIntent().getParcelableExtra("pubs");
        pubs = getIntent().getParcelableArrayListExtra("pubs");
        setContentView(R.layout.activity_home);
        showLocationInformation();
    }

    public void showLocationInformation(){

        String locName;
        if (getIntent().getExtras() == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                locName = null;
            } else {
                locName = extras.getString("town_name");
            }
        }else{
            locName= (String) getIntent().getStringExtra("town_name");
        }

        TextView locationName  = (TextView)
                findViewById(R.id.textView_LocationName);

        locationName.setText(locName);
    }

    public void viewPubs(View view){
        Intent showPubListIntent = new Intent(this,PubListActivity.class);
        showPubListIntent.putExtra("pubs",pubs);
        showPubListIntent.putExtra("all_pubs",
                getIntent().getParcelableArrayListExtra("all_pubs"));
        showPubListIntent.putExtra("town_name",
                getIntent().getStringExtra("town_name"));
        showPubListIntent.putExtra("pub_crawl",
                getIntent().getParcelableArrayListExtra("pub_crawl"));
        showPubListIntent.putExtra("is_crawl",
                getIntent().getIntExtra("is_crawl", 0));
        showPubListIntent.putExtra("time_mins",
                getIntent().getIntExtra("time_mins", 0));
        showPubListIntent.putExtra("interval",
                getIntent().getIntExtra("interval", 0));
        startActivity(showPubListIntent);
    }

    public void viewCrawl(View view){
        Intent viewCrawlIntent;
        ArrayList<Pub> check = new ArrayList<>();
        check = getIntent().getParcelableArrayListExtra("pub_crawl");

        if (check.size() == 0){
        viewCrawlIntent = new Intent(HomeActivity.this, EmptyTourActivity.class);
        viewCrawlIntent.putExtra("pubs",pubs);
        viewCrawlIntent.putExtra("all_pubs",
                getIntent().getParcelableArrayListExtra("all_pubs"));
        viewCrawlIntent.putExtra("town_name",
                getIntent().getStringExtra("town_name"));
        viewCrawlIntent.putExtra("pub_crawl",
                getIntent().getParcelableArrayListExtra("pub_crawl"));
        viewCrawlIntent.putExtra("is_crawl",
                getIntent().getIntExtra("is_crawl", 0));
        viewCrawlIntent.putExtra("time_mins",
                getIntent().getIntExtra("time_mins", 0));
        viewCrawlIntent.putExtra("interval",
                getIntent().getIntExtra("interval", 0));
    } else{
            viewCrawlIntent = new Intent(HomeActivity.this, ViewTourActivity.class);
            viewCrawlIntent.putExtra("pubs",pubs);
            viewCrawlIntent.putExtra("all_pubs",
                    getIntent().getParcelableArrayListExtra("all_pubs"));
            viewCrawlIntent.putExtra("town_name",
                    getIntent().getStringExtra("town_name"));
            viewCrawlIntent.putExtra("pub_crawl",
                    getIntent().getParcelableArrayListExtra("pub_crawl"));
            viewCrawlIntent.putExtra("is_crawl",
                    getIntent().getIntExtra("is_crawl", 0));
            viewCrawlIntent.putExtra("time_mins",
                    getIntent().getIntExtra("time_mins", 0));
            viewCrawlIntent.putExtra("interval",
                    getIntent().getIntExtra("interval", 0));

        }
        startActivity(viewCrawlIntent);
    }

    public void viewFilters(View view){
        Intent viewFiltersIntent = new Intent(HomeActivity.this, FilterActivity.class);
        viewFiltersIntent.putExtra("pubs",pubs);
        viewFiltersIntent.putExtra("all_pubs",
                getIntent().getParcelableArrayListExtra("all_pubs"));
        viewFiltersIntent.putExtra("town_name",
                getIntent().getStringExtra("town_name"));
        viewFiltersIntent.putExtra("pub_crawl",
                getIntent().getParcelableArrayListExtra("pub_crawl"));
        viewFiltersIntent.putExtra("is_crawl",
                getIntent().getIntExtra("is_crawl", 0));
        viewFiltersIntent.putExtra("time_mins",
                getIntent().getIntExtra("time_mins", 0));
        viewFiltersIntent.putExtra("interval",
                getIntent().getIntExtra("interval", 0));
        startActivity(viewFiltersIntent);
    }

    public void changeLocation(View view){
        startActivity(new Intent(HomeActivity.this, SelectLocationActivity.class));
    }

    public void back(View view){
        finish();
    }

}
