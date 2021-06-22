 package com.example.smartdoctor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class DoctorProfile extends AppCompatActivity {
    TextView doctorName,demail,dphone,dlocation,dbio,dservices,backHome;
    private Object String;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        doctorName=findViewById(R.id.doctor_name);
        demail=findViewById(R.id.doctor_email);
        dphone=findViewById(R.id.doctor_number);
        dlocation=findViewById(R.id.doctor_location);
        dservices=findViewById(R.id.doctor_services);
        dbio=findViewById(R.id.doctor_bio);
        backHome=findViewById(R.id.backHome);
        backHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intentBack);
            }
        });
        Intent getDoctorData=getIntent();
        Bundle b=getDoctorData.getExtras();
        if(b!=null){
            String name=(String)b.get("name");
            String email=(String)b.get("email");
            String number=(String)b.get("number");
            String location=(String)b.get("location");
            String bio=(String)b.get("bio");
            String services=(String)b.get("services");
            doctorName.setText(name);
            demail.setText(email);
            dphone.setText(number);
            dlocation.setText(location);
            dbio.setText(bio);
            dservices.setText(services);



        }

    }
}