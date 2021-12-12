package com.intecap.galeriafotos;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class adaptadorGaleriaFotos extends BaseAdapter {
    //Será utilizada para recopilar las fotos
    private Context fotoContexto;
    //recopilar fotografias
    public int[] imagenesArreglo = {R.drawable.ima1, R.drawable.im2, R.drawable.im3, R.drawable.im4, R.drawable.im5};
    public adaptadorGaleriaFotos(Context fotoContexto)
    {
        this.fotoContexto = fotoContexto;
    }
    //Hacer referencia a obtener la cantidad total de elementos del arreglo
    @Override
    public int getCount() {
        //Devolver la cantidad de numeros del arreglo
        return imagenesArreglo.length;
    }

    @Override
    public Object getItem(int position) {
        return imagenesArreglo[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Este metodo será utilizado por la GRID VIEW para crear un visor de imagen
        ImageView visorImagen = new ImageView(fotoContexto);
        //Insertamos imagen
        visorImagen.setBackgroundResource(imagenesArreglo[position]);
        //Centrar las imagenes en la miniatura
        visorImagen.setScaleType(ImageView.ScaleType.CENTER_CROP);
        //Se insertan las dimensiones el recorte de la miniatura
        visorImagen.setLayoutParams(new ViewGroup.LayoutParams(340, 340));
        return visorImagen;
    }
}
