package com.example.categoriadb;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class formCategoria extends AppCompatActivity {
    @Override
    public void onBackPressed() {
    }
    private static final int REQUEST_PERMITION_CAMERA = 20;
    private static final int REQUES_IMAGE_CAMERA = 52;
    EditText NombreCategoria, Descripcion, Ubicacion;
    Button btnRegistrar, RegresarMain;
    FloatingActionButton btnCamara;
    String ubicacionFoto;
    ImageView vImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_categoria);
        vImage = findViewById(R.id.vistaRegistro);
        NombreCategoria = findViewById(R.id.campoNombreCategoria);
        Descripcion = findViewById(R.id.campoDescripcion);
        Ubicacion = findViewById(R.id.campoUbicacion);
        RegresarMain = findViewById(R.id.btnRegresarMain);
        RegresarMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(formCategoria.this, MainActivity.class);
                startActivity(i);
            }
        });
        btnCamara = findViewById(R.id.btnTomarFoto);
        btnCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (ActivityCompat.checkSelfPermission(formCategoria.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    {
                        shootPhoto();
                    }
                    else {
                        ActivityCompat.requestPermissions(formCategoria.this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMITION_CAMERA);
                    }
                }
                else{
                    shootPhoto();
                }

            }
        });
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                accionesCategoria datosCliente = new accionesCategoria(formCategoria.this);

                //Acediendo a variables que nos permitan acceder al metodo insert
                //procediendo a llamar el metodo de inmediato,  realizando la lectura de los campos
                
                //del contenido de los campos y se los enviamos como valores al metodo
                long ultimoCliente = datosCliente.insertarCategoria(NombreCategoria.getText().toString(),Descripcion.getText().toString(), Ubicacion.getText().toString(), ubicacionFoto);
                //evaluando el valor de la variable ultimo cliente
                if (ultimoCliente>0){
                    Toast.makeText(formCategoria.this, "Cliente registrado con éxito", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(formCategoria.this, MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(formCategoria.this, "Error de registro", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(formCategoria.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMITION_CAMERA)
        {
            if (permissions.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                shootPhoto();
            } else
            {
                Toast.makeText(this, "Debe Brindar Permisos", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUES_IMAGE_CAMERA && resultCode == Activity.RESULT_OK)
        {
            vImage.setImageURI(Uri.parse(ubicacionFoto));
        }
    }

    private void shootPhoto() {
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
                Uri fotoUri = FileProvider.getUriForFile(formCategoria.this, "com.example.categoriadb", imagenArchivo);
                i.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(i, REQUES_IMAGE_CAMERA);
            }
        }
    }

    private File crearArchivo() throws IOException {
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