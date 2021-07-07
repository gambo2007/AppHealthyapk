package org.o7planning.healthcareproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class UpdateProfit extends AppCompatActivity {
    private EditText name;
    private RadioGroup RGD;
    private RadioButton rbB;
    private RadioButton rbG;
    private EditText ID;
    private EditText birthdate;
    private EditText QT;
    private EditText address;
    private EditText email;
    private EditText phone;
    private Button submit;
    String userData;
    DatabaseHandle databaseHandle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profit);
        Intent i = getIntent();
        userData = i.getExtras().getString("userData","");
        name = findViewById(R.id.txtName2);
        RGD = findViewById(R.id.RDG);
        rbB = findViewById(R.id.rdB);
        rbG = findViewById(R.id.rdB2);
        ID = findViewById(R.id.txtID);
        birthdate = findViewById(R.id.txtDate);
        QT = findViewById(R.id.txtQT);
        address = findViewById(R.id.txtAddress);
        email = findViewById(R.id.txtEmail);
        phone = findViewById(R.id.txtPhone);
        submit = findViewById(R.id.submit);
        databaseHandle = new DatabaseHandle(UpdateProfit.this);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (databaseHandle.addInfo(userData, name.getText().toString(), Integer.parseInt(ID.getText().toString()), doonsex(), birthdate.getText().toString(), QT.getText().toString(), address.getText().toString(), email.getText().toString(), Integer.parseInt(phone.getText().toString()))) {
                    name.setText("");
                    RGD.clearCheck();
                    ID.setText("");
                    birthdate.setText("");
                    QT.setText("");
                    address.setText("");
                    email.setText("");
                    phone.setText("");
                    showdialog();
                } else {
                    Toast.makeText(getApplicationContext(), "Cập nhật thất bại", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    public String doonsex() {
        int sex = RGD.getCheckedRadioButtonId();
        RadioButton radioButtonSex = (RadioButton) this.findViewById(sex);
        String sxe = radioButtonSex.getText().toString();
        return sxe;
    }
    public void showdialog() {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(UpdateProfit.this);
        builder1.setMessage("Cập nhật thành công.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });

        builder1.setNegativeButton(
                "Cancel",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }
}