package com.intecap.carrosapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

public class universalConection extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "car.db";
    public static final String TABLE_CAR = "t_car";

    public universalConection(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +TABLE_CAR+"(" +
                "idCar INTEGER PRIMARY KEY AUTOINCREMENT," +
                "marca TEXT NOT NULL," +
                "linea TEXT NOT NULL," +
                "tipo TEXT NOT NULL," +
                "transmision TEXT NOT NULL," +
                "modelo TEXT NOT NULL," +
                "km TEXT NOT NULL," +
                "traccion TEXT NOT NULL," +
                "combustible TEXT NOT NULL," +
                "color TEXT NOT NULL," +
                "precio TEXT NOT NULL," +
                "cantidadpuertas TEXT NOT NULL," +
                "foto TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE "+TABLE_CAR);
        onCreate(db);
    }
}
