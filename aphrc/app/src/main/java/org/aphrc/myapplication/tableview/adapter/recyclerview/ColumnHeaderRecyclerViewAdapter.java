
package org.aphrc.myapplication.tableview.adapter.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

import org.aphrc.myapplication.tableview.ITableView;
import org.aphrc.myapplication.tableview.adapter.ITableAdapter;
import org.aphrc.myapplication.tableview.adapter.recyclerview.holder.AbstractSorterViewHolder;
import org.aphrc.myapplication.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import org.aphrc.myapplication.tableview.adapter.recyclerview.holder.AbstractViewHolder.SelectionState;
import org.aphrc.myapplication.tableview.sort.ColumnSortHelper;
import org.aphrc.myapplication.tableview.sort.SortState;

import java.util.List;

/**
 * Created by evrencoskun on 10/06/2017.
 */

public class ColumnHeaderRecyclerViewAdapter<CH> extends AbstractRecyclerViewAdapter<CH> {
    private static final String LOG_TAG = ColumnHeaderRecyclerViewAdapter.class.getSimpleName();

    private ITableAdapter mTableAdapter;
    private ITableView mTableView;
    private ColumnSortHelper mColumnSortHelper;

    public ColumnHeaderRecyclerViewAdapter(Context context, List<CH> itemList, ITableAdapter
            tableAdapter) {
        super(context, itemList);
        this.mTableAdapter = tableAdapter;
        this.mTableView = tableAdapter.getTableView();
    }

    @Override
    public AbstractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mTableAdapter.onCreateColumnHeaderViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(AbstractViewHolder holder, int position) {
        mTableAdapter.onBindColumnHeaderViewHolder(holder, getItem(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        return mTableAdapter.getColumnHeaderItemViewType(position);
    }

    @Override
    public void onViewAttachedToWindow(AbstractViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);

        SelectionState selectionState = mTableView.getSelectionHandler().getColumnSelectionState
                (viewHolder.getAdapterPosition());

        // Control to ignore selection color
        if (!mTableView.isIgnoreSelectionColors()) {

            // Change background color of the view considering it's selected state
            mTableView.getSelectionHandler().changeColumnBackgroundColorBySelectionStatus
                    (viewHolder, selectionState);
        }

        // Change selection status
        viewHolder.setSelected(selectionState);

        // Control whether the TableView is sortable or not.
        if (mTableView.isSortable()) {
            if (viewHolder instanceof AbstractSorterViewHolder) {
                // Get its sorting state
                SortState state = getColumnSortHelper().getSortingStatus(viewHolder
                        .getAdapterPosition());
                // Fire onSortingStatusChanged
                ((AbstractSorterViewHolder) viewHolder).onSortingStatusChanged(state);
            }
        }
    }


    public ColumnSortHelper getColumnSortHelper() {
        if (mColumnSortHelper == null) {
            // It helps to store sorting state of column headers
            this.mColumnSortHelper = new ColumnSortHelper(mTableView.getColumnHeaderLayoutManager
                    ());
        }
        return mColumnSortHelper;
    }
}
