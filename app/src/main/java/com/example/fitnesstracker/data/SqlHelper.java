package com.example.fitnesstracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SqlHelper extends SQLiteOpenHelper {

    //db
    public static final String DB_NAME = "fitness_tracker.db";
    public static final int DB_VERSION = 1;

    //table
    public static final String TABLE_NAME = "calc";


    private static SqlHelper INSTANCE;

    public static SqlHelper getInstance(Context contex) {
        if (INSTANCE == null)
            INSTANCE = new SqlHelper(contex);
        return INSTANCE;
    }


    private SqlHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABLE_NAME + " (id INTEGER primary key, type_calc TEXT, res DECIMAL, create_date DATETIME)";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    }

    public long addItem(String type, double res) {

        SQLiteDatabase db = getWritableDatabase();

        long calcId = 0;

        try {
            db.beginTransaction();

            ContentValues values = new ContentValues();

            values.put("type_calc", type);
            values.put("res", res);

            SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd HH:mm:ss", new Locale("pt", "BR"));

            String hour = sdf.format(new Date());

            values.put("create_date", hour);

            calcId = db.insertOrThrow(TABLE_NAME, null, values);
            db.setTransactionSuccessful();

        } catch (Exception e) {

            Log.e("Sqlite", "Error: " + e.getMessage(), e);

        } finally {
            if (db.isOpen())
                db.endTransaction();

        }

        return calcId;
    }


}
