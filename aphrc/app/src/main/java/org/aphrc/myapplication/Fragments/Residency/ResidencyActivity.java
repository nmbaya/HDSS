package org.aphrc.myapplication.Fragments.Residency;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.ReturnMessage;
import org.aphrc.myapplication.Model.Residency;
import org.aphrc.myapplication.R;

public class ResidencyActivity extends AppCompatActivity {

    private EditText ind_q1p1;
    private EditText ind_q1p2;
    private EditText ind_q1p3;


    private TextView tvMessage;

    DataBaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residency);

        tvMessage = findViewById(R.id.tvMessage);
        ind_q1p1= findViewById(R.id.ind_q1p1);
        ind_q1p2= findViewById(R.id.ind_q1p2);
        ind_q1p3= findViewById(R.id.ind_q1p3);

    }


    private void resetForm() {

        ind_q1p1.setText("");
        ind_q1p2.setText("");
        ind_q1p3.setText("");
        ind_q1p2.requestFocus();
    }

    public void updateRES(View view) {

        Residency res = new Residency();
        //res.setInd_q1p1(ind_q1p1.getText().toString());
        //res.setInd_q1p2(ind_q1p2.getText().toString());
        //res.setInd_q1p3(ind_q1p2.getText().toString());



        try {
         //   ReturnMessage rm = databaseHelper.getResidencyDAO().updateRES(res);
            tvMessage.setText(ReturnMessage.message);
            if (ReturnMessage.success) {
                tvMessage.setTextColor(Color.BLUE);
                //adapter.changeCursor(databaseHelper.getResidencyDAO().getResidencys(new Residency()));
                resetForm();
            } else {
                tvMessage.setTextColor(Color.RED);
            }
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }

    }
}


