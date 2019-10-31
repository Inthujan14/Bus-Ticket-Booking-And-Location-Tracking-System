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

public class ConfirmActivity extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private EditText emailId;
    private EditText phoneNumber;
    private EditText nameCustomer;
    private EditText ageCustomer;
    private ProgressDialog progressDialog;
    private Button confirmBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        emailId=(EditText)findViewById(R.id.editTextEmail);
        phoneNumber=(EditText)findViewById(R.id.editTextPhoneNumber);
        nameCustomer=(EditText)findViewById(R.id.editTextName);
        ageCustomer=(EditText)findViewById(R.id.editTextAge);
        confirmBook=(Button)findViewById(R.id.btnBook);

        progressDialog=new ProgressDialog(this);
        confirmBook.setOnClickListener(this);
        databaseReference= FirebaseDatabase.getInstance().getReference();

        firebaseAuth= FirebaseAuth.getInstance();



        getSupportActionBar().setTitle("Contact Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }
    private void contactBook(){

        String cus_email=emailId.getText().toString().trim();
        String cus_phone=phoneNumber.getText().toString().trim();
        String cus_name=nameCustomer.getText().toString().trim();
        String cus_age=ageCustomer.getText().toString().trim();

        final String nameBus=getIntent().getStringExtra("NAME_BUS");
        final String dateBus=getIntent().getStringExtra("DATE_BUS");
        final String conditionBus=getIntent().getStringExtra("CONDITION_BUS");

        if(TextUtils.isEmpty(cus_email)){
            //email is empty
            Toast.makeText(this,"Please enter the email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(cus_phone)){
            //password is empty
            Toast.makeText(this,"Please enter the phone number",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(cus_name)){
            //password is empty
            Toast.makeText(this,"Please enter the customer name",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(cus_age)){
            //password is empty
            Toast.makeText(this,"Please enter the customer age",Toast.LENGTH_SHORT).show();
            return;
        }

        CustomerDetail customerDetail=new CustomerDetail(cus_email,cus_phone,cus_name,cus_age);

        FirebaseUser user=firebaseAuth.getCurrentUser();
        databaseReference.child(user.getUid()).child("CustomerDetails").setValue(customerDetail);
        //child("bookings").child(userId);
        progressDialog.setMessage("Updating Contact Detail Please Wait...");
         progressDialog.show();

        Intent intent=new Intent(ConfirmActivity.this,PointActivity.class);
        startActivity(intent);
        progressDialog.dismiss();
    }
    @Override
    public void onClick(View view) {
        if (view == confirmBook) {
            contactBook();
        }
    }

}
