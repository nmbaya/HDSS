
package org.aphrc.myapplication.tableview.handler;

import android.support.v7.util.DiffUtil;

import org.aphrc.myapplication.tableview.ITableView;
import org.aphrc.myapplication.tableview.adapter.recyclerview.CellRecyclerViewAdapter;
import org.aphrc.myapplication.tableview.adapter.recyclerview.ColumnHeaderRecyclerViewAdapter;
import org.aphrc.myapplication.tableview.adapter.recyclerview.RowHeaderRecyclerViewAdapter;
import org.aphrc.myapplication.tableview.sort.ColumnForRowHeaderSortComparator;
import org.aphrc.myapplication.tableview.sort.ColumnSortCallback;
import org.aphrc.myapplication.tableview.sort.ColumnSortComparator;
import org.aphrc.myapplication.tableview.sort.ColumnSortStateChangedListener;
import org.aphrc.myapplication.tableview.sort.ISortableModel;
import org.aphrc.myapplication.tableview.sort.RowHeaderForCellSortComparator;
import org.aphrc.myapplication.tableview.sort.RowHeaderSortCallback;
import org.aphrc.myapplication.tableview.sort.RowHeaderSortComparator;
import org.aphrc.myapplication.tableview.sort.SortState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created  on 24.11.2018.
 */

public class ColumnSortHandler {

    private CellRecyclerViewAdapter mCellRecyclerViewAdapter;
    private RowHeaderRecyclerViewAdapter mRowHeaderRecyclerViewAdapter;
    private ColumnHeaderRecyclerViewAdapter mColumnHeaderRecyclerViewAdapter;

    private List<ColumnSortStateChangedListener> columnSortStateChangedListeners = new ArrayList<>();
    private boolean mEnableAnimation = true;

    public boolean isEnableAnimation() {
        return mEnableAnimation;
    }

    public void setEnableAnimation(boolean mEnableAnimation) {
        this.mEnableAnimation = mEnableAnimation;
    }

    public ColumnSortHandler(ITableView tableView) {
        this.mCellRecyclerViewAdapter = (CellRecyclerViewAdapter) tableView.getCellRecyclerView()
                .getAdapter();

        this.mRowHeaderRecyclerViewAdapter = (RowHeaderRecyclerViewAdapter) tableView
                .getRowHeaderRecyclerView().getAdapter();

        this.mColumnHeaderRecyclerViewAdapter = (ColumnHeaderRecyclerViewAdapter) tableView
                .getColumnHeaderRecyclerView().getAdapter();
    }

    public void sortByRowHeader(final SortState sortState) {
        List<ISortableModel> originalRowHeaderList = mRowHeaderRecyclerViewAdapter.getItems();
        List<ISortableModel> sortedRowHeaderList = new ArrayList<>(originalRowHeaderList);

        List<List<ISortableModel>> originalList = mCellRecyclerViewAdapter.getItems();
        List<List<ISortableModel>> sortedList = new ArrayList<>(originalList);

        if (sortState != SortState.UNSORTED) {
            // Do descending / ascending sort
            Collections.sort(sortedRowHeaderList, new RowHeaderSortComparator(sortState) );

            // Sorting Columns/Cells using the same logic has sorting DataSet
            RowHeaderForCellSortComparator rowHeaderForCellSortComparator
                    = new RowHeaderForCellSortComparator(
                    originalRowHeaderList,
                    originalList,
                    sortState);

            Collections.sort(sortedList, rowHeaderForCellSortComparator);
        }

        mRowHeaderRecyclerViewAdapter.getRowHeaderSortHelper().setSortingStatus(sortState);

        // Set sorted data list
        swapItems(originalRowHeaderList, sortedRowHeaderList, sortedList, sortState);
    }

