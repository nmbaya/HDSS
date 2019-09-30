package org.aphrc.myapplication.Fragments.Staff;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.R;
import android.widget.ListView;
import android.widget.TextView;


public class StaffListFragment extends Fragment {

    private ListView lvstf;
    private TextView tvMessage;

    DataBaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_staff_list, container, false);

    }
}