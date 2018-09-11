package com.parkingsystem.adminpanel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;
import android.widget.Button;

import com.parkingsystem.R;

public class AdminProfile extends AppCompatActivity {
Button btnUser,btnBookHistory,btnFeedback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        btnUser=(Button)findViewById(R.id.userInf);
        btnBookHistory=(Button)findViewById(R.id.userBookingHistorysAdm);
        btnFeedback=(Button)findViewById(R.id.userFeedBackAdm);

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminProfile.this,UserInfo.class));
            }
        });

        btnBookHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminProfile.this,BookingHistoryAdmin.class));
            }
        });
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminProfile.this,FeedBackAdmin.class));
            }
        });
    }
}
