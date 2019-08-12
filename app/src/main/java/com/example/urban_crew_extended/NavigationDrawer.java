package com.example.urban_crew_extended;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar toolbar;

    private int[] mImages = new int[]{

            R.drawable.image1, R.drawable.image2, R.drawable.image3, R.drawable.image4, R.drawable.image5
    };

    private String[] mImagesTitles = new String[]{

            "BMW", "Lamborgini", "Viper", "Collection", "Audi"
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
        models.add(new Model(R.drawable.cardimage1,"BMW i8",""));
        models.add(new Model(R.drawable.cardimage2,"Audi E-Tron",""));
        models.add(new Model(R.drawable.cardimage3,"Ford Shelby Mustang",""));
        models.add(new Model(R.drawable.cardimage4,"Fisker Karma",""));
        models.add(new Model(R.drawable.cardimage5,"Lamborghini Aventador",""));

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

            case R.id.users_id:
                Toast.makeText(this,"Users Clicked",Toast.LENGTH_SHORT).show();
                break;

            case R.id.search_id:
                Toast.makeText(this,"Search Clicked",Toast.LENGTH_SHORT).show();
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

<<<<<<< HEAD
            startActivity(new Intent(NavigationDrawer.this,PaymentCard.class));
=======
            startActivity(new Intent(NavigationDrawer.this,ViewCars.class));
>>>>>>> View cars updated
        }

        else if (id == R.id.profile_id){

            startActivity(new Intent(NavigationDrawer.this,Profile.class));

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
