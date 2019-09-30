
package org.aphrc.myapplication.tableview.adapter;

import android.view.View;
import android.view.ViewGroup;

import org.aphrc.myapplication.tableview.ITableView;
import org.aphrc.myapplication.tableview.adapter.recyclerview.holder.AbstractViewHolder;

/**
 * Created  on 10/06/2018.
 */

public interface ITableAdapter {

    int getColumnHeaderItemViewType(int position);

    int getRowHeaderItemViewType(int position);

    int getCellItemViewType(int position);

    View getCornerView();

    AbstractViewHolder onCreateCellViewHolder(ViewGroup parent, int viewType);

    void onBindCellViewHolder(AbstractViewHolder holder, Object cellItemModel, int
            columnPosition, int rowPosition);

    AbstractViewHolder onCreateColumnHeaderViewHolder(ViewGroup parent, int viewType);

    void onBindColumnHeaderViewHolder(AbstractViewHolder holder, Object columnHeaderItemModel,
                                      int columnPosition);

    AbstractViewHolder onCreateRowHeaderViewHolder(ViewGroup parent, int viewType);

    void onBindRowHeaderViewHolder(AbstractViewHolder holder, Object rowHeaderItemModel, int
            rowPosition);

    View onCreateCornerView();

    ITableView getTableView();

    /**
     * Sets the listener for changes of data set on the TableView.
     *
     * @param listener The AdapterDataSetChangedListener listener.
     */
    void addAdapterDataSetChangedListener(AdapterDataSetChangedListener listener);
}
