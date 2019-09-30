
package org.aphrc.myapplication.LocationObservation.tableview.popup;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;


import org.aphrc.myapplication.tableview.holder.ColumnHeaderViewHolder;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.tableview.ITableView;
import org.aphrc.myapplication.tableview.sort.SortState;

/**
 * Created on 24.12.2017.
 */

public class ColumnHeaderLongPressPopup extends PopupMenu implements PopupMenu
        .OnMenuItemClickListener {
    private static final String LOG_TAG = ColumnHeaderLongPressPopup.class.getSimpleName();

    // Menu Item constants
    private static final int IMG_COLUMN = 0;
    private static final int ENT_COLUMN = 1;
    private static final int EVC_COLUMN = 2;
    private static final int HHL_COLUMN = 3;
    private static final int ASCENDING = 4;
    private static final int DESCENDING = 5;



    private ColumnHeaderViewHolder mViewHolder;
    private ITableView mTableView;
    private Context mContext;
    private int mXPosition;

    public ColumnHeaderLongPressPopup(ColumnHeaderViewHolder viewHolder, ITableView tableView) {
        super(viewHolder.itemView.getContext(), viewHolder.itemView);
        this.mViewHolder = viewHolder;
        this.mTableView = tableView;
        this.mContext = viewHolder.itemView.getContext();
        this.mXPosition = mViewHolder.getAdapterPosition();

        // find the view holder
        mViewHolder = (ColumnHeaderViewHolder) mTableView.getColumnHeaderRecyclerView()
                .findViewHolderForAdapterPosition(mXPosition);

        initialize();
    }


    private void initialize() {
        createMenuItem();
        changeMenuItemVisibility();

        this.setOnMenuItemClickListener(this);
    }

    private void createMenuItem() {

        this.getMenu().add(Menu.NONE, IMG_COLUMN, 0, mContext.getString(R.string.img));
        this.getMenu().add(Menu.NONE, ENT_COLUMN, 1, mContext.getString(R.string.ent));
       // this.getMenu().add(Menu.NONE, EVC_COLUMN, 2, mContext.getString(R.string.evc));
        //this.getMenu().add(Menu.NONE, HHL_COLUMN, 3, mContext.getString(R.string.hhl));
        this.getMenu().add(Menu.NONE, ASCENDING, 3, mContext.getString(R.string.sort_ascending));
        this.getMenu().add(Menu.NONE, DESCENDING, 4, mContext.getString(R.string.sort_descending));
        // add new one ...

    }

    private void changeMenuItemVisibility() {
        // Determine which one shouldn't be visible
        SortState sortState = mTableView.getSortingStatus(mXPosition);
        if (sortState == SortState.UNSORTED) {
            // Show others
        } else if (sortState == SortState.DESCENDING) {
            // Hide DESCENDING menu item
            getMenu().getItem(1).setVisible(false);
        } else if (sortState == SortState.ASCENDING) {
            // Hide ASCENDING menu item
            getMenu().getItem(0).setVisible(false);
        }
    }


    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        // Note: item id is index of menu item..

        switch (menuItem.getItemId()) {

            case IMG_COLUMN:

                break;


            case ASCENDING:
                mTableView.sortColumn(mXPosition, SortState.ASCENDING);

                break;
            case DESCENDING:
                mTableView.sortColumn(mXPosition, SortState.DESCENDING);
                break;

        }
        return true;
    }

}
