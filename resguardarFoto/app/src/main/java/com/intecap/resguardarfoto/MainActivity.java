package com.intecap.resguardarfoto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    ImageView vImagen;
    FloatingActionButton btnCamara;
    String ubicacionFoto;
    Button SegundaPantalla;
    private static final int REQUEST_PERMITION_CAMARA = 20;
    private static final int REQUEST_IMAGE_CAMARA = 52;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vImagen = findViewById(R.id.visorImagen);
        btnCamara = findViewById(R.id.btnTomarFoto);
        SegundaPantalla = findViewById(R.id.btnSegundaPantalla);
        SegundaPantalla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, secondScreen.class);
                startActivity(i);
            }
        });
       btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Validar que el sistema sea superior a android 6
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        capturarFoto();
                    } else {
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAMARA);
                    }
                } else {
                    capturarFoto();
                }
            }
        });
        /*ActionBar barOption = getSupportActionBar();
        barOption.setTitle("Fotos");*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_PERMITION_CAMARA) {
            //Mostrar el dialogo para permitir el acceso a la camara
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                capturarFoto();
            } else
            {
                Toast.makeText(this, "Debe Brindar Permisos", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_IMAGE_CAMARA && resultCode == Activity.RESULT_OK)
        {
          vImagen.setImageURI(Uri.parse(ubicacionFoto));
        }
    }

    private void capturarFoto() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (i.resolveActivity(getPackageManager()) != null) {
            File imagenArchivo = null;
            //Validar el contenido
            try {
                imagenArchivo = crearArchivo();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (imagenArchivo != null) {                                                    //aqui estaba el detalle una mayuscula
                Uri fotoUri = FileProvider.getUriForFile(MainActivity.this, "com.intecap.resguardarfoto", imagenArchivo);
                i.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(i, REQUEST_IMAGE_CAMARA);
            }
        }
    }

    private File crearArchivo() throws IOException {
        //preparar la fecha para el nombre de la imagen
        String formatoFecha = new SimpleDateFormat("yyyyMMdd_Hh-mm-ss", Locale.getDefault()).format(new Date());
        String nombreArchivo = "IMG_" + formatoFecha;
        //Ubicacion Final
        File nuevaUbicacion = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        //Estableciendo imagen en el paquete
        File imagenTemporal = File.createTempFile(nombreArchivo, ".jpg", nuevaUbicacion);
        ubicacionFoto = imagenTemporal.getAbsolutePath();
        return imagenTemporal;
    }
}