package com.example.urban_crew_extended;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.appcompat.view.ActionMode.Callback;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyTrips extends AppCompatActivity {

    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    ArrayList<String> list;
    ExpandableListView expandableListView;
    HashMap<String, List<String>> item;
    private ActionMode tripActionMode;
    TripInfoExpandableListAdapter adapter;
    int itemPosition;
    View view1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);

        Toolbar toolbar = findViewById(R.id.toolbar_myTrips);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Trips");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        expandableListView = findViewById(R.id.expandableListView);

        item = new HashMap<>();
        list = new ArrayList<>();


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        final DatabaseReference databaseReference1 = firebaseDatabase.getReference(firebaseAuth.getUid())
                .child("AltoK10").child("Booking Information");
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                String Vehicle  = dataSnapshot.child("Vehicle Name").getValue().toString();
                String PickUp = dataSnapshot.child("PickUpLocation").getValue().toString();
                String Date = dataSnapshot.child("Date").getValue().toString();
                String Time = dataSnapshot.child("Time").getValue().toString();
                String Book = dataSnapshot.child("Booking Price").getValue().toString();
                String Extra = dataSnapshot.child("Rental Extra Options").getValue().toString();
                String Payment = dataSnapshot.child("Payment").getValue().toString();

                list.add(PickUp);
                list.add(Date);
                list.add(Time);
                list.add(Book);
                list.add(Extra);
                list.add(Payment);

                item.put(Vehicle,list);
                adapter = new TripInfoExpandableListAdapter(item);
                expandableListView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(MyTrips.this,databaseError.getCode(), Toast.LENGTH_SHORT).show();

            }
        });

        /*expandableListView.setOnLongClickListener(new OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                if (tripActionMode == null) {

                    return false;

                }

                tripActionMode = startSupportActionMode(tripActionModeCallback);
                return true;
            }
        });*/

        expandableListView.setOnItemLongClickListener(new OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                itemPosition = position;
                view1 = view;

                if (tripActionMode != null){

                    return false;
                }

                tripActionMode = startSupportActionMode(tripActionModeCallback);

                return true;
            }
        });
    }

    private ActionMode.Callback tripActionModeCallback = new Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {

            mode.getMenuInflater().inflate(R.menu.contextual_menu, menu);
            mode.setTitle("Delete Selected Item");

            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

            switch (item.getItemId()){

                case R.id.trip_delete:

                    DatabaseReference databaseReference2 = firebaseDatabase.getReference(firebaseAuth.getUid())
                            .child("AltoK10");
                    databaseReference2.setValue(null);
                    //list.remove(itemPosition);
                    //adapter.notifyDataSetChanged();
                    mode.finish();
                    Toast.makeText(MyTrips.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                    return true;

                default:

                    return true;
            }
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

            tripActionMode = null;

        }
    };
}
