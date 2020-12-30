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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import aber.group9.pubapp.object.Pub;
import aber.group9.pubapp.object.PubCrawl;

public class CreateTourActivity extends AppCompatActivity {


    static class ViewHolder{
        ImageView pubPhoto;
        TextView pubTitle;
        Button moveUp, moveDown;
    }


    ArrayList<Pub> tour;
    ListView listView;
    TourListAdapater myTourListAdapter;
    int timeMins, interval;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tour);
        //get rid of this assigning later
        timeMins = 1430;
        interval = 30;

        listView = (ListView)findViewById(R.id.listview_pubTour);
        tour = getIntent().getParcelableArrayListExtra("pub_crawl");
        myTourListAdapter = new TourListAdapater(this,tour);
        listView.setAdapter(myTourListAdapter);
    }

    private class TourListAdapater extends BaseAdapter
    {
        private Context context;
        private List<Pub> list;

        TourListAdapater(Context c, List<Pub> l){
            context =c;
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

        public boolean isChecked(int position){
            return list.get(position).getChecked();
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = new ViewHolder();

            if (convertView==null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                convertView = inflater.inflate(R.layout.create_tour_layout,null);

                viewHolder.pubTitle = (TextView) convertView.findViewById(R.id.textView_LocationName);
                viewHolder.moveUp = (Button) convertView.findViewById(R.id.btn_goUp);
                viewHolder.moveDown = (Button) convertView.findViewById(R.id.btn_goDown);


                convertView.setTag(viewHolder);
            }
            else
                viewHolder =(ViewHolder)convertView.getTag();


            final String pubNm = list.get(position).getPubName();
            viewHolder.pubTitle.setText(pubNm);



            viewHolder.moveDown.setTag(position);
            viewHolder.moveDown.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    moveItemDown(v,position);
                }
            });

            viewHolder.moveUp.setTag(position);
            viewHolder.moveUp.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v){
                    moveItemUp(v,position);
                }
            });
            return convertView;
        }
    }


    public void moveItemDown(View v, int position){
        Pub temp;
        if(position + 1 < tour.size()) {
            temp = tour.get(position+1);
             tour.set(position+1, tour.get(position));
             tour.set(position, temp);
             ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
        }

    }

    public void moveItemUp(View v, int position){
        Pub temp;
        if(position > 0) {
            temp = tour.get(position-1);
            tour.set(position-1, tour.get(position));
            tour.set(position, temp);
            ((BaseAdapter) listView.getAdapter()).notifyDataSetChanged();
        }
    }


    public void startTour(View v){
        Intent startTourIntent = new Intent(this, ViewTourActivity.class);
        startTourIntent.putExtra("pub_crawl", tour);
        startTourIntent.putExtra("pubs", getIntent().getParcelableArrayListExtra("pubs"));
        startTourIntent.putExtra("time_mins", timeMins);
        startTourIntent.putExtra("interval", interval);
        startActivity(startTourIntent);
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
