package energysaver.com.energykinect;

import android.content.ContentValues;
import android.content.Intent;
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
import android.widget.Toast;

import energysaver.com.DBHelper.EnvelopeDBHelper;
import energysaver.com.data.BuildingValues;

public class PropertyDetailsActivity extends AppCompatActivity {

    BuildingValues bValues=new BuildingValues();
    private static final String PROPERTY_TAG="Property Details Check";
    public static final String EXTRA_MESSAGE="buildingID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_set_first);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    public void onPropertyDetailsScreenClick(View view){
        EditText buildingNameText=(EditText)findViewById(R.id.buildingName);
        String buildingName=buildingNameText.getText().toString();
        if(buildingName.length()==0)
            buildingNameText.setError("Building Name is required");

        else
        bValues.setBuildingName(buildingName);

        EditText buildingIdText=(EditText)findViewById(R.id.buildingId);
        String buildingId=buildingIdText.getText().toString();
        if(buildingId.length()==0)
            buildingIdText.setError("Building Id is required");
        else
        bValues.setBuildingId(buildingId);

        EditText constructionYearText=(EditText)findViewById(R.id.constructionYear);
        String constructionYear=constructionYearText.getText().toString();
        if(constructionYear.length()==0)
            constructionYearText.setError("Construction Year is required");
        else
        bValues.setConstructionYear(constructionYear);

        EditText addressText=(EditText)findViewById(R.id.address);
        String address=addressText.getText().toString();
        if(address.length()==0)
            addressText.setError("Address is required");
        else
        bValues.setAddress(address);

        EditText cityText=(EditText)findViewById(R.id.city);
        String city=cityText.getText().toString();
        if(city.length()==0)
            cityText.setError("City is required");
        else
        bValues.setCity(city);

        EditText stateText=(EditText)findViewById(R.id.state);
        String state=stateText.getText().toString();
        if(state.length()==0)
            stateText.setError("State is required");
        else
        bValues.setState(state);

        EditText zipText=(EditText)findViewById(R.id.zip);
        String zip=zipText.getText().toString();
        if(zip.length()==0)
            zipText.setError("ZIP is required");
        else
        bValues.setZip(zip);

        if(buildingName.length()==0||buildingId.length()==0||constructionYear.length()==0||address.length()==0||
                city.length()==0||state.length()==0||zip.length()==0){
            Toast toast=Toast.makeText(this, "Please fill all the required fields", Toast.LENGTH_SHORT);
            toast.show();

        }
        else{
        ContentValues buildingValues=new ContentValues();


        try{
            //Debug
            Log.e(PROPERTY_TAG, "Inside Try block");
            SQLiteOpenHelper buildingDBHelper=new EnvelopeDBHelper(PropertyDetailsActivity.this);
            SQLiteDatabase db=buildingDBHelper.getWritableDatabase();

            //Checking Unique Value
            Cursor cursor=db.query("BUILDING_VALUES",
                    new String[]{"BUILDING_NAME"},
                    "BUILDING_ID = ?",
                    new String[]{buildingId}
                    , null, null, null
            );
            if (cursor.getCount() !=0){
                Log.e("MyCheck", "Cursor is not empty");
                Toast toast=Toast.makeText(this, "This building id is already registered", Toast.LENGTH_LONG);
                toast.show();

            }else{
                buildingValues.put("BUILDING_NAME", bValues.getBuildingName());
                buildingValues.put("BUILDING_ID", bValues.getBuildingId());
                buildingValues.put("CONSTRUCTION_YEAR", bValues.getConstructionYear());
                buildingValues.put("ADDRESS", bValues.getAddress());
                buildingValues.put("CITY", bValues.getCity());
                buildingValues.put("STATE", bValues.getState());
                buildingValues.put("ZIP", bValues.getZip());
                db.insert("BUILDING_VALUES", null, buildingValues);

                Log.e(PROPERTY_TAG, "Values Saved :" + bValues.getZip() + bValues.getState());

                Toast toast=Toast.makeText(this, "Details Saved", Toast.LENGTH_LONG);
                toast.show();
                db.close();

                Intent intent=new Intent(this,AgentDetailsActivity.class);
                //Intent intent=new Intent(this,DBTestActivity.class);
                intent.putExtra("buildingId", bValues.getBuildingId());
                startActivity(intent);

            }

        }catch (SQLiteException e){
            Toast toast=Toast.makeText(this,"Something Went Wrong", Toast.LENGTH_LONG);
            toast.show();
        }

        }

    }




}
