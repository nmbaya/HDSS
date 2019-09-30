
package org.aphrc.myapplication.LocationObservation.tableview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Database.IndividualDAO;
import org.aphrc.myapplication.tableview.model.Cell;
import org.aphrc.myapplication.tableview.model.ColumnHeader;
import org.aphrc.myapplication.tableview.model.RowHeader;
import org.aphrc.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created  on 4.02.2018.
 */

public class TableViewModel {

    // Columns indexes
    public static final int ID = 0;
    public static final int NAME = 1;
    public static final int SEX = 2;
    public static final int AGE = 3;
    public static final int DOB = 4;
    public static final int ETHINICITY= 5;
    public static final int RHHH= 6;
    public static final int START_EVENT =7;
    public static final int START_DATE = 8;
    public static final int CURRENT_STRATUS = 9;
    public static final int PGO_STATUS = 10;
    public static final int MALIVE = 11;
    public static final int FALIVE = 12;
    public static final int ACTIVITY = 13;
    public static final int MSTATUS = 14;
    public static final int EDUCATION = 15;
    public static final int EDUCATIONLEVEL = 16;
    public static final int EDUCATIONCLASS = 17;
    public static final int IsVISITOR = 18;

    public static final int LOCATION = 19;
    public static final int SOCIALGROUP = 20;

    // Constant size for dummy data sets
    private static final int COLUMN_SIZE = 21;
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
        for (int i = 1; i < db.getIndividualCount() + 1; i++) {
            RowHeader header = new RowHeader(String.valueOf(i), Integer.toString(i));
            list.add(header);
        }

        return list;
    }


    private List<ColumnHeader> getSimpleColumnHeaderList() {
        List<ColumnHeader> list = new ArrayList<>();

        for (int i = 0; i < COLUMN_SIZE; i++) {
            String title = "column " + i;
            if (i == ID) {
                title = "Individual ID";
            } else if (i == NAME) {
                title = "Individual Name";
            }else if (i == SEX) {
                title = "Sex ";
            }else if (i == AGE) {
                title = "Age";
            }else if (i == DOB) {
                title = "Dob";
            } else if (i == ETHINICITY) {
                title = "Ethinicity";
            }else if (i == RHHH) {
                title = "Relation to HHH";
            }else if (i == START_EVENT) {
                title = "Start Event";
            }else if (i == START_DATE) {
                title = "Start Event Date";
            }else if (i == CURRENT_STRATUS) {
                title = "Current Status";
            }else if (i == PGO_STATUS) {
                title = "Is Pregnant";
            }else if (i == MALIVE) {
                title = "Mother Alive";
            }else if (i == FALIVE) {
                title = "Father Alive";
            }else if (i == ACTIVITY) {
                title = "Economic Activity";
            }else if (i == MSTATUS) {
                title = "Marital Status";
            }else if (i == EDUCATION) {
                title = "Ever Schooled";
            }else if (i == EDUCATIONLEVEL) {
                title = "Education Level";
            }else if (i == EDUCATIONCLASS) {
                title = "Education Class";
            }else if (i == IsVISITOR) {
                title = "Is Visitor";
            }else if (i == LOCATION) {
                title = "Location";
            }else if (i == SOCIALGROUP) {
                title = "Socialgroup";
            }


            ColumnHeader header = new ColumnHeader(String.valueOf(i), title);
            list.add(header);
        }

        return list;
    }



    public List<List<Cell>> getCellList() {
        DataBaseHelper dataBaseHelper = new DataBaseHelper(mContext) ;
        return dataBaseHelper.getAllIndividuals(1);
       // return getCellListForSortingTest();
    }

    public List<RowHeader> getRowHeaderList() {
        return getSimpleRowHeaderList();
    }

    public List<ColumnHeader> getColumnHeaderList() {
        return getSimpleColumnHeaderList();
    }
    

}
