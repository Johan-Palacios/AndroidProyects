package com.intecap.resguardarfoto;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class secondScreen extends AppCompatActivity {
    ImageView vImage;
    FloatingActionButton btnCapture;
    String pathPhoto;
    private static final int REQUEST_IMAGE_CAMARA_TWO = 53;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        vImage = findViewById(R.id.visorImagen2);
        btnCapture = findViewById(R.id.btnSecondCapture);
        btnCapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (ActivityCompat.checkSelfPermission(secondScreen.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                        capturarFoto();
                    } else {
                        ActivityCompat.requestPermissions(secondScreen.this, new String[]{Manifest.permission.CAMERA}, REQUEST_IMAGE_CAMARA_TWO);
                    }
                } else {
                    capturarFoto();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAMARA_TWO && resultCode == Activity.RESULT_OK)
        {
            vImage.setImageURI(Uri.parse(pathPhoto));
        }
    }

    private void capturarFoto() {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (i.resolveActivity(getPackageManager()) != null)
        {
            File imagenArchivo = null;
            try {
                imagenArchivo = crearArchivo();
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
            if (imagenArchivo != null)
            {
                Uri fotoUri = FileProvider.getUriForFile(secondScreen.this,"com.intecap.resguardarfoto", imagenArchivo);
                i.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(i, REQUEST_IMAGE_CAMARA_TWO);
            }
        }
    }
    private File crearArchivo() throws IOException {
        String formatoFecha = new SimpleDateFormat("yyyyMMdd_HH-mm-ss", Locale.getDefault()).format(new Date());
        String nombreArchivo = "IMG_" + formatoFecha;
        File nuevaUbicacion = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imagenTemporal = File.createTempFile(nombreArchivo, ".jpg", nuevaUbicacion);
        pathPhoto = imagenTemporal.getAbsolutePath();
        return imagenTemporal;
    }
}