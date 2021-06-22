package com.example.smartdoctor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MessagesAdapter extends FirebaseRecyclerAdapter<Messages,MessagesAdapter.DoctorViewholder> {
    public static final int  MSG_TYPE_LEFT=0;
    public static final int  MSG_TYPE_RIGHT=1;

    private Context mcontext;
    private List<Messages> messagesList;

    FirebaseUser firebaseUser;

    public MessagesAdapter(@NonNull FirebaseRecyclerOptions<Messages> options, Context mcontext, List<Messages> messagesList) {
        super(options);
        this.mcontext = mcontext;
        this.messagesList = messagesList;
    }




    @NonNull
    @Override
    public MessagesAdapter.DoctorViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==MSG_TYPE_LEFT){
            View view= LayoutInflater.from(mcontext).inflate(R.layout.list_item_message_sent,parent,false);
            return new MessagesAdapter.DoctorViewholder(view);
        }
        else{
            View view =LayoutInflater.from(mcontext).inflate(R.layout.list_item_message_received,parent,false);
            return new MessagesAdapter.DoctorViewholder(view);
        }

    }

    @Override
    protected void onBindViewHolder(@NonNull MessagesAdapter.DoctorViewholder holder, int position, @NonNull Messages model) {
        Messages messages=messagesList.get(position);
        holder.messageText.setText(messages.getMessage());

    }


    @Override
    public int getItemViewType(int position) {
        firebaseUser= FirebaseAuth.getInstance().getCurrentUser();
        if(messagesList.get(position).getSender().equals(firebaseUser.getEmail())){
            return MSG_TYPE_LEFT;
        }
        else{
            return MSG_TYPE_RIGHT;
        }

    }


    public class DoctorViewholder extends RecyclerView.ViewHolder {
        TextView messageText;
        public DoctorViewholder(@NonNull View itemView) {
            super(itemView);
            messageText=itemView.findViewById(R.id.messageText);
        }
    }
}
