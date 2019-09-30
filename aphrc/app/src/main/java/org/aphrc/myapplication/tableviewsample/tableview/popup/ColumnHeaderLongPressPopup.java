
package org.aphrc.myapplication.tableviewsample.tableview.popup;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;

import org.aphrc.myapplication.tableview.ITableView;
import org.aphrc.myapplication.tableview.sort.SortState;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.tableviewsample.tableview.holder.ColumnHeaderViewHolder;

/**
 * Created on 24.12.2017.
 */

public class ColumnHeaderLongPressPopup extends PopupMenu implements PopupMenu
        .OnMenuItemClickListener {
    private static final String LOG_TAG = ColumnHeaderLongPressPopup.class.getSimpleName();

    // Menu Item constants
    private static final int ASCENDING = 1;
    private static final int DESCENDING = 2;
    private static final int HIDE_ROW = 3;
    private static final int SHOW_ROW = 4;
    private static final int SCROLL_ROW = 5;


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
        this.getMenu().add(Menu.NONE, ASCENDING, 0, mContext.getString(R.string.sort_ascending));
        this.getMenu().add(Menu.NONE, DESCENDING, 1, mContext.getString(R.string.sort_descending));
        this.getMenu().add(Menu.NONE, HIDE_ROW, 2, mContext.getString(R.string.hiding_row_sample));
        this.getMenu().add(Menu.NONE, SHOW_ROW, 3, mContext.getString(R.string.showing_row_sample));
        this.getMenu().add(Menu.NONE, SCROLL_ROW, 4, mContext.getString(R.string
                .scroll_to_row_position));
        this.getMenu().add(Menu.NONE, SCROLL_ROW, 0, "change width");
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
            case HIDE_ROW:
                mTableView.hideRow(5);
                break;
            case SHOW_ROW:
                mTableView.showRow(5);
                break;
            case SCROLL_ROW:
                mTableView.scrollToRowPosition(5);
                break;
        }
        return true;
    }

}
