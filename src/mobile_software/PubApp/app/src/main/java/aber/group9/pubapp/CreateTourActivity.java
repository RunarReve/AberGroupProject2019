package aber.group9.pubapp;

import android.app.Activity;
import android.app.TimePickerDialog;
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
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import aber.group9.pubapp.object.Pub;

public class CreateTourActivity extends AppCompatActivity {
    static class ViewHolder{
        ImageView pubPhoto;
        TextView pubTitle;
        Button moveUp, moveDown;
    }
    //Set default start time to current time
    Calendar i = Calendar.getInstance();
    int startMinutesOfDay = (i.get(Calendar.HOUR_OF_DAY) * 60) + i.get(Calendar.MINUTE);
    int intervalMinutes = 30;

    ArrayList<Pub> tour;
    ListView listView;
    TourListAdapater myTourListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_tour);
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
        //TODO add this function, and change the timeMins and interval to whatever you named them and set the start tour button to call this on click
        Intent startTourIntent = new Intent(this, ViewTourActivity.class);

        startTourIntent.putExtra("pubs",
                getIntent().getParcelableArrayListExtra("pubs"));
        startTourIntent.putExtra("all_pubs",
                getIntent().getParcelableArrayListExtra("all_pubs"));
        startTourIntent.putExtra("town_name",
                getIntent().getStringExtra("town_name"));
        startTourIntent.putExtra("pub_crawl", tour);
        startTourIntent.putExtra("is_crawl",1);
        startTourIntent.putExtra("time_mins", startMinutesOfDay);
        startTourIntent.putExtra("interval", intervalMinutes);
        startActivity(startTourIntent);
    }

    public void goHome(View view){
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("pubs",
                getIntent().getParcelableArrayListExtra("pubs"));
        homeIntent.putExtra("all_pubs",
                getIntent().getParcelableArrayListExtra("all_pubs"));
        homeIntent.putExtra("town_name",
                getIntent().getStringExtra("town_name"));
        homeIntent.putExtra("pub_crawl", tour);
        homeIntent.putExtra("is_crawl",
                getIntent().getIntExtra("is_crawl",0));
        homeIntent.putExtra("time_mins", startMinutesOfDay);
        homeIntent.putExtra("interval", intervalMinutes);
        startActivity(homeIntent);
    }

    public void back(View view){
        finish();
    }

    public void selectStartTime(View view){
        int startHour = startMinutesOfDay / 60;
        System.out.println(startHour);
        int startMinutes= startMinutesOfDay % 60;
        System.out.println(startMinutes);

        //Creates a time picker dialog that will pop up
        TimePickerDialog timepopup = new TimePickerDialog(CreateTourActivity.this,5,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute ) {
                        startMinutesOfDay = (hourOfDay * 60) + minute;
                        System.out.println("Start Time only bby min: " + startMinutesOfDay);
                        //TODO add toast that display "You have picked HH:MM
                    }
                },startHour, startMinutes, true);
        timepopup.show();
    }
    public void selectTimeInterval(View view){
        int timeIntervalHour = intervalMinutes / 60;
        System.out.println(timeIntervalHour);
        int timeIntervalMin = intervalMinutes % 60;
        System.out.println(timeIntervalMin);
        //Creates a time picker dialog that will pop up
        TimePickerDialog minutepopup = new TimePickerDialog(CreateTourActivity.this,1,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourPicked, int minutePicked) {
                        intervalMinutes = hourPicked * 60;
                        intervalMinutes = intervalMinutes + minutePicked;
                        System.out.println("Interval picked: " + intervalMinutes);
                        //TODO add toast that display "Interval picked: MM
                    }
                },timeIntervalHour, timeIntervalMin, true);
        minutepopup.show();
    }
}
