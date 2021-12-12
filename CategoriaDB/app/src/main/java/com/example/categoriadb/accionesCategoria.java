package com.example.categoriadb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class accionesCategoria extends UniversalConexion{
    Context contextFormulario;
    public accionesCategoria(@Nullable Context context) {
        super(context);
        this.contextFormulario = context;
    }
    public long insertarCategoria(String nombreCategoria, String Descripcion, String Ubicacion)
    {
        long id = 0;
        try {
            UniversalConexion comunicacionConectaCategoria = new UniversalConexion(contextFormulario);
            SQLiteDatabase accionInsertaCategoria = comunicacionConectaCategoria.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("NombreCategoria", nombreCategoria);
            valores.put("Descripcion", Descripcion);
            valores.put("Ubicacion", Ubicacion);
            id = accionInsertaCategoria.insert(TABLE_CATEGORIA, null, valores);
        }
        catch (Exception ex)
        {
            ex.toString();
        }
        return id;
    }
    public ArrayList<metodosCategoria> visualizarCliente()
    {
        UniversalConexion comunicacionVisualizaCategoria = new UniversalConexion(contextFormulario);
        SQLiteDatabase accionVisualizaCategoria = comunicacionVisualizaCategoria.getWritableDatabase();

        ArrayList<metodosCategoria> listarCategoria = new ArrayList<>();
        metodosCategoria obtenerInfoCategoria = null;
        Cursor cursorCategoria = accionVisualizaCategoria.rawQuery("SELECT * FROM "+TABLE_CATEGORIA, null);
        if(cursorCategoria.moveToFirst()){
            do {
                obtenerInfoCategoria = new metodosCategoria();
                obtenerInfoCategoria.setId(cursorCategoria.getInt(0));
                obtenerInfoCategoria.setNombreCategoria(cursorCategoria.getString(1));
                obtenerInfoCategoria.setDescripcion(cursorCategoria.getString(2));
                obtenerInfoCategoria.setUbicacion(cursorCategoria.getString(3));
                listarCategoria.add(obtenerInfoCategoria);
            }while (cursorCategoria.moveToNext());
            return listarCategoria;
        }
        cursorCategoria.close();
        return listarCategoria;
    }
    public metodosCategoria verCategoria(int idCategoria)
    {
        UniversalConexion comunicacionVisualizaCategoria = new UniversalConexion(contextFormulario);
        SQLiteDatabase accionVisualizaCategoria = comunicacionVisualizaCategoria.getWritableDatabase();

        ArrayList<metodosCategoria> listarCategoria = new ArrayList<>();
        metodosCategoria obtenerInfoCategoria = null;
        Cursor cursorCategoria = accionVisualizaCategoria.rawQuery("SELECT * FROM "+TABLE_CATEGORIA+" WHERE idCategoria='" +idCategoria+"'", null);
        if(cursorCategoria.moveToFirst()){
                obtenerInfoCategoria = new metodosCategoria();
                obtenerInfoCategoria.setId(cursorCategoria.getInt(0));
                obtenerInfoCategoria.setNombreCategoria(cursorCategoria.getString(1));
                obtenerInfoCategoria.setDescripcion(cursorCategoria.getString(2));
                obtenerInfoCategoria.setUbicacion(cursorCategoria.getString(3));
        }
        cursorCategoria.close();
        return obtenerInfoCategoria;
    }
    public boolean actualizaCliente(int idCategoria, String NombreCategoria, String Descripcion, String Ubicacion)
    {
        boolean actualicacionCorrecta = false;
        UniversalConexion comunicacionEditaCategoria = new UniversalConexion(contextFormulario);
        SQLiteDatabase accionEditarCliente = comunicacionEditaCategoria.getWritableDatabase();
        try {
            accionEditarCliente.execSQL("UPDATE "+TABLE_CATEGORIA+" SET NombreCategoria='"+NombreCategoria+"', Descripcion='"+Descripcion+"', Ubicacion='"+Ubicacion+"' WHERE idCategoria="+idCategoria);
            actualicacionCorrecta = true;
        }catch (Exception ex)
        {
            ex.toString();
            actualicacionCorrecta = false;
        }
        finally {
            comunicacionEditaCategoria.close();
        }
        return actualicacionCorrecta;
    }
    public boolean eliminarCategoria(int idCategoria)
    {
        boolean eliminacionCorrecta = false;
        UniversalConexion comunicacionEliminaCategoria = new UniversalConexion(contextFormulario);
        SQLiteDatabase accionEliminaCategoria = comunicacionEliminaCategoria.getWritableDatabase();
        try {
            accionEliminaCategoria.execSQL("DELETE FROM "+TABLE_CATEGORIA+" WHERE idCategoria="+idCategoria);
            eliminacionCorrecta = true;
        }catch (Exception ex)
        {
            ex.toString();
            eliminacionCorrecta = false;
        }
        finally {
            comunicacionEliminaCategoria.close();
        }
        return eliminacionCorrecta;
    }
}
