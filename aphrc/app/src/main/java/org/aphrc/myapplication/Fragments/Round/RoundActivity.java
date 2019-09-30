package org.aphrc.myapplication.Fragments.Round;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.ReturnMessage;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.Model.Round;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.Utilities.DateMask;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

public class RoundActivity extends AppCompatActivity {

    private EditText rnd_CreatedAt;
    private EditText rnd_CreatedBy;
    private EditText rnd_RoundNumber;
    private EditText rnd_StartDate;
    private EditText rnd_EndDate;
    private EditText rnd_Remarks;

    private Button Save;
    private Button Update;

    private TextView tvMessage;

    DataBaseHelper databaseHelper;
    GradientDrawable gd;
    GradientDrawable gd1;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round);

        tvMessage = findViewById(R.id.tvMessage);
        rnd_CreatedAt = findViewById(R.id.etDate);
        rnd_CreatedBy = findViewById(R.id.etStaff);
        rnd_RoundNumber = findViewById(R.id.etRoundNumber);
        rnd_StartDate = findViewById(R.id.etStartDate);
        rnd_EndDate = findViewById(R.id.etEndDate);
        rnd_Remarks = findViewById(R.id.etRemarks);

        Save = findViewById(R.id.addRound);
        Update= findViewById(R.id.updateRound);

        gd = new GradientDrawable();
        gd.setColor(Color.parseColor("#ffffffff"));
        gd.setStroke(1, Color.BLACK);

        gd1 = new GradientDrawable();
        gd1.setColor(Color.parseColor("#B3B6B7"));
        gd1.setStroke(1, Color.BLACK);

        databaseHelper = new DataBaseHelper(this);

        rnd_CreatedAt.setEnabled(false);
        rnd_CreatedAt.setBackground(gd1);



        Intent i = getIntent();
        String omode =i.getStringExtra("mode");
        String  oid  = i.getStringExtra("oid");
        String  staff  = i.getStringExtra("staff");
        String  date  = i.getStringExtra("date");
        String  roundNumber  = i.getStringExtra("roundNumber");
        String  startDate  = i.getStringExtra("startDate");
        String  endDate  = i.getStringExtra("endDate");
        String  remarks  = i.getStringExtra("remarks");



        if (omode.equals("U")){
            GlobalClass.roundID=oid;
            rnd_CreatedAt.setText(date);
            rnd_CreatedBy.setText(databaseHelper.getStaff(staff));
            rnd_RoundNumber.setText(roundNumber);
            rnd_StartDate.setText(startDate);
            rnd_EndDate.setText(endDate);
            rnd_Remarks.setText(remarks);

            Save.setEnabled(false);
            Update.setEnabled(true);

        }else {

            GlobalClass.roundID=UUID.randomUUID().toString();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
            rnd_CreatedAt.setText(df.format(Calendar.getInstance().getTime()));
            rnd_CreatedBy.setText(GlobalClass.stfName);
            Save.setEnabled(true);
            Update.setEnabled(false);

        }

        rnd_StartDate.addTextChangedListener(new DateMask());
        rnd_EndDate.addTextChangedListener(new DateMask());

        rnd_CreatedBy.setBackground(gd1);
        rnd_CreatedBy.setEnabled(false);
        rnd_RoundNumber.setBackground(gd);
        rnd_StartDate.setBackground(gd);
        rnd_EndDate.setBackground(gd);
        rnd_Remarks.setBackground(gd);
    }


    public void addRound(View view) {

        Round round = new Round();
        round.setRnd_oid(GlobalClass.roundID);
        round.setRnd_CreatedAt(rnd_CreatedAt.getText().toString());
        round.setRnd_CreatedBy(GlobalClass.stfID);
        round.setRnd_RoundNumber(Integer.parseInt(rnd_RoundNumber.getText().toString()));
        round.setRnd_StartDate(rnd_StartDate.getText().toString());
        round.setRnd_EndDate(rnd_EndDate.getText().toString());
        round.setRnd_Remarks(rnd_Remarks.getText().toString());
        try {
            ReturnMessage rm = databaseHelper.getRoundDAO().addRound(round);
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


    public void updateRound(View view) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Round round = new Round();
        round.setRnd_oid(GlobalClass.roundID);
        round.setRnd_RoundNumber(Integer.parseInt(rnd_RoundNumber.getText().toString()));
        round.setRnd_StartDate(rnd_StartDate.getText().toString());
        round.setRnd_EndDate(rnd_EndDate.getText().toString());
        round.setRnd_Remarks(rnd_Remarks.getText().toString());
        round.setRnd_EditedAt(df.format(Calendar.getInstance().getTime()));
        round.setRnd_EditedBy(GlobalClass.stfID);

        try {
            ReturnMessage rm = databaseHelper.getRoundDAO().updateRound(round);
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
