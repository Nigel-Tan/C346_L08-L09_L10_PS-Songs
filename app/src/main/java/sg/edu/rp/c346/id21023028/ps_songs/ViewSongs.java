package sg.edu.rp.c346.id21023028.ps_songs;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ViewSongs extends AppCompatActivity {

    ListView lv;
    ArrayList<String> dataString; //outdated, L08 code
    ArrayAdapter<Song> adapter;
    Button btnReturn, btn5Stars;
    String order = " ASC";
    ArrayList<Song> al;

    @Override
    protected void onResume() {
        super.onResume();
        dataPopulate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);


        //link view to variables
        btnReturn = findViewById(R.id.btnReturn);
        btn5Stars = findViewById(R.id.btn5Stars);
        //dataString = new ArrayList<>(); //outdated L08 code
        al = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,al); //tbc
        lv = findViewById(R.id.lv);
        lv.setAdapter(adapter);
        dataPopulate();

        //return back button
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //5 stars only button
        btn5Stars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataPopulate5Stars(); //show 5 star songs only
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int
                    position, long identity) {
                Song data = al.get(position);
                Intent i = new Intent(ViewSongs.this,
                        EditSong.class);
                i.putExtra("song", data);
                startActivity(i);
            }
        });

    }

    //method to populate data for listview
    private void dataPopulate(){

        //clear data
        al.clear();

        // Create the DBHelper object, passing in the
        // activity's Context (get instance of DB)
        DBHelper db = new DBHelper(ViewSongs.this);

        //populate arraylist of Song objects
        ArrayList<Song> objectList = db.getTasks(order);

        //access the arraylist of song objects(l08, outdated)
//        for (Song song : objectList){
//            dataString.add(song.toString());
//        }
        al.addAll(objectList);
        adapter.notifyDataSetChanged();
    }

    private void dataPopulate5Stars(){

        //clear the array first
        al.clear();

        // Create the DBHelper object, passing in the
        // activity's Context (get instance of DB)
        DBHelper db = new DBHelper(ViewSongs.this);

        //populate arraylist of Song objects
        ArrayList<Song> objectList = db.getTasksByStars(order,5);

        //access the arraylist of song objects (l08, outdated)
//        for (Song song : objectList){
//            dataString.add(song.toString());
//        }
        al.addAll(objectList);
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
