package org.aphrc.myapplication.Views;

import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.SearchView;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ImageView;

import org.aphrc.myapplication.Fragments.Observation.ObservationFragment;
import org.aphrc.myapplication.Fragments.Room.RoomFragment;
import org.aphrc.myapplication.Fragments.Structure.StructureFragment;
import org.aphrc.myapplication.Fragments.Structure.StructureActivity;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.R;

public class DwellingUnitsActivity extends AppCompatActivity {

    private final AppCompatActivity activity = DwellingUnitsActivity.this;

    ImageView imgClick;
    ImageView imgClick2;
    StructureActivity structure;

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;

    final String[] Structures = new String[] { "n.str_structureID", "h.hrb_Name" };
    final String[] Rooms = new String[] { "rm_roomID" };
    final String[] Observations = new String[] { "obs_observationID" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dwelling_units);
        initializeStuff();


        //this basically defines on click on each menu item.
        setUpNavigationView(navigationView);

        //This is for the Hamburger icon.
        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);

        navigationView.setCheckedItem(R.id.nav_item_households);
        SearchView search= (SearchView)findViewById(R.id.search);
        Spinner SearchSpinner= (Spinner)findViewById(R.id.action_Spinner);



        //=============================================

        imgClick = (ImageView)findViewById(R.id.imgClick);
        imgClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        imgClick2 = (ImageView)findViewById(R.id.action_search);
        imgClick2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = null;

                GlobalClass.search= search.getQuery().toString();
                GlobalClass.columnname=SearchSpinner.getSelectedItem().toString();

                switch(getTitle().toString()) {

                    case "Structure":
                        fragment = new StructureFragment();
                        break;
                    case "Room":
                        fragment = new RoomFragment();
                        break;
                    case "Observation":
                        fragment = new ObservationFragment();
                        break;

                }

                Fragment selectedFragment = fragment;
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.frameContent, selectedFragment).commit();
                return ;

            }
        });


    }





    //=============================================
    void initializeStuff(){
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar1);
        //toolbar.setTitle(menuItem.getTitle());
        navigationView = findViewById(R.id.navigationDrawer);
    }

    private void refreshspinner(String[] name){


        final Spinner spinner = (Spinner) findViewById(R.id.action_Spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, name);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }


    /**
     * Inflate the fragment according to item clicked in navigation drawer.
     */
    private void setUpNavigationView(final NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        //replace the current fragment with the new fragment.
                        Fragment selectedFragment = selectDrawerItem(menuItem);
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.frameContent, selectedFragment).commit();
                        // the current menu item is highlighted in navigation tray.

                        //selectDrawerItem(menuItem);
                        navigationView.setCheckedItem(menuItem.getItemId());
                        setTitle(menuItem.getTitle());
                        //close the drawer when user selects a nav item.
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }

    /**
     * This method returns the fragment according to navigation item selected.
     */
    public Fragment selectDrawerItem(MenuItem menuItem){
        Fragment fragment = null;
        switch(menuItem.getItemId()) {

            case R.id.nav_item_str:
                refreshspinner(Structures);
                GlobalClass.columnname="n.str_structureID";
                fragment = new StructureFragment();
                break;
            case R.id.nav_item_roo:
                refreshspinner(Rooms);
                GlobalClass.columnname="r.rm_roomID";
                fragment = new RoomFragment();
                break;
            case R.id.nav_item_obs:
                GlobalClass.columnname="obs_observationID";
                refreshspinner(Observations);
                fragment = new ObservationFragment();
                break;

        }
        return fragment;
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        return new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.drawer_open,R.string.drawer_close);
    }

    /**
     * This makes sure that the action bar home button that is the toggle button, opens or closes the drawer when tapped.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * This synchronizes the drawer icon that rotates when the drawer is swiped left or right.
     * Called inside onPostCreate so that it can synchronize the animation again when the Activity is restored.
     */
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }
    /**
     * This is to handle generally orientation changes of your device. It is mandatory to include
     * android:configChanges="keyboardHidden|orientation|screenSize" in your activity tag of the manifest for this to work.
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }


}
