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
import com.parkingsystem.userpanel.activities.UserInformation;

import java.util.ArrayList;

public class UserInfo extends AppCompatActivity {
RecyclerView userRecycler;
    ArrayList<UserInformation>mUsers;
    DatabaseReference reference;
    UserAdapter adapter;
    RecyclerView.LayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mUsers=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference().child("users");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserInformation userInformation=dataSnapshot.getValue(UserInformation.class);
                mUsers.add(userInformation);
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


        userRecycler=(RecyclerView)findViewById(R.id.userRecyclerView);
        adapter=new UserAdapter(UserInfo.this,mUsers);
        userRecycler.setAdapter(adapter);
        manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        userRecycler.setLayoutManager(manager);

    }
}
