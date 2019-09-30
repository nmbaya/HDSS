package org.aphrc.myapplication.Fragments.Location;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.aphrc.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LocationDetailFragment extends Fragment {


    public LocationDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_location_detail, container, false);
        return view;
    }

    public void setText(String text) {
        TextView view = (TextView) getView().findViewById(R.id.detailsText);
        view.setText(text);
    }

}
