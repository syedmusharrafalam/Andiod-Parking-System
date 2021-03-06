package com.parkingsystem.adminpanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parkingsystem.R;
import com.parkingsystem.booking.Booking;
import com.parkingsystem.bookinghistorypanel.BookingAdapter;

import java.util.ArrayList;

public class BookingHistoryAdmin extends AppCompatActivity {
RecyclerView recyclerViewBook;
    DatabaseReference ref;
    FirebaseAuth nAuth;
    ArrayList<Booking> mData;
    BookingAdapter adapter;
    RecyclerView.LayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_history_admin);
        mData=new ArrayList<>();
        nAuth=FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference().child("booking");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                Booking booking=dataSnapshot.getValue(Booking.class);

                    mData.add(booking);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        recyclerViewBook=(RecyclerView)findViewById(R.id.bookHistoryRecycler);
        adapter=new BookingAdapter(this,mData);
        recyclerViewBook.setAdapter(adapter);
        manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewBook.setLayoutManager(manager);
    }
}
