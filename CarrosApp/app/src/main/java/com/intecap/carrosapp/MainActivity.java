package com.intecap.carrosapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView listaCarros;
    ArrayList<methodCar> listaArrayCarros;
    Button gotoForm;
    ViewFlipper flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaCarros = findViewById(R.id.listadoCarros);
        listaCarros.setLayoutManager(new LinearLayoutManager(this));
        actionCar infoCar = new actionCar(this);
        listaArrayCarros = new ArrayList<>();
        adapter adaptador = new adapter(infoCar.visualizarCliente());
        listaCarros.setAdapter(adaptador);



        flipper = findViewById(R.id.viewCarFlipper);
        gotoForm = findViewById(R.id.goToForm);
        int images[] = {R.drawable.camaro, R.drawable.nissan, R.drawable.raize, R.drawable.rav4, R.drawable.tesla};
        for(int img:images)
        {
            flipperShow(img);
        }
        gotoForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, formcar.class);
                startActivity(i);
            }
        });
    }

    private void flipperShow(int img) {
        ImageView viewImage = new ImageView(MainActivity.this);
        viewImage.setBackgroundResource(img);
        flipper.addView(viewImage);
        flipper.setFlipInterval(4000);
        flipper.setAutoStart(true);
        flipper.setInAnimation(this,android.R.anim.slide_in_left);
    }

    public void crearBD(View v)
    {
        universalConection bdLocal = new universalConection(MainActivity.this);
        //En este momento se crearìa la tabla
        SQLiteDatabase bd = bdLocal.getWritableDatabase();
        if (bd != null){
            Toast.makeText(MainActivity.this, "Base de datos creada", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "Error en crear la base de datos", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
    }
}