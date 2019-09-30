
package org.aphrc.myapplication.Fragments.Individual.tableview;

import android.content.Context;

import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.IndividualDAO;
import org.aphrc.myapplication.tableview.model.Cell;
import org.aphrc.myapplication.tableview.model.ColumnHeader;
import org.aphrc.myapplication.tableview.model.RowHeader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 4.02.2018.
 */

public class TableViewModel {

    // Columns indexes
    public static final int  COL_date= 0;
    public static final int  COL_staff= 1;
    public static final int  COL_IndID= 2;
    public static final int  COL_name= 3;
    public static final int  COL_nickname= 4;
    public static final int  COL_q4p4a= 5;
    public static final int  COL_q4p4b= 6;
    public static final int  COL_q4p5= 7;
    public static final int  COL_q4p6= 8;
    public static final int  COL_q4p7a= 9;
    public static final int  COL_q4p7b= 10;
    public static final int  COL_q5p28a= 11;
    public static final int  COL_q5p28b= 12;
    public static final int  COL_VIS= 13;
    public static final int  COL_oid= 14;

    // Constant size for dummy data sets
    private static final int COLUMN_SIZE = 15;
    private static final int ROW_SIZE= 0;


    private  IndividualDAO individualDAO;

    private Context mContext;

    public TableViewModel(Context context) {
        mContext = context;

        // initialize drawables
    }

    private List<RowHeader> getSimpleRowHeaderList() {
        DataBaseHelper db =new DataBaseHelper(mContext);

        List<RowHeader> list = new ArrayList<>();
        for (int i = 1; i < db.getINDCount() + 1; i++) {
            RowHeader header = new RowHeader(String.valueOf(i), Integer.toString(i));
            list.add(header);
        }

        return list;
    }


    private List<ColumnHeader> getSimpleColumnHeaderList() {
        List<ColumnHeader> list = new ArrayList<>();

        for (int i = 0; i < COLUMN_SIZE; i++) {
            String title = "column " + i;
            if (i == COL_date) {
                title = "Start Date";
            }else if (i == COL_staff) {
                title = "Field Interviewer";
            }else if (i == COL_IndID) {
                title = "Individual ID ";
            }else if (i == COL_name) {
                title = "Individual Name";
            }else if (i == COL_nickname) {
                title = "Nick Name";
            }else if (i == COL_q4p4a) {
                title = "Actual DOB";
            }else if (i == COL_q4p4b) {
                title = "Calc DOB";
            }else if (i == COL_q4p5) {
                title = "Age";
            }else if (i == COL_q4p6) {
                title = "Sex";
            }else if (i == COL_q4p7a) {
                title = "Ethinicity";
            }else if (i == COL_q4p7b) {
                title = "Ethinicity spy";
            }else if (i == COL_q5p28a) {
                title = "Date to DSA";
            }else if (i == COL_q5p28b) {
                title = "Date to DSA calc";
            }else if (i == COL_VIS) {
                title = "Vistor";
            }else if (i == COL_oid) {
                title = "ID ";
            }


            ColumnHeader header = new ColumnHeader(String.valueOf(i), title);
            list.add(header);
        }

        return list;
    }



    public List<List<Cell>> getCellList() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext) ;
        return dataBaseHelper.getAllINDs();
       // return getCellListForSortingTest();
    }

    public List<RowHeader> getRowHeaderList() {
        return getSimpleRowHeaderList();
    }

    public List<ColumnHeader> getColumnHeaderList() {
        return getSimpleColumnHeaderList();
    }
    

}
