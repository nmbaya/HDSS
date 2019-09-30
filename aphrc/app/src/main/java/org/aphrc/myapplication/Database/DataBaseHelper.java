package org.aphrc.myapplication.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import org.aphrc.myapplication.tableview.model.Cell;
import org.aphrc.myapplication.Model.*;

/**
 * Created by nmbaya on 1/11/2018.
 */


public class DataBaseHelper  extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "xxxxx.db" ;
    public static final int VERSION = 1 ;

    private static StaffDAO staffDAO = null;
    private static RoomDAO roomDAO = null;

    private Context context ;


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
        this.context = context ;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        staffDAO.createStaffTable(sqLiteDatabase);



    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    /**
     * Method return an object
     * @return
     */

    public StaffDAO getStaffDAO(){

        return StaffDAO.getInstance(this);
    }
    public RoomDAO getRoomDAO(){

        return RoomDAO.getInstance(this);
    }

    public RoundDAO getRoundDAO(){

        return RoundDAO.getInstance(this);
    }

    public StructureDAO getStructureDAO(){

        return StructureDAO.getInstance(this);
    }

    public SocialGroupDAO getSocialGroupDAO(){

        return SocialGroupDAO.getInstance(this);
    }


    public Context getContext(){
        return this.context ;
    }


    public List<String> getAllDeadPersons(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  i.oid,ind_IndividualID FROM  Individual i " +
                "INNER JOIN Residency res on res.res_Individual=i.oid " +
                "WHERE res.res_EndEventType='DTH'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public List<String> getAllRooms(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  oid,rm_roomID FROM  Room";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public List<String> getAllHouseholds(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  oid,sgp_socialgroupID FROM  Socialgroup";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public List<String> getAllIndividuals(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  oid,ind_IndividualID FROM  Individual";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public List<String> getAllStructures(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  oid, str_StructureID FROM  Structure";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public List<String> getAllRounds(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  oid, rnd_RoundNumber FROM  Round  order by rnd_RoundNumber desc limit 1 ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }



    public List<List<Cell>> getAllIndividuals(int startIndex){

        List<List<Cell>> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "\n" +
                " SELECT  distinct ind_IndividualID" +
                ",ind_name Name" +
                ",CASE ind_q4p6 WHEN 1 THEN 'Male' WHEN 2 THEN 'Female' ELSE ind_q4p6 END Sex" +
                " ,Date('now')-(substr(ind_q4p4b, 1, 4)||'-'||substr(ind_q4p4b, 6,2)||'-'||substr(ind_q4p4b, 9,2)) as Age,ind_q4p4b , " +
                " CASE ind_q4p7a  WHEN 1 THEN 'Kikuyu' WHEN 2 THEN 'Luhya' WHEN 3 THEN 'Luo' WHEN 4 THEN 'Kamba' " +
                "WHEN 5 THEN 'Meru' WHEN 6 THEN 'Embu' WHEN 7 THEN 'Kisii' WHEN 8 THEN 'Mijikenda' WHEN 9 THEN 'Swahili' " +
                "WHEN 10 THEN 'Somali' WHEN 11 THEN 'Taita' WHEN 12 THEN 'Taveta' WHEN 13 THEN 'Masai' WHEN 14 THEN 'Kalenjin' " +
                "WHEN 15 THEN 'Other' ELSE ind_q4p7a  END Ethinicity " +
                ",CASE ind_q4p10a  WHEN 1  THEN  'AUN' WHEN 2  THEN  'BIL' WHEN 3  THEN  'BRO' WHEN 4  THEN  'CHD' " +
                "WHEN 5  THEN  'COU' WHEN 6  THEN  'CWF' WHEN 7  THEN  'DIL' WHEN 8  THEN  'GCH' WHEN 9  THEN  'GDP' " +
                "WHEN 10  THEN  'HHH' WHEN 11  THEN  'HUS' WHEN 12  THEN  'NEP' WHEN 13  THEN  'NIE' WHEN 14  THEN  'NRL' " +
                "WHEN 15  THEN  'PAR' WHEN 16  THEN  'PIL' WHEN 17  THEN  'SIL' WHEN 18  THEN  'SIS' WHEN 19  THEN  'SOL' " +
                "WHEN 20  THEN  'STC' WHEN 21  THEN  'STP' WHEN 22  THEN  'UNC' WHEN 23  THEN  'UNK' WHEN 24  THEN  'WIF' " +
                "WHEN 25  THEN  'OTH' ELSE ind_q4p10a END RelationtoHHH " +
                ",res_StartEventType,res_StartEventDate " +
                ",CASE ind_CurrentStatus WHEN 1 THEN 'PRR' WHEN 1 THEN 'TAR' WHEN 1 THEN 'DTH' WHEN 1 THEN 'EXT' " +
                "WHEN 1 THEN 'CBS' WHEN 1 THEN 'OMG' WHEN 1 THEN 'UNK' ELSE ind_CurrentStatus END CurrentStatus " +
                ",CASE ind_PGOStatus WHEN 1 THEN 'YES' WHEN 2 THEN 'NO' ELSE ind_PGOStatus END PGOStatus " +
                ",CASE ind_MotherAlive WHEN 1 THEN 'YES' WHEN 2 THEN 'NO' ELSE ind_MotherAlive END MotherAlive " +
                ",CASE ind_FatherAlive WHEN 1 THEN 'YES' WHEN 2 THEN 'NO' ELSE ind_FatherAlive END FatherAlive " +
                ",CASE ind_IncomeActivity WHEN 1 THEN 'Unestablished own business' WHEN 2 THEN 'Established own business' " +
                "WHEN 3 THEN 'Informal casual' WHEN 4 THEN 'Informal salaried' WHEN 5 THEN 'Formal salaried' WHEN 6 THEN 'Formal casual' " +
                "WHEN 7 THEN 'Rural Agriculture' WHEN 8 THEN 'Urban Agriculture' WHEN 9 THEN 'Other' ELSE ind_IncomeActivity END IncomeActivity " +
                ", CASE ind_MaritalStatus WHEN 1 THEN 'Married' WHEN 2 THEN 'Living with a man/woman' WHEN 3 THEN 'Widowed' " +
                "WHEN 4 THEN 'Separated' WHEN 5 THEN 'Divorced' WHEN 6 THEN 'Never Married' WHEN 7 THEN 'Married to new partner' " +
                "WHEN 8 THEN 'Living with new partner' ELSE ind_MaritalStatus END MaritalStatus " +
                ",CASE ind_EducationStatus WHEN 1 THEN 'YES' WHEN 2 THEN 'NO' ELSE ind_EducationStatus END EducationStatus " +
                ",CASE ind_EducationLevel WHEN 1 THEN 'Primary' WHEN 2 THEN 'Secondary' WHEN 3 THEN 'Higher' ELSE ind_EducationLevel END EducationLevel " +
                ",ind_educationClass " +
                ",Individual.ind_visitor,Room.rm_roomID " +
                ",Socialgroup.sgp_socialgroupID FROM  Individual " +
                "INNER JOIN Residency on Residency.res_Individual=Individual.oid " +
                "INNER JOIN Room on Room.oid=Residency.res_Room " +
                "INNER JOIN Membership on Membership.mem_Individual=Individual.oid " +
                "INNER JOIN Socialgroup on Socialgroup.oid=Membership.mem_Socialgroup " +
                "WHERE Socialgroup.sgp_socialgroupID='"+ GlobalClass.socialgroupID +"' " +
                "and Room.rm_roomID='"+ GlobalClass.roomID +"' and res_EndObservation is null and mem_EndObservation is null";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        GlobalClass.ROW_SIZE=cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
            List<Cell> cellList = new ArrayList<>();
            list.add(cellList);
            for (int j = 0; j < 21; j++) {
                String text = cursor.getString(j);
                String id = cursor.getString(1);
                Cell cell = new Cell(id, text);
                cellList.add(cell);
            }

        // looping through all rows and adding to list

            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public int getINDCount(){

        // Select All Query
        //  String selectQuery = "SELECT  * FROM  Individual " +   "";

        String selectQuery = "\n" +
                "SELECT  * FROM  Individual limit 100";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return Count;
    }

    public List<List<Cell>> getAllINDs(){

        List<List<Cell>> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "\n" +
                "select i.ind_createdAt, s.stf_FirstName,\n" +
                "    i.ind_IndividualID, i.ind_name,  i.ind_nickname,  i.ind_q4p4a,  i.ind_q4p4b,\n" +
                "    Date('now')-(substr(ind_q4p4b, 1, 4)||'-'||substr(ind_q4p4b, 6,2)||'-'||substr(ind_q4p4b, 9,2)) ind_q4p5,\n" +
                "    CASE ind_q4p6 WHEN 1 THEN 'Male' WHEN 2 THEN 'Female' ELSE ind_q4p6 END ind_q4p6 ,\n" +
                "    CASE ind_q4p7a WHEN 1 THEN 'KIK' WHEN 2 THEN 'LUH' WHEN 3 THEN 'LUO' " +
                "    WHEN 4 THEN 'KAM' WHEN 5 THEN 'MER' WHEN 6 THEN 'EMB' " +
                "    WHEN 7 THEN 'KIS' WHEN 8 THEN 'MIJ' WHEN 9 THEN 'SWA' " +
                "    WHEN 10 THEN 'SOM' WHEN 11 THEN 'TAI' WHEN 12 THEN 'TAV' " +
                "    WHEN 13 THEN 'MAS' WHEN 14 THEN 'KAL' WHEN 15 THEN 'OTH' " +
                "    ELSE ind_q4p7a END ind_q4p7a ,\n" +
                "    i.ind_q4p7b, i.ind_q5p28a,i.ind_q5p28b,i.ind_visitor, i.oid\n" +
                "    FROM  Individual i " +
                "    INNER JOIN staff s on s.oid=i.ind_createdby" +
                " order by ind_createdAt desc limit 100";


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        GlobalClass.ROW_SIZE=cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                List<Cell> cellList = new ArrayList<>();
                list.add(cellList);
                for (int j = 0; j < 15; j++) {
                    String text = cursor.getString(j);
                    String id = cursor.getString(1);
                    Cell cell = new Cell(id, text);
                    cellList.add(cell);
                }

                // looping through all rows and adding to list

            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public List<List<Cell>> getAllObservations(int startIndex){

        List<List<Cell>> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "\n" +
                "select obs_observationID,Room.rm_roomID obs_room,Round.rnd_roundNumber," +
                "CASE WHEN obs_roomUsage=1 THEN	'Sleeping Room'" +
                "WHEN obs_roomUsage=2   THEN 'Sleeping and Business Room'" +

                "ELSE obs_roomUsage END obs_roomUsage,obs_createdAt,Observation.oid from Observation " +
                "INNER JOIN Room on Room.oid=Observation.obs_room " +
                "INNER JOIN Round on Round.oid=Observation.obs_round " +
                "where " +GlobalClass.columnname + " like '"+GlobalClass.search+"%'" + " limit 50";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        GlobalClass.ROW_SIZE=cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                List<Cell> cellList = new ArrayList<>();
                list.add(cellList);
                for (int j = 0; j < 6; j++) {
                    String text = cursor.getString(j);
                    String id = cursor.getString(1);
                    Cell cell = new Cell(id, text);
                    cellList.add(cell);
                }

                // looping through all rows and adding to list

            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public int getIndividualCount(){

        // Select All Query
      //  String selectQuery = "SELECT  * FROM  Individual " +   "";

        String selectQuery = "\n" +
                "SELECT  distinct Room.rm_roomID,Socialgroup.sgp_socialgroupID,ind_IndividualID,ind_name,case ind_q4p6 " +
                "WHEN 1 THEN 'Male' WHEN 2 THEN 'Female' ELSE ind_q4p6 END ind_q4p6,ind_q4p4b, " +
                "Date('now')-(substr(ind_q4p4b, 1, 4)||'-'||substr(ind_q4p4b, 6,2)||'-'||substr(ind_q4p4b, 9,2)) as Age, " +
                "ind_q4p7a,'','','','','','','' FROM  Individual " +
                "INNER JOIN Residency on Residency.res_Individual=Individual.oid " +
                "INNER JOIN Room on Room.oid=Residency.res_Room " +
                "INNER JOIN Membership on Membership.mem_Individual=Individual.oid " +
                "INNER JOIN Socialgroup on Socialgroup.oid=Membership.mem_Socialgroup " +
                "WHERE Socialgroup.sgp_socialgroupID='"+ GlobalClass.socialgroupID +"' " +
                "and Room.rm_roomID='"+ GlobalClass.roomID +"' and res_EndObservation is null and mem_EndObservation is null";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return Count;
    }

    public int getObservationCount(){

        // Select All Query
        String selectQuery = "SELECT  * FROM  Observation " +
                "where " +GlobalClass.columnname + " like '"+GlobalClass.search+"%'" + " limit 50";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return Count;
    }

    public int getStructureCount(){

        // Select All Query
        String selectQuery = "select  n.str_site,n.str_structureID Structure, h.hrb_Name hrb ,a.str_structureID aAfter,b.str_structureID bbefore,n.oid " +
                "from Structure n " +
                "left join Structure a on a.oid=n.str_after " +
                "left join Structure b on b.oid=n.str_before " +
                "left join HouseholdRegistrationBook h on h.oid=n.str_hrb " +
                "where " +GlobalClass.columnname + " like '"+GlobalClass.search+"%'" + " limit 50";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return Count;
    }



    public List<List<Cell>> getStructures(int startIndex){

        List<List<Cell>> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "\n" +
                "select Case when n.str_site=1 then 'Korogocho' when n.str_site=2 then 'Viwandani' end site\n" +
                ",n.str_structureID Structure, h.hrb_Name hrb ,a.str_structureID aAfter,b.str_structureID bbefore,n.oid \n" +
                "from Structure n \n" +
                "left join Structure a on a.oid=n.str_after\n" +
                "left join Structure b on b.oid=n.str_before \n" +
                "left join HouseholdRegistrationBook h on h.oid=n.str_hrb " +
                "where " +GlobalClass.columnname + " like '"+GlobalClass.search+"%'" + " limit 50";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        GlobalClass.ROW_SIZE=cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                List<Cell> cellList = new ArrayList<>();
                list.add(cellList);
                for (int j = 0; j < 6; j++) {
                    String text = cursor.getString(j);
                    String id = cursor.getString(1);
                    Cell cell = new Cell(id, text);
                    cellList.add(cell);
                }

                // looping through all rows and adding to list

            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }


    ///=======================Room details==============================

    public int getRoomCount(){

        // Select All Query
        String selectQuery = "select r.oid,rm_createdAt,s.str_structureID Structure,rm_number,rm_roomID from Room r\n" +
                " left join Structure s on s.oid=r.rm_structure \n" +
                " where r.rm_structure  in (select oid from Structure) " +
                "and " +GlobalClass.columnname + " like '"+GlobalClass.search+"%'" + " limit 50";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return Count;
    }

    public List<List<Cell>> getAllRooms(int startIndex){

        List<List<Cell>> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "\n" +
                "select rm_roomID,s.str_structureID Structure,rm_createdAt,r.oid from Room r\n" +
                " left join Structure s on s.oid=r.rm_structure \n" +
                " where r.rm_Structure  in (select oid from Structure)  " +
                "and " +GlobalClass.columnname + " like '"+GlobalClass.search+"%'" + " limit 50";




        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        GlobalClass.ROW_SIZE=cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                List<Cell> cellList = new ArrayList<>();
                list.add(cellList);
                for (int j = 0; j < 4; j++) {
                    String text = cursor.getString(j);
                    String id = cursor.getString(1);
                    Cell cell = new Cell(id, text);
                    cellList.add(cell);
                }

                // looping through all rows and adding to list

            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    ///=======================Socialgroup details==============================

    public int getSocialgroupCount(){

        // Select All Query
        String selectQuery = "SELECT  * FROM  Socialgroup";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return Count;
    }

    public int getVisitorCount(){

        // Select All Query
        String selectQuery = "SELECT  * FROM  Visitor";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return Count;
    }

    public Cursor getSocialgroups(String room){

        // Select All Query
        String selectQuery = "SELECT  sgp_socialgroupID FROM  Socialgroup WHERE sgp_socialgroupID LIKE '%" + room +"%' ORDER BY sgp_socialgroupID DESC LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection         // closing connection

       // cursor.close();
       // db.close();
        // returning lables
        return cursor;
    }

    public Cursor getIndividuals(String SocialgroupID){

        // Select All Query
        String selectQuery = "SELECT  ind_IndividualID FROM  Individual WHERE ind_IndividualID LIKE '%" + SocialgroupID +"%' ORDER BY ind_IndividualID DESC LIMIT 1";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection         // closing connection

        // cursor.close();
        // db.close();
        // returning lables
        return cursor;
    }


    public List<List<Cell>> getAllSocialgroups(int startIndex){

        List<List<Cell>> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "\n" +
                "select sgp_socialgroupID,sgp_createdAt,oid from Socialgroup";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        GlobalClass.ROW_SIZE=cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                List<Cell> cellList = new ArrayList<>();
                list.add(cellList);
                for (int j = 0; j < 3; j++) {
                    String text = cursor.getString(j);
                    String id = cursor.getString(1);
                    Cell cell = new Cell(id, text);
                    cellList.add(cell);
                }

                // looping through all rows and adding to list

            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }


    public List<List<Cell>> getAllVistorss(int startIndex){

        List<List<Cell>> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "\n" +
                "select vis_IndividualID,vis_createdAt,oid from Visitor";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        GlobalClass.ROW_SIZE=cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                List<Cell> cellList = new ArrayList<>();
                list.add(cellList);
                for (int j = 0; j < 3; j++) {
                    String text = cursor.getString(j);
                    String id = cursor.getString(1);
                    Cell cell = new Cell(id, text);
                    cellList.add(cell);
                }

                // looping through all rows and adding to list

            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    ///=======================Residency details==============================

    public int getResidencyCount(){

        // Select All Query
        String selectQuery = "SELECT  * FROM  Residency";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return Count;
    }

    public List<List<Cell>> getAllResidencys(int startIndex){

        List<List<Cell>> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "select i.ind_IndividualID,i.ind_name,r.rm_roomID," +
                "res_StartEventType,res_StartEventDate,o1.obs_observationID, " +
                "res_EndEventType,res_EndEventDate,o2.obs_observationID,res_createdAt,i.oid " +
                "from Residency res " +
                "INNER JOIN Individual i on i.oid=res.res_individual " +
                "LEFT JOIN Observation o1 on o1.oid=res.res_StartObservation " +
                "LEFT JOIN Room r on r.oid=res.res_Room " +
                "LEFT JOIN Observation o2 on o2.oid=res.res_EndObservation";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        GlobalClass.ROW_SIZE=cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                List<Cell> cellList = new ArrayList<>();
                list.add(cellList);
                for (int j = 0; j < 11; j++) {
                    String text = cursor.getString(j);
                    String id = cursor.getString(1);
                    Cell cell = new Cell(id, text);
                    cellList.add(cell);
                }

                // looping through all rows and adding to list

            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    ///=======================Membership details==============================

    public int getMembershipCount(){

        // Select All Query
        String selectQuery = "SELECT  * FROM  Membership";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return Count;
    }

    public List<List<Cell>> getAllMemberships(int startIndex){

        List<List<Cell>> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "select i.ind_IndividualID,i.ind_name,mem.mem_rltHHH,s.sgp_socialgroupID," +
                "mem_StartEventType,mem_StartEventDate,o1.obs_observationID, " +
                "mem_EndEventType,mem_EndEventDate,o2.obs_observationID,mem_createdAt,i.oid " +
                "from Membership mem " +
                "INNER JOIN Individual i on i.oid=mem.mem_individual " +
                "LEFT JOIN Observation o1 on o1.oid=mem.mem_StartObservation " +
                "LEFT JOIN Socialgroup s on s.oid=mem.mem_Socialgroup " +
                "LEFT JOIN Observation o2 on o2.oid=mem.mem_EndObservation ";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        GlobalClass.ROW_SIZE=cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                List<Cell> cellList = new ArrayList<>();
                list.add(cellList);
                for (int j = 0; j < 11; j++) {
                    String text = cursor.getString(j);
                    String id = cursor.getString(1);
                    Cell cell = new Cell(id, text);
                    cellList.add(cell);
                }

                // looping through all rows and adding to list

            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }





    public Cursor getOneObservation(String oid){

        String selectQuery = "Select * from Observation where oid='"+oid+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        if (!c.moveToFirst()) {
            return null;
        }
        return c;
    }



    public Cursor getOneRecord(String oid,String table){

        String selectQuery = "Select * from "+ table +" where oid='"+oid+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        if (!c.moveToFirst()) {
            return null;
        }
        return c;
    }



    public Cursor getOneStructure(String oid){

        String selectQuery = "Select * from Structure where oid='"+oid+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        if (!c.moveToFirst()) {
            return null;
        }
        return c;
    }



    public Cursor getOneIndividual(String oid){

        String selectQuery = "Select * from Individual where oid='"+oid+"'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        if (!c.moveToFirst()) {
            return null;
        }
        return c;
    }

    ///=======================Residence details==============================



    ///=======================Household registration Book details==============================

    public int getHRBCount(){

        // Select All Query
        String selectQuery = "SELECT HouseholdRegistrationBook.hrb_Name hrb ,case when rooms >0 then rooms else 0 end rooms" +
                ",Staff.stf_FirstName staff ,hrb_createdAt date,HouseholdRegistrationBook.oid from HouseholdRegistrationBook \n" +
                "INNER JOIN Staff on Staff.Oid=HouseholdRegistrationBook.hrb_createdby \n" +
                "LEFT JOIN (select count(rm_roomID)rooms ,h.hrb_Name,h.oid id from room r \n" +
                "INNER JOIN Structure s on s.oid=r.rm_structure\n" +
                "INNER JOIN HouseholdRegistrationBook h on h.oid=s.str_hrb GROUP BY h.hrb_Name)  ON id=HouseholdRegistrationBook.oid" +
                "  limit 100";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return Count;
    }

    public List<List<Cell>> getAllHRBs(int startIndex){

        List<List<Cell>> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT HouseholdRegistrationBook.hrb_Name hrb ,case when rooms >0 then rooms else 0 end rooms" +
                ",Staff.stf_FirstName staff ,hrb_createdAt date,HouseholdRegistrationBook.oid from HouseholdRegistrationBook \n" +
                "INNER JOIN Staff on Staff.Oid=HouseholdRegistrationBook.hrb_createdby \n" +
                "LEFT JOIN (select count(rm_roomID)rooms ,h.hrb_Name,h.oid id from room r \n" +
                "INNER JOIN Structure s on s.oid=r.rm_structure\n" +
                "INNER JOIN HouseholdRegistrationBook h on h.oid=s.str_hrb GROUP BY h.hrb_Name) " +
                " ON id=HouseholdRegistrationBook.oid limit 10";

             /*   "select hrb_Name,Staff.stf_FirstName,hrb_createdAt,HouseholdRegistrationBook.oid " +
                "from HouseholdRegistrationBook " +
                "INNER JOIN Staff on Staff.Oid=HouseholdRegistrationBook.hrb_createdby " +
                " limit 100";*/
        //"Order by HouseholdRegistrationBook.Name limit 100";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        GlobalClass.ROW_SIZE=cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                List<Cell> cellList = new ArrayList<>();
                list.add(cellList);
                for (int j = 0; j < 5; j++) {
                    String text = cursor.getString(j);
                    String id = cursor.getString(1);
                    Cell cell = new Cell(id, text);
                    cellList.add(cell);
                }
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }

    public List<String> getAllHrbs(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        String selectQuery = "SELECT  oid,hrb_Name FROM  HouseholdRegistrationBook";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }



    ///=======================Get Room ID==============================

    public String getHRBID( String  hrb){

        // Select All Query
        String selectQuery = "SELECT oid FROM  HouseholdRegistrationBook where hrb_Name ='"+ hrb + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (null != cursor) {
            cursor.moveToFirst();
            GlobalClass.hrbID = cursor.getString(0);
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.hrbID;
    }

    public String getHRB(String  hrb){

        // Select All Query
        String selectQuery = "SELECT hrb_Name FROM  HouseholdRegistrationBook where oid ='"+ hrb + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (null != cursor) {
            cursor.moveToFirst();
            GlobalClass.hrbNAME = cursor.getString(0);
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.hrbNAME;
    }


    ///=======================Round details==============================

    public int getRoundCount(){

        // Select All Query
        String selectQuery = "SELECT  * FROM  Round";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        int Count=cursor.getCount();

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return Count;
    }

    public List<List<Cell>> getAllRounds(int startIndex){

        List<List<Cell>> list = new ArrayList<>();
        // Select All Query
        String selectQuery = "\n" +
                "select rnd_roundNumber,rnd_startDate,rnd_endDate,rnd_createdAt,oid from Round";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments
        GlobalClass.ROW_SIZE=cursor.getCount();

        if (cursor.moveToFirst()) {
            do {
                List<Cell> cellList = new ArrayList<>();
                list.add(cellList);
                for (int j = 0; j < 5; j++) {
                    String text = cursor.getString(j);
                    String id = cursor.getString(1);
                    Cell cell = new Cell(id, text);
                    cellList.add(cell);
                }
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return list;
    }


    ///=======================Get Staff ID==============================

    public Integer getRound( String  round){

        // Select All Query
        String selectQuery = "SELECT rnd_roundNumber FROM Round where oid ='"+ round + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (null != cursor) {
            cursor.moveToFirst();
            GlobalClass.roundNumber = cursor.getInt(0);
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.roundNumber;
    }





    ///=======================Get Staff ID==============================

    public String getStaff( String  staff){

        // Select All Query
        String selectQuery = "SELECT stf_FirstName FROM  Staff where oid ='"+ staff + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (null != cursor) {
            cursor.moveToFirst();
            GlobalClass.stfName = cursor.getString(0);
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.stfName;
    }


    ///=======================Get Observation ID==============================

    public String getObservationID( String  obs){

        // Select All Query
        String selectQuery = "SELECT oid FROM  Observation where obs_observationID ='"+ obs + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (null != cursor) {
            cursor.moveToFirst();
            GlobalClass.obsID = cursor.getString(0);
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.obsID;
    }

    ///=======================Get Observation ID==============================

    public String getObservationOBS( String  room){

        // Select All Query
        String selectQuery = "SELECT obs_observationID FROM  Observation where obs_observationID  like '"+ room + "%'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (cursor.getCount()>0 ){
            cursor.moveToFirst();
            GlobalClass.observationID = cursor.getString(0);
        }

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.observationID;
    }




    ///=======================Get Room ID==============================

    public String getRoomID( String  room){

        // Select All Query
        String selectQuery = "SELECT oid FROM  Room where rm_roomID ='"+ room + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (null != cursor) {
            cursor.moveToFirst();
            GlobalClass.rooID = cursor.getString(0);
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.rooID;
    }



    ///=======================Get Structure ID==============================

    public String getStructureID( String  str){

        // Select All Query
        String selectQuery = "SELECT oid FROM  Structure where str_structureID ='"+ str + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (null != cursor) {
            cursor.moveToFirst();
            GlobalClass.strID = cursor.getString(0);
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.strID;
    }

    public String getStructure( String  str){

        // Select All Query
        String selectQuery = "SELECT str_structureID FROM  Structure where oid ='"+ str + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (null != cursor) {
            cursor.moveToFirst();
            GlobalClass.strNum = cursor.getString(0);
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.strNum;
    }




    public String getRoom( String  room){

        // Select All Query
        String selectQuery = "SELECT rm_roomID FROM  Room where oid ='"+ room + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (null != cursor) {
            cursor.moveToFirst();
            GlobalClass.roomID = cursor.getString(0);
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.roomID;
    }

    ///=======================Get Observation ID==============================

    public String getIndividualID( String  ind){

        // Select All Query
        String selectQuery = "SELECT oid,ind_name FROM  Individual where ind_individualID ='"+ ind + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

      if (cursor.getCount()>0){
            cursor.moveToFirst();
            GlobalClass.indID = cursor.getString(0);
            GlobalClass.IndividualName = cursor.getString(1);
        }else{
          GlobalClass.IndividualName ="";
      }

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.indID;
    }

    ///=======================Get Observation ID==============================

    public String getSocialgroupID( String  sgp){

        // Select All Query
        String selectQuery = "SELECT oid FROM  Socialgroup where sgp_socialgroupID ='"+ sgp + "'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (cursor.getCount()>0){
            cursor.moveToFirst();
            GlobalClass.sgpID = cursor.getString(0);
        }else{

        }

        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.sgpID;
    }

    ///=======================Get Observation ID==============================

    public Integer getRoundNumber(){

        // Select All Query
        String selectQuery = "SELECT rnd_roundNumber FROM  Round Order by rnd_roundNumber desc limit 1";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (null != cursor) {
            cursor.moveToFirst();
            GlobalClass.roundNumber = Integer.parseInt(cursor.getString(0).toString());
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.roundNumber;
    }



    ///=======================Get Observation ID==============================

    public String getRoundID( int  rnd){

        // Select All Query
        String selectQuery = "SELECT oid FROM  Round where rnd_roundNumber ="+ rnd + "";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (null != cursor) {
            cursor.moveToFirst();
            GlobalClass.roundID = cursor.getString(0);
        }
        // closing connection
        cursor.close();
        db.close();
        // returning lables
        return GlobalClass.roundID;
    }


    //+++++++++++++++++++++++++++++++++++++++Delete records+++++++++++++++++++++

    public void deleterecord(String Table, String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+Table+" where oid='"+id+"'");
    }
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public boolean recordpgoexits(String Ind, String Obs, String table,String ColIND,String ColOBS){

        String selectQuery="SELECT * FROM " + table + " WHERE "+ColIND+"='"+Ind+"' AND "+ColOBS+"='"+Obs+"'";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor mCursor = db.rawQuery(selectQuery,null);

        int Count=mCursor.getCount();

        if (Count==0){
            return false;
        }else{
            return true;
        }


    }


    //+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

    public  HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        // Select All Query
        String selectQuery = "SELECT rm_roomID FROM  Room r " +
                "INNER JOIN Structure s on s.oid=r.rm_Structure " +
                "INNER JOIN HouseholdRegistrationBook h on h.oid=s.str_hrb " +
                "INNER JOIN WorkAllocation w on w.hrb=h.oid " +
                "WHERE W.active=1 and W.completed=0 " +
                "and w.FieldWorker='"+GlobalClass.stfID+"' " +
                "ORDER BY rm_roomID";
                //"SELECT rm_roomID FROM  Room limit 20";

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        if (cursor.moveToFirst()) {
            do {
                String RoomID = cursor.getString(0);
                List<String> cricket = new ArrayList<String>();

                String selectQuery1 = "SELECT distinct Socialgroup.sgp_socialgroupID FROM Room " +
                        "LEFT JOIN Residency ON Residency.res_room=Room.oid " +
                        "LEFT JOIN Membership ON Membership.mem_individual= Residency.res_individual " +
                        "LEFT JOIN Socialgroup ON Socialgroup.oid= Membership.mem_socialgroup " +
                        "WHERE Membership.mem_EndObservation is null AND Room.rm_roomID='"+RoomID+"'";

                Cursor cursor1 = db.rawQuery(selectQuery1, null);//selectQuery,selectedArguments

                if (cursor1.moveToFirst()) {

                    do {

                        String RoomID2 = cursor1.getString(0);
                        if (!(RoomID2==null)) {
                            cricket.add(RoomID2);
                        }
                    } while (cursor1.moveToNext());

                    expandableListDetail.put(RoomID, cricket);
                }
            }
                while (cursor.moveToNext()) ;

        }

        // closing connection
        cursor.close();
        db.close();
        return expandableListDetail;
    }

}
