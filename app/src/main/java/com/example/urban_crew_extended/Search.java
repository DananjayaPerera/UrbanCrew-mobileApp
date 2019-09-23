package com.example.urban_crew_extended;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.SearchView.OnQueryTextListener;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    ListView listView;
    ListViewAdapter viewAdapter;
    String [] title;
    Toolbar toolbar;
    int[] icon;
    ArrayList<Search_Model> arrayList = new ArrayList<Search_Model>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        toolbar =  findViewById(R.id.toolbar_search);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Search Cars");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        title = new String[]{"Maruti Suzuki AltoK10","Maruti Suzuki Swift","Maruti Suzuki CelerioX",
                "Maruti Suzuki Wagon R","Mazda 6","Toyota Avalon","Audi A7","BMW M760Li","Honda CR-V",
                "Nissan X-Trail","Toyota Land Cruiser V8","Range Rover Evoque"};

        icon = new int[]{R.drawable.cardimage1,R.drawable.cardimage2,R.drawable.cardimage3,R.drawable.cardimage4,
                R.drawable.cardimage5,R.drawable.cardimage6,R.drawable.cardimage7,R.drawable.cardimage8,
                R.drawable.cardimage9,R.drawable.cardimage10,R.drawable.cardimage11,R.drawable.cardimage12};

        listView = findViewById(R.id.listView);

        for (int i=0; i<title.length; i++){

            Search_Model model = new Search_Model(title[i],icon[i]);

            //bind all strings in an array
            arrayList.add(model);
        }

        //pass results to listViewAdapter class
        viewAdapter = new ListViewAdapter(this, arrayList);

        //bind the adapter to the listView
        listView.setAdapter(viewAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();

        searchView.setOnQueryTextListener(new OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if (TextUtils.isEmpty(newText)){

                    viewAdapter.filter("");
                    listView.clearTextFilter();
                }

                else {

                    viewAdapter.filter(newText);
                }

               return true;
            }

        });

        return true;
    }

}
