package com.intecap.carrosapp;

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

import com.airbnb.lottie.Lottie;
import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.imageview.ShapeableImageView;

import java.util.ArrayList;

public class adapter extends RecyclerView.Adapter<adapter.CarViewHoder> {
    ArrayList<methodCar> listadoCarros;

    public adapter(ArrayList<methodCar> listadoCarros)
    {
        this.listadoCarros =listadoCarros;
    }
    @NonNull
    @Override
    public adapter.CarViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_list_car, null, false);
        return new CarViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull adapter.CarViewHoder holder, int position) {
        holder.vistaMarca.setText(listadoCarros.get(position).getMarca());
        holder.vistaLinea.setText(listadoCarros.get(position).getLinea());
        holder.vistaModelo.setText(listadoCarros.get(position).getModelo());
        holder.animation(holder.vImage, R.raw.car);

    }

    @Override
    public int getItemCount() {
        return listadoCarros.size();
    }

    public class CarViewHoder extends RecyclerView.ViewHolder
    {
        TextView vistaMarca, vistaLinea, vistaModelo;
        LottieAnimationView vImage;
        public CarViewHoder(@NonNull View itemView) {
            super(itemView);
            vistaMarca = itemView.findViewById(R.id.listMarca);
            vistaLinea = itemView.findViewById(R.id.listLinea);
            vistaModelo = itemView.findViewById(R.id.listModelo);
            vImage = itemView.findViewById(R.id.listLottieImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context pantVizualizar = v.getContext();
                    Intent i = new Intent(pantVizualizar, verCarIndividual.class);
                    i.putExtra("codigoCar", listadoCarros.get(getAdapterPosition()).getId());
                    pantVizualizar.startActivity(i);
                }
            });
        }
        public void animation(LottieAnimationView image, int Animation)
        {
            image.setAnimation(Animation);
            image.setRepeatCount(90);
            image.playAnimation();
        }
    }
}
