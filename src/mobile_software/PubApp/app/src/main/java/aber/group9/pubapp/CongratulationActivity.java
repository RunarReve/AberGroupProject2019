package aber.group9.pubapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class CongratulationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congratulation);
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
}
