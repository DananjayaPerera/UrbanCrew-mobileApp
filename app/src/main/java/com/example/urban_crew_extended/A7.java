package com.example.urban_crew_extended;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class A7 extends AppCompatActivity {

    ViewFlipper view_flipper;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a7);

        button = (Button)findViewById(R.id.bookNow);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openBookNowA7();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar_a7);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("A7");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int images [] = {R.drawable.audi, R.drawable.audi1, R.drawable.audi2,
                R.drawable.audi3, R.drawable.audi4};

        view_flipper = findViewById(R.id.view_flipper);

        for (int image: images){

            flipperImages(image);
        }
    }

    public void openBookNowA7(){

        Intent intent = new Intent(this,BookNowA7.class);
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
