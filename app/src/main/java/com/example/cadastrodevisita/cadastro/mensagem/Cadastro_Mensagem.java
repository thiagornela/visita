package com.example.cadastrodevisita.cadastro.mensagem;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.listview.MensagemView;
import com.example.cadastrodevisita.model.Mensagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.cadastrodevisita.ui.Constantes.CHAVE_MENSAGEM;

public class Cadastro_Mensagem extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Mensagens de agradecimento";
    private MensagemView MensagemView = new MensagemView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__mensagem);
        setTitle(TITULO_APPBAR);
        configuraFabSalva();
        configuraLista();
    }

    private void configuraFabSalva() {
        FloatingActionButton fabAdiciona = findViewById(R.id.fab_adiciona_mensagem);
        fabAdiciona.setOnClickListener(v -> abreFormularioSalva());
    }

    private void abreFormularioSalva() {
        Intent intent = new Intent(Cadastro_Mensagem.this, TelaCadastro_Mensagem.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MensagemView.atualizaMensagem();
    }

    private void configuraLista() {
        ListView listView = findViewById(R.id.listview_mensagem);
        MensagemView.configuraAdapter(listView);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Mensagem mensagemDevolvida = (Mensagem) parent.getItemAtPosition(position);
            abreFormularioEdita(mensagemDevolvida);
        });
    }

    private void abreFormularioEdita(Mensagem mensagemDevolvida) {
        Intent intent = new Intent(Cadastro_Mensagem.this, TelaCadastro_Mensagem.class);
        intent.putExtra(CHAVE_MENSAGEM, mensagemDevolvida);
        startActivity(intent);
    }
}