package com.example.proveedores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class formClientes extends AppCompatActivity {
    EditText NombreCompania, NombreContacto, CargoContacto, Direccion, Ciudad, Region, CodPostal, Pais, Telefono, Fax, PaginaPrincipal;
    Button btnRegistrar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_clientes);
        NombreCompania = findViewById(R.id.campoNombreCompania);
        NombreContacto = findViewById(R.id.campoNombreContacto);
        CargoContacto = findViewById(R.id.campoCargoContacto);
        Direccion = findViewById(R.id.campoDireccion);
        Ciudad = findViewById(R.id.campoCiudad);
        Region = findViewById(R.id.campoRegión);
        CodPostal = findViewById(R.id.campoCodPostal);
        Pais = findViewById(R.id.campoPais);
        Telefono = findViewById(R.id.campoTelefono);
        Fax = findViewById(R.id.campoFax);
        PaginaPrincipal = findViewById(R.id.campoPaginaPrincipal);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                accionesClientes datosCliente = new accionesClientes(formClientes.this);
                //Acediendo a variables que nos permitan acceder al metodo insert
                //procediendo a llamar el metodo de inmediato,  realizando la lectura de los campos
                //del contenido de los campos y se los enviamos como valores al metodo
                long ultimoCliente = datosCliente.insertarCliente(NombreCompania.getText().toString(),NombreContacto.getText().toString(), CargoContacto.getText().toString(), Direccion.getText().toString(), Ciudad.getText().toString(), Region.getText().toString(), CodPostal.getText().toString(), Pais.getText().toString(), Telefono.getText().toString(), Fax.getText().toString(), PaginaPrincipal.getText().toString());
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