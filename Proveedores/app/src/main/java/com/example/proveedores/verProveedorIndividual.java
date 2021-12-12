package com.example.proveedores;

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

public class verProveedorIndividual extends AppCompatActivity {
    EditText txtNombreCompania, txtNombreContacto, txtCargoContacto, txtDireccion,txtCiudad,txtRegion,txtCodPostal,txtPais,txtTelefono,txtFax, txtPaginaPrincipal;
    int id = 0;
    metodosCliente proveedorIndividualActual;
    FloatingActionButton btnEnviarEdicion, btnEliminarCliente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_proveedor_individual);
        txtNombreCompania = findViewById(R.id.vistaCampoNombreCompania);
        txtNombreContacto = findViewById(R.id.vistaNombreContacto);
        txtCargoContacto = findViewById(R.id.vistaCampoCargo);
        txtDireccion = findViewById(R.id.vistaCampoDireccion);
        txtCiudad = findViewById(R.id.vistaCampoCiudad);
        txtRegion = findViewById(R.id.vistaCampoRegion);
        txtCodPostal = findViewById(R.id.vistaCampoCodigoPostal);
        txtPais = findViewById(R.id.vistaCampoPais);
        txtTelefono = findViewById(R.id.vistaCampoTelefono);
        txtFax = findViewById(R.id.vistaCampoFax);
        txtPaginaPrincipal = findViewById(R.id.vistaCampoPaginaPrincipal);
        //corrijo aqui estaba el error aunque lo de abajo no lo descargo
        btnEnviarEdicion=findViewById(R.id.btnEnviarEdicion);
        btnEliminarCliente = findViewById(R.id.btnEliminar);
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
        accionesClientes clienIndividal = new accionesClientes((verProveedorIndividual.this));
        proveedorIndividualActual = clienIndividal.verCliente(id);

        if (proveedorIndividualActual!=null)
        {
            txtNombreCompania.setText(proveedorIndividualActual.getNombreCompania());
            txtNombreContacto.setText(proveedorIndividualActual.getNombreContacto());
            txtCargoContacto.setText(proveedorIndividualActual.getCargoContacto());
            txtDireccion.setText(proveedorIndividualActual.getDireccion());
            txtCiudad.setText(proveedorIndividualActual.getCiudad());
            txtRegion.setText(proveedorIndividualActual.getRegion());
            txtCodPostal.setText(proveedorIndividualActual.getCodPostal());
            txtPais.setText(proveedorIndividualActual.getPais());
            txtTelefono.setText(proveedorIndividualActual.getTelefono());
            txtFax.setText(proveedorIndividualActual.getFax());
            txtPaginaPrincipal.setText(proveedorIndividualActual.getPaginaPrincipal());
            //Borrar teclado
            txtNombreContacto.setInputType(InputType.TYPE_NULL);
           // txtNombreContacto.setInputType(InputType.TYPE_NULL);
            txtCargoContacto.setInputType(InputType.TYPE_NULL);
            txtDireccion.setInputType(InputType.TYPE_NULL);
            txtCiudad.setInputType(InputType.TYPE_NULL);
            txtRegion.setInputType(InputType.TYPE_NULL);
            txtCodPostal.setInputType(InputType.TYPE_NULL);
            txtPais.setInputType(InputType.TYPE_NULL);
            txtTelefono.setInputType(InputType.TYPE_NULL);
            txtFax.setInputType(InputType.TYPE_NULL);
            txtPaginaPrincipal.setInputType(InputType.TYPE_NULL);
        }else{
            Toast.makeText(verProveedorIndividual.this, "Error al cargar la info", Toast.LENGTH_SHORT).show();
        }
        btnEnviarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               //aca esta el error, hasta donde comprendo no debe de existir una lase editr cliente
                Intent i = new Intent(verProveedorIndividual.this, editarCliente.class);
                i.putExtra("codigoCliente", id);
                startActivity(i);
            }
        });
        btnEliminarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(verProveedorIndividual.this);
                mensaje.setMessage("Realmente desea eliminar?")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(clienIndividal.eliminaCliente(id)){
                                    Toast.makeText(verProveedorIndividual.this, "Usuario eliminado con éxito", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(verProveedorIndividual.this, MainActivity.class);
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
        Intent i = new Intent(verProveedorIndividual.this, MainActivity.class);
        startActivity(i);
    }
}