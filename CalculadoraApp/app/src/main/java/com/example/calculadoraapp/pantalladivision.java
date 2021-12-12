package com.example.calculadoraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class pantalladivision extends AppCompatActivity {
    EditText primerCampoUsuario, segundoCampoUsuario;
    TextView vistaUsuarioResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalladivision);
        primerCampoUsuario = findViewById(R.id.campoPrimeroDivision);
        segundoCampoUsuario = findViewById(R.id.campoSegundoDivision);
        vistaUsuarioResultado=findViewById(R.id.resultadoDivision);
    }
    public void realizarDivision(View v)
    {
        Double resultado = Double.parseDouble(primerCampoUsuario.getText().toString()) / Integer.parseInt(segundoCampoUsuario.getText().toString());
        vistaUsuarioResultado.setText(resultado+"");
    }
    public void enviarPantallaMultiplicacion(View v)
    {
        Intent i = new Intent(pantalladivision.this, pantallaMultiplicacion.class);
        startActivity(i);
    }
    public void enviarPantallaSuma(View v)
    {
        Intent i = new Intent(pantalladivision.this, MainActivity.class);
        startActivity(i);
    }
    public void enviarPantallaResta(View v)
    {
        Intent i = new Intent(pantalladivision.this, pantallaresta.class);
        startActivity(i);
    }
}