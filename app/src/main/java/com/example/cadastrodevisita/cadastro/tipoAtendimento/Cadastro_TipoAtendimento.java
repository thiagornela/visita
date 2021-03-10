package com.example.cadastrodevisita.cadastro.tipoAtendimento;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.adapter.TipoAtendimentoAdapter;
import com.example.cadastrodevisita.adapter.TipoAtendimentoAdapter;
import com.example.cadastrodevisita.cadastro.tipoAtendimento.Cadastro_TipoAtendimento;
import com.example.cadastrodevisita.cadastro.tipoAtendimento.dialog.EditaDialog_TipoAtendimento;
import com.example.cadastrodevisita.cadastro.tipoAtendimento.dialog.SalvaDialog_TipoAtendimento;
import com.example.cadastrodevisita.dao.TipoAtendimentoDAO;
import com.example.cadastrodevisita.model.TipoAtendimento;
import com.example.cadastrodevisita.model.TipoAtendimento;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_TipoAtendimento extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Tipo de atendimento";
    private final TipoAtendimentoDAO dao = new TipoAtendimentoDAO();
    private ListView listView;
    TipoAtendimentoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_tipo_atendimento);
        setTitle(TITULO_APPBAR);
        configuraFabSalvaProduto();
        configuraLista();
    }

    private void configuraFabSalvaProduto() {
        FloatingActionButton fabAdicionaEmailEquipe = findViewById(R.id.fab_adiciona_tipoAtendimento);
        fabAdicionaEmailEquipe.setOnClickListener(v -> abreFormularioSalva());
    }

    private void abreFormularioSalva() {
        new SalvaDialog_TipoAtendimento(this, this::salva).mostra();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualiza();
    }

    private void configuraLista() {
        listView = findViewById(R.id.listview_tipoAtendimento);

        adapter = new TipoAtendimentoAdapter(this, new TipoAtendimentoAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int posicao, TipoAtendimento tipoAtendimento) {
                Cadastro_TipoAtendimento.this.abreFormularioEdita(posicao, tipoAtendimento);
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

    private void abreFormularioEdita(int posicao, TipoAtendimento tipoAtendimento) {
        new EditaDialog_TipoAtendimento(this, tipoAtendimento,
                tipoAtendimentoCriada -> edita(tipoAtendimentoCriada))
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

    private void salva(TipoAtendimento tipoAtendimento){
        dao.salva(tipoAtendimento);
        adapter.atualiza();
    }

    private void edita(TipoAtendimento tipoAtendimento){
        dao.edita(tipoAtendimento);
        adapter.atualiza();
    }
}