package org.aphrc.myapplication.Fragments.Staff;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import org.aphrc.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StaffFragment extends Fragment implements View.OnClickListener  {

    Button btnAdd,btnGetAll;


    public StaffFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_staff, container, false);

        btnAdd = view.findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        btnGetAll = view.findViewById(R.id.btnGetAll);
        btnGetAll.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnAdd:
                Intent intent  = new Intent(getActivity(),RegisterActivity.class);
                startActivity(intent);

                break;

            case R.id.btnGetAll:
            //    Intent i  = new Intent(getActivity(), StaffListActivity.class);
             //   startActivity(i);

                break;
        }

    }

}
