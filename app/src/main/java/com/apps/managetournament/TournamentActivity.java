package com.apps.managetournament;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.SetOptions;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import javax.annotation.Nullable;


public class TournamentActivity extends AppCompatActivity {

    private static final String TAG = "Tournament Activity";
    private static final String TOURNAMENT_NAME = "Tournament name";
    private static final String TOURNAMENT_LOCATION = "Tournament location";


    EditText tournamentName, tournamentLocation;
    Button add_tournament, start_date,end_date;
    private DatePicker datePicker;
    private Calendar calendar;
   // private TextView dateView;
    private TextView retrieve_info;
    private int year, month, day;
    private String mstart_date;

   private FirebaseFirestore db = FirebaseFirestore.getInstance();
   private DocumentReference tournamentRef = db.document("tournament/my tournament");
    // can put the above variable as db.document(tournament/my tournament)
    // write the document and the path to the document

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tournament);


        tournamentName = (EditText) findViewById(R.id.tournament_name);
        tournamentLocation = (EditText) findViewById(R.id.tournament_location);
        add_tournament = (Button) findViewById(R.id.add_tournament);
        start_date = (Button) findViewById(R.id.select_start_date);
        end_date = (Button) findViewById(R.id.select_end_date);


        retrieve_info = (TextView) findViewById(R.id.info);  // to test if data is being retrieved

        calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);

        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);
        showDate(year, month + 1, day);
    }

    @Override
    protected void onStart() {
        super.onStart();
        tournamentRef.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                    Toast.makeText(TournamentActivity.this, "Error ", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, e.toString());
                    return;
                }
                if (documentSnapshot.exists()) {
                    Tournament tournament = documentSnapshot.toObject(Tournament.class);
                    // use getters method from the tournament java class
                    String name = tournament.getTournamentName();
                    String location = tournament.getTournamentLocation();
                    retrieve_info.setText("Name :  " + name + "\n" + "Location:   " + location);
                }else{
                    retrieve_info.setText("");
                }
            }
        });
    }

    @SuppressWarnings("deprecation")
    public void setDate(View view) {
        showDialog(999);
        Toast.makeText(getApplicationContext(), " Please select date",
                Toast.LENGTH_SHORT)
                .show();
    }

    @Deprecated
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
        if (id == 999) {
            return new DatePickerDialog(this,
                    myDateListener, year, month, day);
        }
        return null;
    }

    private DatePickerDialog.OnDateSetListener myDateListener = new
            DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker arg0,
                                      int arg1, int arg2, int arg3) {
                    // TODO Auto-generated method stub
                    // arg1 = year
                    // arg2 = month
                    // arg3 = day
                    showDate(arg1, arg2 + 1, arg3);

                }
            };

    private void showDate(int year, int month, int day) {
      ////  dateView.setText(new StringBuilder().append(day).append("/")
          //      .append(month).append("/").append(year));
        mstart_date = day + "/" + month + "/" + year;
    }


    public void newTournament(View view) {

        String tournament_name = tournamentName.getText().toString();
        String tournament_location = tournamentLocation.getText().toString();
        // String start_date = startDate.getText().toString().trim();
        //String end_date = endDate.getText().toString().trim();
        String type = " 11 aside";

        if (!TextUtils.isEmpty(tournament_name) & !TextUtils.isEmpty(tournament_location)) {
            //  String id = databaseReference.push().getKey();
            String id = "23";
            Tournament newTournament = new Tournament(tournament_name, tournament_location, mstart_date, mstart_date, type, id);
            tournamentRef.set(newTournament)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Toast.makeText(TournamentActivity.this, "You have created new tournament", Toast.LENGTH_LONG).show();
                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(TournamentActivity.this, "Error! Please try again", Toast.LENGTH_LONG).show();
                            Log.d(TAG, e.toString());
                        }
                    });
        }
    }

    public void update(View view){
        String tournamentLoc = tournamentLocation.getText().toString();

        tournamentRef.update(TOURNAMENT_LOCATION, tournamentLoc);
    }

    public void delete(View view){
       tournamentRef.update(TOURNAMENT_LOCATION, FieldValue.delete());
    }

    public void deleteTournament(View view){
        tournamentRef.delete();
    }
    public void viewTournaments(View view){
         tournamentRef.get()
                 .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                     @Override
                     public void onSuccess(DocumentSnapshot documentSnapshot) {
                        if (documentSnapshot.exists()){
                                Tournament tournament = documentSnapshot.toObject(Tournament.class);
                                // use getters method from the tournament java class
                                String name = tournament.getTournamentName();
                                String location = tournament.getTournamentLocation();

                                retrieve_info.setText("Name :  " + name + "\n" + "Location:  " + location);

                        }  else{
                            Toast.makeText(TournamentActivity.this, "Document does not exist", Toast.LENGTH_LONG).show();
                        }
                     }
                 })
                 .addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         Toast.makeText(TournamentActivity.this, "Error! Please try again", Toast.LENGTH_LONG).show();
                         Log.d(TAG, e.toString());
                     }
                 });
    }
}
