
package org.aphrc.myapplication.LocationObservation.tableview.popup;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;



import org.aphrc.myapplication.Database.DataBaseHelper;

import org.aphrc.myapplication.Fragments.Visitor.VisitorActivity;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.tableview.ITableView;

/**
 * Created on 21.01.2018.
 */

public class RowHeaderLongPressPopup extends PopupMenu implements PopupMenu
        .OnMenuItemClickListener {

    // Menu Item constants

    private static final int OMG_COLUMN = 1;
    private static final int EXT_COLUMN = 2;
    private static final int PGO_COLUMN = 3;
    private static final int EVS_COLUMN = 4;
    private static final int EDU_COLUMN = 5;
    private static final int VAC_COLUMN = 6;
    private static final int HHA_COLUMN = 7;
    private static final int VIS_COLUMN = 8;
    private static final int EVC_COLUMN = 9;


    private ITableView mTableView;
    private Context mContext;
    private int mRowPosition;

    public RowHeaderLongPressPopup(RecyclerView.ViewHolder viewHolder, ITableView tableView) {
        super(viewHolder.itemView.getContext(), viewHolder.itemView);

        this.mTableView = tableView;
        this.mContext = viewHolder.itemView.getContext();
        this.mRowPosition = viewHolder.getAdapterPosition();


        initialize();
    }

    private void initialize() {
        createMenuItem();

        this.setOnMenuItemClickListener(this);
    }


    private void createMenuItem() {
        if (!(GlobalClass.Visitor)) {
            this.getMenu().add(Menu.NONE, OMG_COLUMN, 0, mContext.getString(R.string.omg));
            this.getMenu().add(Menu.NONE, EXT_COLUMN, 1, mContext.getString(R.string.ext));
            this.getMenu().add(Menu.NONE, EVS_COLUMN, 2, mContext.getString(R.string.evs));
            //this.getMenu().add(Menu.NONE, HHA_COLUMN, 7, mContext.getString(R.string.hha));
            //this.getMenu().add(Menu.NONE, VAC_COLUMN, 5, mContext.getString(R.string.vac));

            if (GlobalClass.rltHHH.equals("HHH")) {
                this.getMenu().add(Menu.NONE, HHA_COLUMN, 7, mContext.getString(R.string.hha));
                this.getMenu().add(Menu.NONE, EVC_COLUMN, 8, mContext.getString(R.string.evc));
            }

            if (GlobalClass.IndividualSex.equals("Male")) {

                if ((GlobalClass.IndividualAge >= 5) && (GlobalClass.IndividualAge <= 40)) {
                    this.getMenu().add(Menu.NONE, EDU_COLUMN, 3, mContext.getString(R.string.edu));
                }

                if (GlobalClass.IndividualAge < 5) {
                    this.getMenu().add(Menu.NONE, VAC_COLUMN, 3, mContext.getString(R.string.vac));
                }

            } else if (GlobalClass.IndividualSex.equals("Female")) {

                if ((GlobalClass.IndividualAge >= 12) && (GlobalClass.IndividualAge <= 49)) {

                    this.getMenu().add(Menu.NONE, PGO_COLUMN, 3, mContext.getString(R.string.pgo));

                }

                if ((GlobalClass.IndividualAge >= 5) && (GlobalClass.IndividualAge <= 40)) {
                    this.getMenu().add(Menu.NONE, EDU_COLUMN, 4, mContext.getString(R.string.edu));
                }

                if (GlobalClass.IndividualAge < 5) {
                    this.getMenu().add(Menu.NONE, VAC_COLUMN, 5, mContext.getString(R.string.vac));
                }


            } else {

            }
        }else{
            this.getMenu().add(Menu.NONE, VIS_COLUMN, 8, "Is Visitor");
        }
        // add new one ...

    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        // Note: item id is index of menu item..
        DataBaseHelper db= new DataBaseHelper(mContext);
        switch (menuItem.getItemId()) {

            case OMG_COLUMN:
                GlobalClass.indID=db.getIndividualID(GlobalClass.IndividualID);
                GlobalClass.obsID=db.getObservationID(GlobalClass.observationID);
                GlobalClass.colIndividual="omg_q1p7";
                GlobalClass.colObservation="omg_observation";

                if(db.recordpgoexits(GlobalClass.indID,GlobalClass.obsID,"Outmigration",GlobalClass.colIndividual,GlobalClass.colObservation)==true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("RECORD EXISTS");
                    // Ask the final question
                    builder.setMessage("OUT-MIGRATION FORM (OMG) FOR"+" \n\n"+ GlobalClass.IndividualName+" ("+  GlobalClass.IndividualID+")\n\n "+ "ALREADY DONE FOR THIS ROUND");

                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when No button clicked
                        }
                    });

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();

                }
                else
                {


                }


                break;

            case EXT_COLUMN:

                GlobalClass.indID=db.getIndividualID(GlobalClass.IndividualID);
                GlobalClass.obsID=db.getObservationID(GlobalClass.observationID);
                GlobalClass.colIndividual="ext_q1p5";
                GlobalClass.colObservation="ext_observation";

                if(db.recordpgoexits(GlobalClass.indID,GlobalClass.obsID,"Outresidence",GlobalClass.colIndividual,GlobalClass.colObservation)==true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("RECORD EXISTS");
                    // Ask the final question
                    builder.setMessage("OUT-RESIDENCE FORM (OMG) FOR"+" \n\n"+ GlobalClass.IndividualName+" ("+  GlobalClass.IndividualID+")\n\n "+ "ALREADY DONE FOR THIS ROUND");

                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when No button clicked
                        }
                    });

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();

                }
                else
                {


                }

                break;

            case PGO_COLUMN:
                GlobalClass.indID=db.getIndividualID(GlobalClass.IndividualID);
                GlobalClass.obsID=db.getObservationID(GlobalClass.observationID);
                GlobalClass.colIndividual="evs_q4p2";
                GlobalClass.colObservation="evs_observation";

                if(db.recordpgoexits(GlobalClass.indID,GlobalClass.obsID,"Eventstatus",GlobalClass.colIndividual,GlobalClass.colObservation)==true){
                       AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                       builder.setTitle("RECORD EXISTS");
                       // Ask the final question
                            builder.setMessage("PREGNANCY OUTCOME (PGO) FOR"+" \n\n"+ GlobalClass.IndividualName+" ("+  GlobalClass.IndividualID+")\n\n "+ "ALREADY DONE FOR THIS ROUND");

                       // Set the alert dialog no button click listener
                       builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                           @Override
                           public void onClick(DialogInterface dialog, int which) {
                               // Do something when No button clicked
                           }
                       });

                       AlertDialog dialog = builder.create();
                       // Display the alert dialog on interface
                       dialog.show();

                }
                else
                    {


                    }

                break;

            case EVS_COLUMN:
                GlobalClass.indID=db.getIndividualID(GlobalClass.IndividualID);
                GlobalClass.obsID=db.getObservationID(GlobalClass.observationID);
                GlobalClass.colIndividual="evs_q4p2";
                GlobalClass.colObservation="evs_observation";

                if(db.recordpgoexits(GlobalClass.indID,GlobalClass.obsID,"Eventstatus",GlobalClass.colIndividual,GlobalClass.colObservation)==true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("RECORD EXISTS");
                    // Ask the final question
                    builder.setMessage("EVENTS STATUS (EVS) FOR"+" \n\n"+ GlobalClass.IndividualName+" ("+  GlobalClass.IndividualID+")\n\n "+ "ALREADY DONE FOR THIS ROUND");

                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when No button clicked
                        }
                    });

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();

                }
                else
                {


                }

                /*
                Intent evs  = new Intent(mContext, EventstatusActivity.class);
                evs.putExtra("mode","N");
                mContext.startActivity(evs);*/
                break;

            case EDU_COLUMN:
                GlobalClass.indID=db.getIndividualID(GlobalClass.IndividualID);
                GlobalClass.obsID=db.getObservationID(GlobalClass.observationID);
                GlobalClass.colIndividual="edu_q4p2";
                GlobalClass.colObservation="edu_observation";

                if(db.recordpgoexits(GlobalClass.indID,GlobalClass.obsID,"Educationstatus",GlobalClass.colIndividual,GlobalClass.colObservation)==true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("RECORD EXISTS");
                    // Ask the final question
                    builder.setMessage("EDUCATION STATUS (EDU) FOR"+" \n\n"+ GlobalClass.IndividualName+" ("+  GlobalClass.IndividualID+")\n\n "+ "ALREADY DONE FOR THIS ROUND");

                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when No button clicked
                        }
                    });

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();

                }
                else
                {


                }

                break;

            case VAC_COLUMN:
                GlobalClass.indID=db.getIndividualID(GlobalClass.IndividualID);
                GlobalClass.obsID=db.getObservationID(GlobalClass.observationID);
                GlobalClass.colIndividual="vac_q1p5";
                GlobalClass.colObservation="vac_observation";

                if(db.recordpgoexits(GlobalClass.indID,GlobalClass.obsID,"Vaccination",GlobalClass.colIndividual,GlobalClass.colObservation)==true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("RECORD EXISTS");
                    // Ask the final question
                    builder.setMessage("VACCINATION (VAC) FOR"+" \n\n"+ GlobalClass.IndividualName+" ("+  GlobalClass.IndividualID+")\n\n "+ "ALREADY DONE FOR THIS ROUND");

                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when No button clicked
                        }
                    });

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();

                }
                else
                {


                }


                break;

            case HHA_COLUMN:

                GlobalClass.indID=db.getIndividualID(GlobalClass.IndividualID);
                GlobalClass.obsID=db.getObservationID(GlobalClass.observationID);
                GlobalClass.colIndividual="hha_q1p4";
                GlobalClass.colObservation="hha_observation";

                if(db.recordpgoexits(GlobalClass.indID,GlobalClass.obsID,"HouseholdAmenities",GlobalClass.colIndividual,GlobalClass.colObservation)==true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("RECORD EXISTS");
                    // Ask the final question
                    builder.setMessage("HOUSEHOLD AMENITIES (HAL) FOR"+" \n\n"+ GlobalClass.IndividualName+" ("+  GlobalClass.IndividualID+")\n\n "+ "ALREADY DONE FOR THIS ROUND");

                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when No button clicked
                        }
                    });

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();

                }
                else
                {


                }


                break;

            case VIS_COLUMN:
                Intent vis  = new Intent(mContext, VisitorActivity.class);
                vis.putExtra("mode","N");
                mContext.startActivity(vis);
                break;

            case EVC_COLUMN:

                GlobalClass.indID=db.getIndividualID(GlobalClass.IndividualID);
                GlobalClass.obsID=db.getObservationID(GlobalClass.observationID);
                GlobalClass.colIndividual="evc_q1p5";
                GlobalClass.colObservation="evc_observation";

                if(db.recordpgoexits(GlobalClass.indID,GlobalClass.obsID,"EventsConfirmation",GlobalClass.colIndividual,GlobalClass.colObservation)==true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("RECORD EXISTS");
                    // Ask the final question
                    builder.setMessage("EVENTS CONFIRMATION (EVC) FOR"+" \n\n"+ GlobalClass.IndividualName+" ("+  GlobalClass.IndividualID+")\n\n "+ "ALREADY DONE FOR THIS ROUND");

                    // Set the alert dialog no button click listener
                    builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Do something when No button clicked
                        }
                    });

                    AlertDialog dialog = builder.create();
                    // Display the alert dialog on interface
                    dialog.show();

                }
                else
                {

                }

                break;

        }
        return true;
    }



}
