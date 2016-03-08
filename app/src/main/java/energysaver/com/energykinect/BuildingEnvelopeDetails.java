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
import android.widget.Toast;

import energysaver.com.DBHelper.EnvelopeDBHelper;
import energysaver.com.data.BuildingEnvelopeDetailsValues;

public class BuildingEnvelopeDetails extends AppCompatActivity {

    BuildingEnvelopeDetailsValues envDetails=new BuildingEnvelopeDetailsValues();
    private static final String ENV_TAG = "Bulding Envelope Check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_building_envelope_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onBuildingEnvelopeScreenClick(View view) {

        EditText windowToWallRatio = (EditText) findViewById(R.id.windowToWallRatio);
        int windowToWallRatioText = Integer.parseInt(windowToWallRatio.getText().toString());
        if (windowToWallRatio.getText().toString().length() == 0)
            windowToWallRatio.setError("This field is required");
        else
            envDetails.setWindowToWallRatio(windowToWallRatioText);

        EditText glazingSolarHeatGainCoefficient = (EditText) findViewById(R.id.glazingSolarHeatGainCoefficient);
        int glazingSolarHeatGainCoefficientText = Integer.parseInt(glazingSolarHeatGainCoefficient.getText().toString());
        if (glazingSolarHeatGainCoefficient.getText().toString().length() == 0)
            glazingSolarHeatGainCoefficient.setError("This field is required");
        else
            envDetails.setGlazingSolarGainCoefficient(glazingSolarHeatGainCoefficientText);

        EditText glazingUFactor = (EditText) findViewById(R.id.glazingUFactor);
        int glazingUFactorText = Integer.parseInt(glazingUFactor.getText().toString());
        if (glazingUFactor.getText().toString().length() == 0)
            glazingUFactor.setError("This field  is required");
        else
            envDetails.setGlazingUFactor(glazingUFactorText);

        EditText visibleLightTransmittance = (EditText) findViewById(R.id.visibleLightTransmittance);
        int visibleLightTransmittanceText = Integer.parseInt(visibleLightTransmittance.getText().toString());
        if (visibleLightTransmittance.getText().toString().length() == 0)
            visibleLightTransmittance.setError("This field  is required");
        else
            envDetails.setVisibleLightTransmittance(visibleLightTransmittanceText);

        EditText airLeakageRatio = (EditText) findViewById(R.id.airLeakageRatio);
        int airLeakageRatioText = Integer.parseInt(airLeakageRatio.getText().toString());
        if (airLeakageRatio.getText().toString().length() == 0)
            airLeakageRatio.setError("This field  is required");
        else
            envDetails.setAirLeakageRatio(airLeakageRatioText);

        EditText skylightSolarHeatCoefficient = (EditText) findViewById(R.id.skylightSolarHeatCoefficient);
        int skylightSolarHeatCoefficientText = Integer.parseInt(skylightSolarHeatCoefficient.getText().toString());
        if (skylightSolarHeatCoefficient.getText().toString().length() == 0)
            skylightSolarHeatCoefficient.setError("This field  is required");
        else
            envDetails.setWindowToWallRatio(skylightSolarHeatCoefficientText);

        EditText skylightUFactor = (EditText) findViewById(R.id.skylightUFactor);
        int skylightUFactorText = Integer.parseInt(skylightUFactor.getText().toString());
        if (skylightUFactor.getText().toString().length() == 0)
            skylightUFactor.setError("This field  is required");
        else
            envDetails.setSkyLightUFactor(skylightUFactorText);

        EditText roofUFactor = (EditText) findViewById(R.id.roofUFactor);
        int roofUFactorText = Integer.parseInt(roofUFactor.getText().toString());
        if (roofUFactor.getText().toString().length() == 0)
            roofUFactor.setError("This field  is required");
        else
            envDetails.setRoofUFactor(roofUFactorText);

        EditText roofInsulationType = (EditText) findViewById(R.id.roofInsulationType);
        String roofInsulationTypeText = roofInsulationType.getText().toString();
        if (roofInsulationType.getText().toString().length() == 0)
            roofInsulationType.setError("This field  is required");
        else
            envDetails.setRoofInsulationType(roofInsulationTypeText);

        EditText roofThickness = (EditText) findViewById(R.id.roofThickness);
        int roofThicknessText = Integer.parseInt(roofThickness.getText().toString());
        if (roofThickness.getText().toString().length() == 0)
            roofThickness.setError("This field  is required");
        else
            envDetails.setRoofThickness(roofThicknessText);

