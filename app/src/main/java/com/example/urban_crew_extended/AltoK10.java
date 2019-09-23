package com.example.urban_crew_extended;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class AltoK10 extends AppCompatActivity {

    ViewFlipper view_flipper;

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alto_k10);

        button = (Button)findViewById(R.id.bookNow);
        button.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openBookNowAltoK10();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar_altok10);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("AltoK10");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int images [] = {R.drawable.alto, R.drawable.alto1, R.drawable.alto2,
                R.drawable.alto3, R.drawable.alto4};

        view_flipper = findViewById(R.id.view_flipper);

        for (int image: images){

            flipperImages(image);
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
