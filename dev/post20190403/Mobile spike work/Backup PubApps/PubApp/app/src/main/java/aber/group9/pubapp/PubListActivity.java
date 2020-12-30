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
import aber.group9.pubapp.object.PubCrawl;

import java.util.ArrayList;
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
//                String str = "Check items:\n";
//
//                for(int i=0;i<pubs.size();i++){
//                    if (pubs.get(i).getChecked()){
//                        str += i + "\n";
//                    }
//                }
//                Toast.makeText(PubListActivity.this, str, Toast.LENGTH_LONG).show();
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
                        Toast.makeText(getApplicationContext(), pubNm +
                                " setOnClickListener\nChecked: " + newState,
                                Toast.LENGTH_LONG).show();

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
        createCrawlIntent.putExtra("pub_crawl", tempCrawl);
        createCrawlIntent.putExtra("pubs", pubs);
        startActivity(createCrawlIntent);
    }

    public void goHome(View view){
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("pubs", pubs);
        startActivity(homeIntent);
    }

    public void back(View view){
        finish();
    }
}
