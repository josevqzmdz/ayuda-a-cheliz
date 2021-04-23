package com.example.recyclerview_ejercicio_2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorTitulares extends RecyclerView.Adapter<AdaptadorTitulares.TitularesViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    // string para depuracion

    private ArrayList<Titular> datos;

    public AdaptadorTitulares(ArrayList<Titular> datos){
        this.datos = datos;
    }

    @Override
    public TitularesViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_titular, viewGroup, false);
        TitularesViewHolder tvh = new TitularesViewHolder(itemView);

        return tvh;
    }

    @Override
    public void onBindViewHolder(TitularesViewHolder viewHolder,int pos) {
        Titular item = datos.get(pos);
        viewHolder.bindTitular(item);
    }

    @Override
    public int getItemCount() {
        return datos.size();
    }

    // https://www.youtube.com/watch?v=Vyqz_-sJGFk&t=555s
    public static class TitularesViewHolder extends RecyclerView.ViewHolder{

        private TextView txtTitulo = (TextView) itemView.findViewById(R.id.LblTitulo);
        private TextView txtSubitulo = (TextView) itemView.findViewById(R.id.LblSubTitulo);

        public void onCreateViewHolder(){

        }

        public TitularesViewHolder(View itemView) {
            super(itemView);
            TextView txtTitulo = null;
            TextView txtSubtitulo = null;
            txtTitulo = itemView.findViewById(R.id.LblTitulo);
            txtSubtitulo = itemView.findViewById(R.id.LblSubTitulo);
        }

        public void bindTitular(Titular t) {
            txtTitulo.setText(t.getTitulo());
            txtSubitulo.setText(t.getSubtitulo());
        }

    }

}
