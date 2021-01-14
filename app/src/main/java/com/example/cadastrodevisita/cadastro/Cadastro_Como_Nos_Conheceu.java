package com.example.cadastrodevisita.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.ComoNosConheceuDAO;
import com.example.cadastrodevisita.model.ComoNosConheceu;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Como_Nos_Conheceu extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro como nos conheceu";

    private TextInputLayout campo_comoNosConheceu;
    private final ComoNosConheceuDAO dao = new ComoNosConheceuDAO();
    private ComoNosConheceu comoNosConheceu = new ComoNosConheceu();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_como_nos_conheceu);

        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();

    }

    private void inicializacaoDosCampos() {
        campo_comoNosConheceu = findViewById(R.id.como_nos_conheceu_cadastro);
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
        preencheComoNosConheceu();
        dao.salva(comoNosConheceu);
        finish();
    }

    private void preencheComoNosConheceu() {
        String comoNosConheceu_preenchida = campo_comoNosConheceu.getEditText().getText().toString();
        comoNosConheceu.setComoNosConheceu(comoNosConheceu_preenchida);
    }
}