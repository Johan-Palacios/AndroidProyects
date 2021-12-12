package com.intecap.galeriafotos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class galeriaWallpaper extends AppCompatActivity {
    GridView espacioGridWallpaper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria_wallpaper);
        espacioGridWallpaper = findViewById(R.id.vistaImagenWallpaper);
        espacioGridWallpaper.setAdapter(new adaptadorWallpaper(galeriaWallpaper.this));
        espacioGridWallpaper.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), ampliadoWallpaper.class);
                i.putExtra("wallpaperCo", position);
                startActivity(i);
            }
        });
    }
}