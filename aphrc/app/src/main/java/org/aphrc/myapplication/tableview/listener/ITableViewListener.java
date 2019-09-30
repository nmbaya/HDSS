
package org.aphrc.myapplication.tableview.listener;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

/**
 * Created  on 20/09/2018.
 */

public interface ITableViewListener {

    void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int
            row);

    void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, int column, int
            row);

    void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int
            column);

    void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int
            column);

    void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row);

    void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, int
            row);


}
