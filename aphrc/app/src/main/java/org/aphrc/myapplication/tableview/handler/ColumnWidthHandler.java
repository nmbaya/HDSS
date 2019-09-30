
package org.aphrc.myapplication.tableview.handler;

import org.aphrc.myapplication.tableview.ITableView;

/**
 * Created by evrencoskun on 25.04.2018.
 */

public class ColumnWidthHandler {

    private ITableView mTableView;

    public ColumnWidthHandler(ITableView tableView) {
        mTableView = tableView;
    }

    public void setColumnWidth(int columnPosition, int width) {

        // Firstly set the column header cache map
        mTableView.getColumnHeaderLayoutManager().setCacheWidth(columnPosition, width);

        // Set each of cell items that is located on the column position
        mTableView.getCellLayoutManager().setCacheWidth(columnPosition, width);
    }


}
