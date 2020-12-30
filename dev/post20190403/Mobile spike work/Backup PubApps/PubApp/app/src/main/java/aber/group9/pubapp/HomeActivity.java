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
                locName = extras.getString("name");
            }
        }else{
            locName= (String) getIntent().getStringExtra("name");
        }

        TextView locationName  = (TextView)
                findViewById(R.id.textView_LocationName);

        locationName.setText(locName);
    }

    public void viewPubs(View view){
        Intent showPubListIntent = new Intent(this,PubListActivity.class);
        showPubListIntent.putExtra("pubs",pubs);
        startActivity(showPubListIntent);
    }

    public void viewCrawl(View view){
        Intent viewCrawlIntent = new Intent(HomeActivity.this, EmptyTourActivity.class);
        viewCrawlIntent.putExtra("pubs",pubs);
        startActivity(viewCrawlIntent);
    }

    public void viewFilters(View view){
        Intent viewFiltersIntent = new Intent(HomeActivity.this, FilterActivity.class);
        viewFiltersIntent.putExtra("pubs", pubs);
        startActivity(viewFiltersIntent);
    }

    public void changeLocation(View view){
        startActivity(new Intent(HomeActivity.this, SelectLocationActivity.class));
    }

    public void goHome(View view){
        Intent homeIntent = new Intent(this, HomeActivity.class);
        startActivity(homeIntent);
    }

    public void back(View view){
        finish();
    }

}
