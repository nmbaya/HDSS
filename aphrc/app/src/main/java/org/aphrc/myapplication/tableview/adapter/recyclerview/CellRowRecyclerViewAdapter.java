
package org.aphrc.myapplication.tableview.adapter.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

import org.aphrc.myapplication.tableview.ITableView;
import org.aphrc.myapplication.tableview.adapter.ITableAdapter;
import org.aphrc.myapplication.tableview.adapter.recyclerview.holder.AbstractViewHolder;
import org.aphrc.myapplication.tableview.adapter.recyclerview.holder.AbstractViewHolder.SelectionState;

/**
 * Created  on 10/06/2018.
 */

public class CellRowRecyclerViewAdapter<C> extends AbstractRecyclerViewAdapter<C> {

    private static final String LOG_TAG = CellRowRecyclerViewAdapter.class.getSimpleName();

    private int mYPosition;
    private ITableAdapter mTableAdapter;
    private ITableView mTableView;

    public CellRowRecyclerViewAdapter(Context context, ITableView tableView) {
        super(context, null);
        this.mTableAdapter = tableView.getAdapter();
        this.mTableView = tableView;
    }

    @Override
    public AbstractViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return mTableAdapter.onCreateCellViewHolder(parent, viewType);
    }


    @Override
    public void onBindViewHolder(final AbstractViewHolder holder, final int xPosition) {
        mTableAdapter.onBindCellViewHolder(holder, getItem(xPosition), xPosition, mYPosition);
    }

    public int getYPosition() {
        return mYPosition;
    }

    public void setYPosition(int rowPosition) {
        mYPosition = rowPosition;
    }

    @Override
    public int getItemViewType(int position) {
        return mTableAdapter.getCellItemViewType(position);
    }


    @Override
    public void onViewAttachedToWindow(AbstractViewHolder viewHolder) {
        super.onViewAttachedToWindow(viewHolder);

        SelectionState selectionState = mTableView.getSelectionHandler().getCellSelectionState
                (viewHolder.getAdapterPosition(), mYPosition);

        // Control to ignore selection color
        if (!mTableView.isIgnoreSelectionColors()) {

            // Change the background color of the view considering selected row/cell position.
            if (selectionState == SelectionState.SELECTED) {
                viewHolder.setBackgroundColor(mTableView.getSelectedColor());
            } else {
                viewHolder.setBackgroundColor(mTableView.getUnSelectedColor());
            }
        }

        // Change selection status
        viewHolder.setSelected(selectionState);
    }

    @Override
    public boolean onFailedToRecycleView(AbstractViewHolder holder) {
        return holder.onFailedToRecycleView();
    }

    @Override
    public void onViewRecycled(AbstractViewHolder holder) {
        super.onViewRecycled(holder);
        holder.onViewRecycled();
    }
}
