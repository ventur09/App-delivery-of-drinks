package com.example.gohorsedrink.Adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gohorsedrink.Domain.DrinkDomain;
import com.example.gohorsedrink.Helper.GestaoCard;
import com.example.gohorsedrink.Interface.AlterarNumeroItemsLista;
import com.example.gohorsedrink.R;

import java.util.ArrayList;

public class CartListAdapter extends RecyclerView.Adapter<CartListAdapter.ViewHolder> {
    private ArrayList<DrinkDomain> drinkDomains;
    private GestaoCard managementCart;
    private AlterarNumeroItemsLista alterarNumeroItemsLista;

    public CartListAdapter(ArrayList<DrinkDomain> drinkDomains, Context context, AlterarNumeroItemsLista alterarNumeroItemsLista) {

        this.drinkDomains = drinkDomains;
        managementCart = new GestaoCard(context);
        this.alterarNumeroItemsLista = alterarNumeroItemsLista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_card, parent, false);

        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(drinkDomains.get(position).getTitle());
        holder.feeEachItem.setText(String.valueOf(drinkDomains.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((drinkDomains.get(position).getNumberInCard() * drinkDomains.get(position).getFee()) * 100.0) / 100.0));
        holder.num.setText(String.valueOf(drinkDomains.get(position).getNumberInCard()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(drinkDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext())
                .load(drawableResourceId)
                .into(holder.pic);


        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.plusNumberDrink(drinkDomains, position, new AlterarNumeroItemsLista() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        alterarNumeroItemsLista.changed();
                    }
                });
            }
        });

        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                managementCart.MinusNumerDrink(drinkDomains, position, new AlterarNumeroItemsLista() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        alterarNumeroItemsLista.changed();
                    }
                });
            }
        });

    }


    @Override
    public int getItemCount() {
        return drinkDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, feeEachItem;
        ImageView pic, plusItem, minusItem;
        TextView totalEachItem, num;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title2Txt);
            feeEachItem = itemView.findViewById(R.id.taxaItem);
            pic = itemView.findViewById(R.id.imgCard);
            totalEachItem = itemView.findViewById(R.id.total_Item);
            num = itemView.findViewById(R.id.numberItemTxt);
            plusItem = itemView.findViewById(R.id.plusCardBtn);
            minusItem = itemView.findViewById(R.id.minusCardBtn);
        }
    }
}
