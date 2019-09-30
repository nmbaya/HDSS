
package org.aphrc.myapplication.LocationObservation.tableview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.aphrc.myapplication.tableview.holder.ColumnHeaderViewHolder;
import org.aphrc.myapplication.LocationObservation.tableview.popup.ColumnHeaderLongPressPopup;
import org.aphrc.myapplication.LocationObservation.tableview.popup.RowHeaderLongPressPopup;
import org.aphrc.myapplication.tableview.TableView;
import org.aphrc.myapplication.tableview.listener.ITableViewListener;
import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.tableview.holder.CellViewHolder;
import org.aphrc.myapplication.tableview.model.Cell;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        showToast("Cell " + column + " " + row + " "+ mTableView.getAdapter().getCellItem(column,row) + " has been clicked.");

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
        GlobalClass.IndividualID="";
        GlobalClass.IndividualName="";
        GlobalClass.IndividualSex="";
        GlobalClass.IndividualAge=0;
        GlobalClass.IndividualDOB="";
        GlobalClass.IndividualStatus="";
        GlobalClass.motherID="";
        GlobalClass.pgostatus="";
        GlobalClass.lastStatus="";
        GlobalClass.maritalStatus="";
        GlobalClass.eActivity="";
        GlobalClass.motherAlive="";
        GlobalClass.fatherAlive="";
        GlobalClass.rltHHH="";

        GlobalClass.education="";
        GlobalClass.educationLevel="";
        GlobalClass.educationClass="";



        Cell id= (Cell)mTableView.getAdapter().getCellItem(0,row);
        Cell name= (Cell)mTableView.getAdapter().getCellItem(1,row);
        Cell sex= (Cell)mTableView.getAdapter().getCellItem(2,row);
        Cell age= (Cell)mTableView.getAdapter().getCellItem(3,row);
        Cell dob= (Cell)mTableView.getAdapter().getCellItem(4,row);
        Cell eth= (Cell)mTableView.getAdapter().getCellItem(5,row);
        Cell rhhh= (Cell)mTableView.getAdapter().getCellItem(6,row);
        Cell sevent= (Cell)mTableView.getAdapter().getCellItem(7,row);
        Cell sdate= (Cell)mTableView.getAdapter().getCellItem(8,row);
        Cell status= (Cell)mTableView.getAdapter().getCellItem(9,row);
        Cell pgo= (Cell)mTableView.getAdapter().getCellItem(10,row);
        Cell malive= (Cell)mTableView.getAdapter().getCellItem(11,row);
        Cell falive= (Cell)mTableView.getAdapter().getCellItem(12,row);
        Cell activity= (Cell)mTableView.getAdapter().getCellItem(13,row);
        Cell mstatus= (Cell)mTableView.getAdapter().getCellItem(14,row);
        Cell education= (Cell)mTableView.getAdapter().getCellItem(15,row);
        Cell educationLevel= (Cell)mTableView.getAdapter().getCellItem(16,row);
        Cell educationClass= (Cell)mTableView.getAdapter().getCellItem(17,row);
        Cell vis= (Cell)mTableView.getAdapter().getCellItem(18,row);
        Cell loc= (Cell)mTableView.getAdapter().getCellItem(19,row);
        Cell sgp= (Cell)mTableView.getAdapter().getCellItem(20,row);


       // GlobalClass.rltHHH= rhhh.getData().toString();
        GlobalClass.IndividualID= id.getData().toString();
        GlobalClass.IndividualName= name.getData().toString();
        GlobalClass.IndividualSex= sex.getData().toString();
        GlobalClass.IndividualDOB= dob.getData().toString();
        GlobalClass.IndividualAge= Integer.parseInt(age.getData().toString());

         if (Integer.parseInt(vis.getData().toString())==1){
             GlobalClass.Visitor= true;
         }
         else{
             GlobalClass.Visitor= false;
         }

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {

            GlobalClass.StartEventDate = format.parse(sdate.getData().toString());

        } catch (ParseException e) {
            e.printStackTrace();
        }




        if (!(rhhh.getData()==null)) {
            GlobalClass.rltHHH= rhhh.getData().toString();
        }
        if (!(pgo.getData()==null)) {
            GlobalClass.pgostatus= pgo.getData().toString();
        }
        if (!(status.getData()==null)){
            GlobalClass.lastStatus= status.getData().toString();
        }
        if (!(mstatus.getData()==null)){
            GlobalClass.maritalStatus= mstatus.getData().toString();
        }
        if (!(activity.getData()==null)){
            GlobalClass.eActivity= activity.getData().toString();
        }
        if (!(malive.getData()==null)){
            GlobalClass.motherAlive= malive.getData().toString();
        }
        if (!(falive.getData()==null)){
            GlobalClass.fatherAlive= falive.getData().toString();
        }

        //education
        if (!(education.getData()==null)) {
            GlobalClass.education= education.getData().toString();
        }

        if (!(educationLevel.getData()==null)) {
            GlobalClass.educationLevel= educationLevel.getData().toString();
        }

        if (!(educationClass.getData()==null)) {
            GlobalClass.educationClass= educationClass.getData().toString();
        }
        GlobalClass.locationID= loc.getData().toString();
        GlobalClass.socialgroupID= sgp.getData().toString();

        CellViewHolder cellViewHolder = (CellViewHolder) cellView;
       // GlobalClass.IndividualID= cellViewHolder.cell_textview.getText().toString();
        // Do What you want


            if (cellView != null) {
                // Create Long Press Popup

                RowHeaderLongPressPopup popup = new RowHeaderLongPressPopup(cellView, mTableView);
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
        showToast("Column header  " + column + " has been clicked.");
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


        showToast("Row header " + row + " has been clicked.");
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
