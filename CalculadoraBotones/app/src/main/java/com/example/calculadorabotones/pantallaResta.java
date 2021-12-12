package com.example.calculadorabotones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class pantallaResta extends AppCompatActivity {
    String cadenaNumerica = "";
    int primerValor = 0;
    int segundoValor = 0;
    int resultadoOperacion = 0;
    EditText visualizarResultado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_resta);
        visualizarResultado = findViewById(R.id.resultadoResta);
    }
    public void establecerUno(View v)
    {
        //Setiar el numero a la variable de Text
        cadenaNumerica = cadenaNumerica +"1";
        visualizarResultado.setText(cadenaNumerica);
    }
    public void establecerDos(View v)
    {
        cadenaNumerica = cadenaNumerica +"2";
        visualizarResultado.setText(cadenaNumerica);
    }
    public void establecerTres(View v)
    {
        cadenaNumerica = cadenaNumerica +"3";
        visualizarResultado.setText(cadenaNumerica);
    }
    public void establecerCuatro(View v)
    {
        cadenaNumerica = cadenaNumerica +"4";
        visualizarResultado.setText(cadenaNumerica);
    }
    public void establecerCinco(View v)
    {
        cadenaNumerica = cadenaNumerica +"5";
        visualizarResultado.setText(cadenaNumerica);
    }
    public void establecerSeis(View v)
    {
        cadenaNumerica = cadenaNumerica +"6";
        visualizarResultado.setText(cadenaNumerica);
    }
    public void establecerSiete(View v)
    {
        cadenaNumerica = cadenaNumerica +"7";
        visualizarResultado.setText(cadenaNumerica);
    }
    public void establecerOcho(View v)
    {
        cadenaNumerica = cadenaNumerica +"8";
        visualizarResultado.setText(cadenaNumerica);
    }
    public void establecerNueve(View v)
    {
        cadenaNumerica = cadenaNumerica +"9";
        visualizarResultado.setText(cadenaNumerica);
    }
    public void establecerCero(View v)
    {
        cadenaNumerica = cadenaNumerica +"0";
        visualizarResultado.setText(cadenaNumerica);
    }
    public void aplicarResta(View v)
    {
        primerValor = Integer.parseInt(cadenaNumerica);
        visualizarResultado.setText("");
        cadenaNumerica = "";
    }
    public void realizarOperacion(View v)
    {
        segundoValor = Integer.parseInt(cadenaNumerica);
        resultadoOperacion = primerValor - segundoValor;
        visualizarResultado.setText(resultadoOperacion + "");
        cadenaNumerica = String.valueOf(resultadoOperacion);
    }
    public void enviarpantallaMulti(View v)
    {
        Intent i = new Intent(pantallaResta.this, pantallaMultiplicacion.class);
        startActivity(i);
    }
    public void enviarpantallaSuma(View v)
    {
        Intent i = new Intent(pantallaResta.this, MainActivity.class);
        startActivity(i);
    }
    public void enviarpantallaDivi(View v)
    {
        Intent i = new Intent(pantallaResta.this, pantallaDivision.class);
        startActivity(i);
    }
}