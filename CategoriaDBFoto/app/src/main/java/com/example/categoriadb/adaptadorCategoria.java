package com.example.categoriadb;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class adaptadorCategoria extends RecyclerView.Adapter<adaptadorCategoria.ClientesViewHolder> {
    ArrayList<metodosCategoria> listadoCategoria;
    public adaptadorCategoria(ArrayList<metodosCategoria> listadoCategoria) {
        this.listadoCategoria = listadoCategoria;
    }
    @NonNull
    @Override
    public ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listado_categoria, null,false);
        return new ClientesViewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull ClientesViewHolder holder, int position) {
        holder.NombreCategoria.setText(listadoCategoria.get(position).getNombreCategoria());
        holder.Descripcion.setText(listadoCategoria.get(position).getDescripcion());
        holder.Ubicacion.setText(listadoCategoria.get(position).getUbicacion());
        holder.imagenPrevia.setImageURI(Uri.parse(listadoCategoria.get(position).getPicturePath()));
    }

    @Override
    public int getItemCount() {
        return listadoCategoria.size();
    }

    public class ClientesViewHolder extends RecyclerView.ViewHolder {
        TextView NombreCategoria, Descripcion, Ubicacion;
        ImageView imagenPrevia;
        public ClientesViewHolder(@NonNull View itemView) {
            super(itemView);
            NombreCategoria = itemView.findViewById(R.id.vistaNombreCategoria);
            Descripcion = itemView.findViewById(R.id.vistaDescripcion);
            Ubicacion = itemView.findViewById(R.id.vistaUbicacion);
            imagenPrevia = itemView.findViewById(R.id.vistaImagePrevia);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context pantVisualizar = v.getContext();
                    Intent i = new Intent(pantVisualizar, verCategoriaIndividual.class);
                    i.putExtra("codigoCategoria", listadoCategoria.get(getAdapterPosition()).getId());
                    pantVisualizar.startActivity(i);
                }
            });
        }
    }
}
