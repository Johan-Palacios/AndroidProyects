package com.example.calculadoraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class pantallaresta extends AppCompatActivity {
    EditText primerCampoUsuario, segundoCampoUsuario;
    TextView vistaUsuarioResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantallaresta);

        primerCampoUsuario = findViewById(R.id.campoPrimeroResta);
        segundoCampoUsuario = findViewById(R.id.campoSegundoResta);
        vistaUsuarioResultado=findViewById(R.id.resultadoRestaOperacion);
    }

    public void realizarResta(View v)
    {
        int resultado = Integer.parseInt(primerCampoUsuario.getText().toString()) - Integer.parseInt(segundoCampoUsuario.getText().toString());
        vistaUsuarioResultado.setText(resultado+"");
    }
    public void enviarPantallaMultiplicacion(View v)
    {
        Intent i = new Intent(pantallaresta.this, pantallaMultiplicacion.class);
        startActivity(i);
    }
    public void enviarPantallaSuma(View v)
    {
        Intent i = new Intent(pantallaresta.this, MainActivity.class);
        startActivity(i);
    }
    public void enviarPantallaDivision(View v)
    {
        Intent i = new Intent(pantallaresta.this, pantalladivision.class);
        startActivity(i);
    }
}
