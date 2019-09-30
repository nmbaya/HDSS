package org.aphrc.myapplication.Utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import android.text.Editable;
import android.text.TextWatcher;
import java.util.Date;
import java.util.Locale;

public class ParseDateFromString implements TextWatcher {

    private static final int MAX_LENGTH = 8;
    private static final int MIN_LENGTH = 2;

    private String updatedText;
    private int day, month, year;
    private boolean editing;


    @Override
    public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {

    }

    @Override
    public void onTextChanged(CharSequence text, int start, int before, int count) {
        if (text.toString().equals(updatedText) || editing) return;

        String digits = text.toString().replaceAll("\\D", "");
        int length = digits.length();


        if (length == MAX_LENGTH) {
            digits = digits.substring(0, MAX_LENGTH);
            if ((digits=="98")||(digits=="97")||(digits=="99")){
                day=15;
            }else{day=Integer.parseInt(digits);}



        }else if(length == 4){
            digits = digits.substring(2,4);
            if ((digits=="98")||(digits=="97")||(digits=="99")){
                month=07;
            }else {
                day=Integer.parseInt(digits);
            }

        }else if(length == 4){
            digits = digits.substring(4);
            if ((digits=="98")||(digits=="97") ||(digits=="97")){
                year=2200;
                updatedText = String.format(Locale.US, "%s/%s/%s", day ,month , year);
            }else {
                year=Integer.parseInt(digits);
                updatedText = String.format(Locale.US, "%s/%s/%s", day ,month , year);
            }


        }else{

        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

        if (editing) return;

        editing = true;

        editable.clear();
        editable.insert(0, updatedText);

        editing = false;
    }
}
