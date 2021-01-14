package com.example.cadastrodevisita.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.TurnoDAO;
import com.example.cadastrodevisita.model.Turno;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Turno extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro de turno";

    private TextInputLayout campo_turno;
    private final TurnoDAO dao = new TurnoDAO();
    private Turno turno = new Turno();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turno);

        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();

    }

    private void inicializacaoDosCampos() {
        campo_turno = findViewById(R.id.turno_cadastro);
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
        preencheTurno();
        dao.salva(turno);
        finish();
    }

    private void preencheTurno() {
        String turno_preenchido = campo_turno.getEditText().getText().toString();
        turno.setTurno(turno_preenchido);
    }
}