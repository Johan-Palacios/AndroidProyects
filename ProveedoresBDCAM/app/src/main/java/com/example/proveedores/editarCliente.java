package com.example.proveedores;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class editarCliente extends AppCompatActivity {
    EditText txtNombreCompania, txtNombreContacto, txtCargoContacto, txtDireccion,txtCiudad,txtRegion,txtCodPostal,txtPais,txtTelefono,txtFax, txtPaginaPrincipal;
    int id = 0;
    metodosCliente proveedorIndividualActual;
    FloatingActionButton edit, delete;
    Button btnActualizaCLiente;
    ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_proveedor_individual);
        edit = findViewById(R.id.btnEnviarEdicion);
        delete = findViewById(R.id.btnEliminar);
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
        btnActualizaCLiente=findViewById(R.id.btnActualizarCliente);
        image = findViewById(R.id.viewImageIndividual);
        delete.setVisibility(View.GONE);
        edit.setVisibility(View.GONE);
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
            image.setImageURI(Uri.parse(proveedorIndividualActual.getPicturePath()));
        }else{
            Toast.makeText(editarCliente.this, "Error al cargar la info", Toast.LENGTH_SHORT).show();
        }
        btnActualizaCLiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtNombreCompania.getText().toString().equals("") && !txtNombreContacto.getText().toString().equals("") && !txtCargoContacto.getText().toString().equals("") && !txtDireccion.getText().toString().equals("") && !txtCiudad.getText().toString().equals("") && !txtRegion.getText().toString().equals("") && !txtCodPostal.getText().toString().equals("") && !txtPais.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtFax.getText().toString().equals("") && !txtPaginaPrincipal.getText().toString().equals("")){
                    boolean respuesta = clienIndividal.actualizaCliente(id, txtNombreCompania.getText().toString(), txtNombreContacto.getText().toString(), txtCargoContacto.getText().toString(), txtDireccion.getText().toString(), txtCiudad.getText().toString(), txtRegion.getText().toString(), txtCodPostal.getText().toString(), txtPais.getText().toString(), txtTelefono.getText().toString(), txtFax.getText().toString(), txtPaginaPrincipal.getText().toString());
                    if (respuesta){
                        Toast.makeText(editarCliente.this, "Actuliazado Correctamente", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(editarCliente.this, "Error, Intente de nuevo", Toast.LENGTH_LONG).show();
                    }
                    Intent i = new Intent(editarCliente.this, verProveedorIndividual.class);
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