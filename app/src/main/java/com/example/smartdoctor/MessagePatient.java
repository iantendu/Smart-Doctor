package com.example.smartdoctor;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MessagePatient extends AppCompatActivity {
    TextView patientFullname;
    EditText patientMessage;
    Button sendToPatient;
    String email;
    DatabaseReference mDatabase;
    MessagesAdapter messageAdapter;
    ArrayList<String> mChat;
    ListView listView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_patient);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        patientFullname=findViewById(R.id.patientfullname);
        patientMessage=findViewById(R.id.messageField2);
        sendToPatient=findViewById(R.id.sendToPatient);
        listView=findViewById(R.id.listview);
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1,mChat);
        listView.setAdapter(arrayAdapter);


        sendToPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessagePatient();
            }
        });

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

        patientFullname.setText(names.get(index));
    }
    public void sendMessagePatient() {
        String message= patientMessage.getText().toString().trim();
        Map<String,Object> map =new HashMap<String, Object>() ;

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String RegisteredUserID = currentUser.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Doctors").child(RegisteredUserID);
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
        map.put("RecieverEmail","frank@gmail.com");
        map.put("message",message);
        FirebaseDatabase.getInstance().getReference().child("Chats").push().setValue(map);
        patientMessage.setText("");
    }
    private void readMessages(){
        mChat = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReference("Chats");
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String chat = snapshot.child("message").getValue(String.class);

                    mChat.add(chat);



                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}

