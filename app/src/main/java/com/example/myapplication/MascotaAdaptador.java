package com.example.myapplication;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MascotaAdaptador extends RecyclerView.Adapter<MascotaAdaptador.MascotaViewHolder> {
    private final ClickListener listener;
    ArrayList<Mascota> mascotas;

    MascotaAdaptador(ArrayList<Mascota> mascotas, ClickListener listener){
        this.listener = listener;
        this.mascotas=mascotas;
    }

    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota,parent,false);
        return new MascotaViewHolder(v,listener);
    }

    @Override
    public void onBindViewHolder(@NonNull MascotaViewHolder holder, int position) {
        Mascota mascota = mascotas.get(position);
        if(mascota.isFavorito()){
            holder.favorito.setBackgroundResource(R.drawable.huesodorado);
            holder.favorito.setTag(R.drawable.huesodorado);
        }
        else{
            holder.favorito.setBackgroundResource(R.drawable.hueso);
            holder.favorito.setTag(R.drawable.hueso);
        }
        holder.fotoMascota.setImageResource(mascota.getFoto());
        holder.nombre.setText(mascota.getNombre());
        holder.rating.setText(String.valueOf(mascota.getRating()));
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    public static class MascotaViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView fotoMascota;
        private TextView nombre;
        private TextView rating;
        private ImageButton favorito;
        private WeakReference<ClickListener> listenerRef;

        MascotaViewHolder(View view,ClickListener listener){
            super(view);

            listenerRef = new WeakReference<>(listener);
            favorito = (ImageButton) itemView.findViewById(R.id.favorito);
            fotoMascota = (ImageView) itemView.findViewById(R.id.fotoMascota);
            nombre = (TextView) itemView.findViewById(R.id.nombreMascota);
            rating = (TextView) itemView.findViewById(R.id.rating);

            favorito.setOnClickListener(this);

        }

        public void onClick(View v) {

            if (v.getId() == favorito.getId() ) {
                Integer integer = (Integer) favorito.getTag();
                integer = integer == null ? 0 : integer;

                switch (integer){
                    case R.drawable.huesodorado:
                        favorito.setBackgroundResource(R.drawable.hueso);
                        favorito.setTag(R.drawable.hueso);
                        break;

                    case R.drawable.hueso:
                        favorito.setBackgroundResource(R.drawable.huesodorado);
                        favorito.setTag(R.drawable.huesodorado);
                        break;
                }
            }
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }

    }
}
