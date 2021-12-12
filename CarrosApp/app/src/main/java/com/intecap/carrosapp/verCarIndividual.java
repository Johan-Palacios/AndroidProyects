package com.intecap.carrosapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;

public class verCarIndividual extends AppCompatActivity {
    EditText txtmarca, txtlinea, txttipo, txttransmision,txtmodelo, txtkm,txttraccion, txtcombustible, txtcolor,txtprecio,txtcantidadpuertas;
    methodCar carIndividualActual;
    ShapeableImageView vImagen;
    LottieAnimationView Main;
    int id = 0;
    FloatingActionButton btnEnviarEdicion, btnEliminacion;
    Button Update;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_car_individual);
        Main = findViewById(R.id.irMain);
        btnEnviarEdicion = findViewById(R.id.editCar);
        btnEliminacion = findViewById(R.id.deleteCar);
        txtmarca = findViewById(R.id.verMarcaIndividual);
        txtlinea = findViewById(R.id.verlineaIndividual);
        txttipo = findViewById(R.id.verTipoIndividual);
        txttransmision = findViewById(R.id.verTransmisionIndividual);
        txtmodelo = findViewById(R.id.verModeloIndividual);
        txtkm= findViewById(R.id.verKmIndividual);
        txttraccion = findViewById(R.id.verTraccionIndividual);
        txtcombustible = findViewById(R.id.verCombustibleIndividual);
        txtcolor = findViewById(R.id.verColorIndividual);
        txtprecio = findViewById(R.id.verPrecioIndividual);
        txtcantidadpuertas = findViewById(R.id.verPuertaIndividual);
        Update = findViewById(R.id.btnactualizarCarro);
        Update.setVisibility(View.GONE);
        vImagen = findViewById(R.id.photoIndividualCar);
        Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(verCarIndividual.this, MainActivity.class);
                startActivity(i);
            }
        });
        if (savedInstanceState==null)
        {
            Bundle datosAdicionales = getIntent().getExtras();
            if (datosAdicionales==null)
            {
                id=Integer.parseInt(null);
            }
            else{
                id = datosAdicionales.getInt("codigoCar");
            }
        }else {
            id = (int) savedInstanceState.getSerializable("codigoCar");
        }
        actionCar carIndividual = new actionCar((verCarIndividual.this));
        carIndividualActual = carIndividual.verCliente(id);

        if (carIndividualActual!=null)
        {
            txtmarca.setText(carIndividualActual.getMarca());
            txtlinea.setText(carIndividualActual.getLinea());
            txttipo.setText(carIndividualActual.getTipo());
            txttransmision.setText(carIndividualActual.getTransmision());
            txtmodelo.setText(carIndividualActual.getModelo());
            txtkm.setText(carIndividualActual.getKm());
            txttraccion.setText(carIndividualActual.getTraccion());
            txtcombustible.setText(carIndividualActual.getCombustible());
            txtcolor.setText(carIndividualActual.getColor());
            txtprecio.setText(carIndividualActual.getPrecio());
            txtcantidadpuertas.setText(carIndividualActual.getCantidad_puertas());
            vImagen.setImageURI(Uri.parse(carIndividualActual.getFoto()));
            //Inhabilitar Teclado
            txtmarca.setInputType(InputType.TYPE_NULL);
            txtlinea.setInputType(InputType.TYPE_NULL);
            txttipo.setInputType(InputType.TYPE_NULL);
            txttransmision.setInputType(InputType.TYPE_NULL);
            txtmodelo.setInputType(InputType.TYPE_NULL);
            txtkm.setInputType(InputType.TYPE_NULL);
            txttraccion.setInputType(InputType.TYPE_NULL);
            txtcombustible.setInputType(InputType.TYPE_NULL);
            txtcolor.setInputType(InputType.TYPE_NULL);
            txtprecio.setInputType(InputType.TYPE_NULL);
            txtcantidadpuertas.setInputType(InputType.TYPE_NULL);
        }
        else{
            Toast.makeText(verCarIndividual.this, "Error al cargar la info", Toast.LENGTH_SHORT).show();
        }
        btnEnviarEdicion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(verCarIndividual.this, editCar.class);
                //Dar la llaver para enviar la informacion a la pantalla
                i.putExtra("codigoCar", id);
                startActivity(i);
            }
        });
        btnEliminacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder mensaje = new AlertDialog.Builder(verCarIndividual.this);
                mensaje.setMessage("Realmente desea eliminar Vehiculo? ")
                        .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Si el usuario accede a la opcion se elimina
                                //Se decide si el eliminaCliente es verdadero o falso para su eliminacion
                                if(carIndividual.eliminaCliente(id)){
                                    Toast.makeText(verCarIndividual.this, "El Vehiculo se ha eliminado con exito", Toast.LENGTH_SHORT).show();
                                    Intent i = new Intent(verCarIndividual.this, MainActivity.class);
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

    @Override
    public void onBackPressed() {
    }
}