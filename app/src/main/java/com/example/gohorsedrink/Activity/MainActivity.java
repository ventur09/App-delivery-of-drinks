package com.example.gohorsedrink.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.gohorsedrink.Adapater.CategoriaAdapter;
import com.example.gohorsedrink.Adapater.MaisPedidosAdapter;
import com.example.gohorsedrink.Domain.CategoriaDomain;
import com.example.gohorsedrink.Domain.DrinkDomain;
import com.example.gohorsedrink.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView.Adapter adapter, adapter2;
    private RecyclerView recyclerViewCategoryList, recyclerViewPopularList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewCategoryList();
        recyclerViewPopular();
        bottomNavigation();
    }

    private void bottomNavigation() {
        FloatingActionButton floatingActionButton = findViewById(R.id.card_btn);
        LinearLayout homeBtn = findViewById(R.id.btn_home);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CardListActivity.class));
            }
        });

//        homeBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(MainActivity.this, MainActivity.class));
//            }
//        });
    }

    private void recyclerViewPopular() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopularList = findViewById(R.id.recyclerView2);
        recyclerViewPopularList.setLayoutManager(linearLayoutManager);

        ArrayList<DrinkDomain> drinkList = new ArrayList<>();
        drinkList.add(new DrinkDomain("Água", "agua", "Pedrópolias Paulista", 2.43));
        drinkList.add(new DrinkDomain("Vinho ","vinho", "Argentino Tinto Seco Trapiche Malbec Mendoza 750ml", 50.43));
        drinkList.add(new DrinkDomain("Heineken", "heineken", "Cerveja HEINEKEN Garrafa 330ml", 5.99));
        drinkList.add(new DrinkDomain("Brahma", "brahma", "CERVEJA BRAHMA CHOPP 350ML - hiperideal", 3.43));
        drinkList.add(new DrinkDomain("Água", "agua", "Pedrópolias Paulista", 2.43));
        drinkList.add(new DrinkDomain("Vinho ","vinho", "Argentino Tinto Seco Trapiche Malbec Mendoza 750ml", 50.43));
        drinkList.add(new DrinkDomain("Heineken", "heineken", "Cerveja HEINEKEN Garrafa 330ml", 5.99));
        drinkList.add(new DrinkDomain("Brahma", "brahma", "CERVEJA BRAHMA CHOPP 350ML - hiperideal", 3.43));

        adapter2 = new MaisPedidosAdapter(drinkList);
        recyclerViewPopularList.setAdapter(adapter2);
    }

    private void recyclerViewCategoryList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewCategoryList = findViewById(R.id.recyclerView);
        recyclerViewCategoryList.setLayoutManager(linearLayoutManager);

        ArrayList<CategoriaDomain> categoryList = new ArrayList<>();
        categoryList.add(new CategoriaDomain("Cervejas", "cat_1"));
        categoryList.add(new CategoriaDomain("Vinhos", "cat_2"));
        categoryList.add(new CategoriaDomain(" Sucos e Águas ", "cat_3"));
//        categoryList.add(new CategoryDomain("Energéticos", "cat_4"));
        categoryList.add(new CategoriaDomain(" Destilados ", "cat_4"));
        categoryList.add(new CategoriaDomain(" Refrigerantes ", "cat_5"));

        adapter = new CategoriaAdapter(categoryList);
        recyclerViewCategoryList.setAdapter(adapter);
    }
}