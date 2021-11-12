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
import com.aravena.actividad_semana_aa.models.Estante;
import com.aravena.actividad_semana_aa.sqlite.DbEditorial;
import com.aravena.actividad_semana_aa.sqlite.DbEstante;

public class Form_Estante extends AppCompatActivity {
    EditText txtLetraE;
    EditText txtNumeroE;
    EditText txtColorE;
    Button addEstante_button;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_estante);

        txtLetraE = findViewById(R.id.txtLetraE);
        txtNumeroE = findViewById(R.id.txtNumE);
        txtColorE = findViewById(R.id.txtColorE);
        addEstante_button = findViewById(R.id.addEstante_button);



        addEstante_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String letra = txtLetraE.getText().toString();
                int numero = Integer.parseInt(txtNumeroE.getText().toString());
                String color = txtColorE.getText().toString();


                Estante e = new Estante(letra,numero,color);
                DbEstante dbest = new DbEstante(getApplicationContext());
                long id = dbest.insertarEstante(e);
                if( id >= 0 ){
                    Toast.makeText(Form_Estante.this,
                            letra+" insertado", Toast.LENGTH_LONG).show();
                    txtLetraE.setText("");
                    txtNumeroE.setText("");
                    txtColorE.setText("");
                }else{
                    Toast.makeText(Form_Estante.this,
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