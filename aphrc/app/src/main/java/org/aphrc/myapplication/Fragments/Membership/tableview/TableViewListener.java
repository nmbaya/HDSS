
package org.aphrc.myapplication.Fragments.Membership.tableview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.aphrc.myapplication.Fragments.Membership.tableview.popup.ColumnHeaderLongPressPopup;
import org.aphrc.myapplication.Fragments.Membership.tableview.popup.RowHeaderLongPressPopup;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.tableview.TableView;
import org.aphrc.myapplication.tableview.holder.CellViewHolder;
import org.aphrc.myapplication.tableview.holder.ColumnHeaderViewHolder;
import org.aphrc.myapplication.tableview.listener.ITableViewListener;
import org.aphrc.myapplication.tableview.model.Cell;

/**
 * Created on 21/09/2017.
 */

public class TableViewListener implements ITableViewListener {

    private Toast mToast;
    private Context mContext;
    private TableView mTableView;
private CellViewHolder cellViewHolder;


    public TableViewListener(TableView tableView) {
        this.mContext = tableView.getContext();
        this.mTableView = tableView;
        //this.cellViewHolder=tableView.getCellRecyclerView().findContainingViewHolder();
    }

    /**
     * Called when user click any cell item.
     *
     * @param cellView : Clicked Cell ViewHolder.
     * @param column   : X (Column) position of Clicked Cell item.
     * @param row      : Y (Row) position of Clicked Cell item.
     */
    @Override
    public void onCellClicked(@NonNull RecyclerView.ViewHolder cellView, int column, int row) {

        // Do what you want.

     //   showToast("Cell " + column + " " + row + " "+ mTableView.getAdapter().getCellItem(column,row) + " has been clicked.");

    }

    /**
     * Called when user long press any cell item.
     *
     * @param cellView : Long Pressed Cell ViewHolder.
     * @param column   : X (Column) position of Long Pressed Cell item.
     * @param row      : Y (Row) position of Long Pressed Cell item.
     */
    @Override
    public void onCellLongPressed(@NonNull RecyclerView.ViewHolder cellView, final int column,
                                  int row) {


        mTableView.setSelectedRow(row);
        Cell id= (Cell)mTableView.getAdapter().getCellItem(0,row);
        Cell name= (Cell)mTableView.getAdapter().getCellItem(5,row);

        GlobalClass.memID= id.getData().toString();
        GlobalClass.IndividualName= id.getData().toString();

        CellViewHolder cellViewHolder = (CellViewHolder) cellView;
        // Do What you want
        if (cellView != null) {
            // Create Long Press Popup

            org.aphrc.myapplication.Fragments.Membership.tableview.popup.RowHeaderLongPressPopup popup = new org.aphrc.myapplication.Fragments.Membership.tableview.popup.RowHeaderLongPressPopup(cellView, mTableView);
            // Show
            popup.show();
        }
    }

    /**
     * Called when user click any column header item.
     *
     * @param columnHeaderView : Clicked Column Header ViewHolder.
     * @param column           : X (Column) position of Clicked Column Header item.
     */
    @Override
    public void onColumnHeaderClicked(@NonNull RecyclerView.ViewHolder columnHeaderView, int
            column) {
        // Do what you want.
      //  showToast("Column header  " + column + " has been clicked.");
    }

    /**
     * Called when user long press any column header item.
     *
     * @param columnHeaderView : Long Pressed Column Header ViewHolder.
     * @param column           : X (Column) position of Long Pressed Column Header item.
     */
    @Override
    public void onColumnHeaderLongPressed(@NonNull RecyclerView.ViewHolder columnHeaderView, int
            column) {

        if (columnHeaderView != null && columnHeaderView instanceof ColumnHeaderViewHolder) {
            // Create Long Press Popup
            ColumnHeaderLongPressPopup popup = new ColumnHeaderLongPressPopup(
                    (ColumnHeaderViewHolder) columnHeaderView, mTableView);
            // Show
            popup.show();
        }
    }

    /**
     * Called when user click any Row Header item.
     *
     * @param rowHeaderView : Clicked Row Header ViewHolder.
     * @param row           : Y (Row) position of Clicked Row Header item.
     */
    @Override
    public void onRowHeaderClicked(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {
        // Do what you want.


      //  showToast("Row header " + row + " has been clicked.");
    }

    /**
     * Called when user long press any row header item.
     *
     * @param rowHeaderView : Long Pressed Row Header ViewHolder.
     * @param row           : Y (Row) position of Long Pressed Row Header item.
     */
    @Override
    public void onRowHeaderLongPressed(@NonNull RecyclerView.ViewHolder rowHeaderView, int row) {

        if (rowHeaderView != null) {
            // Create Long Press Popup

            RowHeaderLongPressPopup popup = new RowHeaderLongPressPopup(rowHeaderView, mTableView);
            // Show
            popup.show();
        }
    }


    private void showToast(String p_strMessage) {
        if (mToast == null) {
            mToast = Toast.makeText(mContext, "", Toast.LENGTH_SHORT);
        }

        mToast.setText(p_strMessage);
        mToast.show();
    }
}
