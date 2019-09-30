package org.aphrc.myapplication.Fragments.Staff;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.Helpers.ValidationHelper;
import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.StaffDAO;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatTextView textViewMessage;
    private AppCompatButton appCompatButtonLogin;
    private AppCompatTextView textViewLinkRegister;

    private ValidationHelper staffValidation;
    private DataBaseHelper databaseHelper;
    private StaffDAO staffDAO;
    private String username;
    private String UserID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();


    }

    @Override
    public void onBackPressed () {

    }
    /**
     * This method is to initialize views
     */
    private void initViews() {

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);
        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
        textViewMessage = (AppCompatTextView) findViewById(R.id.textViewMessage);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        databaseHelper = new DataBaseHelper(activity);
        staffValidation = new ValidationHelper(activity);
        staffDAO = new StaffDAO(activity);
    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                // Navigate to RegisterActivity
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */

    private void verifyFromSQLite() {
        if (!staffValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!staffValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!staffValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (staffDAO.checkStaff(textInputEditTextEmail.getText().toString().trim() , textInputEditTextPassword.getText().toString().trim())) {


           Cursor c= staffDAO.getStaff(textInputEditTextEmail.getText().toString().trim() );
           while (c.moveToNext()){
               UserID=c.getString(0);
               username=c.getString(1);

               GlobalClass.stfID=UserID;
               GlobalClass.stfName=username;
           }

            this.activity.finish();

        } else {

            textViewMessage.setText(getString(R.string.error_valid_email_password));

        }
    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
