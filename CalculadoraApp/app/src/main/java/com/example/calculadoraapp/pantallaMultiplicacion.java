package com.example.calculadoraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class pantallaMultiplicacion extends AppCompatActivity {

    EditText primerCampoUsuario, segundoCampoUsuario;
    TextView vistaUsuarioResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_multiplicacion);

        primerCampoUsuario = findViewById(R.id.campoPrimeroMultiplicacion);
        segundoCampoUsuario = findViewById(R.id.campoSegundoMultiplicacion);
        vistaUsuarioResultado=findViewById(R.id.resultadoMultiplicacion);
    }
    public void realizarMultiplicacion(View v)
    {
        int resultado = Integer.parseInt(primerCampoUsuario.getText().toString()) * Integer.parseInt(segundoCampoUsuario.getText().toString());
        vistaUsuarioResultado.setText(resultado+"");
    }
    public void enviarPantallaDivision(View v)
    {
        Intent i = new Intent(pantallaMultiplicacion.this, pantalladivision.class);
        startActivity(i);
    }
    public void enviarPantallaSuma(View v)
    {
        Intent i = new Intent(pantallaMultiplicacion.this, MainActivity.class);
        startActivity(i);
    }
    public void enviarPantallaResta(View v)
    {
        Intent i = new Intent(pantallaMultiplicacion.this, pantallaresta.class);
        startActivity(i);
    }
}