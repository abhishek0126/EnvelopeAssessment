package energysaver.com.energykinect;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import energysaver.com.DBHelper.EnvelopeDBHelper;
import energysaver.com.data.AgentValues;

public class AgentDetailsActivity extends AppCompatActivity {

    AgentValues aValues=new AgentValues();
    private static final String AGENT_TAG="Agent Details Check";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_details);

    }

    public void onAgentDetailsScreenClick(View view) {

        EditText agentNameText = (EditText) findViewById(R.id.agentName);
        String agentName = agentNameText.getText().toString();
        if (agentName.length() == 0)
            agentNameText.setError("Agent Name is required");
        else
            aValues.setAgentName(agentName);

        EditText agentEmailText = (EditText) findViewById(R.id.agentEmail);
        String agentEmail = agentEmailText.getText().toString();
        if (agentEmail.length() == 0)
            agentEmailText.setError("Agent Email is required");
        else
            aValues.setAgentEmail(agentEmail);

        EditText agentCompanyText = (EditText) findViewById(R.id.agentCompany);
        String agentCompany = agentCompanyText.getText().toString();
        if (agentCompany.length() == 0)
            agentCompanyText.setError("Agent Company Field is required");
        else
            aValues.setAgentCompany(agentCompany);

        EditText agentMobileText = (EditText) findViewById(R.id.agentMobile);
        String agentMobile = agentMobileText.getText().toString();
        if (agentMobile.length() == 0)
            agentMobileText.setError("Agent mobile Field is required");
        else
            aValues.setAgentMobile(agentMobile);

        Intent intent = getIntent();
        String uniqueBuildingId = intent.getStringExtra("buildingId");
        aValues.setBuildingId(uniqueBuildingId);

        if (agentName.length() == 0 || agentEmail.length() == 0 || agentCompany.length() == 0 || agentMobile.length() == 0) {
            Toast toast = Toast.makeText(this, "Please fill all the required fields", Toast.LENGTH_SHORT);
            toast.show();

        } else {
            ContentValues agentValues = new ContentValues();


            try {
                //Debug
                Log.e(AGENT_TAG, "Inside Try block");
                SQLiteOpenHelper agentDBHelper = new EnvelopeDBHelper(AgentDetailsActivity.this);
                SQLiteDatabase db = agentDBHelper.getWritableDatabase();
                agentValues.put("AGENT_NAME", aValues.getAgentName());
                agentValues.put("BUILDING_ID", aValues.getBuildingId());
                agentValues.put("AGENT_EMAIL", aValues.getAgentEmail());
                agentValues.put("AGENT_COMPANY", aValues.getAgentCompany());
                agentValues.put("AGENT_MOBILE", aValues.getAgentMobile());

                db.insert("AGENT_VALUES", null, agentValues);

                Log.e(AGENT_TAG, "Values Saved :" + aValues.getAgentName() + "\t" + aValues.getBuildingId() + "\t" + aValues.getAgentEmail() + "\t" + aValues.getAgentCompany() + "\t" + aValues.getAgentMobile());

                Toast toast = Toast.makeText(this, "Details Saved", Toast.LENGTH_LONG);
                toast.show();
                db.close();
                intent = new Intent(this, BuildingInfoPOneActivity.class);
                intent.putExtra("buildingId", aValues.getBuildingId());
                startActivity(intent);

            } catch (SQLiteException e) {
                Toast toast = Toast.makeText(this, "Something Went Wrong", Toast.LENGTH_LONG);
                toast.show();
            }



        }
    }


}
