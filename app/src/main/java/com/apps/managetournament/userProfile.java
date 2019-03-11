package com.apps.managetournament;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class userProfile extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private Button logoutbutton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        logoutbutton = findViewById(R.id.buttonlogOut);
        mTextView = findViewById(R.id.textViewUseremail);
    }
}
