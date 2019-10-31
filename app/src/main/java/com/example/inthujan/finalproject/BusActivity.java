package com.example.inthujan.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class BusActivity extends AppCompatActivity implements ItemClickListener{

        private RecyclerView recyclerView;
        private BusAdapter adapter;
        private List<Bus> busList;
        private DatabaseReference dbBuses;
        private FirebaseAuth firebaseAuth;
        private ProgressDialog progressDialog;
        private DatabaseReference databaseReference;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bus);

            getSupportActionBar().setTitle("Select The Your Desired Bus");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            recyclerView = findViewById(R.id.recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            busList = new ArrayList<>();
            adapter = new BusAdapter(this, busList);
            recyclerView.setAdapter(adapter);
            adapter.setClickListener(this);

            firebaseAuth = FirebaseAuth.getInstance();
            databaseReference = FirebaseDatabase.getInstance().getReference();

            String fromBus=getIntent().getStringExtra("FROM_BUS");
           final String toBus=getIntent().getStringExtra("TO_BUS");
            final String dateBus=getIntent().getStringExtra("DATE_BUS");

            FirebaseDatabase.getInstance().getReference("BusDetails")
                    .orderByChild("from")
                    .equalTo(fromBus)
                    .addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            busList.clear();
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                    Bus bus = snapshot.getValue(Bus.class);
                                    busList.add(bus);
                                }
                                adapter.notifyDataSetChanged();
                            }
                            FirebaseDatabase.getInstance().getReference()
                                    .child("BusDetails")
                                    .orderByChild("to")
                                    .equalTo(toBus)
                                    .addValueEventListener(new ValueEventListener() {
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                            busList.clear();
                                            if (dataSnapshot.exists()) {
                                                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                                    Bus bus = snapshot.getValue(Bus.class);
                                                    busList.add(bus);
                                                }
                                                adapter.notifyDataSetChanged();
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError databaseError) {
                                            Toast.makeText(BusActivity.this,"Firebase Database Error",Toast.LENGTH_LONG).show();
                                        }
                                    });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            Toast.makeText(BusActivity.this,"Firebase Database Error",Toast.LENGTH_LONG).show();
                        }
                    });

        }



        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                busList.clear();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        Bus bus = snapshot.getValue(Bus.class);
                        busList.add(bus);
                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };

    @Override
    public void onClick(View view, int position) {
        Bus bus = busList.get(position);

        String busId=bus.getBusId();
        String travelsName=bus.getTravelsName();
        String busNumber=bus.getBusNumber();
        String date=bus.getDate();
        String from=bus.getFrom();
        String to=bus.getTo();
        String busCondition=bus.getBusCondition();

        Bus busDetail=new Bus(busId,travelsName,busNumber,date,from,to,busCondition);
        FirebaseUser user1=firebaseAuth.getCurrentUser();
        databaseReference.child(user1.getUid()).child("BusBookingDetails").setValue(busDetail);

        Toast.makeText(getApplicationContext(),""+travelsName,Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(BusActivity.this,SeatActivity.class);
        intent.putExtra("NAME_BUS",travelsName);
        intent.putExtra("DATE_BUS",date);
        intent.putExtra("CONDITION_BUS",busCondition);
        startActivity(intent);


    }

}