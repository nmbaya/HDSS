package org.aphrc.myapplication.Utilities.SearchSpinner.interfaces;

import android.view.View;
import android.widget.AdapterView;

/**
 * Created by  on 1/14/17.
 */

public interface OnItemSelectedListener {
    void onItemSelected(View view, int position, long id);
    void onNothingSelected();
}
