package com.example.gohorsedrink.Helper;

import android.content.Context;
import android.widget.Toast;

import com.example.gohorsedrink.Domain.DrinkDomain;
import com.example.gohorsedrink.Interface.AlterarNumeroItemsLista;

import java.util.ArrayList;


public class GestaoCard {

    private Context context;
    private SharedDB sharedDB;

    public GestaoCard(Context context) {
        this.context = context;
        this.sharedDB = new SharedDB(context);
    }

    public void insertDrink(DrinkDomain item){
        ArrayList<DrinkDomain> listDrink = getListCard();
        boolean existAlready = false;
        int n = 0;

        for (int i = 0;i<listDrink.size();i++){
            if(listDrink.get(i).getTitle().equals(item.getTitle())){
                existAlready = true;
                n = 1;
                break;
            }
        }

        if(existAlready){
            listDrink.get(n).setNumberInCard(item.getNumberInCard());
        }else {
            listDrink.add(item);
        }


        sharedDB.putListObject("CardList", listDrink);
        Toast.makeText(context, "Added To your Card", Toast.LENGTH_LONG).show();
    }

    public ArrayList<DrinkDomain> getListCard(){
        return sharedDB.getListObject("CardList");
    }

    public void plusNumberDrink(ArrayList<DrinkDomain> listDrink, int position, AlterarNumeroItemsLista alterarNumeroItemsLista){
        listDrink.get(position).setNumberInCard(listDrink.get(position).getNumberInCard() + 1);
        sharedDB.putListObject("CardList", listDrink);
        alterarNumeroItemsLista.changed();
    }
    public void MinusNumerDrink(ArrayList<DrinkDomain> listDrink, int position, AlterarNumeroItemsLista alterarNumeroItemsLista){
        if(listDrink.get(position).getNumberInCard() == 1){
            listDrink.remove(position);
        }else {
            listDrink.get(position).setNumberInCard(listDrink.get(position).getNumberInCard() - 1);
        }
        sharedDB.putListObject("CardList", listDrink);
        alterarNumeroItemsLista.changed();
    }

    // total preço
    public Double getTotalFee(){
        ArrayList<DrinkDomain> listDrink2 = getListCard();
        double  fee = 0;
        for (int i  = 0 ;i < listDrink2.size(); i++){
            fee = fee + (listDrink2.get(i).getFee() * listDrink2.get(i).getNumberInCard());
        }

        System.out.printf("\nTotal\n" +String.valueOf(fee));
        return fee;
    }
}

