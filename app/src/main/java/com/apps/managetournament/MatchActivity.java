package com.apps.managetournament;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.sql.Ref;
import java.util.HashMap;
import java.util.Map;

public class MatchActivity extends AppCompatActivity {
    private static final String TAG = "MatchActivity";

    private static final String TEAM1_NAME = "HOME TEAM";
    private static final String TEAM2_NAME = "AWAY TEAM";

    private EditText home_team;
    private EditText away_team;
    private Button save;

    private FirebaseFirestore db1 = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        home_team = findViewById(R.id.team1);
        away_team = findViewById(R.id.team2);
        save = findViewById(R.id.buttonSave);



    }

    public void saveMatch(View view) {
        String homeTeam = home_team.getText().toString();
        String awayTeam = away_team.getText().toString();
        Toast.makeText(MatchActivity.this, "Match Created "+"\n"+"Home team:  "+homeTeam + "\n"+
                "Away team:  " + awayTeam, Toast.LENGTH_LONG).show();




    }
}
