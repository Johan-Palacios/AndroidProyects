package com.intecap.carrosapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class actionCar extends universalConection{
    Context contextFormulario;

    public actionCar(@Nullable Context context) {
        super(context);
        this.contextFormulario = context;
    }
    public long insertCar(String marca, String linea, String tipo, String transmision, String modelo, String km, String traccion,String combustible, String color, String precio, String puertas, String picturepath)
    {
        long id = 0;

        try {
            //Espacio para estabalecer la comunicacion con base de datos
           universalConection comunicacionConectaCliente = new universalConection(contextFormulario);
            SQLiteDatabase accionInsertaCliente = comunicacionConectaCliente.getWritableDatabase();
            ContentValues valores = new ContentValues();
            valores.put("marca",marca);
            valores.put("linea",linea);
            valores.put("tipo",tipo);
            valores.put("transmision",transmision);
            valores.put("modelo",modelo);
            valores.put("km",km);
            valores.put("traccion",traccion);
            valores.put("combustible",combustible);
            valores.put("color",color);
            valores.put("precio",precio);
            valores.put("cantidadpuertas",puertas);
            valores.put("foto",picturepath);
            id = accionInsertaCliente.insert(TABLE_CAR, null, valores);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }


    public ArrayList<methodCar> visualizarCliente() {

        universalConection comunicacionVisualizaCar = new universalConection(contextFormulario);

        SQLiteDatabase accionVisualizaCar = comunicacionVisualizaCar.getWritableDatabase();

        ArrayList<methodCar> listarCar = new ArrayList<>();

        methodCar obtenerInfoCar = null;

        Cursor cursoCar = accionVisualizaCar.rawQuery("SELECT * FROM " + TABLE_CAR, null);

        if (cursoCar.moveToFirst()) {
            do {
                obtenerInfoCar = new methodCar();
                obtenerInfoCar.setId(cursoCar.getInt(0));
                obtenerInfoCar.setMarca(cursoCar.getString(1));
                obtenerInfoCar.setLinea(cursoCar.getString(2));
                obtenerInfoCar.setModelo(cursoCar.getString(5));
                obtenerInfoCar.setFoto(cursoCar.getString(12));
                listarCar.add(obtenerInfoCar);
            }
            while (cursoCar.moveToNext());//Estableciendo condicion para el ciclo
            return listarCar;
        }
        cursoCar.close();
        /*
        este proceso de arreglo corresponde por la recycled view
         */
        return listarCar;
    }

    //Creando metodo select individual
    //Ver cliente individual
    public methodCar verCliente(int idCliente) {
        universalConection comunicacionVisualizaCar = new universalConection(contextFormulario);
        SQLiteDatabase accionVisualizaCliente = comunicacionVisualizaCar.getWritableDatabase();
        //Se necesita mostrar los datos temporalmente y se hara uso de este arreglo
        //Se necesita acceder a los getter an setter por eso debemos crear un espacio, de tipo metodos cliente
        methodCar obtenerInfoCar = null;
        Cursor cursoCar = accionVisualizaCliente.rawQuery("SELECT * FROM " + TABLE_CAR + " WHERE idCar='" +idCliente+"'", null);
        if (cursoCar.moveToFirst()) {
            obtenerInfoCar = new methodCar();
            obtenerInfoCar.setId(cursoCar.getInt(0));
            obtenerInfoCar.setMarca(cursoCar.getString(1));
            obtenerInfoCar.setLinea(cursoCar.getString(2));
            obtenerInfoCar.setTipo(cursoCar.getString(3));
            obtenerInfoCar.setTransmision(cursoCar.getString(4));
            obtenerInfoCar.setModelo(cursoCar.getString(5));
            obtenerInfoCar.setKm(cursoCar.getString(6));
            obtenerInfoCar.setTraccion(cursoCar.getString(7));
            obtenerInfoCar.setCombustible(cursoCar.getString(8));
            obtenerInfoCar.setColor(cursoCar.getString(9));
            obtenerInfoCar.setPrecio(cursoCar.getString(10));
            obtenerInfoCar.setCantidad_puertas(cursoCar.getString(11));
            obtenerInfoCar.setFoto(cursoCar.getString(12));
        }
        cursoCar.close();
        return obtenerInfoCar;
    }
    public boolean actualizaCliente(int idCar, String marca, String linea, String tipo, String transmision, String modelo, String km, String traccion,String combustible, String color, String precio, String puertas){
        //Variable que permite validar el estado del QUERY
        boolean actualizacionCorrecta = false;
        universalConection comunicacionEditaCar = new universalConection(contextFormulario);
        SQLiteDatabase accionEditarCliente = comunicacionEditaCar.getWritableDatabase();
        try
        {
            accionEditarCliente.execSQL("UPDATE "+TABLE_CAR+" SET marca='"+marca+"', linea='"+linea+"', tipo='"+tipo+"', transmision='"+transmision+"', modelo='"+modelo+"', km='"+km+"', traccion='"+traccion+"', combustible='"+combustible+"', color='"+color+"', precio='"+precio+"', cantidadpuertas='"+puertas+"' WHERE idCar="+idCar);
            actualizacionCorrecta=true;
        }
        catch(Exception ex)
        {
            ex.toString();
            actualizacionCorrecta= false;
        }
        finally {
            comunicacionEditaCar.close();
        }
        return actualizacionCorrecta;

    }
    //Clonar el metodo actualizaCliente
    public boolean eliminaCliente(int idCar){
        //Variable que permite validar el estado del QUERY
        boolean eliminacionCorrecta = false;
        universalConection comunicacionEliminaCliente = new universalConection(contextFormulario);
        SQLiteDatabase accionEliminaCliente = comunicacionEliminaCliente.getWritableDatabase();
        try
        {
            //Preparamos Instruccion SQL
            accionEliminaCliente.execSQL("DELETE FROM "+TABLE_CAR+" WHERE idCar="+idCar);
            //Se cambia el estado
            eliminacionCorrecta=true;
        }
        catch(Exception ex)
        {
            ex.toString();
            eliminacionCorrecta= false;
        }
        finally {
            comunicacionEliminaCliente.close();
        }
        return eliminacionCorrecta;
    }
}
