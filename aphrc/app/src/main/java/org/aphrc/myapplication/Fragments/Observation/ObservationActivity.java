package org.aphrc.myapplication.Fragments.Observation;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputFilter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.ReturnMessage;
import org.aphrc.myapplication.Helpers.ValidationHelper;
import org.aphrc.myapplication.Model.Observation;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.R;

import org.aphrc.myapplication.Utilities.Search.SimpleArrayListAdapter;
import org.aphrc.myapplication.Utilities.Search.SimpleListAdapter;
import org.aphrc.myapplication.Utilities.SearchSpinner.SearchableSpinner;
import org.aphrc.myapplication.Utilities.SearchSpinner.interfaces.IStatusListener;
import org.aphrc.myapplication.Utilities.SearchSpinner.interfaces.OnItemSelectedListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;


public class ObservationActivity extends AppCompatActivity  implements LocationListener {

    private final AppCompatActivity activity = ObservationActivity.this;

    private EditText obs_CreatedAt;
    private EditText obs_Createdby;
    private EditText obs_Round;
    private EditText obs_Room;
    private EditText obs_ObservationID;
   // private EditText obs_RespondentID;
    private EditText obs_RespondentName;
    private Spinner obs_RoomUsage;
    private EditText obs_NumberOfVisits;
    private Spinner obs_Result;
    private EditText obs_ResultOther;
    private EditText obs_Comments;
    private EditText obs_longitude;
    private EditText obs_Latitude;

    private Button Save;
    private Button Update;
    private Button LocationBtn;


    private SearchableSpinner obs_RespondentID;
    private SimpleListAdapter mSimpleListAdapter;
    private SimpleArrayListAdapter mSimpleArrayListAdapter;
    private final ArrayList<String> mIndividuals = new ArrayList<>();


