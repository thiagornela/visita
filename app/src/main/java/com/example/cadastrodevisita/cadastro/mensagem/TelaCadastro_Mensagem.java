package com.example.cadastrodevisita.cadastro.mensagem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.dao.MensagemDAO;
import com.example.cadastrodevisita.dao.ServidorEmailDAO;
import com.example.cadastrodevisita.dao.UnidadeDAO;
import com.example.cadastrodevisita.model.Mensagem;
import com.example.cadastrodevisita.model.ServidorEmail;
import com.example.cadastrodevisita.model.Unidade;
import com.example.cadastrodevisita.validator.ConfiguraCampos;
import com.example.cadastrodevisita.validator.Validador;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

import static com.example.cadastrodevisita.ui.Constantes.ALTERACAO_SUCESSO;
import static com.example.cadastrodevisita.ui.Constantes.CADASTRO_SUCESSO;
import static com.example.cadastrodevisita.ui.Constantes.CHAVE_MENSAGEM;

public class TelaCadastro_Mensagem extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Mensagem de agradecimento";

    UnidadeDAO unidadeDAO = new UnidadeDAO();
    ServidorEmailDAO servidorEmailDAO = new ServidorEmailDAO();
    MensagemDAO dao = new MensagemDAO();
    Mensagem mensagem = new Mensagem();
    private ConfiguraCampos configuracampos = new ConfiguraCampos();
    private TextInputLayout formularioUnidade;
    private AutoCompleteTextView spinnerUnidade;
    private TextInputLayout formularioServidor;
    private AutoCompleteTextView spinnerServidor;
    private TextInputLayout formularioAssunto;
    private TextInputLayout formularioMensagemAgradecimento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telacadastro_mensagem);
        setTitle(TITULO_APPBAR);
        inicializaCampos();
        carregaMensagem();
    }

    private void inicializaCampos() {
        formularioUnidade = findViewById(R.id.mensagem_unidade);
        spinnerUnidade = findViewById(R.id.spinner_mensagem_unidade);
        ArrayAdapter<Unidade> arrayAdapterUnidade = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, unidadeDAO.todos());
        spinnerUnidade.setAdapter(arrayAdapterUnidade);
        configuracampos.configuraCampoObrigatorio(formularioUnidade);

        formularioServidor = findViewById(R.id.mensagem_servidorRemetente);
        spinnerServidor = findViewById(R.id.spinner_servidorRemetente);
        ArrayAdapter<ServidorEmail> arrayAdapterServidor = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, servidorEmailDAO.todos());
        spinnerServidor.setAdapter(arrayAdapterServidor);
        configuracampos.configuraCampoObrigatorio(formularioServidor);

        formularioAssunto = findViewById(R.id.mensagem_assunto);
        configuracampos.configuraCampoObrigatorio(formularioAssunto);
        formularioMensagemAgradecimento = findViewById(R.id.mensagem_mensagemAgradecimento);
        configuracampos.configuraCampoObrigatorio(formularioMensagemAgradecimento);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_visita_menu, menu);
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

    private void carregaMensagem() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_MENSAGEM)) {
            mensagem = (Mensagem) dados.getSerializableExtra(CHAVE_MENSAGEM);
            preencheCampos();
        } else {
            mensagem = new Mensagem();
        }
    }

    private void preencheCampos() {
        spinnerUnidade.setText(mensagem.getUnidade(), false);
        spinnerServidor.setText(mensagem.getServidor(), false);
        formularioAssunto.getEditText().setText(mensagem.getAssunto());
        formularioMensagemAgradecimento.getEditText().setText(mensagem.getMensagem());
    }

    private void finalizaFormulario() throws IOException {
        preencheMensagemFinalizada();
        if (mensagem.temIdValido()) {
            dao.edita(mensagem);
            Toast toast = Toast.makeText(this, ALTERACAO_SUCESSO, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
            toast.show();
        } else {
            dao.salva(mensagem);
            Toast toast = Toast.makeText(this, CADASTRO_SUCESSO, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
            toast.show();
        }
        finish();
    }

    private void preencheMensagemFinalizada() {
        String unidade = spinnerUnidade.getEditableText().toString();
        String servidor = spinnerServidor.getEditableText().toString();
        String assunto = formularioAssunto.getEditText().getText().toString();
        String mensagemAgradecimento = formularioMensagemAgradecimento.getEditText().getText().toString();

        mensagem.setUnidade(unidade);
        mensagem.setServidor(servidor);
        mensagem.setAssunto(assunto);
        mensagem.setMensagem(mensagemAgradecimento);
    }

    private boolean validaTodosCampos() {
        boolean formularioEstaValido = true;
        for (Validador validador : configuracampos.validadores) {
            if (!validador.estaValido()) formularioEstaValido = false;
        }
        return formularioEstaValido;
    }

}