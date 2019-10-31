package com.example.inthujan.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EventActivity extends AppCompatActivity implements View.OnClickListener {
    private RatingBar ratingBar;
    private Button buttonSubmit;
    float myRating;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        getSupportActionBar().setTitle("Rate Us");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        ratingBar=(RatingBar)findViewById(R.id.ratingBar);
        buttonSubmit=(Button)findViewById(R.id.btnSubmit);

        progressDialog = new ProgressDialog(this);
        buttonSubmit.setOnClickListener(this);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                int rating=(int)v;
                String message=null;
                myRating=ratingBar.getRating();

                switch (rating){
                    case 1:
                        message="Thank You For Your Feedback";
                        break;
                    case 2:
                        message="Thank You For Your Feedback";
                        break;
                    case 3:
                        message="Thank You For Your Feedback";
                        break;
                    case 4:
                        message="Thank You For Your Feedback";
                        break;
                    case 5:
                        message="Thank You For Your Feedback";
                        break;
                        default:
                            message="Thamk You For Your Feedback";
                            break;
                }
                Toast.makeText(EventActivity.this,String.valueOf(myRating),Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void addRate() {

        String ratingNumber = String.valueOf(ratingBar.getRating());



        if (TextUtils.isEmpty(ratingNumber)) {
            //password is empty
            Toast.makeText(this, "Please select the Rating value", Toast.LENGTH_SHORT).show();
            return;
        }

        RatingDetail ratingDetail = new RatingDetail(ratingNumber);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("RatingDetails").setValue(ratingDetail);
        Toast.makeText(EventActivity.this,"Thank You For Your Feedback",Toast.LENGTH_SHORT).show();
        startActivity(new Intent(getApplicationContext(), NavigationActivity.class));
    }

    @Override
    public void onClick(View view) {
        if (view == buttonSubmit) {
            addRate();
        }
    }


}
