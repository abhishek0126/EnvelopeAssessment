package energysaver.com.energykinect;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    public void showData(View view){
        Intent intent=new Intent(this, ShowDetailsFront.class);
        startActivity(intent);
    }

    public void collectData(View view){
        Intent intent=new Intent(this, PropertyDetailsActivity.class);
        startActivity(intent);
    }

}
