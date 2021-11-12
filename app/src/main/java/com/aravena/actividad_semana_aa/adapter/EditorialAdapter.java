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
import com.aravena.actividad_semana_aa.models.Editorial;
import com.aravena.actividad_semana_aa.updateEditorial;

import java.util.ArrayList;

public class EditorialAdapter extends RecyclerView.Adapter<EditorialAdapter.ViewHolder> {
    ArrayList<Editorial> listaEditoriales;
    private Context context;
    public EditorialAdapter(ArrayList<Editorial> lista){
        this.listaEditoriales = lista;
    }

    @NonNull
    @Override
    public EditorialAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_editorial,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull EditorialAdapter.ViewHolder holder, int position){

        holder.cargarEditorial(listaEditoriales.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), updateEditorial.class);
                intent.putExtra("Editorial",listaEditoriales.get(position));
                holder.itemView.getContext().startActivity(intent);


            }
        });
    }
    @Override
    public int getItemCount(){
        return listaEditoriales.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtNombreEditorial,txtNacionalidadEditorial;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtNombreEditorial = itemView.findViewById((R.id.txtNombreEditorial));
            txtNacionalidadEditorial = itemView.findViewById((R.id.txtNacionalidadEditorial));


        }

        public void cargarEditorial(Editorial e){
            txtNombreEditorial.setText(e.getNombre());
            txtNacionalidadEditorial.setText(e.getNacionalidad());



        }
    }
}


