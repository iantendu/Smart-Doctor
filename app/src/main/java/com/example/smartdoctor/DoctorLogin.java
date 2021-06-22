package com.example.smartdoctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class DoctorLogin extends AppCompatActivity {
    TextView DoctorRegister;
    Button LoginButton;
    TextInputEditText email,password;
    public FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.doctorLoginEmail);
        password=findViewById(R.id.doctorLoginPassword);
        DoctorRegister=findViewById(R.id.doctorRegister);
        LoginButton=findViewById(R.id.loginDoctor);
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginDoctor();
            }
        });

        DoctorRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctorRegister= new Intent(getApplicationContext(), com.example.smartdoctor.DoctorRegister.class);     
                startActivity(doctorRegister);
            }
        });



    }
    public void LoginDoctor(){
        String doctorEmail=email.getText().toString().trim();
        String doctorPassword=password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(doctorEmail,doctorPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   Intent doctorIntent=new Intent(getApplicationContext(),MessagePatient.class);
                   startActivity(doctorIntent);
               }
            }
        });


    }
}