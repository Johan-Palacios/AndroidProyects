package com.intecap.carruselnocturno;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {
    ViewFlipper vFlipper;
    ViewFlipper v2Flipper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vFlipper = findViewById(R.id.vistaFlipper);
        v2Flipper = findViewById(R.id.vistaFlipperDos);
        //Creando el arreglo donde se resguardará la ubicación de las imagenes
        //estas se ecnuenctran en el paquete
        int imagenes[] =  {R.drawable.im1, R.drawable.im2, R.drawable.im3, R.drawable.im4, R.drawable.im5};
        int imagenesDos[] = {R.drawable.minimal1, R.drawable.minimal2, R.drawable.minimal4, R.drawable.minimal5};
        //Aca crearemos un ciclo infinito ...
        for(int foto:imagenes)
        {
            carruselImagenes(foto);
        }
        for(int image:imagenesDos)
        {
            carruselImagenesDos(image);
        }
    }
    //Creando metodo para remplazar la imagen por la siguiente
    public void carruselImagenes(int fotoActual){
        ImageView vistaImagen = new ImageView(MainActivity.this);
        vistaImagen.setBackgroundResource(fotoActual);
        //Cargar imagen
        vFlipper.addView(vistaImagen);
        //Duracion
        vFlipper.setFlipInterval(3000);
        //iniciar de forma automatica el flipper
        vFlipper.setAutoStart(true);
        //Estableciendo animación
        vFlipper.setInAnimation(this, android.R.anim.slide_in_left);
    }
    public void carruselImagenesDos(int fotos)
    {
        ImageView vistaImagen = new ImageView(MainActivity.this);
        vistaImagen.setBackgroundResource(fotos);
        v2Flipper.addView(vistaImagen);
        v2Flipper.setFlipInterval(4000);
        v2Flipper.setAutoStart(true);
        v2Flipper.setInAnimation(this, android.R.anim.slide_in_left);
    }
}