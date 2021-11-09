package com.example.gohorsedrink.Adapater;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gohorsedrink.Activity.DetalhesActivity;
import com.example.gohorsedrink.Domain.DrinkDomain;
import com.example.gohorsedrink.R;

import java.util.ArrayList;

public class MaisPedidosAdapter extends RecyclerView.Adapter<MaisPedidosAdapter.ViewHolder> {
    ArrayList<DrinkDomain> drinkDomains;

    public MaisPedidosAdapter(ArrayList<DrinkDomain> drinkDomains) {
        this.drinkDomains =drinkDomains;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_mais_pedidos, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.titulo.setText(drinkDomains.get(position).getTitle());

        holder.taxa.setText(String.valueOf(drinkDomains.get(position).getFee()));

        int drawableResourceId=holder.itemView.getContext().getResources().getIdentifier(drinkDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.imagem);

        holder.btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), DetalhesActivity.class);
                intent.putExtra("object", drinkDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {

        return drinkDomains.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView titulo, taxa;
        ImageView imagem;
        TextView btn_Add;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titulo = itemView.findViewById(R.id.titulo);
            taxa = itemView.findViewById(R.id.txtTaxa);
            imagem = itemView.findViewById(R.id.imagem);
            btn_Add = itemView.findViewById(R.id.btn_add);
        }
    }
}

