package energysaver.com.energykinect;

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
import android.widget.TextView;
import android.widget.Toast;

import energysaver.com.DBHelper.EnvelopeDBHelper;

public class DBTestActivity extends AppCompatActivity {

    private static final String DBTEST_TAG="DBTEST Check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




    }

    public void enterBuildingID(View view){

        EditText building_Id=(EditText)findViewById(R.id.enterId);
        String buildingId=building_Id.getText().toString();

        Intent intent=getIntent();
        String buildingIdFromPre = intent.getStringExtra("buildingId");
        try{
            SQLiteOpenHelper testDBHelper=new EnvelopeDBHelper(this);
            //SQLiteDatabase db=testDBHelper.getReadableDatabase();
            SQLiteDatabase db=testDBHelper.getWritableDatabase();
            Cursor cursor=db.query("BUILDING_VALUES",
                    new String[]{"BUILDING_NAME", "BUILDING_ID", "CONSTRUCTION_YEAR", "ADDRESS", "CITY", "STATE", "ZIP"},
                    "BUILDING_ID = ?",
                    new String[]{buildingIdFromPre}
                    , null, null, null
            );

            Log.e(DBTEST_TAG, "inside db.query");

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

                Log.e(DBTEST_TAG, "Values retrieved :"+cursor.getString(5)+cursor.getString(6));

                TextView building_name_text=(TextView)findViewById(R.id.buildingNameTest);
                building_name_text.setText(buildingNameTest);

                TextView building_id_text=(TextView)findViewById(R.id.buildingIdTest);
                building_id_text.setText(buildingIdTest);

                TextView construction_year_text=(TextView)findViewById(R.id.constructionYeartest);
                construction_year_text.setText(constructionYearTest);

                TextView address_text=(TextView)findViewById(R.id.addressTest);
                address_text.setText(addressTest);

                TextView city_text=(TextView)findViewById(R.id.cityTest);
                city_text.setText(cityTest);

                TextView state_text=(TextView)findViewById(R.id.stateTest);
                state_text.setText(stateTest);

                TextView zip_text=(TextView)findViewById(R.id.zipTest);
                zip_text.setText(zipTest);


            }
            cursor.close();
            db.close();

        }catch (SQLiteException e){
            Toast toast=Toast.makeText(this, "DB Unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }


    }

}
