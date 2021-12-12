package com.example.categoriadb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class verCategoriaIndividual extends AppCompatActivity {
    EditText NombreCategoria, descripcion, ubicacion;
    metodosCategoria categoriaIndividualActual;
    FloatingActionButton btnEditarCategoria, btnEliminaCategoria;
    int id = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_categoria_individual);
        NombreCategoria = findViewById(R.id.vistaCampoNombreCategoria);
        descripcion = findViewById(R.id.vistaCampoDescripcion);
        ubicacion = findViewById(R.id.vistaCampoUbicacion);
        btnEditarCategoria = findViewById(R.id.btnEditaCategoria);
        btnEliminaCategoria = findViewById(R.id.btnEliminar);
        if (savedInstanceState==null)
        {
            Bundle datosAdicionales = getIntent().getExtras();
            if (datosAdicionales==null)
            {
                //convirtiendo a NULL a INT .... zzz no se para que XD
                id=Integer.parseInt(null);
            }
            else{
                //En este punto se ejecuta para una versi{on reciente de Android
                //Cuando ingrese al else, se valida que existan valores que se le asignan al ID
                id = datosAdicionales.getInt("codigoCategoria");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("codigoCategoria");
        }
        accionesCategoria cateIndividual = new accionesCategoria((verCategoriaIndividual.this));
        categoriaIndividualActual = cateIndividual.verCategoria(id);

        if (categoriaIndividualActual!=null)
        {
            NombreCategoria.setText(categoriaIndividualActual.getNombreCategoria());
            descripcion.setText(categoriaIndividualActual.getDescripcion());
            ubicacion.setText(categoriaIndividualActual.getUbicacion());
            //Inhabilitar el teclado
            NombreCategoria.setInputType(InputType.TYPE_NULL);
            descripcion.setInputType(InputType.TYPE_NULL);
            ubicacion.setInputType(InputType.TYPE_NULL);
        }else{
            Toast.makeText(verCategoriaIndividual.this, "Error al cargar la info", Toast.LENGTH_SHORT).show();
        }
        btnEditarCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(verCategoriaIndividual.this, editaCategoria.class);
                i.putExtra("codigoCategoria", id);
                startActivity(i);
            }
        });
        btnEliminaCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(verCategoriaIndividual.this);
                mensaje.setMessage("Desea eliminar la categoria? ")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (cateIndividual.eliminarCategoria(id)){
                                    Toast.makeText(verCategoriaIndividual.this, "Categoria Eliminada con éxito", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(verCategoriaIndividual.this, MainActivity.class);
                                    startActivity(i);
                                }
                            }
                        })
                        .setNegativeButton("Denegar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
            }
        });
    }
    public void irMain(View view)
    {
        Intent i = new Intent(verCategoriaIndividual.this, MainActivity.class);
        startActivity(i);
    }
}