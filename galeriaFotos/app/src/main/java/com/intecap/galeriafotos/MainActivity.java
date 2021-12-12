package com.intecap.galeriafotos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.net.Inet4Address;

public class MainActivity extends AppCompatActivity {
    GridView espacioGrid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        espacioGrid = findViewById(R.id.vistaGaleria);
        espacioGrid.setAdapter(new adaptadorGaleriaFotos(MainActivity.this));
        espacioGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), imagenAmpliada.class);
                i.putExtra("imagenCorrespondiente", position);
                startActivity(i);
            }
        });
    }
    public void irWallpaper(View view)
    {
        Intent i = new Intent(MainActivity.this, galeriaWallpaper.class);
        startActivity(i);
    }
}