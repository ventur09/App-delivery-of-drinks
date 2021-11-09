package com.example.gohorsedrink.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gohorsedrink.Adapater.CartListAdapter;
import com.example.gohorsedrink.Helper.GestaoCard;
import com.example.gohorsedrink.Interface.AlterarNumeroItemsLista;
import com.example.gohorsedrink.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CardListActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;
    private GestaoCard gestaoCard;
    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_list);

        gestaoCard = new GestaoCard(this);

        initView();
        initList();
        calculateCard();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.btn_home);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardListActivity.this, CardListActivity.class));
            }
        });

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CardListActivity.this, MainActivity.class));
            }
        });
    }

    private void  initList(){
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewList.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(gestaoCard.getListCard(), this, new AlterarNumeroItemsLista() {
            @Override
            public void changed() {
                calculateCard();
            }
        });

        recyclerViewList.setAdapter(adapter);
        if(gestaoCard.getListCard().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }



    private void calculateCard() {
        double percentTax = 0.00;
        double delivery = 0;

        tax = Math.round((gestaoCard.getTotalFee() * percentTax) * 100.0) / 100.0;
        double total = Math.round((gestaoCard.getTotalFee() + tax + delivery) * 100.0) / 100.0;
        double itemTotal = Math.round(gestaoCard.getTotalFee() * 100.0) / 100.0;

        totalFeeTxt.setText("R$" + itemTotal);
        taxTxt.setText("R$" + tax);
        deliveryTxt.setText("R$" + delivery);
        totalTxt.setText("R$"+ total);
    }

    private void initView(){
        recyclerViewList = findViewById(R.id.recyclerview);
        totalFeeTxt = findViewById(R.id.totalFeeTxt);
        taxTxt = findViewById(R.id.taxTxt);
        deliveryTxt = findViewById(R.id.deliveryTxt);
        totalTxt = findViewById(R.id.totalTxt);
        emptyTxt = findViewById(R.id.emptyTxt);
        scrollView = findViewById(R.id.scrollView4);
    }
}

