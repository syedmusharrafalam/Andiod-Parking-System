package com.parkingsystem.userpanel.activities.shopingmall;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parkingsystem.R;
import com.parkingsystem.booking.Booking;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by User on 4/8/2018.
 */

public class AreaThreeFragementMall extends Fragment{
    List<Booking> mData;
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
    int d,m,y,sentHour,hour,minute,sec;
    String format,dates,times,checkinDate;
    Spinner spin;
    String[] duration={"0","1","2","3","4","5","6","7"};
    public AreaThreeFragementMall()
    {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v=inflater.inflate(R.layout.mall_three_activity, container, false);
        mData=new ArrayList<>();
        endTimeTextView=(TextView)v.findViewById(R.id.endTime);
       // slots1=(TextView)v.findViewById(R.id.slot1);
        btnCalendar=(Button)v.findViewById(R.id.calendar);
        btnTime=(Button)v.findViewById(R.id.btnTime);
        btnshow=(Button)v.findViewById(R.id.btnShow);
     /*   slot1=(Button)v.findViewById(R.id.slot1);
        slot2=(TextView)v.findViewById(R.id.slot2);
        slot3=(TextView)v.findViewById(R.id.slot3);*/
        date=(TextView)v.findViewById(R.id.date);
        time=(TextView)v.findViewById(R.id.tvTime);
        spin=(Spinner)v.findViewById(R.id.spinnerDuration);


        ArrayAdapter<String> aa = new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_spinner_item,duration);
        // aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);

       /* slot1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                booking();
            }
        });*/



        btnTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                java.util.Calendar calendar= java.util.Calendar.getInstance();
                hour = calendar.get(java.util.Calendar.HOUR_OF_DAY);
            minute = calendar.get(java.util.Calendar.MINUTE);
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
                    },hour,minute,true);
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
        a=getTime(checkinDate,sentHour,minute,sec);
        String s=spin.getSelectedItem().toString();
        endTime= sentHour+Integer.parseInt(s);
        b=getTimeEnd(checkinDate,endTime,minute,sec);
        Toast.makeText(getContext(),"last"+b,Toast.LENGTH_LONG).show();
        // endTimeTextView.setText(endTime);
       /* slot1.setVisibility(View.VISIBLE);
        slot2.setVisibility(View.VISIBLE);
        slot3.setVisibility(View.VISIBLE);

        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("booking").child("Resturant").child("Area1");
        reference.addChildEventListener(new ChildEventListener() {
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
        });*/

        /*for(int i=0;i<=mData.size();i++)
        {
*//*
                String start=mData.get(i).getStartMils();
                String end=mData.get(i).getEndMils();*//*
                if(a == mData.get(i).getStartMils() && b == mData.get(i).getEndMils()){
                    if(mData.get(i).getSlots().equals("Slots 1")) {
                        slot1.setEnabled(false);
                        slot1.setBackgroundColor(Color.RED);
                        slot1.setText("Already book");
                        Toast.makeText(getContext(),"perfect",Toast.LENGTH_LONG).show();

                    }
                    else if(mData.get(i).getSlots().equals("Slots 2"))
                    {
                        slot2.setEnabled(false);
                        slot2.setBackgroundColor(Color.RED);
                        slot2.setText("Already book");
                    }
                    else if(mData.get(i).getSlots().equals("Slots 3"))
                    {
                        slot3.setEnabled(false);
                        slot3.setBackgroundColor(Color.RED);
                    }
                    else {

                    }

                }









        }
*/

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
                   dates=dayOfMonth + " - " + month +  " - " + year;

                   //   date.setText(""+dayOfMonth+"/"+month+"/"+year);
                        date.setText(dates);
                  checkinDate = year +"-"+ month + "-" + dayOfMonth;
                    }
                },y,m,d);
                pickerDialog.show();
            }
        });
        return v;
    }

    private void booking() {
         nAuth=FirebaseAuth.getInstance();
        String uid=nAuth.getCurrentUser().getUid();
        /*DatabaseReference reference=ref.child("users").child(uid);
        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserInformation information=dataSnapshot.getValue(UserInformation.class);
                name=information.getName();
                email=information.getEmail();
                uids=information.getUid();


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
        });*/
      /*   ref=FirebaseDatabase.getInstance().getReference().child("booking").child("Resturant").child("Area1");
        String bid=ref.push().getKey();
        Booking booking=new Booking(uid,bid,a,b,"Slots 1");
        //Booking booking=new Booking(bid,uid,name,email,"Resturant","Area 1","Slot 1",String.valueOf(sentHour),String.valueOf(minute),String.valueOf(endTime),String.valueOf(minute),spin.getSelectedItem().toString(),String.valueOf(d),String.valueOf(m),String.valueOf(y),a,b);
          ref.child(bid).setValue(booking);*/


        //Booking(String bid, String uid, String name, String email, String location, String area, String slots, String hoursStart, String minutesStart, String hourEnd, String minutesEnd, String duration, String day, String month, String year,String startmills, String endmills)



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
