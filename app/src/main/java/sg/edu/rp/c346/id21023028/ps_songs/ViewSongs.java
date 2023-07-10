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
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;


public class ViewSongs extends AppCompatActivity {

    ListView lv;
    ArrayList<String> dataString; //outdated, L08 code
    ArrayAdapter<Song> adapter;
    ArrayAdapter<String> yearAdapter;
    Button btnReturn, btn5Stars;
    String order = " ASC";
    ArrayList<Song> al;
    ArrayList<Integer> yearAl;
    ArrayList<String> finalSpinnerList;
    Spinner spinnerActivity;
    boolean filterOption = false; //this is used so when 5 star button clicked, it will reset droplist while changing to only 5 star songs

    @Override
    protected void onResume() {
        super.onResume();
        spinnerActivity.setSelection(0);
        dataPopulate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_activity);

        //population for spinner
        populateSpinner();

        //link view to variables
        spinnerActivity = findViewById(R.id.spinnerActivity);
        btnReturn = findViewById(R.id.btnReturn);
        btn5Stars = findViewById(R.id.btn5Stars);
        lv = findViewById(R.id.lv);
        //dataString = new ArrayList<>(); //outdated L08 code

        //create adapter and needed arraylist
        al = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,al);

        //set adapter
        spinnerActivity.setAdapter(yearAdapter);
        lv.setAdapter(adapter);

        dataPopulate();

        //manage spinner value
        spinnerActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DBHelper dbh = new DBHelper(ViewSongs.this);
                al.clear();
                if (position==0){ //default value selected
                    if (filterOption){
                        dataPopulate5Stars();
                        filterOption = false;
                    }
                    else{
                        al.addAll(dbh.getTasks(order));
                    }

                }
                else{
                    String selectedValue = parent.getItemAtPosition(position).toString();
                    int intValue = Integer.parseInt(selectedValue);
                    al.addAll(dbh.getTasksByYear(order, intValue));
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


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
                spinnerActivity.setSelection(0);
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
        db.close();
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
        db.close();
        filterOption = true;
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

    private void populateSpinner(){
        DBHelper db = new DBHelper(ViewSongs.this);
        yearAl = db.populateSpinner();
        finalSpinnerList = new ArrayList<>();
        finalSpinnerList.add("Select year value to filter");
        for (int year : yearAl) {
            finalSpinnerList.add(String.valueOf(year));
        }

        yearAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, finalSpinnerList);
        yearAdapter.notifyDataSetChanged();
        db.close();
    }
}
