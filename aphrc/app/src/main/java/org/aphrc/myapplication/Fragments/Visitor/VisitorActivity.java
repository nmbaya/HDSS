package org.aphrc.myapplication.Fragments.Visitor;

import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.ReturnMessage;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.Model.Individual;
import org.aphrc.myapplication.Model.Membership;
import org.aphrc.myapplication.Model.Residency;
import org.aphrc.myapplication.Model.Visitor;
import  org.aphrc.myapplication.R;
import org.aphrc.myapplication.Utilities.Search.SimpleArrayListAdapter;
import org.aphrc.myapplication.Utilities.Search.SimpleListAdapter;
import org.aphrc.myapplication.Utilities.SearchSpinner.SearchableSpinner;
import org.aphrc.myapplication.Utilities.SearchSpinner.interfaces.IStatusListener;
import org.aphrc.myapplication.Utilities.SearchSpinner.interfaces.OnItemSelectedListener;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class VisitorActivity extends AppCompatActivity {

    public EditText  vis_CreatedAt;
    public EditText  vis_CreatedBy;
    public Spinner  vis_Status;
    public EditText  vis_IndividualID;
    public EditText vis_StatusDate;
    public Spinner  vis_MovedWith;
    public Spinner  vis_MovedTo;
    public Spinner  vis_rlthhh;

    private SearchableSpinner vis_Room;
    private SimpleListAdapter mSimpleListAdapter;
    private SimpleArrayListAdapter mSimpleArrayListAdapter;
    private final ArrayList<String> mRooms = new ArrayList<>();

    private SearchableSpinner vis_Socialgroup;
    private SimpleListAdapter mSimpleListAdapter1;
    private SimpleArrayListAdapter mSimpleArrayListAdapter1;
    private final ArrayList<String> mSocialgroup = new ArrayList<>();

    private Button Save;
    private Button Update;

    private TextView tvMessage;

    DataBaseHelper databaseHelper;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor);

        databaseHelper = new DataBaseHelper(this);
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.parseColor("#ffffffff"));
        gd.setStroke(1,Color.BLACK);

        GradientDrawable gd1 = new GradientDrawable();
        gd1.setColor(Color.parseColor("#B3B6B7"));
        gd1.setStroke(1,Color.BLACK);

        tvMessage = findViewById(R.id.tvMessage);

        Save = findViewById(R.id.addVIS);
        Update= findViewById(R.id.updateVIS);
        vis_CreatedBy = findViewById(R.id.vis_Staff);
        vis_CreatedBy.setBackground(gd1);
        vis_CreatedAt = findViewById(R.id.vis_Date);
        vis_CreatedAt.setBackground(gd1);
        vis_IndividualID = findViewById(R.id.vis_IndividualID);
        vis_IndividualID.setBackground(gd1);

        vis_Status = findViewById(R.id.vis_Status);
        vis_StatusDate = findViewById(R.id.vis_StatusDate);
        vis_MovedWith = findViewById(R.id.vis_MovedWith);
        vis_MovedTo = findViewById(R.id.vis_MovedTo);
        vis_rlthhh = findViewById(R.id.vis_rltHHH);

        databaseHelper = new DataBaseHelper(this);
        loadSpinnerData();
        loadSocialgroupsData();

        //===========================
        mSimpleListAdapter = new SimpleListAdapter(this, mRooms);
        mSimpleArrayListAdapter = new SimpleArrayListAdapter(this, mRooms);

        vis_Room = (SearchableSpinner) findViewById(R.id.vis_Room);
        vis_Room.setAdapter(mSimpleArrayListAdapter);
        vis_Room.setOnItemSelectedListener(mOnItemSelectedListener);
        vis_Room.setBackground(gd);
        vis_Room.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {

            }

            @Override
            public void spinnerIsClosing() {


            }
        });

        //===========================
        mSimpleListAdapter1 = new SimpleListAdapter(this, mSocialgroup);
        mSimpleArrayListAdapter1 = new SimpleArrayListAdapter(this, mSocialgroup);

        vis_Socialgroup = (SearchableSpinner) findViewById(R.id.vis_Socialgroup);
        vis_Socialgroup.setAdapter(mSimpleArrayListAdapter);
        vis_Socialgroup.setOnItemSelectedListener(mOnItemSelectedListener);
        vis_Socialgroup.setBackground(gd);
        vis_Socialgroup.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {

            }

            @Override
            public void spinnerIsClosing() {


            }
        });


        vis_Status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                int Hold = vis_Status.getSelectedItemPosition() ;

                if ((Hold==1) |(Hold==3)) {
                    vis_StatusDate.setEnabled(false);
                    vis_StatusDate.setBackgroundColor(Color.LTGRAY);
                    vis_MovedTo.setEnabled(false);
                    vis_MovedTo.setBackgroundColor(Color.LTGRAY);
                    vis_MovedWith.setEnabled(false);
                    vis_MovedWith.setBackgroundColor(Color.LTGRAY);
                    vis_rlthhh.setEnabled(false);
                    vis_rlthhh.setBackgroundColor(Color.LTGRAY);
                    vis_Room.setVisibility(View.INVISIBLE);
                    vis_Socialgroup.setVisibility(View.INVISIBLE);
                }else if (Hold==2){
                    vis_StatusDate.setEnabled(true);
                    vis_StatusDate.setBackgroundColor(Color.WHITE);
                    vis_MovedTo.setEnabled(false);
                    vis_MovedTo.setBackgroundColor(Color.LTGRAY);
                    vis_MovedWith.setEnabled(false);
                    vis_MovedWith.setBackgroundColor(Color.LTGRAY);
                    vis_rlthhh.setEnabled(false);
                    vis_rlthhh.setBackgroundColor(Color.LTGRAY);
                    vis_Room.setVisibility(View.INVISIBLE);
                    vis_Socialgroup.setVisibility(View.INVISIBLE);
                }else if (Hold==4){
                    vis_StatusDate.setEnabled(true);
                    vis_StatusDate.setBackgroundColor(Color.WHITE);
                    vis_MovedTo.setEnabled(true);
                    vis_MovedTo.setBackgroundColor(Color.WHITE);
                    vis_MovedWith.setEnabled(true);
                    vis_MovedWith.setBackgroundColor(Color.WHITE);
                    vis_rlthhh.setEnabled(true);
                    vis_rlthhh.setBackgroundColor(Color.WHITE);
                    vis_Room.setVisibility(View.VISIBLE);
                    vis_Socialgroup.setVisibility(View.VISIBLE);
                }else{

                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        vis_MovedTo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                int Hold = vis_MovedTo.getSelectedItemPosition() ;

                if (Hold==1) {
                   // vis_Room.setVisibility(View.VISIBLE);
                    vis_Socialgroup.setVisibility(View.INVISIBLE);
                }else{
                    vis_Socialgroup.setVisibility(View.VISIBLE);
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        //loadStructureData();
        Intent i = getIntent();
        String omode =i.getStringExtra("mode");

        if (omode.equals("U")){

           /* rm_Time.setText(time);
            rm_Date.setText(date);
            rm_createdby.setText(databaseHelper.getStaff(createdby));
            rm_structure.setText(databaseHelper.getStructure(structure));
            rm_number.setText(number);
            rm_roomID.setText(roomID);
            Save.setEnabled(false);
            Update.setEnabled(true);*/

            Save.setEnabled(false);
            Update.setEnabled(true);

        }else {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            vis_CreatedAt.setText(df.format(Calendar.getInstance().getTime()));
            vis_CreatedBy.setText(GlobalClass.stfName);
            vis_IndividualID.setText(GlobalClass.IndividualID +"("+GlobalClass.IndividualName+")");
            GlobalClass.visID= UUID.randomUUID().toString();

            Save.setEnabled(true);
            Update.setEnabled(false);


        }

        vis_CreatedAt.setEnabled(false);
        vis_CreatedBy.setEnabled(false);
        vis_IndividualID.setEnabled(false);
        vis_Status.setBackground(gd);
        vis_StatusDate.setBackground(gd);
        vis_MovedTo.setBackground(gd);
        vis_MovedWith.setBackground(gd);
        vis_rlthhh.setBackground(gd);
    }


    public void addVIS(View view) {

        Visitor vis = new Visitor();
        //str.setStr_ID(-1);
        vis.setVis_oid(GlobalClass.visID);
        vis.setVis_CreatedAt(vis_CreatedAt.getText().toString());
        vis.setVis_Createdby(GlobalClass.stfID);
        vis.setVis_IndividualID(databaseHelper.getIndividualID(GlobalClass.IndividualID));
        vis.setVis_Status(vis_Status.getSelectedItemPosition());
        vis.setVis_StatusDate(vis_StatusDate.getText().toString());
        vis.setVis_MovedTo(vis_MovedTo.getSelectedItemPosition());
        vis.setVis_MovedWith(vis_MovedWith.getSelectedItemPosition());
        vis.setVis_rlthhh(vis_rlthhh.getSelectedItemPosition());

        if ((vis_Room.getSelectedItem()==null)){
            vis.setVis_Room("");
        }else{
            vis.setVis_Room(databaseHelper.getRoomID(vis_Room.getSelectedItem().toString()));
        }

        if ((vis_Socialgroup.getSelectedItem()==null)){
            vis.setVis_Socialgroup("");
        }else{
            vis.setVis_Socialgroup(databaseHelper.getSocialgroupID(vis_Socialgroup.getSelectedItem().toString()));
        }



        try {
        //    ReturnMessage rm = databaseHelper.getVisitorDAO().addVisitor(vis);
            tvMessage.setText(ReturnMessage.message);
            if (ReturnMessage.success) {
                tvMessage.setTextColor(Color.BLUE);
                //adapter.changeCursor(databaseHelper.getStructureDAO().getStructures(new Structure()));
                DeleteVIS();
                this.finish();
            } else {
                tvMessage.setTextColor(Color.RED);
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG);
        }

    }

    public void DeleteVIS() {

        Visitor vis = new Visitor();
        vis.setVis_IndividualID(GlobalClass.indID);

        Individual ind = new Individual();
        ind.setInd_oid(GlobalClass.indID);

        Residency res = new Residency();
        res.setRes_Individual(GlobalClass.indID);
        res.setRes_Room(databaseHelper.getRoomID(vis_Room.getSelectedItem().toString()));

        Membership mem = new Membership();
        mem.setMem_Individual(GlobalClass.indID);




}


    public void updateVIS(View view) {

        Visitor vis = new Visitor();
        vis.setVis_oid(GlobalClass.visID);
        vis.setVis_Status(vis_Status.getSelectedItemPosition());
        vis.setVis_StatusDate(vis_StatusDate.getText().toString());
        vis.setVis_MovedTo(vis_MovedTo.getSelectedItemPosition());
        vis.setVis_MovedWith(vis_MovedWith.getSelectedItemPosition());
        vis.setVis_rlthhh(vis_rlthhh.getSelectedItemPosition());
        vis.setVis_Room(databaseHelper.getRoomID(vis_Room.getSelectedItem().toString()));
        vis.setVis_Socialgroup(databaseHelper.getSocialgroupID(vis_Socialgroup.getSelectedItem().toString()));

        try {
          //  ReturnMessage rm = databaseHelper.getVisitorDAO().updateVisitor(vis);
            tvMessage.setText(ReturnMessage.message);
            if (ReturnMessage.success) {
                tvMessage.setTextColor(Color.BLUE);
                this.finish();
            } else {
                tvMessage.setTextColor(Color.RED);
            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }

    }






    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
        List<String> labels = db.getAllRooms();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        Iterator itr=labels.iterator();
        while(itr.hasNext()){
            mRooms.add(itr.next().toString());
        }

    }


    private void loadSocialgroupsData() {
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
        List<String> labels = db.getAllHouseholds();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        Iterator itr=labels.iterator();
        while(itr.hasNext()){
            mSocialgroup.add(itr.next().toString());
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
        if (!vis_Room.isInsideSearchEditText(event)) {
            vis_Room.hideEdit();
        }

        if (!vis_Socialgroup.isInsideSearchEditText(event)) {
            vis_Socialgroup.hideEdit();
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
            vis_Room.setSelectedItem(0);
            vis_Socialgroup.setSelectedItem(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //==========================

}
