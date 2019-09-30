package org.aphrc.myapplication.Fragments;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.aphrc.myapplication.Fragments.Observation.ObservationFragment;
import org.aphrc.myapplication.Fragments.Room.RoomFragment;
import org.aphrc.myapplication.Fragments.Structure.StructureFragment;
import org.aphrc.myapplication.LocationObservation.LocationObservation;
import org.aphrc.myapplication.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class LocationsFragment extends Fragment {

    public static TabLayout tabLayout;
    public static ViewPager viewPager;
    public static int int_items = 3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //this inflates out tab layout file.
        View x =  inflater.inflate(R.layout.locations_fragment_layout,null);
        // set up stuff.
        tabLayout = x.findViewById(R.id.tabs);
        viewPager = x.findViewById(R.id.viewpager);

        // create a new adapter for our pageViewer. This adapters returns child fragments as per the positon of the page Viewer.
        viewPager.setAdapter(new MyAdapter(getChildFragmentManager()));

        // this is a workaround
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                //provide the viewPager to TabLayout.
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        //to preload the adjacent tabs. This makes transition smooth.
        viewPager.setOffscreenPageLimit(2);

        return x;
    }

    class MyAdapter extends FragmentPagerAdapter{

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        //return the fragment with respect to page position.
        @Override
        public Fragment getItem(int position)
        {
            switch (position){
                case 0 : return new ObservationFragment();
                case 1 : return new RoomFragment();
                case 2 : return new StructureFragment();

            }
            return null;
        }

        @Override
        public int getCount() {

            return int_items;

        }

        //This method returns the title of the tab according to the position.
        @Override
        public CharSequence getPageTitle(int position) {

            switch (position){
                case 0:
                    return "OBSERVATIONS";

                case 1 :
                    return "ROOMS";

                case 2 :
                    return "STRUCTURES";


            }
            return null;
        }
    }
}