package aber.group9.pubapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EmptyTourActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty_tour);
    }

    public void goHome(View view){
        Intent homeIntent = new Intent(this, HomeActivity.class);
        homeIntent.putExtra("pubs", getIntent().getParcelableArrayListExtra("pubs"));
        startActivity(homeIntent);
    }

    public void back(View view){
        finish();
    }

    public void viewPubs(View view){
        Intent viewPubsIntent = new Intent(EmptyTourActivity.this, PubListActivity.class);
        viewPubsIntent.putExtra("pubs", getIntent().getParcelableArrayListExtra("pubs"));
        startActivity(viewPubsIntent);
    }

    public void viewEnd(View view){
      Intent viewEndIntent = new Intent(EmptyTourActivity.this, CongratulationActivity.class);
      viewEndIntent.putExtra("pubs", getIntent().getParcelableArrayListExtra("pubs"));
      startActivity(viewEndIntent);
    }

    public void viewMap(View view){
        Intent viewMapIntent = new Intent(EmptyTourActivity.this,MapActivity.class);
        viewMapIntent.putExtra("pubs", getIntent().getParcelableArrayListExtra("pubs"));
        startActivity(viewMapIntent);
    }
}
