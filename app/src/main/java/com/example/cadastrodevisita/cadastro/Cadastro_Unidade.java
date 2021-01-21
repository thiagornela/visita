package com.example.cadastrodevisita.cadastro;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.model.Unidade;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Unidade extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro de unidades";

    private TextInputLayout campo_unidade;
    private final UnidadeDAO dao = new UnidadeDAO();
    private Unidade unidade = new Unidade();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_unidade);

        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();

    }

    private void inicializacaoDosCampos() {
        campo_unidade = findViewById(R.id.unidade_cadastro);
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
        preencheUnidade();
        dao.salva(unidade);
        finish();
    }

    private void preencheUnidade() {
        String unidade_preenchida = campo_unidade.getEditText().getText().toString();
        unidade.setUnidade(unidade_preenchida);
    }

}