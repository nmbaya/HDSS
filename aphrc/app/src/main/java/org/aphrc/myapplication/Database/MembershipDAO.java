package org.aphrc.myapplication.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.aphrc.myapplication.Model.Membership;
import org.aphrc.myapplication.Model.Residency;
import org.aphrc.myapplication.R;

import java.util.Random;

public class MembershipDAO {
    public static final String TABLE_NAME = "Membership";
    public static final String COL_ID = "oid";
    public static final String COL_DATE = "mem_CreatedAt";
    public static final String COL_STF = "mem_CreatedBy";
    public static final String COL_IND = "mem_Individual";
    public static final String COL_HH = "mem_rltHHH";
    public static final String COL_SGP = "mem_Socialgroup";
    public static final String COL_SOBS = "mem_StartObservation";
    public static final String COL_STYPE = "mem_StartEventType";
    public static final String COL_SDATE = "mem_StartEventDate";
    public static final String COL_EOBS = "mem_EndObservation";
    public static final String COL_ETYPE = "mem_EndEventType";
    public static final String COL_EDATE = "mem_EndEventDate";
    public static final String COL_EBY = "mem_editedBy";
    public static final String COL_EAT = "mem_editedAt";
    public static final String COL_GC = "mem_GCRecord";

    private static DataBaseHelper healper = null ;
    private static String TAG ="MembershipDAO" ;

    private MembershipDAO() {
    }

    protected static MembershipDAO getInstance(DataBaseHelper helper){
        healper = helper;
        return new MembershipDAO();
    }

    /**
     * This method called once version of SQLiteHelper get updated
     * @param db is writable database passed from SQLiteHelper
     */
    public static void createMembershipTable(SQLiteDatabase db){
        String createQuery = "Create Table " +
                TABLE_NAME + " ( " +
                COL_ID + " text Primary key , " +
                COL_DATE + " text NOT NULL," +
                COL_STF+ " text NOT NULL," +
                COL_IND + " text NOT NULL ," +
                COL_HH + " integer ," +
                COL_SGP + " text ," +
                COL_SOBS + " text ," +
                COL_STYPE + " text ," +
                COL_SDATE + " text ," +
                COL_EOBS + " text," +
                COL_ETYPE + " text," +
                COL_EDATE + " text," +
                COL_GC + " text ," +
                COL_EBY+  " text ," +
                COL_EAT + " text ," +
                "FOREIGN KEY("+COL_SOBS+") REFERENCES "+ ObservationDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_SGP+") REFERENCES "+ SocialGroupDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_IND+") REFERENCES "+ IndividualDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_EBY+") REFERENCES "+ StaffDAO.TABLE_NAME +"(oid),"+
                "FOREIGN KEY("+COL_STF+") REFERENCES "+ StaffDAO.TABLE_NAME +"(oid)"+")";
        db.execSQL(createQuery);

    }

    /**
     * Method called from SqliteHelper in case of upgrade
     * @param db
     */
    public static void upgradeMembershipTable(SQLiteDatabase db){
        // write upgrade query if any
        String upgradeQuery = "" ;
        db.execSQL(upgradeQuery);
        // db.close();
    }


    public ReturnMessage addMEM(Membership mem){

        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, mem.getMem_oid());
        cv.put(COL_IND,mem.getMem_Individual());
        cv.put(COL_DATE,mem.getMem_CreatedAt());
        cv.put(COL_STF,mem.getMem_CreatedBy());
        cv.put(COL_HH,mem.getMem_rltHHH());
        cv.put(COL_SGP,mem.getMem_Socialgroup());
        cv.put(COL_SOBS,mem.getMem_StartObservation());
        cv.put(COL_STYPE,mem.getMem_StartEventType());
        cv.put(COL_SDATE,mem.getMem_StartEventDate());
        cv.put(COL_EOBS,mem.getMem_EndObservation());
        cv.put(COL_ETYPE,mem.getMem_EndEventType());
        cv.put(COL_EDATE,mem.getMem_EndEventDate());

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
     * Method used to update Membership
     * @param mem
     * @return
     */
    public ReturnMessage updateMEM(Membership mem){
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();

        cv.put(COL_EOBS,mem.getMem_EndObservation());
        cv.put(COL_ETYPE,mem.getMem_EndEventType());
        cv.put(COL_EDATE,mem.getMem_EndEventDate());
        String whereClause = COL_IND + "=? and " + COL_SGP + "=? and " + COL_EOBS +" IS NULL ";
        int i = healper.getWritableDatabase().update(TABLE_NAME ,cv, whereClause , new String[]{mem.getMem_Individual()+"",mem.getMem_Socialgroup()+""} );

        if(i>0){
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully)  ;
        }
        else{
            ReturnMessage.success = false ;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated)  ;
        }
        return rm ;
    }

    public void updateVis (Membership mem){
        Random r =new Random();
        int randomnumber =r.nextInt(100000);

        ContentValues cv = new ContentValues();
        cv.put(COL_GC, randomnumber);
        String whereClause = COL_IND + "=?";
        int i = healper.getWritableDatabase().update(TABLE_NAME, cv, whereClause, new String[]{mem.getMem_Individual() + ""});
    }

}