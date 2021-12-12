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

public class editCar extends AppCompatActivity {
    EditText txtmarca, txtlinea, txttipo, txttransmision,txtmodelo, txtkm,txttraccion, txtcombustible, txtcolor,txtprecio,txtcantidadpuertas;
    methodCar carIndividualActual;
    ShapeableImageView vImagen;
    int id = 0;
    FloatingActionButton btnEnviarEdicion, btnEliminacion;
    Button UpdateCar;
    LottieAnimationView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_car_individual);
        back = findViewById(R.id.irMain);
        UpdateCar = findViewById(R.id.btnactualizarCarro);
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
        vImagen = findViewById(R.id.photoIndividualCar);
        btnEnviarEdicion.setVisibility(View.GONE);
        btnEliminacion.setVisibility(View.GONE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
        actionCar carIndividual = new actionCar((editCar.this));
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
        }
        else{
            Toast.makeText(editCar.this, "Error al cargar la info", Toast.LENGTH_SHORT).show();
        }
        UpdateCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!txtmarca.getText().toString().equals("") && !txtlinea.getText().toString().equals("") && !txttipo.getText().toString().equals("") && !txttransmision.getText().toString().equals("") && !txtmodelo.getText().toString().equals("") && !txtkm.getText().toString().equals("") && !txttraccion.getText().toString().equals("") && !txtcombustible.getText().toString().equals("") && !txtcolor.getText().toString().equals("") && !txtprecio.getText().toString().equals("") && !txtcantidadpuertas.getText().toString().equals(""))
                {
                    boolean respuesta = carIndividual.actualizaCliente(id, txtmarca.getText().toString(), txtlinea.getText().toString(),txttipo.getText().toString(), txttransmision.getText().toString(), txtmodelo.getText().toString(), txtkm.getText().toString(), txttraccion.getText().toString(), txtcombustible.getText().toString(), txtcolor.getText().toString(), txtprecio.getText().toString(), txtcantidadpuertas.getText().toString());
                    if (respuesta){
                        Toast.makeText(editCar.this, "Actualizado Correctamente",Toast.LENGTH_LONG).show();
                    }else{

                        Toast.makeText(editCar.this, "Error, intente de nuevo",Toast.LENGTH_LONG).show();
                    }
                    Intent i = new Intent(editCar.this, verCarIndividual.class);
                    i.putExtra("codigoCar", id);
                    startActivity(i);
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
