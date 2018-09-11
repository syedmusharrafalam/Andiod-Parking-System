package com.parkingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
Spinner spinner;
    EditText editText;
    Button button;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner=(Spinner)findViewById(R.id.spinner);
        editText=(EditText)findViewById(R.id.edtslots);
        button=(Button)findViewById(R.id.btnSlots);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String slot=editText.getText().toString();
                ref= FirebaseDatabase.getInstance().getReference().child(spinner.getSelectedItem().toString());
                Slots slots=new Slots(slot);
                String id=ref.push().getKey();
                ref.child(id).setValue(slots);
            }
        });


    }
}
