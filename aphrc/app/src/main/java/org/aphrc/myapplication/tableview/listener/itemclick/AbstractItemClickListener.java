
package org.aphrc.myapplication.tableview.listener.itemclick;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;

import org.aphrc.myapplication.tableview.ITableView;
import org.aphrc.myapplication.tableview.adapter.recyclerview.CellRecyclerView;
import org.aphrc.myapplication.tableview.handler.SelectionHandler;
import org.aphrc.myapplication.tableview.listener.ITableViewListener;

/**
 * Created on 22.11.2018.
 */

public abstract class AbstractItemClickListener implements RecyclerView.OnItemTouchListener {
    private ITableViewListener mListener;
    protected GestureDetector mGestureDetector;
    protected CellRecyclerView mRecyclerView;
    protected SelectionHandler mSelectionHandler;
    protected ITableView mTableView;

    public AbstractItemClickListener(CellRecyclerView recyclerView, ITableView tableView) {
        this.mRecyclerView = recyclerView;
        this.mTableView = tableView;
        this.mSelectionHandler = tableView.getSelectionHandler();

        mGestureDetector = new GestureDetector(mRecyclerView.getContext(), new GestureDetector
                .SimpleOnGestureListener() {

            MotionEvent start;

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                return clickAction(mRecyclerView, e);
            }

            @Override
            public boolean onDown(MotionEvent e) {
                start = e;
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                // Check distance to prevent scroll to trigger the event
                if (start != null && Math.abs(start.getRawX() - e.getRawX()) < 20 && Math.abs
                        (start.getRawY() - e.getRawY()) < 20) {
                    longPressAction(e);
                }
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView view, MotionEvent e) {
        return mGestureDetector.onTouchEvent(e);
    }

    @Override
    public void onTouchEvent(RecyclerView view, MotionEvent motionEvent) { }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}


    protected ITableViewListener getTableViewListener() {
        if (mListener == null) {
            mListener = mTableView.getTableViewListener();
        }
        return mListener;
    }

    abstract protected boolean clickAction(RecyclerView view, MotionEvent e);

    abstract protected void longPressAction(MotionEvent e);
}
