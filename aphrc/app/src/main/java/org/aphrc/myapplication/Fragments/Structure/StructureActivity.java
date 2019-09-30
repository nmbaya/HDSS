package org.aphrc.myapplication.Fragments.Structure;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.ReturnMessage;
import org.aphrc.myapplication.Database.StructureDAO;
import org.aphrc.myapplication.LocationObservation.tableview.TableViewAdapter;
import org.aphrc.myapplication.LocationObservation.tableview.TableViewListener;
import org.aphrc.myapplication.LocationObservation.tableview.TableViewModel;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.Model.Structure;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.Utilities.Calculate;
import org.aphrc.myapplication.Utilities.DateMask;
import org.aphrc.myapplication.Utilities.Search.SimpleArrayListAdapter;
import org.aphrc.myapplication.Utilities.Search.SimpleListAdapter;
import org.aphrc.myapplication.Utilities.SearchSpinner.SearchableSpinner;
import org.aphrc.myapplication.Utilities.SearchSpinner.interfaces.IStatusListener;
import org.aphrc.myapplication.Utilities.SearchSpinner.interfaces.OnItemSelectedListener;
import org.aphrc.myapplication.tableview.TableView;
import org.aphrc.myapplication.tableview.adapter.AbstractTableAdapter;
import org.aphrc.myapplication.tableview.filter.Filter;
import org.aphrc.myapplication.tableview.pagination.Pagination;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class StructureActivity extends AppCompatActivity {

    private EditText str_CreatedAt;
    private EditText str_CreatedBy;
    public EditText str_Number;
    public EditText str_StructureID;
    public EditText str_comment;

    private Button Save;
    private Button Update;

    private TextView tvMessage;

    DataBaseHelper databaseHelper;
    GradientDrawable gd;
    GradientDrawable gd1;

    private int ea;

    private StructureDAO structureDAO;
    Calculate calculate;

    private SearchableSpinner str_After;
    private SearchableSpinner str_Before;
    private SimpleListAdapter mSimpleListAdapter;
    private SimpleArrayListAdapter mSimpleArrayListAdapter;
    private final ArrayList<String> mStrings = new ArrayList<>();

    private SearchableSpinner str_EA;
    private SimpleListAdapter mSimpleListAdapter1;
    private SimpleArrayListAdapter mSimpleArrayListAdapter1;

    private SearchableSpinner str_site;
    private SimpleListAdapter mSimpleListAdapter2;
    private SimpleArrayListAdapter mSimpleArrayListAdapter2;

    private SearchableSpinner str_hrb;
    private SimpleListAdapter mSimpleListAdapter3;
    private SimpleArrayListAdapter mSimpleArrayListAdapter3;
    private final ArrayList<String> mHRB = new ArrayList<>();

    //=========================
    private AbstractTableAdapter mTableViewAdapter;
    private TableView mTableView;
    private Filter mTableFilter; // This is used for filtering the table.
    private Pagination mPagination; // This is used for paginating the table.

    // This is a sample class that provides the cell value objects and other configurations.
    private TableViewModel mTableViewModel;

    private boolean mPaginationEnabled = false;
    //=========================


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structure);

        gd = new GradientDrawable();
        gd.setColor(Color.parseColor("#ffffffff"));
        gd.setStroke(1, Color.BLACK);

        gd1 = new GradientDrawable();
        gd1.setColor(Color.parseColor("#B3B6B7"));
        gd1.setStroke(1, Color.BLACK);

        tvMessage = findViewById(R.id.tvMessage);

        str_CreatedBy = findViewById(R.id.str_STAFF);
        str_CreatedAt = findViewById(R.id.str_DATE);
        str_site = findViewById(R.id.str_site);
        str_site.setBackground(gd);
        str_EA = findViewById(R.id.str_EA);
        str_EA.setBackground(gd);
        str_Number = findViewById(R.id.str_Number);
        str_Number.setBackground(gd);
        str_StructureID = findViewById(R.id.str_structureID);
        str_StructureID.setBackground(gd);
        str_comment = findViewById(R.id.str_COM);
        str_comment.setBackground(gd);

        Save = findViewById(R.id.addSTR);
        Update= findViewById(R.id.updateStructure);


        databaseHelper = new DataBaseHelper(this);
        calculate = new Calculate(this);

        calculate.loadSite();
        loadSpinnerData();
        loadHRBData();

        Intent i = getIntent();
        String omode =i.getStringExtra("mode");
        String  oid  = i.getStringExtra("oid");
        String  date  = i.getStringExtra("date");
        String  createdby  = i.getStringExtra("createdby");
        String  site  = i.getStringExtra("site");
        String  ea  = i.getStringExtra("ea");
        String  number  = i.getStringExtra("number");
        String  structureID  = i.getStringExtra("structureID");
        String  before  = i.getStringExtra("before");
        String  after  = i.getStringExtra("after");
        String  hrb  = i.getStringExtra("hrb");
        String  comment  = i.getStringExtra("comment");


        //===========================
        mSimpleListAdapter = new SimpleListAdapter(this, mStrings);
        mSimpleArrayListAdapter = new SimpleArrayListAdapter(this, mStrings);

        str_Before = (SearchableSpinner) findViewById(R.id.str_Before);
        str_Before.setAdapter(mSimpleArrayListAdapter);
        str_Before.setOnItemSelectedListener(mOnItemSelectedListener);
        str_Before.setBackground(gd);
        str_Before.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {

            }

            @Override
            public void spinnerIsClosing() {


            }
        });



        //========================

        str_After = (SearchableSpinner) findViewById(R.id.str_After);
        str_After.setAdapter(mSimpleArrayListAdapter);
        str_After.setOnItemSelectedListener(mOnItemSelectedListener);
        str_After.setBackground(gd);
        str_After.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {

            }

            @Override
            public void spinnerIsClosing() {

            }
        });
        //========================

        //=====================str_HRB======
        mSimpleListAdapter3 = new SimpleListAdapter(this,mHRB );
        mSimpleArrayListAdapter3 = new SimpleArrayListAdapter(this, mHRB);

        str_hrb = (SearchableSpinner) findViewById(R.id.str_hrb);
        str_hrb.setAdapter(mSimpleArrayListAdapter3);
        str_hrb.setOnItemSelectedListener(mOnItemSelectedListener);
        str_hrb.setBackground(gd);
        str_hrb.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {

            }

            @Override
            public void spinnerIsClosing() {

            }
        });

        //=====================str_EA======
        mSimpleListAdapter = new SimpleListAdapter(this, calculate.mEA);
        mSimpleArrayListAdapter = new SimpleArrayListAdapter(this, calculate.mEA);

        str_EA = (SearchableSpinner) findViewById(R.id.str_EA);
        str_EA.setAdapter(mSimpleListAdapter);
        str_EA.setOnItemSelectedListener(mOnItemSelectedListener);
        str_EA.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {


            }

            @Override
            public void spinnerIsClosing() {


            }
        });

        //=====================str_site======
        mSimpleListAdapter = new SimpleListAdapter(this, calculate.mSite);
        mSimpleArrayListAdapter = new SimpleArrayListAdapter(this, calculate.mSite);

        str_site = (SearchableSpinner) findViewById(R.id.str_site);
        str_site.setAdapter(mSimpleListAdapter);
        str_site.setOnItemSelectedListener(mOnItemSelectedListener);
        str_site.setStatusListener(new IStatusListener() {
            @Override
            public void spinnerIsOpening() {

                calculate.mEA.clear();

            }

            @Override
            public void spinnerIsClosing() {
                str_EA.setSelectedItem(0);
                //calculate.mEA.clear();
                if (!(str_site.getSelectedItem()==null)){
               calculate.loadea(str_site.getSelectedItem().toString());
                }

            }
        });

        str_Number.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {            }
            public void beforeTextChanged(CharSequence s, int start,int count, int after) {            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!(str_EA.getSelectedItem() == null)) {
                    if (str_Number.getText().length() == 1) {
                        str_StructureID.setText(str_EA.getSelectedItem().toString() + "00" + str_Number.getText().toString());
                    } else if (str_Number.getText().length() == 2) {
                        str_StructureID.setText(str_EA.getSelectedItem().toString() + "0" + str_Number.getText().toString());
                    } else {
                        str_StructureID.setText(str_EA.getSelectedItem().toString() + str_Number.getText().toString());
                    }
                    str_StructureID.setEnabled(false);

                } else {

                }
            }
        });


        if (omode.equals("U")){

            str_CreatedAt.setText(date);
            str_CreatedBy.setText(databaseHelper.getStaff(createdby));

            if (site.equals("1")){
                str_site.setSelectedItem("Korogocho");
                calculate.loadea(site);
            }else if (site.equals("2")){
                str_site.setSelectedItem("Viwandani");
                calculate.loadea(site);
            }else{
                calculate.loadea(site);
            }

            str_EA.setSelectedItem(ea);
            str_Number.setText(number);
            str_StructureID.setText(structureID);
            str_Before.setSelectedItem(before);
            str_After.setSelectedItem(after);
            str_hrb.setSelectedItem(databaseHelper.getHRB(hrb));
            str_comment.setText(comment);

            Save.setEnabled(false);
            Update.setEnabled(true);

        }else {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            str_CreatedAt.setText(df.format(Calendar.getInstance().getTime()));
            str_CreatedBy.setText(GlobalClass.stfName);

            GlobalClass.strID=UUID.randomUUID().toString();

            Save.setEnabled(true);
            Update.setEnabled(false);

        }

        str_CreatedAt.setEnabled(false);
        str_CreatedAt.setBackground(gd1);
        str_CreatedBy.setEnabled(false);
        str_CreatedBy.setBackground(gd1);
        str_StructureID.setBackground(gd1);

    }

    public void addSTR(View view) {
        if( verifyInput()){
        Structure str = new Structure();
        str.setStr_oid(GlobalClass.strID);
        str.setStr_CreatedAt(str_CreatedAt.getText().toString());
        str.setStr_Createdby(GlobalClass.stfID);
        str.setStr_Site(str_site.getSelectedPosition());
        str.setStr_EA(str_EA.getSelectedItem().toString());
        str.setStr_Number(Integer.parseInt(str_Number.getText().toString()));
        str.setStr_StructureID(str_StructureID.getText().toString());
        str.setStr_Before(databaseHelper.getStructureID(str_Before.getSelectedItem().toString()));
        str.setStr_After(databaseHelper.getStructureID(str_After.getSelectedItem().toString()));
        str.setStr_hrb(databaseHelper.getHRBID(str_hrb.getSelectedItem().toString()));
        str.setStr_Comment(str_comment.getText().toString());

        try {
            ReturnMessage rm = databaseHelper.getStructureDAO().addSTR(str);
            tvMessage.setText(ReturnMessage.message);
            if (ReturnMessage.success) {
                tvMessage.setTextColor(Color.BLUE);
                //adapter.changeCursor(databaseHelper.getStructureDAO().getStructures(new Structure()));
                this.finish();
            } else {
                tvMessage.setTextColor(Color.RED);
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG);
        }

        }else

        {
            // Build an AlertDialog
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            // Set a title for alert dialog
            builder.setTitle("VALIDATION CHECKS.");
            // Ask the final question
            builder.setMessage("Check all Fields");

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

    public void updateStructure(View view) {

            Structure str = new Structure();
            str.setStr_oid(GlobalClass.strID);
            str.setStr_Site(str_site.getSelectedPosition());
            str.setStr_EA(str_EA.getSelectedItem().toString());
            str.setStr_Number(Integer.parseInt(str_Number.getText().toString()));
            str.setStr_StructureID(str_StructureID.getText().toString());
            str.setStr_Before(str_Before.getSelectedItem().toString());
            str.setStr_After(str_After.getSelectedItem().toString());
            str.setStr_hrb(databaseHelper.getHRBID(str_hrb.getSelectedItem().toString()));
            str.setStr_Comment(str_comment.getText().toString());
            try {
                ReturnMessage rm = databaseHelper.getStructureDAO().updateStructure(str);
                tvMessage.setText(ReturnMessage.message);
                if (ReturnMessage.success) {
                    tvMessage.setTextColor(Color.BLUE);
                   // adapter.changeCursor(databaseHelper.getStructureDAO().getStructures(new Structure()));
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
        List<String> labels = db.getAllStructures();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        Iterator itr=labels.iterator();
        while(itr.hasNext()){
            mStrings.add(itr.next().toString());
        }

    }


    private void loadHRBData() {
        DataBaseHelper db = new DataBaseHelper(getApplicationContext());
        List<String> labels = db.getAllHrbs();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, labels);

        Iterator itr=labels.iterator();
        while(itr.hasNext()){
            mHRB.add(itr.next().toString());
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
        if (!str_Before.isInsideSearchEditText(event)) {
            str_Before.hideEdit();
        }

        if (!str_After.isInsideSearchEditText(event)) {
            str_After.hideEdit();
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
            str_Before.setSelectedItem(0);
            str_After.setSelectedItem(0);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    //==========================


    private boolean verifyInput() {

        boolean isValid=true;
        if( str_Number.getText().toString().length() == 0 ){
            str_Number.setError( "Structure number is required!" );

            isValid=false;}

        if (!( str_Number.getText().toString().length() == 3) ){
            str_Number.setError( "Must enter a 3 digits figure !" );

            isValid=false;}

        if( str_StructureID.getText().toString().length() == 0 ){
            str_StructureID.setError( "Structure number is required!" );

            isValid=false;}

        if (!( str_StructureID.getText().toString().length() == 7) ){
            str_StructureID.setError( "Must be a 7 digits figure !" );

            isValid=false;}

        return isValid;
    }

}
