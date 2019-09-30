
package org.aphrc.myapplication.tableview;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import org.aphrc.myapplication.tableview.adapter.AbstractTableAdapter;
import org.aphrc.myapplication.tableview.adapter.recyclerview.CellRecyclerView;
import org.aphrc.myapplication.tableview.filter.Filter;
import org.aphrc.myapplication.tableview.handler.ColumnSortHandler;
import org.aphrc.myapplication.tableview.handler.FilterHandler;
import org.aphrc.myapplication.tableview.handler.ScrollHandler;
import org.aphrc.myapplication.tableview.handler.SelectionHandler;
import org.aphrc.myapplication.tableview.layoutmanager.CellLayoutManager;
import org.aphrc.myapplication.tableview.layoutmanager.ColumnHeaderLayoutManager;
import org.aphrc.myapplication.tableview.listener.ITableViewListener;
import org.aphrc.myapplication.tableview.listener.scroll.HorizontalRecyclerViewListener;
import org.aphrc.myapplication.tableview.listener.scroll.VerticalRecyclerViewListener;
import org.aphrc.myapplication.tableview.sort.SortState;

/**
 * Created  on 19/06/2018.
 */

public interface ITableView {

    void addView(View child, ViewGroup.LayoutParams params);

    boolean hasFixedWidth();

    boolean isIgnoreSelectionColors();

    boolean isShowHorizontalSeparators();
    
    boolean isShowVerticalSeparators();

    boolean isSortable();

    CellRecyclerView getCellRecyclerView();

    CellRecyclerView getColumnHeaderRecyclerView();

    CellRecyclerView getRowHeaderRecyclerView();

    ColumnHeaderLayoutManager getColumnHeaderLayoutManager();

    CellLayoutManager getCellLayoutManager();

    LinearLayoutManager getRowHeaderLayoutManager();

    HorizontalRecyclerViewListener getHorizontalRecyclerViewListener();

    VerticalRecyclerViewListener getVerticalRecyclerViewListener();

    ITableViewListener getTableViewListener();

    SelectionHandler getSelectionHandler();
    
    ColumnSortHandler getColumnSortHandler();

    DividerItemDecoration getHorizontalItemDecoration();
    
    DividerItemDecoration getVerticalItemDecoration();

    SortState getSortingStatus(int column);

    SortState getRowHeaderSortingStatus();

    void scrollToColumnPosition(int column);

    void scrollToColumnPosition(int column, int offset);

    void scrollToRowPosition(int row);

    void scrollToRowPosition(int row, int offset);

    void showRow(int row);

    void hideRow(int row);

    boolean isRowVisible(int row);

    void showAllHiddenRows();

    void clearHiddenRowList();

    void showColumn(int column);

    void hideColumn(int column);

    boolean isColumnVisible(int column);

    void showAllHiddenColumns();

    void clearHiddenColumnList();

    int getShadowColor();

    int getSelectedColor();

    int getUnSelectedColor();

    int getSeparatorColor();

    void sortColumn(int columnPosition, SortState sortState);

    void sortRowHeader(SortState sortState);

    void remeasureColumnWidth(int column);

    int getRowHeaderWidth();

    void setRowHeaderWidth(int rowHeaderWidth);

    AbstractTableAdapter getAdapter();

    /**
     * Filters the whole table using the provided Filter object which supports multiple filters.
     *
     * @param filter The filter object.
     */
    void filter(Filter filter);

    /**
     * Retrieves the FilterHandler of the TableView.
     *
     * @return The FilterHandler of the TableView.
     */
    FilterHandler getFilterHandler();
    
    /**
     * Retrieves the ScrollHandler of the TableView.
     *
     * @return The ScrollHandler of the TableView.
     */
    ScrollHandler getScrollHandler();
}
