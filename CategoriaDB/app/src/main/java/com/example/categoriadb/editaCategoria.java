package com.example.categoriadb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editaCategoria extends AppCompatActivity {
    EditText NombreCategoria, descripcion, ubicacion;
    metodosCategoria categoriaIndividualActual;
    int id = 0;
    Button btnActualizaCategoria;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_categoria_individual);
        NombreCategoria = findViewById(R.id.vistaCampoNombreCategoria);
        descripcion = findViewById(R.id.vistaCampoDescripcion);
        ubicacion = findViewById(R.id.vistaCampoUbicacion);
        btnActualizaCategoria = findViewById(R.id.btnActualizarCategoria);
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
        accionesCategoria cateIndividual = new accionesCategoria((editaCategoria.this));
        categoriaIndividualActual = cateIndividual.verCategoria(id);

        if (categoriaIndividualActual!=null)
        {
            NombreCategoria.setText(categoriaIndividualActual.getNombreCategoria());
            descripcion.setText(categoriaIndividualActual.getDescripcion());
            ubicacion.setText(categoriaIndividualActual.getUbicacion());
        }else{
            Toast.makeText(editaCategoria.this, "Error al cargar la info", Toast.LENGTH_SHORT).show();
        }
        btnActualizaCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!NombreCategoria.getText().toString().equals("") && !descripcion.getText().toString().equals("") && !ubicacion.getText().toString().equals(""))
                {
                    boolean respuesta = cateIndividual.actualizaCliente(id, NombreCategoria.getText().toString(), descripcion.getText().toString(), ubicacion.getText().toString());
                    if (respuesta)
                    {
                        Toast.makeText(editaCategoria.this, "Actualizado Correctamente", Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(editaCategoria.this, "Error, porfavor intente de nuevo", Toast.LENGTH_LONG).show();
                    }
                    Intent i = new Intent(editaCategoria.this, verCategoriaIndividual.class);
                    i.putExtra("codigoCategoria", id);
                    startActivity(i);
                }
            }
        });
    }

    public void irMain(View view)
    {
        Intent i = new Intent(editaCategoria.this, MainActivity.class);
        startActivity(i);
    }
}
