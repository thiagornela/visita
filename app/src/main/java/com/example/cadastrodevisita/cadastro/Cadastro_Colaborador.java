package com.example.cadastrodevisita.cadastro;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.ColaboradorDAO;
import com.example.cadastrodevisita.model.Colaborador;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Colaborador extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro de colaborador";

    private TextInputLayout campo_colaborador;
    private final ColaboradorDAO dao = new ColaboradorDAO();
    private Colaborador colaborador = new Colaborador();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_colaborador);

        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();

    }

    private void inicializacaoDosCampos() {
        campo_colaborador = findViewById(R.id.colaborador_cadastro);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_visita_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finalizaFormulario();
        return super.onOptionsItemSelected(item);
    }

    private void finalizaFormulario() {
        preencheColaborador();
        dao.salva(colaborador);
        finish();
    }

    private void preencheColaborador() {
        String colaborador_preenchido = campo_colaborador.getEditText().getText().toString();
        colaborador.setColaborador(colaborador_preenchido);
    }
}