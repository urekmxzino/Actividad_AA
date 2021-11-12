package com.aravena.actividad_semana_aa.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aravena.actividad_semana_aa.R;
import com.aravena.actividad_semana_aa.UpdateLibro;
import com.aravena.actividad_semana_aa.models.Libro;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.ViewHolder> {
  ArrayList<Libro> listaLibros;
  ArrayList<Libro> listaOriginal;

  private Context context;

  public LibroAdapter(ArrayList<Libro> lista) {
    this.listaLibros = lista;
    listaOriginal = new ArrayList<>();
    listaOriginal.addAll(listaLibros);
  }

  @NonNull
  @Override
  public LibroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro, parent, false);
    ViewHolder holder = new ViewHolder(view);
    return holder;
  }

  @Override
  public void onBindViewHolder(@NonNull LibroAdapter.ViewHolder holder, int position) {

    holder.cargarLibro(listaLibros.get(position));
    holder.itemView.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent = new Intent(holder.itemView.getContext(), UpdateLibro.class);
            intent.putExtra("Libro", listaLibros.get(position));
            holder.itemView.getContext().startActivity(intent);
          }
        });
  }

  public void filtrado(String txtBuscar) {
    int longitud = txtBuscar.length();

    listaLibros.clear();
    listaLibros.addAll(listaOriginal);
    if (longitud != 0) {

      if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        List<Libro> coleccion =
            listaLibros.stream()
                .filter(i -> i.getTitulo().toLowerCase().contains(txtBuscar.toLowerCase()))
                .collect(Collectors.toList());
        List<Libro> coleccion1 =
                listaLibros.stream()
                        .filter(i -> i.getAutor().getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
        List<Libro> coleccion2 =
                listaLibros.stream()
                        .filter(i -> i.getEditorial().getNombre().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
        List<Libro> coleccion3 =
                listaLibros.stream()
                        .filter(i -> i.getEstante().getLetra().toLowerCase().contains(txtBuscar.toLowerCase()))
                        .collect(Collectors.toList());
        listaLibros.clear();
        listaLibros.addAll(coleccion);
        listaLibros.addAll(coleccion1);
        listaLibros.addAll(coleccion2);
        listaLibros.addAll(coleccion3);

      } else {
        for (Libro l : listaOriginal) {
          if (l.getTitulo().toLowerCase().contains(txtBuscar.toLowerCase())) {
            listaLibros.add(l);
          }
        }
      }
    }
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return listaLibros.size();
  }

  public class ViewHolder extends RecyclerView.ViewHolder {
    TextView txtFechaP,
        txtTituloL,
        txtDescripcion,
        txtCopias,
        txtCantP,
        txtAutor,
        txtEditorial,
        txtEstante;

    public ViewHolder(@NonNull View itemView) {
      super(itemView);
      txtFechaP = itemView.findViewById((R.id.txtNumeroEstante));
      txtTituloL = itemView.findViewById((R.id.txtTituloLibroU));
      txtDescripcion = itemView.findViewById((R.id.txtColorEstante));
      txtCopias = itemView.findViewById((R.id.txtCopias));
      txtCantP = itemView.findViewById((R.id.txtCantP));
      txtAutor = itemView.findViewById((R.id.txtAutor));
      txtEditorial = itemView.findViewById((R.id.txtEditorial));
      txtEstante = itemView.findViewById((R.id.txtEstante));
    }

    public void cargarLibro(Libro l) {
      txtFechaP.setText(l.getFechaP());
      txtTituloL.setText(l.getTitulo());
      txtDescripcion.setText(l.getDescripcion());
      txtCopias.setText(String.valueOf(l.getCopias()));
      txtCantP.setText(String.valueOf(l.getCantP()));
      txtAutor.setText(l.getAutor().getNombre());
      txtEditorial.setText(l.getEditorial().getNombre());
      txtEstante.setText(l.getEstante().getLetra());
    }
  }
}
