package com.example.inthujan.finalproject;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

            firebaseAuth= FirebaseAuth.getInstance();

            if(firebaseAuth.getCurrentUser() != null){
                //profile activity here
                finish();
                startActivity(new Intent(getApplicationContext(),NavigationActivity.class));
            }

            buttonSignIn = (Button)findViewById(R.id.buttonSignIn);
            editTextEmail = (EditText) findViewById(R.id.editTextEmail);
            editTextPassword = (EditText) findViewById(R.id.editTextPassword);
            textViewSignup = (TextView) findViewById(R.id.textViewSignup);

            progressDialog=new ProgressDialog(this);

            buttonSignIn.setOnClickListener(this);
            textViewSignup.setOnClickListener(this);

    }


    private void userLogin(){
        String email=editTextEmail.getText().toString().trim();
        String password=editTextPassword.getText().toString().trim();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        if(TextUtils.isEmpty(email)){
            //email is empty
            Toast.makeText(this,"Please enter the email",Toast.LENGTH_SHORT).show();
            return;
        }
        if (!email.matches(emailPattern)){
            Toast.makeText(this,"Please enter the valid email",Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this,"Please enter password",Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Logging Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                            finish();
                           startActivity(new Intent(getApplicationContext(),NavigationActivity.class));
                            //Toast.makeText(LoginActivity.this,"Logged ",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                            Toast.makeText(LoginActivity.this,"Please enter correct Email and Password",Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }
                    }
                });
    }

    @Override
    public void onClick(View view) {

        if (view == buttonSignIn) {
            userLogin();
        }
        if(view == textViewSignup){
            finish();
            startActivity(new Intent(this,RegisterActivity.class));
        }
    }
}
