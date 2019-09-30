package org.aphrc.myapplication;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import org.aphrc.myapplication.Fragments.Staff.LoginActivity;

import org.aphrc.myapplication.Views.AdminActivity;
import org.aphrc.myapplication.Views.DwellingUnitsActivity;
import org.aphrc.myapplication.Views.FormsActivity;
import org.aphrc.myapplication.Views.HouseholdsActivity;
import org.aphrc.myapplication.Views.IndividualsActivity;
import org.aphrc.myapplication.Masterdetail.ItemsListActivity;

import java.io.File;


public class MainActivity extends AppCompatActivity {

    private final AppCompatActivity activity = MainActivity.this;


    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeStuff();

        // since, NoActionBar was defined in theme, we set toolbar as our action bar.
        setSupportActionBar(toolbar);

        //this basically defines on click on each menu item.
        setUpNavigationView(navigationView);

        //This is for the Hamburger icon.
        drawerToggle = setupDrawerToggle();
        drawerLayout.addDrawerListener(drawerToggle);


        Intent i = new Intent(activity, LoginActivity.class);
        startActivity(i);

     /*  File file = new File(this.getDatabasePath("mDSS.db").toString());
        if(file.exists())
        {

            Intent i = new Intent(activity, LoginActivity.class);
            startActivity(i);

        }
            //Do something
        else{

            Intent i = new Intent(activity, SqlSyncMain.class);
            startActivityForResult(i,3);


        }*/


        navigationView.setCheckedItem(R.id.nav_item_admin);
        setTitle("Nairobi Urban Health & Demographic Surveillance System");
       // GlobalClass global=(GlobalClass)this.getApplication();


    }

    void initializeStuff(){
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
       // toolbar.setTitle(R.string.app_name);
       // toolbar.setNavigationIcon(R.drawable.ic_previous);
        navigationView = findViewById(R.id.navigationDrawer);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Intent i = new Intent(activity, LoginActivity.class);
        startActivity(i);
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

                        selectDrawerItem(menuItem);
                        navigationView.setCheckedItem(menuItem.getItemId());
                        setTitle(menuItem.getTitle());
                        //close the drawer when user selects a nav item.
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });
    }


    public void selectDrawerItem(MenuItem menuItem){
        //Fragment fragment = null;
        switch(menuItem.getItemId()) {

            case R.id.nav_item_locations:
                Intent dwe  = new Intent(this, DwellingUnitsActivity.class);
                this.startActivity(dwe);

                break;
            case R.id.nav_item_Individuals:
                Intent ind  = new Intent(this, IndividualsActivity.class);
                this.startActivity(ind);

                break;
            case R.id.nav_item_households:
                Intent sgp  = new Intent(this, HouseholdsActivity.class);
                this.startActivity(sgp);
                break;
            case R.id.nav_item_forms:
                Intent frm  = new Intent(this, FormsActivity.class);
                this.startActivity(frm);

                break;

            case R.id.nav_item_admin:
                Intent adm  = new Intent(this, AdminActivity.class);
                this.startActivity(adm);

                break;

            case R.id.nav_item_loc_obs:
                Intent loc  = new Intent(this, ItemsListActivity.class);
                this.startActivity(loc);

                break;

        }
        //return fragment;
    }


    /**
     * This is to setup our Toggle icon. The strings R.string.drawer_open and R.string.drawer close, are for accessibility (generally audio for visually impaired)
     * use only. It is now showed on the screen. While the remaining parameters are required initialize the toggle.
     */
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
            //break;


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


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

}
