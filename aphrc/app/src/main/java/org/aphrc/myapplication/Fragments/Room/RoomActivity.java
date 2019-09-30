package org.aphrc.myapplication.Fragments.Room;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.ReturnMessage;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.Model.Room;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.Utilities.Search.SimpleArrayListAdapter;
import org.aphrc.myapplication.Utilities.Search.SimpleListAdapter;
import org.aphrc.myapplication.Utilities.SearchSpinner.SearchableSpinner;
import org.aphrc.myapplication.Utilities.SearchSpinner.interfaces.IStatusListener;
import org.aphrc.myapplication.Utilities.SearchSpinner.interfaces.OnItemSelectedListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class RoomActivity extends AppCompatActivity {

//implements AdapterView.OnItemSelectedListener
    private EditText rm_CreatedAt;
    private EditText rm_CreatedBy;
    public EditText rm_number;
    public EditText rm_roomID;
    public EditText rm_structure;
    private Button Save;
    private Button Update;

    private TextView tvMessage;

    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        databaseHelper = new DataBaseHelper(this);
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(Color.parseColor("#ffffffff"));
        gd.setStroke(1,Color.BLACK);

        GradientDrawable gd1 = new GradientDrawable();
        gd1.setColor(Color.parseColor("#B3B6B7"));
        gd1.setStroke(1,Color.BLACK);


        tvMessage = findViewById(R.id.tvMessage);

        rm_CreatedBy = findViewById(R.id.rm_staff);
        rm_CreatedBy.setBackground(gd1);

        rm_CreatedAt = findViewById(R.id.rm_date);
        rm_CreatedAt.setBackground(gd1);


        rm_structure = findViewById(R.id.rm_structure);
        rm_structure.setBackground(gd1);

        rm_number = findViewById(R.id.RoomNumber);
        Save = findViewById(R.id.addRoom);
        Update= findViewById(R.id.updateRoom);

        rm_number.setBackground(gd);

        rm_roomID = findViewById(R.id.room_ID);
        rm_roomID.setBackground(gd1);

        //loadStructureData();
        Intent i = getIntent();
        String omode =i.getStringExtra("mode");
        String  oid  = i.getStringExtra("oid");
        String  date  = i.getStringExtra("date");
        String  createdby  = i.getStringExtra("createdby");
        String  structure  = i.getStringExtra("structure");
        String  number  = i.getStringExtra("number");
        String  roomID  = i.getStringExtra("roomID");


        rm_number.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
            }

            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (!(count==0)) {
                   // if (count == 3) {

                        int x = Integer.parseInt(rm_number.getText().toString());
                       // rm_roomID.setText("");

                        if (x <= 9) {
                            rm_roomID.setText("");
                            rm_roomID.setText(rm_structure.getText().toString() + "00" + x);
                        } else if (x <= 99) {
                            rm_roomID.setText("");
                            rm_roomID.setText(rm_structure.getText().toString() + "0" + x);
                        } else if (x > 99){
                            rm_roomID.setText("");
                            rm_roomID.setText(rm_structure.getText().toString() + x);
                        }else{

                        }

                        rm_roomID.setEnabled(false);

                    } else {

                 //   rm_roomID.setText("");

                    }
             //   }
            }
        });


        if (omode.equals("U")){

            rm_CreatedAt.setText(date);
            rm_CreatedBy.setText(databaseHelper.getStaff(createdby));
            rm_structure.setText(databaseHelper.getStructure(structure));
            rm_number.setText(number);
            rm_roomID.setText(roomID);
            Save.setEnabled(false);
            Update.setEnabled(true);

        }else {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            rm_CreatedAt.setText(df.format(Calendar.getInstance().getTime()));
            rm_CreatedBy.setText(GlobalClass.stfName);
            rm_structure.setText(GlobalClass.strNum);
            GlobalClass.rooID=UUID.randomUUID().toString();

            Save.setEnabled(true);
            Update.setEnabled(false);


        }

        rm_CreatedAt.setEnabled(false);
        rm_CreatedBy.setEnabled(false);
        rm_structure.setEnabled(false);
        rm_roomID.setEnabled(false);

    }

    public void addRoom(View view) {

        if( verifyInput()){

        Room r = new Room();
        r.setRm_oid(GlobalClass.rooID);
        r.setRm_CreatedAt(rm_CreatedAt.getText().toString());
        r.setRm_CreatedBy(GlobalClass.stfID);
        r.setRm_Structure(databaseHelper.getStructureID(rm_structure.getText().toString()));
        r.setRm_Number(Integer.parseInt(rm_number.getText().toString()));
        r.setRm_roomID(rm_roomID.getText().toString());


        try {
            ReturnMessage rm = databaseHelper.getRoomDAO().addRoom(r);
            tvMessage.setText(ReturnMessage.message);
            if (ReturnMessage.success) {
                tvMessage.setTextColor(Color.BLUE);
                //adapter.changeCursor(databaseHelper.getRoomDAO().getRooms(new Room()));
                this.finish();

            } else {
                tvMessage.setTextColor(Color.RED);
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
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

    public void updateRoom(View view) {

        Room r = new Room();
        r.setRm_oid(GlobalClass.rooID);
        r.setRm_Structure(databaseHelper.getStructureID(rm_structure.getText().toString()));
        r.setRm_Number(Integer.parseInt(rm_number.getText().toString()));
        r.setRm_roomID(rm_roomID.getText().toString());

        try {
            ReturnMessage rm = databaseHelper.getRoomDAO().updateRoom(r);
            tvMessage.setText(ReturnMessage.message);
            if (ReturnMessage.success) {
                tvMessage.setTextColor(Color.BLUE);
                //adapter.changeCursor(databaseHelper.getRoomDAO().getRooms(new Room()));
                this.finish();

            } else {
                tvMessage.setTextColor(Color.RED);
            }
        }catch(Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }

    }


    private boolean verifyInput() {

        boolean isValid=true;
        if( rm_number.getText().toString().length() == 0 ){
            rm_number.setError( "Room number is required!" );

            isValid=false;}

        if (!( rm_number.getText().toString().length() == 3) ){
            rm_number.setError( "Must enter a 3 digits figure !" );

            isValid=false;}

        return isValid;
    }

}
