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

import com.aravena.actividad_semana_aa.adapter.EditorialAdapter;
import com.aravena.actividad_semana_aa.models.Editorial;
import com.aravena.actividad_semana_aa.sqlite.DbEditorial;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class EditorialList extends AppCompatActivity {
    RecyclerView recyclerEditorial;
    FloatingActionButton add_buttonEditorial;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editorial_list);

        recyclerEditorial = findViewById(R.id.recyclerEditorial);
        add_buttonEditorial = findViewById(R.id.add_buttonEditorial);

        ArrayList<Editorial> array = new DbEditorial(getApplicationContext()).getEditoriales();
        recyclerEditorial.setLayoutManager(new LinearLayoutManager(this));


        EditorialAdapter adapter = new EditorialAdapter(array);
        recyclerEditorial.setAdapter(adapter);
        add_buttonEditorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(EditorialList.this, Form_Editorial.class);
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