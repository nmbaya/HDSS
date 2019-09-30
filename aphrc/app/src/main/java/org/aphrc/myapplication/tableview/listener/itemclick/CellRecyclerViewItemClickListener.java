
package org.aphrc.myapplication.tableview.listener.itemclick;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import org.aphrc.myapplication.tableview.ITableView;
import org.aphrc.myapplication.tableview.adapter.recyclerview.CellRecyclerView;
import org.aphrc.myapplication.tableview.adapter.recyclerview.CellRowRecyclerViewAdapter;
import org.aphrc.myapplication.tableview.adapter.recyclerview.holder.AbstractViewHolder;

/**
 * Created on 26/09/2018.
 */

public class CellRecyclerViewItemClickListener extends AbstractItemClickListener {
    private static final String LOG_TAG = CellRecyclerViewItemClickListener.class.getSimpleName();

    private CellRecyclerView mCellRecyclerView;

    public CellRecyclerViewItemClickListener(CellRecyclerView recyclerView, ITableView tableView) {
        super(recyclerView, tableView);
        this.mCellRecyclerView = tableView.getCellRecyclerView();
    }

    @Override
    protected boolean clickAction(RecyclerView view, MotionEvent e) {
        // Get interacted view from x,y coordinate.
        View childView = view.findChildViewUnder(e.getX(), e.getY());

        if (childView != null && mGestureDetector.onTouchEvent(e)) {
            // Find the view holder
            AbstractViewHolder holder = (AbstractViewHolder) mRecyclerView.getChildViewHolder
                    (childView);

            // Get y position from adapter
            CellRowRecyclerViewAdapter adapter = (CellRowRecyclerViewAdapter) mRecyclerView
                    .getAdapter();

            int column = holder.getAdapterPosition();
            int row = adapter.getYPosition();

            // Control to ignore selection color
            if (!mTableView.isIgnoreSelectionColors()) {
                mSelectionHandler.setSelectedCellPositions(holder, column, row);
            }

            if (getTableViewListener() != null) {
                // Call ITableView listener for item click
                getTableViewListener().onCellClicked(holder, column, row);
            }

            return true;
        }
        return false;
    }

    protected void longPressAction(MotionEvent e) {
        // Consume the action for the time when either the cell row recyclerView or
        // the cell recyclerView is scrolling.
        if ((mRecyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE) ||
                (mCellRecyclerView.getScrollState() != RecyclerView.SCROLL_STATE_IDLE)) {
            return;
        }

        // Get interacted view from x,y coordinate.
        View child = mRecyclerView.findChildViewUnder(e.getX(), e.getY());

        if (child != null && getTableViewListener() != null) {
            // Find the view holder
            RecyclerView.ViewHolder holder = mRecyclerView.getChildViewHolder(child);

            // Get y position from adapter
            CellRowRecyclerViewAdapter adapter = (CellRowRecyclerViewAdapter) mRecyclerView
                    .getAdapter();

            // Call ITableView listener for long click
            getTableViewListener().onCellLongPressed(holder, holder.getAdapterPosition(), adapter
                    .getYPosition());
        }
    }
}