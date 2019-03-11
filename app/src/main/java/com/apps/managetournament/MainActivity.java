package com.apps.managetournament;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button register;
    private EditText user_email;
    private EditText user_password;
    private TextView signIN;

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = findViewById(R.id.register);
        user_email = findViewById(R.id.user_email);
        user_password = findViewById(R.id.user_password);
        signIN = findViewById(R.id.SingIN);

        mFirebaseAuth = FirebaseAuth.getInstance();
        if(mFirebaseAuth.getCurrentUser() != null){
            //profile activity here. if somebody already login
            finish();
            startActivity(new Intent(getApplicationContext(),userProfile.class));
        }

        register.setOnClickListener(this);
        signIN.setOnClickListener(this);

    }

    private void registerUser(){

        String email = user_email.getText().toString().trim();
        String password = user_password.getText().toString().trim();

        if (TextUtils.isEmpty(email)){
            //Give info that email must be entered
            Toast.makeText(this, "Please enter your email",Toast.LENGTH_LONG).show();
            // if email is not entered do not go further
            return;
        }

        if (TextUtils.isEmpty(password)){
            //password is empty
            Toast.makeText(this, "Please enter your password",Toast.LENGTH_LONG).show();
            // if email is not entered do not go further
            return;
        }
        // try to add progress dialog and start here

       mFirebaseAuth.createUserWithEmailAndPassword(email,password)
              .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Successful Registration",
                                    Toast.LENGTH_LONG).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(),userProfile.class));
                        } if (!task.isSuccessful()){
                          Toast.makeText(MainActivity.this, "Registration Not Successful",
                                  Toast.LENGTH_LONG).show();
                      }
                  }
              });


    }

    @Override
    public void onClick(View v) {
        if (v == register){
            registerUser();
        }

        if (v == signIN){
            // End this activity and start an other one
            // Uses less battery. Mention this in the report
            finish();
            startActivity(new Intent(this,Login.class));
        }
    }
}
