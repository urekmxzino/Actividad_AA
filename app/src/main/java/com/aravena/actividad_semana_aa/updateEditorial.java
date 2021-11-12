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

public class updateEditorial extends AppCompatActivity {
    EditText txtNombre,txtNacionalidad;
    Button update,delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_editorial);
        txtNombre = findViewById(R.id.txtNombreEU);
        txtNacionalidad = findViewById(R.id.txtNacionalidadEU);
        update = findViewById(R.id.update_buttonEdit);
        delete = findViewById(R.id.deleteEdit_button);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        Editorial editorial = (Editorial) bundle.get("Editorial");
        txtNombre.setText(editorial.getNombre());
        txtNacionalidad.setText(editorial.getNacionalidad());



        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbEditorial dbedit = new DbEditorial(getApplicationContext());
                int id = editorial.getId();
                String nombre = txtNombre.getText().toString();
                String nacionalidad = txtNacionalidad.getText().toString();

                Editorial EditorialEdito = new Editorial(id,nombre,nacionalidad);
                dbedit.actualizarEditorial(EditorialEdito);
                Toast.makeText(getApplicationContext(), "Editorial modificada", Toast.LENGTH_SHORT).show();

            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DbEditorial dbedit = new DbEditorial(getApplicationContext());
                dbedit.eliminarEditorial(editorial.getId());
                Toast.makeText(getApplicationContext(), "Editorial eliminada", Toast.LENGTH_SHORT).show();
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