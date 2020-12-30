package aber.group9.pubapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

import aber.group9.pubapp.database.DbReceiver;
import aber.group9.pubapp.object.Pub;

public class SelectLocationActivity extends AppCompatActivity {
    ArrayList<Pub> pubs = new ArrayList<>();
    ArrayList<Pub> allPubs = new ArrayList<>();
    ArrayList<Pub> tour = new ArrayList<>();
    int isCrawl;
    int time;
    int interval;

    static class ViewHolder {
        TextView locationName;
        Button button;
    }


    Button btn_selectLocation;
    List<String> locations;
    ListView listView;
    LocationsListAdapter myLocationListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_location);
        isCrawl = 0; //this will be used to keep track of if a tour is in progress
        listView = (ListView) findViewById(R.id.location_list);
        btn_selectLocation = (Button) findViewById(R.id.btn_select_location);
        initLocations();
        myLocationListAdapter = new LocationsListAdapter(this,locations);
        listView.setAdapter(myLocationListAdapter);

    }

    private class LocationsListAdapter extends BaseAdapter {
        private Context context;
        private List<String> list;

        LocationsListAdapter(Context c, List<String> l) {
            context = c;
            list = l;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = new ViewHolder();
            if (convertView==null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                convertView = inflater.inflate(R.layout.location_list_layout, null);

                viewHolder.locationName = (TextView) convertView.findViewById(R.id.textView_LocationName);
                viewHolder.button = (Button) convertView.findViewById(R.id.btn_select_location);

                convertView.setTag(viewHolder);
            }else
                viewHolder=(ViewHolder)convertView.getTag();

            final String locName = list.get(position);
            viewHolder.locationName.setText(locName);

            viewHolder.button.setTag(position);
            viewHolder.button.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    initPubs(position);
                   showHomeScreen(v,list.get(position));
                }
            });

            return convertView;
        }
    }

    private void initLocations() {
        locations = new ArrayList<>();
        DbReceiver com = new DbReceiver();
        locations.addAll(com.retrieveTowns());
    }


    private void initPubs(int townIndex){
        DbReceiver com = new DbReceiver();
        pubs = com.retrievePubs(locations.get(townIndex));
        allPubs = pubs;
    }

    private void showHomeScreen(View view, String name){
        Intent showHomeIntent = new Intent(this, HomeActivity.class);
        showHomeIntent.putExtra("town_name",name);
        showHomeIntent.putExtra("pubs",pubs);
        showHomeIntent.putExtra("all_pubs", allPubs);
        showHomeIntent.putExtra("pub_crawl", tour);
        showHomeIntent.putExtra("is_crawl", isCrawl);
        showHomeIntent.putExtra("time_mins", time);
        showHomeIntent.putExtra("interval",interval);
        startActivity(showHomeIntent);
    }

}