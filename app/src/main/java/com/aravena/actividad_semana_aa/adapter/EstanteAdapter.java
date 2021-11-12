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
import com.aravena.actividad_semana_aa.models.Estante;
import com.aravena.actividad_semana_aa.updateAutor;
import com.aravena.actividad_semana_aa.updateEstante;

import java.util.ArrayList;

public class EstanteAdapter extends RecyclerView.Adapter<EstanteAdapter.ViewHolder> {
    ArrayList<Estante> listaEstantes;
    private Context context;
    public EstanteAdapter(ArrayList<Estante> lista){
        this.listaEstantes = lista;
    }

    @NonNull
    @Override
    public EstanteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_estante,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(@NonNull EstanteAdapter.ViewHolder holder, int position){

        holder.cargarEstante(listaEstantes.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), updateEstante.class);
                intent.putExtra("Estante",listaEstantes.get(position));
                holder.itemView.getContext().startActivity(intent);


            }
        });
    }
    @Override
    public int getItemCount(){
        return listaEstantes.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtLetraEstante,txtNumeroEstante,txtColorEstante;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            txtLetraEstante = itemView.findViewById((R.id.txtLetraE));
            txtNumeroEstante = itemView.findViewById((R.id.txtNumeroEstante));
            txtColorEstante = itemView.findViewById((R.id.txtColorEstante));


        }

        public void cargarEstante(Estante e){
            txtLetraEstante.setText(e.getLetra());
            txtNumeroEstante.setText(String.valueOf(e.getNumero()));
            txtColorEstante.setText(e.getColor());


        }
    }
}

