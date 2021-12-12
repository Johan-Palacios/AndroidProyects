package com.example.proveedores;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView listaCliente;
    ArrayList<metodosCliente> listaArrayClientes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCliente = findViewById(R.id.listadoClientes);
        listaCliente.setLayoutManager(new LinearLayoutManager(this));
        accionesClientes informacionCliente = new accionesClientes(this);
        listaArrayClientes = new ArrayList<>();
        adaptadorClientes adaptador = new adaptadorClientes(informacionCliente.visualizarCliente());
        listaCliente.setAdapter(adaptador);
    }
    public void crearBD(View v)
    {
        UniversalConexion bdLocal = new UniversalConexion(MainActivity.this);
        SQLiteDatabase bd = bdLocal.getWritableDatabase();
        if (bd != null){
            Toast.makeText(MainActivity.this, "Base de datos creada con éxito", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    public void irRegistrar(View v)
    {
        Intent i = new Intent(MainActivity.this, formClientes.class);
        startActivity(i);
    }
}