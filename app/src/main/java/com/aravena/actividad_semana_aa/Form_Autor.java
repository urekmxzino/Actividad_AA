package com.aravena.actividad_semana_aa;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.aravena.actividad_semana_aa.models.Autor;
import com.aravena.actividad_semana_aa.sqlite.DbAutor;

public class Form_Autor extends AppCompatActivity {
    EditText txtNombreA;
    EditText txtApellidoA;
    EditText txtNacionalidadA;
    Button addA_button;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_autor);

        txtNombreA = findViewById(R.id.txtNombreA);
        txtApellidoA = findViewById(R.id.txtApellidoA);
        txtNacionalidadA = findViewById(R.id.txtNacionalidadA);
        addA_button = findViewById(R.id.addA_button);



        addA_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombreA.getText().toString();
                String apellido = txtApellidoA.getText().toString();
                String nacionalidad = txtNacionalidadA.getText().toString();

                Autor a = new Autor(nombre,apellido,nacionalidad);
                DbAutor dbaut = new DbAutor(getApplicationContext());
                long id = dbaut.insertarAutor(a);
                if( id >= 0 ){
                    Toast.makeText(Form_Autor.this,
                            nombre+" insertado", Toast.LENGTH_LONG).show();
                    txtNombreA.setText("");
                }else{
                    Toast.makeText(Form_Autor.this,
                            "Error al insertar", Toast.LENGTH_LONG).show();
                }

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