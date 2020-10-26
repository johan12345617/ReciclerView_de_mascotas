package com.example.myapplication;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.Serializable;

public class Mascota implements Parcelable {
    private int rating;
    private String nombre;
    private int foto;
    private boolean favorito=false;

    Mascota(int rating ,String nombre, int foto,boolean favorito){
        this.rating=rating;
        this.foto= foto;
        this.nombre=nombre;
        this.setFavorito(favorito);
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    Mascota(Parcel fuente){
        this.rating = fuente.readInt();
        this.nombre = fuente.readString();
        this.foto = fuente.readInt();
        this.favorito = fuente.readBoolean();
    }

    @Override
    public int describeContents(){
        return 0;
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public void writeToParcel(Parcel dest,int bandera){
        dest.writeInt(rating);
        dest.writeString(nombre);
        dest.writeInt(foto);
        dest.writeBoolean(favorito);
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public boolean isFavorito() {
        return favorito;
    }

    public void setFavorito(boolean favorito) {
        this.favorito = favorito;
    }

    public static final Creator<Mascota> CREATOR = new Creator<Mascota>() {
        @Override
        public Mascota[] newArray(int size) {
            return new Mascota[size];
        }

        @Override
        public Mascota createFromParcel(Parcel source) {
            return new Mascota(source);
        }
    };

}
