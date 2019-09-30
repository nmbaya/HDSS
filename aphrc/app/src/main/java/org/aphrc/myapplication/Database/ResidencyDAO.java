package org.aphrc.myapplication.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.aphrc.myapplication.Model.Individual;
import org.aphrc.myapplication.Model.Residency;
import org.aphrc.myapplication.R;

import java.util.Random;

public class ResidencyDAO {
    public static final String TABLE_NAME = "Residency";
    public static final String COL_ID = "oid";
    public static final String COL_DATE = "res_createdAt";
    public static final String COL_STF = "res_createdBy";
    public static final String COL_IND = "res_individual";
    public static final String COL_ROOM = "res_room";
    public static final String COL_SOBS = "res_startObservation";
    public static final String COL_STYPE = "res_startEventType";
    public static final String COL_SDATE = "res_startEventDate";
    public static final String COL_EOBS = "res_endObservation";
    public static final String COL_ETYPE = "res_endEventType";
    public static final String COL_EDATE = "res_endEventDate";
    public static final String COL_EBY = "res_editedBy";
    public static final String COL_EAT = "res_editedAt";
    public static final String COL_GC = "res_GCRecord";
    private static DataBaseHelper healper = null ;
    private static String TAG ="ResidencyDAO" ;

    private ResidencyDAO() {
    }

    protected static ResidencyDAO getInstance(DataBaseHelper helper){
        healper = helper;
        return new ResidencyDAO();
    }

    /**
     * This method called once version of SQLiteHelper get updated
     * @param db is writable database passed from SQLiteHelper
     */
    public static void createResidencyTable(SQLiteDatabase db){
        String createQuery = "Create Table " +
                TABLE_NAME + " ( " +
                COL_ID + " text Primary key , " +
                COL_DATE + " text NOT NULL," +
                COL_STF + " text NOT NULL," +
                COL_IND + " text NOT NULL," +
                COL_ROOM + " text NOT NULL ," +
                COL_SOBS + " text NOT NULL," +
                COL_STYPE + " text NOT NULL," +
                COL_SDATE + " text ," +
                COL_EOBS + " text," +
                COL_ETYPE + " text," +
                COL_EDATE  + " text," +
                COL_GC + " text ," +
                COL_EBY+  " text ," +
                COL_EAT +  " text ," +
                "FOREIGN KEY("+COL_SOBS+") REFERENCES "+ ObservationDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_ROOM+") REFERENCES "+ RoundDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_IND+") REFERENCES "+ IndividualDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_EBY+") REFERENCES "+ StaffDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_STF+") REFERENCES "+ StaffDAO.TABLE_NAME +"(oid)"+")";
        db.execSQL(createQuery);

    }

    /**
     * Method called from SqliteHelper in case of upgrade
     * @param db
     */
    public static void upgradeResidencyTable(SQLiteDatabase db){
        // write upgrade query if any
        String upgradeQuery = "" ;
        db.execSQL(upgradeQuery);
        // db.close();
    }


    public ReturnMessage addRES(Residency res){

        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, res.getRes_oid());
        cv.put(COL_DATE,res.getRes_CreatedAt());
        cv.put(COL_STF,res.getRes_CreatedBy());
        cv.put(COL_IND,res.getRes_Individual());
        cv.put(COL_ROOM,res.getRes_Room());
        cv.put(COL_SOBS,res.getRes_StartObservation());
        cv.put(COL_STYPE,res.getRes_StartEventType());
        cv.put(COL_SDATE,res.getRes_StartEventDate());
        cv.put(COL_EOBS,res.getRes_EndObservation());
        cv.put(COL_ETYPE,res.getRes_EndEventType());
        cv.put(COL_EDATE,res.getRes_EndEventDate());

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
     * Method used to update Residency
     * @param res
     * @return
     */
    public ReturnMessage updateRES(Residency res){
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();

        cv.put(COL_EOBS,res.getRes_EndObservation());
        cv.put(COL_ETYPE,res.getRes_EndEventType());
        cv.put(COL_EDATE,res.getRes_EndEventDate());
        cv.put(COL_EBY, res.getRes_EditedBy());
        cv.put(COL_EAT, res.getRes_EditedAt());
        String whereClause = COL_IND + "=? and " + COL_ROOM + "=? and " + COL_EOBS +" IS NULL ";
        int i = healper.getWritableDatabase().update(TABLE_NAME ,cv, whereClause , new String[]{res.getRes_Individual()+"",res.getRes_Room()+""} );
        if(i>0){
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully)  ;
        }
        else{
            ReturnMessage.success = false ;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated)  ;
        }
        return rm ;
    }

    public void updateVis (Residency res){
        Random r =new Random();
        int randomnumber =r.nextInt(100000);
        ContentValues cv = new ContentValues();
        cv.put(COL_GC, randomnumber);
        String whereClause = COL_IND + "=?";
        int i = healper.getWritableDatabase().update(TABLE_NAME, cv, whereClause, new String[]{res.getRes_Individual() + ""});
    }

    public void MoveVisitor (Residency res){
        ContentValues cv = new ContentValues();
        cv.put(COL_ROOM,res.getRes_Room());
        String whereClause = COL_IND + "=?";
        int i = healper.getWritableDatabase().update(TABLE_NAME, cv, whereClause, new String[]{res.getRes_Individual() + ""});
    }
}