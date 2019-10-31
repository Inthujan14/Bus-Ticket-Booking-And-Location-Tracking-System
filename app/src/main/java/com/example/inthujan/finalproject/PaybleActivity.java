package com.example.inthujan.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PaybleActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;
    private Button buttonPay;
     TextView totalCost;
     TextView totalSeat;
     private TextView a,b,c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payble);

        firebaseAuth= FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        getSupportActionBar().setTitle("You Can Pay");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        a=(TextView)findViewById(R.id.textView11);
        b=(TextView)findViewById(R.id.textView21);
        c=(TextView)findViewById(R.id.textView31);

        totalCost=(TextView)findViewById(R.id.totalCostFinal);
        totalSeat=(TextView)findViewById(R.id.totalSeatsFinal);

        final String total=getIntent().getStringExtra("TOTALCOST");
        final String seats=getIntent().getStringExtra("TOTALSEAT");

        final String nameBus=getIntent().getStringExtra("NAME_BUS");
        final String dateBus=getIntent().getStringExtra("DATE_BUS");
        final String conditionBus=getIntent().getStringExtra("CONDITION_BUS");

        a.setText(nameBus);
        b.setText(dateBus);
        c.setText(conditionBus);

        totalCost.setText("Payable : Rs."+total);
        totalSeat.setText("Number Of Seats : "+seats);

        buttonPay=(Button)findViewById(R.id.btnPay);
        buttonPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                String totalPriceI=totalCost.getText().toString();

                String total_cost=totalCost.getText().toString().trim();
                String total_seats=totalSeat.getText().toString().trim();


                Intent intent=new Intent(PaybleActivity.this,PayActivity.class);
                intent.putExtra("TOTALCOSTI",total_cost);

                intent.putExtra("NAME_BUS",nameBus);
                intent.putExtra("DATE_BUS",dateBus);
                intent.putExtra("CONDITION_BUS",conditionBus);
                startActivity(intent);
//                startActivity(new Intent(getApplicationContext(), PayActivity.class));
            }
        });
    }
}
