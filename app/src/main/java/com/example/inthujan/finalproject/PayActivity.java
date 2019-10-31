package com.example.inthujan.finalproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PayActivity extends AppCompatActivity {
    private Button buttonOffer;
    private Button buttonCredit;
    private Button buttonDebit;
    private Button buttonNetBanking;
    private Button buttonWallets;
    private TextView textViewTotal;
    private TextView a,b,c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        getSupportActionBar().setTitle("Pay Information");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        buttonOffer = (Button) findViewById(R.id.buttonOffer);
        buttonCredit = (Button) findViewById(R.id.buttonCredit);
        buttonDebit = (Button) findViewById(R.id.buttonDebit);
        buttonNetBanking = (Button) findViewById(R.id.buttonNetBanking);
        buttonWallets = (Button) findViewById(R.id.buttonWallets);

        textViewTotal=(TextView)findViewById(R.id.textViewTotal);
        a=(TextView)findViewById(R.id.textView1);
        b=(TextView)findViewById(R.id.textView2);
        c=(TextView)findViewById(R.id.textView3);

        final String nameBus=getIntent().getStringExtra("NAME_BUS");
        final String dateBus=getIntent().getStringExtra("DATE_BUS");
        final String conditionBus=getIntent().getStringExtra("CONDITION_BUS");

        a.setText(nameBus);
        b.setText(dateBus);
        c.setText(conditionBus);

        String total=getIntent().getStringExtra("TOTALCOSTI");
        textViewTotal.setText(total);

        buttonOffer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(PayActivity.this,CreditActivity.class);
                intent.putExtra("NAME_BUS",nameBus);
                intent.putExtra("DATE_BUS",dateBus);
                intent.putExtra("CONDITION_BUS",conditionBus);
                startActivity(intent);
            }
        });

        buttonCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayActivity.this,CreditActivity.class);
                intent.putExtra("NAME_BUS",nameBus);
                intent.putExtra("DATE_BUS",dateBus);
                intent.putExtra("CONDITION_BUS",conditionBus);
                startActivity(intent);
            }
        });

        buttonDebit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayActivity.this,CreditActivity.class);
                intent.putExtra("NAME_BUS",nameBus);
                intent.putExtra("DATE_BUS",dateBus);
                intent.putExtra("CONDITION_BUS",conditionBus);
                startActivity(intent);
            }
        });

        buttonNetBanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayActivity.this,CreditActivity.class);
                intent.putExtra("NAME_BUS",nameBus);
                intent.putExtra("DATE_BUS",dateBus);
                intent.putExtra("CONDITION_BUS",conditionBus);
                startActivity(intent);
            }
        });

        buttonWallets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PayActivity.this,CreditActivity.class);
                intent.putExtra("NAME_BUS",nameBus);
                intent.putExtra("DATE_BUS",dateBus);
                intent.putExtra("CONDITION_BUS",conditionBus);
                startActivity(intent);
            }
        });

    }
}
