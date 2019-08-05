package com.example.urban_crew_extended;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SignIn extends AppCompatActivity {

    Button register;
    Button log_in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        register = (Button)findViewById(R.id.register);
        log_in = (Button)findViewById(R.id.log_in);

        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignUp();
            }
        });

        log_in.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openNavigationDrawer();
            }
        });
    }

    public void openSignUp() {

        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }

    public void openNavigationDrawer() {

        Intent intent = new Intent(this,NavigationDrawer.class);
        startActivity(intent);
    }
}
