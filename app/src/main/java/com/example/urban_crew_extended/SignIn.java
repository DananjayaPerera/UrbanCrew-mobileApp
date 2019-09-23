package com.example.urban_crew_extended;

import android.app.ProgressDialog;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignIn extends AppCompatActivity {

    private TextInputLayout userEmail, userPassword;
    Button register;
    Button log_in;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView passwordReset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        userEmail = findViewById(R.id.email_login_1);
        userPassword = findViewById(R.id.password_login_1);
        passwordReset = findViewById(R.id.sign_in_password_reset);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null){

            finish();
            startActivity(new Intent(SignIn.this,NavigationDrawer.class));
        }

        register = (Button)findViewById(R.id.register_1);
        log_in = (Button)findViewById(R.id.login_1);

        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignIn.this, SignUp.class));
            }
        });

        log_in.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Validate(userEmail.getEditText().getText().toString(), userPassword.getEditText()
                        .getText().toString());
            }
        });

        passwordReset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SignIn.this, PasswordReset.class));
            }
        });

    }


    private void Validate(String email, String password){

        progressDialog.setTitle("Verifying");
        progressDialog.setMessage("Please Wait....");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener
                (new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    progressDialog.dismiss();
                    Toast.makeText(SignIn.this, "SignIn successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignIn.this,NavigationDrawer.class));

                } else {

                    progressDialog.dismiss();
                    Toast.makeText(SignIn.this,"SignIn Failed",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
