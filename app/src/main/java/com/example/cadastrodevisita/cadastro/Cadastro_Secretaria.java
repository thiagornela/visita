package com.example.cadastrodevisita.cadastro;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.SecretariaDAO;
import com.example.cadastrodevisita.model.Secretaria;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Secretaria extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro das secretárias";

    private TextInputLayout campo_secretaria;
    private final SecretariaDAO dao = new SecretariaDAO();
    private Secretaria secretaria = new Secretaria();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_secretaria);

        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();

    }

    private void inicializacaoDosCampos() {
        campo_secretaria = findViewById(R.id.secretaria_cadastro);
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
        preencheSecretaria();
        dao.salva(secretaria);
        finish();
    }

    private void preencheSecretaria() {
        String secretaria_preenchida = campo_secretaria.getEditText().getText().toString();
        secretaria.setSecretaria(secretaria_preenchida);
    }
}