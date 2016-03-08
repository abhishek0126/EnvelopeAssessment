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

public class ShowBuildingDataActivity extends AppCompatActivity {

    private static final String SHOW_TAG="Show Building Check";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_building_data);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onClickShow(View view){

        EditText building_Id=(EditText)findViewById(R.id.enterBuildingId);
        String buildingId=building_Id.getText().toString();

        /*Intent intent=getIntent();
        String buildingIdFromPre = intent.getStringExtra("buildingId");*/

        try{
            SQLiteOpenHelper showBuildingDataDBHelper=new EnvelopeDBHelper(this);
            //SQLiteDatabase db=testDBHelper.getReadableDatabase();
            SQLiteDatabase db=showBuildingDataDBHelper.getWritableDatabase();

            Cursor cursor=db.query("BUILDING_VALUES",
                    new String[]{"BUILDING_NAME", "BUILDING_ID", "CONSTRUCTION_YEAR", "ADDRESS", "CITY", "STATE", "ZIP"},
                    "BUILDING_ID = ?",
                    new String[]{buildingId}
                    , null, null, null
            );

            Cursor cursor1=db.query("AGENT_VALUES",
                    new String[]{"AGENT_NAME", "BUILDING_ID", "AGENT_EMAIL", "AGENT_EMAIL", "AGENT_MOBILE"},
                    "BUILDING_ID = ?",
                    new String[]{buildingId}
                    , null, null, null
            );


            Cursor cursor2=db.query("INFO_PONE_VALUES",
                    new String[]{"BUILDING_TYPE", "BUILDING_ID", "WORKING_HOURS", "WORKING_DAYS", "BUILT_UP_AREA", "CONDITIONAL_AREA", "TOTAL_ABOVE_GRADE_WALL_AREA", "VERTICAL_WALL_AREA_PERCENT", "TOTAL_ROOF_AREA", "SKYLIGHT_ROOF_AREA_PERCENT" },
                    "BUILDING_ID = ?",
                    new String[]{buildingId}
                    , null, null, null
            );

            Log.e(SHOW_TAG, "inside db.query");

            if (!(cursor.moveToFirst()) || cursor.getCount() ==0){
                Log.e("MyCheck", "Cursor is empty");

            }
            if (!(cursor1.moveToFirst()) || cursor1.getCount() ==0){
                Log.e("MyCheck", "Cursor1 is empty");

            }
            if (!(cursor2.moveToFirst()) || cursor2.getCount() ==0){
                Log.e("MyCheck", "Cursor2 is empty");

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

                TextView building_name_text=(TextView)findViewById(R.id.buildingNameText);
                building_name_text.setText(buildingNameTest);

                /*TextView building_id_text=(TextView)findViewById(R.id.buildingIdText);
                building_id_text.setText(buildingIdTest);*/

                TextView construction_year_text=(TextView)findViewById(R.id.constructionYearText);
                construction_year_text.setText(constructionYearTest);

                TextView address_text=(TextView)findViewById(R.id.addressText);
                address_text.setText(addressTest);

                TextView city_text=(TextView)findViewById(R.id.cityText);
                city_text.setText(cityTest);

                TextView state_text=(TextView)findViewById(R.id.stateText);
                state_text.setText(stateTest);

                TextView zip_text=(TextView)findViewById(R.id.zipText);
                zip_text.setText(zipTest);


            }
            cursor.close();
            db.close();

        }catch (SQLiteException e){
            Toast toast=Toast.makeText(this, "DB Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }

        try{
            SQLiteOpenHelper showBuildingDataDBHelper=new EnvelopeDBHelper(this);
            //SQLiteDatabase db=testDBHelper.getReadableDatabase();
            SQLiteDatabase db=showBuildingDataDBHelper.getWritableDatabase();

            Cursor cursor1=db.query("AGENT_VALUES",
                    new String[]{"AGENT_NAME", "BUILDING_ID", "AGENT_EMAIL", "AGENT_EMAIL", "AGENT_MOBILE"},
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

                Log.e(SHOW_TAG, "Values retrieved :" + cursor1.getString(0) + cursor1.getString(1));

                TextView agent_name_text=(TextView)findViewById(R.id.agentNameText);
                agent_name_text.setText(agentNameTest);

                /*TextView building_id_text=(TextView)findViewById(R.id.buildingIdTest);
                building_id_text.setText(buildingId);*/

                TextView agent_email_text=(TextView)findViewById(R.id.agentEmailText);
                agent_email_text.setText(agentEmailTest);

                TextView agent_company_text=(TextView)findViewById(R.id.agentCompanyText);
                agent_company_text.setText(agentCompanyTest);

                TextView agent_mobile_text=(TextView)findViewById(R.id.agentMobileText);
                agent_mobile_text.setText(agentMobileTest);



            }
            cursor1.close();
            db.close();

        }catch (SQLiteException e){
            Toast toast=Toast.makeText(this, "DB Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

}
