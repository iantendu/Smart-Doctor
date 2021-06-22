package com.example.smartdoctor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MessageDoctor extends AppCompatActivity {
    TextView doctorName;
    EditText msg;
    Button sendmsgBtn;
    DatabaseReference ref;
    String key, email;
    DatabaseReference mDatabase;
    private RecyclerView recyclerView;
    DatabaseReference mbase;
    MessagesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_doctor);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        doctorName = findViewById(R.id.doctorfullname);
        msg = findViewById(R.id.messageField);
        sendmsgBtn = findViewById(R.id.sendToDoctor);
        recyclerView = findViewById(R.id.recyclerview);



        ArrayList<String> names= new ArrayList<>();
        names.add(" Muringato");
        names.add("Mweiga");
        names.add("Karatina");
        names.add("Kingongo ");
        names.add("Kimathi ");
        names.add("Othaya");
        names.add("Chaka ");
        names.add("Skuta");
        Random random=new Random();
        int index=random.nextInt(names.size());

        doctorName.setText(names.get(index));


        sendmsgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message= msg.getText().toString().trim();
                Map<String,Object> map =new HashMap<String, Object>() ;

                FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                String RegisteredUserID = currentUser.getUid();
                mDatabase = FirebaseDatabase.getInstance().getReference().child("Patients").child(RegisteredUserID);
                mDatabase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        email=snapshot.child("email").getValue(String.class);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                map.put("SenderEmail",email);
                map.put("RecieverEmail","Collins@gmail.com");
                map.put("message",message);
                FirebaseDatabase.getInstance().getReference().child("Chats").push().setValue(map);
                msg.setText("");


            }
        });










//        Intent getDoctorData=getIntent();
//        Bundle b=getDoctorData.getExtras();
//        if(b!=null){
//            String name=(String)b.get("name");
//             key=(String)b.get("key");
//
//
//        }
    }



}