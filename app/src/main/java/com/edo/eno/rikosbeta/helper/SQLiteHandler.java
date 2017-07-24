package com.edo.eno.rikosbeta.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.HashMap;

/**
 * Created by Edo Firmansyah on 14/03/2017.
 */

public class SQLiteHandler extends SQLiteOpenHelper {

    private static final String TAG = SQLiteHandler.class.getSimpleName();

    //All Static variables
    //Database Version
    private static final int DATABASE_VERSION = 2;

    //DAtabase Name
    private static final String DATABASE_NAME = "rikos";

    //Nama tabel login
    private static final String TABLE_USER = "user";

    //Nama Kolom tabel
    private static final String KEY_ID = "id";
    private static final String KEY_FULLNAME = "fullname";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_UID = "uid";
    private static final String KEY_CREATED_AT = "created_at";

    public SQLiteHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //Membuat tabel

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOGIN_TABLE = "CREATE TABLE " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_FULLNAME + " TEXT,"
                + KEY_USERNAME + " TEXT," + KEY_EMAIL + " TEXT UNIQUE,"
                + KEY_UID + " TEXT," + KEY_CREATED_AT + " TEXT" + ");";
        db.execSQL(CREATE_LOGIN_TABLE);

        Log.d(TAG, "Database berhasil dibuat");
    }

    //upgrade database

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Drop tabel lama jika ada
        db.execSQL("DROP TABLE IF EXIST " + TABLE_USER);

        //Membuat tabel lagi
        onCreate(db);
    }

    //Setor data user di database
    public void addUser(String fullname, String username, String email, String uid, String created_at) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new  ContentValues();
        values.put(KEY_FULLNAME, fullname); //nama
        values.put(KEY_USERNAME, username); // username
        values.put(KEY_EMAIL, email); //email
        values.put(KEY_UID, uid); //uid
        values.put(KEY_CREATED_AT, created_at); //created at

        //insert row
        long id = db.insert(TABLE_USER, null, values);
        db.close(); //close db connection

        Log.d(TAG, "User baru ditambahkan ke sqlite: " + id);
    }

    //ambil data dari database
    public HashMap<String, String> getUserDetail() {
        HashMap<String, String> user = new HashMap<String, String>();
        String selectQuery = "SELECT * FROM " + TABLE_USER;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        //move to first row
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            user.put("fullname", cursor.getString(1));
            user.put("username", cursor.getString(2));
            user.put("email", cursor.getString(3));
            user.put("uid", cursor.getString(4));
            user.put("created_at", cursor.getString(5));
        }
        cursor.close();
        db.close();
        //return user
        Log.d(TAG, "Fetching user dari sqlite: " + user.toString());

        return user;
    }

    //delete tabel dan create lagi
    public void deleteUsers() {
        SQLiteDatabase db = this.getWritableDatabase();
        //hapus semua row
        db.delete(TABLE_USER, null, null);
        db.close();

        Log.d(TAG, "Hapus semua user info dari sqlite");
    }


}
