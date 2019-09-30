
package org.aphrc.myapplication.tableview.adapter.recyclerview.holder;

import android.view.View;

import org.aphrc.myapplication.tableview.sort.SortState;

/**
 * Created  on 16.12.2018.
 */

public class AbstractSorterViewHolder extends AbstractViewHolder {

    private SortState mSortState = SortState.UNSORTED;

    public AbstractSorterViewHolder(View itemView) {
        super(itemView);
    }

    public void onSortingStatusChanged(SortState pSortState) {
        this.mSortState = pSortState;
    }

    public SortState getSortState() {
        return mSortState;
    }
}
