package org.aphrc.myapplication.Fragments.Membership;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.ReturnMessage;
import org.aphrc.myapplication.Model.Membership;
import org.aphrc.myapplication.R;

public class MembershipActivity extends AppCompatActivity {

    private TextView tvMessage;

    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership);

        tvMessage = findViewById(R.id.tvMessage);

    }



    public void updateMEM(View view) {

        Membership mem = new Membership();
        //res.setInd_q1p1(ind_q1p1.getText().toString());
        //res.setInd_q1p2(ind_q1p2.getText().toString());
        //res.setInd_q1p3(ind_q1p2.getText().toString());

        try {
         //   ReturnMessage rm = databaseHelper.getMembershipDAO().updateMEM(mem);
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


