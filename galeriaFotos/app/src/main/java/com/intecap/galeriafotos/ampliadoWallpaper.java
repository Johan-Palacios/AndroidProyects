package com.intecap.galeriafotos;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

public class ampliadoWallpaper extends AppCompatActivity {
    ImageView vImagen;
    adaptadorWallpaper enlaceWallpaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ampliado_wallpaper);
        vImagen = findViewById(R.id.vistaWallpaper);
        Intent i = getIntent();
        int posicionWallpaper = i.getExtras().getInt("wallpaperCo");
       enlaceWallpaper = new adaptadorWallpaper(this);
       vImagen.setImageResource(enlaceWallpaper.wallpaperArreglo[posicionWallpaper]);
       ActionBar barraOpciones = getSupportActionBar();
       barraOpciones.setTitle("Wallpaper Amplio");
    }
}