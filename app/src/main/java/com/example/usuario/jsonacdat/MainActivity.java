package com.example.usuario.jsonacdat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.usuario.jsonacdat.ui.CreacionJSON;
import com.example.usuario.jsonacdat.ui.ListaContactos;
import com.example.usuario.jsonacdat.ui.ListaContactosGSON;
import com.example.usuario.jsonacdat.ui.Primitiva;
import com.example.usuario.jsonacdat.ui.Repositorios;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnPrimitiva,
            btnContactos,
            btnContactosGSON,
            btnCreacionJSON,
            btnRepositorios;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnPrimitiva = findViewById(R.id.btnPrimitiva);
        btnContactos = findViewById(R.id.btnContactos);
        btnCreacionJSON = findViewById(R.id.btnCreacionJSON);
        btnContactosGSON = findViewById(R.id.btnContactosGSON);
        btnRepositorios = findViewById(R.id.btnRepositorios);
        btnContactos.setOnClickListener(this);
        btnPrimitiva.setOnClickListener(this);
        btnContactosGSON.setOnClickListener(this);
        btnCreacionJSON.setOnClickListener(this);
        btnRepositorios.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        if (view == btnPrimitiva){
            intent = new Intent(this, Primitiva.class);
            startActivity(intent);
        }
        if (view == btnContactos){
            intent = new Intent(this, ListaContactos.class);
            startActivity(intent);
        }
        if (view == btnContactosGSON){
            intent = new Intent(this, ListaContactosGSON.class);
            startActivity(intent);
        }
        if (view == btnCreacionJSON){
            intent = new Intent(this, CreacionJSON.class);
            startActivity(intent);
        }
        if (view == btnRepositorios){
            intent = new Intent(this, Repositorios.class);
            startActivity(intent);
        }
    }
}