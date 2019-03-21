package com.apps.managetournament;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class userActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private Button logout_button, saveButton;
    private TextView mTextView;
    private EditText full_name, username;
    private FloatingActionButton mFloatingActionButton;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser()==null){
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference("user");

        full_name = findViewById(R.id.fullName);
        username = findViewById(R.id.userName);

        FirebaseUser user= firebaseAuth.getCurrentUser();

        logout_button = findViewById(R.id.buttonlogOut);
        saveButton = findViewById(R.id.buttonSave);

        mTextView = findViewById(R.id.textViewUseremail);

        mFloatingActionButton = findViewById(R.id.floatingActionButton);
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(userActivity.this,showInformation.class);
                startActivity(intent);
            }
        });



        mTextView.setText("Welcome " +user.getEmail());
        logout_button.setOnClickListener(this);
        saveButton .setOnClickListener(this);
    }


    private void saveUserInformation(){

        final String name = full_name.getText().toString().trim();
        String user_name = username.getText().toString().trim();

        if(!TextUtils.isEmpty(name)) {
            String id = databaseReference.push().getKey();
           // String id1 = "23";
            User newUser = new User(name,user_name,id);
            //databaseReference.child(id).setValue(newUser);
            databaseReference.setValue(newUser);

        }else {
            Toast.makeText(this, "Please enter values", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == logout_button){
            firebaseAuth.signOut();      //Sign out user
            finish();                      // close/end activity
            startActivity(new Intent(this, LoginActivity.class));   // start login activity
        }

        if (v == saveButton){
            saveUserInformation();
            finish();
            startActivity(new Intent(this, TournamentActivity.class));
        }
    }
}
