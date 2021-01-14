package com.example.cadastrodevisita.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.SituacaoDAO;
import com.example.cadastrodevisita.model.Situacao;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Situacao extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro de situações das visitas";

    private TextInputLayout campo_situacao;
    private final SituacaoDAO dao = new SituacaoDAO();
    private Situacao situacao = new Situacao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_situacao);

        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();

    }

    private void inicializacaoDosCampos() {
        campo_situacao = findViewById(R.id.situacao_cadastro);
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
        preencheSituacao();
        dao.salva(situacao);
        finish();
    }

    private void preencheSituacao() {
        String situacao_preenchida = campo_situacao.getEditText().getText().toString();
        situacao.setSituacao(situacao_preenchida);
    }
}