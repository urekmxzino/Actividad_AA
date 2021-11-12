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
import com.aravena.actividad_semana_aa.adapter.LibroAdapter;
import com.aravena.actividad_semana_aa.models.Autor;
import com.aravena.actividad_semana_aa.sqlite.DbAutor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class AutorList extends AppCompatActivity {
    RecyclerView recyclerAutor;
    FloatingActionButton add_buttonAutor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autor_list);

        recyclerAutor = findViewById(R.id.recyclerAutor);
        add_buttonAutor = findViewById(R.id.add_buttonAutor);

        ArrayList<Autor> array = new DbAutor(getApplicationContext()).getAutores();
        recyclerAutor.setLayoutManager(new LinearLayoutManager(this));


        AutorAdapter adapter = new AutorAdapter(array);
        recyclerAutor.setAdapter(adapter);
        add_buttonAutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(AutorList.this, Form_Autor.class);
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