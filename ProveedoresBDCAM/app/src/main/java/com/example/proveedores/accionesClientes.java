package com.example.proveedores;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Region;

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
    public long insertarCliente(String NombreCompania, String NombreContacto, String CargoContacto, String Direccion, String Ciudad, String Region, String CodPostal, String Pais, String Telefono, String Fax, String PaginaPrincipal, String picturePath) {
        //Creando variable de tipo long para resguardar el ID recopilado por SQLite
        long id = 0;
        try {
            //Espacio para estabalecer la comunicacion con base de datos
            UniversalConexion comunicacionConectaCliente = new UniversalConexion(contextFormulario);
            //Nos permitira hacer la conexion con la tabla
            SQLiteDatabase accionInsertaCliente = comunicacionConectaCliente.getWritableDatabase();
            //Creando variable de tipo valores que permite agrupar correctamente los parametros enviados desde el formulario

            ContentValues valores = new ContentValues();
            valores.put("NombreCompania", NombreCompania);
            valores.put("NombreContacto", NombreContacto);
            valores.put("CargoContacto", CargoContacto);
            valores.put("Direccion", Direccion);
            valores.put("Ciudad", Ciudad);
            valores.put("Region", Region);
            valores.put("CodPostal", CodPostal);
            valores.put("Pais", Pais);
            valores.put("Telefono", Telefono);
            valores.put("Fax", Fax);
            valores.put("PaginaPrincipal", PaginaPrincipal);
            valores.put("picturePath", picturePath);
            //Se envia los datos a la tabla, se ejecuta el insert
            id = accionInsertaCliente.insert(TABLE_CLIENTES, null, valores);
        } catch (Exception ex) {
            ex.toString();
        }
        return id;
    }

    public ArrayList<metodosCliente> visualizarCliente() {
        UniversalConexion comunicacionVisualizaCliente = new UniversalConexion(contextFormulario);
        SQLiteDatabase accionVisualizaCliente = comunicacionVisualizaCliente.getWritableDatabase();
        ArrayList<metodosCliente> listarClientes = new ArrayList<>();
        metodosCliente obtenerInfoCliente = null;
        Cursor cursoClientes = accionVisualizaCliente.rawQuery("SELECT * FROM "+TABLE_CLIENTES, null);
        if (cursoClientes.moveToFirst()) {
            do{
                obtenerInfoCliente = new metodosCliente();
                obtenerInfoCliente.setId(cursoClientes.getInt(0));
                obtenerInfoCliente.setNombreCompania(cursoClientes.getString(1));
                obtenerInfoCliente.setNombreContacto(cursoClientes.getString(2));
                obtenerInfoCliente.setCargoContacto(cursoClientes.getString(3));
                obtenerInfoCliente.setPicturePath(cursoClientes.getString(12));
                listarClientes.add(obtenerInfoCliente);
            }while (cursoClientes.moveToNext());
        }
        cursoClientes.close();
        return listarClientes;
    }

    public metodosCliente verCliente(int idCliente) {
        UniversalConexion comunicacionVisualizaCliente = new UniversalConexion(contextFormulario);
        SQLiteDatabase accionVisualizaCliente = comunicacionVisualizaCliente.getWritableDatabase();
        //Se necesita mostrar los datos temporalmente y se hara uso de este arreglo
        //Se necesita acceder a los getter an setter por eso debemos crear un espacio, de tipo metodos cliente
        metodosCliente obtenerInfoCliente = null;
        Cursor cursoClientes = accionVisualizaCliente.rawQuery("SELECT * FROM " + TABLE_CLIENTES + " WHERE idProveedor='" +idCliente+"'", null);
        if (cursoClientes.moveToFirst()) {
            obtenerInfoCliente = new metodosCliente();
            obtenerInfoCliente.setId(cursoClientes.getInt(0));
            obtenerInfoCliente.setNombreCompania(cursoClientes.getString(1));
            obtenerInfoCliente.setNombreContacto(cursoClientes.getString(2));
            obtenerInfoCliente.setCargoContacto(cursoClientes.getString(3));
            obtenerInfoCliente.setDireccion(cursoClientes.getString(4));
            obtenerInfoCliente.setCiudad(cursoClientes.getString(5));
            obtenerInfoCliente.setRegion(cursoClientes.getString(6));
            obtenerInfoCliente.setCodPostal(cursoClientes.getString(7));
            obtenerInfoCliente.setPais(cursoClientes.getString(8));
            obtenerInfoCliente.setTelefono(cursoClientes.getString(9));
            obtenerInfoCliente.setFax(cursoClientes.getString(10));
            obtenerInfoCliente.setPaginaPrincipal(cursoClientes.getString(11));
            obtenerInfoCliente.setPicturePath(cursoClientes.getString(12));
        }
        cursoClientes.close();
        return obtenerInfoCliente;
    }
    public boolean actualizaCliente(int idProveedor, String NombreCompania, String NombreContacto, String CargoContacto, String Direccion, String Ciudad, String Region, String Codpostal, String Pais, String Telefono, String Fax, String PaginaPrincipal)
    {
        boolean actualizacionCorrecta = false;
        UniversalConexion comunicacionEditaCliente = new UniversalConexion(contextFormulario);
        SQLiteDatabase accionEditarCliente = comunicacionEditaCliente.getWritableDatabase();
        try{
            accionEditarCliente.execSQL("UPDATE "+TABLE_CLIENTES+" SET NombreCompania='"+NombreCompania+"', NombreContacto='"+NombreContacto+"', CargoContacto='"+CargoContacto+"', Direccion='"+Direccion+"', Ciudad='"+Ciudad+"', Region='"+Region+"', CodPostal='"+Codpostal+"', Pais='"+Pais+"', Telefono='"+Telefono+"', Fax='"+Fax+"', PaginaPrincipal='"+PaginaPrincipal+"' WHERE idProveedor="+idProveedor);
            actualizacionCorrecta= true;
        }
        catch (Exception ex)
        {
            ex.toString();
            actualizacionCorrecta= false;
        }
        finally {
            comunicacionEditaCliente.close();
        }
        return actualizacionCorrecta;
    }
    public boolean eliminaCliente(int idProveedor)
    {
        boolean eliminacionCorrecta = false;
        UniversalConexion comunicacionEliminaCliente = new UniversalConexion(contextFormulario);
        SQLiteDatabase accionEliminaCliente = comunicacionEliminaCliente.getWritableDatabase();
        try{
            accionEliminaCliente.execSQL("DELETE FROM "+TABLE_CLIENTES+" WHERE idProveedor="+idProveedor);
            eliminacionCorrecta= true;
        }
        catch (Exception ex)
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