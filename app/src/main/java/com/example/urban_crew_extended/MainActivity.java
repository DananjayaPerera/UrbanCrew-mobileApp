package com.example.urban_crew_extended;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    Button start;
    Animation frombottom , fromtop;
    ImageView logo;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        start = (Button) findViewById(R.id.start);

        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){

            startActivity(new Intent(MainActivity.this,NavigationDrawer.class));
            Toast.makeText(MainActivity.this, "Welcome Back To Urban Crew", Toast.LENGTH_LONG).show();
            finish();
        }

        start.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, SignIn_SignUp.class);
                startActivity(intent);
            }
        });

        logo = (ImageView)findViewById(R.id.logo);

        frombottom = AnimationUtils.loadAnimation(this,R.anim.from_bottom);
        fromtop = AnimationUtils.loadAnimation(this,R.anim.from_top);

        start.setAnimation(frombottom);
        logo.setAnimation(fromtop);
    }

}
