package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Switch;

import java.util.ArrayList;

public class Mascotas extends AppCompatActivity {
    ArrayList<Mascota> mascotas = new ArrayList<>();
    private RecyclerView listaMascotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascotas);
        cargarMascotas();
        this.setTitle(R.string.titulo);
        listaMascotas = (RecyclerView) findViewById(R.id.rvlistaMascotas);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
        iniciarAdaptador();
    }

    public void cargarMascotas(){
        this.mascotas.add(new Mascota(8,"Firualais",R.drawable.perro,true));
        this.mascotas.add(new Mascota(7,"doge",R.drawable.doge,false));
        this.mascotas.add(new Mascota(6,"nya",R.drawable.como_es_el_bulldog_ingles_1_655x368,true));
        this.mascotas.add(new Mascota(5,"gaea",R.drawable.descarga,false));
        this.mascotas.add(new Mascota(4,"guau",R.drawable.maxresdefault,true));
    }

    public void iniciarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
                if(mascotas.get(position).isFavorito()){
                    mascotas.get(position).setFavorito(false);
                }
                else{
                    mascotas.get(position).setFavorito(true);
                }
            }
        });
        listaMascotas.setAdapter(adaptador);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite:
                Intent i = new Intent(this,Favoritos.class);
                i.putParcelableArrayListExtra("lista",this.mascotas);
                startActivity(i);
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}