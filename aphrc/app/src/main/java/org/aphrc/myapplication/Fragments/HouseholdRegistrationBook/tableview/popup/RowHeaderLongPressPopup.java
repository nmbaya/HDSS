
package org.aphrc.myapplication.Fragments.HouseholdRegistrationBook.tableview.popup;

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
import org.aphrc.myapplication.Fragments.HouseholdRegistrationBook.HouseholdRegistrationBookActivity;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.tableview.ITableView;

/**
 * Created on 21.01.2018.
 */

public class RowHeaderLongPressPopup extends PopupMenu implements PopupMenu
        .OnMenuItemClickListener {

    // Menu Item constants
    private static final int ALL_COLUMN = 0;
    private static final int DEL_COLUMN = 1;
    private static final int UPDATE_COLUMN = 2;

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
        this.getMenu().add(Menu.NONE, ALL_COLUMN, 0, mContext.getString(R.string.allrec));
        this.getMenu().add(Menu.NONE, DEL_COLUMN, 1, mContext.getString(R.string.delrec));
        this.getMenu().add(Menu.NONE, UPDATE_COLUMN, 2, mContext.getString(R.string.updrec));
        // add new one ...

    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        // Note: item id is index of menu item..

        switch (menuItem.getItemId()) {
            case ALL_COLUMN:

                break;

            case DEL_COLUMN:

                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                // Set a title for alert dialog
                builder.setTitle("Select your answer.");
                // Ask the final question
                builder.setMessage("Are you sure want to delete HRB \n\n" + GlobalClass.hrbNAME+"?");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext) ;
                        dataBaseHelper.deleterecord("HouseholdRegistrationBook", GlobalClass.hrbID);
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
                Cursor cursor = dataBaseHelper.getOneRecord(GlobalClass.hrbID,"HouseholdRegistrationBook");

                Intent upd  = new Intent(mContext, HouseholdRegistrationBookActivity.class);
                upd.putExtra("oid",cursor.getString(cursor.getColumnIndex("oid")));
                upd.putExtra("staff",cursor.getString(cursor.getColumnIndex("hrb_createdBy")));
                upd.putExtra("date",cursor.getString(cursor.getColumnIndex("hrb_createdAt")));
                upd.putExtra("hrb",cursor.getString(cursor.getColumnIndex("hrb_Name")));
                upd.putExtra("mode","U");
                mContext.startActivity(upd);
                break;

        }
        return true;
    }



}
