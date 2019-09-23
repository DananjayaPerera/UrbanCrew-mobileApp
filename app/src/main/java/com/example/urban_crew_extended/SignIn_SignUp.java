package com.example.urban_crew_extended;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SignIn_SignUp extends AppCompatActivity {

    Button sign_in;
    Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in__sign_up);

        sign_in = (Button)findViewById(R.id.sign_in);
        sign_up = (Button)findViewById(R.id.sign_up_1);

        sign_in.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignIn();

            }
        });

        sign_up.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                openSignUp();

            }
        });
    }

    public void openSignIn() {

        Intent intent = new Intent(this,SignIn.class);
        startActivity(intent);
    }

    public void openSignUp() {

        Intent intent = new Intent(this,SignUp.class);
        startActivity(intent);
    }
}
