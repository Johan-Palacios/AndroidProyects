package com.intecap.resguardarfoto;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class actionPhoto extends universalConection{
    Context screenPhoto;
    public actionPhoto(@Nullable Context context) {
        super(context);
        this.screenPhoto = context;
    }
    public long insertPhoto(String ubicacion)
    {
        long correlativo = 0;
        try {
            universalConection comunicationInsertPhoto = new universalConection(screenPhoto);
            SQLiteDatabase actionInsertPhoto = comunicationInsertPhoto.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ubicacion", ubicacion);
            actionInsertPhoto.insert(TABLE_PHOTO, null, values);
        }
        catch (Exception ex)
        {
            ex.toString();
        }
        return correlativo;
    }
}
