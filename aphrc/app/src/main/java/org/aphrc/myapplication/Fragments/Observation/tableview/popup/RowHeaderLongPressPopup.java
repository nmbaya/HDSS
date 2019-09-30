
package org.aphrc.myapplication.Fragments.Observation.tableview.popup;

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
import org.aphrc.myapplication.Fragments.Observation.ObservationActivity;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.tableview.ITableView;

/**
 * Created on 21.01.2018.
 */

public class RowHeaderLongPressPopup extends PopupMenu implements PopupMenu
        .OnMenuItemClickListener {

    // Menu Item constants
  //  private static final int OBS_COLUMN = 1;
    private static final int DEL_COLUMN = 2;
    private static final int UPDATE_COLUMN = 3;

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
     //   this.getMenu().add(Menu.NONE, OBS_COLUMN, 0, mContext.getString(R.string.newrec));
        this.getMenu().add(Menu.NONE, DEL_COLUMN, 2, mContext.getString(R.string.delrec));
        this.getMenu().add(Menu.NONE, UPDATE_COLUMN, 1, mContext.getString(R.string.updrec));
        // add new one ...

    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        // Note: item id is index of menu item..

        switch (menuItem.getItemId()) {
           // case OBS_COLUMN:
             // Intent obs  = new Intent(mContext, ObservationActivity.class);
             //   obs.putExtra("Mode","N");
             //   mContext.startActivity(obs);
            //    break;

            case DEL_COLUMN:
                // add delete function

                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                // Set a title for alert dialog
                builder.setTitle("Select your answer.");
                // Ask the final question
                builder.setMessage("Are you sure want to delete Observation:  \n\n" + GlobalClass.observationID+"?");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext) ;
                        dataBaseHelper.deleterecord("Observation", GlobalClass.obsID);
                    }
                });

                // Set the alert dialog no button click listener
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Do something when No button clicked

                    }
                });

                AlertDialog dialog = builder.create();
                // Display the alert dialog on interface
                dialog.show();
                break;

            case UPDATE_COLUMN:
                DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext) ;
                Cursor cursor = dataBaseHelper.getOneObservation(GlobalClass.obsID);

                Intent obs  = new Intent(mContext, ObservationActivity.class);
                obs.putExtra("oid",cursor.getString(cursor.getColumnIndex("oid")));
                obs.putExtra("date",cursor.getString(cursor.getColumnIndex("obs_createdAt")));
                obs.putExtra("createdby",cursor.getString(cursor.getColumnIndex("obs_createdBy")));
                obs.putExtra("room",cursor.getString(cursor.getColumnIndex("obs_room")));
                obs.putExtra("round",cursor.getString(cursor.getColumnIndex("obs_round")));
                obs.putExtra("observationID",cursor.getString(cursor.getColumnIndex("obs_observationID")));
                obs.putExtra("respondentID",cursor.getString(cursor.getColumnIndex("obs_respondentID")));
                obs.putExtra("respondentName",cursor.getString(cursor.getColumnIndex("obs_respondentName")));
                obs.putExtra("roomUsage",cursor.getString(cursor.getColumnIndex("obs_roomUsage")));
                obs.putExtra("numberOfVisits",cursor.getString(cursor.getColumnIndex("obs_numberOfVisits")));
                obs.putExtra("result",cursor.getString(cursor.getColumnIndex("obs_result")));
                obs.putExtra("resultOther",cursor.getString(cursor.getColumnIndex("obs_resultOther")));
                obs.putExtra("comments",cursor.getString(cursor.getColumnIndex("obs_comments")));
                obs.putExtra("mode","U");


                mContext.startActivity(obs);
                break;

        }
        return true;
    }



}
