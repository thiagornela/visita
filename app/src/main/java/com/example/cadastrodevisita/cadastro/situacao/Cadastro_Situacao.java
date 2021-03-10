package com.example.cadastrodevisita.cadastro.situacao;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.adapter.SituacaoAdapter;
import com.example.cadastrodevisita.adapter.SituacaoAdapter;
import com.example.cadastrodevisita.cadastro.situacao.Cadastro_Situacao;
import com.example.cadastrodevisita.cadastro.situacao.dialog.EditaDialog_Situacao;
import com.example.cadastrodevisita.cadastro.situacao.dialog.SalvaDialog_Situacao;
import com.example.cadastrodevisita.dao.SituacaoDAO;
import com.example.cadastrodevisita.model.Situacao;
import com.example.cadastrodevisita.model.Situacao;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Situacao extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Situação das visitas";
    private final SituacaoDAO dao = new SituacaoDAO();
    private ListView listView;
    private SituacaoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_situacao);
        setTitle(TITULO_APPBAR);
        configuraFabSalvaProduto();
        configuraLista();
    }

    private void configuraFabSalvaProduto() {
        FloatingActionButton fabAdicionaEmailEquipe = findViewById(R.id.fab_adiciona_situacao);
        fabAdicionaEmailEquipe.setOnClickListener(v -> abreFormularioSalva());
    }

    private void abreFormularioSalva() {
        new SalvaDialog_Situacao(this, this::salva).mostra();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualiza();
    }

    private void configuraLista() {
        listView = findViewById(R.id.listview_situacao);

        adapter = new SituacaoAdapter(this, new SituacaoAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int posicao, Situacao situacao) {
                Cadastro_Situacao.this.abreFormularioEdita(posicao, situacao);
            }
        });

        listView.setAdapter(adapter);

        listView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(Menu.NONE, 1, Menu.NONE, "Remover");
            }
        });
    }

    private void abreFormularioEdita(int posicao, Situacao situacao) {
        new EditaDialog_Situacao(this, situacao,
                situacaoCriada -> edita(situacaoCriada))
                .mostra();
    }

    @Override public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = menuInfo.position;
        if(item.getItemId() == 1) {
            remover(position);
        }
        return super.onContextItemSelected(item);
    }

    private void remover(int position) {
        dao.removeComPosicao(position);
        adapter.atualiza();
    }

    private void salva(Situacao situacao){
        dao.salva(situacao);
        adapter.atualiza();
    }

    private void edita(Situacao situacao){
        dao.edita(situacao);
        adapter.atualiza();
    }
}