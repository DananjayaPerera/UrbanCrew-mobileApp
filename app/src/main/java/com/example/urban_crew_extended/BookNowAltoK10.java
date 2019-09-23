package com.example.urban_crew_extended;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class BookNowAltoK10 extends AppCompatActivity{

    Button button_1;
    EditText userLocation_1,userpickupLocation,editText_date, editText_time;
    TextView user,email,phone;
    int year, month, day;
    String DMY,YourLocation,PickUpLocation,Date,Time;
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    Calendar calendar_1;
    int currentHour;
    int currentMinute;
    String amPm;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_now_alto_k10);


        Toolbar toolbar = findViewById(R.id.toolbar_bookNowAlto);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AltoK10 2019");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button_1 = (Button)findViewById(R.id.selectPayment);
        button_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                YourLocation = userLocation_1.getText().toString();
                PickUpLocation = userpickupLocation.getText().toString();
                Date = editText_date.getText().toString();
                Time = editText_time.getText().toString();

                if (YourLocation.isEmpty() | PickUpLocation.isEmpty() | Date.isEmpty() | Time.isEmpty()){

                    Toast.makeText(BookNowAltoK10.this, "Field's can't be empty", Toast.LENGTH_LONG).show();
                    return;
                }
                YourLocation = userLocation_1.getText().toString();
                PickUpLocation = userpickupLocation.getText().toString();
                Date = editText_date.getText().toString();
                Time = editText_time.getText().toString();

                if (YourLocation.isEmpty() | PickUpLocation.isEmpty() | Date.isEmpty() | Time.isEmpty()){

                    Toast.makeText(BookNowAltoK10.this, "Field's can't be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                SendInput();
                openSuccess();
            }
        });

                SendInput();
                Intent intent = new Intent(BookNowAltoK10.this, PaymentSelection.class);
                startActivity(intent);
            }
        });


        editText_date = findViewById(R.id.datePicker_alto);
        editText_date = findViewById(R.id.datePicker_alto);
        final Calendar calendar = Calendar.getInstance();
        editText_date.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(BookNowAltoK10.this, new OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        DMY = dayOfMonth+"/"+(month+1)+"/"+year;
                        editText_date.setText(DMY);

                    }
                },year, month, day);

                datePickerDialog.show();

                    }
                });

        editText_time = findViewById(R.id.timePicker_alto);
        editText_time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar_1 = Calendar.getInstance();
                currentHour = calendar_1.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar_1.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(BookNowAltoK10.this, new OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if (hourOfDay >= 12){

                            amPm = "PM";
                        } else {

                            amPm = "AM";
                        }

                        editText_time.setText(String.format("%02d:%02d", hourOfDay, minute) + amPm);

                    }
                },currentHour,currentMinute,false);

                timePickerDialog.show();
            }
        });

        userLocation_1 = findViewById(R.id.alto_your_location);
        userpickupLocation = findViewById(R.id.alto_pickup_location);
        user = findViewById(R.id.username_bookAlto);
        email = findViewById(R.id.email_bookAlto);
        phone = findViewById(R.id.phone_bookAlto);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid()).child("User Info");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String UserName = dataSnapshot.child("UserName").getValue().toString();
                String UserEmail = dataSnapshot.child("UserEmail").getValue().toString();
                String UserPhone = dataSnapshot.child(("UserPhone")).getValue().toString();
                user.setText(UserName);
                email.setText(UserEmail);
                phone.setText(UserPhone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(BookNowAltoK10.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
    }





        editText_time = findViewById(R.id.timePicker_alto);
        editText_time.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                calendar_1 = Calendar.getInstance();
                currentHour = calendar_1.get(Calendar.HOUR_OF_DAY);
                currentMinute = calendar_1.get(Calendar.MINUTE);

                timePickerDialog = new TimePickerDialog(BookNowAltoK10.this, new OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                        if (hourOfDay >= 12){

                            amPm = "PM";
                        } else {

                            amPm = "AM";
                        }

                        editText_time.setText(String.format("%02d:%02d", hourOfDay, minute) + amPm);
    public void openSuccess(){

        Intent intent = new Intent(this,Success.class);
        startActivity(intent);
    }


                    }
                },currentHour,currentMinute,false);
    public void SendInput(){

                timePickerDialog.show();
            }
        });

        userLocation_1 = findViewById(R.id.alto_your_location);
        userpickupLocation = findViewById(R.id.alto_pickup_location);
        user = findViewById(R.id.username_bookAlto);
        email = findViewById(R.id.email_bookAlto);
        phone = findViewById(R.id.phone_bookAlto);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        YourLocation = userLocation_1.getText().toString();
        PickUpLocation = userpickupLocation.getText().toString();
        Date = editText_date.getText().toString();
        Time = editText_time.getText().toString();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid()).child("User Info");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String UserName = dataSnapshot.child("UserName").getValue().toString();
                String UserEmail = dataSnapshot.child("UserEmail").getValue().toString();
                String UserPhone = dataSnapshot.child(("UserPhone")).getValue().toString();
                user.setText(UserName);
                email.setText(UserEmail);
                phone.setText(UserPhone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(BookNowAltoK10.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void SendInput(){

        YourLocation = userLocation_1.getText().toString();
        PickUpLocation = userpickupLocation.getText().toString();
        Date = editText_date.getText().toString();
        Time = editText_time.getText().toString();

        DatabaseReference myref = firebaseDatabase.getReference(firebaseAuth.getUid()).child("AltoK10")
                .child("Booking Information");
        BookingProfile bookingProfile = new BookingProfile(YourLocation,PickUpLocation,Date,Time);
        myref.setValue(bookingProfile);
        DatabaseReference myref = firebaseDatabase.getReference(firebaseAuth.getUid()).child("AltoK10")
                .child("Booking Information");
        BookingProfile bookingProfile = new BookingProfile(YourLocation,PickUpLocation,Date,Time);
        myref.setValue(bookingProfile);
    }

    //gone
}
