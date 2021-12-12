package com.example.proveedores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class adaptadorClientes extends RecyclerView.Adapter<adaptadorClientes.ClientesViewHolder> {
    ArrayList<metodosCliente> listadoClientes;
    public adaptadorClientes(ArrayList<metodosCliente> listadoClientes)
    {
        this.listadoClientes = listadoClientes;
    }
    @NonNull
    @Override
    public adaptadorClientes.ClientesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_listado_clientes, null, false);
        return new ClientesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adaptadorClientes.ClientesViewHolder holder, int position) {
        holder.compania.setText(listadoClientes.get(position).getNombreCompania());
        holder.contacto.setText(listadoClientes.get(position).getNombreContacto());
        holder.cargo.setText(listadoClientes.get(position).getCargoContacto());
        holder.image.setImageURI(Uri.parse(listadoClientes.get(position).getPicturePath()));
    }

    @Override
    public int getItemCount() {
        return listadoClientes.size();
    }

    public class ClientesViewHolder extends RecyclerView.ViewHolder {
        TextView compania, contacto,cargo;
        ImageView image;
        public ClientesViewHolder(@NonNull View itemView) {
            super(itemView);
            compania = itemView.findViewById(R.id.vistaNombreCompania);
            contacto = itemView.findViewById(R.id.vistaContacto);
            cargo = itemView.findViewById(R.id.vistaCargo);
            image = itemView.findViewById(R.id.imageViewMain);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context pantVisualizar = v.getContext();
                    Intent i = new Intent(pantVisualizar, verProveedorIndividual.class);
                    i.putExtra("codigoCliente", listadoClientes.get(getAdapterPosition()).getId());;
                    pantVisualizar.startActivity(i);
                }
            });
        }
    }
}
