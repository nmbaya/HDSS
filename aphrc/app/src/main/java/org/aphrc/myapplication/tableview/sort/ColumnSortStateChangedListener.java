
package org.aphrc.myapplication.tableview.sort;

public abstract class ColumnSortStateChangedListener {

    /**
     * Dispatches sorting changes on a column to listeners.
     *
     * @param column    Column to be sorted.
     * @param sortState SortState of the column to be sorted.
     */
    public void onColumnSortStatusChanged(int column, SortState sortState) {
    }

    /**
     * Dispatches sorting changes to the row header column to listeners.
     *
     * @param sortState SortState of the row header column.
     */
    public void onRowHeaderSortStatusChanged(SortState sortState) {
    }
}
