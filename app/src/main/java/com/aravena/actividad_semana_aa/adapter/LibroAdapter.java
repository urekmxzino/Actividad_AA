package com.aravena.actividad_semana_aa.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aravena.actividad_semana_aa.R;
import com.aravena.actividad_semana_aa.models.Libro;

import java.util.ArrayList;

public class LibroAdapter extends RecyclerView.Adapter<LibroAdapter.ViewHolder> {
    ArrayList<Libro> listaLibros;
    public LibroAdapter(ArrayList<Libro> lista){
        this.listaLibros = lista;
    }

    @NonNull
    @Override
    public LibroAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_libro,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull LibroAdapter.ViewHolder holder, int position){
        holder.cargarLibro(listaLibros.get(position));
    }
    @Override
    public int getItemCount(){
        return listaLibros.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtFechaP,txtTituloL,txtDescripcion,txtCopias,txtCantP,txtAutor,txtEditorial,txtEstante;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtFechaP = itemView.findViewById((R.id.txtFechaP));
            txtTituloL = itemView.findViewById((R.id.txtTituloL));
            txtDescripcion = itemView.findViewById((R.id.txtDescripcion));
            txtCopias = itemView.findViewById((R.id.txtCopias));
            txtCantP = itemView.findViewById((R.id.txtCantP));
            txtAutor = itemView.findViewById((R.id.txtAutor));
            txtEditorial = itemView.findViewById((R.id.txtEditorial));
            txtEstante = itemView.findViewById((R.id.txtEstante));
        }

        public void cargarLibro(Libro l){
            txtFechaP.setText(l.getFechaP());
            txtTituloL.setText(l.getTitulo());
            txtDescripcion.setText(l.getDescripcion());
            txtCopias.setText(String.valueOf(l.getCopias()));
            txtCantP.setText(String.valueOf(l.getCantP()));
            txtAutor.setText(l.getAutor().toString());
            txtEditorial.setText(l.getEditorial().toString());

           txtEstante.setText(l.getEstante().toString());
        }
    }
}
