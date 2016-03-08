package energysaver.com.energykinect;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import energysaver.com.DBHelper.EnvelopeDBHelper;
import energysaver.com.data.BuildingInfoPOneValues;

public class BuildingInfoPOneActivity extends AppCompatActivity {

    BuildingInfoPOneValues bInfoPOneValues=new BuildingInfoPOneValues();
    private static final String P_ONE_TAG="BuildingInfoPOne Check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_info_pone);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onContentBuildingPageOneClick(View view){

        Spinner buildingTypeSpinner=(Spinner)findViewById(R.id.buildingTypeSpinner);
        String buildingTypeText=buildingTypeSpinner.getSelectedItem().toString();
        bInfoPOneValues.setBuildingType(buildingTypeText);

        Spinner workingHoursSpinner=(Spinner)findViewById(R.id.workingHoursSpinner);
        String workingHoursText=workingHoursSpinner.getSelectedItem().toString();
        bInfoPOneValues.setBuildingHours(workingHoursText);

        Spinner workingDaysSpinner=(Spinner)findViewById(R.id.workingDaysSpinner);
        String workingDaysText=workingDaysSpinner.getSelectedItem().toString();
        bInfoPOneValues.setWorkingDays(workingDaysText);

        Spinner floorCountSpinner=(Spinner)findViewById(R.id.floorCountSpinner);
        String floorCountText=floorCountSpinner.getSelectedItem().toString();
        bInfoPOneValues.setFloorCount(floorCountText);

        EditText builtUpArea=(EditText)findViewById(R.id.builtUpArea);
        int builtUpAreaText=Integer.parseInt(builtUpArea.getText().toString());
        bInfoPOneValues.setBuiltUpArea(builtUpAreaText);

        EditText conditionalArea=(EditText)findViewById(R.id.conditionalArea);
        int conditionalAreaText=Integer.parseInt(conditionalArea.getText().toString());
        bInfoPOneValues.setConditionalArea(conditionalAreaText);


        EditText totalAboveGradeWallArea=(EditText)findViewById(R.id.totalAboveGradeWallArea);
        int totalAboveGradeWallAreaText=Integer.parseInt(totalAboveGradeWallArea.getText().toString());
        bInfoPOneValues.setTotalAboveGradWallArea(totalAboveGradeWallAreaText);

        EditText verticalWallAreaPercent=(EditText)findViewById(R.id.verticalWallAreaPercent);
        int verticalWallAreaPercentText=Integer.parseInt(verticalWallAreaPercent.getText().toString());
        bInfoPOneValues.setVerticalWallAreaPercent(verticalWallAreaPercentText);

        EditText totalRoofArea=(EditText)findViewById(R.id.totalRoofArea);
        int totalRoofAreaText=Integer.parseInt(totalRoofArea.getText().toString());
        bInfoPOneValues.setTotalRoofArea(totalRoofAreaText);

        EditText skylightRoofAreaPercent=(EditText)findViewById(R.id.skylightRoofAreaPercent);
        int skylightRoofAreaPercentText=Integer.parseInt(skylightRoofAreaPercent.getText().toString());
        bInfoPOneValues.setSkylightRoofAreaPercent(skylightRoofAreaPercentText);

        Intent intent=getIntent();
        String uniqueBuildingId = intent.getStringExtra("buildingId");
        bInfoPOneValues.setBuildingId(uniqueBuildingId);

        ContentValues buildingInfoPOneValues=new ContentValues();
        SQLiteOpenHelper buildingInfoPOneHelper=new EnvelopeDBHelper(BuildingInfoPOneActivity.this);

        try{
            //Debug
            Log.e(P_ONE_TAG, "Inside Try block");

            SQLiteDatabase db=buildingInfoPOneHelper.getWritableDatabase();
            buildingInfoPOneValues.put("BUILDING_TYPE", bInfoPOneValues.getBuildingType());
            buildingInfoPOneValues.put("BUILDING_ID", bInfoPOneValues.getBuildingId());
            buildingInfoPOneValues.put("WORKING_HOURS", bInfoPOneValues.getBuildingHours());
            buildingInfoPOneValues.put("WORKING_DAYS", bInfoPOneValues.getWorkingDays());
            buildingInfoPOneValues.put("FLOOR_COUNT", bInfoPOneValues.getFloorCount());
            buildingInfoPOneValues.put("BUILT_UP_AREA", bInfoPOneValues.getBuiltUpArea());
            buildingInfoPOneValues.put("CONDITIONAL_AREA", bInfoPOneValues.getConditionalArea());
            buildingInfoPOneValues.put("TOTAL_ABOVE_GRADE_WALL_AREA", bInfoPOneValues.getTotalAboveGradWallArea());
            buildingInfoPOneValues.put("VERTICAL_WALL_AREA_PERCENT", bInfoPOneValues.getVerticalWallAreaPercent());
            buildingInfoPOneValues.put("TOTAL_ROOF_AREA", bInfoPOneValues.getTotalRoofArea());
            buildingInfoPOneValues.put("SKYLIGHT_ROOF_AREA_PERCENT", bInfoPOneValues.getSkylightRoofAreaPercent());

            db.insert("INFO_PONE_VALUES", null, buildingInfoPOneValues);

            Log.e(P_ONE_TAG, "Values Saved :" + bInfoPOneValues.getBuildingType() + bInfoPOneValues.getBuildingId() + bInfoPOneValues.getBuildingHours() + bInfoPOneValues.getWorkingDays() + bInfoPOneValues.getFloorCount() + bInfoPOneValues.getBuiltUpArea());

            Toast toast=Toast.makeText(this, "Details Saved", Toast.LENGTH_LONG);
            toast.show();
            db.close();
        }catch (SQLiteException e){
            Toast toast=Toast.makeText(this,"Something Went Wrong", Toast.LENGTH_LONG);
            toast.show();
        }


        intent=new Intent(this, BuildingEnvelopeDetails.class);
        intent.putExtra("buildingId", bInfoPOneValues.getBuildingId());
        startActivity(intent);
    }

}
