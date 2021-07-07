package org.o7planning.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Profit extends AppCompatActivity {
    private Button back1;
    private Button update;
    private Button showprofile;
    String userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profit);
        back1 = findViewById(R.id.back1);
        update = findViewById(R.id.updateProfit);
        showprofile = findViewById(R.id.showprf);
        Intent i = getIntent();
        userData = i.getExtras().getString("userData","");
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent updatePro = new Intent(getApplicationContext(),UpdateProfit.class);
                updatePro.putExtra("userData", userData);
                startActivity(updatePro);

            }
        });

        showprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent updatePro1 = new Intent(getApplicationContext(),ShowProfit.class);
                updatePro1.putExtra("userData", userData);
                startActivity(updatePro1);

            }
        });
    }

}