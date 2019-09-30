package org.aphrc.myapplication.Fragments.SocialGroup;

import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.ReturnMessage;
import org.aphrc.myapplication.Database.SocialGroupDAO;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.Model.SocialGroup;
import org.aphrc.myapplication.R;

public class SocialgroupActivity extends AppCompatActivity {

    private SocialGroupDAO socialGroupDAO;

    private EditText sgp_sgp;
    private EditText sgp_sgpid;
    private EditText sgp_room;

    private TextView tvMessage;

    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socialgroup);

        tvMessage = findViewById(R.id.tvMessage);
        sgp_sgp= findViewById(R.id.sgp_sgp);
        sgp_sgpid= findViewById(R.id.sgp_sgpid);
        sgp_room= findViewById(R.id.sgp_room);

    }


    public void updateRES(View view) {

        SocialGroup sgp = new SocialGroup();
        //res.setInd_q1p1(ind_q1p1.getText().toString());
        //res.setInd_q1p2(ind_q1p2.getText().toString());
        //res.setInd_q1p3(ind_q1p2.getText().toString());

        try {
            ReturnMessage rm = databaseHelper.getSocialGroupDAO().updateSGP(sgp);
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


