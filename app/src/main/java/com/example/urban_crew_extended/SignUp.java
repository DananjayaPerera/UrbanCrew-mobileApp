package com.example.urban_crew_extended;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SignUp extends AppCompatActivity {

    Button sign_up_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sign_up_1 = (Button)findViewById(R.id.sign_up_1);

        sign_up_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openNavigationDrawer();
            }
        });
    }

    public void openNavigationDrawer() {

        Intent intent = new Intent(this,NavigationDrawer.class);
        startActivity(intent);
    }}
