package com.example.cadastrodevisita.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.cadastrodevisita.R;

public class Activity_Informar_Atendimento extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Informar atendimento";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informar__atendimento);

        setTitle(TITULO_APPBAR);
    }
}