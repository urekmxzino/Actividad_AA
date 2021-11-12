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
import android.widget.Button;
import android.widget.Toast;

import com.aravena.actividad_semana_aa.adapter.LibroAdapter;
import com.aravena.actividad_semana_aa.models.Libro;
import com.aravena.actividad_semana_aa.sqlite.DbLibro;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class LibroList extends AppCompatActivity {
    RecyclerView recyclerView;
    FloatingActionButton add_button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro_list);

        recyclerView = findViewById(R.id.recyclerLibroList);
        add_button = findViewById(R.id.add_buttonAutor);


        ArrayList<Libro> array = new DbLibro(getApplicationContext()).getLibros();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        LibroAdapter adapter = new LibroAdapter(array);
        recyclerView.setAdapter(adapter);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(LibroList.this, Form_Libro.class);
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
