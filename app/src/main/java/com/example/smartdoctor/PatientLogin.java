package com.example.smartdoctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class PatientLogin extends AppCompatActivity {
   TextView PatientRegister;
   Button Login;
   TextInputEditText email,password;
    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mAuth = FirebaseAuth.getInstance();
        email=findViewById(R.id.patientEmailLogin);
        password=findViewById(R.id.patientPasswordLogin);
        PatientRegister=findViewById(R.id.patientRegister);
        Login=findViewById(R.id.messageDoctor);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginPatient();
            }
        });
        PatientRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patientRegister=new Intent(getApplicationContext(), PatientRegister.class);
                startActivity(patientRegister);
            }
        });
    }
    public void LoginPatient(){
        String patientEmail=email.getText().toString().trim();
        String patientPassword=password.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(patientEmail,patientPassword)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(getApplicationContext(),"Login successfull" ,Toast.LENGTH_LONG).show();
                            Intent patientIntent=new Intent(getApplicationContext(),MessageDoctor.class);
                            startActivity(patientIntent);
                        }
                    }
                });
    }
}