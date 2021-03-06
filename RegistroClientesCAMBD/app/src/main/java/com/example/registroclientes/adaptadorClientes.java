package com.example.registroclientes;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class adaptadorClientes extends RecyclerView.Adapter<adaptadorClientes.ClientesViewHolder> {
    ArrayList<metodosCliente> listadoClientes;

    public adaptadorClientes(ArrayList<metodosCliente> listadoClientes){
       this.listadoClientes = listadoClientes;
    }
    @NonNull
    @Override
    public adaptadorClientes.ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Aca le indicamos que trabaje sobre la pantalla de listado clientes
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listado_clientes, null, false);
        //Retornando el contenido de la pantalla
        return new ClientesViewHolder(view);
    }

    @Override
    public int getItemCount() {
        //En esta parte se retornan la cantidad de registros en la tabla
        return listadoClientes.size();
    }

    @Override
    //Indica que cada elemento que se duplique lo ponga en la recycled View
    public void onBindViewHolder(@NonNull adaptadorClientes.ClientesViewHolder holder, int position) {
        holder.vistaNombres.setText(listadoClientes.get(position).getNombres());
        holder.vistaPais.setText(listadoClientes.get(position).getPais());
        holder.vistaEmpresa.setText(listadoClientes.get(position).getEmpresa());
        holder.viewImage.setImageURI(Uri.parse(listadoClientes.get(position).getPicturePath()));
    }


    public class ClientesViewHolder extends RecyclerView.ViewHolder {
        TextView vistaNombres,vistaPais, vistaEmpresa;
        ImageView viewImage;
        public ClientesViewHolder(@NonNull View itemView) {
            super(itemView);
            //Enlazando con la interfaz gr??fica
            vistaNombres = itemView.findViewById(R.id.vistaClientesNombre);
            vistaPais = itemView.findViewById(R.id.vistaClientePais);
            vistaEmpresa = itemView.findViewById(R.id.vistaClienteEmpresa);
            viewImage = itemView.findViewById(R.id.viewPrevMain);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context pantVisualizar = v.getContext();
                    Intent i = new Intent(pantVisualizar, verClienteIndividual.class);
                    i.putExtra("codigoCliente", listadoClientes.get(getAdapterPosition()).getId());
                    pantVisualizar.startActivity(i);
                }
            });
        }
    }

}