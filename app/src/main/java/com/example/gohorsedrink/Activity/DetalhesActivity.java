package com.example.gohorsedrink.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.gohorsedrink.Domain.DrinkDomain;
import com.example.gohorsedrink.Helper.GestaoCard;
import com.example.gohorsedrink.R;

public class DetalhesActivity extends AppCompatActivity {

    private TextView btn_addToCard;
    private TextView txt_title, txt_preco, txt_description, txt_numeroQtd;
    private ImageView btn_Add, btn_menos, FotoDrink;
    private DrinkDomain object;
    private int numberQtd = 1;
    private GestaoCard gestaoCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        gestaoCard = new GestaoCard(this);

        iniView();
        getBundle();
    }

    private void getBundle() {

        object = (DrinkDomain) getIntent().getSerializableExtra("object");

        int drawableResourceId= this.getResources().getIdentifier(object.getPic(), "drawable", this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(FotoDrink);

        txt_title.setText(object.getTitle());
        txt_preco.setText("R$ " + object.getFee());
        txt_description.setText(object.getDescription());
        txt_numeroQtd.setText(String.valueOf(numberQtd));

        btn_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                numberQtd = numberQtd + 1;
                txt_numeroQtd.setText(String.valueOf(numberQtd));
            }
        });

        btn_menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (numberQtd>1){
                    numberQtd = numberQtd - 1;
                }

                txt_numeroQtd.setText(String.valueOf(numberQtd));
            }
        });

        btn_addToCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                object.setNumberInCard(numberQtd);
                gestaoCard.insertDrink(object);
            }
        });
    }

    private void iniView() {
        btn_addToCard= findViewById(R.id.add_card);
        txt_title= findViewById(R.id.txt_title);
        txt_description= findViewById(R.id.txt_description);
        txt_preco= findViewById(R.id.txt_preco);
        txt_numeroQtd= findViewById(R.id.numeroQtd);
        btn_Add = findViewById(R.id.btn_mais);
        btn_menos= findViewById(R.id.btn_menos);
        FotoDrink = findViewById(R.id.drink_foto);
    }
}
