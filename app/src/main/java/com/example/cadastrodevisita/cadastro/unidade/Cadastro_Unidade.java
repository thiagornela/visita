package com.example.cadastrodevisita.cadastro.unidade;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.cadastro.mensagem.Cadastro_Mensagem;
import com.example.cadastrodevisita.cadastro.mensagem.TelaCadastro_Mensagem;
import com.example.cadastrodevisita.listview.MensagemView;
import com.example.cadastrodevisita.listview.UnidadeView;
import com.example.cadastrodevisita.model.Mensagem;
import com.example.cadastrodevisita.model.Unidade;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.cadastrodevisita.ui.Constantes.CHAVE_MENSAGEM;
import static com.example.cadastrodevisita.ui.Constantes.CHAVE_UNIDADE;

public class Cadastro_Unidade extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Unidades";
    private UnidadeView unidadeView= new UnidadeView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__unidade);
        setTitle(TITULO_APPBAR);
        configuraFabSalva();
        configuraLista();
    }

    private void configuraFabSalva() {
        FloatingActionButton fabAdiciona = findViewById(R.id.fab_adiciona_unidade);
        fabAdiciona.setOnClickListener(v -> abreFormularioSalva());
    }

    private void abreFormularioSalva() {
        Intent intent = new Intent(Cadastro_Unidade.this, TelaCadastro_Unidade.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        unidadeView.atualizaUnidades();
    }

    private void configuraLista() {
        ListView listView = findViewById(R.id.listview_unidadeCadastro);
        unidadeView.configuraAdapter(listView);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            Unidade unidadeDevolvida = (Unidade) parent.getItemAtPosition(position);
            abreFormularioEdita(unidadeDevolvida);
        });
    }

    private void abreFormularioEdita(Unidade unidadeDevolvida) {
        Intent intent = new Intent(Cadastro_Unidade.this, TelaCadastro_Unidade.class);
        intent.putExtra(CHAVE_UNIDADE, unidadeDevolvida);
        startActivity(intent);
    }
}