package org.aphrc.myapplication.Database;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.aphrc.myapplication.Model.Visitor;
import org.aphrc.myapplication.R;

public class VisitorDAO {


    public static final String TABLE_NAME = "Visitor";
    public static final String COL_ID = "oid";
    public static final String COL_DATE = "vis_createdAt";
    public static final String COL_STF = "vis_createdBy";
    public static final String COL_IND= "vis_individualID";
    public static final String COL_STS = "vis_status";
    public static final String COL_STD = "vis_statusDate";
    public static final String COL_MVTO = "vis_movedTo";
    public static final String COL_MVWITH = "vis_movedWith";
    public static final String COL_HHH = "vis_rlthhh";
    public static final String COL_RMD = "vis_room";
    public static final String COL_SGP = "vis_socialgroup";
    public static final String COL_EBY = "vis_editedBy";
    public static final String COL_EAT = "vis_editedAt";
    public static final String COL_GC = "vis_GCRecord";


    private static DataBaseHelper healper = null;
    private static String TAG = "VisitorDAO";


    private VisitorDAO() {
    }

    protected static VisitorDAO getInstance (DataBaseHelper helper){
        healper = helper;
        return new VisitorDAO();
    }

    /**
     * This method called once version of SQLiteHelper get updated
     * @param db is writable database passed from SQLiteHelper
     */


    public static void createVisitorTable (SQLiteDatabase db){
        String createQuery = "Create Table " +
                TABLE_NAME + " ( " +
                COL_ID + " text NOT NULL UNIQUE PRIMARY KEY," +
                COL_DATE + " text not null," +
                COL_STF + " text not null," +
                COL_IND + " text not null, " +
                COL_STS + " int not null, " +
                COL_STD + " text , " +
                COL_MVTO + " text , " +
                COL_MVWITH+ " int , " +
                COL_HHH+ " int , " +
                COL_RMD + " text , " +
                COL_SGP + " text , " +
                COL_GC + " text ," +
                COL_EBY+  " text ," +
                COL_EAT + " text ," +
                "FOREIGN KEY(" + COL_RMD + ") REFERENCES " + RoomDAO.TABLE_NAME + "(oid)," +
                "FOREIGN KEY(" + COL_IND + ") REFERENCES " + IndividualDAO.TABLE_NAME + "(oid)," +
                "FOREIGN KEY(" + COL_SGP + ") REFERENCES " + SocialGroupDAO.TABLE_NAME + "(oid)," +
                "FOREIGN KEY(" + COL_STF + ") REFERENCES " + StaffDAO.TABLE_NAME + "(oid)" + ")";
        db.execSQL(createQuery);

    }

    /**
     * Method called from SqliteHelper in case of upgrade
     * @param db
     */
    public static void upgradeVisitorTable (SQLiteDatabase db){
        // write upgrade query if any
        String upgradeQuery = "";
        db.execSQL(upgradeQuery);
        // db.close();
    }


    public ReturnMessage addVisitor (Visitor vis){

        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, vis.getVis_oid());
        cv.put(COL_DATE, vis.getVis_CreatedAt());
        cv.put(COL_STF, vis.getVis_Createdby());
        cv.put(COL_IND, vis.getVis_IndividualID());
        cv.put(COL_STS, vis.getVis_Status());
        cv.put(COL_STD, vis.getVis_StatusDate());
        cv.put(COL_MVTO, vis.getVis_MovedTo());
        cv.put(COL_MVWITH, vis.getVis_MovedWith());
        cv.put(COL_HHH, vis.getVis_rlthhh());
        cv.put(COL_RMD, vis.getVis_Room());
        cv.put(COL_SGP, vis.getVis_Socialgroup());

        long i = healper.getWritableDatabase().insert(TABLE_NAME, null, cv);
        Log.v(TAG, "Row Id: " + i);

        if (i > 0) {
            ReturnMessage.message = healper.getContext().getString(R.string.record_added_successfully);
        } else {

            ReturnMessage.success = false;
            ReturnMessage.message = healper.getContext().getString(R.string.record_could_not_be_added);
        }
        return rm;
    }

    /**
     * Method used to update Vistor
     * @param vis
     * @return
     */
    public ReturnMessage updateVisitor (Visitor vis){
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_STS, vis.getVis_Status());
        cv.put(COL_STD, vis.getVis_StatusDate());
        cv.put(COL_MVTO, vis.getVis_MovedTo());
        cv.put(COL_MVWITH, vis.getVis_MovedWith());
        cv.put(COL_HHH, vis.getVis_rlthhh());
        cv.put(COL_RMD, vis.getVis_Room());
        cv.put(COL_SGP, vis.getVis_Socialgroup());
        cv.put(COL_EBY, vis.getVis_EditedBy());
        cv.put(COL_EAT, vis.getVis_EditedAt());
        String whereClause = COL_ID + "=?";
        int i = healper.getWritableDatabase().update(TABLE_NAME, cv, whereClause, new String[]{vis.getVis_oid() + ""});
        if (i > 0) {
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully);
        } else {
            ReturnMessage.success = false;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated);
        }
        return rm;
    }


    public ReturnMessage updateVis (Visitor vis){
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_GC, 999999);
        String whereClause = COL_IND + "=?";
        int i = healper.getWritableDatabase().update(TABLE_NAME, cv, whereClause, new String[]{vis.getVis_IndividualID() + ""});
        if (i > 0) {
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully);
        } else {
            ReturnMessage.success = false;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated);
        }
        return rm;
    }

}

