package com.example.urban_crew_extended;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{6,}" +               //at least 6 characters
                    "$");

    private TextInputLayout userName, userEmail, userPassword, userConfirmPassword, userPhone;
    private TextView textView_1;
    Button sign_up_1;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;
    String user_name, user_email, user_password, user_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        userName = findViewById(R.id.user_name_signup_1);
        userEmail = findViewById(R.id.email_signup_1);
        userPassword = findViewById(R.id.password_signup_1);
        userConfirmPassword = findViewById(R.id.confirm_password_signup_1);
        userPhone = findViewById(R.id.phone_signup_1);
        textView_1 = findViewById(R.id.have_account);

        textView_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(SignUp.this, SignIn.class);
                startActivity(intent);
            }
        });


        sign_up_1 = (Button) findViewById(R.id.sign_up_1);

        sign_up_1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                ConfirmInput();

            }
        });
    }


    private boolean ValidateUserName() {

        String username = userName.getEditText().getText().toString().trim();

        if (username.isEmpty()) {

            userName.setError("Field can't be empty");
            return false;

        } else if (username.length() > 15) {

            userName.setError("Username too long");
            return false;

        } else {

            userName.setError(null);
            return true;
        }
    }


    private boolean ValidateEmail() {

        String email = userEmail.getEditText().getText().toString().trim();

        if (email.isEmpty()) {

            userEmail.setError("Field can't be empty");
            return false;

        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {

            userEmail.setError("Please Enter a valid email address");
            return false;

        } else {

            userEmail.setError(null);
            return true;
        }
    }


    private boolean ValidatePassword() {

        String password = userPassword.getEditText().getText().toString().trim();

        if (password.isEmpty()) {

            userPassword.setError("Field can't be empty");
            return false;

        } else if (!PASSWORD_PATTERN.matcher(password).matches()) {

            userPassword.setError("Password too weak");
            return false;

        } else {

            userPassword.setError(null);
            return true;
        }
    }


    private boolean ValidateConfirmPassword() {

        String confirm_password = userConfirmPassword.getEditText().getText().toString().trim();

        if (confirm_password.isEmpty()) {

            userConfirmPassword.setError("Field can't be empty");
            return false;

        } else if (!userConfirmPassword.getEditText().getText().toString().equals(userPassword
                .getEditText().getText().toString())) {

            userConfirmPassword.setError("Passwords do not match");
            return false;

        } else {

            userConfirmPassword.setError(null);
            return true;
        }
    }


    private boolean ValidatePhone() {

        String phone = userPhone.getEditText().getText().toString().trim();

        if (phone.isEmpty()) {

            userPhone.setError("Field can't be empty");
            return false;

        } else if (!Patterns.PHONE.matcher(phone).matches()) {

            userPhone.setError("Please Enter a valid phone number");
            return false;

        } else {

            userPhone.setError(null);
            return true;
        }
    }


    private void ConfirmInput(){

        if (!ValidateUserName() | !ValidateEmail() | !ValidatePassword() | !ValidateConfirmPassword()
                | !ValidatePhone()) {
            return;
        }

        user_name = userName.getEditText().getText().toString().trim();
        user_email = userEmail.getEditText().getText().toString().trim();
        user_password = userPassword.getEditText().getText().toString().trim();
        user_phone = userPhone.getEditText().getText().toString().trim();


        firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener
                (new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

                    sendUserData();
                    firebaseAuth.signOut();
                    Toast.makeText(SignUp.this,"Sign Up Successfull",Toast.LENGTH_LONG).show();
                    finish();
                    startActivity(new Intent(SignUp.this,SignIn.class));

                } else {

                    Toast.makeText(SignUp.this,"Sign Up Failed",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void sendUserData(){

        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid()).child("User Info");
        UserProfile userProfile = new UserProfile(user_name, user_email, user_phone);
        myRef.setValue(userProfile);
    }
}
