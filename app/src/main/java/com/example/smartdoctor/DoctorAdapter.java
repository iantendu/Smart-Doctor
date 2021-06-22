package com.example.smartdoctor;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;


public class DoctorAdapter extends FirebaseRecyclerAdapter<Doctor,DoctorAdapter.DoctorViewholder>{


    public DoctorAdapter(
            @NonNull FirebaseRecyclerOptions<Doctor> options)
    {
        super(options);
    }


    protected void
    onBindViewHolder(@NonNull DoctorViewholder holder,
                     int position, @NonNull Doctor model)
    {

        holder.doctorFullname.setText("Dr "+model.getFname()+" "+model.getLname());
        holder.Service.setText(model.getServices());
        holder.messageDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context , MessageDoctor.class);
                //String ref= FirebaseDatabase.getInstance().getReference("Doctors").child("email").

                intent.putExtra("name",model.getFname()+" "+model.getLname());
                intent.putExtra("key",model.getEmail());


                context.startActivity(intent);
            }
        });
        holder.viewDoctorProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context , DoctorProfile.class);
                intent.putExtra("name",model.getFname()+" "+model.getLname());
                intent.putExtra("email",model.getEmail());
                intent.putExtra("number",model.getPhone());
                intent.putExtra("location",model.getLocation());
                intent.putExtra("bio",model.getBio());
                intent.putExtra("services",model.getServices());
                context.startActivity(intent);
            }
        });


    }


    public DoctorViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.doctor, parent, false);
        return new DoctorAdapter.DoctorViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class DoctorViewholder
            extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView doctorFullname ,Service;
        Button messageDoctor;
        ImageView viewDoctorProfile;
        public DoctorViewholder(@NonNull View itemView)
        {
            super(itemView);

            doctorFullname=itemView.findViewById(R.id.doctorfullname);
            Service=itemView.findViewById(R.id.doctorService);
            messageDoctor=itemView.findViewById(R.id.messageDoctor);
            viewDoctorProfile=itemView.findViewById(R.id.doctorProfile);
            messageDoctor.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.messageDoctor:


                  break;
                case  R.id.doctorProfile:
                    break;
                default:
                    break;

            }
        }
    }
}
