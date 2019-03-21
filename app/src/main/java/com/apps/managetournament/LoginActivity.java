package com.apps.managetournament;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button sign_in;         //Button to complete registration
    private EditText user_email;     //Email input
    private EditText user_password;  //Password input
    private TextView register;        //Link to take registered users from the registration page to the
                                      //Login activity page

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sign_in = findViewById(R.id.login_sign_in_button);
        user_email = findViewById(R.id.login_email);
        user_password = findViewById(R.id.login_password);
        register = findViewById(R.id.login_register_button);

        mFirebaseAuth = FirebaseAuth.getInstance();
        if(mFirebaseAuth.getCurrentUser() != null){
            //profile activity here. if somebody already login
            finish();
            startActivity(new Intent(getApplicationContext(), userActivity.class));
        }

        sign_in.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void loginUser(){

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

        mFirebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                             // finish progress dialog here . do the same in the main activity class
                               if (task.isSuccessful()){
                                   // end this activity and Start profile activity
                                   finish();
                                   startActivity(new Intent(getApplicationContext(),userActivity.class));
                               }else{
                                   Toast.makeText(LoginActivity.this,"Please enter valid email and password",Toast.LENGTH_LONG).show();
                               }
                            }
                        }
                );
    }

    @Override
    public void onClick(View v) {
        if (v == sign_in){
            loginUser();
        }

        if (v == register){
            // End this activity and start an other one
            // Uses less battery. Mention this in the report
            finish();
            startActivity(new Intent(this,MainActivity.class));
        }
    }
}
