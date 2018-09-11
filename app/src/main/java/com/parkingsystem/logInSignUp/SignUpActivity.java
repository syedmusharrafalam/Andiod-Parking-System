package com.parkingsystem.logInSignUp;

import android.content.Intent;
import android.media.MediaCodec;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.parkingsystem.R;
import com.parkingsystem.userpanel.activities.UserInformation;

public class SignUpActivity extends AppCompatActivity {
    EditText editNameSignUp,editContactSignUp,editEmailSignUp,editPasswordSignUp;
    Button btnSignUp,btnLogIn;
    FirebaseAuth nAuth;
    DatabaseReference dr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        nAuth=FirebaseAuth.getInstance();
        editNameSignUp=(EditText)findViewById(R.id.userNameSignUp);
        editContactSignUp=(EditText)findViewById(R.id.userContactSignup);
        editEmailSignUp=(EditText)findViewById(R.id.userEmailSignUp);
        editPasswordSignUp=(EditText)findViewById(R.id.userPasswordSignUp);
        btnSignUp=(Button)findViewById(R.id.buttonSignup);
        btnLogIn=(Button)findViewById(R.id.buttonLogIn);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addUser();

            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(SignUpActivity.this,LogIn.class));
            }
        });

    }

    private void addUser() {
        final String name=editNameSignUp.getText().toString().trim();
        final String contact=editContactSignUp.getText().toString().trim();
        final String email=editEmailSignUp.getText().toString().trim();
        final String password=editPasswordSignUp.getText().toString().trim();

        if(name.isEmpty())
        {
            editNameSignUp.setError("Please Enter Name");
            editNameSignUp.requestFocus();

        }
        if(contact.isEmpty())
        {
            editContactSignUp.setError("Please Contact");
            editContactSignUp.requestFocus();
        }
        if(email.isEmpty())
        {
            editEmailSignUp.setError("please enter email");
            editEmailSignUp.requestFocus();
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            editEmailSignUp.setError("Please enter valid email");
        }


        if(password.isEmpty())
        {
            editPasswordSignUp.setError("Please enter password");
            editPasswordSignUp.requestFocus();
        }
        if(password.length()<6)
        {
            editPasswordSignUp.setError("please enter 6 digits");
            editPasswordSignUp.requestFocus();
        }

        else {

            nAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
if(task.isSuccessful()){
                   String uid=nAuth.getCurrentUser().getUid();
                    dr= FirebaseDatabase.getInstance().getReference().child("users").child(uid);
                    UserInformation userInformation=new UserInformation(uid,name,contact,email,password);
                    dr.setValue(userInformation);
                    finish();
                    startActivity(new Intent(SignUpActivity.this,LogIn.class));}
//public UserInformation(String uid, String name, String contact, String email, String password)


                }
            });




        }

    }
}
