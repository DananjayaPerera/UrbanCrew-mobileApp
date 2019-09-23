package com.example.urban_crew_extended;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    private CircleImageView profilePic;
    private TextView profile_userName, profile_userEmail, profile_userPhone;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar toolbar = findViewById(R.id.toolbar_profile);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        profilePic = findViewById(R.id.profile_pic);
        profile_userName =  findViewById(R.id.profile_username_1);
        profile_userEmail = findViewById(R.id.profile_email_1);
        profile_userPhone = findViewById(R.id.profile_phone_1);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid());

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

               String UserName = dataSnapshot.child("UserName").getValue().toString();
               String UserEmail = dataSnapshot.child("UserEmail").getValue().toString();
               String UserPhone = dataSnapshot.child("UserPhone").getValue().toString();
               profile_userName.setText(UserName);
               profile_userEmail.setText(UserEmail);
               profile_userPhone.setText(UserPhone);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Profile.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}