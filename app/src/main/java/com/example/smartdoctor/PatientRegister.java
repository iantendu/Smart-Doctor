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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class PatientRegister extends AppCompatActivity {
    TextView patientLogin;
    Button registerPatient;
    TextInputEditText fname,lname,email,password ,confirmpass;

    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        mAuth = FirebaseAuth.getInstance();
        patientLogin=findViewById(R.id.patientLogin);
        registerPatient=findViewById(R.id.patientRegisterBtn);
        fname=(TextInputEditText) findViewById(R.id.patientFname);
        lname=(TextInputEditText)findViewById(R.id.patientLname);
        email=(TextInputEditText)findViewById(R.id.patientEmail);
        password=(TextInputEditText)findViewById(R.id.patientPassword);
        confirmpass=findViewById(R.id.patientPassword2);


        patientLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent patientIntent=new Intent(getApplicationContext(),PatientLogin.class);
                startActivity(patientIntent);
            }
        });
        registerPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerPatient();
            }
        });
    }
    public void registerPatient(){
        String patientFname=fname.getText().toString().trim();
        String patientLname=lname.getText().toString().trim();
        String patientEmail=email.getText().toString().trim();
        String patientPassword=password.getText().toString().trim();
        //String patientConfirmPass=confirmpass.getText().toString().trim();
//        if( fname.getText().toString().length() == 0 )
//            fname.setError( "First name is required!" );
//        if( lname.getText().toString().length() == 0 )
//            lname.setError( "Last name is required!" );
//        if( email.getText().toString().length() == 0 )
//            email.setError( "Email is required!" );
//        if( password.getText().toString().length() == 0 )
//            password.setError( "Password is required!" );
//        if( password.getText().toString().length() == 0 )
//            confirmpass.setError( "Confirm password is required!" );
//        if(patientConfirmPass==patientPassword){z
//            confirmpass.setError( "Password mismatch!" );
//        }



        mAuth.createUserWithEmailAndPassword(patientEmail,patientPassword).addOnCompleteListener(task -> {
            if(task.isSuccessful()){
                Patient patient=new Patient(patientFname,patientLname,patientEmail);
                FirebaseDatabase.getInstance().getReference("Patients").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).setValue(patient).addOnCompleteListener(this,new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Intent patientIntent=new Intent(getApplicationContext(),PatientLogin.class);
                            startActivity(patientIntent);
                        }
                        else{

                        }

                    }
                });
            }
        });



    }
}