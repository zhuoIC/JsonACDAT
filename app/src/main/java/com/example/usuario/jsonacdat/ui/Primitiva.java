package com.example.usuario.jsonacdat.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.usuario.jsonacdat.R;

public class Primitiva extends AppCompatActivity {

    private static final String JSON = "http://alumno.mobi/~alumno/superior/alumno/primitiva.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primitiva);
    }
}
