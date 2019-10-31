package com.example.inthujan.finalproject;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Logger;

import java.util.Calendar;

public class NavigationActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    private static final String TAG = "NavigationActivity";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDatesetListener;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;
    private TextView textViewUserEmail;
    private TextView textViewUserName;

    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private Spinner spinner1;
    private Spinner spinner2;
    private TextView tvDate;
    private Button angry_btn;

//    public static final String FROM_BUS = "firstlocation";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        databaseReference = FirebaseDatabase.getInstance().getReference();


        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner2 = (Spinner) findViewById(R.id.spinner2);
        tvDate = (TextView) findViewById(R.id.tvDate);
        angry_btn = (Button) findViewById(R.id.angry_btn);

        progressDialog = new ProgressDialog(this);
        angry_btn.setOnClickListener(this);

        //From
        Spinner dropdown1 = findViewById(R.id.spinner1);
        String[] items1 = new String[]{"FROM", "Jaffna", "Vavuniya", "Mannar", "Colombo", "Kandy", "Batticola", "Ampara"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        dropdown1.setAdapter(adapter1);

        //To
        Spinner dropdown2 = findViewById(R.id.spinner2);
        String[] items2 = new String[]{"TO", "Jaffna", "Vavuniya", "Mannar", "Colombo", "Kandy", "Batticola", "Ampara"};
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        dropdown2.setAdapter(adapter2);

        angry_btn.setOnClickListener(this);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        getSupportActionBar().setTitle("Searching Buses");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(NavigationActivity.this
                        , android.R.style.Theme_Holo_Light_Dialog_MinWidth
                        , mDatesetListener
                        , year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDatesetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Log.d(TAG, "OnDateSet:date :" + day + "/" + (month + 1) + "/" + year);
                String date = day + "/" + (month + 1) + "/" + year;
                // String status="Journey Date";
                // mDisplayDate.setText(status+"\n"+date);
                mDisplayDate.setText(date);
            }
        };

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigation);
        navigationView.setNavigationItemSelectedListener(this);
    }


    private void searchBuses() {


        String from = spinner1.getSelectedItem().toString().trim();
        String to = spinner2.getSelectedItem().toString().trim();
        String date = tvDate.getText().toString().trim();
        // String key = databaseReference.push().getKey();

        if (TextUtils.equals(from,"FROM")) {
            //email is empty
            Toast.makeText(this, "Please select departure place", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.equals(to,"TO")) {
            //password is empty
            Toast.makeText(this, "Please select destination place", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(date)) {
            //password is empty
            Toast.makeText(this, "Please select journey date", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Searching Buses Please Wait...");
        progressDialog.show();
        BookingDetail bookingDetail = new BookingDetail(from, to, date);

        FirebaseUser user1 = firebaseAuth.getCurrentUser();
        databaseReference.child(user1.getUid()).child("BookingDetails").setValue(bookingDetail);
        //child("bookings").child(userId);
        
//        String fromDetail = spinner1.getSelectedItem().toString().trim();
         Intent intent=new Intent(NavigationActivity.this,BusActivity.class);
//        startActivity(new Intent(getApplicationContext(), BusActivity.class));
        intent.putExtra("FROM_BUS",from);
        intent.putExtra("TO_BUS",to);
        intent.putExtra("DATE_BUS",date);
        startActivity(intent);
        progressDialog.dismiss();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.home:
                Intent h = new Intent(NavigationActivity.this, NavigationActivity.class);
                startActivity(h);
                break;
            case R.id.location:
                Intent l = new Intent(NavigationActivity.this, MapsActivity.class);
                startActivity(l);
                break;
            case R.id.offer:
                Intent o = new Intent(NavigationActivity.this, OfferActivity.class);
                startActivity(o);
                break;
            case R.id.setting:
                Intent s = new Intent(NavigationActivity.this, SettingActivity.class);
                startActivity(s);
                break;
            case R.id.help:
                Intent he = new Intent(NavigationActivity.this, HelpActivity.class);
                startActivity(he);
                break;
            case R.id.detail:
                Intent d = new Intent(NavigationActivity.this, DetailActivity.class);
                startActivity(d);
                break;
            case R.id.cancel:
                Intent c = new Intent(NavigationActivity.this, CancelActivity.class);
                startActivity(c);
                break;
            case R.id.contact:
                Intent co = new Intent(NavigationActivity.this, ContactActivity.class);
                startActivity(co);
                break;
            case R.id.logout:
//                Intent lo = new Intent(NavigationActivity.this, LogoutActivity.class);
//                startActivity(lo);
                firebaseAuth.signOut();
                finish();
                startActivity(new Intent(this,LoginActivity.class));
                break;
            case R.id.event:
                Intent e = new Intent(NavigationActivity.this, EventActivity.class);
                startActivity(e);
                break;
        }
        return false;
    }

    @Override
    public void onClick(View view) {
        if (view == angry_btn) {
            searchBuses();
        }
    }
}