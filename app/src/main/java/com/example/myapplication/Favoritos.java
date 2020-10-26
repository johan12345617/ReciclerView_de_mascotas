package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Favoritos extends AppCompatActivity{

    ArrayList<Mascota> mascotas = new ArrayList<>();
    private RecyclerView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent parametros = this.getIntent();
        if(parametros !=null){
            this.mascotas = parametros.getParcelableArrayListExtra("lista");
        }
        cargarMascotas();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);
        this.setTitle(R.string.favoritos);
        lista = (RecyclerView) findViewById(R.id.rvlistaFavoritos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lista.setLayoutManager(llm);
        iniciarAdaptador();
    }

    public void cargarMascotas(){
        int tamanio = this.mascotas.size();
        ArrayList<Mascota> temp = new ArrayList<>();
        for(int i=0;i<tamanio;i++){
            Mascota mascota = this.mascotas.get(i);
            if(mascota.isFavorito()){
                temp.add(mascota);
            }
        }
        this.mascotas = temp;
    }

    public void dialogo(View v){
    }

    public void iniciarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
            }
        });
        lista.setAdapter(adaptador);
    }

}