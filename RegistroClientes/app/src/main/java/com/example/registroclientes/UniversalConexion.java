package com.example.registroclientes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class UniversalConexion extends SQLiteOpenHelper {
    //Parametros escenciales que deberan usar SQLite open helper
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NOMBRE = "cliente.db";
    public static final String TABLE_CLIENTES = "t_clientes";
    //Contructor, solo se brindaran la pantalla cuando sea llamada esta clase y que debemos eliminar
    //los parametros adicionales
    public UniversalConexion(@Nullable Context context) {
        //Remplazar los nombres de las variables en los parametros del super
        super(context, DATABASE_NOMBRE, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+TABLE_CLIENTES+"(" +
                "idCliente INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombres TEXT NOT NULL," +
                "pais TEXT NOT NULL," +
                "empresa TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Se necesita Eliminar la tabla cuando haya una actualización en la APP
        db.execSQL("DROP TABLE "+TABLE_CLIENTES);
        onCreate(db);

    }
}
