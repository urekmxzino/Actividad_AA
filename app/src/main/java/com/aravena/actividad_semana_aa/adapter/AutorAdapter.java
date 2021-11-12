package com.aravena.actividad_semana_aa.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aravena.actividad_semana_aa.R;
import com.aravena.actividad_semana_aa.UpdateLibro;
import com.aravena.actividad_semana_aa.models.Autor;
import com.aravena.actividad_semana_aa.updateAutor;

import java.util.ArrayList;

public class AutorAdapter extends RecyclerView.Adapter<AutorAdapter.ViewHolder> {
    ArrayList<Autor> listaAutores;
    private Context context;
    public AutorAdapter(ArrayList<Autor> lista){
        this.listaAutores = lista;
    }

    @NonNull
    @Override
    public AutorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_autor,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull AutorAdapter.ViewHolder holder, int position){

        holder.cargarAutor(listaAutores.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), updateAutor.class);
                intent.putExtra("Autor",listaAutores.get(position));
                holder.itemView.getContext().startActivity(intent);


            }
        });
    }
    @Override
    public int getItemCount(){
        return listaAutores.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNombreAutor,txtApellidoAutor,txtNacionalidadAutor;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtNombreAutor = itemView.findViewById((R.id.txtNombreAutor));
            txtApellidoAutor = itemView.findViewById((R.id.txtApellidoAutor));
            txtNacionalidadAutor = itemView.findViewById((R.id.txtNacionalidadAutor));


        }

        public void cargarAutor(Autor a){
            txtNombreAutor.setText(a.getNombre());
            txtApellidoAutor.setText(a.getApellido());
            txtNacionalidadAutor.setText(a.getNacionalidad());


        }
    }
}

