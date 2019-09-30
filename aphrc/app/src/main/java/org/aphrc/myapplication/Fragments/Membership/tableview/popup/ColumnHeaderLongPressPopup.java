
package org.aphrc.myapplication.Fragments.Membership.tableview.popup;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;

import org.aphrc.myapplication.R;
import org.aphrc.myapplication.tableview.ITableView;
import org.aphrc.myapplication.tableview.holder.ColumnHeaderViewHolder;
import org.aphrc.myapplication.tableview.sort.SortState;

/**
 * Created on 24.12.2017.
 */

public class ColumnHeaderLongPressPopup extends PopupMenu implements PopupMenu
        .OnMenuItemClickListener {
    private static final String LOG_TAG = ColumnHeaderLongPressPopup.class.getSimpleName();

    // Menu Item constants
    private static final int ASCENDING = 0;
    private static final int DESCENDING = 1;



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

        this.getMenu().add(Menu.NONE, ASCENDING, 4, mContext.getString(R.string.sort_ascending));
        this.getMenu().add(Menu.NONE, DESCENDING, 5, mContext.getString(R.string.sort_descending));
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
