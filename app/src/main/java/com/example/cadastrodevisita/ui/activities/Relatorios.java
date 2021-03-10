package com.example.cadastrodevisita.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cadastrodevisita.R;

public class Relatorios extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Relatórios";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);
        setTitle(TITULO_APPBAR);

    }
}