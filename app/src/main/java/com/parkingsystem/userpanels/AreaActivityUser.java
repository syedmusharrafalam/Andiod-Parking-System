package com.parkingsystem.userpanels;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parkingsystem.Listener;
import com.parkingsystem.R;
import com.parkingsystem.Slots;
import com.parkingsystem.booking.Booking;
import com.parkingsystem.userpanel.activities.resturants.RecyclerViewAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AreaActivityUser extends AppCompatActivity {
    ArrayList<Booking>mBooking;
    Listener listener;
    RecyclerViewAdapter recyclerViewAdapter;
    RecyclerView recyclerView1;
    ArrayList<Slots> mData;
    String name,email,uids;
    String a,b;
    FirebaseAuth nAuth;
    // Button slot1;
    TextView endTimeTextView,date,time,slot2,slot3;
    int endTime=0;
    DatabaseReference ref;
    // TextView slots1;
    Button btnCalendar,btnTime,btnshow;
    View v;
    int d,m,y,sentHour,hour,min,sec;
    String format,dates,times,checkinDate;
    Spinner spin;
    String[] duration={"0","1","2","3","4","5","6","7"};
    RecyclerView.LayoutManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_user);

        mData=new ArrayList<>();
        mBooking=new ArrayList<>();

        DatabaseReference ref3=FirebaseDatabase.getInstance().getReference().child("booking");
        ref3.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Booking booking=dataSnapshot.getValue(Booking.class);
                mBooking.add(booking);
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

        endTimeTextView=(TextView)findViewById(R.id.endTimes);
        // slots1=(TextView)v.findViewById(R.id.slot1);
        btnCalendar=(Button)findViewById(R.id.calendars);
        btnTime=(Button)findViewById(R.id.btnTimes);
        btnshow=(Button)findViewById(R.id.btnShows);

        date=(TextView)findViewById(R.id.dates);
        time=(TextView)findViewById(R.id.tvTimes);
        spin=(Spinner)findViewById(R.id.spinnerDurations);
        recyclerView1=(RecyclerView)findViewById(R.id.recyclerViews1);
        recyclerViewAdapter=new RecyclerViewAdapter(this,mBooking,mData,listener,a,b);
       // recyclerView1.setLayoutManager(new LinearLayoutManager(this);
        recyclerView1.setAdapter(recyclerViewAdapter);
        manager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView1.setLayoutManager(manager);

        DatabaseReference reference= FirebaseDatabase.getInstance().getReference().child("Resturant");
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Slots slots=dataSnapshot.getValue(Slots.class);
                // Booking booking=dataSnapshot.getValue(Booking.class);
                mData.add(slots);

                //recyclerViewAdapter.notifyDataSetChanged();

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
        ArrayAdapter<String> aa = new ArrayAdapter<String>(AreaActivityUser.this,android.R.layout.simple_spinner_item,duration);
        spin.setAdapter(aa);
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar calendar= java.util.Calendar.getInstance();
                hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
                min = calendar.get(java.util.Calendar.MINUTE);
                sec = calendar.get(java.util.Calendar.SECOND);
                try {
                    TimePickerDialog timePickerDialog=new TimePickerDialog(AreaActivityUser.this, new TimePickerDialog.OnTimeSetListener() {
                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                            // selectTimeFormat(hourOfDay);

                            time.setText(hourOfDay+":"+minute);

                            sentHour = hourOfDay;
                            int start2 = minute;
                            int start3 = 00;



                            //Toast.makeText(getContext(),"abc"+endTimes,Toast.LENGTH_LONG).show();


                        }
                    },hour,min,true);
                    timePickerDialog.show();
                }
                catch (Exception ex )
                {
                    ex.printStackTrace();
                }



            }
        });
        btnshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    a=getTime(checkinDate,sentHour,min,sec);
                    String s=spin.getSelectedItem().toString();
                    endTime= sentHour+Integer.parseInt(s);
                    b=getTimeEnd(checkinDate,endTime,min,sec);
                    Toast.makeText(AreaActivityUser.this,"last"+min,Toast.LENGTH_LONG).show();

                    recyclerView1.setVisibility(View.VISIBLE);
                    recyclerViewAdapter=new RecyclerViewAdapter(AreaActivityUser.this,mBooking,mData,listener,a,b);
                   // recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
                    recyclerView1.setAdapter(recyclerViewAdapter);
                    manager=new LinearLayoutManager(AreaActivityUser.this,LinearLayoutManager.VERTICAL,false);
                    recyclerView1.setLayoutManager(manager);
                    recyclerViewAdapter.notifyDataSetChanged();}
                catch (Exception e)
                {
                    e.printStackTrace();
                    Toast.makeText(AreaActivityUser.this,""+e.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
        btnCalendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar calendar= java.util.Calendar.getInstance();
                d=calendar.get(java.util.Calendar.DAY_OF_MONTH);
                m=calendar.get(java.util.Calendar.MONTH);
                y=calendar.get(java.util.Calendar.YEAR);


                DatePickerDialog pickerDialog=new DatePickerDialog(AreaActivityUser.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        month=month+1;
                        dates=dayOfMonth + " - " + month +  " - " + year;
                        date.setText(dates);

                        //   date.setText(""+dayOfMonth+"/"+month+"/"+year);
                        // date.setText(dates);
                        checkinDate = year +"-"+ month + "-" + dayOfMonth;
                    }
                },y,m,d);
                pickerDialog.show();
            }
        });}
        private void selectTimeFormat(int hour) {
            if(hour==0)
            {
                hour+=12;
                format="AM";
            }
            else if(hour==12)
            {
                format="PM";
            }
            else if(hour>12)
            {
                hour-=12;
                format="PM";

            }
            else {
                format="AM";
            }


        }



    public String getTime(String date,int hours,int min,int sec){
        String givenDateString = "Tue Apr 23 16:08:28 GMT+05:30 2013";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            String time = hours +":"+min +":"+"00"+".000";
            // Date mDate = sdf.parse(givenDateString);
            Date mDate = sdf.parse(date +" "+ time);
            long ab = mDate.getTime();
            return String.valueOf(ab);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        return null;
    }
    public String getTimeEnd(String date,int hours,int min,int sec){
        String givenDateString = "Tue Apr 23 16:08:28 GMT+05:30 2013";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        try {
            String time = hours +":"+min +":"+"00"+".000";
            // Date mDate = sdf.parse(givenDateString);
            Date mDate = sdf.parse(date +" "+ time);
            long ab = mDate.getTime();
            return String.valueOf(ab);
        } catch (ParseException e) {
            e.printStackTrace();
        }



        return null;
    }
}
