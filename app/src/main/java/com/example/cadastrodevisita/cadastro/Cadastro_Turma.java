package com.example.cadastrodevisita.cadastro;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.TurmaDAO;
import com.example.cadastrodevisita.model.Turma;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Turma extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro de turma";

    private TextInputLayout campo_turma;
    private final TurmaDAO dao = new TurmaDAO();
    private Turma turma = new Turma();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);

        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();

    }

    private void inicializacaoDosCampos() {
        campo_turma = findViewById(R.id.turma_cadastro);
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
        preencheTurma();
        dao.salva(turma);
        finish();
    }

    private void preencheTurma() {
        String turma_preenchida = campo_turma.getEditText().getText().toString();
        turma.setTurma(turma_preenchida);
    }
}