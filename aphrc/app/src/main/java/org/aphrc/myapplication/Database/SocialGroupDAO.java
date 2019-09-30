package org.aphrc.myapplication.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.aphrc.myapplication.Model.SocialGroup;
import org.aphrc.myapplication.R;

public class SocialGroupDAO {
    public static final String TABLE_NAME = "Socialgroup";
    public static final String COL_ID = "oid";
    public static final String COL_DATE = "sgp_createdAt";
    public static final String COL_STF = "sgp_createdBy";
    public static final String COL_SGPID = "sgp_socialgroupID";
    public static final String COL_EBY = "sgp_editedBy";
    public static final String COL_EAT = "sgp_editedAt";
    public static final String COL_GC = "sgp_GCRecord";

    private static DataBaseHelper healper = null;
    private static String TAG = "SocialGroupDAO";

    private SocialGroupDAO() {
    }

    protected static SocialGroupDAO getInstance(DataBaseHelper helper) {
        healper = helper;
        return new SocialGroupDAO();
    }

    /**
     * This method called once version of SQLiteHelper get updated
     *
     * @param db is writable database passed from SQLiteHelper
     */
    public static void createSocialGroupTable(SQLiteDatabase db) {
        String createQuery = "Create Table " +
                TABLE_NAME + " ( " +
                COL_ID + " text NOT NULL UNIQUE PRIMARY KEY, " +
                COL_DATE + " text NOT NULL," +
                COL_STF + " text NOT NULL," +
                COL_SGPID + " text NOT NULL UNIQUE," +
                COL_GC + " text ," +
                COL_EBY+  " text ," +
                COL_EAT + " text," +
                "FOREIGN KEY(" + COL_EBY + ") REFERENCES " + StaffDAO.TABLE_NAME + "(oid)," +
                "FOREIGN KEY(" + COL_STF + ") REFERENCES " + StaffDAO.TABLE_NAME + "(oid)" + ")";
        db.execSQL(createQuery);

    }

    /**
     * Method called from SqliteHelper in case of upgrade
     *
     * @param db
     */
    public static void upgradeSocialGroupTable(SQLiteDatabase db) {
        // write upgrade query if any
        String upgradeQuery = "";
        db.execSQL(upgradeQuery);
        // db.close();
    }


    public ReturnMessage addSGP(SocialGroup sgp) {

        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, sgp.getSgp_oid());
        cv.put(COL_DATE, sgp.getSgp_CreatedAt());
        cv.put(COL_STF, sgp.getSgp_CreatedBy());
        cv.put(COL_SGPID, sgp.getSgp_SgpID());

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
     * Method used to update Sooialgroup
     *
     * @param sgp
     * @return
     */
    public ReturnMessage updateSGP(SocialGroup sgp) {
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
      //  cv.put(COL_DATE, sgp.getSgp_Date());
        cv.put(COL_SGPID, sgp.getSgp_SgpID());
        cv.put(COL_EBY, sgp.getSgp_EditedBy());
        cv.put(COL_EAT, sgp.getSgp_EditedAt());
        String whereClause = COL_ID + "=?";
        int i = healper.getWritableDatabase().update(TABLE_NAME, cv, whereClause, new String[]{sgp.getSgp_oid() + ""});
        if (i > 0) {
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully);
        } else {
            ReturnMessage.success = false;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated);
        }
        return rm;
    }


}