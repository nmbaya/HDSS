package org.aphrc.myapplication.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.aphrc.myapplication.Model.*;
import org.aphrc.myapplication.R;

public class RoomDAO {
    public static final String TABLE_NAME = "Room" ;
    public static final String COL_ID = "oid" ;
    public static final String COL_DATE = "rm_createdAt" ;
    public static final String COL_STF = "rm_createdBy" ;
    public static final String COL_STR = "rm_structure" ;
    public static final String COL_NUM= "rm_number" ;
    public static final String COL_RID= "rm_roomID" ;
    public static final String COL_EBY = "rm_editedBy";
    public static final String COL_EAT = "rm_editedAt";
    public static final String COL_GC = "rm_GCRecord";

    private static DataBaseHelper healper = null ;
    private static String TAG ="RoomDAO" ;


    private RoomDAO() {
    }

    protected static RoomDAO getInstance(DataBaseHelper helper){
        healper = helper;
        return new RoomDAO();
    }

    /**
     * This method called once version of SQLiteHelper get updated
     * @param db is writable database passed from SQLiteHelper
     */


    public static void createRoomTable(SQLiteDatabase db){
        String createQuery = "Create Table " +
                TABLE_NAME + " ( " +
                COL_ID + " text NOT NULL UNIQUE PRIMARY KEY," +
                COL_DATE + " text not null," +
                COL_STF + " text not null, " +
                COL_STR + " text not null, " +
                COL_NUM + " text NOT NULL, " +
                COL_RID + " text NOT NULL UNIQUE, " +
                COL_GC + " text ," +
                COL_EBY+  " text ," +
                COL_EAT + " text ," +
                "FOREIGN KEY("+COL_STR+") REFERENCES "+ StructureDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_STF+") REFERENCES "+ StaffDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_EBY+") REFERENCES "+ StaffDAO.TABLE_NAME +"(oid)"+")";
        db.execSQL(createQuery);

    }


    /**
     * Method called from SqliteHelper in case of upgrade
     * @param db
     */
    public static void upgradeRoomTable(SQLiteDatabase db){
        // write upgrade query if any
        String upgradeQuery = "" ;
        db.execSQL(upgradeQuery);
        // db.close();
    }


    public ReturnMessage addRoom(Room r){

        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID,r.rm_oid);
        cv.put(COL_DATE,r.getRm_CreatedAt());
        cv.put(COL_STF,r.getRm_CreatedBy());
        cv.put(COL_STR,r.getRm_Structure());
        cv.put(COL_NUM,r.getRm_Number());
        cv.put(COL_RID,r.getRm_roomID());

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
     * Method used to update Room
     * @param r
     * @return
     */
    public ReturnMessage updateRoom(Room r){
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_NUM,r.getRm_Number());
        cv.put(COL_RID,r.getRm_roomID());
        cv.put(COL_EBY, r.getRm_EditedBy());
        cv.put(COL_EAT, r.getRm_EditedAt());
        String whereClause = COL_ID + "=?" ;
        int i = healper.getWritableDatabase().update(TABLE_NAME ,cv, whereClause , new String[]{r.getRm_oid()+""} );
        if(i>0){
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully)  ;
        }
        else{
            ReturnMessage.success = false ;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated)  ;
        }
        return rm ;
    }


    /**
     * This method to check Room Exists exist or not
     *
     * @param room
     * @return true/false
     */

    public boolean checkRoom(String room) {

        // array of columns to fetch
        String[] columns = {
                COL_RID,
        };

        // selection criteria
        String selection = COL_RID + " = ?";
        // selection argument
        String[] selectionArgs = {room};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         */
        Cursor cursor = healper.getReadableDatabase().query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();

        cursor.close();
        //db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

}