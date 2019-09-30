package org.aphrc.myapplication.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.aphrc.myapplication.Model.GlobalClass;
import org.aphrc.myapplication.Model.Staff;
import org.aphrc.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class StaffDAO extends Activity {
    public static final String TABLE_NAME = "Staff" ;
    public static final String COL_ID = "oid" ;
    public static final String COL_FNAME = "stf_FirstName" ;
    public static final String COL_SNAME = "stf_secondName" ;
    public static final String COL_UNAME = "stf_UserName" ;
    public static final String COL_EMAIL = "stf_Email" ;
    public static final String COL_PASSWORD = "stf_Password" ;

    public Context context;

    private static DataBaseHelper healper = null ;
    private static String TAG ="StaffDAO" ;



    public StaffDAO(Context context) {
        this.context=context;
        healper=new DataBaseHelper(this.context);

    }

    public StaffDAO() {

    }

    protected static StaffDAO getInstance(DataBaseHelper helper){
        healper = helper;
        return new StaffDAO();
    }

    /**
     * This method called once version of SQLiteHelper get updated
     * @param db is writable database passed from SQLiteHelper
     */
    public static void createStaffTable(SQLiteDatabase db){
        String createQuery = "Create Table " +
                TABLE_NAME + " ( " +
                COL_ID + " text Primary key , " +
                COL_FNAME + " text not null," +
                COL_SNAME + " text not null," +
                COL_UNAME + " text not null," +
                COL_EMAIL + " text not null," +
                COL_PASSWORD + " text not null )" ;
        db.execSQL(createQuery);

    }

    /**
     * Method called from SqliteHelper in case of upgrade
     * @param db
     */
    public static void upgradeStaffTable(SQLiteDatabase db){
        // write upgrade query if any
        String upgradeQuery = "" ;
        db.execSQL(upgradeQuery);
        // db.close();
    }


    public ReturnMessage addStaff(Staff staff){

        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, staff.getStf_ID());
        cv.put(COL_FNAME, staff.getStf_FirstName());
        cv.put(COL_SNAME, staff.getStf_SecondName());
        cv.put(COL_UNAME, staff.getStf_UserName());
        cv.put(COL_EMAIL, staff.getStf_Email());
        cv.put(COL_PASSWORD, staff.getStf_Password());

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
     * @param staff
     * @return
     */
    public ReturnMessage updateStaff(Staff staff){
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, staff.getStf_ID());
        cv.put(COL_FNAME, staff.getStf_FirstName());
        cv.put(COL_SNAME, staff.getStf_SecondName());
        cv.put(COL_UNAME, staff.getStf_UserName());
        cv.put(COL_EMAIL, staff.getStf_Email());
        cv.put(COL_PASSWORD, staff.getStf_Password());

        String whereClause = COL_ID + "=?" ;
        int i = healper.getWritableDatabase().update(TABLE_NAME ,cv, whereClause , new String[]{staff.getStf_ID()+""} );
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
     * Method used to delete Room record
     * @param id
     * @return
     */
    public ReturnMessage deleteRoom(int id){
        ReturnMessage rm = new ReturnMessage();
        int i = healper.getWritableDatabase().delete(TABLE_NAME,COL_ID + "=?" , new String[]{id+""});
        if(i>0){
            ReturnMessage.message = healper.getContext().getString(R.string.record_deleted_successfully)  ;
        }
        else{
            ReturnMessage.success = false ;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_deleted)  ;
        }
        return rm ;
    }

    public Cursor getStaffs(Staff staff){

        Cursor cursor = null ;
        String []columns = new String[]{COL_ID,COL_FNAME,COL_SNAME,COL_UNAME,COL_EMAIL,COL_PASSWORD} ;
        String selection = "" ;
        String selectionArg[] = null ;
        String orderBy =  COL_FNAME + "," +COL_SNAME ;

        cursor = healper.getReadableDatabase().query(TABLE_NAME, columns, selection, selectionArg, null,null,orderBy);
        return cursor ;
    }

    /**
     * This method is to fetch all user and return the list of user records
     *
     * @return list
     */
    public List<Staff> getAllStaffs() {
        // array of columns to fetch
        String[] columns = {
                COL_ID,COL_FNAME,COL_SNAME,COL_UNAME,COL_EMAIL,COL_PASSWORD
        };
        // sorting orders
        String sortOrder =
                COL_FNAME + " ASC";
        List<Staff> staffList = new ArrayList<Staff>();

        //SQLiteDatabase db = this.getReadableDatabase();

        // query the user table
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id,user_name,user_email,user_password FROM user ORDER BY user_name;
         */
        Cursor cursor = healper.getReadableDatabase().query(TABLE_NAME, //Table to query
                columns,    //columns to return
                null,        //columns for the WHERE clause
                null,        //The values for the WHERE clause
                null,       //group the rows
                null,       //filter by row groups
                sortOrder); //The sort order


        // Traversing through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Staff staff = new Staff();
                staff.setStf_ID(cursor.getString(cursor.getColumnIndex(COL_ID)));
                staff.setStf_FirstName(cursor.getString(cursor.getColumnIndex(COL_FNAME)));
                staff.setStf_SecondName(cursor.getString(cursor.getColumnIndex(COL_SNAME)));
                staff.setStf_UserName(cursor.getString(cursor.getColumnIndex(COL_UNAME)));
                staff.setStf_Email(cursor.getString(cursor.getColumnIndex(COL_EMAIL)));
                staff.setStf_Password(cursor.getString(cursor.getColumnIndex(COL_PASSWORD)));
                // Adding user record to list
                staffList.add(staff);
            } while (cursor.moveToNext());
        }
        cursor.close();
       // db.close();

        // return user list
        return staffList;
    }



    /**
     * This method to check Staff exist or not
     *
     * @param email
     * @return true/false
     */

    public boolean checkStaff(String email) {

        // array of columns to fetch
        String[] columns = {
                COL_ID
        };

        // selection criteria
        String selection = COL_EMAIL + " = ?";
        // selection argument
        String[] selectionArgs = {email};

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

    /**
     * This method to check user exist or not
     *
     * @param email
     * @param password
     * @return true/false
     */
    public boolean checkStaff(String email, String password) {

        // array of columns to fetch
        String[] columns = {
                COL_ID,COL_UNAME
        };

        // selection criteria
        String selection = COL_EMAIL + " = ?" + " AND " + COL_PASSWORD + " = ?";

        // selection arguments
        String[] selectionArgs = {email, password};

        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = healper.getReadableDatabase().query(TABLE_NAME, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order

        int cursorCount = cursor.getCount();

        cursor.close();
        //db.close();
        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    public Cursor getStaff(String email){

        Cursor cursor = null ;
        String []columns = new String[]{COL_ID,COL_FNAME,COL_SNAME,COL_UNAME,COL_EMAIL,COL_PASSWORD} ;
        String selection = COL_EMAIL + "=?";
        String selectionArg[] = {email} ;
        String orderBy =  COL_FNAME + "," +COL_SNAME ;

        cursor = healper.getReadableDatabase().query(TABLE_NAME, columns, selection, selectionArg, null,null,orderBy);
        return cursor ;
    }

}