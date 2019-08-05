package com.example.urban_crew_extended;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Button start;
    Animation frombottom , fromtop;
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);
        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openNavigationDrawer();
            }
        });

        logo = (ImageView)findViewById(R.id.logo);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.from_bottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.from_top);

        start.setAnimation(frombottom);
        logo.setAnimation(fromtop);
    }

    public void openNavigationDrawer() {

        Intent intent = new Intent(this, NavigationDrawer.class);
        startActivity(intent);
    }
}
