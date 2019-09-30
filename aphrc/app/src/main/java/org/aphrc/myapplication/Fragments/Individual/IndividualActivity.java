package org.aphrc.myapplication.Fragments.Individual;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.ReturnMessage;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.Model.Individual;
import org.aphrc.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class IndividualActivity extends AppCompatActivity  {

    private EditText ind_CreatedAt;
    private EditText ind_CreatedBy;
    private EditText ind_IndID;
    private EditText ind_Name;
    private EditText ind_NickName;

    private EditText ind_q4p4a;
    private EditText ind_q4p4b;
    private EditText ind_q4p5;
    private Spinner ind_q4p6;
    private Spinner ind_q4p7a;
    private EditText ind_q4p7b;
    private EditText ind_q5p28a;
    private EditText ind_q5p28b;


    private Spinner   ind_MotherAlive;
    private Spinner   ind_FatherAlive;
    private Spinner   ind_IsVisitor;
    private Spinner   ind_MaritalStatus;
    private Spinner  ind_CurrentStatus;
    private EditText  ind_StatusDate;
    private Spinner  ind_PGOStatus;
    private EditText ind_StatusComment;
    private Spinner  ind_visitorStatus;
    private Spinner   ind_IncomeActivity;
    private Spinner   ind_EducationStatus;
    private Spinner  ind_EducationLevel;
    private Spinner  ind_EducationClass;


    private TextView tvMessage;
    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual);

        Intent i =getIntent();
        tvMessage = findViewById(R.id.tvMessage);
        databaseHelper = new DataBaseHelper(this);

        ind_CreatedAt= findViewById(R.id.ind_q1p2);
        ind_CreatedBy= findViewById(R.id.ind_q1p3);
        ind_IndID= findViewById(R.id.ind_q1p4a);
        ind_Name= findViewById(R.id.ind_q1p4b);
        ind_NickName= findViewById(R.id.ind_q1p5);

        ind_q4p4a= findViewById(R.id.ind_q4p4a);
        ind_q4p4b= findViewById(R.id.ind_q4p4b);
        ind_q4p5= findViewById(R.id.ind_q4p5);
        ind_q4p6= findViewById(R.id.ind_q4p6);
        ind_q4p7a= findViewById(R.id.ind_q4p7a);
        ind_q4p7b= findViewById(R.id.ind_q4p7b);
        ind_q5p28a= findViewById(R.id.ind_q5p28a);
        ind_q5p28b= findViewById(R.id.ind_q5p28b);

        String omode =i.getStringExtra("mode");
        String oid = i.getStringExtra("oid");
        String q1p2 = i.getStringExtra("q1p2");
        String q1p3 = i.getStringExtra("q1p3");
        String IndividualID = i.getStringExtra("IndividualID");
        String name = i.getStringExtra("name");
        String nickname = i.getStringExtra("nickname");

        String q1p4a = i.getStringExtra("q1p4a");
        String q1p4b = i.getStringExtra("q1p4b");
        String q1p5 = i.getStringExtra("IndividualID");
        String q4p4a = i.getStringExtra("q4p4a");
        String q4p4b = i.getStringExtra("q4p4b");
        String q4p5 = i.getStringExtra("q4p5");
        String q4p6= i.getStringExtra("q4p6");
        String q4p7a = i.getStringExtra("q4p7a");
        String q4p7b =i.getStringExtra("q4p7b");
        String q5p28a = i.getStringExtra("q5p28a");
        String q5p28b = i.getStringExtra("q5p28b");
        String visitor = i.getStringExtra("visitor");


        if (omode.equals("U")) {

            ind_CreatedAt.setText(q1p2);
            ind_CreatedBy.setText(databaseHelper.getStaff(q1p3));
            ind_IndID.setText(IndividualID);
            ind_Name.setText(name);
            ind_NickName.setText(nickname);

            ind_q4p4a.setText(q4p4a);
            ind_q4p4b.setText(q4p4b);
            ind_q4p5.setText(q4p5);
            ind_q4p6.setSelection(Integer.parseInt(q4p6));
            ind_q4p7a.setSelection(Integer.parseInt(q4p7a));
            ind_q4p7b.setText(q4p7b);
            ind_q5p28a.setText(q5p28a);
            ind_q5p28b.setText(q5p28b);


        }else {

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ind_CreatedAt.setText(df.format(Calendar.getInstance().getTime()));
            ind_CreatedBy.setText(GlobalClass.stfName);

        }
        ind_CreatedAt.setEnabled(false);
        ind_CreatedAt.setBackgroundColor(Color.LTGRAY);
        ind_CreatedBy.setEnabled(false);
        ind_CreatedBy.setBackgroundColor(Color.LTGRAY);

        ind_IndID.setBackgroundColor(Color.WHITE);
        ind_IndID.setEnabled(true);
        ind_Name.setBackgroundColor(Color.WHITE);
        ind_Name.setEnabled(true);
        ind_NickName.setBackgroundColor(Color.WHITE);
        ind_NickName.setEnabled(true);
        ind_q4p4a.setBackgroundColor(Color.WHITE);
        ind_q4p4a.setEnabled(true);
        ind_q4p4b.setBackgroundColor(Color.WHITE);
        ind_q4p4b.setEnabled(true);
        ind_q4p5.setBackgroundColor(Color.WHITE);
        ind_q4p5.setEnabled(true);
        ind_q4p6.setBackgroundColor(Color.WHITE);
        ind_q4p6.setEnabled(true);
        ind_q4p7a.setBackgroundColor(Color.WHITE);
        ind_q4p7a.setEnabled(true);
        ind_q4p7b.setBackgroundColor(Color.WHITE);
        ind_q4p7b.setEnabled(true);
        ind_q5p28a.setBackgroundColor(Color.WHITE);
        ind_q5p28a.setEnabled(true);
        ind_q5p28b.setBackgroundColor(Color.WHITE);
        ind_q5p28b.setEnabled(true);


}



    public void updateIND(View view) {

        Individual ind = new Individual();

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ind.setInd_oid((GlobalClass.indID));
        ind.setInd_Name(ind_Name.getText().toString());
        ind.setInd_NickName(ind_NickName.getText().toString());
        ind.setInd_q4p4a(ind_q4p4a.getText().toString());
        ind.setInd_q4p4b(ind_q4p4b.getText().toString());
        ind.setInd_q4p6(ind_q4p6.getSelectedItemPosition());
        ind.setInd_q4p7a(ind_q4p7a.getSelectedItemPosition());
        ind.setInd_q4p7b(ind_q4p7b.getText().toString());
        ind.setInd_q5p28a(ind_q5p28a.getText().toString());
        ind.setInd_q5p28b(ind_q5p28a.getText().toString());

       // ind.ind_completed=img_Completed.isChecked();

        ind.setInd_EditedAt(df.format(Calendar.getInstance().getTime()));
        ind.setInd_EditedBy(GlobalClass.stfID);


            try {
              //  ReturnMessage rm = databaseHelper.getIndividualDAO().updateIND(ind);
                tvMessage.setText(ReturnMessage.message);
                if (ReturnMessage.success) {
                    tvMessage.setTextColor(Color.BLUE);
                    this.finish();

                } else {
                    tvMessage.setTextColor(Color.RED);
                }
            } catch (Exception e) {
                Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
            }

        }




    public void updateIND_EDU(View view) {

        Individual ind = new Individual();

        ind.setInd_EducationStatus(Integer.parseInt(ind_EducationStatus.getSelectedItem().toString()));
        ind.setInd_EducationLevel(Integer.parseInt(ind_EducationLevel.getSelectedItem().toString()));
        ind.setInd_EducationClass(Integer.parseInt(ind_EducationClass.getSelectedItem().toString()));

        try {
         //   ReturnMessage rm = databaseHelper.getIndividualDAO().updateIND(ind);
            tvMessage.setText(ReturnMessage.message);
            if (ReturnMessage.success) {
                tvMessage.setTextColor(Color.BLUE);

            } else {
                tvMessage.setTextColor(Color.RED);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }

    }
}

