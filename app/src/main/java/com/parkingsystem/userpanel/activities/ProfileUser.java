package com.parkingsystem.userpanel.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.parkingsystem.R;
import com.parkingsystem.bookinghistorypanel.BookingHistory;
import com.parkingsystem.feedbackUser.FeedBackUser;


public class ProfileUser extends AppCompatActivity {
Button userBooking,btnHistory,btnFeedback;
    DatabaseReference ref;
    FirebaseAuth nAuth;
    TextView valueProfileUserName;
    TextView valueProfileUserEmail;
    TextView valueProfileUserContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_user);
        nAuth=FirebaseAuth.getInstance();

        valueProfileUserName=(TextView)findViewById(R.id.valueProfileUserName);
        valueProfileUserEmail=(TextView)findViewById(R.id.valueProfileUserEmail);
        valueProfileUserContact=(TextView)findViewById(R.id.valueProfileUserContact);
        final String uid=nAuth.getCurrentUser().getUid();
         ref= FirebaseDatabase.getInstance().getReference();
        DatabaseReference reference=ref.child("users").child(uid);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String name=dataSnapshot.child("name").getValue(String.class);
                String contact=dataSnapshot.child("contact").getValue(String.class);
                String email=dataSnapshot.child("email").getValue(String.class);
                valueProfileUserName.setText(name);
                valueProfileUserEmail.setText(contact);
                valueProfileUserContact.setText(email);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
btnFeedback=(Button)findViewById(R.id.userFeedBack);
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileUser.this, FeedBackUser.class));
            }
        });

        btnHistory=(Button)findViewById(R.id.userBookingHistorys);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileUser.this, BookingHistory.class));
            }
        });

        userBooking=(Button)findViewById(R.id.userBooking);
        userBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfileUser.this,PlacesActivity.class));
            }
        });
    }


}