    public void sort(int column, SortState sortState) {
        List<List<ISortableModel>> originalList = mCellRecyclerViewAdapter.getItems();
        List<List<ISortableModel>> sortedList = new ArrayList<>(originalList);

        List<ISortableModel> originalRowHeaderList
                = mRowHeaderRecyclerViewAdapter.getItems();
        List<ISortableModel> sortedRowHeaderList
                = new ArrayList<>(originalRowHeaderList);

        if (sortState != SortState.UNSORTED) {
            // Do descending / ascending sort
            Collections.sort(sortedList, new ColumnSortComparator(column, sortState));

            // Sorting RowHeader using the same logic has sorting DataSet
            ColumnForRowHeaderSortComparator columnForRowHeaderSortComparator
                    = new ColumnForRowHeaderSortComparator(
                            originalRowHeaderList,
                            originalList,
                            column,
                            sortState);

            Collections.sort(sortedRowHeaderList, columnForRowHeaderSortComparator);
        }

        // Update sorting list of column headers
        mColumnHeaderRecyclerViewAdapter.getColumnSortHelper().setSortingStatus(column, sortState);

        // Set sorted data list
        swapItems(originalList, sortedList, column, sortedRowHeaderList, sortState);
    }

    private void swapItems(List<ISortableModel> oldRowHeader,
                           List<ISortableModel> newRowHeader,
                           List<List<ISortableModel>> newColumnItems,
                           SortState sortState
    ) {

        // Set new items without calling notifyCellDataSetChanged method of CellRecyclerViewAdapter
        mRowHeaderRecyclerViewAdapter.setItems(newRowHeader, !mEnableAnimation);
        mCellRecyclerViewAdapter.setItems(newColumnItems, !mEnableAnimation);

        if(mEnableAnimation) {
            // Find the differences between old cell items and new items.
            final RowHeaderSortCallback diffCallback = new RowHeaderSortCallback(oldRowHeader, newRowHeader);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            diffResult.dispatchUpdatesTo(mRowHeaderRecyclerViewAdapter);
            diffResult.dispatchUpdatesTo(mCellRecyclerViewAdapter);
        }

        for (ColumnSortStateChangedListener listener : columnSortStateChangedListeners) {
            listener.onRowHeaderSortStatusChanged(sortState);
        }
    }

    private void swapItems(List<List<ISortableModel>> oldItems, List<List<ISortableModel>>
            newItems, int column, List<ISortableModel> newRowHeader, SortState sortState) {

        // Set new items without calling notifyCellDataSetChanged method of CellRecyclerViewAdapter
        mCellRecyclerViewAdapter.setItems(newItems, !mEnableAnimation);
        mRowHeaderRecyclerViewAdapter.setItems(newRowHeader, !mEnableAnimation);

        if(mEnableAnimation) {
            // Find the differences between old cell items and new items.
            final ColumnSortCallback diffCallback = new ColumnSortCallback(oldItems, newItems, column);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            diffResult.dispatchUpdatesTo(mCellRecyclerViewAdapter);
            diffResult.dispatchUpdatesTo(mRowHeaderRecyclerViewAdapter);
        }

        for (ColumnSortStateChangedListener listener : columnSortStateChangedListeners) {
            listener.onColumnSortStatusChanged(column, sortState);
        }
    }

    public void swapItems(List<List<ISortableModel>> newItems, int column) {

        List<List<ISortableModel>> oldItems = (List<List<ISortableModel>>)
                mCellRecyclerViewAdapter.getItems();

        // Set new items without calling notifyCellDataSetChanged method of CellRecyclerViewAdapter
        mCellRecyclerViewAdapter.setItems(newItems, !mEnableAnimation);

        if(mEnableAnimation) {
            // Find the differences between old cell items and new items.
            final ColumnSortCallback diffCallback = new ColumnSortCallback(oldItems, newItems, column);
            final DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            diffResult.dispatchUpdatesTo(mCellRecyclerViewAdapter);
            diffResult.dispatchUpdatesTo(mRowHeaderRecyclerViewAdapter);
        }

    }

    public SortState getSortingStatus(int column) {
        return mColumnHeaderRecyclerViewAdapter.getColumnSortHelper().getSortingStatus(column);
    }

    public SortState getRowHeaderSortingStatus() {
        return mRowHeaderRecyclerViewAdapter.getRowHeaderSortHelper().getSortingStatus();
    }

    /**
     * Sets the listener for the changes in column sorting.
     *
     * @param listener ColumnSortStateChangedListener listener.
     */
    public void addColumnSortStateChangedListener(ColumnSortStateChangedListener listener) {
        if (columnSortStateChangedListeners == null) {
            columnSortStateChangedListeners = new ArrayList<>();
        }

        columnSortStateChangedListeners.add(listener);
    }
}
