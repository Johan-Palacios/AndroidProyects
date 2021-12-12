package com.example.categoriadb;

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
    RecyclerView listaCategoria;
    ArrayList<metodosCategoria> listaArrayCategoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCategoria = findViewById(R.id.listadoCategoria);
        listaCategoria.setLayoutManager(new LinearLayoutManager(this));
        accionesCategoria informacionCategoria = new accionesCategoria(this);
        listaArrayCategoria = new ArrayList<>();
        adaptadorCategoria adaptador = new adaptadorCategoria(informacionCategoria.visualizarCliente());
        listaCategoria.setAdapter(adaptador);
    }
    public void crearBD(View v){
        UniversalConexion bdLocal = new UniversalConexion(MainActivity.this);
        SQLiteDatabase bd = bdLocal.getWritableDatabase();
        if (bd != null){
            Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Error al crear", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
    }

    public void irFormulario(View v)
    {
        Intent i = new Intent(MainActivity.this, formCategoria.class);
        startActivity(i);
    }

}
