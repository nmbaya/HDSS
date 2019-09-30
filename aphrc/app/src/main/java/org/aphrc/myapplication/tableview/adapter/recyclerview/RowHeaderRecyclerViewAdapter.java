
package org.aphrc.myapplication.tableview.adapter.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

import org.aphrc.myapplication.tableview.ITableView;
import org.aphrc.myapplication.tableview.adapter.ITableAdapter;
import org.aphrc.myapplication.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import org.aphrc.myapplication.tableview.adapter.recyclerview.holder.AbstractViewHolder.SelectionState;
import org.aphrc.myapplication.tableview.sort.RowHeaderSortHelper;

import java.util.List;

/**
 * Created  on 10/06/2018.
 */

public class RowHeaderRecyclerViewAdapter<RH> extends AbstractRecyclerViewAdapter<RH> {

    private ITableAdapter mTableAdapter;
    private ITableView mTableView;
    private RowHeaderSortHelper mRowHeaderSortHelper;

    public RowHeaderRecyclerViewAdapter(Context context, List<RH> itemList, ITableAdapter
            tableAdapter) {
        super(context, itemList);
        this.mTableAdapter = tableAdapter;
        this.mTableView = tableAdapter.getTableView();
    }

    @Override
    public AbstractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mTableAdapter.onCreateRowHeaderViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(AbstractViewHolder holder, int position) {
        mTableAdapter.onBindRowHeaderViewHolder(holder, getItem(position), position);
    }

    @Override
    public int getItemViewType(int position) {
        return mTableAdapter.getRowHeaderItemViewType(position);
    }

    @Override
    public void onViewAttachedToWindow(AbstractViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);

        SelectionState selectionState = mTableView.getSelectionHandler().getRowSelectionState
                (viewHolder.getAdapterPosition());

        // Control to ignore selection color
        if (!mTableView.isIgnoreSelectionColors()) {

            // Change background color of the view considering it's selected state
            mTableView.getSelectionHandler().changeRowBackgroundColorBySelectionStatus
                    (viewHolder, selectionState);
        }

        // Change selection status
        viewHolder.setSelected(selectionState);
    }

    public RowHeaderSortHelper getRowHeaderSortHelper() {
        if (mRowHeaderSortHelper == null) {
            // It helps to store sorting state of row headers
            this.mRowHeaderSortHelper = new RowHeaderSortHelper();
        }
        return mRowHeaderSortHelper;
    }

}
