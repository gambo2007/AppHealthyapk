package org.o7planning.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RiskAssessment extends AppCompatActivity {
    private Button BackRR;
    private Button SubR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_risk_assessment);

        BackRR = findViewById(R.id.backRe);
        SubR = findViewById(R.id.sub);

        BackRR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        SubR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Submit successful",Toast.LENGTH_SHORT).show();
            }
        });
    }
}