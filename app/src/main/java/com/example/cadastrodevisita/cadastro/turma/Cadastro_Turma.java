package com.example.cadastrodevisita.cadastro.turma;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.adapter.TurmaAdapter;
import com.example.cadastrodevisita.adapter.TurmaAdapter;
import com.example.cadastrodevisita.cadastro.turma.Cadastro_Turma;
import com.example.cadastrodevisita.cadastro.turma.dialog.EditaDialog_Turma;
import com.example.cadastrodevisita.cadastro.turma.dialog.SalvaDialog_Turma;
import com.example.cadastrodevisita.dao.TurmaDAO;
import com.example.cadastrodevisita.model.Turma;
import com.example.cadastrodevisita.model.Turma;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Turma extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Turmas";
    private final TurmaDAO dao = new TurmaDAO();
    private ListView listView;
    TurmaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turma);
        setTitle(TITULO_APPBAR);
        configuraFabSalvaProduto();
        configuraLista();
    }

    private void configuraFabSalvaProduto() {
        FloatingActionButton fabAdicionaEmailEquipe = findViewById(R.id.fab_adiciona_turma);
        fabAdicionaEmailEquipe.setOnClickListener(v -> abreFormularioSalva());
    }

    private void abreFormularioSalva() {
        new SalvaDialog_Turma(this, this::salva).mostra();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualiza();
    }

    private void configuraLista() {
        listView = findViewById(R.id.listview_turma);

        adapter = new TurmaAdapter(this, new TurmaAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int posicao, Turma turma) {
                Cadastro_Turma.this.abreFormularioEdita(posicao, turma);
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

    private void abreFormularioEdita(int posicao, Turma turma) {
        new EditaDialog_Turma(this, turma,
                turmaCriada -> edita(turmaCriada))
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

    private void salva(Turma turma){
        dao.salva(turma);
        adapter.atualiza();
    }

    private void edita(Turma turma){
        dao.edita(turma);
        adapter.atualiza();
    }
}