package sg.edu.rp.c346.id21023028.ps_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditSong extends AppCompatActivity {

    Song data;
    Button btnUpdate, btnDelete, btnBack;
    EditText etTitle, etSinger, etYear;
    RadioGroup rgStars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_song);

        //link variable to UI
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnBack = findViewById(R.id.btnBack);
        etTitle = findViewById(R.id.etTitle1);
        etSinger = findViewById(R.id.etSinger1);
        etYear = findViewById(R.id.etYear1);
        rgStars = findViewById(R.id.rgStars1);


        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("song");

        //get radiobutton value and set it
        int star = data.getStars();
        checkRadioButtons(star);

        //set the fields
        etTitle.setText(data.getTitle());
        etSinger.setText(data.getSingers());
        etYear.setText(String.valueOf(data.getYear()));

        //btn delete
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditSong.this);
                dbh.deleteSong(data.get_id());
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()){

                    //get value
                    String title = etTitle.getText().toString();
                    String singer = etSinger.getText().toString();
                    int year = Integer.parseInt(etYear.getText().toString());
                    int stars = checkRadioButtonsValue();

                    //set value
                    data.setStars(stars);
                    data.setYear(year);
                    data.setSingers(singer);
                    data.setTitle(title);

                    DBHelper dbh = new DBHelper(EditSong.this);
                    dbh.updateSong(data);
                    dbh.close();
                    finish();
                }

            }
        });


    }

    private void checkRadioButtons(int star){
        if (star == 1) {
            // Action for radioButton1 selected
            rgStars.check(R.id.radioButton1);
        } else if (star == 2) {
            // Action for radioButton2 selected
            rgStars.check(R.id.radioButton2);
        } else if (star == 3) {
            // Action for radioButton3 selected
            rgStars.check(R.id.radioButton3);
        } else if (star == 4) {
            // Action for radioButton4 selected
            rgStars.check(R.id.radioButton4);
        } else if (star == 5) {
            // Action for radioButton5 selected
            rgStars.check(R.id.radioButton5);
        } else {
            // Action for no option selected or unknown option
        }
    }
    private boolean validateFields(){
        boolean title = !etTitle.getText().toString().trim().isEmpty();
        boolean singer = !etSinger.getText().toString().trim().isEmpty();
        boolean year = !etYear.getText().toString().isEmpty();
        boolean yearValue = false;

        if (year){ //verify if the year is 4 digits (valid year)
            yearValue = etYear.getText().toString().length() ==4;
        }

        if (title && singer && year && yearValue){
            return true;
        }
        else{
            Toast.makeText(EditSong.this,"Update failed, check fields.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private int checkRadioButtonsValue(){
        int checkedRB = rgStars.getCheckedRadioButtonId();
        int value = 1;

        if (checkedRB == R.id.radioButton1) {
            // Action for radioButton1 selected
            value = 1;
        } else if (checkedRB == R.id.radioButton2) {
            // Action for radioButton2 selected
            value = 2;
        } else if (checkedRB == R.id.radioButton3) {
            // Action for radioButton3 selected
            value = 3;
        } else if (checkedRB == R.id.radioButton4) {
            // Action for radioButton4 selected
            value = 4;
        } else if (checkedRB == R.id.radioButton5) {
            // Action for radioButton5 selected
            value = 5;
        } else {
            // Action for no option selected or unknown option
            value = 1;
        }
        return value;
    }
}