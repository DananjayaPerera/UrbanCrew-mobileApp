package com.example.urban_crew_extended;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.BreakIterator;
import java.util.ArrayList;

public class AltoK10 extends AppCompatActivity {

    ViewFlipper view_flipper;
    CheckBox food,drink;
    RadioGroup book_Price;
    RadioButton book_Option;
    private ArrayList<String>altoExtraResult;
    Button button;
    String resultExtra, resultBook;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alto_k10);

        Toolbar toolbar = findViewById(R.id.toolbar_altok10);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AltoK10");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        book_Price = findViewById(R.id.bookPrice_Alto);
        food = findViewById(R.id.alto_food_check);
        drink = findViewById(R.id.alto_drink_check);

        altoExtraResult = new ArrayList<>();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        book_Price.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                book_Option = book_Price.findViewById(checkedId);

                switch (checkedId) {

                    case R.id.alto_hour_check:
                        String option_1 = "Hourly Rs 300.00 LKR";
                        resultBook = option_1;
                        break;


                    case R.id.alto_day_check:
                        String option_2 = "Daily Rs 6000.00 LKR";
                        resultBook = option_2;
                        break;

                    default:
                }

            }
        });


        food.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (food.isChecked()){

                    altoExtraResult.add("Foods Rs 2000.00 LKR");
                } else {

                    altoExtraResult.remove("Foods Rs 2000.00 LKR");
                }
            }
        });

        drink.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                if (drink.isChecked()){

                    altoExtraResult.add("Drinks Rs 1000.00 LKR");
                } else {

                    altoExtraResult.remove("Drinks Rs 1000.00 LKR");
                }
            }
        });


        button = (Button)findViewById(R.id.bookNow);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {


                if (resultBook == null){

                    Toast.makeText(AltoK10.this, "Booking Price can't be empty", Toast.LENGTH_LONG).show();
                    return;
                }

                sendUserData();
                openBookNowAltoK10();
            }
        });



        int images [] = {R.drawable.alto, R.drawable.alto1, R.drawable.alto2,
                R.drawable.alto3, R.drawable.alto4};

        view_flipper = findViewById(R.id.view_flipper);

        for (int image: images){

            flipperImages(image);
        }
    }

    public void sendUserData(){


        DatabaseReference reference_1 = firebaseDatabase.getReference(firebaseAuth.getUid()).child("AltoK10")
                    .child("Booking Options").child("Booking Price");
        reference_1.setValue(resultBook);


        StringBuilder stringBuilderExtra = new StringBuilder();
        for (String s: altoExtraResult){

            stringBuilderExtra.append(s + ", ");
            resultExtra = stringBuilderExtra.toString();
            DatabaseReference reference_2 = firebaseDatabase.getReference(firebaseAuth.getUid()).child("AltoK10")
                    .child("Booking Options").child("Rental Extra Options");
            reference_2.setValue(resultExtra);
        }
    }

    public void openBookNowAltoK10(){

        Intent intent = new Intent(this,BookNowAltoK10.class);
        startActivity(intent);
    }

    public void flipperImages(int image){

        ImageView imageView = new ImageView(this);
        imageView.setBackgroundResource(image);

        view_flipper.addView(imageView);
        view_flipper.setFlipInterval(3000);
        view_flipper.setAutoStart(true);

        view_flipper.setInAnimation(this,android.R.anim.fade_in);
        view_flipper.setOutAnimation(this,android.R.anim.fade_out);
    }
}
