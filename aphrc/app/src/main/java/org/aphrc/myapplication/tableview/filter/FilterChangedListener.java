
package org.aphrc.myapplication.tableview.filter;

import java.util.List;

public abstract class FilterChangedListener<T> {

    /**
     * Called when a filter has been changed.
     *
     * @param filteredCellItems      The list of filtered cell items.
     * @param filteredRowHeaderItems The list of filtered row items.
     */
    public void onFilterChanged(List<List<T>> filteredCellItems, List<T> filteredRowHeaderItems) {
    }

    /**
     * Called when the TableView filters are cleared.
     *
     * @param originalCellItems      The unfiltered cell item list.
     * @param originalRowHeaderItems The unfiltered row header list.
     */
    public void onFilterCleared(List<List<T>> originalCellItems, List<T> originalRowHeaderItems) {
    }
}
