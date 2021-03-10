package com.example.cadastrodevisita.cadastro.turno;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.adapter.TurnoAdapter;
import com.example.cadastrodevisita.adapter.TurnoAdapter;
import com.example.cadastrodevisita.cadastro.turno.Cadastro_Turno;
import com.example.cadastrodevisita.cadastro.turno.dialog.EditaDialog_Turno;
import com.example.cadastrodevisita.cadastro.turno.dialog.SalvaDialog_Turno;
import com.example.cadastrodevisita.dao.TurnoDAO;
import com.example.cadastrodevisita.model.Turno;
import com.example.cadastrodevisita.model.Turno;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Turno extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Turnos";
    private final TurnoDAO dao = new TurnoDAO();
    private ListView listView;
    TurnoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_turno);
        setTitle(TITULO_APPBAR);
        configuraFabSalvaProduto();
        configuraLista();
    }

    private void configuraFabSalvaProduto() {
        FloatingActionButton fabAdicionaEmailEquipe = findViewById(R.id.fab_adiciona_turno);
        fabAdicionaEmailEquipe.setOnClickListener(v -> abreFormularioSalva());
    }

    private void abreFormularioSalva() {
        new SalvaDialog_Turno(this, this::salva).mostra();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualiza();
    }

    private void configuraLista() {
        listView = findViewById(R.id.listview_turno);

        adapter = new TurnoAdapter(this, new TurnoAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int posicao, Turno turno) {
                Cadastro_Turno.this.abreFormularioEdita(posicao, turno);
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

    private void abreFormularioEdita(int posicao, Turno turno) {
        new EditaDialog_Turno(this, turno,
                turnoCriada -> edita(turnoCriada))
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

    private void salva(Turno turno){
        dao.salva(turno);
        adapter.atualiza();
    }

    private void edita(Turno turno){
        dao.edita(turno);
        adapter.atualiza();
    }
}