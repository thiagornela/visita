package com.example.cadastrodevisita.cadastro.email;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.adapter.EmailEquipeAdapter;
import com.example.cadastrodevisita.dao.EmailEquipeDAO;
import com.example.cadastrodevisita.model.EmailEquipe;
import com.example.cadastrodevisita.cadastro.email.dialog.EditaDialog_Email;
import com.example.cadastrodevisita.cadastro.email.dialog.SalvaDialog_Email;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Cadastro_Emails_Equipe extends AppCompatActivity {

    public static final String TITULO_APPBAR = "E-mails da equipe";
    private EmailEquipeDAO emailEquipeDAO = new EmailEquipeDAO();
    private ListView listaEmails;
    EmailEquipeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__emails__equipe);
        setTitle(TITULO_APPBAR);
        configuraFabSalvaProduto();
        configuraLista();
    }

    private void configuraFabSalvaProduto() {
        FloatingActionButton fabAdicionaEmailEquipe = findViewById(R.id.fab_adiciona_emailEquipe);
        fabAdicionaEmailEquipe.setOnClickListener(v -> abreFormularioSalva());
    }

    private void abreFormularioSalva() {
        new SalvaDialog_Email(this, this::salva).mostra();
    }

    @Override
    protected void onResume() {
        super.onResume();
        adapter.atualiza();
    }

    private void configuraLista() {
        listaEmails = findViewById(R.id.listview_emailEquipe);

        adapter = new EmailEquipeAdapter(this, new EmailEquipeAdapter.OnItemClickListener(){
            @Override
            public void onItemClick(int posicao, EmailEquipe emailEquipe) {
                Cadastro_Emails_Equipe.this.abreFormularioEdita(posicao, emailEquipe);
            }
        });

        listaEmails.setAdapter(adapter);

        listaEmails.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(Menu.NONE, 1, Menu.NONE, "Remover");
            }
        });
    }

    private void abreFormularioEdita(int posicao, EmailEquipe emailEquipe) {
        new EditaDialog_Email(this, emailEquipe,
                emailEquipeCriado -> edita(emailEquipeCriado))
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
        emailEquipeDAO.removeComPosicao(position);
        adapter.atualiza();
    }

    private void salva(EmailEquipe emailEquipe){
        emailEquipeDAO.salva(emailEquipe);
        adapter.atualiza();
    }

    private void edita(EmailEquipe emailEquipe){
        emailEquipeDAO.edita(emailEquipe);
        adapter.atualiza();
    }
}