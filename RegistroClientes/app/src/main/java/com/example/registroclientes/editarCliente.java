package com.example.registroclientes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class editarCliente extends AppCompatActivity {
    EditText txtNombres, txtPais, txtEmpresa;
    metodosCliente clienteIndividualActual;
    Button btnActualizaCliente;
    int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cliente_individual);
        txtNombres=findViewById(R.id.vistaCampoNombres);
        txtPais=findViewById(R.id.vistaCampoPais);
        txtEmpresa=findViewById(R.id.vistaCampoEmpresa);
        btnActualizaCliente = findViewById(R.id.btnActualizaCliente);
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
                id = datosAdicionales.getInt("codigoCliente");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("codigoCliente");
        }
        accionesClientes clienIndividal = new accionesClientes((editarCliente.this));
        clienteIndividualActual = clienIndividal.verCliente(id);

        if (clienteIndividualActual!=null)
        {
            txtNombres.setText(clienteIndividualActual.getNombres());
            txtPais.setText(clienteIndividualActual.getPais());
            txtEmpresa.setText(clienteIndividualActual.getEmpresa());
        }else{
            Toast.makeText(editarCliente.this, "Error al cargar la info", Toast.LENGTH_SHORT).show();
        }
        btnActualizaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Se realizará una validacion para que los datos no este vacios
                if (!txtNombres.getText().toString().equals("") && !txtPais.getText().toString().equals("") && !txtEmpresa.getText().toString().equals(""))
                {
                    //Se recupera el estado verdadero o falso en el metodo actualiza Cliente
                    boolean respuesta = clienIndividal.actualizaCliente(id, txtNombres.getText().toString(), txtPais.getText().toString(),txtEmpresa.getText().toString());
                    if (respuesta){

                        Toast.makeText(editarCliente.this, "Actualizado Correctamente",Toast.LENGTH_LONG).show();
                    }else{

                        Toast.makeText(editarCliente.this, "Error, intente de nuevo",Toast.LENGTH_LONG).show();
                    }
                    Intent i = new Intent(editarCliente.this, verClienteIndividual.class);
                    i.putExtra("codigoCliente", id);
                    startActivity(i);
                }
            }
        });

    }
    public void irMain(View view)
    {
        Intent i = new Intent(editarCliente.this, MainActivity.class);
        startActivity(i);

    }
}