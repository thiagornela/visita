package com.example.cadastrodevisita.cadastro.servidorEmail;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.listview.ServidorEmailView;
import com.example.cadastrodevisita.model.ServidorEmail;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.cadastrodevisita.ui.Constantes.CHAVE_SERVIDOR_EMAIL;

public class Cadastro_ServidorEmail extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Servidor de E-mails";
    private ServidorEmailView servidorEmailView = new ServidorEmailView(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro__servidoremail);
        setTitle(TITULO_APPBAR);
        configuraFabSalva();
        configuraLista();
    }

    private void configuraFabSalva() {
        FloatingActionButton fabAdiciona = findViewById(R.id.fab_adiciona_servidoremail);
        fabAdiciona.setOnClickListener(v -> abreFormularioSalva());
    }

    private void abreFormularioSalva() {
        Intent intent = new Intent(Cadastro_ServidorEmail.this, TelaCadastro_ServidorEmail.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        servidorEmailView.atualizaServidores();
    }

    private void configuraLista() {
        ListView listView = findViewById(R.id.listview_servidoremail);
        servidorEmailView.configuraAdapter(listView);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            ServidorEmail servidorDevolvido = (ServidorEmail) parent.getItemAtPosition(position);
            abreFormularioEdita(servidorDevolvido);
        });
    }

    private void abreFormularioEdita(ServidorEmail servidorDevolvido) {
        Intent intent = new Intent(Cadastro_ServidorEmail.this, TelaCadastro_ServidorEmail.class);
        intent.putExtra(CHAVE_SERVIDOR_EMAIL, servidorDevolvido);
        startActivity(intent);
    }
}