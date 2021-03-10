package com.example.cadastrodevisita.cadastro.unidade;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.model.Unidade;
import com.example.cadastrodevisita.validator.ConfiguraCampos;
import com.example.cadastrodevisita.validator.Validador;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import static com.example.cadastrodevisita.ui.Constantes.ALTERACAO_SUCESSO;
import static com.example.cadastrodevisita.ui.Constantes.CADASTRO_SUCESSO;
import static com.example.cadastrodevisita.ui.Constantes.CHAVE_MENSAGEM;
import static com.example.cadastrodevisita.ui.Constantes.CHAVE_UNIDADE;

public class TelaCadastro_Unidade extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Cadastro de unidades";

    private final UnidadeDAO dao = new UnidadeDAO();
    private Unidade unidade = new Unidade();
    private ConfiguraCampos configuracampos = new ConfiguraCampos();
    private TextInputLayout formularioUnidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_unidade);

        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        carregaUnidade();
    }

    private void inicializacaoDosCampos() {
        formularioUnidade = findViewById(R.id.unidade_cadastro);
        configuracampos.configuraCampoObrigatorio(formularioUnidade);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_visita_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_formulario_menu_salvar) {
            boolean formularioEstaValido = validaTodosCampos();
            if (formularioEstaValido) {
                try {
                    finalizaFormulario();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void carregaUnidade() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_UNIDADE)) {
            unidade = (Unidade) dados.getSerializableExtra(CHAVE_UNIDADE);
            preencheCampos();
        } else {
            unidade = new Unidade();
        }
    }

    private void preencheCampos() {
        formularioUnidade.getEditText().setText(unidade.getUnidade());
    }

    private void finalizaFormulario() throws IOException {
        preencheUnidadeFinalizada();
        if (unidade.temIdValido()) {
            dao.edita(unidade);
            Toast toast = Toast.makeText(this, ALTERACAO_SUCESSO, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
            toast.show();
        } else {
            dao.salva(unidade);
            Toast toast = Toast.makeText(this, CADASTRO_SUCESSO, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
            toast.show();
        }
        finish();
    }

    private void preencheUnidadeFinalizada() {
        String unidadePreenchida = formularioUnidade.getEditText().getText().toString();
        unidade.setUnidade(unidadePreenchida);
    }

    private boolean validaTodosCampos() {
        boolean formularioEstaValido = true;
        for (Validador validador : configuracampos.validadores) {
            if (!validador.estaValido()) formularioEstaValido = false;
        }
        return formularioEstaValido;
    }

}