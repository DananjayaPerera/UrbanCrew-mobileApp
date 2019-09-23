package com.example.urban_crew_extended;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import android.os.Bundle;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyTrips extends AppCompatActivity {

    private RecyclerView myTrips;
    RecyclerView.LayoutManager layoutManager;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference1,databaseReference2,databaseReference3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trips);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();

        databaseReference1 = firebaseDatabase.getReference(firebaseAuth.getUid()).child("AltoK10")
                .child("Booking Information");
        databaseReference2 = firebaseDatabase.getReference(firebaseAuth.getUid()).child("AltoK10")
                .child("Booking Options");
        databaseReference3 = firebaseDatabase.getReference(firebaseAuth.getUid()).child("AltoK10")
                .child("Payment Options").child("Card Payment").child("Card Details");
    }

}