        EditText roofRValue = (EditText) findViewById(R.id.roofRValue);
        int roofRValueText = Integer.parseInt(roofRValue.getText().toString());
        if (roofRValue.getText().toString().length() == 0)
            roofRValue.setError("This field  is required");
        else
            envDetails.setRoofRValue(roofRValueText);

        Intent intent = getIntent();
        String uniqueBuildingId = intent.getStringExtra("buildingId");
        envDetails.setBuildingId(uniqueBuildingId);

        if (windowToWallRatio.getText().toString().length() == 0 || glazingSolarHeatGainCoefficient.getText().toString().length() == 0 || glazingUFactor.getText().toString().length() == 0 ||
                visibleLightTransmittance.getText().toString().length() == 0 || airLeakageRatio.getText().toString().length() == 0 || skylightSolarHeatCoefficient.getText().toString().length() == 0 ||
                skylightUFactor.getText().toString().length() == 0 || roofUFactor.getText().toString().length() == 0 || roofInsulationType.getText().toString().length() == 0 ||
                roofThickness.getText().toString().length() == 0 || roofRValue.getText().toString().length() == 0) {
            Toast toast = Toast.makeText(this, "Please fill all the required fields or else fill 0 if you value not known", Toast.LENGTH_LONG);
            toast.show();

        } else {
            ContentValues envelopeValues = new ContentValues();
            SQLiteOpenHelper buildingEnvelopeDBHelper = new EnvelopeDBHelper(BuildingEnvelopeDetails.this);

            try {
                //Debug
                Log.e(ENV_TAG, "Inside Try block");

                SQLiteDatabase db = buildingEnvelopeDBHelper.getWritableDatabase();
                envelopeValues.put("WINDOW_TO_WALL_RATIO", envDetails.getWindowToWallRatio());
                envelopeValues.put("BUILDING_ID", envDetails.getBuildingId());
                envelopeValues.put("GLAZING_SHGC", envDetails.getGlazingSolarGainCoefficient());
                envelopeValues.put("GLAZING_UFACTOR", envDetails.getGlazingUFactor());
                envelopeValues.put("VISIBLE_LIGHT_TRANSMITTANCE", envDetails.getVisibleLightTransmittance());
                envelopeValues.put("AIR_LEAKAGE_RATIO", envDetails.getAirLeakageRatio());
                envelopeValues.put("SKYLIGHT_SHC", envDetails.getSkylightSolarHeatCoefficient());
                envelopeValues.put("SKYLIGHT_UFACTOR", envDetails.getSkyLightUFactor());
                envelopeValues.put("ROOF_UFACTOR", envDetails.getRoofUFactor());
                envelopeValues.put("ROOF_INSULATION_TYPE", envDetails.getRoofInsulationType());
                envelopeValues.put("ROOF_THICKNESS", envDetails.getRoofThickness());
                envelopeValues.put("ROOF_RVALUE", envDetails.getRoofRValue());

                db.insert("ENVELOPE_VALUES", null, envelopeValues);

                Log.e(ENV_TAG, "Values Saved :" + envDetails.getWindowToWallRatio() + "\t" + envDetails.getWindowToWallRatio() + "\t" +
                        envDetails.getWindowToWallRatio() + "\t" + envDetails.getGlazingSolarGainCoefficient() + "\t" +
                        envDetails.getGlazingUFactor() + "\t" + envDetails.getVisibleLightTransmittance() + "\t" +
                        envDetails.getAirLeakageRatio() + "\t" + envDetails.getSkylightSolarHeatCoefficient() + "\t" +
                        envDetails.getSkyLightUFactor() + "\t" + envDetails.getRoofUFactor() + "\t" + envDetails.getRoofInsulationType() + "\t" +
                        envDetails.getRoofThickness() + "\tLast Value :" + envDetails.getRoofRValue());

                Toast toast = Toast.makeText(this, "Details Saved", Toast.LENGTH_LONG);
                toast.show();
                db.close();
                intent = new Intent(this, LaunchActivity.class);
                intent.putExtra("buildingId", envDetails.getBuildingId());
                startActivity(intent);
            } catch (SQLiteException e) {
                Toast toast = Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_LONG);
                toast.show();
            }



        }

    }

}
