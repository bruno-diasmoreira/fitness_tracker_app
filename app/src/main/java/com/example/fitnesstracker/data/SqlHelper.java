package com.example.fitnesstracker.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.fitnesstracker.model.Register;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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


    public List<Register> getListRegisterBy(String type) {

        List<Register> registers = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE type_calc = ?", new String[]{type});

        try {

            if (cursor.moveToFirst()) {
                do {
                    Register register = new Register();

                    register.setType(cursor.getString(1));
                    register.setResult(cursor.getDouble(2));
                    register.setCreated_date(cursor.getString(3));
                    //register.setType(cursor.getString(cursor.getColumnIndex("type_calc")));
                    //register.setCreated_date(cursor.getString(cursor.getColumnIndex("created_date")));
                    //register.setResult(cursor.getDouble(cursor.getColumnIndex("res")));

                    registers.add(register);

                } while (cursor.moveToNext());
            }

        } catch (Exception e) {

            Log.e("error", "getListRegisterBy: " + e.getMessage());

        } finally {
            if (cursor != null && !cursor.isClosed())
                cursor.close();
        }


        return registers;
    }


    public long addItem(String type, double res) {

        SQLiteDatabase db = getWritableDatabase();

        long calcId = 0;

        try {
            db.beginTransaction();

            ContentValues values = new ContentValues();

            values.put("type_calc", type);
            values.put("res", res);

            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy HH:mm:ss", new Locale("pt", "BR"));

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
