package com.example.cadastrodevisita.cadastro.categoria;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.adapter.CategoriaAdapter;
import com.example.cadastrodevisita.cadastro.categoria.dialog.EditaDialog_Categoria;
import com.example.cadastrodevisita.cadastro.categoria.dialog.SalvaDialog_Categoria;
import com.example.cadastrodevisita.dao.CategoriaDAO;
import com.example.cadastrodevisita.model.Categoria;
import com.example.cadastrodevisita.validator.ConfiguraCampos;
import com.example.cadastrodevisita.validator.Validador;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class Cadastro_Categoria extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Categorias";
    private CategoriaDAO dao = new CategoriaDAO();
    private ListView listView;
    CategoriaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__categoria);
        setTitle(TITULO_APPBAR);
        configuraFabSalvaProduto();
        configuraLista();
    }

    private void configuraFabSalvaProduto() {
        FloatingActionButton fabAdicionaEmailEquipe = findViewById(R.id.fab_adiciona_categoria);
        fabAdicionaEmailEquipe.setOnClickListener(v -> abreFormularioSalva());
    }

    private void abreFormularioSalva() {
        new SalvaDialog_Categoria(this, this::salva).mostra();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualiza();
    }

    private void configuraLista() {
        listView = findViewById(R.id.listview_categoria);

        adapter = new CategoriaAdapter(this, new CategoriaAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int posicao, Categoria categoria) {
                Cadastro_Categoria.this.abreFormularioEdita(posicao, categoria);
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

    private void abreFormularioEdita(int posicao, Categoria categoria) {
        new EditaDialog_Categoria(this, categoria,
                categoriaCriada -> edita(categoriaCriada))
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

    private void salva(Categoria categoria){
        dao.salva(categoria);
        adapter.atualiza();
    }

    private void edita(Categoria categoria){
        dao.edita(categoria);
        adapter.atualiza();
    }
}