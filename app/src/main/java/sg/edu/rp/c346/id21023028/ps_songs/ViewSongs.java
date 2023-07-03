package sg.edu.rp.c346.id21023028.ps_songs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ViewSongs extends AppCompatActivity {

    ListView lv;
    ArrayList<String> dataString;
    ArrayAdapter adapter;
    Button btnReturn;
    String order = " ASC";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);


        //link view to variables
        btnReturn = findViewById(R.id.btnReturn);
        dataString = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,dataString);
        lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);
        dataPopulate();

        //return back button
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewSongs.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

    //method to populate data for listview
    private void dataPopulate(){

        // Create the DBHelper object, passing in the
        // activity's Context (get instance of DB)
        DBHelper db = new DBHelper(ViewSongs.this);

        //populate arraylist of Song objects
        ArrayList<Song> objectList = db.getTasks(order);

        //access the arraylist of song objects
        for (Song song : objectList){
            dataString.add(song.toString());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here.
        int id = item.getItemId();
        if (id == R.id.Ascending) {
            order = " ASC";
            return true;
        } else if (id == R.id.Descending) {
            order = " DESC";
            return true;
        } else {
            order = " ASC";
        }
        return super.onOptionsItemSelected(item);
    }
}
