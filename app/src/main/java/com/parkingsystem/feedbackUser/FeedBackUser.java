package com.parkingsystem.feedbackUser;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parkingsystem.R;
import com.parkingsystem.userpanel.activities.UserInformation;

import java.util.ArrayList;

public class FeedBackUser extends AppCompatActivity {
EditText edtFeedBack;
    Button btnFeedBack;
    FirebaseAuth nAuth;
    String uid;
    ArrayList<FeedBackInf>mFeedBack;
    RecyclerView recyclerView;
    FeedBackAdapter adapter;
    RecyclerView.LayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back_user);
        mFeedBack=new ArrayList<>();
        recyclerView=(RecyclerView)findViewById(R.id.feedBackRecycler);
        edtFeedBack=(EditText)findViewById(R.id.edtFeedBack);
        btnFeedBack=(Button)findViewById(R.id.btnFeedBack);
          nAuth=FirebaseAuth.getInstance();
        uid=nAuth.getCurrentUser().getUid();
        btnFeedBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
            }
        });

        DatabaseReference ref=FirebaseDatabase.getInstance().getReference().child("feedback").child(uid);
        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FeedBackInf book=dataSnapshot.getValue(FeedBackInf.class);
                mFeedBack.add(book);


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
        adapter=new FeedBackAdapter(this,mFeedBack);
        recyclerView.setAdapter(adapter);
        manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);



    }

    private void submit() {
        final String message=edtFeedBack.getText().toString();

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("users").child(uid);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child("name").getValue(String.class);
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference().child("feedback").child(uid);
                String fid=ref.push().getKey();
                FeedBackInf feedbackInf=new FeedBackInf(fid,uid,name,message);
                ref.child(fid).setValue(feedbackInf);

                DatabaseReference ref2=FirebaseDatabase.getInstance().getReference().child("feedbacks").child(uid);
                FeedBackUid feedBackUid=new FeedBackUid(uid,name,fid);
                ref2.setValue(feedBackUid);



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });




    }
}
