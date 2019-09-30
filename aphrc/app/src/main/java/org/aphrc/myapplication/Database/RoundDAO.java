package org.aphrc.myapplication.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.aphrc.myapplication.Model.Round;
import org.aphrc.myapplication.R;

public class RoundDAO {
    public static final String TABLE_NAME = "Round" ;
    public static final String COL_ID = "oid" ;
    public static final String COL_DATE = "rnd_createdAt" ;
    public static final String COL_STF= "rnd_createdBy" ;
    public static final String COL_RN = "rnd_roundNumber" ;
    public static final String COL_SD = "rnd_startDate" ;
    public static final String COL_ED = "rnd_endDate" ;
    public static final String COL_COM = "rnd_remarks" ;
    public static final String COL_EBY = "rnd_editedBy";
    public static final String COL_EAT = "rnd_editedAt";
    public static final String COL_GC = "rnd_GCRecord";


    private static DataBaseHelper healper = null ;
    private static String TAG ="RoundDAO" ;

    private RoundDAO() {
    }

    protected static RoundDAO getInstance(DataBaseHelper helper){
        healper = helper;
        return new RoundDAO();
    }

    /**
     * This method called once version of SQLiteHelper get updated
     * @param db is writable database passed from SQLiteHelper
     */
    public static void createRoundTable(SQLiteDatabase db){
        String createQuery = "Create Table " +
                TABLE_NAME + " ( " +
                COL_ID + " text NOT NULL UNIQUE PRIMARY KEY, " +
                COL_DATE + " text not null , " +
                COL_STF + " text not null , " +
                COL_RN + " Integer not null , " +
                COL_SD + " text not null , " +
                COL_ED + " text not null , " +
                COL_COM + " text , " +
                COL_GC + " text ," +
                COL_EBY+  " text ," +
                COL_EAT + " text," +
                "FOREIGN KEY("+COL_STF+") REFERENCES "+ StaffDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_EBY+") REFERENCES "+ StaffDAO.TABLE_NAME +"(oid)"+")";
        db.execSQL(createQuery);

    }

    /**
     * Method called from SqliteHelper in case of upgrade
     * @param db
     */
    public static void upgradeRoundTable(SQLiteDatabase db){
        // write upgrade query if any
        String upgradeQuery = "" ;
        db.execSQL(upgradeQuery);
        // db.close();
    }


    public ReturnMessage addRound(Round round){

        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID,round.getRnd_oid());
        cv.put(COL_DATE,round.getRnd_CreatedAt());
        cv.put(COL_STF,round.getRnd_CreatedBy());
        cv.put(COL_RN,round.getRnd_RoundNumber());
        cv.put(COL_SD,round.getRnd_StartDate());
        cv.put(COL_ED,round.getRnd_EndDate());
        cv.put(COL_COM,round.getRnd_Remarks());

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
     * Method used to update round
     * @param round
     * @return
     */
    public ReturnMessage updateRound(Round round){
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();

        cv.put(COL_RN,round.getRnd_RoundNumber());
        cv.put(COL_SD,round.getRnd_StartDate());
        cv.put(COL_ED,round.getRnd_EndDate());
        cv.put(COL_COM,round.getRnd_Remarks());
        cv.put(COL_EBY, round.getRnd_EditedBy());
        cv.put(COL_EAT, round.getRnd_EditedAt());
        String whereClause = COL_ID + "=?" ;
        int i = healper.getWritableDatabase().update(TABLE_NAME ,cv, whereClause , new String[]{round.getRnd_oid()+""} );
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