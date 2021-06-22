 package com.example.smartdoctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PatientProfileActivity extends AppCompatActivity {
    TextView patientName,patientEmail,backHomePatient;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        patientEmail=findViewById(R.id.patient_email);
        patientName=findViewById(R.id.patient_name);
        backHomePatient=findViewById(R.id.backHomePatient);

        backHomePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent =new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(newIntent);
            }
        });

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String RegisteredUserID = currentUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Patients").child(RegisteredUserID);
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                showData(snapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private void showData(DataSnapshot snapshot) {
        String fname=snapshot.child("fname").getValue(String.class);
        String lname=snapshot.child("lname").getValue(String.class);
        String email=snapshot.child("email").getValue(String.class);

        patientName.setText(fname+" "+lname);
        patientEmail.setText(email);
    }
}