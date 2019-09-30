package org.aphrc.myapplication.Fragments.Staff;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import org.aphrc.myapplication.R;
import org.aphrc.myapplication.Helpers.ValidationHelper;
import org.aphrc.myapplication.Model.Staff;
import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.StaffDAO;

import java.util.UUID;

/**
 * Created by mbaya on 8/27/2016.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = RegisterActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutFirstName;
    private TextInputLayout textInputLayoutSecondName;
    private TextInputLayout textInputLayoutUserName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private TextInputLayout textInputLayoutConfirmPassword;

    private TextInputEditText textInputEditTextFirstName;
    private TextInputEditText textInputEditTextSecondName;
    private TextInputEditText textInputEditTextUserName;
    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;
    private TextInputEditText textInputEditTextConfirmPassword;

    private AppCompatButton appCompatButtonRegister;
    private AppCompatTextView appCompatTextViewLoginLink;

    private ValidationHelper staffValidation;
    private DataBaseHelper databaseHelper;
    private StaffDAO staffDAO;
    private Staff staff;
    private String stfID=UUID.randomUUID().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        initViews();
        initListeners();
        initObjects();
    }

    /**
     * This method is to initialize views
     */
    private void initViews() {
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutFirstName = (TextInputLayout) findViewById(R.id.textInputLayoutFirstName);
        textInputLayoutSecondName = (TextInputLayout) findViewById(R.id.textInputLayoutSecondName);
        textInputLayoutUserName = (TextInputLayout) findViewById(R.id.textInputLayoutUserName);
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);
        textInputLayoutConfirmPassword = (TextInputLayout) findViewById(R.id.textInputLayoutConfirmPassword);

        textInputEditTextFirstName = (TextInputEditText) findViewById(R.id.textInputEditTextFirstName);
        textInputEditTextSecondName = (TextInputEditText) findViewById(R.id.textInputEditTextSecondName);
        textInputEditTextUserName = (TextInputEditText) findViewById(R.id.textInputEditTextUserName);
        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);
        textInputEditTextConfirmPassword = (TextInputEditText) findViewById(R.id.textInputEditTextConfirmPassword);

        appCompatButtonRegister = (AppCompatButton) findViewById(R.id.appCompatButtonRegister);

        appCompatTextViewLoginLink = (AppCompatTextView) findViewById(R.id.appCompatTextViewLoginLink);

    }

    /**
     * This method is to initialize listeners
     */
    private void initListeners() {
        appCompatButtonRegister.setOnClickListener(this);
        appCompatTextViewLoginLink.setOnClickListener(this);

    }

    /**
     * This method is to initialize objects to be used
     */
    private void initObjects() {
        staffValidation = new ValidationHelper(activity);
        databaseHelper = new DataBaseHelper(activity);
        staffDAO = new StaffDAO(getApplicationContext());
        staff = new Staff();

    }

    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.appCompatButtonRegister:
                postDataToSQLite();
                finish();
                Intent i = new Intent(activity, LoginActivity.class);
                startActivity(i);

                break;

            case R.id.appCompatTextViewLoginLink:
                finish();
                break;
        }
    }

    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private void postDataToSQLite() {
        if (!staffValidation.isInputEditTextFilled(textInputEditTextFirstName, textInputLayoutFirstName, getString(R.string.error_message_fname))) {
            return;        }
        if (!staffValidation.isInputEditTextFilled(textInputEditTextSecondName, textInputLayoutSecondName, getString(R.string.error_message_sname))) {
            return;
        }
        if (!staffValidation.isInputEditTextFilled(textInputEditTextUserName, textInputLayoutUserName, getString(R.string.error_message_uname))) {
            return;
        }
        if (!staffValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!staffValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return;
        }
        if (!staffValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return;
        }
        if (!staffValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return;
        }


        if (!staffDAO.checkStaff(textInputEditTextEmail.getText().toString().trim())) {

            staff.setStf_ID(stfID);
            staff.setStf_FirstName(textInputEditTextFirstName.getText().toString().trim());
            staff.setStf_SecondName(textInputEditTextSecondName.getText().toString().trim());
            staff.setStf_UserName(textInputEditTextUserName.getText().toString().trim());
            staff.setStf_Email(textInputEditTextEmail.getText().toString().trim());
            staff.setStf_Password(textInputEditTextPassword.getText().toString().trim());

            staffDAO.addStaff(staff);

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show();
            emptyInputEditText();


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show();
        }

    }

    /**
     * This method is to empty all input edit text
     */
    private void emptyInputEditText() {
        textInputEditTextFirstName.setText(null);
        textInputEditTextSecondName.setText(null);
        textInputEditTextUserName.setText(null);
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
        textInputEditTextConfirmPassword.setText(null);
    }

}
