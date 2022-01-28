package com.example.calculadoraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText primerCampoUsuario, segundoCampoUsuario;
    TextView vistaUsuarioResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        primerCampoUsuario = findViewById(R.id.campoPrimerValor);
        segundoCampoUsuario = findViewById(R.id.campoSegundoValor);
        vistaUsuarioResultado=findViewById(R.id.resultadoOperacion);
    }

    public void realizarSuma(View v)
    {
        int resultado = Integer.parseInt(primerCampoUsuario.getText().toString()) + Integer.parseInt(segundoCampoUsuario.getText().toString());
        vistaUsuarioResultado.setText(resultado+"");
    }
    public void enviarpantallaresta(View v)
    {
        Intent i = new Intent(MainActivity.this, pantallaresta.class);
        startActivity(i);
    }
    public void enviarpantallaMultiplicacion(View v)
    {
        Intent i = new Intent(MainActivity.this, pantallaMultiplicacion.class);
        startActivity(i);
    }
    public void enviarpantallaDivision(View v)
    {
        Intent i = new Intent(MainActivity.this, pantalladivision.class);
        startActivity(i);
    }
}
