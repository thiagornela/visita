package com.example.cadastrodevisita.cadastro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.TipoAtendimentoDAO;
import com.example.cadastrodevisita.model.TipoAtendimento;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Tipo_Atendimento extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro tipo de atendimento";

    private TextInputLayout campo_tipoAtendimento;
    private final TipoAtendimentoDAO dao = new TipoAtendimentoDAO();
    private TipoAtendimento tipoAtendimento = new TipoAtendimento();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tipo_atendimento);

        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();

    }

    private void inicializacaoDosCampos() {
        campo_tipoAtendimento = findViewById(R.id.tipo_atendimento_cadastro);
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
        preencheTipoAtendimento();
        dao.salva(tipoAtendimento);
        finish();
    }

    private void preencheTipoAtendimento() {
        String tipoAtendimento_preenchido = campo_tipoAtendimento.getEditText().getText().toString();
        tipoAtendimento.setAtendimento(tipoAtendimento_preenchido);
    }
}