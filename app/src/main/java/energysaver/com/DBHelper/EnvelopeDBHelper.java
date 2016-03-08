package energysaver.com.DBHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by abhishekkumar on 06/03/16.
 */
public class EnvelopeDBHelper extends SQLiteOpenHelper {

    private static final String DB_name="envelopedb";
    private static final int DB_VERSION=2;

    private final static String BUILDING_TAG="BuildingCheck";

    public EnvelopeDBHelper(Context context){
        super(context, DB_name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE BUILDING_VALUES ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "BUILDING_NAME TEXT, "
                + "BUILDING_ID TEXT unique, "
                + "CONSTRUCTION_YEAR TEXT, "
                + "ADDRESS TEXT, "
                + "CITY TEXT, "
                + "STATE TEXT, "
                + "ZIP TEXT);");

        db.execSQL("CREATE TABLE AGENT_VALUES ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "AGENT_NAME TEXT, "
                + "BUILDING_ID TEXT unique, "
                + "AGENT_EMAIL TEXT, "
                + "AGENT_COMPANY TEXT, "
                + "AGENT_MOBILE TEXT);");

        db.execSQL("CREATE TABLE INFO_PONE_VALUES ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "BUILDING_TYPE TEXT, "
                + "BUILDING_ID TEXT unique, "
                + "WORKING_HOURS TEXT, "
                + "WORKING_DAYS TEXT, "
                + "FLOOR_COUNT TEXT, "
                + "BUILT_UP_AREA INTEGER, "
                + "CONDITIONAL_AREA INTEGER, "
                + "TOTAL_ABOVE_GRADE_WALL_AREA INTEGER, "
                + "VERTICAL_WALL_AREA_PERCENT INTEGER, "
                + "TOTAL_ROOF_AREA INTEGER, "
                + "SKYLIGHT_ROOF_AREA_PERCENT INTEGER);");

        db.execSQL("CREATE TABLE ENVELOPE_VALUES ("
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "WINDOW_TO_WALL_RATIO INTEGER, "
                + "BUILDING_ID TEXT unique, "
                + "GLAZING_SHGC INTEGER, "
                + "GLAZING_UFACTOR INTEGER, "
                + "VISIBLE_LIGHT_TRANSMITTANCE INTEGER, "
                + "AIR_LEAKAGE_RATIO INTEGER, "
                + "SKYLIGHT_SHC INTEGER, "
                + "SKYLIGHT_UFACTOR INTEGER, "
                + "ROOF_UFACTOR INTEGER, "
                + "ROOF_INSULATION_TYPE TEXT, "
                + "ROOF_THICKNESS INTEGER, "
                + "ROOF_RVALUE INTEGER);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }

    public static void insertDetails(SQLiteDatabase db, String buildingName, String buildingId, String constructionYear, String address, String city, String state, String zip){
        ContentValues buildingValues=new ContentValues();
        buildingValues.put("BUILDING_NAME", buildingName);
        buildingValues.put("BUILDING_ID", buildingId);
        buildingValues.put("CONSTRUCTION_YEAR", constructionYear);
        buildingValues.put("ADDRESS", address);
        buildingValues.put("CITY", city);
        buildingValues.put("STATE", state);
        buildingValues.put("ZIP", zip);
        long rowInserted=db.insert("BUILDING_VALUES", null, buildingValues);

        /*Debug code*/
        if(rowInserted!=-1)
            Log.e(BUILDING_TAG, "Records Added");
        else
            Log.e(BUILDING_TAG, "Something is wrong");
    }
}
