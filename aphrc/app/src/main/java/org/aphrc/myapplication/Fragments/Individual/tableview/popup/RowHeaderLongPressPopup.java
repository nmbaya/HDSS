
package org.aphrc.myapplication.Fragments.Individual.tableview.popup;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;

import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Fragments.Individual.IndividualActivity;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.tableview.ITableView;

/**
 * Created on 21.01.2018.
 */

public class RowHeaderLongPressPopup extends PopupMenu implements PopupMenu
        .OnMenuItemClickListener {

    // Menu Item constants

    private static final int UPD_COLUMN = 1;
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

        this.getMenu().add(Menu.NONE, UPD_COLUMN, 0, mContext.getString(R.string.updrec));
        this.getMenu().add(Menu.NONE, DEL_COLUMN, 1, mContext.getString(R.string.delrec));

        // add new one ...

    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        // Note: item id is index of menu item..

        switch (menuItem.getItemId()) {

            case UPD_COLUMN:
                DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext) ;
                Cursor cursor = dataBaseHelper.getOneIndividual(GlobalClass.indID);

                Intent ind  = new Intent(mContext, IndividualActivity.class);
                ind.putExtra("oid",cursor.getString(cursor.getColumnIndex("oid")));
                ind.putExtra("q1p2",cursor.getString(cursor.getColumnIndex("ind_createdAt")));
                ind.putExtra("q1p3",cursor.getString(cursor.getColumnIndex("ind_createdBy")));
                ind.putExtra("IndividualID",cursor.getString(cursor.getColumnIndex("ind_IndividualID")));
                ind.putExtra("name",cursor.getString(cursor.getColumnIndex("ind_name")));
                ind.putExtra("nickname",cursor.getString(cursor.getColumnIndex("ind_nickname")));
                ind.putExtra("q4p4a",cursor.getString(cursor.getColumnIndex("ind_q4p4a")));
                ind.putExtra("q4p4b",cursor.getString(cursor.getColumnIndex("ind_q4p4b")));
                ind.putExtra("q4p5",cursor.getString(cursor.getColumnIndex("ind_q4p5")));
                ind.putExtra("q4p6",cursor.getString(cursor.getColumnIndex("ind_q4p6")));
                ind.putExtra("q4p7a",cursor.getString(cursor.getColumnIndex("ind_q4p7a")));
                ind.putExtra("q4p7b",cursor.getString(cursor.getColumnIndex("ind_q4p7b")));
                ind.putExtra("q4p10a",cursor.getString(cursor.getColumnIndex("ind_q4p10a")));
                ind.putExtra("q4p10b",cursor.getString(cursor.getColumnIndex("ind_q4p10b")));
                ind.putExtra("q5p28a",cursor.getString(cursor.getColumnIndex("ind_q5p28a")));
                ind.putExtra("q5p28b",cursor.getString(cursor.getColumnIndex("ind_q5p28b")));
                ind.putExtra("visitor",cursor.getString(cursor.getColumnIndex("ind_visitor")));
                ind.putExtra("mode","U");

                mContext.startActivity(ind);
                break;

            case DEL_COLUMN:
               // Intent ext  = new Intent(mContext, OutresidenceActivity.class);
               // mContext.startActivity(ext);
                break;

        }
        return true;
    }



}
