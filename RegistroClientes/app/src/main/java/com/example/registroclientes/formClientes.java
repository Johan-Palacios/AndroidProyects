package com.example.registroclientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class formClientes extends AppCompatActivity {
    EditText campoNombre, campoPais, campoEmpresa;
    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_clientes);
        campoNombre = findViewById(R.id.campoNombres);
        campoPais = findViewById(R.id.campoPais);
        campoEmpresa = findViewById(R.id.campoEmpresa);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accionesClientes datosCliente = new accionesClientes(formClientes.this);
                //Acediendo a variables que nos permitan acceder al metodo insert
                //procediendo a llamar el metodo de inmediato,  realizando la lectura de los campos
                //del contenido de los campos y se los enviamos como valores al metodo
                long ultimoCliente = datosCliente.insertarCliente(campoNombre.getText().toString(),campoPais.getText().toString(), campoEmpresa.getText().toString());
                //evaluando el valor de la variable ultimo cliente
                if (ultimoCliente>0){
                    Toast.makeText(formClientes.this, "Cliente registrado con éxito", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(formClientes.this, MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(formClientes.this, "Error de registro", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(formClientes.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
}