
package org.aphrc.myapplication.tableview.listener;

import android.view.View;

import org.aphrc.myapplication.tableview.ITableView;
import org.aphrc.myapplication.tableview.adapter.recyclerview.CellRecyclerView;
import org.aphrc.myapplication.tableview.layoutmanager.CellLayoutManager;

import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created  on 21.01.2018.
 */

public class TableViewLayoutChangeListener implements View.OnLayoutChangeListener {

    private CellRecyclerView mCellRecyclerView;
    private CellRecyclerView mColumnHeaderRecyclerView;
    private CellLayoutManager mCellLayoutManager;

    public TableViewLayoutChangeListener(ITableView tableView) {
        this.mCellRecyclerView = tableView.getCellRecyclerView();
        this.mColumnHeaderRecyclerView = tableView.getColumnHeaderRecyclerView();
        this.mCellLayoutManager = tableView.getCellLayoutManager();
    }


    @Override
    public void onLayoutChange(View v, int left, int top, int right, int bottom, int oldLeft, int
            oldTop, int oldRight, int oldBottom) {

        if (v.isShown() && (right - left) != (oldRight - oldLeft)) {

            // Control who need the remeasure
            if (mColumnHeaderRecyclerView.getWidth() > mCellRecyclerView.getWidth()) {
                // Remeasure all nested CellRow recyclerViews
                mCellLayoutManager.remeasureAllChild();

            } else if (mCellRecyclerView.getWidth() > mColumnHeaderRecyclerView.getWidth()) {

                // It seems Column Header is needed.
                mColumnHeaderRecyclerView.getLayoutParams().width = WRAP_CONTENT;
                mColumnHeaderRecyclerView.requestLayout();
            }
        }
    }
}
