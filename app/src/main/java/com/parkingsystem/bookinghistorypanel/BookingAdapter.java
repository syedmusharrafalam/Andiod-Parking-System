package com.parkingsystem.bookinghistorypanel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parkingsystem.R;
import com.parkingsystem.booking.Booking;
import com.parkingsystem.userpanel.activities.resturants.RecyclerViewAdapter;

import java.util.ArrayList;

/**
 * Created by User on 4/23/2018.
 */

public class BookingAdapter extends RecyclerView.Adapter<BookingAdapter.MyViewHolder> {

Context context;
    ArrayList<Booking>mData;

    public BookingAdapter(Context context, ArrayList<Booking> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public BookingAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        v= LayoutInflater.from(context).inflate(R.layout.custom_booking_user_list,parent,false);
        MyViewHolder viewHolders=new MyViewHolder(v);
        return viewHolders;
    }

    @Override
    public void onBindViewHolder(BookingAdapter.MyViewHolder holder, final int position) {



        holder.username.setText(mData.get(position).getName());
        holder.email.setText(mData.get(position).getEmail());
        holder.contact.setText(mData.get(position).getContact());

        holder.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete(mData.get(position).getBid());
            }
        });


    }

    private void delete(String bid) {
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("booking").child(bid);
        reference.removeValue();

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView username,contact,email;
        Button btnCancel;


        public MyViewHolder(View itemView) {
            super(itemView);
          username=(TextView)itemView.findViewById(R.id.name);
            contact=(TextView)itemView.findViewById(R.id.contact);
            email=(TextView)itemView.findViewById(R.id.email);
          btnCancel=(Button) itemView.findViewById(R.id.bookingCancel);



        }
    }







}
