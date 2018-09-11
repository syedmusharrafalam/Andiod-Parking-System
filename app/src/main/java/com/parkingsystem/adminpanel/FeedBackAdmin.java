package com.parkingsystem.adminpanel;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parkingsystem.R;
import com.parkingsystem.feedbackUser.FeedBackInf;
import com.parkingsystem.feedbackUser.FeedBackUid;

import java.util.ArrayList;

public class FeedBackAdmin extends AppCompatActivity {
RecyclerView recyclerView;
FeedBackAdminAdapter adapter;
    RecyclerView.LayoutManager manager;
    ArrayList<FeedBackUid>mFeedBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_admin);
        mFeedBack=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.feedBackAdminRecycler);
        DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("feedbacks");
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
              FeedBackUid feedBackUid=dataSnapshot.getValue(FeedBackUid.class);
                mFeedBack.add(feedBackUid);


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
        adapter=new FeedBackAdminAdapter(this,mFeedBack);
        recyclerView.setAdapter(adapter);
        manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);

    }
}
