package com.example.urban_crew_extended;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Calendar;

public class BookNowEvoque extends AppCompatActivity implements OnItemSelectedListener {

    Button button_12;
    Spinner spinner;
    ArrayList<CustomSpinnerItems> customlist;
    EditText editText;
    int year, month, day;
    String DMY;
    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now_evoque);

        Toolbar toolbar = findViewById(R.id.toolbar_bookNowEvoque);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Evoque 2019");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button_12 = (Button)findViewById(R.id.selectPayment);
        button_12.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openSuccess();
            }
        });

        spinner = findViewById(R.id.spinner_alto);
        customlist = getCustomList();
        CustomAdapter adapter = new CustomAdapter(this,customlist);
        if (spinner != null) {
            spinner.setAdapter(adapter);
            spinner.setOnItemSelectedListener(this);
        }


        editText = findViewById(R.id.datePicker_alto);
        final Calendar calendar = Calendar.getInstance();
        editText.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookNowEvoque.this, new OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        DMY = dayOfMonth+"/"+(month+1)+"/"+year;
                        editText.setText(DMY);
                    }
                },year, month, day);

                datePickerDialog.show();

            }
        });
    }

    private ArrayList<CustomSpinnerItems> getCustomList() {

        customlist = new ArrayList<>();
        customlist.add(new CustomSpinnerItems("Hourly Booking"));
        customlist.add(new CustomSpinnerItems("Daily Booking"));
        return customlist;
    }



    public void openSuccess(){

        Intent intent = new Intent(this,Success.class);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
