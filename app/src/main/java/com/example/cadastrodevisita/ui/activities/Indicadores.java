package com.example.cadastrodevisita.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cadastrodevisita.R;

public class Indicadores extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Indicadores";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicadores);
        setTitle(TITULO_APPBAR);
    }
}