package com.intecap.carrosapp;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class formcar extends AppCompatActivity {

    private static final int REQUEST_PERMITION_CAMERA = 20;
    private static final int REQUEST_IMAGE_CAMERA = 52;
    EditText fieldMarca, fieldLinea, fieldTipo, fieldTranmision, fieldModelo, fieldKm, fieldTraccion, fieldCombustible, fieldColor, fieldPrecio, fieldCantidadPuertas;
    String picturePath;
    Button btnRegisterCar;
    FloatingActionButton btnTakeCam;
    LottieAnimationView gotomain;
    LottieAnimationView vImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formcar);
        //Campos de Interfaz
        fieldMarca = findViewById(R.id.marcaFormCamp);
        fieldLinea = findViewById(R.id.lineaFormCamp);
        fieldTipo = findViewById(R.id.tipoFormCamp);
        fieldTranmision = findViewById(R.id.transmisionFormCamp);
        fieldModelo = findViewById(R.id.modeloFormCamp);
        fieldKm = findViewById(R.id.kmFormCamp);
        fieldTraccion = findViewById(R.id.traccionFormCamp);
        fieldCombustible = findViewById(R.id.combustibleFormCamp);
        fieldColor = findViewById(R.id.colorFormCamp);
        fieldPrecio = findViewById(R.id.precioFormCamp);
        fieldCantidadPuertas = findViewById(R.id.puertaFormCamp);


        //Botones o otros

        btnRegisterCar = findViewById(R.id.Registrar);
        btnTakeCam = findViewById(R.id.takePhoto);
        vImage = findViewById(R.id.photoForm);
        gotomain = findViewById(R.id.gotoMain);
        btnTakeCam.setVisibility(View.GONE);
        btnRegisterCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    actionCar datoscar = new actionCar(formcar.this);

                    long ultimoCliente = datoscar.insertCar(fieldMarca.getText().toString(), fieldLinea.getText().toString(), fieldTipo.getText().toString(), fieldTranmision.getText().toString(), fieldModelo.getText().toString(), fieldKm.getText().toString(), fieldTraccion.getText().toString(), fieldCombustible.getText().toString(), fieldColor.getText().toString(), fieldPrecio.getText().toString(), fieldCantidadPuertas.getText().toString(), picturePath);


                    if (ultimoCliente>0){
                        Toast.makeText(formcar.this, "Carro registrado con éxito", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(formcar.this, MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(formcar.this, "Error de registro", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(formcar.this, MainActivity.class);
                        startActivity(i);
                    }

            }
        });
        vImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(vImage, R.raw.photo);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (ActivityCompat.checkSelfPermission(formcar.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    {
                        shootPhoto();
                    }
                    else {
                        ActivityCompat.requestPermissions(formcar.this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMITION_CAMERA);
                    }
                }
                else{
                    shootPhoto();
                }
            }
        });
        gotomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animate(gotomain, R.raw.pop);
                Intent i = new Intent(formcar.this, MainActivity.class);
                startActivity(i);
            }
        });
        vImage.setAnimation(R.raw.photo);
        vImage.playAnimation();
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
        if(requestCode == REQUEST_IMAGE_CAMERA && resultCode == Activity.RESULT_OK)
        {
            vImage.setImageURI(Uri.parse(picturePath));
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
                Uri fotoUri = FileProvider.getUriForFile(formcar.this, "com.intecap.carrosapp", imagenArchivo);
                i.putExtra(MediaStore.EXTRA_OUTPUT, fotoUri);
                startActivityForResult(i, REQUEST_IMAGE_CAMERA);
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
        picturePath = imagenTemporal.getAbsolutePath();
        return imagenTemporal;
    }
    private void animate(LottieAnimationView image, int json)
    {
        image.setAnimation(json);
        image.playAnimation();
    }

    @Override
    public void onBackPressed() {
    }
}