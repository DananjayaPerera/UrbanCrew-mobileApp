package com.example.urban_crew_extended;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;
    private TextView HeaderUserName, HeaderEmail;
    View HView;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase firebaseDatabase;

    private int[] mImages = new int[]{

            R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5,
            R.drawable.image6, R.drawable.image7, R.drawable.image8, R.drawable.image9, R.drawable.image10,
            R.drawable.image11, R.drawable.image12
    };

    private String[] mImagesTitles = new String[]{

            "Celerio X", "Swift", "Wagon R", "Alto K10", "Toyota Avalon", "Mazda 6", "Audi A7",
            " BMW M760Li", "Honda CR-V", "Nissan X-Trail", "Toyota Land Cruiser V8", "Range Rover Evoque"
    };

    ViewPager viewPager;
    Adapter adapter;
    List<Model> models;


    DrawerLayout drawerLayout;
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);


        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        DatabaseReference databaseReference = firebaseDatabase.getReference(firebaseAuth.getUid()).child("User Info");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String UserName = dataSnapshot.child("UserName").getValue().toString();
                String UserEmail = dataSnapshot.child("UserEmail").getValue().toString();
                HeaderUserName.setText(UserName);
                HeaderEmail.setText(UserEmail);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(NavigationDrawer.this, databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

        CarouselView carouselView = findViewById(R.id.carousel);
        carouselView.setPageCount(mImages.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {

                imageView.setImageResource(mImages[position]);

            }
        });

        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(NavigationDrawer.this,mImagesTitles[position],Toast.LENGTH_SHORT).show();
            }
        });

        models = new ArrayList<>();
        models.add(new Model(R.drawable.cardimage3,"Maruti Suzuki CelerioX",""));
        models.add(new Model(R.drawable.cardimage6,"Toyota Avalon",""));
        models.add(new Model(R.drawable.cardimage7,"Audi A7",""));
        models.add(new Model(R.drawable.cardimage12,"Range Rover Evoque",""));


        adapter = new Adapter(models,this);
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        viewPager.setPadding(130,0,130,0);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        HView = navigationView.getHeaderView(0);

        HeaderUserName = HView.findViewById(R.id.NavHeader_UserName);
        HeaderEmail = HView.findViewById(R.id.NavHeader_Email);

        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,
                R.string.open_drawer,R.string.close_drawer);

        drawerLayout.setDrawerListener(drawerToggle);
        drawerToggle.syncState();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){

            case R.id.settings_id:
                Toast.makeText(this,"Settings Clicked",Toast.LENGTH_SHORT).show();
                break;

            case R.id.delete_id:
                Toast.makeText(this,"Delete Clicked",Toast.LENGTH_SHORT).show();
                break;

            case android.R.id.home:
                finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        if (id == R.id.view_cars_id){

            startActivity(new Intent(NavigationDrawer.this,ViewCars.class));
        }

        else if (id == R.id.search_cars_id){

            startActivity(new Intent(NavigationDrawer.this, Search.class));
        }

        else if (id == R.id.profile_id){

            startActivity(new Intent(NavigationDrawer.this,Profile.class));

        }

        else if (id == R.id.Logout_id){

            firebaseAuth.signOut();
            startActivity(new Intent(NavigationDrawer.this, SignIn_SignUp.class));
            Toast.makeText(NavigationDrawer.this, "Logout Successful", Toast.LENGTH_SHORT).show();
            finish();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }else {

            super.onBackPressed();
        }

    }
}
