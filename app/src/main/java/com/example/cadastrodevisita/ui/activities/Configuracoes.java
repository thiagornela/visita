package com.example.cadastrodevisita.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cadastrodevisita.R;
import com.example.cadastrodevisita.cadastro.email.Cadastro_Emails_Equipe;
import com.example.cadastrodevisita.cadastro.mensagem.Cadastro_Mensagem;
import com.example.cadastrodevisita.cadastro.servidorEmail.Cadastro_ServidorEmail;
import com.example.cadastrodevisita.cadastro.servidorEmail.TelaCadastro_ServidorEmail;

public class Configuracoes extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Configurações";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        setTitle(TITULO_APPBAR);

        Button configGerais = findViewById(R.id.config_cadastrosGerais);
        Button servidorEmail = findViewById(R.id.config_ServidorEmails);
        Button mensagemAgradecimento = findViewById(R.id.config_MensagemAgradecimento);
        Button emailsEquipe = findViewById(R.id.config_EmailsEquipe);

        configGerais.setOnClickListener(v -> vaiParaCadastrosGerais());
        servidorEmail.setOnClickListener(v -> vaiParaServidorEmail());
        mensagemAgradecimento.setOnClickListener(v -> vaiParaMensagemAgradecimento());
        emailsEquipe.setOnClickListener(v -> vaiParaEmailsEquipe());

    }

    private void vaiParaCadastrosGerais() {
        Intent intent = new Intent(Configuracoes.this, CadastrosGerais.class);
        startActivity(intent);
    }

    private void vaiParaServidorEmail() {
        Intent intent = new Intent(Configuracoes.this, Cadastro_ServidorEmail.class);
        startActivity(intent);
    }

    private void vaiParaMensagemAgradecimento() {
        Intent intent = new Intent(Configuracoes.this, Cadastro_Mensagem.class);
        startActivity(intent);
    }

    private void vaiParaEmailsEquipe() {
        Intent intent = new Intent(Configuracoes.this, Cadastro_Emails_Equipe.class);
        startActivity(intent);
    }
}
