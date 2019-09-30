package org.aphrc.myapplication.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.aphrc.myapplication.Model.Structure;
import org.aphrc.myapplication.R;

import java.util.UUID;


public class StructureDAO {
    public static final String TABLE_NAME = "Structure" ;
    public static final String COL_ID = "oid" ;
    public static final String COL_DATE = "str_createdAt";
    public static final String COL_STAFF = "str_createdBy" ;
    public static final String COL_SITE = "str_site" ;
    public static final String COL_EA = "str_ea" ;
    public static final String COL_SNUM = "str_number";
    public static final String COL_STRID = "str_structureID" ;
    public static final String COL_BNUM = "str_before" ;
    public static final String COL_ANUM = "str_after" ;
    public static final String COL_HRB = "str_hrb" ;
    public static final String COL_COM = "str_comment" ;
    public static final String COL_EBY = "str_editedBy";
    public static final String COL_EAT = "str_editedAt";
    public static final String COL_GC = "str_GCRecord";



    private static DataBaseHelper healper = null ;
    private static String TAG ="StructureDAO" ;


    private StructureDAO() {
    }

    protected static StructureDAO getInstance(DataBaseHelper helper){
        healper = helper;
        return new StructureDAO();
    }

    /**
     * This method called once version of SQLiteHelper get updated
     * @param db is writable database passed from SQLiteHelper
     */
    public static void createStructureTable(SQLiteDatabase db){
        String createQuery = "Create Table " +
                TABLE_NAME + " ( " +
                COL_ID + " text Primary key , " +
                COL_DATE + " text not null," +
                COL_STAFF + " text not null," +
                COL_SITE+ " Integer not null," +
                COL_EA + " text not null," +
                COL_SNUM + " Integer not null," +
                COL_STRID+ " text not null unique," +
                COL_BNUM + " text," +
                COL_ANUM + " text," +
                COL_HRB + " text," +
                COL_COM + " text," +
                COL_GC + " text ," +
                COL_EBY+  " text ," +
                COL_EAT + " text ," +
                "FOREIGN KEY("+COL_HRB+") REFERENCES "+ HouseholdRegistrationBookDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_STAFF+") REFERENCES "+ StaffDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_EBY+") REFERENCES "+ StaffDAO.TABLE_NAME +"(oid)"+")";
        db.execSQL(createQuery);
        final String Insert_Data="INSERT INTO Structure VALUES('79ea2702-450f-4aeb-b51c-7089cf40b68f','','',98,'98','98','98','98','98','','','','','')";
        db.execSQL(Insert_Data);

    }

    /**
     * Method called from SqliteHelper in case of upgrade
     * @param db
     */
    public static void upgradeStructureTable(SQLiteDatabase db){
        // write upgrade query if any
        String upgradeQuery = "" ;
        db.execSQL(upgradeQuery);
        // db.close();
    }

    public ReturnMessage addSTR(Structure str){

        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID,str.getStr_oid());
        cv.put(COL_DATE,str.getStr_CreatedAt());
        cv.put(COL_STAFF,str.getStr_Createdby());
        cv.put(COL_SITE,str.getStr_Site());
        cv.put(COL_EA,str.getStr_EA());
        cv.put(COL_SNUM,str.getStr_Number());
        cv.put(COL_STRID,str.getStr_StructureID());
        cv.put(COL_BNUM,str.getStr_Before());
        cv.put(COL_ANUM,str.getStr_After());
        cv.put(COL_HRB,str.getStr_hrb());
        cv.put(COL_COM,str.getStr_Comment());

        long i =healper.getWritableDatabase().insert(TABLE_NAME, null,cv) ;
        Log.v(TAG,"Row Id: " + i);

        if (i>0)
        {
            ReturnMessage.message = healper.getContext().getString(R.string.record_added_successfully)  ;
        }
        else
        {

            ReturnMessage.success = false ;
            ReturnMessage.message = healper.getContext().getString(R.string.record_could_not_be_added)  ;
        }
        return rm ;
    }

    /**
     * Method used to update Structure
     // * @param Structure
     * @return
     */
    public ReturnMessage updateStructure(Structure str){
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_SITE,str.getStr_Site());
        cv.put(COL_EA,str.getStr_EA());
        cv.put(COL_SNUM,str.getStr_Number());
        cv.put(COL_STRID,str.getStr_StructureID());
        cv.put(COL_BNUM,str.getStr_Before());
        cv.put(COL_ANUM,str.getStr_After());
        cv.put(COL_COM,str.getStr_Comment());
        cv.put(COL_HRB,str.getStr_hrb());
        cv.put(COL_EBY, str.getStr_EditedBy());
        cv.put(COL_EAT, str.getStr_EditedAt());
        String whereClause = COL_ID + "=?" ;
        int i = healper.getWritableDatabase().update(TABLE_NAME ,cv, whereClause , new String[]{str.getStr_oid()+""} );
        if(i>0){
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully)  ;
        }
        else{
            ReturnMessage.success = false ;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated)  ;
        }
        return rm ;
    }

}