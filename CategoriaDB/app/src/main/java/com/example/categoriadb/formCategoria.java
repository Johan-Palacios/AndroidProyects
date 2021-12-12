package com.example.categoriadb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class formCategoria extends AppCompatActivity {
    EditText NombreCategoria, Descripcion, Ubicacion;
    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_categoria);
        NombreCategoria = findViewById(R.id.campoNombreCategoria);
        Descripcion = findViewById(R.id.campoDescripcion);
        Ubicacion = findViewById(R.id.campoUbicacion);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accionesCategoria datosCliente = new accionesCategoria(formCategoria.this);

                //Acediendo a variables que nos permitan acceder al metodo insert
                //procediendo a llamar el metodo de inmediato,  realizando la lectura de los campos
                
                //del contenido de los campos y se los enviamos como valores al metodo
                long ultimoCliente = datosCliente.insertarCategoria(NombreCategoria.getText().toString(),Descripcion.getText().toString(), Ubicacion.getText().toString());
                //evaluando el valor de la variable ultimo cliente
                if (ultimoCliente>0){
                    Toast.makeText(formCategoria.this, "Cliente registrado con éxito", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(formCategoria.this, MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(formCategoria.this, "Error de registro", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(formCategoria.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}