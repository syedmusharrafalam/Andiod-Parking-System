package com.parkingsystem.userpanel.activities.resturants;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import com.google.firebase.database.ValueEventListener;
import com.parkingsystem.Listener;
import com.parkingsystem.MainActivity;
import com.parkingsystem.R;
import com.parkingsystem.Slots;
import com.parkingsystem.booking.Booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by User on 4/8/2018.
 */

public class AreaOneFragement extends Fragment{
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

    public AreaOneFragement()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.area_one_activity, container, false);
        mData=new ArrayList<>();

        endTimeTextView=(TextView)v.findViewById(R.id.endTime);
       // slots1=(TextView)v.findViewById(R.id.slot1);
        btnCalendar=(Button)v.findViewById(R.id.calendar);
        btnTime=(Button)v.findViewById(R.id.btnTime);
        btnshow=(Button)v.findViewById(R.id.btnShow);

        date=(TextView)v.findViewById(R.id.date);
        time=(TextView)v.findViewById(R.id.tvTime);
        spin=(Spinner)v.findViewById(R.id.spinnerDuration);
        recyclerView1=(RecyclerView)v.findViewById(R.id.recyclerView1);
      //  recyclerViewAdapter=new RecyclerViewAdapter(getContext(),mData,listener,a,b);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView1.setAdapter(recyclerViewAdapter);
        //recyclerViewAdapter.notifyDataSetChanged();
       /* recyclerViewAdapter.notifyDataSetChanged();*/

      DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Resturant");
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

        ArrayAdapter<String> aa = new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_spinner_item,duration);
        spin.setAdapter(aa);
        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar calendar= java.util.Calendar.getInstance();
                hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
            min = calendar.get(java.util.Calendar.MINUTE);
          sec = calendar.get(java.util.Calendar.SECOND);
                try {
                    TimePickerDialog timePickerDialog=new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
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
        Toast.makeText(getContext(),"last"+b,Toast.LENGTH_LONG).show();

      recyclerView1.setVisibility(View.VISIBLE);
       // recyclerViewAdapter=new RecyclerViewAdapter(getContext(),mData,listener,a,b);
        recyclerView1.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView1.setAdapter(recyclerViewAdapter);
        recyclerViewAdapter.notifyDataSetChanged();}
catch (Exception e)
{
    e.printStackTrace();
    Toast.makeText(getContext(),""+e.getMessage(),Toast.LENGTH_LONG).show();
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


                DatePickerDialog pickerDialog=new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
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
        });
        return v;
    }



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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);







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
