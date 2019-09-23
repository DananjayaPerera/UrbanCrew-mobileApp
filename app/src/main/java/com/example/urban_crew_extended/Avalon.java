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

public class Avalon extends AppCompatActivity {

    ViewFlipper view_flipper;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avalon);

        button = (Button)findViewById(R.id.bookNow);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openBookNowAvalon();
            }
        });


        Toolbar toolbar = findViewById(R.id.toolbar_avalon);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Avalon");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int images [] = {R.drawable.avalon, R.drawable.avalon1, R.drawable.avalon2,
                R.drawable.avalon3, R.drawable.avalon4};

        view_flipper = findViewById(R.id.view_flipper);

        for (int image: images){

            flipperImages(image);
        }
    }

    public void openBookNowAvalon(){

        Intent intent = new Intent(this,BookNowAvalon.class);
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
