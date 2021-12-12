package com.example.registroclientes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class accionesClientes extends UniversalConexion {
    Context contextFormulario;

    public accionesClientes(@Nullable Context context) {
        super(context);
        //Cuando la clase sea llamada se solicitara el contexto
        this.contextFormulario = context;
    }

    //Crear metodo de insertar para el cliente
    public long insertarCliente(String nombres, String pais, String empresa) {
        //Creando variable de tipo long para resguardar el ID recopilado por SQLite
        long id = 0;
        try {
            //Espacio para estabalecer la comunicacion con base de datos
            UniversalConexion comunicacionConectaCliente = new UniversalConexion(contextFormulario);
            //Nos permitira hacer la conexion con la tabla
            SQLiteDatabase accionInsertaCliente = comunicacionConectaCliente.getWritableDatabase();
            //Creando variable de tipo valores que permite agrupar correctamente los parametros enviados desde el formulario

            ContentValues valores = new ContentValues();
            valores.put("nombres", nombres);
            valores.put("pais", pais);
            valores.put("empresa", empresa);

            //Se envia los datos a la tabla, se ejecuta el insert
            id = accionInsertaCliente.insert(TABLE_CLIENTES, null, valores);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    //Creando metodo para visualizar
    public ArrayList<metodosCliente> visualizarCliente() {
        UniversalConexion comunicacionVisualizaCliente = new UniversalConexion(contextFormulario);
        SQLiteDatabase accionVisualizaCliente = comunicacionVisualizaCliente.getWritableDatabase();
        //Se necesita mostrar los datos temporalmente y se hara uso de este arreglo
        ArrayList<metodosCliente> listarClientes = new ArrayList<>();
        //Se necesita acceder a los getter an setter por eso debemos crear un espacio, de tipo metodos cliente
        metodosCliente obtenerInfoCliente = null;
        Cursor cursoClientes = accionVisualizaCliente.rawQuery("SELECT * FROM " + TABLE_CLIENTES, null);
        if (cursoClientes.moveToFirst()) {
            do {
                obtenerInfoCliente = new metodosCliente();
                obtenerInfoCliente.setId(cursoClientes.getInt(0));
                obtenerInfoCliente.setNombres(cursoClientes.getString(1));
                obtenerInfoCliente.setPais(cursoClientes.getString(2));
                obtenerInfoCliente.setEmpresa(cursoClientes.getString(3));
                listarClientes.add(obtenerInfoCliente);
            }
            while (cursoClientes.moveToNext());//Estableciendo condicion para el ciclo
            return listarClientes;
        }
        cursoClientes.close();
        /*
        este proceso de arreglo corresponde por la recycled view
         */
        return listarClientes;
    }

    //Creando metodo select individual
    //Ver cliente individual
    public metodosCliente verCliente(int idCliente) {
        UniversalConexion comunicacionVisualizaCliente = new UniversalConexion(contextFormulario);
        SQLiteDatabase accionVisualizaCliente = comunicacionVisualizaCliente.getWritableDatabase();
        //Se necesita mostrar los datos temporalmente y se hara uso de este arreglo
        //Se necesita acceder a los getter an setter por eso debemos crear un espacio, de tipo metodos cliente
        metodosCliente obtenerInfoCliente = null;
        Cursor cursoClientes = accionVisualizaCliente.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " WHERE idCliente='" +idCliente+"'", null);
        if (cursoClientes.moveToFirst()) {
                obtenerInfoCliente = new metodosCliente();
                obtenerInfoCliente.setId(cursoClientes.getInt(0));
                obtenerInfoCliente.setNombres(cursoClientes.getString(1));
                obtenerInfoCliente.setPais(cursoClientes.getString(2));
                obtenerInfoCliente.setEmpresa(cursoClientes.getString(3));
        }
        cursoClientes.close();
        return obtenerInfoCliente;
    }
    public boolean actualizaCliente(int idCliente, String nombres, String pais, String empresa){
        //Variable que permite validar el estado del QUERY
        boolean actualizacionCorrecta = false;
        UniversalConexion comunicacionEditaCliente = new UniversalConexion(contextFormulario);
        SQLiteDatabase accionEditarCliente = comunicacionEditaCliente.getWritableDatabase();
        try
        {
            //Preparamos Instruccion SQL
            accionEditarCliente.execSQL("UPDATE "+TABLE_CLIENTES+" SET nombres='"+nombres+"', pais='"+pais+"', empresa='"+empresa+"' WHERE idCliente="+idCliente);
            //Se cambia el estado
            actualizacionCorrecta=true;
        }
        catch(Exception ex)
        {
            ex.toString();
            actualizacionCorrecta= false;
        }
        finally {
            comunicacionEditaCliente.close();
        }
        return actualizacionCorrecta;

    }
    //Clonar el metodo actualizaCliente
    public boolean eliminaCliente(int idCliente){
        //Variable que permite validar el estado del QUERY
        boolean eliminacionCorrecta = false;
        UniversalConexion comunicacionEliminaCliente = new UniversalConexion(contextFormulario);
        SQLiteDatabase accionEliminaCliente = comunicacionEliminaCliente.getWritableDatabase();
        try
        {
            //Preparamos Instruccion SQL
            accionEliminaCliente.execSQL("DELETE FROM "+TABLE_CLIENTES+" WHERE idCliente="+idCliente);
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