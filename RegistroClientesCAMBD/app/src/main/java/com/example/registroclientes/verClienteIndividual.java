package com.example.registroclientes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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

public class verClienteIndividual extends AppCompatActivity {
    EditText txtNombres, txtPais, txtEmpresa;
    metodosCliente clienteIndividualActual;
    ImageView vImage;
    int id=0;
    FloatingActionButton btnEnviarEdicion, btnEliminaCliente;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cliente_individual);
        update =findViewById(R.id.btnActualizaCliente);
        txtNombres=findViewById(R.id.vistaCampoNombres);
        txtPais=findViewById(R.id.vistaCampoPais);
        txtEmpresa=findViewById(R.id.vistaCampoEmpresa);
        vImage = findViewById(R.id.vistaPrevia);
        btnEnviarEdicion = findViewById(R.id.btnEnviarEdicion);
        btnEliminaCliente = findViewById(R.id.btnEliminar);
        update.setVisibility(View.GONE);
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
        accionesClientes clienIndividal = new accionesClientes((verClienteIndividual.this));
        clienteIndividualActual = clienIndividal.verCliente(id);

        if (clienteIndividualActual!=null)
        {
            txtNombres.setText(clienteIndividualActual.getNombres());
            txtPais.setText(clienteIndividualActual.getPais());
            txtEmpresa.setText(clienteIndividualActual.getEmpresa());
            vImage.setImageURI(Uri.parse(clienteIndividualActual.getPicturePath()));
            //Inhabilitar los campos, el teclado
            txtNombres.setInputType(InputType.TYPE_NULL);
            txtPais.setInputType(InputType.TYPE_NULL);
            txtEmpresa.setInputType(InputType.TYPE_NULL);
        }
        else{
            Toast.makeText(verClienteIndividual.this, "Error al cargar la info", Toast.LENGTH_SHORT).show();
        }
        btnEnviarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Esta debe ir dentro del metodo onCreate
                Intent i = new Intent(verClienteIndividual.this, editarCliente.class);
                //Dar la llaver para enviar la informacion a la pantalla
                i.putExtra("codigoCliente", id);
                startActivity(i);
            }
        });
        //Creando evento onClick para mensaje Elminar
        btnEliminaCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creandoe l cuadro de dialogo
                AlertDialog.Builder mensaje = new AlertDialog.Builder(verClienteIndividual.this);
                mensaje.setMessage("Realmente desea eliminar Cliente? ")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Si el usuario accede a la opcion se elimina
                                //Se decide si el eliminaCliente es verdadero o falso para su eliminacion
                                if(clienIndividal.eliminaCliente(id)){
                                    Toast.makeText(verClienteIndividual.this, "El cliente se ha eliminado con ÉXITO", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(verClienteIndividual.this, MainActivity.class);
                                    startActivity(i);
                                }
                            }
                        })
                        //Sin codigo se cierra automaticamente
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
            }
        });
    }
    public void irMain(View view)
    {
        Intent i = new Intent(verClienteIndividual.this, MainActivity.class);
        startActivity(i);
    }
}