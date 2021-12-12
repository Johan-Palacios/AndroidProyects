package com.intecap.usocamara;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton btnTomarFoto;
    ImageView vImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnTomarFoto = findViewById(R.id.btnTomarFoto);
        vImagen = findViewById(R.id.visorImagen);
        btnTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirPrimeraCamara();
            }
        });
    }
    //Es accionado cuando finalice el servico de camara
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //realizando la evaluacion de requestCode
        if (requestCode == 320 && resultCode  == RESULT_OK)
        {
            Bundle foto = data.getExtras();
            Bitmap imagenFinal = (Bitmap) foto.get("data");
            vImagen.setImageBitmap(imagenFinal);
        }
    }

    //Code- OverrayMethods - onActyvityResult
    //Metodo que abrira la camara
    private void abrirPrimeraCamara() {
        //Creando intent que permite abrir el servicio de camara
        //Este servicio no es necesario indicarle la pantalla de ida, por que en algun momento
        //Abrira esta pantalla
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //Despues de la pausa se evaluara el metodo revolve activity por un IF
        if (i.resolveActivity(getPackageManager()) != null)
        {
            //Cuando el servicio de camara se ejecute, se debera recolectar la imagen
            //por eso se inicia la pantalla pero esperando ese resultado
            //el codigo debe ser personalizado
            startActivityForResult(i,320);
        }
    }

}