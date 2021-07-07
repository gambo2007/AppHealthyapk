package org.o7planning.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class Registrer extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private EditText repass;
    private Button register;
    private  Button turnback;
    DatabaseHandle databaseHandle;
    ArrayList arrayList;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrer);
        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        repass = findViewById(R.id.password2);
        register = findViewById(R.id.registry);
        turnback = findViewById(R.id.imageView);
        databaseHandle = new DatabaseHandle(Registrer.this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us = username.getText().toString();
                String pa = password.getText().toString();
                String pa1 = repass.getText().toString();
                if (!us.isEmpty()&&!pa.isEmpty()&&pa.equals(pa1)){
                    if (databaseHandle.addUser(us, pa)){
                        username.setText("");
                        password.setText("");
                        repass.setText("");
                        Toast.makeText(getApplicationContext(), "Đăng ký thành công", Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Tên đăng nhập đã được người khắc sử dụng", Toast.LENGTH_LONG).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
        turnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}