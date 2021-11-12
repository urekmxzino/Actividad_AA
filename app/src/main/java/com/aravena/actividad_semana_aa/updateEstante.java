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

import com.aravena.actividad_semana_aa.models.Estante;
import com.aravena.actividad_semana_aa.sqlite.DbEstante;

public class updateEstante extends AppCompatActivity {
  EditText txtLetra, txtNumero, txtColor;
  Button update, delete;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_update_estante);
    txtLetra = findViewById(R.id.txtLetraEU);
    txtNumero = findViewById(R.id.txtNumEU);
    txtColor = findViewById(R.id.txtColorEU);
    update = findViewById(R.id.update_buttonEst);
    delete = findViewById(R.id.delete_buttonEst);

    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();
    Estante estante = (Estante) bundle.get("Estante");
    txtLetra.setText(estante.getLetra());
    txtNumero.setText(estante.getNumero());
    txtColor.setText(estante.getColor());

    update.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            DbEstante dbest = new DbEstante(getApplicationContext());
            int id = estante.getId();
            String letra = txtLetra.getText().toString();
            int numero = Integer.parseInt(txtNumero.getText().toString());
            String color = txtColor.getText().toString();

            Estante estanteEdit = new Estante(id, letra, numero, color);
            dbest.actualizarEstante(estanteEdit);
            Toast.makeText(getApplicationContext(), "Estante modificado", Toast.LENGTH_SHORT).show();
          }
        });

    delete.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            DbEstante dbest = new DbEstante(getApplicationContext());
            dbest.eliminarEstante(estante.getId());
            Toast.makeText(getApplicationContext(), "Estante eliminado", Toast.LENGTH_SHORT).show();
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
