package com.example.cadastrodevisita.cadastro.secretaria;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.adapter.SecretariaAdapter;
import com.example.cadastrodevisita.cadastro.secretaria.Cadastro_Secretaria;
import com.example.cadastrodevisita.cadastro.secretaria.dialog.EditaDialog_Secretaria;
import com.example.cadastrodevisita.cadastro.secretaria.dialog.SalvaDialog_Secretaria;
import com.example.cadastrodevisita.dao.SecretariaDAO;
import com.example.cadastrodevisita.model.Secretaria;
import com.example.cadastrodevisita.model.Secretaria;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Secretaria extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Secretárias";
    private final SecretariaDAO dao = new SecretariaDAO();
    private ListView listView;
    SecretariaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_secretaria);
        setTitle(TITULO_APPBAR);
        configuraFabSalvaProduto();
        configuraLista();
    }

    private void configuraFabSalvaProduto() {
        FloatingActionButton fabAdicionaEmailEquipe = findViewById(R.id.fab_adiciona_secretaria);
        fabAdicionaEmailEquipe.setOnClickListener(v -> abreFormularioSalva());
    }

    private void abreFormularioSalva() {
        new SalvaDialog_Secretaria(this, this::salva).mostra();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualiza();
    }

    private void configuraLista() {
        listView = findViewById(R.id.listview_secretaria);

        adapter = new SecretariaAdapter(this, new SecretariaAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int posicao, Secretaria secretaria) {
                Cadastro_Secretaria.this.abreFormularioEdita(posicao, secretaria);
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

    private void abreFormularioEdita(int posicao, Secretaria secretaria) {
        new EditaDialog_Secretaria(this, secretaria,
                secretariaCriada -> edita(secretariaCriada))
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

    private void salva(Secretaria secretaria){
        dao.salva(secretaria);
        adapter.atualiza();
    }

    private void edita(Secretaria secretaria){
        dao.edita(secretaria);
        adapter.atualiza();
    }
}