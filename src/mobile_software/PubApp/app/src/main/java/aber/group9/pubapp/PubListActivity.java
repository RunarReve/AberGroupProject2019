package aber.group9.pubapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import aber.group9.pubapp.object.Pub;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


//I used this website http://androidcocktail.blogspot.com/p/adding-checkboxes-to-custom-listview-in.html
// and http://android-er.blogspot.com/2017/03/android-listview-with-checkbox.html
//to help with chaching and interacting with checkboxes as the code here is beyond my ability in android studio

public class PubListActivity extends AppCompatActivity {

    static class ViewHolder{
        ImageView pubPhoto;
        TextView pubTitle, pubDesc;
        CheckBox checkBox;
        Button button;
    }



    Button btnCrawl;
    ArrayList<Pub> pubs;
    ListView listView;
    PubsListAdapater myPubListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pub_list);
        listView = (ListView)findViewById(R.id.listview);
        btnCrawl = (Button)findViewById(R.id.btn_create_crawl);
        //fill the arrayList of pubs
        pubs = getIntent().getParcelableArrayListExtra("pubs");

        myPubListAdapter = new PubsListAdapater(this, pubs);
        listView.setAdapter(myPubListAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PubListActivity.this,
                        ((Pub)(parent.getItemAtPosition(position))).getPubName(),
                Toast.LENGTH_LONG).show();
            }
        });

        btnCrawl.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                createCrawl(view);
            }
        });

    }

        private class PubsListAdapater extends BaseAdapter
        {
            private Context context;
            private List<Pub> list;

            PubsListAdapater(Context c, List<Pub> l){
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
                    convertView = inflater.inflate(R.layout.pub_list_layout,null);

                   // viewHolder.pubPhoto = (ImageView) convertView.findViewById(R.id.imageView_pubImage);
                    viewHolder.pubTitle = (TextView) convertView.findViewById(R.id.textView_LocationName);
                    viewHolder.pubDesc = (TextView) convertView.findViewById(R.id.textView_pubDescription);
                    viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkBox_isCrawl);
                    viewHolder.button = (Button) convertView.findViewById(R.id.btn_pub_info);

                    convertView.setTag(viewHolder);
                }
                else
                    viewHolder =(ViewHolder)convertView.getTag();

                //viewHolder.pubPhoto.setImageURI(list.get(position).getImgLink());
                viewHolder.checkBox.setChecked(list.get(position).getChecked());

                final String pubNm = list.get(position).getPubName();
                viewHolder.pubTitle.setText(pubNm);
                final String pubDs = list.get(position).getAddress();
                viewHolder.pubDesc.setText(pubDs);


                viewHolder.checkBox.setTag(position);

                viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean newState = !list.get(position).getChecked();
                        list.get(position).setChecked(newState);
                    }
                });

                viewHolder.button.setTag(position);
                viewHolder.button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        showPubInfo(v,position);
                    }
                });

                viewHolder.checkBox.setChecked(isChecked(position));

                return convertView;
            }
        }

    public void showPubInfo(View view, int position){
        Intent showPubIntent = new Intent(this,PubInfoActivity.class);
        showPubIntent.putExtra("selected_pub",pubs.get(position));
        showPubIntent.putExtra("pubs", pubs);
        showPubIntent.putExtra("all_pubs",
                getIntent().getParcelableArrayListExtra("all_pubs"));
        showPubIntent.putExtra("town_name", getIntent().getStringExtra("town_name"));
        startActivity(showPubIntent);
    }

    public void createCrawl(View view){
        Intent createCrawlIntent = new Intent(this, CreateTourActivity.class);
        ArrayList<Pub> tempCrawl = new ArrayList<>();

        for(int i=0;i<pubs.size();i++){
            if(pubs.get(i).getChecked()){
                tempCrawl.add(pubs.get(i));
            }
        }
        createCrawlIntent.putExtra("pubs", pubs);
        createCrawlIntent.putExtra("all_pubs",
                getIntent().getParcelableArrayListExtra("all_pubs"));
        createCrawlIntent.putExtra("town_name",
                getIntent().getStringExtra("town_name"));
        createCrawlIntent.putExtra("pub_crawl", tempCrawl);
        createCrawlIntent.putExtra("is_crawl",
                getIntent().getIntExtra("is_crawl", 0));
        createCrawlIntent.putExtra("time_mins",
                getIntent().getIntExtra("time_mins", 0));
        createCrawlIntent.putExtra("interval",
                getIntent().getIntExtra("interval", 0));

        startActivity(createCrawlIntent);
    }

    public void randomizeTour(View view){
        Intent createCrawlIntent = new Intent(this, CreateTourActivity.class);
        ArrayList<Pub> tempCrawl = new ArrayList<>();

        for(int i=0;i<pubs.size();i++){
            if(pubs.get(i).getChecked()){
                tempCrawl.add(pubs.get(i));
            }
        }
        Collections.shuffle(tempCrawl); //Randomize order of the pubs in the crawl

        createCrawlIntent.putExtra("pubs", pubs);
        createCrawlIntent.putExtra("all_pubs",
                getIntent().getParcelableArrayListExtra("all_pubs"));
        createCrawlIntent.putExtra("town_name",
                getIntent().getStringExtra("town_name"));
        createCrawlIntent.putExtra("pub_crawl", tempCrawl);
        createCrawlIntent.putExtra("is_crawl",
                getIntent().getIntExtra("is_crawl", 0));
        createCrawlIntent.putExtra("time_mins",
                getIntent().getIntExtra("time_mins", 0));
        createCrawlIntent.putExtra("interval",
                getIntent().getIntExtra("interval", 0));

        startActivity(createCrawlIntent);
    }

    public void goHome(View view){
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("pubs", pubs);
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
