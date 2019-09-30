package org.aphrc.myapplication.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.aphrc.myapplication.Model.HouseholdRegistrationBook;
import org.aphrc.myapplication.R;

public class HouseholdRegistrationBookDAO {


    public static final String TABLE_NAME = "HouseholdRegistrationBook" ;
    public static final String COL_ID = "oid" ;
    public static final String COL_DATE = "hrb_createdAt" ;
    public static final String COL_STF= "hrb_createdBy" ;
    public static final String COL_NAME = "hrb_Name" ;
    public static final String COL_EBY = "hrb_editedBy";
    public static final String COL_EAT = "hrb_editedAt";
    public static final String COL_GC = "hrb_GCRecord";

    private static DataBaseHelper healper = null ;
    private static String TAG ="HouseholdRegistrationBookDAO" ;

    private HouseholdRegistrationBookDAO() {
    }

    protected static HouseholdRegistrationBookDAO getInstance(DataBaseHelper helper){
        healper = helper;
        return new HouseholdRegistrationBookDAO();
    }

    /**
     * This method called once version of SQLiteHelper get updated
     * @param db is writable database passed from SQLiteHelper
     */
    public static void createHouseholdRegistrationBookTable(SQLiteDatabase db){
        String createQuery = "Create Table " +
                TABLE_NAME + " ( " +
                COL_ID + " text Primary key , " +
                COL_DATE + " text not null , " +
                COL_STF + " text not null , " +
                COL_NAME + " text not null unique, " +
                COL_GC + " text ," +
                COL_EBY+  " text ," +
                COL_EAT+  " text)" ;
        db.execSQL(createQuery);

    }

    /**
     * Method called from SqliteHelper in case of upgrade
     * @param db
     */
    public static void upgradeHouseholdRegistrationBookTable(SQLiteDatabase db){
        // write upgrade query if any
        String upgradeQuery = "" ;
        db.execSQL(upgradeQuery);
        // db.close();
    }


    public ReturnMessage addHouseholdRegistrationBook(HouseholdRegistrationBook hrb){

        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID,hrb.getHrb_oid());
        cv.put(COL_DATE,hrb.getHrb_CreatedAt());
        cv.put(COL_STF,hrb.getHrb_Createdby());
        cv.put(COL_NAME,hrb.getHrb_Name());
        long i =healper.getWritableDatabase().insert(TABLE_NAME, null,cv) ;
        Log.v(TAG,"Row Id: " + i);

        if (i>0)
        {
            ReturnMessage.message = healper.getContext().getString(R.string.record_added_successfully)  ;
            //this.finish();
        }
        else
        {

            ReturnMessage.success = false ;
            ReturnMessage.message = healper.getContext().getString(R.string.record_could_not_be_added)  ;
        }
        return rm ;
    }

    /**
     * Method used to update round
     * @param hrb
     * @return
     */
    public ReturnMessage updateHouseholdRegistrationBook(HouseholdRegistrationBook hrb){
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_NAME,hrb.getHrb_Name());
        cv.put(COL_EBY, hrb.getHrb_EditedBy());
        cv.put(COL_EAT, hrb.getHrb_EditedAt());
        String whereClause = COL_ID + "=?" ;
        int i = healper.getWritableDatabase().update(TABLE_NAME ,cv, whereClause , new String[]{hrb.getHrb_oid()+""} );
        if(i>0){
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully)  ;
           // this.finish();
                    }
        else{
            ReturnMessage.success = false ;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated)  ;
        }

        return rm ;
    }

}
