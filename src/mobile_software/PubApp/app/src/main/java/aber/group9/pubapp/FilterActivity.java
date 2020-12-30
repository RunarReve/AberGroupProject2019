package aber.group9.pubapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.ArrayList;

import aber.group9.pubapp.object.Pub;

public class FilterActivity extends AppCompatActivity {

    private SeekBar barFood;
    private SeekBar barAle;
    private SeekBar barDogs;
    private SeekBar barMusic;
    private SeekBar barClub;
    private SeekBar barTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        barFood = (SeekBar) findViewById(R.id.barFood);
        barAle = (SeekBar) findViewById(R.id.barAle);
        barDogs = (SeekBar) findViewById(R.id.barDogs);
        barMusic = (SeekBar) findViewById(R.id.barMusic);
        barClub = (SeekBar) findViewById(R.id.barClub);
        barTV = (SeekBar) findViewById(R.id.barTV);
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

    public void doFilter(View view) {
        ArrayList<Pub> allPubs = getIntent().getParcelableArrayListExtra("all_pubs");

        ArrayList<Pub> filterPub = new ArrayList<>();
        for (int i = 0; i < allPubs.size(); i++)
            filterPub = filter(allPubs.get(i), filterPub);

        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("pubs", filterPub);
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
    private ArrayList<Pub> filter(Pub pub, ArrayList<Pub> list){
        if(barFood.getProgress() != 1){
            if(barFood.getProgress() == 0){
                if(pub.isHasFood())
                    return list;
            }
            else{
                if(!pub.isHasFood())
                    return list;
            }
        }
        if(barAle.getProgress() != 1){
            if(barAle.getProgress() == 0){
                if(pub.isHasRealAle())
                    return list;
            }
            else{
                if(!pub.isHasRealAle())
                    return list;
            }
        }
        if(barDogs.getProgress() != 1){
            if(barDogs.getProgress() == 0){
                if(pub.isAllowsDogs())
                    return list;
            }
            else{
                if(!pub.isAllowsDogs())
                    return list;
            }
        }
        if(barMusic.getProgress() != 1){
            if(barMusic.getProgress() == 0){
                if(pub.isLoudMusic())
                    return list;
            }
            else{
                if(!pub.isLoudMusic())
                    return list;
            }
        }
        if(barClub.getProgress() != 1){
            if(barClub.getProgress() == 0){
                if(pub.isClub())
                    return list;
            }
            else{
                if(!pub.isClub())
                    return list;
            }
        }
        if(barTV.getProgress() != 1){
            if(barTV.getProgress() == 0){
                if(pub.isTV())
                    return list;
            }
            else{
                if(!pub.isTV())
                    return list;
            }
        }
        list.add(pub);
        return list;
    }


}
