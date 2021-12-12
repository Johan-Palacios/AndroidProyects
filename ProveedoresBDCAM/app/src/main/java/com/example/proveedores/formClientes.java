package com.example.proveedores;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class formClientes extends AppCompatActivity {
    private static final int REQUEST_PERMITION_CAMERA = 20;
    private static final int REQUES_IMAGE_CAMERA = 12;
    EditText NombreCompania, NombreContacto, CargoContacto, Direccion, Ciudad, Region, CodPostal, Pais, Telefono, Fax, PaginaPrincipal;
    Button btnRegistrar;
    ImageView image;
    FloatingActionButton btnCam;
    String picturePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_clientes);
        NombreCompania = findViewById(R.id.campoNombreCompania);
        NombreContacto = findViewById(R.id.campoNombreContacto);
        CargoContacto = findViewById(R.id.campoCargoContacto);
        Direccion = findViewById(R.id.campoDireccion);
        Ciudad = findViewById(R.id.campoCiudad);
        Region = findViewById(R.id.campoRegión);
        CodPostal = findViewById(R.id.campoCodPostal);
        Pais = findViewById(R.id.campoPais);
        Telefono = findViewById(R.id.campoTelefono);
        Fax = findViewById(R.id.campoFax);
        PaginaPrincipal = findViewById(R.id.campoPaginaPrincipal);
        image = findViewById(R.id.viewImageForm);
        btnCam = findViewById(R.id.btnCam);
        btnCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                {
                    if (ActivityCompat.checkSelfPermission(formClientes.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED)
                    {
                        shootPhoto();
                    }
                    else {
                        ActivityCompat.requestPermissions(formClientes.this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMITION_CAMERA);
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
                accionesClientes datosCliente = new accionesClientes(formClientes.this);
                //Acediendo a variables que nos permitan acceder al metodo insert
                //procediendo a llamar el metodo de inmediato,  realizando la lectura de los campos
                //del contenido de los campos y se los enviamos como valores al metodo
                long ultimoCliente = datosCliente.insertarCliente(NombreCompania.getText().toString(),NombreContacto.getText().toString(), CargoContacto.getText().toString(), Direccion.getText().toString(), Ciudad.getText().toString(), Region.getText().toString(), CodPostal.getText().toString(), Pais.getText().toString(), Telefono.getText().toString(), Fax.getText().toString(), PaginaPrincipal.getText().toString(), picturePath.toString());
                //evaluando el valor de la variable ultimo cliente
                if (ultimoCliente>0){
                    Toast.makeText(formClientes.this, "Cliente registrado con éxito", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(formClientes.this, MainActivity.class);
                    startActivity(i);
                }
                else {
                    Toast.makeText(formClientes.this, "Error de registro", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(formClientes.this, MainActivity.class);
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
            image.setImageURI(Uri.parse(picturePath));
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
                Uri fotoUri = FileProvider.getUriForFile(formClientes.this, "com.example.proveedores", imagenArchivo);
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
        picturePath = imagenTemporal.getAbsolutePath();
        return imagenTemporal;
    }
}