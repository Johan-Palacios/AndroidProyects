package com.intecap.usocamara;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class segundaCamara extends AppCompatActivity {
    FloatingActionButton btn1TomarFoto, btn2TomarFoto;
    ImageView imagenUno, imagenDos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segunda_camara);
        btn1TomarFoto = findViewById(R.id.btn1Pantalla2);
        btn2TomarFoto = findViewById(R.id.btn2Pantalla2);
        imagenUno = findViewById(R.id.visorUnoPantallaDos);
        imagenDos = findViewById(R.id.visorDosPantallaDos);
        btn1TomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara(322);
            }
        });
        btn2TomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirCamara(323);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 322 && resultCode == RESULT_OK)
        {
            Bundle foto = data.getExtras();
            Bitmap imagenFinal = (Bitmap) foto.get("data");
            imagenUno.setImageBitmap(imagenFinal);
        }
        else if(requestCode == 323 && resultCode == RESULT_OK){
            Bundle foto = data.getExtras();
            Bitmap imagenFinal = (Bitmap) foto.get("data");
            imagenDos.setImageBitmap(imagenFinal);
        }
    }

    private void abrirCamara(int code) {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (i.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(i, code);
        }
    }
}