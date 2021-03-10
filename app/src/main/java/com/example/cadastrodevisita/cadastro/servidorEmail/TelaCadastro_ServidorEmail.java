package com.example.cadastrodevisita.cadastro.servidorEmail;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
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
import static com.example.cadastrodevisita.ui.Constantes.CHAVE_SERVIDOR_EMAIL;

public class TelaCadastro_ServidorEmail extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Servidor de E-mails";

    private ServidorEmail servidorEmail;
    private AutoCompleteTextView spinnerUnidade;
    private TextInputLayout formularioUnidade;
    private TextInputLayout campo_usuario;
    private TextInputLayout campo_host;
    private TextInputLayout campo_porta;
    private TextInputLayout campo_senha;
    private ServidorEmailDAO dao = new ServidorEmailDAO();
    private ConfiguraCampos configuracampos = new ConfiguraCampos();
    UnidadeDAO unidadeDAO = new UnidadeDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__servidor__email);
        setTitle(TITULO_APPBAR);
        inicializacaoDosCampos();
        carregaServidorEmail();
    }

    private void inicializacaoDosCampos() {
        formularioUnidade = findViewById(R.id.servidorEmail_unidade);
        spinnerUnidade = findViewById(R.id.spinner_servidorEmail_unidade);
        ArrayAdapter<Unidade> arrayAdapterUnidade = new ArrayAdapter<>(this, android.R.layout.simple_selectable_list_item, unidadeDAO.todos());
        spinnerUnidade.setAdapter(arrayAdapterUnidade);
        configuracampos.configuraCampoObrigatorio(formularioUnidade);
        campo_usuario = findViewById(R.id.ServidorEmail_cadastro_usuario);
        configuracampos.configuraCampoObrigatorio(campo_usuario);
        campo_host = findViewById(R.id.ServidorEmail_host);
        configuracampos.configuraCampoObrigatorio(campo_host);
        campo_porta = findViewById(R.id.ServidorEmail_porta);
        configuracampos.configuraCampoObrigatorio(campo_porta);
        campo_senha = findViewById(R.id.ServidorEmail_cadastro_senha);
        configuracampos.configuraCampoObrigatorio(campo_senha);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater()
                .inflate(R.menu.activity_formulario_visita_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
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

    private void carregaServidorEmail() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_SERVIDOR_EMAIL)) {
            servidorEmail = (ServidorEmail) dados.getSerializableExtra(CHAVE_SERVIDOR_EMAIL);
            preencheCampos();
        } else {
            servidorEmail = new ServidorEmail();
        }
    }

    private void preencheCampos() {
        spinnerUnidade.setText(servidorEmail.getUnidade(), false);
        campo_usuario.getEditText().setText(servidorEmail.getUsuario());
        campo_host.getEditText().setText(servidorEmail.getHost());
        campo_porta.getEditText().setText(servidorEmail.getPorta());
        campo_senha.getEditText().setText(servidorEmail.getSenha());
    }

    private void finalizaFormulario() throws IOException {
        preencheServidorEmail();
        if (servidorEmail.temIdValido()) {
            dao.edita(servidorEmail);
            Toast toast = Toast.makeText(this, ALTERACAO_SUCESSO, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
            toast.show();
        } else {
            dao.salva(servidorEmail);
            Toast toast = Toast.makeText(this, CADASTRO_SUCESSO, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 50);
            toast.show();
        }
        finish();
    }

    private void preencheServidorEmail() {
        String unidade = spinnerUnidade.getEditableText().toString();
        String usuario = campo_usuario.getEditText().getText().toString();
        String host = campo_host.getEditText().getText().toString();
        String porta = campo_porta.getEditText().getText().toString();
        String senha = campo_senha.getEditText().getText().toString();
        servidorEmail.setUnidade(unidade);
        servidorEmail.setUsuario(usuario);
        servidorEmail.setHost(host);
        servidorEmail.setPorta(porta);
        servidorEmail.setSenha(senha);
    }

    private boolean validaTodosCampos() {
        boolean formularioEstaValido = true;
        for (Validador validador : configuracampos.validadores) {
            if (!validador.estaValido()) formularioEstaValido = false;
        }
        return formularioEstaValido;
    }
}