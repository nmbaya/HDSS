
package org.aphrc.myapplication.tableview.adapter;

import java.util.List;

public abstract class AdapterDataSetChangedListener<CH, RH, C> {

    /**
     * Dispatches changes on column header items to listener.
     *
     * @param columnHeaderItems The current column header items.
     */
    public void onColumnHeaderItemsChanged(List<CH> columnHeaderItems) {
    }

    /**
     * Dispatches changes on row header items to listener.
     *
     * @param rowHeaderItems The current row header items.
     */
    public void onRowHeaderItemsChanged(List<RH> rowHeaderItems) {
    }

    /**
     * Dispatches changes on cell items to listener.
     *
     * @param cellItems The current cell items.
     */
    public void onCellItemsChanged(List<List<C>> cellItems) {
    }

    /**
     * Dispatches the changes on column header, row header and cell items.
     *
     * @param columnHeaderItems The current column header items.
     * @param rowHeaderItems    The current row header items.
     * @param cellItems         The current cell items.
     */
    public void onDataSetChanged(
            List<CH> columnHeaderItems,
            List<RH> rowHeaderItems,
            List<List<C>> cellItems) {
    }
}
