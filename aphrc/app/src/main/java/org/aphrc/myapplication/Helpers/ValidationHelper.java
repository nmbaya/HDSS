package org.aphrc.myapplication.Helpers;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by Mbaya on 21/01/2019.
 */
public class ValidationHelper {
    private Context context;

    private Pattern pattern;
    private Matcher matcher;

    /**
     * constructor
     *
     * @param context
     */
    public ValidationHelper(Context context) {
        this.context = context;
    }

    /**
     * method to check InputEditText filled .
     *
     * @param textInputEditText
     * @param textInputLayout
     * @param message
     * @return
     */


    public boolean isInputEditTextFilled(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }


    public boolean isRoomID(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        String xx =value.substring(0,1);

        if (value.isEmpty()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;}

         else if (value.length()!=10){
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        else if (!xx.equals("V") && !xx.equals("Y") && !xx.equals("G")) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        }
        else {
            textInputLayout.setErrorEnabled(false);
        }

        return true;
    }


    public boolean isObservation(String Observation, TextView appCompatTextView, String message) {
        if (Observation.isEmpty()) {
            appCompatTextView.setError(message);
            return false;
        } else {
            appCompatTextView.setText("");
        }
        return true;
    }





    /**
     * method to check InputEditText has valid email .
     *
     * @param textInputEditText
     * @param textInputLayout
     * @param message
     * @return
     */
    public boolean isInputEditTextEmail(TextInputEditText textInputEditText, TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }


    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1, TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * method to Hide keyboard
     *
     * @param view
     */
    private void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }




    private static final String DATE_PATTERN =
            "(0?[1-9]|1[012]) [/.-] (0?[1-9]|[12][0-9]|3[01]) [/.-] ((19|20)\\d\\d)";
    /**
     * Validate date format with regular expression
     * @param date date address for validation
     * @return true valid date format, false invalid date format
     */

    public boolean validate(final String date){

        matcher = pattern.matcher(date);

        if(matcher.matches()){
            matcher.reset();

            if(matcher.find()){
                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if (day.equals("31") &&
                        (month.equals("4") || month .equals("6") || month.equals("9") ||
                                month.equals("11") || month.equals("04") || month .equals("06") ||
                                month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                }

                else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if(year % 4==0){
                        if(day.equals("30") || day.equals("31")){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                    else{
                        if(day.equals("29")||day.equals("30")||day.equals("31")){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                }

                else{
                    return true;
                }
            }

            else{
                return false;
            }
        }
        else{
            return false;
        }
    }


}