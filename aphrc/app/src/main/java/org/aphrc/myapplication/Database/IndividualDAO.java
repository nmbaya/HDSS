package org.aphrc.myapplication.Database;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.aphrc.myapplication.Model.Individual;
import org.aphrc.myapplication.Model.Visitor;
import org.aphrc.myapplication.R;

public class IndividualDAO {
    public static final String TABLE_NAME = "Individual";
    public static final String COL_ID = "oid";
    public static final String COL_DATE = "ind_createdAt";
    public static final String COL_STF = "ind_createdBy";
    public static final String COL_IND = "ind_individualID";
    public static final String COL_NAME = "ind_name";
    public static final String COL_NICKNAME = "ind_nickname";
    public static final String COL_Q4P4A = "ind_q4p4a";
    public static final String COL_Q4P4B = "ind_q4p4b";
    public static final String COL_Q4P5 = "ind_q4p5";
    public static final String COL_Q4P6 = "ind_q4p6";
    public static final String COL_Q4P7A = "ind_q4p7a";
    public static final String COL_Q4P7B = "ind_q4p7b";
    public static final String COL_Q4P10A = "ind_q4p10a";
    public static final String COL_Q4P10B = "ind_q4p10b";
    public static final String COL_Q5P28A= "ind_q5p28a";
    public static final String COL_Q5P28B= "ind_q5p28b";
    public static final String COL_VIS = "ind_visitor";

    public static final String COL_MotherAlive= "ind_motherAlive";
    public static final String COL_FatherAlive= "ind_fatherAlive";
    public static final String COL_MaritalStatus= "ind_maritalStatus";
    public static final String COL_CurrentStatus= "ind_currentStatus";
    public static final String COL_StatusDate= "ind_statusDate";
    public static final String COL_PGOStatus= "ind_pgoStatus";
    public static final String COL_StatusComment= "ind_statusComment";
    public static final String COL_IncomeActivity= "ind_incomeActivity";
    public static final String COL_EducationStatus= "ind_educationStatus";
    public static final String COL_EducationLevel= "ind_educationLevel";
    public static final String COL_EducationClass= "ind_educationClass";
    public static final String COL_EBY = "ind_editedBy";
    public static final String COL_EAT = "ind_editedAt";
    public static final String COL_GC = "ind_GCRecord";


    private static DataBaseHelper healper = null;
    private static String TAG = "IndividualDAO";

    private IndividualDAO() {
    }

    protected static IndividualDAO getInstance(DataBaseHelper helper) {
        healper = helper;
        return new IndividualDAO();
    }

    /**
     * This method called once version of SQLiteHelper get updated
     *
     * @param db is writable database passed from SQLiteHelper
     */
    public static void createIndividualTable(SQLiteDatabase db) {
        String createQuery = "Create Table " +
                TABLE_NAME + " ( " +
                COL_ID + " text Primary key , " +
                COL_DATE + " text NOT NULL," +
                COL_STF + " text NOT NULL," +
                COL_IND + " text NOT NULL UNIQUE," +
                COL_NAME + " text," +
                COL_NICKNAME  + " text," +
                COL_Q4P4A + " text," +
                COL_Q4P4B + " text," +
                COL_Q4P5 + " text," +
                COL_Q4P6 + " int," +
                COL_Q4P7A + " int," +
                COL_Q4P7B + " text," +
                COL_Q4P10A + " int," +
                COL_Q4P10B + " text," +
                COL_Q5P28A + " text," +
                COL_Q5P28B + " text," +
                COL_VIS+ " Integer ," +
                COL_MotherAlive + " int," +
                COL_FatherAlive + " int," +
                COL_MaritalStatus + " int," +
                COL_CurrentStatus + " int," +
                COL_StatusDate + " text," +
                COL_PGOStatus + " int," +
                COL_StatusComment + " text," +
                COL_IncomeActivity + " int," +
                COL_EducationStatus + " int," +
                COL_EducationLevel + " int," +
                COL_EducationClass + " int," +
                COL_GC + " text ," +
                COL_EBY+  " text ," +
                COL_EAT + " text ," +
                "FOREIGN KEY(" + COL_EBY + ") REFERENCES " + StaffDAO.TABLE_NAME + "(oid)," +
                "FOREIGN KEY("+COL_STF+") REFERENCES "+ StaffDAO.TABLE_NAME +"(oid)"+")";

        db.execSQL(createQuery);

    }

    /**
     * Method called from SqliteHelper in case of upgrade
     *
     * @param db
     */
    public static void upgradeIndividualTable(SQLiteDatabase db) {
        // write upgrade query if any
        String upgradeQuery = "";
        db.execSQL(upgradeQuery);
        // db.close();
    }


