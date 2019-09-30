
package org.aphrc.myapplication.Fragments.HouseholdRegistrationBook.tableview;

import android.content.Context;

import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.tableview.model.Cell;
import org.aphrc.myapplication.tableview.model.ColumnHeader;
import org.aphrc.myapplication.tableview.model.RowHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 29.04.2019.
 */

public class TableViewModel {

    // Columns indexes
    public static final int HRB_INDEX = 0;
    public static final int ROOM_INDEX = 1;
    public static final int STAFF_INDEX = 2;
    public static final int DATE_INDEX = 3;
    public static final int OID_INDEX = 4;

    // Constant size for dummy data sets
    private static final int COLUMN_SIZE = 5;


    private Context mContext;

    public TableViewModel(Context context) {
        mContext = context;

    }

    private List<RowHeader> getSimpleRowHeaderList() {
        DataBaseHelper db =new DataBaseHelper(mContext);

        List<RowHeader> list = new ArrayList<>();
        for (int i = 1; i < db.getHRBCount() + 1; i++) {
            RowHeader header = new RowHeader(String.valueOf(i), Integer.toString(i));
            list.add(header);
        }

        return list;
    }


    private List<ColumnHeader> getSimpleColumnHeaderList() {
        List<ColumnHeader> list = new ArrayList<>();

        for (int i = 0; i < COLUMN_SIZE; i++) {
            String title = "column " + i;
             if (i == HRB_INDEX) {
                title = "HRB NO.";
            } else if (i == ROOM_INDEX) {
                title = "No of Rooms";
            }else if (i == STAFF_INDEX) {
                 title = "Created By";
             } else if (i == DATE_INDEX) {
                title = "Date Created";
            }else if (i == OID_INDEX) {
                 title = "Record ID";
             }
                ColumnHeader header = new ColumnHeader(String.valueOf(i), title);
                list.add(header);
            }

            return list;
        }


    public List<List<Cell>> getCellList() {
        DataBaseHelper db = new DataBaseHelper(mContext) ;
        return db.getAllHRBs(1);
        // return getCellListForSortingTest();
    }

    public List<RowHeader> getRowHeaderList() {
        return getSimpleRowHeaderList();
    }

    public List<ColumnHeader> getColumnHeaderList() {
        return getSimpleColumnHeaderList();
    }

}
