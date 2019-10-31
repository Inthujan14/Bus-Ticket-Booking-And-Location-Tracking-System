package com.example.inthujan.finalproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreditActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;
    private EditText editTextCardNumber;
    private EditText editTextDate;
    private EditText editTextCvvNumber;
    private EditText editTextCardName;
    private Button buttonNext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        getSupportActionBar().setTitle("Card Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextCardNumber=(EditText)findViewById(R.id.textViewCardNumber);
        editTextDate=(EditText)findViewById(R.id.textViewDate);
        editTextCvvNumber=(EditText)findViewById(R.id.textViewCvvNumber);
        editTextCardName=(EditText)findViewById(R.id.textViewCardName);
        buttonNext=(Button)findViewById(R.id.btnFinish);

        progressDialog = new ProgressDialog(this);
        buttonNext.setOnClickListener(this);




    }
    private void addCredit() {

        String cardNumber = editTextCardNumber.getText().toString().trim();
        String cardDate = editTextDate.getText().toString().trim();
        String cvvNumber = editTextCvvNumber.getText().toString().trim();
        String cardName = editTextCardName.getText().toString().trim();

        final String nameBus=getIntent().getStringExtra("NAME_BUS");
        final String dateBus=getIntent().getStringExtra("DATE_BUS");
        final String conditionBus=getIntent().getStringExtra("CONDITION_BUS");


        if (TextUtils.isEmpty(cardNumber)) {
            //email is empty
            Toast.makeText(this, "Please Enter The Card Number", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(cardDate)) {
            //password is empty
            Toast.makeText(this, "Please Enter The Expery Date", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(cvvNumber)) {
            //password is empty
            Toast.makeText(this, "Please Enter The CVV  Number ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(cardName)) {
            //password is empty
            Toast.makeText(this, "Please Enter The Card Owner Name", Toast.LENGTH_SHORT).show();
            return;
        }

        CreditDetail creditDetail = new CreditDetail(cardNumber,cardDate,cvvNumber,cardName);

        FirebaseUser user = firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("PaymentDetails").setValue(creditDetail);
        progressDialog.setMessage("Making Payment Please Wait...");
        progressDialog.show();

        Intent intent=new Intent(CreditActivity.this,ConfirmActivity.class);
        intent.putExtra("NAME_BUS",nameBus);
        intent.putExtra("DATE_BUS",dateBus);
        intent.putExtra("CONDITION_BUS",conditionBus);
        startActivity(intent);
    }

    @Override
    public void onClick(View view) {
        if (view == buttonNext) {
            addCredit();
        }
    }


}
