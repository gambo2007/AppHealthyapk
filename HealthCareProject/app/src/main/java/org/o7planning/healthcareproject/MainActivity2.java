package org.o7planning.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity2 extends AppCompatActivity {

    ImageView flags;
    Spinner spinner;
    Button stat;
    Button stat2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_2);

        spinner = findViewById(R.id.spinner);
        flags = findViewById(R.id.flag);
        stat = findViewById(R.id.stat);
        stat2 = findViewById(R.id.stat2);


        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                CountryData.countryNames));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                flags.setImageResource(CountryData.countryFlag[spinner.getSelectedItemPosition()]);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        stat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity2.this,CountryPage.class);
                startActivity(i);

            }
        });

        stat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity2.this,GlobalPage.class);
                startActivity(i);

            }
        });


    }
}
