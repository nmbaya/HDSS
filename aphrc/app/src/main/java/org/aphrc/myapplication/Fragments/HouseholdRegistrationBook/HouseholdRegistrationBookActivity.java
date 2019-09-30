package org.aphrc.myapplication.Fragments.HouseholdRegistrationBook;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.ReturnMessage;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.Model.HouseholdRegistrationBook;
import org.aphrc.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class HouseholdRegistrationBookActivity extends AppCompatActivity {


    private EditText CreatedAt;
    private EditText CreatedBy;
    private EditText Name;
    private Button Save;
    private Button Update;

    private TextView tvMessage;

    DataBaseHelper databaseHelper;
    GradientDrawable gd;
    GradientDrawable gd1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_household_registration_book);

        tvMessage = findViewById(R.id.tvMessage);
        CreatedAt = findViewById(R.id.etCreatedAt);
        CreatedBy = findViewById(R.id.etCreatedBy);
        Name = findViewById(R.id.etName);

        Save = findViewById(R.id.addHRB);
        Update= findViewById(R.id.updateHRB);

        gd = new GradientDrawable();
        gd.setColor(Color.parseColor("#ffffffff"));
        gd.setStroke(1, Color.BLACK);

        gd1 = new GradientDrawable();
        gd1.setColor(Color.parseColor("#B3B6B7"));
        gd1.setStroke(1, Color.BLACK);


        GlobalClass.hrbID= UUID.randomUUID().toString();
        databaseHelper = new DataBaseHelper(this);

        Name.setFilters(new InputFilter[]{new InputFilter.AllCaps()});

        Intent i = getIntent();
        String omode =i.getStringExtra("mode");
        String  oid  = i.getStringExtra("oid");
        String  staff  = i.getStringExtra("staff");
        String  date  = i.getStringExtra("date");
        String  hrb  = i.getStringExtra("hrb");


        if (omode.equals("U")){

            CreatedAt.setText(date);
            CreatedBy.setText(databaseHelper.getStaff(staff));
            Name.setText(hrb);
            GlobalClass.hrbID=oid;
            Save.setEnabled(false);
            Update.setEnabled(true);

        }else {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            CreatedAt.setText(df.format(Calendar.getInstance().getTime()));

            CreatedAt.setText(df.format(Calendar.getInstance().getTime()));
            CreatedBy.setText(GlobalClass.stfName);
            //Name.setText(GlobalClass.strNum);
            GlobalClass.hrbID=UUID.randomUUID().toString();

            Save.setEnabled(true);
            Update.setEnabled(false);


        }

        CreatedAt.setEnabled(false);
        CreatedAt.setBackground(gd1);
        CreatedBy.setEnabled(false);
        CreatedBy.setBackground(gd1);
        Name.setBackground(gd);

    }


    public void addHRB(View view) {


        HouseholdRegistrationBook hrb = new HouseholdRegistrationBook();
        hrb.setHrb_oid(GlobalClass.hrbID);
        hrb.setHrb_CreatedAt(CreatedAt.getText().toString());
        hrb.setHrb_Createdby(GlobalClass.stfID);
        hrb.setHrb_Name(Name.getText().toString());
        try {
          //  ReturnMessage rm = databaseHelper.getHouseholdRegistrationBookDAO().addHouseholdRegistrationBook(hrb);
            tvMessage.setText(ReturnMessage.message);
            if (ReturnMessage.success) {
                tvMessage.setTextColor(Color.BLUE);
                //adapter.changeCursor(databaseHelper.getRoundDAO().getRounds(new Round()));
                this.finish();

            } else {
                tvMessage.setTextColor(Color.RED);
            }
        }catch (Exception e){
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();
        }
    }


    public void updateHRB(View view) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
      //  CreatedAt.setText(df.format(Calendar.getInstance().getTime()));
        HouseholdRegistrationBook hrb = new HouseholdRegistrationBook();
        hrb.setHrb_oid(GlobalClass.hrbID);
        hrb.setHrb_Name(Name.getText().toString());
        hrb.setHrb_EditedAt(df.format(Calendar.getInstance().getTime()));
        hrb.setHrb_EditedBy(GlobalClass.stfID);
        try {
     //       ReturnMessage rm = databaseHelper.getHouseholdRegistrationBookDAO().updateHouseholdRegistrationBook(hrb);
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



}
