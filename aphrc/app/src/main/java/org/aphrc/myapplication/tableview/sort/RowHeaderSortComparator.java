package org.aphrc.myapplication.tableview.sort;

import java.util.Comparator;

/**
 * Created  on 6/2/18.
 */

public class RowHeaderSortComparator extends AbstractSortComparator implements Comparator<ISortableModel> {

    private static final String LOG_TAG = RowHeaderSortComparator.class.getSimpleName();


    public RowHeaderSortComparator(SortState sortState) {
        this.mSortState = sortState;
    }

    @Override
    public int compare(ISortableModel o1, ISortableModel o2) {
        if (mSortState == SortState.DESCENDING) {
            return compareContent(o2.getContent(), o1.getContent());
        } else {
            return compareContent(o1.getContent(), o2.getContent());
        }
    }
}
