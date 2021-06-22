package com.example.smartdoctor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class DoctorRegister extends AppCompatActivity {
    TextInputEditText fname,lname,email,phone,password,location,services,bio;
    TextView doctorLogin;
    Button doctorRegisterBtn;
    public FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mAuth = FirebaseAuth.getInstance();
        doctorRegisterBtn=findViewById(R.id.doctorRegisterBtn);
        doctorLogin=findViewById(R.id.doctorLogin);
        fname=findViewById(R.id.doctorFname);
        lname=findViewById(R.id.doctorLname);
        email=findViewById(R.id.doctorEmail);
        phone=findViewById(R.id.doctorPhone);
        password=findViewById(R.id.doctorPassword);
        location=findViewById(R.id.doctorLocation);
        services=findViewById(R.id.doctorService);
        bio=findViewById(R.id.doctorBio);
        doctorRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doctorRegister();
            }
        });
        doctorLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent doctorIntent=new Intent(getApplicationContext(),DoctorLogin.class);
                startActivity(doctorIntent);
            }
        });
    }
    public void doctorRegister(){
        String doctorFname=fname.getText().toString().trim();
        String doctorLname=lname.getText().toString().trim();
        String doctorEmail=email.getText().toString().trim();
        String doctorPhone=phone.getText().toString().trim();
        String doctorPassword=password.getText().toString().trim();
        String doctorLocation=location.getText().toString().trim();
        String doctorServices=services.getText().toString().trim();
        String doctorBio=bio.getText().toString().trim();

        mAuth.createUserWithEmailAndPassword(doctorEmail,doctorPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Doctor doctor=new Doctor(doctorFname,doctorLname,doctorEmail,doctorPhone,doctorLocation,doctorServices,doctorBio);
                    FirebaseDatabase.getInstance().getReference("Doctors").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(doctor).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Intent doctorIntent=new Intent(getApplicationContext(),DoctorLogin.class);
                                startActivity(doctorIntent);
                            }

                        }
                    });
                }

            }
        });

    }
}