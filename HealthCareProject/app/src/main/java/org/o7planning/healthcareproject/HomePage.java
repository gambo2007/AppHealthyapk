package org.o7planning.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomePage extends AppCompatActivity {
    private TextView nameshow;
    String userData;
    private Button logout;
    private Button man2;
    private Button Profit;
    private Button RiskB;
    private Button Inff;
    private Button prot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        logout = findViewById(R.id.logout);
        man2 = findViewById(R.id.man2);
        Profit = findViewById(R.id.Profit);
        nameshow = findViewById(R.id.nameshow);
        RiskB = findViewById(R.id.RiskA);
        Inff = findViewById(R.id.Inf);
        prot = findViewById(R.id.Protect);
        Intent i = getIntent();
        userData = i.getExtras().getString("userData","");
        nameshow.setText("Xin chào, "+userData);

        man2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Man2Intent = new Intent(getApplicationContext(), MainActivity2.class);
                Man2Intent.putExtra("userData", userData);
                startActivity(Man2Intent);
            }
        });

        Profit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PIntent = new Intent(getApplicationContext(), Profit.class);
                PIntent.putExtra("userData", userData);
                startActivity(PIntent);
            }
        });

        RiskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RIntent = new Intent(getApplicationContext(), RiskAssessment.class);
                RIntent.putExtra("userData", userData);
                startActivity(RIntent);
            }
        });
        Inff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent InfIntent = new Intent(getApplicationContext(), InformationCovid.class);
                InfIntent.putExtra("userData", userData);
                startActivity(InfIntent);
            }
        });
        prot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent PrtfIntent = new Intent(getApplicationContext(), Advice.class);
                PrtfIntent.putExtra("userData", userData);
                startActivity(PrtfIntent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }
}