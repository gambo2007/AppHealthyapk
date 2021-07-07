package org.o7planning.healthcareproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button register;
    private Button login;
    String user;
    String pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        login = findViewById(R.id.login);
        register.setPaintFlags(register.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = username.getText().toString();
                pass = password.getText().toString();
                try{
                    if(username.length() > 0 && password.length() >0)
                    {
                        DatabaseHandle databaseHandle = new DatabaseHandle(MainActivity.this);
                        databaseHandle.getAllText();

                        if(databaseHandle.Login(user, pass))
                        {
                            username.setText("");
                            password.setText("");
                            homepageintent();
                        }else{
                            Toast.makeText(MainActivity.this,"Sai tài khoản/mật khẩu", Toast.LENGTH_LONG).show();
                        }
                        databaseHandle.close();
                    }

                }catch(Exception e)
                {
                    Toast.makeText(MainActivity.this,e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerintent();
            }
        });
    }
    public void homepageintent(){
        Intent HomeIntent = new Intent(this, HomePage.class);
        HomeIntent.putExtra("userData", user);
        startActivity(HomeIntent);
    }
    public void registerintent(){
        Intent RegisterIntent = new Intent(this, Registrer.class);
        startActivity(RegisterIntent);
    }
}