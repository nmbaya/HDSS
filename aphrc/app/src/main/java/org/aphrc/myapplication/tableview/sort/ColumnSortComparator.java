
package org.aphrc.myapplication.tableview.sort;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created  on 25.11.2018.
 */

public class ColumnSortComparator extends AbstractSortComparator implements Comparator<List<ISortableModel>> {

    private int mXPosition;

    public ColumnSortComparator(int xPosition, SortState sortState) {
        this.mXPosition = xPosition;
        this.mSortState = sortState;
    }

    @Override
    public int compare(List<ISortableModel> t1, List<ISortableModel> t2) {
        Object o1 = t1.get(mXPosition).getContent();
        Object o2 = t2.get(mXPosition).getContent();

        if (mSortState == SortState.DESCENDING) {
            return compareContent(o2, o1);
        } else {
            // Default sorting process is ASCENDING
            return compareContent(o1, o2);
        }
    }

}