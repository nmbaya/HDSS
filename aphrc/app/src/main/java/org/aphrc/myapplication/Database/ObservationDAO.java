package org.aphrc.myapplication.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.aphrc.myapplication.Model.Observation;
import org.aphrc.myapplication.Model.Room;
import org.aphrc.myapplication.R;

public class ObservationDAO {
    public static final String TABLE_NAME = "Observation" ;
    public static final String COL_ID = "oid" ;
    public static final String COL_DATE = "obs_createdAt";
    public static final String COL_STF = "obs_createdBy";
    public static final String COL_ROOM = "obs_room";
    public static final String COL_ROUND = "obs_round";
    public static final String COL_OBSID = "obs_observationID";
    public static final String COL_RESID = "obs_respondentID";
    public static final String COL_RNAME = "obs_respondentName";
    public static final String COL_USAGE = "obs_roomUsage";
    public static final String COL_VISIT = "obs_numberOfVisits";
    public static final String COL_RESULT = "obs_result";
    public static final String COL_RESULTSPY = "obs_resultOther";
    public static final String COL_COM = "obs_comments";
    public static final String COL_LAT = "obs_Latitude";
    public static final String COL_LON = "obs_longitude";
    public static final String COL_EBY = "obs_editedBy";
    public static final String COL_EAT = "obs_editedAt";
    public static final String COL_GC = "obs_GCRecord";


    private static DataBaseHelper healper = null ;
    private static String TAG ="ObservationDAO" ;

    private ObservationDAO() {
    }

    protected static ObservationDAO getInstance(DataBaseHelper helper){
        healper = helper;
        return new ObservationDAO();
    }

    /**
     * This method called once version of SQLiteHelper get updated
     * @param db is writable database passed from SQLiteHelper
     */
    public static void createObservationTable(SQLiteDatabase db){
        String createQuery = "Create Table " +
                TABLE_NAME + " ( " +
                COL_ID + " text Primary key, " +
                COL_DATE + " text not null," +
                COL_STF + " text not null," +
                COL_ROOM  + " text  not null," +
                COL_ROUND  + " text  not null," +
                COL_OBSID  + " text  not null unique," +
                COL_RESID  + " text not null," +
                COL_RNAME + " text not null," +
                COL_USAGE + " text not null," +
                COL_VISIT  + " text  not null," +
                COL_RESULT  + " text not null," +
                COL_RESULTSPY  + " text," +
                COL_COM + " text," +
                COL_LAT+ " text," +
                COL_LON+ " text," +
                COL_GC + " text ," +
                COL_EBY+  " text ," +
                COL_EAT + " text ," +
                "FOREIGN KEY("+COL_ROOM+") REFERENCES "+ RoomDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_ROUND+") REFERENCES "+ RoundDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_EBY+") REFERENCES "+ RoundDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_STF+") REFERENCES "+ StaffDAO.TABLE_NAME +"(oid)"+")";
        db.execSQL(createQuery);

    }


    /**
     * Method called from SqliteHelper in case of upgrade
     * @param db
     */
    public static void upgradeObservationTable(SQLiteDatabase db){
        // write upgrade query if any
        String upgradeQuery = "" ;
        db.execSQL(upgradeQuery);
        // db.close();
    }



    public ReturnMessage addOBS(Observation obs){

        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, obs.getObs_ID());
        cv.put(COL_DATE, obs.getObs_CreatedAt());
        cv.put(COL_STF,obs.getObs_Createdby());
        cv.put(COL_ROOM,obs.getObs_Room());
        cv.put(COL_ROUND,obs.getObs_Round());
        cv.put(COL_OBSID,obs.getObs_ObservationID());
        cv.put(COL_RESID,obs.getObs_RespondentID());
        cv.put(COL_RNAME,obs.getObs_RespondentName());
        cv.put(COL_USAGE,obs.getObs_RoomUsage());
        cv.put(COL_VISIT,obs.getObs_NumberOfVisits());
        cv.put(COL_RESULT,obs.getObs_Result());
        cv.put(COL_RESULTSPY,obs.getObs_ResultOther());
        cv.put(COL_COM, obs.getObs_Comments());
        cv.put(COL_LAT, obs.getObs_Latitude());
        cv.put(COL_LON, obs.getObs_longitude());

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
     * Method used to update Observation
    // * @param Observation
     * @return
     */
    public ReturnMessage updateOBS(Observation obs){
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();

        cv.put(COL_ROOM,obs.getObs_Room());
        cv.put(COL_ROUND,obs.getObs_Round());
        cv.put(COL_OBSID,obs.getObs_ObservationID());
        cv.put(COL_RESID,obs.getObs_RespondentID());
        cv.put(COL_RNAME,obs.getObs_RespondentName());
        cv.put(COL_USAGE,obs.getObs_RoomUsage());
        cv.put(COL_VISIT,obs.getObs_NumberOfVisits());
        cv.put(COL_RESULT,obs.getObs_Result());
        cv.put(COL_RESULTSPY,obs.getObs_ResultOther());
        cv.put(COL_COM, obs.getObs_Comments());
        cv.put(COL_EBY, obs.getObs_EditedBy());
        cv.put(COL_EAT, obs.getObs_EditedAt());
        String whereClause = COL_ID + "=?" ;
        int i = healper.getWritableDatabase().update(TABLE_NAME ,cv, whereClause , new String[]{obs.getObs_ID()+""} );
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