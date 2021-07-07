package org.o7planning.healthcareproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Base64;

import java.security.MessageDigest;
import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class DatabaseHandle extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Final_project";
    private static final String TABLE_NAME = "User_info";
    private static  final String TABLE_NAME1 = "Private_info";
    String AES = "AES";
    String PasswordENC = "ENCRYPTEDPASSWORD";
    String encpass = "";
    long check = 0;

    DatabaseHandle(Context context) {
        super(context, DATABASE_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + TABLE_NAME + "(username TEXT PRIMARY KEY, password TEXT)");
        sqLiteDatabase.execSQL("create table " + TABLE_NAME1 + "(username TEXT PRIMARY KEY, name TEXT, id INT, gender TEXT, bd TEXT, region TEXT, address TEXT, email TEXT, phone INT)");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME1 + " values('thinh0122', 'nguyen truong anh thinh', '28132', 'nam', 0608, 'vietnam', 'dskjafoisdf', 'sdosdjfosdf', 0362645245 );");
        sqLiteDatabase.execSQL("insert into " + TABLE_NAME1 + " values('thinh', 'nguyen truong anh thinh', '28132', 'nam', 0608, 'vietnam', 'dskjafoisdf', 'sdosdjfosdf', 0362645245 );");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
        onCreate(sqLiteDatabase);
    }

    public boolean addUser(String text1, String text2) {
        try{
            encpass = encrypt(text2, PasswordENC);
        } catch (Exception e) {e.printStackTrace();}
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", text1);
        contentValues.put("password", encpass);
        check = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if(check==-1){
            return false;
        }
        return true;
    }

    public boolean addInfo(String text1, String text2, int text3, String text4, String text5, String text6, String text7, String text8, int text9){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", text1);
        contentValues.put("name", text2);
        contentValues.put("id", text3);
        contentValues.put("gender", text4);
        contentValues.put("bd", text5);
        contentValues.put("region", text6);
        contentValues.put("address", text7);
        contentValues.put("email", text8);
        contentValues.put("phone", text9);
        sqLiteDatabase.insert(TABLE_NAME1, null, contentValues);
        return true;
    }

    public ArrayList getAllText() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        ArrayList<String> arrayList = new ArrayList<String>();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from " + TABLE_NAME, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            arrayList.add(cursor.getString(cursor.getColumnIndex("username")));
            cursor.moveToNext();
        }
        return arrayList;
    }

    public boolean Login(String username, String password) {
        try{
            password = encrypt(password, PasswordENC);
        } catch (Exception e) {e.printStackTrace();}
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor mCursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE username=? AND password=?", new String[]{username, password});
        if (mCursor != null) {
            if (mCursor.getCount() > 0) {
                return true;
            }
        }
        return false;
    }
    private String decrypt (String Data, String password) throws Exception{
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.decode(Data, Base64.DEFAULT);
        byte[] decValue = c.doFinal(decodedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }
    private String encrypt(String Data, String password) throws Exception {
        SecretKeySpec key = generateKey(password);
        Cipher c = Cipher.getInstance(AES);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        String encryptedValue = Base64.encodeToString(encVal, Base64.DEFAULT);
        return encryptedValue;
    }

    private SecretKeySpec generateKey(String password) throws Exception {
        final MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = password.getBytes("UTF-8");
        digest.update(bytes, 0, bytes.length);
        byte[] key = digest.digest();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        return secretKeySpec;
    }
}
