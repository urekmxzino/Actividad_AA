package com.aravena.actividad_semana_aa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aravena.actividad_semana_aa.adapter.AutorAdapter;
import com.aravena.actividad_semana_aa.adapter.EstanteAdapter;
import com.aravena.actividad_semana_aa.adapter.LibroAdapter;
import com.aravena.actividad_semana_aa.models.Autor;
import com.aravena.actividad_semana_aa.models.Estante;
import com.aravena.actividad_semana_aa.sqlite.DbAutor;
import com.aravena.actividad_semana_aa.sqlite.DbEstante;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EstanteList extends AppCompatActivity {
    RecyclerView recyclerEstante;
    FloatingActionButton add_buttonEstante;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estante_list);

        recyclerEstante = findViewById(R.id.recyclerEstante);
        add_buttonEstante = findViewById(R.id.add_buttonEstante);

        ArrayList<Estante> array = new DbEstante(getApplicationContext()).getEstantes();
        recyclerEstante.setLayoutManager(new LinearLayoutManager(this));


        EstanteAdapter adapter = new EstanteAdapter(array);
        recyclerEstante.setAdapter(adapter);
        add_buttonEstante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EstanteList.this, Form_Estante.class);
                startActivity(intent1);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // condicional - switch

        switch (item.getItemId()) { // tomamos el id del item seleccionado
            case R.id.menu_inicio:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;


            case R.id.menu_Libro:
                Intent intent1 = new Intent(this, LibroList.class);
                startActivity(intent1);
                return true;

            case R.id.menu_Autor:
                Intent intent2 = new Intent(this, AutorList.class);
                startActivity(intent2);
                return true;
            case R.id.menu_Estante:
                Intent intent3 = new Intent(this, EstanteList.class);
                startActivity(intent3);
                return true;
            case R.id.menu_Editorial:
                Intent intent4 = new Intent(this, EditorialList.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}