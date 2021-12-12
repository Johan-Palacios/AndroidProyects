package com.intecap.galeriafotos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class imagenAmpliada extends AppCompatActivity {
    ImageView vImagen;
    adaptadorGaleriaFotos enlaceImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagen_ampliada);
        vImagen = findViewById(R.id.fotoAmpliada);

        Intent i = getIntent();
        int posicionImagen = i.getExtras().getInt("imagenCorrespondiente");
        enlaceImagen = new adaptadorGaleriaFotos(this);
        vImagen.setImageResource(enlaceImagen.imagenesArreglo[posicionImagen]);
        ActionBar barraOpciones = getSupportActionBar();
        barraOpciones.setTitle("Fotografia Amplia");
    }
}