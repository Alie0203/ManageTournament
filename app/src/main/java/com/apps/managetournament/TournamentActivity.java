package com.apps.managetournament;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TournamentActivity extends AppCompatActivity implements View.OnClickListener {


    EditText tournamentName,tournamentLocation,editText, editText2;
    Button submit,buttonLogout;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;
    private FloatingActionButton mFloatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);
        databaseReference = FirebaseDatabase.getInstance().getReference("Tournament");

        tournamentName = (EditText) findViewById(R.id.login_password);
        tournamentLocation= (EditText) findViewById(R.id.login_password);
        editText = (EditText) findViewById(R.id.login_password);
        editText2 = (EditText) findViewById(R.id.login_password);
        submit = (Button) findViewById(R.id.login_sign_in_button);
        buttonLogout = (Button) findViewById(R.id.login_sign_in_button);





        submit.setOnClickListener(this);
        buttonLogout.setOnClickListener(this);
    }



    private void saveUserInformation(){

        String Tournamentname = tournamentName.getText().toString().trim();
        String Location = tournamentLocation.getText().toString().trim();
        String startdate = editText.getText().toString().trim();
        String enddate = editText2.getText().toString().trim();
        String type = " 11 aside";

        if(!TextUtils.isEmpty(Tournamentname)&!TextUtils.isEmpty(Location) & (!TextUtils.isEmpty(startdate))&(!TextUtils.isEmpty(enddate))){
            String id = databaseReference.push().getKey();
            // String id1 = "23";
            Tournament newTournament = new Tournament(Tournamentname,Location,startdate,enddate,type,id);
            //databaseReference.child(id).setValue(newUser);
            databaseReference.setValue(newTournament);

        }else {
            Toast.makeText(this, "Please enter values", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onClick(View v) {
        if (v == buttonLogout){
            //firebaseAuth.signOut();      //Sign out user
            finish();                      // close/end activity
            startActivity(new Intent(this, LoginActivity.class));   // start login activity
        }

        if (v == submit){
            saveUserInformation();
            finish();
            startActivity(new Intent(this, userActivity.class));
        }
    }
}
