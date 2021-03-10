package com.example.cadastrodevisita.cadastro.comoNosConheceu;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.adapter.ComoNosConheceuAdapter;
import com.example.cadastrodevisita.cadastro.comoNosConheceu.Cadastro_ComoNosConheceu;
import com.example.cadastrodevisita.cadastro.comoNosConheceu.dialog.EditaDialog_ComoNosConheceu;
import com.example.cadastrodevisita.cadastro.comoNosConheceu.dialog.SalvaDialog_ComoNosConheceu;
import com.example.cadastrodevisita.dao.ComoNosConheceuDAO;
import com.example.cadastrodevisita.model.ComoNosConheceu;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_ComoNosConheceu extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Como nos conheceu";

    private final ComoNosConheceuDAO dao = new ComoNosConheceuDAO();
    private ListView listView;
    ComoNosConheceuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_como_nos_conheceu);
        setTitle(TITULO_APPBAR);
        configuraFabSalvaProduto();
        configuraLista();
    }

    private void configuraFabSalvaProduto() {
        FloatingActionButton fabAdicionaEmailEquipe = findViewById(R.id.fab_adiciona_comoNosConheceu);
        fabAdicionaEmailEquipe.setOnClickListener(v -> abreFormularioSalva());
    }

    private void abreFormularioSalva() {
        new SalvaDialog_ComoNosConheceu(this, this::salva).mostra();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualiza();
    }

    private void configuraLista() {
        listView = findViewById(R.id.listview_comoNosConheceu);

        adapter = new ComoNosConheceuAdapter(this, new ComoNosConheceuAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int posicao, ComoNosConheceu comoNosConheceu) {
                Cadastro_ComoNosConheceu.this.abreFormularioEdita(posicao, comoNosConheceu);
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

    private void abreFormularioEdita(int posicao, ComoNosConheceu comoNosConheceu) {
        new EditaDialog_ComoNosConheceu(this, comoNosConheceu,
                comoNosConheceuCriada -> edita(comoNosConheceuCriada))
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

    private void salva(ComoNosConheceu comoNosConheceu){
        dao.salva(comoNosConheceu);
        adapter.atualiza();
    }

    private void edita(ComoNosConheceu comoNosConheceu){
        dao.edita(comoNosConheceu);
        adapter.atualiza();
    }
}