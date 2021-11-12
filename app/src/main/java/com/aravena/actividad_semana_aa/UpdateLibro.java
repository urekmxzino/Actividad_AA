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

public class UpdateLibro extends AppCompatActivity {
  EditText txtTitulo, txtDescripcion, txtFechaP, txtCopias, txtCantP;
  Spinner spAut, spEdit, spEst;
  Button update, delete;
  ArrayList<Autor> autores;
  ArrayList<Editorial> editoriales;
  ArrayList<Estante> estantes;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_update_libro);
    txtTitulo = findViewById(R.id.txtTituloLibroU);
    txtDescripcion = findViewById(R.id.txtDescripcionLibroU);
    txtFechaP = findViewById(R.id.txtFechaLU);
    txtCopias = findViewById(R.id.txtCopiasLU);
    txtCantP = findViewById(R.id.txtCantPLU);
    spAut = findViewById(R.id.spAutU);
    spEdit = findViewById(R.id.spEditU);
    spEst = findViewById(R.id.spEstU);
    update = findViewById(R.id.update_button);
    delete = findViewById(R.id.deleteL_button);

    cargarSpinner();

    Intent intent = getIntent();
    Bundle bundle = intent.getExtras();
    Libro libro = (Libro) bundle.get("Libro");
    txtTitulo.setText(libro.getTitulo());
    txtDescripcion.setText(libro.getDescripcion());
    txtFechaP.setText(libro.getFechaP());
    txtCopias.setText(String.valueOf(libro.getCopias()));
    txtCantP.setText(String.valueOf(libro.getCantP()));
    int index = indexAutor(libro);
    spAut.setSelection(index);
    int indexEdit = indexEditorial(libro);
    spEdit.setSelection(indexEdit);
    int indexEst = indexEstante(libro);
    spEst.setSelection(indexEst);

    update.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            DbLibro dblibro = new DbLibro(getApplicationContext());
            int id = libro.getId();
            String titulo = txtTitulo.getText().toString();
            String descripcion = txtDescripcion.getText().toString();
            String fechaP = txtFechaP.getText().toString();
            int copias = Integer.parseInt(txtCopias.getText().toString());
            int cantP = Integer.parseInt(txtCantP.getText().toString());
            Autor autor = (Autor) spAut.getSelectedItem();
            Editorial editorial = (Editorial) spEdit.getSelectedItem();
            Estante estante = (Estante) spEst.getSelectedItem();
            Libro libroEdit =
                new Libro(
                    id, titulo, descripcion, fechaP, copias, cantP, autor, editorial, estante);

            dblibro.actualizarLibro(libroEdit);
            Toast.makeText(getApplicationContext(), "Libro modificado", Toast.LENGTH_SHORT).show();
          }
        });
    delete.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        DbLibro dbLibro = new DbLibro(getApplicationContext());
        dbLibro.eliminarLibro(libro.getId());
        Toast.makeText(getApplicationContext(), "Libro eliminado", Toast.LENGTH_SHORT).show();
      }
    });
  }

  public int indexAutor(Libro libro) {
    int index = 0;
    for (Autor autor : autores) {

      if (autor.toString().equals(libro.getAutor().toString())) {
        return index;
      } else {
        index += 1;
      }
    }
    return -1;
  }

  public int indexEditorial(Libro libro) {
    int index = 0;
    for (Editorial editorial : editoriales) {

      if (editorial.toString().equals(libro.getEditorial().toString())) {
        return index;
      } else {
        index += 1;
      }
    }
    return -1;
  }

  public int indexEstante(Libro libro) {
    int index = 0;
    for (Estante estante : estantes) {

      if (estante.toString().equals(libro.getEstante().toString())) {
        return index;
      } else {
        index += 1;
      }
    }
    return -1;
  }

  public void cargarSpinner() {
    DbAutor dbaut = new DbAutor(this); // instancia
    autores = dbaut.getAutores();
    if (autores != null) { // si categorias no es vacío
      ArrayAdapter<Autor> adapter =
          new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, autores);
      spAut.setAdapter(adapter);
    }

    DbEditorial dbedit = new DbEditorial(this); // instancia
    editoriales = dbedit.getEditoriales();
    if (editoriales != null) { // si categorias no es vacío
      ArrayAdapter<Editorial> adapter =
          new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, editoriales);
      spEdit.setAdapter(adapter);
    }

    DbEstante dbest = new DbEstante(this); // instancia
    estantes = dbest.getEstantes();
    if (estantes != null) { // si categorias no es vacío
      ArrayAdapter<Estante> adapter =
          new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, estantes);
      spEst.setAdapter(adapter);
    }
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
