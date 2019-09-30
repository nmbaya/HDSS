
package org.aphrc.myapplication.Fragments.HouseholdRegistrationBook.tableview.popup;

import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.PopupMenu;

import org.aphrc.myapplication.Fragments.HouseholdRegistrationBook.HouseholdRegistrationBookActivity;
import org.aphrc.myapplication.R;
import org.aphrc.myapplication.tableview.ITableView;
import org.aphrc.myapplication.tableview.holder.ColumnHeaderViewHolder;
import org.aphrc.myapplication.tableview.sort.SortState;

//import org.aphrc.myapplication.expandablelistview.ExpandableActivity;

/**
 * Created on 24.12.2017.
 */

public class ColumnHeaderLongPressPopup extends PopupMenu implements PopupMenu
        .OnMenuItemClickListener {
    private static final String LOG_TAG = ColumnHeaderLongPressPopup.class.getSimpleName();

    // Menu Item constants
    private static final int NEW_HRB = 1;
    private static final int ASCENDING = 2;
    private static final int DESCENDING = 3;
    //private static final int WIDTH_ROW = 4;
   // private static final int EXP_ROW = 5;


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
        this.getMenu().add(Menu.NONE, NEW_HRB, 0, "Add New HRB");
        this.getMenu().add(Menu.NONE, ASCENDING, 1, mContext.getString(R.string.sort_ascending));
        this.getMenu().add(Menu.NONE, DESCENDING, 2, mContext.getString(R.string.sort_descending));
       // this.getMenu().add(Menu.NONE, WIDTH_ROW, 0, "change width");
       // this.getMenu().add(Menu.NONE, EXP_ROW, 0, "Expandable View");
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
            case NEW_HRB:
                Intent r  = new Intent(mContext, HouseholdRegistrationBookActivity.class);
                r.putExtra("mode","N");
                mContext.startActivity(r);

              //  mContext.startActivity( )
                break;

            case ASCENDING:
                mTableView.sortColumn(mXPosition, SortState.ASCENDING);

                break;
            case DESCENDING:
                mTableView.sortColumn(mXPosition, SortState.DESCENDING);
                break;

          /*  case WIDTH_ROW:
                mTableView.hideRow(5);
                break;

            case EXP_ROW:
               // Intent exp  = new Intent(mContext, MainActivitySearch.class);
               // exp.putExtra("Mode","N");
               // mContext.startActivity(exp);*/

        }
        return true;
    }



}
