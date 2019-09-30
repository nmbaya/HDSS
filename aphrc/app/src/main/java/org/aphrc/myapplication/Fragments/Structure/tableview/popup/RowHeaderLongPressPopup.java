
package org.aphrc.myapplication.Fragments.Structure.tableview.popup;

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
import org.aphrc.myapplication.Fragments.Room.RoomActivity;
import org.aphrc.myapplication.Fragments.Structure.StructureActivity;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.tableview.ITableView;

/**
 * Created on 21.01.2018.
 */

public class RowHeaderLongPressPopup extends PopupMenu implements PopupMenu
        .OnMenuItemClickListener {

    // Menu Item constants
    private static final int ROOM_COLUMN = 0;
    private static final int UPDATE_COLUMN = 1;
    private static final int DEL_COLUMN = 2;


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
      //  this.getMenu().add(Menu.NONE, STR_COLUMN, 0, mContext.getString(R.string.newrec));
        this.getMenu().add(Menu.NONE, ROOM_COLUMN, 0, "Add New Room");
        this.getMenu().add(Menu.NONE, UPDATE_COLUMN, 1, mContext.getString(R.string.updrec));
        this.getMenu().add(Menu.NONE, DEL_COLUMN, 2, mContext.getString(R.string.delrec));

        // add new one ...

    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        // Note: item id is index of menu item..

        switch (menuItem.getItemId()) {

            case ROOM_COLUMN:
                 Intent r  = new Intent(mContext, RoomActivity.class);
                   r.putExtra("mode","N");
                  mContext.startActivity(r);
                break;

            case DEL_COLUMN:
            // add delete function

                // Build an AlertDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                // Set a title for alert dialog
                builder.setTitle("Select your answer.");
                // Ask the final question
                builder.setMessage("Are you sure want to delete Structure \n\n" +GlobalClass.strNum+"?");

                // Set the alert dialog yes button click listener
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext) ;
                        dataBaseHelper.deleterecord("Structure", GlobalClass.strID);
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
                Cursor cursor = dataBaseHelper.getOneStructure(GlobalClass.strID);

                Intent str  = new Intent(mContext, StructureActivity.class);
                str.putExtra("oid",cursor.getString(cursor.getColumnIndex("oid")));
                str.putExtra("date",cursor.getString(cursor.getColumnIndex("str_createdAt")));
                str.putExtra("createdby",cursor.getString(cursor.getColumnIndex("str_createdBy")));
                str.putExtra("site",cursor.getString(cursor.getColumnIndex("str_site")));
                str.putExtra("ea",cursor.getString(cursor.getColumnIndex("str_ea")));
                str.putExtra("number",cursor.getString(cursor.getColumnIndex("str_number")));
                str.putExtra("structureID",cursor.getString(cursor.getColumnIndex("str_structureID")));
                str.putExtra("before",cursor.getString(cursor.getColumnIndex("str_before")));
                str.putExtra("after",cursor.getString(cursor.getColumnIndex("str_after")));
                str.putExtra("hrb",cursor.getString(cursor.getColumnIndex("str_hrb")));
                str.putExtra("comment",cursor.getString(cursor.getColumnIndex("str_comment")));
                str.putExtra("mode","U");

                mContext.startActivity(str);
                break;

        }
        return true;
    }



}
