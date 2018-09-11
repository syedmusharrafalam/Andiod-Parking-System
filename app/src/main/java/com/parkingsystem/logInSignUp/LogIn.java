package com.parkingsystem.logInSignUp;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.parkingsystem.MainActivity;
import com.parkingsystem.R;
import com.parkingsystem.adminpanel.AdminProfile;
import com.parkingsystem.userpanel.activities.ProfileUser;
import com.parkingsystem.userpanel.activities.UserInformation;

import java.util.ArrayList;
import java.util.List;

public class LogIn extends AppCompatActivity {
EditText userEmailLogIn,userPasswordLogIn;
    Button btnLogIn,btnSignUp;
    FirebaseAuth nAuth;
    DatabaseReference ref;
    List<UserInformation> users;
    String admin="admin@gmail.com";
    String pass="123456";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        nAuth=FirebaseAuth.getInstance();
        users=new ArrayList<>();
        userEmailLogIn=(EditText)findViewById(R.id.userEmailLogIn);
        userPasswordLogIn=(EditText)findViewById(R.id.userPasswordLogIn);
        btnLogIn=(Button)findViewById(R.id.userLogInBtn);
        btnSignUp=(Button)findViewById(R.id.userSignUpBtn);
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             userLogin();

            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(LogIn.this,SignUpActivity.class));

            }
        });
    }

    private void userLogin() {
        String email=userEmailLogIn.getText().toString();
        String password=userPasswordLogIn.getText().toString();
        if(email.isEmpty())
        {
            userEmailLogIn.setError("Email is required");
        }
        if(password.isEmpty())
        {
            userPasswordLogIn.setError("Password is required");
        }
        if(email.equals(admin)&&password.equals(pass))
        {
            startActivity(new Intent(LogIn.this, AdminProfile.class));
        }

        else {
            nAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful())
                    {





                        startActivity(new Intent(LogIn.this, ProfileUser.class));

                    }
                }
            });


        }


    }
}
