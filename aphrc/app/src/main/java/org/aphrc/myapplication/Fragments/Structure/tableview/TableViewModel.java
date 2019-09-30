
package org.aphrc.myapplication.Fragments.Structure.tableview;

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

    public static final int SITE_COLUMN_INDEX = 0;
    public static final int DSS_COLUMN_INDEX = 1;
    public static final int HRB_COLUMN_INDEX = 2;
    public static final int ASTR_COLUMN_INDEX = 3;
    public static final int BSTR_COLUMN_INDEX = 4;
    public static final int OID_COLUMN_INDEX = 5;


    // Constant size data sets
    private static final int COLUMN_SIZE = 6;


    private Context mContext;

    public TableViewModel(Context context) {
        mContext = context;

    }

    private List<RowHeader> getSimpleRowHeaderList() {
        DataBaseHelper db =new DataBaseHelper(mContext);

        List<RowHeader> list = new ArrayList<>();
        for (int i = 1; i < db.getStructureCount() + 1; i++) {
            RowHeader header = new RowHeader(String.valueOf(i), Integer.toString(i));
            list.add(header);
        }

        return list;
    }


    private List<ColumnHeader> getSimpleColumnHeaderList() {
        List<ColumnHeader> list = new ArrayList<>();

        for (int i = 0; i < COLUMN_SIZE; i++) {
            String title = "column " + i;
             if (i == SITE_COLUMN_INDEX) {
                title = "SITE";
            } else if (i == DSS_COLUMN_INDEX) {
                title = "STRUCTURE";
            }else if (i == HRB_COLUMN_INDEX) {
                 title = "HRB";
             }else if (i == ASTR_COLUMN_INDEX) {
                title = "AFTER";
            }else if (i == BSTR_COLUMN_INDEX) {
                 title = "BEFORE";
             } else if (i == OID_COLUMN_INDEX) {
                 title = "OID";
             }

                ColumnHeader header = new ColumnHeader(String.valueOf(i), title);
                list.add(header);
            }

            return list;
        }


    public List<List<Cell>> getCellList() {
        DataBaseHelper db = new DataBaseHelper(mContext) ;
        return db.getStructures(1);
        // return getCellListForSortingTest();
    }

    public List<RowHeader> getRowHeaderList() {
        return getSimpleRowHeaderList();
    }

    public List<ColumnHeader> getColumnHeaderList() {
        return getSimpleColumnHeaderList();
    }

}
