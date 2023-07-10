package sg.edu.rp.c346.id21023028.ps_songs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnInsert, btnView;
    EditText etTitle, etSinger, etYear;
    RadioGroup rgStars;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link view and controls
        btnInsert = findViewById(R.id.btnInsert);
        btnView = findViewById(R.id.btnView);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);
        rgStars = findViewById(R.id.rgStars);

        //clear all fields
        reset();

        //when insert button clicked
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateFields()){

                    //get values from fields
                    String title = etTitle.getText().toString();
                    String singer = etSinger.getText().toString();
                    int year = Integer.parseInt(etYear.getText().toString());
                    int stars = checkRadioButtons();


                    //intialize DB connection
                    DBHelper db = new DBHelper(MainActivity.this);

                    //insert value
                    db.insertTask(title,year,singer,stars);
                    reset();
                    Toast.makeText(MainActivity.this,"Insert Complete",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        //when switch view button is clicked
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ViewSongs.class);
                Log.i("hi","hi");
                startActivity(intent);
            }
        });
    }

    private void reset(){
        rgStars.check(R.id.radioButton1);
        etTitle.setText("");
        etSinger.setText("");
        etYear.setText("");
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
            Toast.makeText(MainActivity.this,"Insert failed, check fields.",
                    Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private int checkRadioButtons(){
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