    public ReturnMessage addIND(Individual ind) {

        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_ID, ind.getInd_oid());
        cv.put(COL_DATE, ind.getInd_CreatedAt());
        cv.put(COL_STF, ind.getInd_Createdby());
        cv.put(COL_IND, ind.getInd_IndividualID());
        cv.put(COL_NAME, ind.getInd_Name());
        cv.put(COL_NICKNAME, ind.getInd_NickName());
        cv.put(COL_Q4P4A, ind.getInd_q4p4a());
        cv.put(COL_Q4P4B, ind.getInd_q4p4b());
        cv.put(COL_Q4P5, ind.getInd_q4p5());
        cv.put(COL_Q4P6, ind.getInd_q4p6());
        cv.put(COL_Q4P7A, ind.getInd_q4p7a());
        cv.put(COL_Q4P7B, ind.getInd_q4p7b());
        cv.put(COL_Q4P10A, ind.getInd_q4p10a());
        cv.put(COL_Q4P10B, ind.getInd_q4p10b());
        cv.put(COL_Q5P28A, ind.getInd_q5p28a());
        cv.put(COL_Q5P28B, ind.getInd_q5p28b());
        cv.put(COL_VIS, ind.getInd_Visitor());


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
     * Method used to update Room
     *
     * @param ind
     * @return
     */
    public ReturnMessage updateIND(Individual ind) {
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();

       // cv.put(COL_IND, ind.getInd_IndividualID());
        cv.put(COL_NAME, ind.getInd_Name());
        cv.put(COL_NICKNAME, ind.getInd_NickName());
        cv.put(COL_Q4P4A, ind.getInd_q4p4a());
        cv.put(COL_Q4P4B, ind.getInd_q4p4b());
        cv.put(COL_Q4P5, ind.getInd_q4p5());
        cv.put(COL_Q4P6, ind.getInd_q4p6());
        cv.put(COL_Q4P7A, ind.getInd_q4p7a());
        cv.put(COL_Q4P7B, ind.getInd_q4p7b());
        cv.put(COL_Q4P10A, ind.getInd_q4p10a());
        cv.put(COL_Q4P10B, ind.getInd_q4p10b());
        cv.put(COL_Q5P28A, ind.getInd_q5p28a());
        cv.put(COL_Q5P28B, ind.getInd_q5p28b());
       // cv.put(COL_VIS, ind.getInd_Visitor());
        cv.put(COL_EBY, ind.getInd_EditedBy());
        cv.put(COL_EAT, ind.getInd_EditedAt());
        String whereClause = COL_ID + "=?";
        int i = healper.getWritableDatabase().update(TABLE_NAME, cv, whereClause, new String[]{ind.getInd_oid() + ""});
        if (i > 0) {
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully);
        } else {
            ReturnMessage.success = false;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated);
        }
        return rm;
    }

    /**
     * Method used to update Room
     *
     * @param ind
     * @return
     */
    public ReturnMessage updateIND_EVS(Individual ind) {
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();

        cv.put(COL_MotherAlive, ind.getInd_MotherAlive());
        cv.put(COL_FatherAlive, ind.getInd_FatherAlive());
        cv.put(COL_MaritalStatus, ind.getInd_MaritalStatus());
        cv.put(COL_CurrentStatus, ind.getInd_CurrentStatus());
        cv.put(COL_StatusDate, ind.getInd_StatusDate());
        cv.put(COL_PGOStatus, ind.getInd_PGOStatus());
        cv.put(COL_StatusComment, ind.getInd_StatusComment());
        cv.put(COL_IncomeActivity, ind.getInd_IncomeActivity());

        String whereClause = COL_ID + "=?";
        int i = healper.getWritableDatabase().update(TABLE_NAME, cv, whereClause, new String[]{ind.getInd_oid() + ""});
        if (i > 0) {
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully);
        } else {
            ReturnMessage.success = false;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated);
        }
        return rm;
    }

    /**
     * Method used to update Room
     *
     * @param ind
     * @return
     */
    public ReturnMessage updateIND_EDU(Individual ind) {
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();

        cv.put(COL_EducationStatus, ind.getInd_EducationStatus());
        cv.put(COL_EducationLevel, ind.getInd_EducationLevel());
        cv.put(COL_EducationClass,  ind.getInd_EducationClass());
        String whereClause = COL_ID + "=?";
        int i = healper.getWritableDatabase().update(TABLE_NAME, cv, whereClause, new String[]{ind.getInd_oid() + ""});
        if (i > 0) {
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully);
        } else {
            ReturnMessage.success = false;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated);
        }
        return rm;
    }

    public ReturnMessage updateVis (Individual ind){
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_GC, 999999);
        String whereClause = COL_ID + "=?";
        int i = healper.getWritableDatabase().update(TABLE_NAME, cv, whereClause, new String[]{ind.getInd_oid() + ""});
        if (i > 0) {
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully);
        } else {
            ReturnMessage.success = false;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated);
        }
        return rm;
    }

    public ReturnMessage updatetoPRR (Individual ind){
        ReturnMessage rm = new ReturnMessage();
        ContentValues cv = new ContentValues();
        cv.put(COL_VIS, 0);
        String whereClause = COL_ID + "=?";
        int i = healper.getWritableDatabase().update(TABLE_NAME, cv, whereClause, new String[]{ind.getInd_oid() + ""});
        if (i > 0) {
            ReturnMessage.message = healper.getContext().getString(R.string.record_updated_successfully);
        } else {
            ReturnMessage.success = false;
            ReturnMessage.message = healper.getContext().getString(R.string.record_couldnt_be_updated);
        }
        return rm;
    }
}