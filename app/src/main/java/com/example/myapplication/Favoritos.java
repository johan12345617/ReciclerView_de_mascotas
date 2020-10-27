package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
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
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        lista = (RecyclerView) findViewById(R.id.rvlistaFavoritos);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        lista.setLayoutManager(llm);
        iniciarAdaptador();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.principal:
                Intent i = new Intent(this,MainActivity.class);
                startActivity(i);
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    public void iniciarAdaptador(){
        MascotaAdaptador adaptador = new MascotaAdaptador(mascotas, new ClickListener() {
            @Override
            public void onPositionClicked(int position) {
            }
        });
        lista.setAdapter(adaptador);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_favoritos, menu);
        return super.onCreateOptionsMenu(menu);
    }

}