    private TextView tvMessage;
    LocationManager locationManager;

    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation);

        tvMessage = findViewById(R.id.tvMessage);

        databaseHelper = new DataBaseHelper(this);
        databaseHelper.getRoundNumber();


        obs_Createdby = findViewById(R.id.obs_Staff);
        obs_Createdby.setText(GlobalClass.stfName);
        obs_Createdby.setBackgroundColor(Color.LTGRAY);
        obs_Createdby.setEnabled(false);

        obs_CreatedAt = findViewById(R.id.obs_Date);
        obs_CreatedAt.setBackgroundColor(Color.LTGRAY);
        obs_CreatedAt.setEnabled(false);

        obs_Round = findViewById(R.id.Obs_Round);
        obs_Round.setText(GlobalClass.roundNumber.toString());
        obs_Round.setBackgroundColor(Color.LTGRAY);
        obs_Round.setEnabled(false);

        obs_ObservationID = findViewById(R.id.Obs_ObsID);
        obs_ObservationID.setBackgroundColor(Color.LTGRAY);

        obs_Room = findViewById(R.id.Obs_Room);
        obs_Room.setBackgroundColor(Color.LTGRAY);

        obs_RespondentID = findViewById(R.id.Obs_ResID);
        obs_RespondentID.setBackgroundColor(Color.WHITE);
        obs_RespondentName = findViewById(R.id.Obs_Resname);
        obs_RespondentName.setBackgroundColor(Color.WHITE);
        obs_RoomUsage = findViewById(R.id.Obs_RoomUsage);
        obs_RoomUsage.setBackgroundColor(Color.WHITE);
        obs_NumberOfVisits = findViewById(R.id.Obs_VisitNo);
        obs_NumberOfVisits.setBackgroundColor(Color.WHITE);
        obs_Result = findViewById(R.id.Obs_Result);
        obs_Result.setBackgroundColor(Color.WHITE);
        obs_ResultOther = findViewById(R.id.Obs_ResultOther);
        obs_ResultOther.setBackgroundColor(Color.WHITE);
        obs_Comments = findViewById(R.id.Obs_Comments);
        obs_Comments.setBackgroundColor(Color.WHITE);

        obs_Latitude = findViewById(R.id.Latitude);
        obs_Latitude.setBackgroundColor(Color.LTGRAY);
        obs_longitude = findViewById(R.id.longitude);
        obs_longitude.setBackgroundColor(Color.LTGRAY);

        LocationBtn = (Button)findViewById(R.id.Location);
        LocationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
            }
        });

        Save = findViewById(R.id.addOBS);
        Update= findViewById(R.id.updateOBS);
        obs_RespondentName.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        Intent i = getIntent();
        String omode = i.getStringExtra("mode");
        String oid = i.getStringExtra("oid");
        String time = i.getStringExtra("time");
        String date = i.getStringExtra("date");
        String createdby = i.getStringExtra("createdby");
        String room = i.getStringExtra("room");
        String round = i.getStringExtra("round");
        String observationID = i.getStringExtra("observationID");
        String respondentID = i.getStringExtra("respondentID");
        String respondentName = i.getStringExtra("respondentName");
        String roomUsage = i.getStringExtra("roomUsage");
        String numberOfVisits = i.getStringExtra("numberOfVisits");
        String result = i.getStringExtra("result");
        String resultOther = i.getStringExtra("resultOther");
        String comments = i.getStringExtra("comments");


        //===========================
        mSimpleListAdapter = new SimpleListAdapter(this, mIndividuals);
        mSimpleArrayListAdapter = new SimpleArrayListAdapter(this, mIndividuals);

        obs_RespondentID = (SearchableSpinner) findViewById(R.id.Obs_ResID);
        obs_RespondentID.setAdapter(mSimpleArrayListAdapter);
        obs_RespondentID.setOnItemSelectedListener(mOnItemSelectedListener);
        obs_RespondentID.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {

            }

            @Override
            public void spinnerIsClosing() {

                if (obs_RespondentID.getSelectedPosition()!=0){
                    databaseHelper.getIndividualID(obs_RespondentID.getSelectedItem().toString());
                    obs_RespondentName.setText(GlobalClass.IndividualName);
                    obs_RespondentName.setEnabled(false);
                }else{
                    obs_RespondentName.setText("");
                    obs_RespondentName.setEnabled(true);
                }

            }
        });


        obs_Result.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                int Hold = obs_Result.getSelectedItemPosition();
                if (Hold == 7) {
                    obs_ResultOther.setEnabled(true);
                    obs_ResultOther.setBackgroundColor(Color.WHITE);
                } else {
                    obs_ResultOther.setEnabled(false);
                    obs_ResultOther.setBackgroundColor(Color.LTGRAY);
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        if (omode.equals("U")) {

            obs_CreatedAt.setText(date);
            obs_Createdby.setText(databaseHelper.getStaff(createdby));
            obs_Room.setText(databaseHelper.getRoom(room));
            obs_Round.setText(databaseHelper.getRound(round).toString());
            obs_ObservationID.setText(observationID);
            obs_RespondentID.setSelectedItem(respondentID);
            obs_RespondentName.setText(respondentName);
            obs_RoomUsage.setSelection(Integer.parseInt(roomUsage));
            obs_NumberOfVisits.setText(numberOfVisits);
            obs_Result.setSelection(Integer.parseInt(result));
            obs_ResultOther.setText(resultOther);
            obs_Comments.setText(comments);

            Save.setEnabled(false);
            Update.setEnabled(true);

        } else {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            obs_CreatedAt.setText(df.format(Calendar.getInstance().getTime()));

            obs_Createdby.setText(GlobalClass.stfName);
            obs_Room.setText(GlobalClass.roomID);
            GlobalClass.obsID = UUID.randomUUID().toString();
            obs_ObservationID.setText(GlobalClass.roomID+GlobalClass.roundNumber);
            obs_RespondentName.setAllCaps(true);

            Save.setEnabled(true);
            Update.setEnabled(false);

        }

        obs_CreatedAt.setEnabled(false);
        obs_Createdby.setEnabled(false);
        obs_Room.setEnabled(false);
        obs_ObservationID.setEnabled(false);

        loadSpinnerIndividualData();

    }




    private int getIndex(Spinner spinner, String myString){
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                return i;
            }
        }

        return 0;
    }

    public void addOBS(View view) {
        Observation obs = new Observation();
        if( verifyInput()){

        obs.setObs_ID(GlobalClass.obsID);
        obs.setObs_CreatedAt(obs_CreatedAt.getText().toString());
        obs.setObs_Createdby(GlobalClass.stfID);
        obs.setObs_ObservationID(obs_ObservationID.getText().toString());
        obs.setObs_Room(databaseHelper.getRoomID(obs_Room.getText().toString()));
        obs.setObs_RoomUsage(obs_RoomUsage.getSelectedItemPosition());
        obs.setObs_Round(databaseHelper.getRoundID(Integer.parseInt(obs_Round.getText().toString())));
        obs.setObs_NumberOfVisits(Integer.parseInt(obs_NumberOfVisits.getText().toString()));
        obs.setObs_Result(obs_Result.getSelectedItemPosition());
        obs.setObs_ResultOther(obs_ResultOther.getText().toString());
            if ((obs_RespondentID.getSelectedItem()==null)){
                obs.setObs_RespondentID("");
            }else{
                obs.setObs_RespondentID(obs_RespondentID.getSelectedItem().toString());
            }
        //obs.setObs_RespondentID(obs_RespondentID.getSelectedItem().toString());
        obs.setObs_RespondentName(obs_RespondentName.getText().toString());
        obs.setObs_Comments(obs_Comments.getText().toString());
        obs.setObs_Latitude(obs_Latitude.getText().toString());
        obs.setObs_longitude(obs_longitude.getText().toString());


        try {
           // ReturnMessage rm = databaseHelper.getObservationDAO().addOBS(obs);
            tvMessage.setText(ReturnMessage.message);
            if (ReturnMessage.success) {
                tvMessage.setTextColor(Color.BLUE);
               // adapter.changeCursor(databaseHelper.getObservationDAO().getObservations(new Observation()));
                this.finish();
            } else {
                tvMessage.setTextColor(Color.RED);
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG);
        } }else

        {
            // Build an AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Set a title for alert dialog
            builder.setTitle("VALIDATION CHECKS.");
            // Ask the final question
            builder.setMessage("Please ensure all the field are filled properly");

            // Set the alert dialog no button click listener
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when No button clicked

                }
            });

            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();


        }

    }


    public void updateOBS(View view) {
        Observation obs = new Observation();
        if( verifyInput()){
        obs.setObs_ID(GlobalClass.obsID);

        obs.setObs_ObservationID(obs_ObservationID.getText().toString());
        obs.setObs_Room(databaseHelper.getRoomID(obs_Room.getText().toString()));
        obs.setObs_RoomUsage(obs_RoomUsage.getSelectedItemPosition());
        obs.setObs_Round(databaseHelper.getRoundID(Integer.parseInt(obs_Round.getText().toString())));
        obs.setObs_NumberOfVisits(Integer.parseInt(obs_NumberOfVisits.getText().toString()));
        obs.setObs_Result(obs_Result.getSelectedItemPosition());
        obs.setObs_ResultOther(obs_ResultOther.getText().toString());
            if ((obs_RespondentID.getSelectedItem()==null)){
                obs.setObs_RespondentID("");
            }else{
                obs.setObs_RespondentID(obs_RespondentID.getSelectedItem().toString());
            }
       // obs.setObs_RespondentID(obs_RespondentID.getSelectedItem().toString());
        obs.setObs_RespondentName(obs_RespondentName.getText().toString());
        obs.setObs_Comments(obs_Comments.getText().toString());

            try {
              //  ReturnMessage rm = databaseHelper.getObservationDAO().updateOBS(obs);
                tvMessage.setText(ReturnMessage.message);
                if (ReturnMessage.success) {
                    tvMessage.setTextColor(Color.BLUE);
                   // adapter.changeCursor(databaseHelper.getObservationDAO().getObservations(new Observation()));
                    this.finish();
                    GlobalClass.observationID=null;
                } else {
                    tvMessage.setTextColor(Color.RED);
                }
            }catch(Exception e){
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            }}else

        {
            // Build an AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Set a title for alert dialog
            builder.setTitle("VALIDATION CHECKS.");
            // Ask the final question
            builder.setMessage("Please ensure all the field are filled properly");

            // Set the alert dialog no button click listener
            builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Do something when No button clicked

                }
            });

            AlertDialog dialog = builder.create();
            // Display the alert dialog on interface
            dialog.show();


        }

        }

    private boolean verifyInput() {

        boolean isValid=true;
        if( obs_ObservationID.getText().toString().length() == 0 ){
            obs_ObservationID.setError( "Observation ID is Required!" );

            isValid=false;}
       // if( obs_RespondentID.getSelectedItem().toString().length() == 0 ){
          //  obs_RespondentID.setError( "Respondent ID is Required!" );

          //  isValid=false;}

        if( obs_RespondentName.getText().toString().length() == 0 ){
            obs_RespondentName.setError( "Respondent Name Required!" );

            isValid=false;}

        if( obs_RoomUsage.getSelectedItemPosition()==0){
            TextView errorText = (TextView)obs_RoomUsage.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Room usage Required");//changes the selected item text to this

            isValid=false;}

        if( obs_NumberOfVisits.getText().toString().length() == 0 ){
            obs_NumberOfVisits.setError( "Number Of Visits Required!" );

            isValid=false;}

        if( obs_Result.getSelectedItemPosition()==0){
            TextView errorText = (TextView)obs_Result.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(Color.RED);//just to highlight that this is an error
            errorText.setText("Result of Interview Required");//changes the selected item text to this

            isValid=false;}

        return isValid;
    }


    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerIndividualData() {
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
        List<String> labels = db.getAllIndividuals();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        Iterator itr=labels.iterator();
        while(itr.hasNext()){
            mIndividuals.add(itr.next().toString());
        }

    }


    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        //  Toast.makeText(parent.getContext(), "You selected: " + label,Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }


    //============================
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (!obs_RespondentID.isInsideSearchEditText(event)) {
            obs_RespondentID.hideEdit();
        }

        return super.onTouchEvent(event);
    }

    private OnItemSelectedListener mOnItemSelectedListener = new OnItemSelectedListener() {
        @Override
        public void onItemSelected(View view, int position, long id) {
            SimpleArrayListAdapter mSimpleListAdapter;
            //  Toast.makeText(activity_observation.this, "Item on position " + position + " : " + mSimpleListAdapter.getItem(position) + " Selected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected() {
            // Toast.makeText(activity_observation.this, "Nothing Selected", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
            obs_RespondentID.setSelectedItem(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
            Toast.makeText(ObservationActivity.this,e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        //locationText.setText("Current Location: " + location.getLatitude() + ", " + location.getLongitude());
        obs_Latitude.setText(String.valueOf(location.getLatitude()));
        obs_longitude.setText( String.valueOf(location.getLongitude()));
    }

    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(ObservationActivity.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

}

