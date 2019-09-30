
package org.aphrc.myapplication.Fragments.Residency.tableview;

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



    public static final int IND_COLUMN_INDEX = 0;
    public static final int NAME_COLUMN_INDEX = 1;
    public static final int ROOM_COLUMN_INDEX = 2;
    public static final int START_COLUMN_INDEX = 3;
    public static final int DSTART_COLUMN_INDEX = 4;
    public static final int OSTART_COLUMN_INDEX = 5;
    public static final int END_COLUMN_INDEX = 6;
    public static final int DEND_COLUMN_INDEX = 7;
    public static final int OEND_COLUMN_INDEX = 8;
    public static final int DATE_COLUMN_INDEX = 9;
    public static final int ID_COLUMN_INDEX = 10;



    // Constant size for dummy data sets
    private static final int COLUMN_SIZE = 11;


    private Context mContext;

    public TableViewModel(Context context) {
        mContext = context;

    }

    private List<RowHeader> getSimpleRowHeaderList() {
        DataBaseHelper db =new DataBaseHelper(mContext);

        List<RowHeader> list = new ArrayList<>();
        for (int i = 1; i < db.getResidencyCount() + 1; i++) {
            RowHeader header = new RowHeader(String.valueOf(i), Integer.toString(i));
            list.add(header);
        }

        return list;
    }


    private List<ColumnHeader> getSimpleColumnHeaderList() {
        List<ColumnHeader> list = new ArrayList<>();

        for (int i = 0; i < COLUMN_SIZE; i++) {
            String title = "column " + i;
             if (i == IND_COLUMN_INDEX) {
                title = "Individual ID";
             } else if (i == NAME_COLUMN_INDEX) {
                 title = "Individual Name";
             }else if (i == ROOM_COLUMN_INDEX) {
                 title = "Room ID";
             } else if (i == START_COLUMN_INDEX) {
                title = "Start Event";
            }  else if (i == DSTART_COLUMN_INDEX) {
                 title = "Start Event Date";
             } else if (i == OSTART_COLUMN_INDEX) {
                 title = "Start Observation";
             } else if (i == END_COLUMN_INDEX) {
                 title = "End Event ";
             } else if (i == DEND_COLUMN_INDEX) {
                 title = "End Event Date";
             } else if (i == OEND_COLUMN_INDEX) {
                 title = "End Observation";
             } else if (i == DATE_COLUMN_INDEX) {
                 title = "Interview Date";
             } else if (i == OEND_COLUMN_INDEX) {
                 title = "OID";
             }


                ColumnHeader header = new ColumnHeader(String.valueOf(i), title);
                list.add(header);
            }

            return list;
        }


    public List<List<Cell>> getCellList() {
        DataBaseHelper db = new DataBaseHelper(mContext) ;
        return db.getAllResidencys(1);
        // return getCellListForSortingTest();
    }

    public List<RowHeader> getRowHeaderList() {
        return getSimpleRowHeaderList();
    }

    public List<ColumnHeader> getColumnHeaderList() {
        return getSimpleColumnHeaderList();
    }

}
