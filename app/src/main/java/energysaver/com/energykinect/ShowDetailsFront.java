package energysaver.com.energykinect;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import energysaver.com.DBHelper.EnvelopeDBHelper;

public class ShowDetailsFront extends AppCompatActivity {

    private static final String SHOW_TAG="Show Building Check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_details_front);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onClickShowFront(View view){

        EditText building_Id=(EditText)findViewById(R.id.enterBuildingId1);
        String buildingId=building_Id.getText().toString();

        /*Intent intent=getIntent();
        String buildingIdFromPre = intent.getStringExtra("buildingId");*/

        try{
            SQLiteOpenHelper showBuildingDataDBHelper=new EnvelopeDBHelper(this);
            SQLiteDatabase db=showBuildingDataDBHelper.getWritableDatabase();

            Cursor cursor=db.query("BUILDING_VALUES",
                    new String[]{"BUILDING_NAME", "BUILDING_ID", "CONSTRUCTION_YEAR", "ADDRESS", "CITY", "STATE", "ZIP"},
                    "BUILDING_ID = ?",
                    new String[]{buildingId}
                    , null, null, null
            );


            Log.e(SHOW_TAG, "inside db.query");

            if (!(cursor.moveToFirst()) || cursor.getCount() ==0){
                Log.e("MyCheck", "Cursor is empty");

            }

            for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                Log.e("MyCheck", "for");
                String buildingNameTest=cursor.getString(0);
                String buildingIdTest=cursor.getString(1);
                String constructionYearTest=cursor.getString(2);
                String addressTest=cursor.getString(3);
                String cityTest=cursor.getString(4);
                String stateTest=cursor.getString(5);
                String zipTest=cursor.getString(6);

                Log.e(SHOW_TAG, "Values retrieved :"+cursor.getString(5)+cursor.getString(6));

                TextView building_name_text=(TextView)findViewById(R.id.buildingNameText1);
                building_name_text.setText("Building Name : " + "\t" + buildingNameTest);

                /*TextView building_id_text=(TextView)findViewById(R.id.buildingIdText);
                building_id_text.setText(buildingIdTest);*/

                TextView construction_year_text=(TextView)findViewById(R.id.constructionYearText1);
                construction_year_text.setText("Construction Year : "+ "\t" +constructionYearTest);

                TextView address_text=(TextView)findViewById(R.id.addressText1);
                address_text.setText("Address : "+ "\t" +addressTest);

                TextView city_text=(TextView)findViewById(R.id.cityText1);
                city_text.setText("City : "+ "\t" +cityTest);

                TextView state_text=(TextView)findViewById(R.id.stateText1);
                state_text.setText("State : "+ "\t" +stateTest);

                TextView zip_text=(TextView)findViewById(R.id.zipText1);
                zip_text.setText("ZIP : "+ "\t" +zipTest);


            }
            cursor.close();
            db.close();

        }catch (SQLiteException e){
            Toast toast=Toast.makeText(this, "DB Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        try{
            SQLiteOpenHelper showBuildingDataDBHelper=new EnvelopeDBHelper(this);
            SQLiteDatabase db=showBuildingDataDBHelper.getWritableDatabase();

            Cursor cursor1=db.query("AGENT_VALUES",
                    new String[]{"AGENT_NAME", "BUILDING_ID", "AGENT_EMAIL", "AGENT_COMPANY", "AGENT_MOBILE"},
                    "BUILDING_ID = ?",
                    new String[]{buildingId}
                    , null, null, null
            );

            Log.e(SHOW_TAG, "inside db.query");

            if (!(cursor1.moveToFirst()) || cursor1.getCount() ==0){
                Log.e("MyCheck", "Cursor1 is empty");

            }


            for(cursor1.moveToFirst();!cursor1.isAfterLast();cursor1.moveToNext()){
                Log.e("MyCheck", "for Cursor1");
                String agentNameTest=cursor1.getString(0);
                String buildingId1=cursor1.getString(1);
                String agentEmailTest=cursor1.getString(2);
                String agentCompanyTest=cursor1.getString(3);
                String agentMobileTest=cursor1.getString(4);

                Log.e(SHOW_TAG, "Values retrieved :" + cursor1.getString(0) + "\t" + cursor1.getString(1) + "\t" + cursor1.getString(2) + "\t" + cursor1.getString(3) + "\t" + cursor1.getString(4));

                TextView agent_name_text=(TextView)findViewById(R.id.agentNameText1);
                agent_name_text.setText("Agent Name : "+ "\t" +agentNameTest);

                /*TextView building_id_text=(TextView)findViewById(R.id.buildingIdTest);
                building_id_text.setText(buildingId);*/

                TextView agent_email_text=(TextView)findViewById(R.id.agentEmailText1);
                agent_email_text.setText("Agent Email : "+ "\t" +agentEmailTest);

                TextView agent_company_text=(TextView)findViewById(R.id.agentCompanyText1);
                agent_company_text.setText("Agent Company : "+ "\t" +agentCompanyTest);

                TextView agent_mobile_text=(TextView)findViewById(R.id.agentMobileText1);
                agent_mobile_text.setText("Agent Mobile : "+ "\t" +agentMobileTest);



            }
            cursor1.close();
            db.close();

        }catch (SQLiteException e){
            Toast toast=Toast.makeText(this, "DB Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        try{
            SQLiteOpenHelper showBuildingDataDBHelper=new EnvelopeDBHelper(this);
            SQLiteDatabase db=showBuildingDataDBHelper.getWritableDatabase();


            Cursor cursor2=db.query("INFO_PONE_VALUES",
                    new String[]{"BUILDING_TYPE", "BUILDING_ID", "WORKING_HOURS", "WORKING_DAYS", "FLOOR_COUNT",
                                    "BUILT_UP_AREA", "CONDITIONAL_AREA", "TOTAL_ABOVE_GRADE_WALL_AREA", "VERTICAL_WALL_AREA_PERCENT",
                                    "TOTAL_ROOF_AREA", "SKYLIGHT_ROOF_AREA_PERCENT"},
                    "BUILDING_ID = ?",
                    new String[]{buildingId}
                    , null, null, null
            );

            Log.e(SHOW_TAG, "inside db.query");

            if (!(cursor2.moveToFirst()) || cursor2.getCount() ==0){
                Log.e("MyCheck", "Cursor2 is empty");

            }


            for(cursor2.moveToFirst();!cursor2.isAfterLast();cursor2.moveToNext()){
                Log.e("MyCheck", "for Cursor2");
                String buildingTypeTest=cursor2.getString(0);
                String buildingId2=cursor2.getString(1);
                String workingHoursTest=cursor2.getString(2);
                String workingDaysTest=cursor2.getString(3);
                String floorCountTest=cursor2.getString(4);
                String builtUpAreaTest=cursor2.getString(5);
                String conditionalAreaTest=cursor2.getString(6);
                String totalAboveGradeWallAreaTest=cursor2.getString(7);
                String verticalWallAreaPercent=cursor2.getString(8);
                String totalRoofArea=cursor2.getString(9);
                String skylightRoofAreaPercent=cursor2.getString(10);


                Log.e(SHOW_TAG, "Values retrieved :" + cursor2.getString(0) + "\t" + cursor2.getString(1) + "\t" + cursor2.getString(2) +
                        "\t" + cursor2.getString(3) + "\t" + cursor2.getString(4) + "\t" + cursor2.getString(5) + "\t" + cursor2.getString(6)
                        + "\t" + cursor2.getString(7) + "\t" + cursor2.getString(8) + "\t" + cursor2.getString(9) + "\t" + cursor2.getString(10));

                TextView building_type_text=(TextView)findViewById(R.id.buildingTypeText1);
                building_type_text.setText("Building Type : "+ "\t" +buildingTypeTest);

                /*TextView building_id_text=(TextView)findViewById(R.id.buildingIdTest);
                building_id_text.setText(buildingId);*/

                TextView working_hours_text=(TextView)findViewById(R.id.workingHoursText1);
                working_hours_text.setText("Working Hours : "+ "\t" +workingHoursTest);

                TextView working_days_text=(TextView)findViewById(R.id.workingDaysText1);
                working_days_text.setText("Working Days : "+ "\t" +workingDaysTest);

                TextView floor_count_text=(TextView)findViewById(R.id.floorCountText1);
                floor_count_text.setText("Floor Count : "+ "\t" +floorCountTest);

                TextView built_up_area_text=(TextView)findViewById(R.id.builtUpAreaText1);
                built_up_area_text.setText("Built Up Area : "+ "\t" +builtUpAreaTest);

                TextView conditional_area_text=(TextView)findViewById(R.id.conditionalAreaText1);
                conditional_area_text.setText("Conditional Area : "+ "\t" +conditionalAreaTest);

                TextView total_above_grade_wall_text=(TextView)findViewById(R.id.totalAboveGradeWallAreaText1);
                total_above_grade_wall_text.setText("Total Above Grade Wall Area : "+ "\t" +totalAboveGradeWallAreaTest);

                TextView vertical_area_percent_text=(TextView)findViewById(R.id.verticalWallAreaPercentText1);
                vertical_area_percent_text.setText("Vertical Wall Area : "+ "\t" +verticalWallAreaPercent);

                TextView total_roof_area_text=(TextView)findViewById(R.id.totalRoofAreaText1);
                total_roof_area_text.setText("Total Roof Area : "+ "\t" +totalRoofArea);

                TextView sky_light_roof_area_percent_text=(TextView)findViewById(R.id.skylightRoofAreaPercentText1);
                sky_light_roof_area_percent_text.setText("SkyLight Roof Area : "+ "\t" +skylightRoofAreaPercent);



            }
            cursor2.close();
            db.close();

        }catch (SQLiteException e){
            Toast toast=Toast.makeText(this, "DB Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        try{
            SQLiteOpenHelper showBuildingDataDBHelper=new EnvelopeDBHelper(this);
            SQLiteDatabase db=showBuildingDataDBHelper.getWritableDatabase();

            Cursor cursor3=db.query("ENVELOPE_VALUES",
                    new String[]{"WINDOW_TO_WALL_RATIO", "BUILDING_ID", "GLAZING_SHGC", "GLAZING_UFACTOR", "VISIBLE_LIGHT_TRANSMITTANCE",
                            "AIR_LEAKAGE_RATIO", "SKYLIGHT_SHC", "SKYLIGHT_UFACTOR", "ROOF_UFACTOR",
                            "ROOF_INSULATION_TYPE","ROOF_THICKNESS", "ROOF_RVALUE"},
                    "BUILDING_ID = ?",
                    new String[]{buildingId}
                    , null, null, null
            );

            Log.e(SHOW_TAG, "inside db.query");

            if (!(cursor3.moveToFirst()) || cursor3.getCount() ==0){
                Log.e("MyCheck", "Cursor3 is empty");


            }


            for(cursor3.moveToFirst();!cursor3.isAfterLast();cursor3.moveToNext()){
                Log.e("MyCheck", "for Cursor3");
                String windowToWallRatioTest=cursor3.getString(0);
                String buildingId2=cursor3.getString(1);
                String glazingSHGCTest=cursor3.getString(2);
                String glazingUfactorTest=cursor3.getString(3);
                String visibleLightTransmittanceTest=cursor3.getString(4);
                String airLeakageRatioTest=cursor3.getString(5);
                String skylightSHGCTest=cursor3.getString(6);
                String skylightUFactorTest=cursor3.getString(7);
                String roofUFactorTest=cursor3.getString(8);
                String roofInsulationType=cursor3.getString(9);
                String roofThickness=cursor3.getString(10);
                String roofRValue=cursor3.getString(11);


                Log.e(SHOW_TAG, "Values retrieved :" + cursor3.getString(0) + "\t" + cursor3.getString(1) + "\t" + cursor3.getString(2) +
                        "\t" + cursor3.getString(3) + "\t" + cursor3.getString(4) + "\t" + cursor3.getString(5) + "\t" + cursor3.getString(6)
                        + "\t" + cursor3.getString(7) + "\t" + cursor3.getString(8) + "\t" + cursor3.getString(9) + "\t" + cursor3.getString(10) + "\t" + cursor3.getString(11));

                TextView window_to_wall_ratio_text=(TextView)findViewById(R.id.windowToWallRatioText1);
                window_to_wall_ratio_text.setText("Window To Wall Ratio : "+ "\t" +windowToWallRatioTest);

                /*TextView building_id_text=(TextView)findViewById(R.id.buildingIdTest);
                building_id_text.setText(buildingId);*/

                TextView glazing_shgc_text=(TextView)findViewById(R.id.glazingSolarHeatGainCoefficientText1);
                glazing_shgc_text.setText("Glazing SHGC : "+ "\t" +glazingSHGCTest);

                TextView glazing_u_factor_text=(TextView)findViewById(R.id.glazingUFactorText1);
                glazing_u_factor_text.setText("Glazing U Factor : "+ "\t" +glazingUfactorTest);

                TextView visible_light_transmittance_text=(TextView)findViewById(R.id.visibleLightTransmittanceText1);
                visible_light_transmittance_text.setText("Visible Light Transmittance : "+ "\t" +visibleLightTransmittanceTest);

                TextView air_leakage_ratio_text=(TextView)findViewById(R.id.airLeakageRatioText1);
                air_leakage_ratio_text.setText("Air Leakage Ratio : "+ "\t" +airLeakageRatioTest);

                TextView skylight_shgc_text=(TextView)findViewById(R.id.skylightSolarHeatCoefficientText1);
                skylight_shgc_text.setText("SkyLight SHGC : "+ "\t" +skylightSHGCTest);

                TextView skylight_u_factor_text=(TextView)findViewById(R.id.skylightUFactorText1);
                skylight_u_factor_text.setText("SkyLight U factor : "+ "\t" +skylightUFactorTest);

                TextView roof_u_factor_text=(TextView)findViewById(R.id.roofUFactorText1);
                roof_u_factor_text.setText("Roof U Factor : "+ "\t" +roofUFactorTest);

                TextView roof_insulation_type_text=(TextView)findViewById(R.id.roofInsulationTypeText1);
                roof_insulation_type_text.setText("Roof Insulation Type : "+ "\t" +roofInsulationType);

                TextView roof_thickness_text=(TextView)findViewById(R.id.roofThicknessText1);
                roof_thickness_text.setText("Roof Thickness :"+ "\t" +roofThickness);

                TextView roof_r_value_text=(TextView)findViewById(R.id.roofRValueText1);
                roof_r_value_text.setText("Roof R value : "+ "\t" +roofRValue);



            }
            cursor3.close();
            db.close();

        }catch (SQLiteException e){
            Toast toast=Toast.makeText(this, "DB Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}
