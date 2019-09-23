package com.example.urban_crew_extended;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager.OnActivityResultListener;
import android.provider.MediaStore;
import android.provider.MediaStore.Images.Media;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.google.firebase.storage.UploadTask.TaskSnapshot;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.theartofdev.edmodo.cropper.CropImageView.Guidelines;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile extends AppCompatActivity {

    private CircleImageView profilePic;
    private TextView profile_userName, profile_userEmail, profile_userPhone;
    private FirebaseAuth firebaseAuth;
    Uri imageUri;
    private FirebaseDatabase firebaseDatabase;
    private FirebaseStorage firebaseStorage;
    private String currentUser;
    private StorageReference storageReference;
    private static final int GalleryPick = 1;

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
        currentUser = firebaseAuth.getCurrentUser().getUid();
        firebaseDatabase = FirebaseDatabase.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference().child("Profile Image");



        final DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid()).child("User Info");

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

    public void onChooseFile(View v){

        CropImage.activity().start(Profile.this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {

            CropImage.ActivityResult result = CropImage.getActivityResult(data);

            if (resultCode == RESULT_OK) {

                imageUri = result.getUri();
                profilePic.setImageURI(imageUri);

                StorageReference myRef = storageReference.child(firebaseAuth.getUid()).child("Profile Image");
                myRef.putFile(imageUri);

                Toast.makeText(Profile.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();

            }

            else if(resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){

                Exception e = result.getError();
                Toast.makeText(this, "Error"+e, Toast.LENGTH_SHORT).show();
            }
        }
    }
}
