package org.aphrc.myapplication.Utilities;

import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import org.aphrc.myapplication.Database.DataBaseHelper;
import org.aphrc.myapplication.Model.GlobalClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;


public class Calculate {

    private Integer sID=0;
    DataBaseHelper databaseHelper;
    private Context mContext;

    public final ArrayList<String> mCounty = new ArrayList<>();
    public final ArrayList<String> mSubCounty = new ArrayList<>();

    public final ArrayList<String> mSite = new ArrayList<>();
    public final ArrayList<String> mEA = new ArrayList<>();

    public Calculate(Context mContext) {
        this.mContext = mContext;
    }

    public static String CalculateRealDate(String idate) {
        //to calculate real year
        String dd, mm, yyyy;
        //date

        if (idate.substring(0, 2).equals("97")|idate.substring(0, 2).equals("98")|idate.substring(0, 2).equals("99")){
            dd = "15";
        } else{
            dd=idate.substring(0, 2).toString();
        }

        //months
        if (idate.substring(3, 5).equals("97")|idate.substring(3, 5).equals("98")|idate.substring(3, 5).equals("99")){
            mm = "07";
        } else{
            mm=idate.substring(3, 5).toString();
        }

        //year

        if (idate.substring(6, 10).equals("9997")|idate.substring(6, 10).equals("9998")|idate.substring(6, 10).equals("9997")){
            yyyy = "2200";
        } else{
            yyyy=idate.substring(6, 10).toString();
        }


        String calc_date = yyyy + "-" + mm + "-" + dd;

        return calc_date;

    }

    public static String getAge( String dateCreated, String dateOfBirth){

        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        int year, month, day, year1, month1, day1;
        year = Integer.parseInt(dateOfBirth.substring(0, 4));
        month = Integer.parseInt(dateOfBirth.substring(5, 7));
        day = Integer.parseInt(dateOfBirth.substring(8, 10));

        year1 = Integer.parseInt(dateCreated.substring(0, 4));
        month1 = Integer.parseInt(dateCreated.substring(5, 7));
        day1 = Integer.parseInt(dateCreated.substring(8, 10));


        dob.set(year, month, day);
        today.set(year1, month1, day1);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);

       String ageS = ageInt.toString();

