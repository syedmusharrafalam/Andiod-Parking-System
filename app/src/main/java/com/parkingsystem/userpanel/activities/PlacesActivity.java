package com.parkingsystem.userpanel.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.parkingsystem.R;
import com.parkingsystem.userpanel.activities.publicpicnicplace.PicnicPlaceActivity;
import com.parkingsystem.userpanel.activities.resturants.Resturant;
import com.parkingsystem.userpanel.activities.shopingmall.ShopingMallActivity;
import com.parkingsystem.userpanels.AreaActivityUser;

import java.util.ArrayList;

public class PlacesActivity extends AppCompatActivity {
    ListView listViewPlaces;
    ArrayList<String> placess;
    //String[] places={"Resturant","Shopping Mall","Public Picnic Place"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);
        placess=new ArrayList<String>();
        placess.add("Resturant");
        placess.add("Shoping mall");
        placess.add("Public picnic place");

        listViewPlaces=(ListView)findViewById(R.id.listViewPlaces);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.custom_place_list,R.id.namePlace,placess);
        listViewPlaces.setAdapter(adapter);
        listViewPlaces.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             String s=placess.get(position);
                Toast.makeText(PlacesActivity.this,""+s,Toast.LENGTH_LONG).show();
                if(s.equals("Resturant")){
              Intent intent=new Intent(PlacesActivity.this,AreaActivityUser.class);
                startActivity(intent);}
                else if(s.equals("Shoping mall"))
                {
                    Intent intent=new Intent(PlacesActivity.this,ShopingMallActivity.class);
                    startActivity(intent);
                }
                else if(s.equals("Public picnic place"))
                {
                    Intent intent=new Intent(PlacesActivity.this, PicnicPlaceActivity.class);
                    startActivity(intent);
                }

            }
        });


    }
}
