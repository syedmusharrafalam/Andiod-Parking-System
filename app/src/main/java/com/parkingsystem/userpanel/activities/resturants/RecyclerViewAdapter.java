package com.parkingsystem.userpanel.activities.resturants;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parkingsystem.Listener;
import com.parkingsystem.R;
import com.parkingsystem.Slots;
import com.parkingsystem.booking.Booking;
import com.parkingsystem.userpanel.activities.UserInformation;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by User on 4/17/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>  {
  Context context;
Listener click;
    String startTime;
    String endTime;
    FirebaseAuth mAuth;
    ArrayList<Slots>mData;
    ArrayList<Booking>mBooking;

    String startTimes="1524273000000";
    String endTimes="1524280200000";



    public RecyclerViewAdapter(Context context,ArrayList<Booking>mBooking,ArrayList<Slots>mData,Listener click,String startTime,String endTime) {
       this.click=click;
        this.context = context;
        this.mData=mData;
        this.mBooking=mBooking;

        this.startTime=startTime;
        this.endTime=endTime;


    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.custom_button,parent,false);
        MyViewHolder vHolder=new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        for (int i=0;i<mBooking.size();i++)
        {
            if(startTime==mBooking.get(i).getStartMils()&&endTimes==mBooking.get(i).getEndMils())
            {
                if(mBooking.get(i).getSlots()==Integer.parseInt(mData.get(position).getSlot()))
                {
                    holder.btn.setText("Already Book");
                    break;
                }
                else {
                    holder.btn.setText("Slot"+mData.get(position).getSlot());
                }

            }
            else {
                holder.btn.setText("Slot"+mData.get(position).getSlot());
            }
        }


       //  holder.btn.setText("Slot "+mData.get(position).getSlot());
         holder.btn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
            /*     click.onItemClick(position,v);
                 holder.btn.setEnabled(false);*/
            bookings(position);

             }
         });

    }

    private void bookings(final int position) {
       // final ArrayList<UserInformation>mUsers=new ArrayList<>();

        mAuth=FirebaseAuth.getInstance();
        final String uid=mAuth.getCurrentUser().getUid();
        DatabaseReference ref2=FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             String name=dataSnapshot.child("name").getValue(String.class);
                String contact=dataSnapshot.child("contact").getValue(String.class);
                String email=dataSnapshot.child("email").getValue(String.class);
                DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("booking");
                String bid=ref.push().getKey();
                Booking book=new Booking(name, contact, email,uid,bid,startTime,endTime,position+1);
                ref.child(bid).setValue(book);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });






    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

  /*  @Override
    public void onItemClick(int position, View v) {

    }*/


    public static class MyViewHolder extends RecyclerView.ViewHolder{
    TextView btn;



        public MyViewHolder(View itemView) {
            super(itemView);
            btn=(TextView) itemView.findViewById(R.id.btnSlots);


        }
    }
}
