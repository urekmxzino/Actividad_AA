package com.aravena.actividad_semana_aa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.aravena.actividad_semana_aa.models.Autor;
import com.aravena.actividad_semana_aa.models.Editorial;
import com.aravena.actividad_semana_aa.models.Estante;
import com.aravena.actividad_semana_aa.models.Libro;
import com.aravena.actividad_semana_aa.sqlite.DbAutor;
import com.aravena.actividad_semana_aa.sqlite.DbEditorial;
import com.aravena.actividad_semana_aa.sqlite.DbEstante;
import com.aravena.actividad_semana_aa.sqlite.DbLibro;

import java.util.ArrayList;

public class Form_Libro extends AppCompatActivity {
    EditText txtTituloL, txtDescripcionL, txtFechaL, txtCopiasL, txtCantPL;
    Button addL_button;
    Spinner spaut, spedit, spest;

    public void cargarSpinner() {
        DbAutor dbaut = new DbAutor(this);// instancia
        ArrayList<Autor> autores = dbaut.getAutores();
        if (autores != null) { // si categorias no es vacío
            ArrayAdapter<Autor> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item, autores);
            spaut.setAdapter(adapter);
        }

        DbEditorial dbedit = new DbEditorial(this);// instancia
        ArrayList<Editorial> editoriales = dbedit.getEditoriales();
        if (editoriales != null) { // si categorias no es vacío
            ArrayAdapter<Editorial> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item, editoriales);
            spedit.setAdapter(adapter);
        }


        DbEstante dbest = new DbEstante(this);// instancia
        ArrayList<Estante> estantes = dbest.getEstantes();
        if (estantes != null) { // si categorias no es vacío
            ArrayAdapter<Estante> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_spinner_dropdown_item, estantes);
            spest.setAdapter(adapter);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_libro);

        txtTituloL = findViewById(R.id.txtTituloL);
        txtDescripcionL = findViewById(R.id.txtDescripcionL);
        txtFechaL = findViewById(R.id.txtFechaL);
        txtCopiasL = findViewById(R.id.txtCopiasL);
        txtCantPL = findViewById(R.id.txtCantPL);
        spaut = findViewById(R.id.spAut);
        spedit = findViewById(R.id.spEdit);
        spest = findViewById(R.id.spEst);

        addL_button = findViewById(R.id.addL_button);

        cargarSpinner();

        addL_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo = txtTituloL.getText().toString();
                String descripcion = txtDescripcionL.getText().toString();
                String fecha = txtFechaL.getText().toString();
                int copias = Integer.parseInt(txtCopiasL.getText().toString());
                int cantP = Integer.parseInt(txtCantPL.getText().toString());
                Autor autor = (Autor) spaut.getSelectedItem();
                Editorial editorial = (Editorial) spedit.getSelectedItem();
                Estante estante = (Estante) spest.getSelectedItem();


                Libro l = new Libro(titulo, descripcion, fecha, copias, cantP, autor, editorial, estante);

                DbLibro db = new DbLibro(getApplicationContext());

                long id = db.insertarLibro(l);
                if (id >= 0) {
                    Toast.makeText(Form_Libro.this,
                            titulo + " insertado", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(Form_Libro.this,
                            "Error al insertar", Toast.LENGTH_LONG).show();
                }

            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item){
        // condicional - switch

        switch (item.getItemId()) { // tomamos el id del item seleccionado


            case R.id.menu_inicio:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_Libro:
                Intent intent1 = new Intent(this, Form_Libro.class);
                startActivity(intent1);
                return true;

            case R.id.menu_Autor:
                Intent intent2 = new Intent(this, Form_Autor.class);
                startActivity(intent2);
                return true;
            case R.id.menu_Estante:
                Intent intent3 = new Intent(this, Form_Estante.class);
                startActivity(intent3);
                return true;
            case R.id.menu_Editorial:
                Intent intent4 = new Intent(this, Form_Editorial.class);
                startActivity(intent4);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}