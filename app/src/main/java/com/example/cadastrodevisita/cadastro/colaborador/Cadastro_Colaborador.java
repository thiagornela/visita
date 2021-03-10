package com.example.cadastrodevisita.cadastro.colaborador;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.adapter.CategoriaAdapter;
import com.example.cadastrodevisita.adapter.ColaboradorAdapter;
import com.example.cadastrodevisita.cadastro.colaborador.dialog.EditaDialog_Colaborador;
import com.example.cadastrodevisita.cadastro.colaborador.dialog.SalvaDialog_Colaborador;
import com.example.cadastrodevisita.dao.ColaboradorDAO;
import com.example.cadastrodevisita.model.Colaborador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Cadastro_Colaborador extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Funcionários";
    private final ColaboradorDAO dao = new ColaboradorDAO();
    private ListView listView;
    ColaboradorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_colaborador);
        setTitle(TITULO_APPBAR);
        configuraFabSalvaProduto();
        configuraLista();
    }

    private void configuraFabSalvaProduto() {
        FloatingActionButton fabAdicionaEmailEquipe = findViewById(R.id.fab_adiciona_colaborador);
        fabAdicionaEmailEquipe.setOnClickListener(v -> abreFormularioSalva());
    }

    private void abreFormularioSalva() {
        new SalvaDialog_Colaborador(this, this::salva).mostra();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualiza();
    }

    private void configuraLista() {
        listView = findViewById(R.id.listview_colaborador);

        adapter = new ColaboradorAdapter(this, new ColaboradorAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int posicao, Colaborador colaborador) {
                Cadastro_Colaborador.this.abreFormularioEdita(posicao, colaborador);
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

    private void abreFormularioEdita(int posicao, Colaborador colaborador) {
        new EditaDialog_Colaborador(this, colaborador,
                colaboradorCriada -> edita(colaboradorCriada))
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

    private void salva(Colaborador colaborador){
        dao.salva(colaborador);
        adapter.atualiza();
    }

    private void edita(Colaborador colaborador){
        dao.edita(colaborador);
        adapter.atualiza();
    }
}