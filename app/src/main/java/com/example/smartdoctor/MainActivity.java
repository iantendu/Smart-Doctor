package com.example.smartdoctor;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button patientStart,doctorStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        patientStart=findViewById(R.id.patientStart);
        doctorStart=findViewById(R.id.doctorStart);
        patientStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patientIntent=new Intent(getApplicationContext(),PatientLogin.class);
                startActivity(patientIntent);
            }
        });
        doctorStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctorIntent=new Intent(getApplicationContext(),DoctorLogin.class);
                startActivity(doctorIntent);
            }
        });

    }
}