        return ageS;
    }

    public static String isDateValid (String idate) {
        //to calculate real year
        String dd, mm, yyyy;
        //date

        if (((idate.substring(0, 2).equals("97")|idate.substring(0, 2).equals("98")|idate.substring(0, 2).equals("99")))  & ((Integer.parseInt(idate.substring(0, 2))>=0 &(Integer.parseInt(idate.substring(0, 2))<=31)))) {
            dd = "15";
        } else{
            dd=idate.substring(0, 2).toString();
        }

        //months
        if (idate.substring(3, 5).equals("97")|idate.substring(3, 5).equals("98")|idate.substring(3, 5).equals("99")){
            mm = "07";
        } else{
            mm=idate.substring(3, 5).toString();
        }

        //year

        if (idate.substring(6, 10).equals("9997")|idate.substring(6, 10).equals("9998")|idate.substring(6, 10).equals("9997")){
            yyyy = "2200";
        } else{
            yyyy=idate.substring(6, 10).toString();
        }


        String calc_date = yyyy + "-" + mm + "-" + dd;

        return calc_date;

    }


    public static long getDays(Date dateCreated, Date dateOfBirth){

        long ageInt= (dateOfBirth.getTime()- dateCreated.getTime());

        ageInt=(ageInt/(1000*60*60*24));

        //Integer ageS = ageInt.toString();

        return ageInt;
    }

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
            String dateStr = "1900-01-01";
            Date d = dateFormat.parse(dateStr);
            if (dateFormat.parse(inDate.trim()).before(d)) {
                return false;
            }

        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static boolean isValidInmigrationDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
            String dateStr = "2002-08-01";
            Date d1 = dateFormat.parse(inDate.trim());
            Date d = dateFormat.parse(dateStr);

            if (d1.before(d)) {
                return false;
            }

        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static boolean isValidDateToDay(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
            Date d1 = dateFormat.parse(inDate.trim());
            Date d = new Date();

            if (d1.after(d)) {
                return false;
            }

        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static boolean isValidDateBeforeToDay(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
            Date d1 = dateFormat.parse(inDate.trim());
            Date d = new Date();

            if (d.after(d1)) {
                return false;
            }

        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static boolean isValidOutDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {

            dateFormat.parse(inDate.trim());
            Date d1 = dateFormat.parse(inDate.trim());
            Date d = GlobalClass.StartEventDate;

            if (!(GlobalClass.StartEventDate == null)){
                if (d1.before(d)) {
                    return false;
                }
        }

        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static boolean isValidDate(String date1,String date2) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //dateFormat.setLenient(false);
        try {

           // dateFormat.parse(inDate.trim());

            Date Date1 = dateFormat.parse(date1.trim());
            Date Date2 = dateFormat.parse(date2.trim());

            if (Date2.before(Date1)) {
                return false;
            }

        } catch (ParseException pe) {
            return false;
        }
        return true;
    }


    public void GenSocialgroupID( String room) {

        try {

             databaseHelper= new DataBaseHelper(this.mContext);
            Cursor c= databaseHelper.getSocialgroups(room);

            if (c.getCount()>=1) {
                while (c.moveToNext()) {
                    sID = Integer.parseInt(c.getString(0).substring(10, 12));

                    sID = sID + 1;

                    if (sID <= 9) {
                        GlobalClass.socialgroupID = room + "0" + sID;
                    } else {
                        GlobalClass.socialgroupID = room + sID;
                    }
                }
            } else{
                GlobalClass.socialgroupID = room + "00";
            }
        } catch (Exception e) {
         //   Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void GenIndividualID( String SocialgroupID) {

        databaseHelper= new DataBaseHelper(this.mContext);
        Cursor c= databaseHelper.getIndividuals(SocialgroupID);
        //Cursor c = databaseHelper.getIndividuals("G00100100100");

            if (c.getCount()>=1) {
                while (c.moveToNext()) {
                    sID = Integer.parseInt(c.getString(0).substring(12, 14));

                    sID = sID + 1;

                    if (sID <= 9) {
                        GlobalClass.IndividualID = SocialgroupID + "0" + sID;
                    } else {
                        GlobalClass.IndividualID = SocialgroupID + sID;
                    }
                }
            } else{
                GlobalClass.IndividualID = SocialgroupID + "01";
            }

    }


    public int calculateAgeyear(Date birthDate, Date currentDate) {
        // validate inputs ...
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    int d1 = Integer.parseInt(formatter.format(birthDate));
    int d2 = Integer.parseInt(formatter.format(currentDate));
    int age = (d2 - d1) / 10000;
    return age;
    }


    public int calculateAgemonth(Date birthDate, Date currentDate) {
        // validate inputs ...
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(currentDate));
        int age = (d2 - d1) / 10000;
        return age;
    }

    public int calculateAgedays(Date birthDate, Date currentDate) {
        // validate inputs ...
        DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        int d1 = Integer.parseInt(formatter.format(birthDate));
        int d2 = Integer.parseInt(formatter.format(currentDate));
        int age = (d2 - d1) / (60*1000);
        return age;
    }


    public void loadCountyData() {
        mCounty.add("TAITA-TAVETA");
        mCounty.add("KWALE");
        mCounty.add("MOMBASA");
        mCounty.add("KILIFI");
        mCounty.add("TANA RIVER");
        mCounty.add("LAMU");
        mCounty.add("NYANDARUA");
        mCounty.add("NYERI");
        mCounty.add("KIRINYAGA");
        mCounty.add("MURANGA");
        mCounty.add("KIAMBU");
        mCounty.add("MACHAKOS");
        mCounty.add("KITUI");
        mCounty.add("EMBU");
        mCounty.add("MERU");
        mCounty.add("MARSABIT");
        mCounty.add("ISIOLO");
        mCounty.add("MAKUENI");
        mCounty.add("THARAKA");
        mCounty.add("NAIROBI");
        mCounty.add("TURKANA");
        mCounty.add("SAMBURU");
        mCounty.add("TRANS-NZOIA");
        mCounty.add("WEST POKOT");
        mCounty.add("BOMET");
        mCounty.add("UASIN GISHU");
        mCounty.add("NAKURU");
        mCounty.add("KERICHO");
        mCounty.add("NANDI");
        mCounty.add("LAIKIPIA");
        mCounty.add("KAJIADO");
        mCounty.add("NAROK");
        mCounty.add("BARINGO");
        mCounty.add("ELGEYO MARAKWET");
        mCounty.add("BUSIA");
        mCounty.add("BUNGOMA");
        mCounty.add("KAKAMEGA");
        mCounty.add("VIHIGA");
        mCounty.add("KISUMU");
        mCounty.add("KISII");
        mCounty.add("HOMA BAY");
        mCounty.add("SIAYA");
        mCounty.add("NYAMIRA");
        mCounty.add("MIGORI");
        mCounty.add("GARISSA");
        mCounty.add("WAJIR");
        mCounty.add("MANDERA");
        mCounty.add("OUTSIDE KENYA");


    }


    public void loadSubCountyData(String County) {
        if (County.equals("TAITA-TAVETA")) {

            mSubCounty.add("TAITA (WUNDANYI)");
            mSubCounty.add("TAVETA");
            mSubCounty.add("MWATATE");
            mSubCounty.add("VOI");

        } else if (County.equals("KWALE")) {

            mSubCounty.add("KWALE");
            mSubCounty.add("KINANGO");
            mSubCounty.add("MSAMBWENI");

        } else if (County.equals("MOMBASA")) {

            mSubCounty.add("MVITA");
            mSubCounty.add("LIKONI");
            mSubCounty.add("CHANGAMWE");
            mSubCounty.add("KISAUNI");
        } else if (County.equals("KILIFI")) {
            mSubCounty.add("KILIFI");
            mSubCounty.add("MALINDI");
            mSubCounty.add("KALOLENI");
            mSubCounty.add("GANZE");
            mSubCounty.add("MAGARINI");
            mSubCounty.add("RABAI");
        }else if (County.equals("TANA RIVER")) {
            mSubCounty.add("TANA RIVER");
            mSubCounty.add("TANA DELTA");
            mSubCounty.add("TANA NORTH");
        }else if (County.equals("LAMU")) {
            mSubCounty.add("LAMU WEST");
            mSubCounty.add("LAMU EAST");
        }



        else if (County.equals("NYANDARUA")) {
            mSubCounty.add("NYANDARUA NORTH");
            mSubCounty.add("NYANDARUA SOUTH");
            mSubCounty.add("NYANDARUA CENTRAL");
            mSubCounty.add("NYANDARUA WEST");
            mSubCounty.add("KIPIPIRI");
            mSubCounty.add("KINANGOP");
            mSubCounty.add("MIRANGINGE");
        }

        else if (County.equals("NYERI")) {
            mSubCounty.add("NYERI CENTRAL");
            mSubCounty.add("KIENI EAST");
            mSubCounty.add("MATHIRA EAST");
            mSubCounty.add("MATHIRA WEST");
            mSubCounty.add("MUKURWEINI");
            mSubCounty.add("KIENI WEST");
            mSubCounty.add("TETU");
            mSubCounty.add("NYERI SOUTH");
        }
        else if (County.equals("KIRINYAGA")) {
            mSubCounty.add("KIRINYAGA CENTRAL");
            mSubCounty.add("KIRINYAGA EAST");
            mSubCounty.add("KIRINYAGA WEST");
            mSubCounty.add("MWEA EAST");
            mSubCounty.add("MWEA WEST");

        }


        else if (County.equals("MURANGA")) {
            mSubCounty.add("MURANGA EAST");
            mSubCounty.add("MURANGA SOUTH");
            mSubCounty.add("KANDARA");
            mSubCounty.add("KIGUMO");
            mSubCounty.add("MATHIOYA");
            mSubCounty.add("KANGEMA");
            mSubCounty.add("GATANGA");
            mSubCounty.add("KAHURO");
        }

        else if (County.equals("KIAMBU")) {
            mSubCounty.add("KIAMBU");
            mSubCounty.add("THIKA WEST");
            mSubCounty.add("LIMURU");
            mSubCounty.add("GATUNDU");
            mSubCounty.add("KIKUYU");
            mSubCounty.add("LARI");
            mSubCounty.add("GITHUNGURI");
            mSubCounty.add("THIKA EAST");
            mSubCounty.add("RUIRU");
            mSubCounty.add("GATUNDU SOUTH");
        }


        else if (County.equals("MACHAKOS")) {
            mSubCounty.add("MACHAKOS");
            mSubCounty.add("MWALA");
            mSubCounty.add("YATTA");
            mSubCounty.add("KANGUNDO");
            mSubCounty.add("KATHIANI");
            mSubCounty.add("ATHI RIVER");
            mSubCounty.add("GITHUNGURI");
            mSubCounty.add("MASINGA");
            mSubCounty.add("MATUNGULU");

        }
        else if (County.equals("KITUI")) {
            mSubCounty.add("KITUI CENTRAL");
            mSubCounty.add("MWINGI CENTRAL");
            mSubCounty.add("MUTOMO");
            mSubCounty.add("KYUSO");
            mSubCounty.add("KITUI WEST");
            mSubCounty.add("MUTITU");
            mSubCounty.add("MWINGI EAST");
            mSubCounty.add("MWINGI WEST");
            mSubCounty.add("LOWER YATTA");
            mSubCounty.add("MUMONI");
            mSubCounty.add("NZAMBANI");
            mSubCounty.add("TSEIKURU");
            mSubCounty.add("KATULANI");
            mSubCounty.add("KISASI");
            mSubCounty.add("IKUTHA");
            mSubCounty.add("MATINYANI");
        }


        else if (County.equals("EMBU")) {
            mSubCounty.add("EMBU WEST");
            mSubCounty.add("MBEERE NORTH");
            mSubCounty.add("EMBU EAST");
            mSubCounty.add("MBEERE SOUTH");
            mSubCounty.add("EMBU NORTH");

        }


        else if (County.equals("MERU")) {
            mSubCounty.add("IMENTI NORTH");
            mSubCounty.add("IGEMBE SOUTH");
            mSubCounty.add("MERU CENTRAL");
            mSubCounty.add("IMENTI SOUTH");
            mSubCounty.add("TIGANIA WEST");
            mSubCounty.add("BUURI");
            mSubCounty.add("IGEMBE NORTH");
            mSubCounty.add("TIGANIA EAST");
        }


        else if (County.equals("MARSABIT")) {
            mSubCounty.add("MARSABIT");
            mSubCounty.add("MOYALE");
            mSubCounty.add("CHALBI");
            mSubCounty.add("LAISAMIS");
            mSubCounty.add("SOLOLO");
            mSubCounty.add("NORTH HORR");
            mSubCounty.add("LOIYANGALANI");
        }


        else if (County.equals("ISIOLO")) {
            mSubCounty.add("GARBATULA");
            mSubCounty.add("MERTI");
            mSubCounty.add("ISIOLO");

        }

        else if (County.equals("MAKUENI")) {
            mSubCounty.add("MAKUENI ");
            mSubCounty.add("MBOONI WEST");
            mSubCounty.add("NZAUNI");
            mSubCounty.add("KIBWEZI");
            mSubCounty.add("KATHONZWENI");
            mSubCounty.add("KILUNGU");
            mSubCounty.add("MBOONI EAST");
            mSubCounty.add("MUKAA");
            mSubCounty.add("MAKINDU");
        }

        else if (County.equals("THARAKA")) {
            mSubCounty.add("MERU SOUTH ");
            mSubCounty.add("THARAKA SOUTH");
            mSubCounty.add("MAARA");
            mSubCounty.add("THARAKA NORTH");

        }

        else if (County.equals("NAIROBI")) {
            mSubCounty.add("MAKADARA ");
            mSubCounty.add("KAMUKUNJI");
            mSubCounty.add("STAREHE");
            mSubCounty.add("LANGATA");
            mSubCounty.add("DAGORETTI");
            mSubCounty.add("WESTLANDS");
            mSubCounty.add("KASARANI");
            mSubCounty.add("EMBAKASI");
            mSubCounty.add("NJIRU");
        }

        else if (County.equals("TURKANA")) {
            mSubCounty.add("TURKANA CENTRAL ");
            mSubCounty.add("TURKANA NORTH");
            mSubCounty.add("TURKANA SOUTH");
            mSubCounty.add("TURKANA EAST");
            mSubCounty.add("TURKANA WEST");
            mSubCounty.add("LOIMA");
        }

        else if (County.equals("SAMBURU")) {
            mSubCounty.add("SAMBURU CENTRAL ");
            mSubCounty.add("SAMBURU EAST");
            mSubCounty.add("SAMBURU NORTH");
        }
        else if (County.equals("TRANS-NZOIA")) {
            mSubCounty.add("TRANS-NZOIA WEST");
            mSubCounty.add("TRANS-NZOIA EAST");
            mSubCounty.add("KWANZA");
        }
        else if (County.equals("WEST POKOT")) {
            mSubCounty.add("WEST POKOT");
            mSubCounty.add("NORTH POKOT");
            mSubCounty.add("POKOT CENTRAL");
            mSubCounty.add("POKOT SOUTH");
        }
        else if (County.equals("BOMET")) {
            mSubCounty.add("BOMET");
            mSubCounty.add("SOTIK");
            mSubCounty.add("CHEPALUNGU");
            mSubCounty.add("KONOIN");
        }
        else if (County.equals("UASIN GISHU")) {
            mSubCounty.add("ELDORET WEST");
            mSubCounty.add("ELDORET EAST");
            mSubCounty.add("WARENG");
        }
        else if (County.equals("NAKURU")) {
            mSubCounty.add("NAKURU");
            mSubCounty.add("NAKURU NORTH");
            mSubCounty.add("NAIVASHA");
            mSubCounty.add("MOLO");
            mSubCounty.add("RONGAI");
            mSubCounty.add("NJORO");
            mSubCounty.add("KURESOI");
            mSubCounty.add("SUBUKIA");
            mSubCounty.add("GILGIL");
        }
        else if (County.equals("KERICHO")) {
            mSubCounty.add("KERICHO ");
            mSubCounty.add("BURETI");
            mSubCounty.add("KIPKELION");
            mSubCounty.add("BELGUT");
            mSubCounty.add("LONDIANI");
        }
        else if (County.equals("NANDI")) {
            mSubCounty.add("NANDI CENTRAL");
            mSubCounty.add("NANDI SOUTH");
            mSubCounty.add("NANDI NORTH");
            mSubCounty.add("NANDI EAST");
            mSubCounty.add("TINDERET");
        }
        else if (County.equals("LAIKIPIA")) {
            mSubCounty.add("LAIKIPIA EAST");
            mSubCounty.add("LAIKIPIA NORTH");
            mSubCounty.add("LAIKIPIA WEST");
            mSubCounty.add("LAIKIPIA CENTRAL");
            mSubCounty.add("NYAHURURU");
        }
        else if (County.equals("KAJIADO")) {
            mSubCounty.add("KAJIADO CENTRAL");
            mSubCounty.add("LOITOKITOK");
            mSubCounty.add("KAJIADO NORTH");
            mSubCounty.add("MASHURU");
            mSubCounty.add("ISINYA");
        }
        else if (County.equals("NAROK")) {
            mSubCounty.add("NAROK NORTH");
            mSubCounty.add("TRANSMARA WEST");
            mSubCounty.add("NAROK SOUTH");
            mSubCounty.add("TRANSMARA EAST");
        }
        else if (County.equals("BARINGO")) {
            mSubCounty.add("BARINGO CENTRAL");
            mSubCounty.add("KOIBATEK");
            mSubCounty.add("EAST POKOT");
            mSubCounty.add("BARINGO NORTH");
            mSubCounty.add("MARIGAT");
            mSubCounty.add("MGOTIO");
        }
        else if (County.equals("ELGEYO MARAKWET")) {
            mSubCounty.add("KEIYO");
            mSubCounty.add("MARAKWET WEST");
            mSubCounty.add("KEIYO SOUTH");
            mSubCounty.add("MARAKWET EAST");
        }
        else if (County.equals("BUSIA")) {
            mSubCounty.add("BUSIA");
            mSubCounty.add("TESO NORTH");
            mSubCounty.add("SAMIA");
            mSubCounty.add("BUNYALA");
            mSubCounty.add("TESO SOUTH");
            mSubCounty.add("BUTULA");
            mSubCounty.add("NAMBALE");
        }
        else if (County.equals("BUNGOMA")) {
            mSubCounty.add("BUNGOMA SOUTH");
            mSubCounty.add("MT ELGON");
            mSubCounty.add("BUNGOMA NORTH");
            mSubCounty.add("BUNGOMA CENTRAL");
            mSubCounty.add("BUNGOMA EAST");
            mSubCounty.add("KIMILILI");
            mSubCounty.add("BUNGOMA WEST");
            mSubCounty.add("BUMULA");
            mSubCounty.add("CHEPTAIS");
        }
        else if (County.equals("KAKAMEGA")) {
            mSubCounty.add("KAKAMEGA CENTRAL");
            mSubCounty.add("LUGARI");
            mSubCounty.add("BUTERE");
            mSubCounty.add("KAKAMEGA NORTH");
            mSubCounty.add("KAKAMEGA SOUTH");
            mSubCounty.add("KAKAMEGA EAST");
            mSubCounty.add("MUMIAS");
            mSubCounty.add("MATETE");
            mSubCounty.add("KHWISERO");
            mSubCounty.add("MATUNGU");
            mSubCounty.add("LIKUYANI");
            mSubCounty.add("NAVAKHOLO");
        }
        else if (County.equals("VIHIGA")) {
            mSubCounty.add("VIHIGA");
            mSubCounty.add("EMUHAYA");
            mSubCounty.add("HAMISI");
            mSubCounty.add("SABATIA");
        }
        else if (County.equals("KISUMU")) {
            mSubCounty.add("KISUMU EAST");
            mSubCounty.add("NYANDO");
            mSubCounty.add("KISUMU WEST");
            mSubCounty.add("NYAKACH");
            mSubCounty.add("MUHORONI");
            mSubCounty.add("KISUMU NORTH");
        }

        else if (County.equals("KISII")) {
            mSubCounty.add("KISII CENTRAL");
            mSubCounty.add("GUCHA");
            mSubCounty.add("KISII SOUTH");
            mSubCounty.add("GUCHA SOUTH");
            mSubCounty.add("MARANI");
            mSubCounty.add("KENYENYA");
            mSubCounty.add("NYAMACHE");
            mSubCounty.add("MASABA SOUTH");
            mSubCounty.add("SAMETA");
        }

        else if (County.equals("HOMA BAY")) {
            mSubCounty.add("HOMA BAY");
            mSubCounty.add("SUBA");
            mSubCounty.add("RACHUONYO SOUTH");
            mSubCounty.add("NDHIWA");
            mSubCounty.add("MBITA");
            mSubCounty.add("RACHUONYO NORTH");
        }

        else if (County.equals("SIAYA")) {
            mSubCounty.add("SIAYA ");
            mSubCounty.add("BONDO");
            mSubCounty.add("RARIEDA");
            mSubCounty.add("UGENYA");
            mSubCounty.add("GEM");
            mSubCounty.add("UGUNJA");
        }

        else if (County.equals("NYAMIRA")) {
            mSubCounty.add("NYAMIRA");
            mSubCounty.add("MASABA NORTH");
            mSubCounty.add("BORABU");
            mSubCounty.add("MANGA");
            mSubCounty.add("NYAMIRA NORTH");
        }

        else if (County.equals("MIGORI")) {
            mSubCounty.add("MIGORI");
            mSubCounty.add("KURIA WEST");
            mSubCounty.add("RONGO");
            mSubCounty.add("KURIA EAST");
            mSubCounty.add("NYATIKE");
            mSubCounty.add("URIRI");
            mSubCounty.add("AWENDO");
        }

        else if (County.equals("GARISSA")) {
            mSubCounty.add("GARISSA");
            mSubCounty.add("IJARA");
            mSubCounty.add("YLAGDERA");
            mSubCounty.add("FAFI");
            mSubCounty.add("DAADAB");
            mSubCounty.add("BALAMBALA");
            mSubCounty.add("HULUGHO");
        }

        else if (County.equals("WAJIR")) {
            mSubCounty.add("WAJIR EAST");
            mSubCounty.add("WAJIR NORTH");
            mSubCounty.add("WAJIR WEST");
            mSubCounty.add("WAJIR SOUTH");
            mSubCounty.add("ELDAS");
            mSubCounty.add("TARBAJI");
            mSubCounty.add("BUNA");
            mSubCounty.add("HABASWEIN");
        }

        else if (County.equals("MANDERA")) {
            mSubCounty.add("MANDERA EAST");
            mSubCounty.add("MANDERA WEST");
            mSubCounty.add("MANDERA CENTRAL");
            mSubCounty.add("MANDERA NORTH");
            mSubCounty.add("LAFEY");
            mSubCounty.add("BANISA*");
        }

        else if (County.equals("OUTSIDE KENYA")) {
            mSubCounty.add("OUTSIDE KENYA");

        }

        else{


        }
    }


    public void loadSite() {
        mSite.add("Korogocho");
        mSite.add("Viwandani");
    }


    public void loadea(String ea) {
        if (ea.equals("Korogocho")|ea.equals("1")) {

            mEA.add("G001");
            mEA.add("G002");
            mEA.add("G003");
            mEA.add("G004");
            mEA.add("G005");
            mEA.add("G006");
            mEA.add("G007");
            mEA.add("G008");
            mEA.add("G009");
            mEA.add("G010");
            mEA.add("G011");
            mEA.add("G012");
            mEA.add("G013");
            mEA.add("G014");
            mEA.add("G015");
            mEA.add("G016");
            mEA.add("G017");
            mEA.add("G018");
            mEA.add("G019");
            mEA.add("G020");
            mEA.add("G021");
            mEA.add("G022");
            mEA.add("G023");
            mEA.add("G024");
            mEA.add("G025");
            mEA.add("G026");
            mEA.add("G027");
            mEA.add("G028");
            mEA.add("G029");
            mEA.add("G030");
            mEA.add("G031");
            mEA.add("G032");
            mEA.add("G033");
            mEA.add("G034");
            mEA.add("G035");
            mEA.add("G036");
            mEA.add("G037");
            mEA.add("G038");
            mEA.add("G039");
            mEA.add("G040");
            mEA.add("G041");
            mEA.add("G042");
            mEA.add("G043");
            mEA.add("G044");
            mEA.add("G045");
            mEA.add("G046");
            mEA.add("G047");
            mEA.add("G048");
            mEA.add("G049");
            mEA.add("G050");
            mEA.add("G051");
            mEA.add("G052");
            mEA.add("G053");
            mEA.add("G054");
            mEA.add("G055");
            mEA.add("G056");
            mEA.add("G057");
            mEA.add("G058");
            mEA.add("G059");
            mEA.add("G060");
            mEA.add("G061");

            mEA.add("Y001");
            mEA.add("Y002");
            mEA.add("Y003");
            mEA.add("Y004");
            mEA.add("Y005");
            mEA.add("Y006");
            mEA.add("Y007");
            mEA.add("Y008");
            mEA.add("Y009");
            mEA.add("Y010");
            mEA.add("Y011");
            mEA.add("Y012");
            mEA.add("Y013");
            mEA.add("Y014");
            mEA.add("Y015");
            mEA.add("Y016");
            mEA.add("Y017");
            mEA.add("Y018");
            mEA.add("Y019");
            mEA.add("Y020");
            mEA.add("Y021");
            mEA.add("Y022");
            mEA.add("Y023");
            mEA.add("Y024");
            mEA.add("Y025");
            mEA.add("Y026");
            mEA.add("Y027");
            mEA.add("Y028");
            mEA.add("Y029");
            mEA.add("Y030");
            mEA.add("Y031");
            mEA.add("Y032");
            mEA.add("Y033");
            mEA.add("Y034");
            mEA.add("Y035");
            mEA.add("Y036");
            mEA.add("Y037");
            mEA.add("Y038");
            mEA.add("Y039");
            mEA.add("Y040");
            mEA.add("Y041");
            mEA.add("Y042");
            mEA.add("Y043");
            mEA.add("Y044");
            mEA.add("Y045");
            mEA.add("Y046");
            mEA.add("Y047");
            mEA.add("Y048");
            mEA.add("Y049");
            mEA.add("Y050");
            mEA.add("Y051");
            mEA.add("Y052");
            mEA.add("Y053");
            mEA.add("Y054");
            mEA.add("Y055");
            mEA.add("Y056");
            mEA.add("Y057");
            mEA.add("Y058");
            mEA.add("Y059");
            mEA.add("Y060");
            mEA.add("Y061");
            mEA.add("Y062");
            mEA.add("Y063");
            mEA.add("Y064");
            mEA.add("Y065");
            mEA.add("Y066");
            mEA.add("Y067");
            mEA.add("Y068");
            mEA.add("Y069");
            mEA.add("Y070");



        } else if (ea.equals("Viwandani")|ea.equals("2")) {

            mEA.add("V007");
            mEA.add("V008");
            mEA.add("V009");
            mEA.add("V010");
            mEA.add("V011");
            mEA.add("V012");
            mEA.add("V013");
            mEA.add("V014");
            mEA.add("V015");
            mEA.add("V016");
            mEA.add("V017");
            mEA.add("V018");
            mEA.add("V019");
            mEA.add("V020");
            mEA.add("V021");
            mEA.add("V022");
            mEA.add("V023");
            mEA.add("V024");
            mEA.add("V025");
            mEA.add("V026");
            mEA.add("V027");
            mEA.add("V028");
            mEA.add("V029");
            mEA.add("V030");
            mEA.add("V031");
            mEA.add("V032");
            mEA.add("V033");
            mEA.add("V034");
            mEA.add("V035");
            mEA.add("V036");
            mEA.add("V037");
            mEA.add("V038");
            mEA.add("V039");
            mEA.add("V040");
            mEA.add("V140");


        }else {
        }
    }
}

