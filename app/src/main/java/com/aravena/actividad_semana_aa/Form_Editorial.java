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

import com.aravena.actividad_semana_aa.models.Editorial;
import com.aravena.actividad_semana_aa.sqlite.DbEditorial;

public class Form_Editorial extends AppCompatActivity {
    EditText txtNombreE;
    EditText txtNacionalidadE;
    Button addE_button;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_editorial);

        txtNombreE = findViewById(R.id.txtNombreE);
        txtNacionalidadE = findViewById(R.id.txtNacionalidadE);
        addE_button = findViewById(R.id.addE_button);



        addE_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = txtNombreE.getText().toString();
                String nacionalidad = txtNacionalidadE.getText().toString();

                Editorial e = new Editorial(nombre,nacionalidad);
                DbEditorial dbedit = new DbEditorial(getApplicationContext());
                long id = dbedit.insertarEditorial(e);
                if( id >= 0 ){
                    Toast.makeText(Form_Editorial.this,
                            nombre+" insertado", Toast.LENGTH_LONG).show();
                    txtNombreE.setText("");
                }else{
                    Toast.makeText(Form_Editorial.this,
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